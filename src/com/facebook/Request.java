package com.facebook;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.location.Location;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.ParcelFileDescriptor.AutoCloseInputStream;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.facebook.internal.AttributionIdentifiers;
import com.facebook.internal.Logger;
import com.facebook.internal.ServerProtocol;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.facebook.model.GraphMultiResult;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphObjectList;
import com.facebook.model.GraphPlace;
import com.facebook.model.GraphUser;
import com.facebook.model.OpenGraphAction;
import com.facebook.model.OpenGraphObject;
import com.facebook.model.OpenGraphObject.Factory;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Request
{
  private static final String ACCEPT_LANGUAGE_HEADER = "Accept-Language";
  private static final String ACCESS_TOKEN_PARAM = "access_token";
  private static final String ATTACHED_FILES_PARAM = "attached_files";
  private static final String ATTACHMENT_FILENAME_PREFIX = "file";
  private static final String BATCH_APP_ID_PARAM = "batch_app_id";
  private static final String BATCH_BODY_PARAM = "body";
  private static final String BATCH_ENTRY_DEPENDS_ON_PARAM = "depends_on";
  private static final String BATCH_ENTRY_NAME_PARAM = "name";
  private static final String BATCH_ENTRY_OMIT_RESPONSE_ON_SUCCESS_PARAM = "omit_response_on_success";
  private static final String BATCH_METHOD_PARAM = "method";
  private static final String BATCH_PARAM = "batch";
  private static final String BATCH_RELATIVE_URL_PARAM = "relative_url";
  private static final String CONTENT_TYPE_HEADER = "Content-Type";
  private static final String FORMAT_JSON = "json";
  private static final String FORMAT_PARAM = "format";
  private static final String ISO_8601_FORMAT_STRING = "yyyy-MM-dd'T'HH:mm:ssZ";
  public static final int MAXIMUM_BATCH_SIZE = 50;
  private static final String ME = "me";
  private static final String MIME_BOUNDARY = "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f";
  private static final String MY_ACTION_FORMAT = "me/%s";
  private static final String MY_FEED = "me/feed";
  private static final String MY_FRIENDS = "me/friends";
  private static final String MY_OBJECTS_FORMAT = "me/objects/%s";
  private static final String MY_PHOTOS = "me/photos";
  private static final String MY_STAGING_RESOURCES = "me/staging_resources";
  private static final String MY_VIDEOS = "me/videos";
  private static final String OBJECT_PARAM = "object";
  private static final String PICTURE_PARAM = "picture";
  private static final String SDK_ANDROID = "android";
  private static final String SDK_PARAM = "sdk";
  private static final String SEARCH = "search";
  private static final String STAGING_PARAM = "file";
  public static final String TAG = Request.class.getSimpleName();
  private static final String USER_AGENT_BASE = "FBAndroidSDK";
  private static final String USER_AGENT_HEADER = "User-Agent";
  private static final String VIDEOS_SUFFIX = "/videos";
  private static String defaultBatchApplicationId;
  private static volatile String userAgent;
  private static Pattern versionPattern = Pattern.compile("^/?v\\d+\\.\\d+/(.*)");
  private String batchEntryDependsOn;
  private String batchEntryName;
  private boolean batchEntryOmitResultOnSuccess = true;
  private Callback callback;
  private GraphObject graphObject;
  private String graphPath;
  private HttpMethod httpMethod;
  private String overriddenURL;
  private Bundle parameters;
  private Session session;
  private boolean skipClientToken = false;
  private Object tag;
  private String version;
  
  public Request()
  {
    this(null, null, null, null, null);
  }
  
  public Request(Session paramSession, String paramString)
  {
    this(paramSession, paramString, null, null, null);
  }
  
  public Request(Session paramSession, String paramString, Bundle paramBundle, HttpMethod paramHttpMethod)
  {
    this(paramSession, paramString, paramBundle, paramHttpMethod, null);
  }
  
  public Request(Session paramSession, String paramString, Bundle paramBundle, HttpMethod paramHttpMethod, Callback paramCallback)
  {
    this(paramSession, paramString, paramBundle, paramHttpMethod, paramCallback, null);
  }
  
  public Request(Session paramSession, String paramString1, Bundle paramBundle, HttpMethod paramHttpMethod, Callback paramCallback, String paramString2)
  {
    this.session = paramSession;
    this.graphPath = paramString1;
    this.callback = paramCallback;
    this.version = paramString2;
    setHttpMethod(paramHttpMethod);
    if (paramBundle != null) {}
    for (this.parameters = new Bundle(paramBundle);; this.parameters = new Bundle())
    {
      if (this.version == null) {
        this.version = ServerProtocol.getAPIVersion();
      }
      return;
    }
  }
  
  Request(Session paramSession, URL paramURL)
  {
    this.session = paramSession;
    this.overriddenURL = paramURL.toString();
    setHttpMethod(HttpMethod.GET);
    this.parameters = new Bundle();
  }
  
  private void addCommonParameters()
  {
    String str1;
    if (this.session != null)
    {
      if (!this.session.isOpened()) {
        throw new FacebookException("Session provided to a Request in un-opened state.");
      }
      if (!this.parameters.containsKey("access_token"))
      {
        str1 = this.session.getAccessToken();
        Logger.registerAccessToken(str1);
        this.parameters.putString("access_token", str1);
      }
    }
    for (;;)
    {
      this.parameters.putString("sdk", "android");
      this.parameters.putString("format", "json");
      return;
      if ((!this.skipClientToken) && (!this.parameters.containsKey("access_token")))
      {
        String str2 = Settings.getApplicationId();
        str1 = Settings.getClientToken();
        if ((!Utility.isNullOrEmpty(str2)) && (!Utility.isNullOrEmpty(str1)))
        {
          str1 = str2 + "|" + str1;
          this.parameters.putString("access_token", str1);
        }
        else
        {
          Log.d(TAG, "Warning: Sessionless Request needs token but missing either application ID or client token.");
        }
      }
    }
  }
  
  private String appendParametersToBaseUrl(String paramString)
  {
    Uri.Builder localBuilder = new Uri.Builder().encodedPath(paramString);
    Iterator localIterator = this.parameters.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      Object localObject = this.parameters.get(str);
      paramString = (String)localObject;
      if (localObject == null) {
        paramString = "";
      }
      if (isSupportedParameterType(paramString)) {
        localBuilder.appendQueryParameter(str, parameterToString(paramString).toString());
      } else if (this.httpMethod == HttpMethod.GET) {
        throw new IllegalArgumentException(String.format("Unsupported parameter type for GET request: %s", new Object[] { paramString.getClass().getSimpleName() }));
      }
    }
    return localBuilder.toString();
  }
  
  static HttpURLConnection createConnection(URL paramURL)
    throws IOException
  {
    paramURL = (HttpURLConnection)paramURL.openConnection();
    paramURL.setRequestProperty("User-Agent", getUserAgent());
    paramURL.setRequestProperty("Content-Type", getMimeContentType());
    paramURL.setRequestProperty("Accept-Language", Locale.getDefault().toString());
    paramURL.setChunkedStreamingMode(0);
    return paramURL;
  }
  
  public static Response executeAndWait(Request paramRequest)
  {
    paramRequest = executeBatchAndWait(new Request[] { paramRequest });
    if ((paramRequest == null) || (paramRequest.size() != 1)) {
      throw new FacebookException("invalid state: expected a single response");
    }
    return (Response)paramRequest.get(0);
  }
  
  public static List<Response> executeBatchAndWait(RequestBatch paramRequestBatch)
  {
    Validate.notEmptyAndContainsNoNulls(paramRequestBatch, "requests");
    try
    {
      HttpURLConnection localHttpURLConnection = toHttpConnection(paramRequestBatch);
      paramRequestBatch = executeConnectionAndWait(localHttpURLConnection, paramRequestBatch);
    }
    catch (Exception localException)
    {
      for (;;)
      {
        List localList = Response.constructErrorResponses(paramRequestBatch.getRequests(), null, new FacebookException(localException));
        runCallbacks(paramRequestBatch, localList);
        paramRequestBatch = localList;
      }
    }
    return paramRequestBatch;
  }
  
  public static List<Response> executeBatchAndWait(Collection<Request> paramCollection)
  {
    return executeBatchAndWait(new RequestBatch(paramCollection));
  }
  
  public static List<Response> executeBatchAndWait(Request... paramVarArgs)
  {
    Validate.notNull(paramVarArgs, "requests");
    return executeBatchAndWait(Arrays.asList(paramVarArgs));
  }
  
  public static RequestAsyncTask executeBatchAsync(RequestBatch paramRequestBatch)
  {
    Validate.notEmptyAndContainsNoNulls(paramRequestBatch, "requests");
    paramRequestBatch = new RequestAsyncTask(paramRequestBatch);
    paramRequestBatch.executeOnSettingsExecutor();
    return paramRequestBatch;
  }
  
  public static RequestAsyncTask executeBatchAsync(Collection<Request> paramCollection)
  {
    return executeBatchAsync(new RequestBatch(paramCollection));
  }
  
  public static RequestAsyncTask executeBatchAsync(Request... paramVarArgs)
  {
    Validate.notNull(paramVarArgs, "requests");
    return executeBatchAsync(Arrays.asList(paramVarArgs));
  }
  
  public static List<Response> executeConnectionAndWait(HttpURLConnection paramHttpURLConnection, RequestBatch paramRequestBatch)
  {
    List localList = Response.fromHttpConnection(paramHttpURLConnection, paramRequestBatch);
    Utility.disconnectQuietly(paramHttpURLConnection);
    int i = paramRequestBatch.size();
    if (i != localList.size()) {
      throw new FacebookException(String.format("Received %d responses while expecting %d", new Object[] { Integer.valueOf(localList.size()), Integer.valueOf(i) }));
    }
    runCallbacks(paramRequestBatch, localList);
    paramHttpURLConnection = new HashSet();
    Iterator localIterator = paramRequestBatch.iterator();
    while (localIterator.hasNext())
    {
      paramRequestBatch = (Request)localIterator.next();
      if (paramRequestBatch.session != null) {
        paramHttpURLConnection.add(paramRequestBatch.session);
      }
    }
    paramHttpURLConnection = paramHttpURLConnection.iterator();
    while (paramHttpURLConnection.hasNext()) {
      ((Session)paramHttpURLConnection.next()).extendAccessTokenIfNeeded();
    }
    return localList;
  }
  
  public static List<Response> executeConnectionAndWait(HttpURLConnection paramHttpURLConnection, Collection<Request> paramCollection)
  {
    return executeConnectionAndWait(paramHttpURLConnection, new RequestBatch(paramCollection));
  }
  
  public static RequestAsyncTask executeConnectionAsync(Handler paramHandler, HttpURLConnection paramHttpURLConnection, RequestBatch paramRequestBatch)
  {
    Validate.notNull(paramHttpURLConnection, "connection");
    paramHttpURLConnection = new RequestAsyncTask(paramHttpURLConnection, paramRequestBatch);
    paramRequestBatch.setCallbackHandler(paramHandler);
    paramHttpURLConnection.executeOnSettingsExecutor();
    return paramHttpURLConnection;
  }
  
  public static RequestAsyncTask executeConnectionAsync(HttpURLConnection paramHttpURLConnection, RequestBatch paramRequestBatch)
  {
    return executeConnectionAsync(null, paramHttpURLConnection, paramRequestBatch);
  }
  
  @Deprecated
  public static RequestAsyncTask executeGraphPathRequestAsync(Session paramSession, String paramString, Callback paramCallback)
  {
    return newGraphPathRequest(paramSession, paramString, paramCallback).executeAsync();
  }
  
  @Deprecated
  public static RequestAsyncTask executeMeRequestAsync(Session paramSession, GraphUserCallback paramGraphUserCallback)
  {
    return newMeRequest(paramSession, paramGraphUserCallback).executeAsync();
  }
  
  @Deprecated
  public static RequestAsyncTask executeMyFriendsRequestAsync(Session paramSession, GraphUserListCallback paramGraphUserListCallback)
  {
    return newMyFriendsRequest(paramSession, paramGraphUserListCallback).executeAsync();
  }
  
  @Deprecated
  public static RequestAsyncTask executePlacesSearchRequestAsync(Session paramSession, Location paramLocation, int paramInt1, int paramInt2, String paramString, GraphPlaceListCallback paramGraphPlaceListCallback)
  {
    return newPlacesSearchRequest(paramSession, paramLocation, paramInt1, paramInt2, paramString, paramGraphPlaceListCallback).executeAsync();
  }
  
  @Deprecated
  public static RequestAsyncTask executePostRequestAsync(Session paramSession, String paramString, GraphObject paramGraphObject, Callback paramCallback)
  {
    return newPostRequest(paramSession, paramString, paramGraphObject, paramCallback).executeAsync();
  }
  
  @Deprecated
  public static RequestAsyncTask executeStatusUpdateRequestAsync(Session paramSession, String paramString, Callback paramCallback)
  {
    return newStatusUpdateRequest(paramSession, paramString, paramCallback).executeAsync();
  }
  
  @Deprecated
  public static RequestAsyncTask executeUploadPhotoRequestAsync(Session paramSession, Bitmap paramBitmap, Callback paramCallback)
  {
    return newUploadPhotoRequest(paramSession, paramBitmap, paramCallback).executeAsync();
  }
  
  @Deprecated
  public static RequestAsyncTask executeUploadPhotoRequestAsync(Session paramSession, File paramFile, Callback paramCallback)
    throws FileNotFoundException
  {
    return newUploadPhotoRequest(paramSession, paramFile, paramCallback).executeAsync();
  }
  
  private static String getBatchAppId(RequestBatch paramRequestBatch)
  {
    if (!Utility.isNullOrEmpty(paramRequestBatch.getBatchApplicationId())) {
      paramRequestBatch = paramRequestBatch.getBatchApplicationId();
    }
    for (;;)
    {
      return paramRequestBatch;
      Iterator localIterator = paramRequestBatch.iterator();
      for (;;)
      {
        if (localIterator.hasNext())
        {
          paramRequestBatch = ((Request)localIterator.next()).session;
          if (paramRequestBatch != null)
          {
            paramRequestBatch = paramRequestBatch.getApplicationId();
            break;
          }
        }
      }
      paramRequestBatch = defaultBatchApplicationId;
    }
  }
  
  public static final String getDefaultBatchApplicationId()
  {
    return defaultBatchApplicationId;
  }
  
  private String getGraphPathWithVersion()
  {
    if (versionPattern.matcher(this.graphPath).matches()) {}
    for (String str = this.graphPath;; str = String.format("%s/%s", new Object[] { this.version, this.graphPath })) {
      return str;
    }
  }
  
  private static String getMimeContentType()
  {
    return String.format("multipart/form-data; boundary=%s", new Object[] { "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f" });
  }
  
  private static String getUserAgent()
  {
    if (userAgent == null) {
      userAgent = String.format("%s.%s", new Object[] { "FBAndroidSDK", "3.20.0/Unity.6.2.2" });
    }
    return userAgent;
  }
  
  private static boolean hasOnProgressCallbacks(RequestBatch paramRequestBatch)
  {
    boolean bool = true;
    Iterator localIterator = paramRequestBatch.getCallbacks().iterator();
    do
    {
      if (!localIterator.hasNext()) {
        break;
      }
    } while (!((RequestBatch.Callback)localIterator.next() instanceof RequestBatch.OnProgressCallback));
    for (;;)
    {
      return bool;
      paramRequestBatch = paramRequestBatch.iterator();
      for (;;)
      {
        if (paramRequestBatch.hasNext()) {
          if ((((Request)paramRequestBatch.next()).getCallback() instanceof OnProgressCallback)) {
            break;
          }
        }
      }
      bool = false;
    }
  }
  
  private static boolean isMeRequest(String paramString)
  {
    boolean bool2 = true;
    Matcher localMatcher = versionPattern.matcher(paramString);
    if (localMatcher.matches()) {
      paramString = localMatcher.group(1);
    }
    boolean bool1 = bool2;
    if (!paramString.startsWith("me/")) {
      if (!paramString.startsWith("/me/")) {
        break label49;
      }
    }
    label49:
    for (bool1 = bool2;; bool1 = false) {
      return bool1;
    }
  }
  
  private static boolean isSupportedAttachmentType(Object paramObject)
  {
    if (((paramObject instanceof Bitmap)) || ((paramObject instanceof byte[])) || ((paramObject instanceof ParcelFileDescriptor)) || ((paramObject instanceof ParcelFileDescriptorWithMimeType))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private static boolean isSupportedParameterType(Object paramObject)
  {
    if (((paramObject instanceof String)) || ((paramObject instanceof Boolean)) || ((paramObject instanceof Number)) || ((paramObject instanceof Date))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static Request newCustomAudienceThirdPartyIdRequest(Session paramSession, Context paramContext, Callback paramCallback)
  {
    return newCustomAudienceThirdPartyIdRequest(paramSession, paramContext, null, paramCallback);
  }
  
  public static Request newCustomAudienceThirdPartyIdRequest(Session paramSession, Context paramContext, String paramString, Callback paramCallback)
  {
    Object localObject1 = paramSession;
    if (paramSession == null) {
      localObject1 = Session.getActiveSession();
    }
    Object localObject2 = localObject1;
    if (localObject1 != null)
    {
      localObject2 = localObject1;
      if (!((Session)localObject1).isOpened()) {
        localObject2 = null;
      }
    }
    paramSession = paramString;
    if (paramString == null) {
      if (localObject2 == null) {
        break label68;
      }
    }
    label68:
    for (paramSession = ((Session)localObject2).getApplicationId(); paramSession == null; paramSession = Utility.getMetadataApplicationId(paramContext)) {
      throw new FacebookException("Facebook App ID cannot be determined");
    }
    String str = paramSession + "/custom_audience_third_party_id";
    paramString = AttributionIdentifiers.getAttributionIdentifiers(paramContext);
    localObject1 = new Bundle();
    if (localObject2 == null) {
      if (paramString.getAttributionId() == null) {
        break label188;
      }
    }
    label188:
    for (paramSession = paramString.getAttributionId();; paramSession = paramString.getAndroidAdvertiserId())
    {
      if (paramString.getAttributionId() != null) {
        ((Bundle)localObject1).putString("udid", paramSession);
      }
      if ((Settings.getLimitEventAndDataUsage(paramContext)) || (paramString.isTrackingLimited())) {
        ((Bundle)localObject1).putString("limit_event_usage", "1");
      }
      return new Request((Session)localObject2, str, (Bundle)localObject1, HttpMethod.GET, paramCallback);
    }
  }
  
  public static Request newDeleteObjectRequest(Session paramSession, String paramString, Callback paramCallback)
  {
    return new Request(paramSession, paramString, null, HttpMethod.DELETE, paramCallback);
  }
  
  public static Request newGraphPathRequest(Session paramSession, String paramString, Callback paramCallback)
  {
    return new Request(paramSession, paramString, null, null, paramCallback);
  }
  
  public static Request newMeRequest(Session paramSession, GraphUserCallback paramGraphUserCallback)
  {
    new Request(paramSession, "me", null, null, new Callback()
    {
      public void onCompleted(Response paramAnonymousResponse)
      {
        if (this.val$callback != null) {
          this.val$callback.onCompleted((GraphUser)paramAnonymousResponse.getGraphObjectAs(GraphUser.class), paramAnonymousResponse);
        }
      }
    });
  }
  
  public static Request newMyFriendsRequest(Session paramSession, GraphUserListCallback paramGraphUserListCallback)
  {
    new Request(paramSession, "me/friends", null, null, new Callback()
    {
      public void onCompleted(Response paramAnonymousResponse)
      {
        if (this.val$callback != null) {
          this.val$callback.onCompleted(Request.typedListFromResponse(paramAnonymousResponse, GraphUser.class), paramAnonymousResponse);
        }
      }
    });
  }
  
  public static Request newPlacesSearchRequest(Session paramSession, Location paramLocation, int paramInt1, int paramInt2, String paramString, GraphPlaceListCallback paramGraphPlaceListCallback)
  {
    if ((paramLocation == null) && (Utility.isNullOrEmpty(paramString))) {
      throw new FacebookException("Either location or searchText must be specified.");
    }
    Bundle localBundle = new Bundle(5);
    localBundle.putString("type", "place");
    localBundle.putInt("limit", paramInt2);
    if (paramLocation != null)
    {
      localBundle.putString("center", String.format(Locale.US, "%f,%f", new Object[] { Double.valueOf(paramLocation.getLatitude()), Double.valueOf(paramLocation.getLongitude()) }));
      localBundle.putInt("distance", paramInt1);
    }
    if (!Utility.isNullOrEmpty(paramString)) {
      localBundle.putString("q", paramString);
    }
    paramLocation = new Callback()
    {
      public void onCompleted(Response paramAnonymousResponse)
      {
        if (this.val$callback != null) {
          this.val$callback.onCompleted(Request.typedListFromResponse(paramAnonymousResponse, GraphPlace.class), paramAnonymousResponse);
        }
      }
    };
    return new Request(paramSession, "search", localBundle, HttpMethod.GET, paramLocation);
  }
  
  public static Request newPostOpenGraphActionRequest(Session paramSession, OpenGraphAction paramOpenGraphAction, Callback paramCallback)
  {
    if (paramOpenGraphAction == null) {
      throw new FacebookException("openGraphAction cannot be null");
    }
    if (Utility.isNullOrEmpty(paramOpenGraphAction.getType())) {
      throw new FacebookException("openGraphAction must have non-null 'type' property");
    }
    return newPostRequest(paramSession, String.format("me/%s", new Object[] { paramOpenGraphAction.getType() }), paramOpenGraphAction, paramCallback);
  }
  
  public static Request newPostOpenGraphObjectRequest(Session paramSession, OpenGraphObject paramOpenGraphObject, Callback paramCallback)
  {
    if (paramOpenGraphObject == null) {
      throw new FacebookException("openGraphObject cannot be null");
    }
    if (Utility.isNullOrEmpty(paramOpenGraphObject.getType())) {
      throw new FacebookException("openGraphObject must have non-null 'type' property");
    }
    if (Utility.isNullOrEmpty(paramOpenGraphObject.getTitle())) {
      throw new FacebookException("openGraphObject must have non-null 'title' property");
    }
    String str = String.format("me/objects/%s", new Object[] { paramOpenGraphObject.getType() });
    Bundle localBundle = new Bundle();
    localBundle.putString("object", paramOpenGraphObject.getInnerJSONObject().toString());
    return new Request(paramSession, str, localBundle, HttpMethod.POST, paramCallback);
  }
  
  public static Request newPostOpenGraphObjectRequest(Session paramSession, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, GraphObject paramGraphObject, Callback paramCallback)
  {
    paramString1 = OpenGraphObject.Factory.createForPost(OpenGraphObject.class, paramString1, paramString2, paramString3, paramString4, paramString5);
    if (paramGraphObject != null) {
      paramString1.setData(paramGraphObject);
    }
    return newPostOpenGraphObjectRequest(paramSession, paramString1, paramCallback);
  }
  
  public static Request newPostRequest(Session paramSession, String paramString, GraphObject paramGraphObject, Callback paramCallback)
  {
    paramSession = new Request(paramSession, paramString, null, HttpMethod.POST, paramCallback);
    paramSession.setGraphObject(paramGraphObject);
    return paramSession;
  }
  
  public static Request newStatusUpdateRequest(Session paramSession, String paramString, Callback paramCallback)
  {
    return newStatusUpdateRequest(paramSession, paramString, (String)null, null, paramCallback);
  }
  
  public static Request newStatusUpdateRequest(Session paramSession, String paramString, GraphPlace paramGraphPlace, List<GraphUser> paramList, Callback paramCallback)
  {
    Object localObject = null;
    if (paramList != null)
    {
      ArrayList localArrayList = new ArrayList(paramList.size());
      paramList = paramList.iterator();
      for (;;)
      {
        localObject = localArrayList;
        if (!paramList.hasNext()) {
          break;
        }
        localArrayList.add(((GraphUser)paramList.next()).getId());
      }
    }
    if (paramGraphPlace == null) {}
    for (paramGraphPlace = null;; paramGraphPlace = paramGraphPlace.getId()) {
      return newStatusUpdateRequest(paramSession, paramString, paramGraphPlace, (List)localObject, paramCallback);
    }
  }
  
  private static Request newStatusUpdateRequest(Session paramSession, String paramString1, String paramString2, List<String> paramList, Callback paramCallback)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("message", paramString1);
    if (paramString2 != null) {
      localBundle.putString("place", paramString2);
    }
    if ((paramList != null) && (paramList.size() > 0)) {
      localBundle.putString("tags", TextUtils.join(",", paramList));
    }
    return new Request(paramSession, "me/feed", localBundle, HttpMethod.POST, paramCallback);
  }
  
  public static Request newUpdateOpenGraphObjectRequest(Session paramSession, OpenGraphObject paramOpenGraphObject, Callback paramCallback)
  {
    if (paramOpenGraphObject == null) {
      throw new FacebookException("openGraphObject cannot be null");
    }
    String str = paramOpenGraphObject.getId();
    if (str == null) {
      throw new FacebookException("openGraphObject must have an id");
    }
    Bundle localBundle = new Bundle();
    localBundle.putString("object", paramOpenGraphObject.getInnerJSONObject().toString());
    return new Request(paramSession, str, localBundle, HttpMethod.POST, paramCallback);
  }
  
  public static Request newUpdateOpenGraphObjectRequest(Session paramSession, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, GraphObject paramGraphObject, Callback paramCallback)
  {
    paramString2 = OpenGraphObject.Factory.createForPost(OpenGraphObject.class, null, paramString2, paramString3, paramString4, paramString5);
    paramString2.setId(paramString1);
    paramString2.setData(paramGraphObject);
    return newUpdateOpenGraphObjectRequest(paramSession, paramString2, paramCallback);
  }
  
  public static Request newUploadPhotoRequest(Session paramSession, Bitmap paramBitmap, Callback paramCallback)
  {
    Bundle localBundle = new Bundle(1);
    localBundle.putParcelable("picture", paramBitmap);
    return new Request(paramSession, "me/photos", localBundle, HttpMethod.POST, paramCallback);
  }
  
  public static Request newUploadPhotoRequest(Session paramSession, File paramFile, Callback paramCallback)
    throws FileNotFoundException
  {
    paramFile = ParcelFileDescriptor.open(paramFile, 268435456);
    Bundle localBundle = new Bundle(1);
    localBundle.putParcelable("picture", paramFile);
    return new Request(paramSession, "me/photos", localBundle, HttpMethod.POST, paramCallback);
  }
  
  public static Request newUploadStagingResourceWithImageRequest(Session paramSession, Bitmap paramBitmap, Callback paramCallback)
  {
    Bundle localBundle = new Bundle(1);
    localBundle.putParcelable("file", paramBitmap);
    return new Request(paramSession, "me/staging_resources", localBundle, HttpMethod.POST, paramCallback);
  }
  
  public static Request newUploadStagingResourceWithImageRequest(Session paramSession, File paramFile, Callback paramCallback)
    throws FileNotFoundException
  {
    ParcelFileDescriptorWithMimeType localParcelFileDescriptorWithMimeType = new ParcelFileDescriptorWithMimeType(ParcelFileDescriptor.open(paramFile, 268435456), "image/png");
    paramFile = new Bundle(1);
    paramFile.putParcelable("file", localParcelFileDescriptorWithMimeType);
    return new Request(paramSession, "me/staging_resources", paramFile, HttpMethod.POST, paramCallback);
  }
  
  public static Request newUploadVideoRequest(Session paramSession, File paramFile, Callback paramCallback)
    throws FileNotFoundException
  {
    ParcelFileDescriptor localParcelFileDescriptor = ParcelFileDescriptor.open(paramFile, 268435456);
    Bundle localBundle = new Bundle(1);
    localBundle.putParcelable(paramFile.getName(), localParcelFileDescriptor);
    return new Request(paramSession, "me/videos", localBundle, HttpMethod.POST, paramCallback);
  }
  
  private static String parameterToString(Object paramObject)
  {
    if ((paramObject instanceof String)) {
      paramObject = (String)paramObject;
    }
    for (;;)
    {
      return (String)paramObject;
      if (((paramObject instanceof Boolean)) || ((paramObject instanceof Number)))
      {
        paramObject = paramObject.toString();
      }
      else
      {
        if (!(paramObject instanceof Date)) {
          break;
        }
        paramObject = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US).format(paramObject);
      }
    }
    throw new IllegalArgumentException("Unsupported parameter type.");
  }
  
  private static void processGraphObject(GraphObject paramGraphObject, String paramString, KeyValueSerializer paramKeyValueSerializer)
    throws IOException
  {
    int i = 0;
    if (isMeRequest(paramString))
    {
      i = paramString.indexOf(":");
      int j = paramString.indexOf("?");
      if ((i > 3) && ((j == -1) || (i < j))) {
        i = 1;
      }
    }
    else
    {
      paramString = paramGraphObject.asMap().entrySet().iterator();
      label62:
      if (!paramString.hasNext()) {
        return;
      }
      paramGraphObject = (Map.Entry)paramString.next();
      if ((i == 0) || (!((String)paramGraphObject.getKey()).equalsIgnoreCase("image"))) {
        break label135;
      }
    }
    label135:
    for (boolean bool = true;; bool = false)
    {
      processGraphObjectProperty((String)paramGraphObject.getKey(), paramGraphObject.getValue(), paramKeyValueSerializer, bool);
      break label62;
      i = 0;
      break;
    }
  }
  
  private static void processGraphObjectProperty(String paramString, Object paramObject, KeyValueSerializer paramKeyValueSerializer, boolean paramBoolean)
    throws IOException
  {
    Class localClass = paramObject.getClass();
    Object localObject1;
    Object localObject2;
    if (GraphObject.class.isAssignableFrom(localClass))
    {
      localObject1 = ((GraphObject)paramObject).getInnerJSONObject();
      localObject2 = localObject1.getClass();
    }
    for (;;)
    {
      if (JSONObject.class.isAssignableFrom((Class)localObject2))
      {
        paramObject = (JSONObject)localObject1;
        if (paramBoolean)
        {
          localObject2 = ((JSONObject)paramObject).keys();
          while (((Iterator)localObject2).hasNext())
          {
            localObject1 = (String)((Iterator)localObject2).next();
            processGraphObjectProperty(String.format("%s[%s]", new Object[] { paramString, localObject1 }), ((JSONObject)paramObject).opt((String)localObject1), paramKeyValueSerializer, paramBoolean);
          }
          localObject2 = localClass;
          localObject1 = paramObject;
          if (GraphObjectList.class.isAssignableFrom(localClass))
          {
            localObject1 = ((GraphObjectList)paramObject).getInnerJSONArray();
            localObject2 = localObject1.getClass();
          }
        }
        else if (((JSONObject)paramObject).has("id"))
        {
          processGraphObjectProperty(paramString, ((JSONObject)paramObject).optString("id"), paramKeyValueSerializer, paramBoolean);
        }
      }
    }
    for (;;)
    {
      return;
      if (((JSONObject)paramObject).has("url"))
      {
        processGraphObjectProperty(paramString, ((JSONObject)paramObject).optString("url"), paramKeyValueSerializer, paramBoolean);
      }
      else if (((JSONObject)paramObject).has("fbsdk:create_object"))
      {
        processGraphObjectProperty(paramString, ((JSONObject)paramObject).toString(), paramKeyValueSerializer, paramBoolean);
        continue;
        if (JSONArray.class.isAssignableFrom((Class)localObject2))
        {
          paramObject = (JSONArray)localObject1;
          int j = ((JSONArray)paramObject).length();
          for (int i = 0; i < j; i++) {
            processGraphObjectProperty(String.format("%s[%d]", new Object[] { paramString, Integer.valueOf(i) }), ((JSONArray)paramObject).opt(i), paramKeyValueSerializer, paramBoolean);
          }
        }
        else if ((String.class.isAssignableFrom((Class)localObject2)) || (Number.class.isAssignableFrom((Class)localObject2)) || (Boolean.class.isAssignableFrom((Class)localObject2)))
        {
          paramKeyValueSerializer.writeString(paramString, localObject1.toString());
        }
        else if (Date.class.isAssignableFrom((Class)localObject2))
        {
          paramObject = (Date)localObject1;
          paramKeyValueSerializer.writeString(paramString, new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US).format((Date)paramObject));
        }
      }
    }
  }
  
  private static void processRequest(RequestBatch paramRequestBatch, Logger paramLogger, int paramInt, URL paramURL, OutputStream paramOutputStream)
    throws IOException, JSONException
  {
    paramOutputStream = new Serializer(paramOutputStream, paramLogger);
    if (paramInt == 1)
    {
      Request localRequest = paramRequestBatch.get(0);
      HashMap localHashMap = new HashMap();
      Iterator localIterator = localRequest.parameters.keySet().iterator();
      while (localIterator.hasNext())
      {
        paramRequestBatch = (String)localIterator.next();
        Object localObject = localRequest.parameters.get(paramRequestBatch);
        if (isSupportedAttachmentType(localObject)) {
          localHashMap.put(paramRequestBatch, new Attachment(localRequest, localObject));
        }
      }
      if (paramLogger != null) {
        paramLogger.append("  Parameters:\n");
      }
      serializeParameters(localRequest.parameters, paramOutputStream, localRequest);
      if (paramLogger != null) {
        paramLogger.append("  Attachments:\n");
      }
      serializeAttachments(localHashMap, paramOutputStream);
      if (localRequest.graphObject != null) {
        processGraphObject(localRequest.graphObject, paramURL.getPath(), paramOutputStream);
      }
    }
    for (;;)
    {
      return;
      paramURL = getBatchAppId(paramRequestBatch);
      if (Utility.isNullOrEmpty(paramURL)) {
        throw new FacebookException("At least one request in a batch must have an open Session, or a default app ID must be specified.");
      }
      paramOutputStream.writeString("batch_app_id", paramURL);
      paramURL = new HashMap();
      serializeRequestsAsJSON(paramOutputStream, paramRequestBatch, paramURL);
      if (paramLogger != null) {
        paramLogger.append("  Attachments:\n");
      }
      serializeAttachments(paramURL, paramOutputStream);
    }
  }
  
  static void runCallbacks(final RequestBatch paramRequestBatch, List<Response> paramList)
  {
    int j = paramRequestBatch.size();
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; i < j; i++)
    {
      Request localRequest = paramRequestBatch.get(i);
      if (localRequest.callback != null) {
        localArrayList.add(new Pair(localRequest.callback, paramList.get(i)));
      }
    }
    if (localArrayList.size() > 0)
    {
      paramList = new Runnable()
      {
        public void run()
        {
          Iterator localIterator = this.val$callbacks.iterator();
          while (localIterator.hasNext())
          {
            Pair localPair = (Pair)localIterator.next();
            ((Request.Callback)localPair.first).onCompleted((Response)localPair.second);
          }
          localIterator = paramRequestBatch.getCallbacks().iterator();
          while (localIterator.hasNext()) {
            ((RequestBatch.Callback)localIterator.next()).onBatchCompleted(paramRequestBatch);
          }
        }
      };
      paramRequestBatch = paramRequestBatch.getCallbackHandler();
      if (paramRequestBatch != null) {
        break label102;
      }
      paramList.run();
    }
    for (;;)
    {
      return;
      label102:
      paramRequestBatch.post(paramList);
    }
  }
  
  private static void serializeAttachments(Map<String, Attachment> paramMap, Serializer paramSerializer)
    throws IOException
  {
    Iterator localIterator = paramMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      Attachment localAttachment = (Attachment)paramMap.get(str);
      if (isSupportedAttachmentType(localAttachment.getValue())) {
        paramSerializer.writeObject(str, localAttachment.getValue(), localAttachment.getRequest());
      }
    }
  }
  
  private static void serializeParameters(Bundle paramBundle, Serializer paramSerializer, Request paramRequest)
    throws IOException
  {
    Iterator localIterator = paramBundle.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      Object localObject = paramBundle.get(str);
      if (isSupportedParameterType(localObject)) {
        paramSerializer.writeObject(str, localObject, paramRequest);
      }
    }
  }
  
  private static void serializeRequestsAsJSON(Serializer paramSerializer, Collection<Request> paramCollection, Map<String, Attachment> paramMap)
    throws JSONException, IOException
  {
    JSONArray localJSONArray = new JSONArray();
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext()) {
      ((Request)localIterator.next()).serializeToBatch(localJSONArray, paramMap);
    }
    paramSerializer.writeRequestsAsJson("batch", localJSONArray, paramCollection);
  }
  
  private void serializeToBatch(JSONArray paramJSONArray, final Map<String, Attachment> paramMap)
    throws JSONException, IOException
  {
    JSONObject localJSONObject = new JSONObject();
    if (this.batchEntryName != null)
    {
      localJSONObject.put("name", this.batchEntryName);
      localJSONObject.put("omit_response_on_success", this.batchEntryOmitResultOnSuccess);
    }
    if (this.batchEntryDependsOn != null) {
      localJSONObject.put("depends_on", this.batchEntryDependsOn);
    }
    String str1 = getUrlForBatchedRequest();
    localJSONObject.put("relative_url", str1);
    localJSONObject.put("method", this.httpMethod);
    if (this.session != null) {
      Logger.registerAccessToken(this.session.getAccessToken());
    }
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.parameters.keySet().iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (String)localIterator.next();
      localObject = this.parameters.get((String)localObject);
      if (isSupportedAttachmentType(localObject))
      {
        String str2 = String.format("%s%d", new Object[] { "file", Integer.valueOf(paramMap.size()) });
        localArrayList.add(str2);
        paramMap.put(str2, new Attachment(this, localObject));
      }
    }
    if (!localArrayList.isEmpty()) {
      localJSONObject.put("attached_files", TextUtils.join(",", localArrayList));
    }
    if (this.graphObject != null)
    {
      paramMap = new ArrayList();
      processGraphObject(this.graphObject, str1, new KeyValueSerializer()
      {
        public void writeString(String paramAnonymousString1, String paramAnonymousString2)
          throws IOException
        {
          paramMap.add(String.format("%s=%s", new Object[] { paramAnonymousString1, URLEncoder.encode(paramAnonymousString2, "UTF-8") }));
        }
      });
      localJSONObject.put("body", TextUtils.join("&", paramMap));
    }
    paramJSONArray.put(localJSONObject);
  }
  
  static final void serializeToUrlConnection(RequestBatch paramRequestBatch, HttpURLConnection paramHttpURLConnection)
    throws IOException, JSONException
  {
    int i = 0;
    Logger localLogger = new Logger(LoggingBehavior.REQUESTS, "Request");
    int j = paramRequestBatch.size();
    if (j == 1) {}
    URL localURL;
    for (Object localObject = paramRequestBatch.get(0).httpMethod;; localObject = HttpMethod.POST)
    {
      paramHttpURLConnection.setRequestMethod(((HttpMethod)localObject).name());
      localURL = paramHttpURLConnection.getURL();
      localLogger.append("Request:\n");
      localLogger.appendKeyValue("Id", paramRequestBatch.getId());
      localLogger.appendKeyValue("URL", localURL);
      localLogger.appendKeyValue("Method", paramHttpURLConnection.getRequestMethod());
      localLogger.appendKeyValue("User-Agent", paramHttpURLConnection.getRequestProperty("User-Agent"));
      localLogger.appendKeyValue("Content-Type", paramHttpURLConnection.getRequestProperty("Content-Type"));
      paramHttpURLConnection.setConnectTimeout(paramRequestBatch.getTimeout());
      paramHttpURLConnection.setReadTimeout(paramRequestBatch.getTimeout());
      if (localObject == HttpMethod.POST) {
        i = 1;
      }
      if (i != 0) {
        break;
      }
      localLogger.log();
      return;
    }
    paramHttpURLConnection.setDoOutput(true);
    for (;;)
    {
      try
      {
        if (hasOnProgressCallbacks(paramRequestBatch))
        {
          localObject = new com/facebook/ProgressNoopOutputStream;
          ((ProgressNoopOutputStream)localObject).<init>(paramRequestBatch.getCallbackHandler());
          processRequest(paramRequestBatch, null, j, localURL, (OutputStream)localObject);
          i = ((ProgressNoopOutputStream)localObject).getMaxProgress();
          localObject = ((ProgressNoopOutputStream)localObject).getProgressMap();
          BufferedOutputStream localBufferedOutputStream = new java/io/BufferedOutputStream;
          localBufferedOutputStream.<init>(paramHttpURLConnection.getOutputStream());
          paramHttpURLConnection = new com/facebook/ProgressOutputStream;
          paramHttpURLConnection.<init>(localBufferedOutputStream, paramRequestBatch, (Map)localObject, i);
        }
      }
      finally
      {
        paramHttpURLConnection = null;
      }
      try
      {
        processRequest(paramRequestBatch, localLogger, j, localURL, paramHttpURLConnection);
        if (paramHttpURLConnection != null) {
          paramHttpURLConnection.close();
        }
        localLogger.log();
        break;
      }
      finally {}
      paramHttpURLConnection = new BufferedOutputStream(paramHttpURLConnection.getOutputStream());
    }
    if (paramHttpURLConnection != null) {
      paramHttpURLConnection.close();
    }
    throw paramRequestBatch;
  }
  
  public static final void setDefaultBatchApplicationId(String paramString)
  {
    defaultBatchApplicationId = paramString;
  }
  
  public static HttpURLConnection toHttpConnection(RequestBatch paramRequestBatch)
  {
    for (;;)
    {
      try
      {
        if (paramRequestBatch.size() == 1) {
          localObject = new URL(paramRequestBatch.get(0).getUrlForSingleRequest());
        }
      }
      catch (MalformedURLException paramRequestBatch)
      {
        Object localObject;
        throw new FacebookException("could not construct URL for request", paramRequestBatch);
      }
      try
      {
        localObject = createConnection((URL)localObject);
        serializeToUrlConnection(paramRequestBatch, (HttpURLConnection)localObject);
        return (HttpURLConnection)localObject;
      }
      catch (IOException paramRequestBatch)
      {
        throw new FacebookException("could not construct request body", paramRequestBatch);
      }
      catch (JSONException paramRequestBatch)
      {
        throw new FacebookException("could not construct request body", paramRequestBatch);
      }
      localObject = new URL(ServerProtocol.getGraphUrlBase());
    }
  }
  
  public static HttpURLConnection toHttpConnection(Collection<Request> paramCollection)
  {
    Validate.notEmptyAndContainsNoNulls(paramCollection, "requests");
    return toHttpConnection(new RequestBatch(paramCollection));
  }
  
  public static HttpURLConnection toHttpConnection(Request... paramVarArgs)
  {
    return toHttpConnection(Arrays.asList(paramVarArgs));
  }
  
  private static <T extends GraphObject> List<T> typedListFromResponse(Response paramResponse, Class<T> paramClass)
  {
    Object localObject = null;
    paramResponse = (GraphMultiResult)paramResponse.getGraphObjectAs(GraphMultiResult.class);
    if (paramResponse == null) {
      paramResponse = (Response)localObject;
    }
    for (;;)
    {
      return paramResponse;
      GraphObjectList localGraphObjectList = paramResponse.getData();
      paramResponse = (Response)localObject;
      if (localGraphObjectList != null) {
        paramResponse = localGraphObjectList.castToListOf(paramClass);
      }
    }
  }
  
  public final Response executeAndWait()
  {
    return executeAndWait(this);
  }
  
  public final RequestAsyncTask executeAsync()
  {
    return executeBatchAsync(new Request[] { this });
  }
  
  public final String getBatchEntryDependsOn()
  {
    return this.batchEntryDependsOn;
  }
  
  public final String getBatchEntryName()
  {
    return this.batchEntryName;
  }
  
  public final boolean getBatchEntryOmitResultOnSuccess()
  {
    return this.batchEntryOmitResultOnSuccess;
  }
  
  public final Callback getCallback()
  {
    return this.callback;
  }
  
  public final GraphObject getGraphObject()
  {
    return this.graphObject;
  }
  
  public final String getGraphPath()
  {
    return this.graphPath;
  }
  
  public final HttpMethod getHttpMethod()
  {
    return this.httpMethod;
  }
  
  public final Bundle getParameters()
  {
    return this.parameters;
  }
  
  public final Session getSession()
  {
    return this.session;
  }
  
  public final Object getTag()
  {
    return this.tag;
  }
  
  final String getUrlForBatchedRequest()
  {
    if (this.overriddenURL != null) {
      throw new FacebookException("Can't override URL for a batch request");
    }
    String str = getGraphPathWithVersion();
    addCommonParameters();
    return appendParametersToBaseUrl(str);
  }
  
  final String getUrlForSingleRequest()
  {
    if (this.overriddenURL != null)
    {
      str = this.overriddenURL.toString();
      return str;
    }
    if ((getHttpMethod() == HttpMethod.POST) && (this.graphPath != null) && (this.graphPath.endsWith("/videos"))) {}
    for (String str = ServerProtocol.getGraphVideoUrlBase();; str = ServerProtocol.getGraphUrlBase())
    {
      str = String.format("%s/%s", new Object[] { str, getGraphPathWithVersion() });
      addCommonParameters();
      str = appendParametersToBaseUrl(str);
      break;
    }
  }
  
  public final String getVersion()
  {
    return this.version;
  }
  
  public final void setBatchEntryDependsOn(String paramString)
  {
    this.batchEntryDependsOn = paramString;
  }
  
  public final void setBatchEntryName(String paramString)
  {
    this.batchEntryName = paramString;
  }
  
  public final void setBatchEntryOmitResultOnSuccess(boolean paramBoolean)
  {
    this.batchEntryOmitResultOnSuccess = paramBoolean;
  }
  
  public final void setCallback(Callback paramCallback)
  {
    this.callback = paramCallback;
  }
  
  public final void setGraphObject(GraphObject paramGraphObject)
  {
    this.graphObject = paramGraphObject;
  }
  
  public final void setGraphPath(String paramString)
  {
    this.graphPath = paramString;
  }
  
  public final void setHttpMethod(HttpMethod paramHttpMethod)
  {
    if ((this.overriddenURL != null) && (paramHttpMethod != HttpMethod.GET)) {
      throw new FacebookException("Can't change HTTP method on request with overridden URL.");
    }
    if (paramHttpMethod != null) {}
    for (;;)
    {
      this.httpMethod = paramHttpMethod;
      return;
      paramHttpMethod = HttpMethod.GET;
    }
  }
  
  public final void setParameters(Bundle paramBundle)
  {
    this.parameters = paramBundle;
  }
  
  public final void setSession(Session paramSession)
  {
    this.session = paramSession;
  }
  
  public final void setSkipClientToken(boolean paramBoolean)
  {
    this.skipClientToken = paramBoolean;
  }
  
  public final void setTag(Object paramObject)
  {
    this.tag = paramObject;
  }
  
  public final void setVersion(String paramString)
  {
    this.version = paramString;
  }
  
  public String toString()
  {
    return "{Request: " + " session: " + this.session + ", graphPath: " + this.graphPath + ", graphObject: " + this.graphObject + ", httpMethod: " + this.httpMethod + ", parameters: " + this.parameters + "}";
  }
  
  private static class Attachment
  {
    private final Request request;
    private final Object value;
    
    public Attachment(Request paramRequest, Object paramObject)
    {
      this.request = paramRequest;
      this.value = paramObject;
    }
    
    public Request getRequest()
    {
      return this.request;
    }
    
    public Object getValue()
    {
      return this.value;
    }
  }
  
  public static abstract interface Callback
  {
    public abstract void onCompleted(Response paramResponse);
  }
  
  public static abstract interface GraphPlaceListCallback
  {
    public abstract void onCompleted(List<GraphPlace> paramList, Response paramResponse);
  }
  
  public static abstract interface GraphUserCallback
  {
    public abstract void onCompleted(GraphUser paramGraphUser, Response paramResponse);
  }
  
  public static abstract interface GraphUserListCallback
  {
    public abstract void onCompleted(List<GraphUser> paramList, Response paramResponse);
  }
  
  private static abstract interface KeyValueSerializer
  {
    public abstract void writeString(String paramString1, String paramString2)
      throws IOException;
  }
  
  public static abstract interface OnProgressCallback
    extends Request.Callback
  {
    public abstract void onProgress(long paramLong1, long paramLong2);
  }
  
  private static class ParcelFileDescriptorWithMimeType
    implements Parcelable
  {
    public static final Parcelable.Creator<ParcelFileDescriptorWithMimeType> CREATOR = new Parcelable.Creator()
    {
      public Request.ParcelFileDescriptorWithMimeType createFromParcel(Parcel paramAnonymousParcel)
      {
        return new Request.ParcelFileDescriptorWithMimeType(paramAnonymousParcel, null);
      }
      
      public Request.ParcelFileDescriptorWithMimeType[] newArray(int paramAnonymousInt)
      {
        return new Request.ParcelFileDescriptorWithMimeType[paramAnonymousInt];
      }
    };
    private final ParcelFileDescriptor fileDescriptor;
    private final String mimeType;
    
    private ParcelFileDescriptorWithMimeType(Parcel paramParcel)
    {
      this.mimeType = paramParcel.readString();
      this.fileDescriptor = paramParcel.readFileDescriptor();
    }
    
    public ParcelFileDescriptorWithMimeType(ParcelFileDescriptor paramParcelFileDescriptor, String paramString)
    {
      this.mimeType = paramString;
      this.fileDescriptor = paramParcelFileDescriptor;
    }
    
    public int describeContents()
    {
      return 1;
    }
    
    public ParcelFileDescriptor getFileDescriptor()
    {
      return this.fileDescriptor;
    }
    
    public String getMimeType()
    {
      return this.mimeType;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      paramParcel.writeString(this.mimeType);
      paramParcel.writeFileDescriptor(this.fileDescriptor.getFileDescriptor());
    }
  }
  
  private static class Serializer
    implements Request.KeyValueSerializer
  {
    private boolean firstWrite = true;
    private final Logger logger;
    private final OutputStream outputStream;
    
    public Serializer(OutputStream paramOutputStream, Logger paramLogger)
    {
      this.outputStream = paramOutputStream;
      this.logger = paramLogger;
    }
    
    public void write(String paramString, Object... paramVarArgs)
      throws IOException
    {
      if (this.firstWrite)
      {
        this.outputStream.write("--".getBytes());
        this.outputStream.write("3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f".getBytes());
        this.outputStream.write("\r\n".getBytes());
        this.firstWrite = false;
      }
      this.outputStream.write(String.format(paramString, paramVarArgs).getBytes());
    }
    
    public void writeBitmap(String paramString, Bitmap paramBitmap)
      throws IOException
    {
      writeContentDisposition(paramString, paramString, "image/png");
      paramBitmap.compress(Bitmap.CompressFormat.PNG, 100, this.outputStream);
      writeLine("", new Object[0]);
      writeRecordBoundary();
      if (this.logger != null) {
        this.logger.appendKeyValue("    " + paramString, "<Image>");
      }
    }
    
    public void writeBytes(String paramString, byte[] paramArrayOfByte)
      throws IOException
    {
      writeContentDisposition(paramString, paramString, "content/unknown");
      this.outputStream.write(paramArrayOfByte);
      writeLine("", new Object[0]);
      writeRecordBoundary();
      if (this.logger != null) {
        this.logger.appendKeyValue("    " + paramString, String.format("<Data: %d>", new Object[] { Integer.valueOf(paramArrayOfByte.length) }));
      }
    }
    
    public void writeContentDisposition(String paramString1, String paramString2, String paramString3)
      throws IOException
    {
      write("Content-Disposition: form-data; name=\"%s\"", new Object[] { paramString1 });
      if (paramString2 != null) {
        write("; filename=\"%s\"", new Object[] { paramString2 });
      }
      writeLine("", new Object[0]);
      if (paramString3 != null) {
        writeLine("%s: %s", new Object[] { "Content-Type", paramString3 });
      }
      writeLine("", new Object[0]);
    }
    
    public void writeFile(String paramString1, ParcelFileDescriptor paramParcelFileDescriptor, String paramString2)
      throws IOException
    {
      localObject = paramString2;
      if (paramString2 == null) {
        localObject = "content/unknown";
      }
      writeContentDisposition(paramString1, paramString1, (String)localObject);
      int i = 0;
      int j = 0;
      if ((this.outputStream instanceof ProgressNoopOutputStream))
      {
        ((ProgressNoopOutputStream)this.outputStream).addProgress(paramParcelFileDescriptor.getStatSize());
        label49:
        writeLine("", new Object[0]);
        writeRecordBoundary();
        if (this.logger != null) {
          this.logger.appendKeyValue("    " + paramString1, String.format("<Data: %d>", new Object[] { Integer.valueOf(j) }));
        }
        return;
      }
      localBufferedInputStream = null;
      localObject = null;
      try
      {
        paramString2 = new android/os/ParcelFileDescriptor$AutoCloseInputStream;
        paramString2.<init>(paramParcelFileDescriptor);
      }
      finally
      {
        try
        {
          localBufferedInputStream = new java/io/BufferedInputStream;
          localBufferedInputStream.<init>(paramString2);
        }
        finally
        {
          label146:
          label217:
          paramString1 = paramString2;
          paramString2 = (String)localObject;
        }
        try
        {
          paramParcelFileDescriptor = new byte[' '];
          j = localBufferedInputStream.read(paramParcelFileDescriptor);
          if (j != -1)
          {
            this.outputStream.write(paramParcelFileDescriptor, 0, j);
            i += j;
            break label146;
          }
          if (localBufferedInputStream != null) {
            localBufferedInputStream.close();
          }
          j = i;
          if (paramString2 == null) {
            break label49;
          }
          paramString2.close();
          j = i;
          break label49;
        }
        finally
        {
          localObject = localBufferedInputStream;
          paramString1 = paramString2;
          paramString2 = (String)localObject;
          break label217;
        }
        paramParcelFileDescriptor = finally;
        paramString1 = localBufferedInputStream;
        paramString2 = (String)localObject;
        if (paramString2 != null) {
          paramString2.close();
        }
        if (paramString1 != null) {
          paramString1.close();
        }
        throw paramParcelFileDescriptor;
      }
    }
    
    public void writeFile(String paramString, Request.ParcelFileDescriptorWithMimeType paramParcelFileDescriptorWithMimeType)
      throws IOException
    {
      writeFile(paramString, paramParcelFileDescriptorWithMimeType.getFileDescriptor(), paramParcelFileDescriptorWithMimeType.getMimeType());
    }
    
    public void writeLine(String paramString, Object... paramVarArgs)
      throws IOException
    {
      write(paramString, paramVarArgs);
      write("\r\n", new Object[0]);
    }
    
    public void writeObject(String paramString, Object paramObject, Request paramRequest)
      throws IOException
    {
      if ((this.outputStream instanceof RequestOutputStream)) {
        ((RequestOutputStream)this.outputStream).setCurrentRequest(paramRequest);
      }
      if (Request.isSupportedParameterType(paramObject)) {
        writeString(paramString, Request.parameterToString(paramObject));
      }
      for (;;)
      {
        return;
        if ((paramObject instanceof Bitmap))
        {
          writeBitmap(paramString, (Bitmap)paramObject);
        }
        else if ((paramObject instanceof byte[]))
        {
          writeBytes(paramString, (byte[])paramObject);
        }
        else if ((paramObject instanceof ParcelFileDescriptor))
        {
          writeFile(paramString, (ParcelFileDescriptor)paramObject, null);
        }
        else
        {
          if (!(paramObject instanceof Request.ParcelFileDescriptorWithMimeType)) {
            break;
          }
          writeFile(paramString, (Request.ParcelFileDescriptorWithMimeType)paramObject);
        }
      }
      throw new IllegalArgumentException("value is not a supported type: String, Bitmap, byte[]");
    }
    
    public void writeRecordBoundary()
      throws IOException
    {
      writeLine("--%s", new Object[] { "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f" });
    }
    
    public void writeRequestsAsJson(String paramString, JSONArray paramJSONArray, Collection<Request> paramCollection)
      throws IOException, JSONException
    {
      if (!(this.outputStream instanceof RequestOutputStream)) {
        writeString(paramString, paramJSONArray.toString());
      }
      for (;;)
      {
        return;
        RequestOutputStream localRequestOutputStream = (RequestOutputStream)this.outputStream;
        writeContentDisposition(paramString, null, null);
        write("[", new Object[0]);
        int i = 0;
        Iterator localIterator = paramCollection.iterator();
        if (localIterator.hasNext())
        {
          paramCollection = (Request)localIterator.next();
          JSONObject localJSONObject = paramJSONArray.getJSONObject(i);
          localRequestOutputStream.setCurrentRequest(paramCollection);
          if (i > 0) {
            write(",%s", new Object[] { localJSONObject.toString() });
          }
          for (;;)
          {
            i++;
            break;
            write("%s", new Object[] { localJSONObject.toString() });
          }
        }
        write("]", new Object[0]);
        if (this.logger != null) {
          this.logger.appendKeyValue("    " + paramString, paramJSONArray.toString());
        }
      }
    }
    
    public void writeString(String paramString1, String paramString2)
      throws IOException
    {
      writeContentDisposition(paramString1, null, null);
      writeLine("%s", new Object[] { paramString2 });
      writeRecordBoundary();
      if (this.logger != null) {
        this.logger.appendKeyValue("    " + paramString1, paramString2);
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\facebook\Request.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */