package android.support.v4.app;

import android.content.ClipData;
import android.content.ClipData.Item;
import android.content.ClipDescription;
import android.content.Intent;
import android.os.Bundle;

class RemoteInputCompatJellybean
{
  public static final String EXTRA_RESULTS_DATA = "android.remoteinput.resultsData";
  private static final String KEY_ALLOW_FREE_FORM_INPUT = "allowFreeFormInput";
  private static final String KEY_CHOICES = "choices";
  private static final String KEY_EXTRAS = "extras";
  private static final String KEY_LABEL = "label";
  private static final String KEY_RESULT_KEY = "resultKey";
  public static final String RESULTS_CLIP_LABEL = "android.remoteinput.results";
  
  static void addResultsToIntent(RemoteInputCompatBase.RemoteInput[] paramArrayOfRemoteInput, Intent paramIntent, Bundle paramBundle)
  {
    Bundle localBundle = new Bundle();
    int j = paramArrayOfRemoteInput.length;
    for (int i = 0; i < j; i++)
    {
      RemoteInputCompatBase.RemoteInput localRemoteInput = paramArrayOfRemoteInput[i];
      Object localObject = paramBundle.get(localRemoteInput.getResultKey());
      if ((localObject instanceof CharSequence)) {
        localBundle.putCharSequence(localRemoteInput.getResultKey(), (CharSequence)localObject);
      }
    }
    paramArrayOfRemoteInput = new Intent();
    paramArrayOfRemoteInput.putExtra("android.remoteinput.resultsData", localBundle);
    paramIntent.setClipData(ClipData.newIntent("android.remoteinput.results", paramArrayOfRemoteInput));
  }
  
  static RemoteInputCompatBase.RemoteInput fromBundle(Bundle paramBundle, RemoteInputCompatBase.RemoteInput.Factory paramFactory)
  {
    return paramFactory.build(paramBundle.getString("resultKey"), paramBundle.getCharSequence("label"), paramBundle.getCharSequenceArray("choices"), paramBundle.getBoolean("allowFreeFormInput"), paramBundle.getBundle("extras"));
  }
  
  static RemoteInputCompatBase.RemoteInput[] fromBundleArray(Bundle[] paramArrayOfBundle, RemoteInputCompatBase.RemoteInput.Factory paramFactory)
  {
    Object localObject;
    if (paramArrayOfBundle == null)
    {
      localObject = null;
      return (RemoteInputCompatBase.RemoteInput[])localObject;
    }
    RemoteInputCompatBase.RemoteInput[] arrayOfRemoteInput = paramFactory.newArray(paramArrayOfBundle.length);
    for (int i = 0;; i++)
    {
      localObject = arrayOfRemoteInput;
      if (i >= paramArrayOfBundle.length) {
        break;
      }
      arrayOfRemoteInput[i] = fromBundle(paramArrayOfBundle[i], paramFactory);
    }
  }
  
  static Bundle getResultsFromIntent(Intent paramIntent)
  {
    Object localObject = null;
    ClipData localClipData = paramIntent.getClipData();
    if (localClipData == null) {
      paramIntent = (Intent)localObject;
    }
    for (;;)
    {
      return paramIntent;
      ClipDescription localClipDescription = localClipData.getDescription();
      paramIntent = (Intent)localObject;
      if (localClipDescription.hasMimeType("text/vnd.android.intent"))
      {
        paramIntent = (Intent)localObject;
        if (localClipDescription.getLabel().equals("android.remoteinput.results")) {
          paramIntent = (Bundle)localClipData.getItemAt(0).getIntent().getExtras().getParcelable("android.remoteinput.resultsData");
        }
      }
    }
  }
  
  static Bundle toBundle(RemoteInputCompatBase.RemoteInput paramRemoteInput)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("resultKey", paramRemoteInput.getResultKey());
    localBundle.putCharSequence("label", paramRemoteInput.getLabel());
    localBundle.putCharSequenceArray("choices", paramRemoteInput.getChoices());
    localBundle.putBoolean("allowFreeFormInput", paramRemoteInput.getAllowFreeFormInput());
    localBundle.putBundle("extras", paramRemoteInput.getExtras());
    return localBundle;
  }
  
  static Bundle[] toBundleArray(RemoteInputCompatBase.RemoteInput[] paramArrayOfRemoteInput)
  {
    Object localObject;
    if (paramArrayOfRemoteInput == null)
    {
      localObject = null;
      return (Bundle[])localObject;
    }
    Bundle[] arrayOfBundle = new Bundle[paramArrayOfRemoteInput.length];
    for (int i = 0;; i++)
    {
      localObject = arrayOfBundle;
      if (i >= paramArrayOfRemoteInput.length) {
        break;
      }
      arrayOfBundle[i] = toBundle(paramArrayOfRemoteInput[i]);
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\android\support\v4\app\RemoteInputCompatJellybean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */