package android.support.v4.content;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.os.CancellationSignal;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Arrays;

public class CursorLoader
  extends AsyncTaskLoader<Cursor>
{
  CancellationSignal mCancellationSignal;
  Cursor mCursor;
  final Loader<Cursor>.ForceLoadContentObserver mObserver = new Loader.ForceLoadContentObserver(this);
  String[] mProjection;
  String mSelection;
  String[] mSelectionArgs;
  String mSortOrder;
  Uri mUri;
  
  public CursorLoader(Context paramContext)
  {
    super(paramContext);
  }
  
  public CursorLoader(Context paramContext, Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2)
  {
    super(paramContext);
    this.mUri = paramUri;
    this.mProjection = paramArrayOfString1;
    this.mSelection = paramString1;
    this.mSelectionArgs = paramArrayOfString2;
    this.mSortOrder = paramString2;
  }
  
  public void cancelLoadInBackground()
  {
    super.cancelLoadInBackground();
    try
    {
      if (this.mCancellationSignal != null) {
        this.mCancellationSignal.cancel();
      }
      return;
    }
    finally {}
  }
  
  public void deliverResult(Cursor paramCursor)
  {
    if (isReset()) {
      if (paramCursor != null) {
        paramCursor.close();
      }
    }
    for (;;)
    {
      return;
      Cursor localCursor = this.mCursor;
      this.mCursor = paramCursor;
      if (isStarted()) {
        super.deliverResult(paramCursor);
      }
      if ((localCursor != null) && (localCursor != paramCursor) && (!localCursor.isClosed())) {
        localCursor.close();
      }
    }
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    super.dump(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mUri=");
    paramPrintWriter.println(this.mUri);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mProjection=");
    paramPrintWriter.println(Arrays.toString(this.mProjection));
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mSelection=");
    paramPrintWriter.println(this.mSelection);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mSelectionArgs=");
    paramPrintWriter.println(Arrays.toString(this.mSelectionArgs));
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mSortOrder=");
    paramPrintWriter.println(this.mSortOrder);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mCursor=");
    paramPrintWriter.println(this.mCursor);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mContentChanged=");
    paramPrintWriter.println(this.mContentChanged);
  }
  
  public String[] getProjection()
  {
    return this.mProjection;
  }
  
  public String getSelection()
  {
    return this.mSelection;
  }
  
  public String[] getSelectionArgs()
  {
    return this.mSelectionArgs;
  }
  
  public String getSortOrder()
  {
    return this.mSortOrder;
  }
  
  public Uri getUri()
  {
    return this.mUri;
  }
  
  /* Error */
  public Cursor loadInBackground()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual 135	android/support/v4/content/CursorLoader:isLoadInBackgroundCanceled	()Z
    //   6: ifeq +18 -> 24
    //   9: new 137	android/support/v4/os/OperationCanceledException
    //   12: astore_1
    //   13: aload_1
    //   14: invokespecial 139	android/support/v4/os/OperationCanceledException:<init>	()V
    //   17: aload_1
    //   18: athrow
    //   19: astore_1
    //   20: aload_0
    //   21: monitorexit
    //   22: aload_1
    //   23: athrow
    //   24: new 51	android/support/v4/os/CancellationSignal
    //   27: astore_1
    //   28: aload_1
    //   29: invokespecial 140	android/support/v4/os/CancellationSignal:<init>	()V
    //   32: aload_0
    //   33: aload_1
    //   34: putfield 49	android/support/v4/content/CursorLoader:mCancellationSignal	Landroid/support/v4/os/CancellationSignal;
    //   37: aload_0
    //   38: monitorexit
    //   39: aload_0
    //   40: invokevirtual 144	android/support/v4/content/CursorLoader:getContext	()Landroid/content/Context;
    //   43: invokevirtual 150	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   46: aload_0
    //   47: getfield 35	android/support/v4/content/CursorLoader:mUri	Landroid/net/Uri;
    //   50: aload_0
    //   51: getfield 37	android/support/v4/content/CursorLoader:mProjection	[Ljava/lang/String;
    //   54: aload_0
    //   55: getfield 39	android/support/v4/content/CursorLoader:mSelection	Ljava/lang/String;
    //   58: aload_0
    //   59: getfield 41	android/support/v4/content/CursorLoader:mSelectionArgs	[Ljava/lang/String;
    //   62: aload_0
    //   63: getfield 43	android/support/v4/content/CursorLoader:mSortOrder	Ljava/lang/String;
    //   66: aload_0
    //   67: getfield 49	android/support/v4/content/CursorLoader:mCancellationSignal	Landroid/support/v4/os/CancellationSignal;
    //   70: invokestatic 156	android/support/v4/content/ContentResolverCompat:query	(Landroid/content/ContentResolver;Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Landroid/support/v4/os/CancellationSignal;)Landroid/database/Cursor;
    //   73: astore_1
    //   74: aload_1
    //   75: ifnull +20 -> 95
    //   78: aload_1
    //   79: invokeinterface 160 1 0
    //   84: pop
    //   85: aload_1
    //   86: aload_0
    //   87: getfield 31	android/support/v4/content/CursorLoader:mObserver	Landroid/support/v4/content/Loader$ForceLoadContentObserver;
    //   90: invokeinterface 164 2 0
    //   95: aload_0
    //   96: monitorenter
    //   97: aload_0
    //   98: aconst_null
    //   99: putfield 49	android/support/v4/content/CursorLoader:mCancellationSignal	Landroid/support/v4/os/CancellationSignal;
    //   102: aload_0
    //   103: monitorexit
    //   104: aload_1
    //   105: areturn
    //   106: astore_2
    //   107: aload_1
    //   108: invokeinterface 65 1 0
    //   113: aload_2
    //   114: athrow
    //   115: astore_1
    //   116: aload_0
    //   117: monitorenter
    //   118: aload_0
    //   119: aconst_null
    //   120: putfield 49	android/support/v4/content/CursorLoader:mCancellationSignal	Landroid/support/v4/os/CancellationSignal;
    //   123: aload_0
    //   124: monitorexit
    //   125: aload_1
    //   126: athrow
    //   127: astore_1
    //   128: aload_0
    //   129: monitorexit
    //   130: aload_1
    //   131: athrow
    //   132: astore_1
    //   133: aload_0
    //   134: monitorexit
    //   135: aload_1
    //   136: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	137	0	this	CursorLoader
    //   12	6	1	localOperationCanceledException	android.support.v4.os.OperationCanceledException
    //   19	4	1	localObject1	Object
    //   27	81	1	localObject2	Object
    //   115	11	1	localObject3	Object
    //   127	4	1	localObject4	Object
    //   132	4	1	localObject5	Object
    //   106	8	2	localRuntimeException	RuntimeException
    // Exception table:
    //   from	to	target	type
    //   2	19	19	finally
    //   20	22	19	finally
    //   24	39	19	finally
    //   78	95	106	java/lang/RuntimeException
    //   39	74	115	finally
    //   78	95	115	finally
    //   107	115	115	finally
    //   97	104	127	finally
    //   128	130	127	finally
    //   118	125	132	finally
    //   133	135	132	finally
  }
  
  public void onCanceled(Cursor paramCursor)
  {
    if ((paramCursor != null) && (!paramCursor.isClosed())) {
      paramCursor.close();
    }
  }
  
  protected void onReset()
  {
    super.onReset();
    onStopLoading();
    if ((this.mCursor != null) && (!this.mCursor.isClosed())) {
      this.mCursor.close();
    }
    this.mCursor = null;
  }
  
  protected void onStartLoading()
  {
    if (this.mCursor != null) {
      deliverResult(this.mCursor);
    }
    if ((takeContentChanged()) || (this.mCursor == null)) {
      forceLoad();
    }
  }
  
  protected void onStopLoading()
  {
    cancelLoad();
  }
  
  public void setProjection(String[] paramArrayOfString)
  {
    this.mProjection = paramArrayOfString;
  }
  
  public void setSelection(String paramString)
  {
    this.mSelection = paramString;
  }
  
  public void setSelectionArgs(String[] paramArrayOfString)
  {
    this.mSelectionArgs = paramArrayOfString;
  }
  
  public void setSortOrder(String paramString)
  {
    this.mSortOrder = paramString;
  }
  
  public void setUri(Uri paramUri)
  {
    this.mUri = paramUri;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\android\support\v4\content\CursorLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */