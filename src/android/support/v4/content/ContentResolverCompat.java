package android.support.v4.content;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.v4.os.CancellationSignal;
import android.support.v4.os.OperationCanceledException;

public class ContentResolverCompat
{
  private static final ContentResolverCompatImpl IMPL;
  
  static
  {
    if (Build.VERSION.SDK_INT >= 16) {}
    for (IMPL = new ContentResolverCompatImplJB();; IMPL = new ContentResolverCompatImplBase()) {
      return;
    }
  }
  
  public static Cursor query(ContentResolver paramContentResolver, Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2, CancellationSignal paramCancellationSignal)
  {
    return IMPL.query(paramContentResolver, paramUri, paramArrayOfString1, paramString1, paramArrayOfString2, paramString2, paramCancellationSignal);
  }
  
  static abstract interface ContentResolverCompatImpl
  {
    public abstract Cursor query(ContentResolver paramContentResolver, Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2, CancellationSignal paramCancellationSignal);
  }
  
  static class ContentResolverCompatImplBase
    implements ContentResolverCompat.ContentResolverCompatImpl
  {
    public Cursor query(ContentResolver paramContentResolver, Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2, CancellationSignal paramCancellationSignal)
    {
      if (paramCancellationSignal != null) {
        paramCancellationSignal.throwIfCanceled();
      }
      return paramContentResolver.query(paramUri, paramArrayOfString1, paramString1, paramArrayOfString2, paramString2);
    }
  }
  
  static class ContentResolverCompatImplJB
    extends ContentResolverCompat.ContentResolverCompatImplBase
  {
    public Cursor query(ContentResolver paramContentResolver, Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2, CancellationSignal paramCancellationSignal)
    {
      if (paramCancellationSignal != null) {}
      for (;;)
      {
        try
        {
          paramCancellationSignal = paramCancellationSignal.getCancellationSignalObject();
          paramContentResolver = ContentResolverCompatJellybean.query(paramContentResolver, paramUri, paramArrayOfString1, paramString1, paramArrayOfString2, paramString2, paramCancellationSignal);
          return paramContentResolver;
        }
        catch (Exception paramContentResolver)
        {
          if (!ContentResolverCompatJellybean.isFrameworkOperationCanceledException(paramContentResolver)) {
            continue;
          }
          throw new OperationCanceledException();
          throw paramContentResolver;
        }
        paramCancellationSignal = null;
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\android\support\v4\content\ContentResolverCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */