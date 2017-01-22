package android.support.v4.print;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.CancellationSignal.OnCancelListener;
import android.print.PrintAttributes;
import android.print.PrintAttributes.Builder;
import android.print.PrintAttributes.MediaSize;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentAdapter.LayoutResultCallback;
import android.print.PrintDocumentInfo.Builder;
import android.print.PrintManager;
import android.util.Log;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

class PrintHelperKitkat
{
  public static final int COLOR_MODE_COLOR = 2;
  public static final int COLOR_MODE_MONOCHROME = 1;
  private static final String LOG_TAG = "PrintHelperKitkat";
  private static final int MAX_PRINT_SIZE = 3500;
  public static final int ORIENTATION_LANDSCAPE = 1;
  public static final int ORIENTATION_PORTRAIT = 2;
  public static final int SCALE_MODE_FILL = 2;
  public static final int SCALE_MODE_FIT = 1;
  int mColorMode = 2;
  final Context mContext;
  BitmapFactory.Options mDecodeOptions = null;
  private final Object mLock = new Object();
  int mOrientation = 1;
  int mScaleMode = 2;
  
  PrintHelperKitkat(Context paramContext)
  {
    this.mContext = paramContext;
  }
  
  private Bitmap convertBitmapForColorMode(Bitmap paramBitmap, int paramInt)
  {
    if (paramInt != 1) {}
    for (;;)
    {
      return paramBitmap;
      Bitmap localBitmap = Bitmap.createBitmap(paramBitmap.getWidth(), paramBitmap.getHeight(), Bitmap.Config.ARGB_8888);
      Canvas localCanvas = new Canvas(localBitmap);
      Paint localPaint = new Paint();
      ColorMatrix localColorMatrix = new ColorMatrix();
      localColorMatrix.setSaturation(0.0F);
      localPaint.setColorFilter(new ColorMatrixColorFilter(localColorMatrix));
      localCanvas.drawBitmap(paramBitmap, 0.0F, 0.0F, localPaint);
      localCanvas.setBitmap(null);
      paramBitmap = localBitmap;
    }
  }
  
  private Matrix getMatrix(int paramInt1, int paramInt2, RectF paramRectF, int paramInt3)
  {
    Matrix localMatrix = new Matrix();
    float f = paramRectF.width() / paramInt1;
    if (paramInt3 == 2) {}
    for (f = Math.max(f, paramRectF.height() / paramInt2);; f = Math.min(f, paramRectF.height() / paramInt2))
    {
      localMatrix.postScale(f, f);
      localMatrix.postTranslate((paramRectF.width() - paramInt1 * f) / 2.0F, (paramRectF.height() - paramInt2 * f) / 2.0F);
      return localMatrix;
    }
  }
  
  private Bitmap loadBitmap(Uri paramUri, BitmapFactory.Options paramOptions)
    throws FileNotFoundException
  {
    if ((paramUri == null) || (this.mContext == null)) {
      throw new IllegalArgumentException("bad argument to loadBitmap");
    }
    localUri = null;
    try
    {
      paramUri = this.mContext.getContentResolver().openInputStream(paramUri);
      localUri = paramUri;
      paramOptions = BitmapFactory.decodeStream(paramUri, null, paramOptions);
      if (paramUri != null) {}
      try
      {
        paramUri.close();
        return paramOptions;
      }
      catch (IOException paramUri)
      {
        for (;;)
        {
          Log.w("PrintHelperKitkat", "close fail ", paramUri);
        }
      }
      try
      {
        localUri.close();
        throw paramUri;
      }
      catch (IOException paramOptions)
      {
        for (;;)
        {
          Log.w("PrintHelperKitkat", "close fail ", paramOptions);
        }
      }
    }
    finally
    {
      if (localUri == null) {}
    }
  }
  
  private Bitmap loadConstrainedBitmap(Uri arg1, int paramInt)
    throws FileNotFoundException
  {
    BitmapFactory.Options localOptions = null;
    if ((paramInt <= 0) || (??? == null) || (this.mContext == null)) {
      throw new IllegalArgumentException("bad argument to getScaledBitmap");
    }
    ??? = new BitmapFactory.Options();
    ((BitmapFactory.Options)???).inJustDecodeBounds = true;
    loadBitmap(???, (BitmapFactory.Options)???);
    int k = ((BitmapFactory.Options)???).outWidth;
    int m = ((BitmapFactory.Options)???).outHeight;
    ??? = localOptions;
    if (k > 0)
    {
      if (m > 0) {
        break label86;
      }
      ??? = localOptions;
    }
    label86:
    int i;
    do
    {
      do
      {
        return (Bitmap)???;
        int j = Math.max(k, m);
        i = 1;
        while (j > paramInt)
        {
          j >>>= 1;
          i <<= 1;
        }
        ??? = localOptions;
      } while (i <= 0);
      ??? = localOptions;
    } while (Math.min(k, m) / i <= 0);
    synchronized (this.mLock)
    {
      localOptions = new android/graphics/BitmapFactory$Options;
      localOptions.<init>();
      this.mDecodeOptions = localOptions;
      this.mDecodeOptions.inMutable = true;
      this.mDecodeOptions.inSampleSize = i;
      localOptions = this.mDecodeOptions;
    }
    try
    {
      ??? = loadBitmap(???, localOptions);
      synchronized (this.mLock)
      {
        this.mDecodeOptions = null;
      }
      ??? = finally;
      throw ???;
    }
    finally {}
  }
  
  public int getColorMode()
  {
    return this.mColorMode;
  }
  
  public int getOrientation()
  {
    return this.mOrientation;
  }
  
  public int getScaleMode()
  {
    return this.mScaleMode;
  }
  
  public void printBitmap(final String paramString, final Bitmap paramBitmap, final OnPrintFinishCallback paramOnPrintFinishCallback)
  {
    if (paramBitmap == null) {}
    for (;;)
    {
      return;
      final int i = this.mScaleMode;
      PrintManager localPrintManager = (PrintManager)this.mContext.getSystemService("print");
      Object localObject = PrintAttributes.MediaSize.UNKNOWN_PORTRAIT;
      if (paramBitmap.getWidth() > paramBitmap.getHeight()) {
        localObject = PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE;
      }
      localObject = new PrintAttributes.Builder().setMediaSize((PrintAttributes.MediaSize)localObject).setColorMode(this.mColorMode).build();
      localPrintManager.print(paramString, new PrintDocumentAdapter()
      {
        private PrintAttributes mAttributes;
        
        public void onFinish()
        {
          if (paramOnPrintFinishCallback != null) {
            paramOnPrintFinishCallback.onFinish();
          }
        }
        
        public void onLayout(PrintAttributes paramAnonymousPrintAttributes1, PrintAttributes paramAnonymousPrintAttributes2, CancellationSignal paramAnonymousCancellationSignal, PrintDocumentAdapter.LayoutResultCallback paramAnonymousLayoutResultCallback, Bundle paramAnonymousBundle)
        {
          boolean bool = true;
          this.mAttributes = paramAnonymousPrintAttributes2;
          paramAnonymousCancellationSignal = new PrintDocumentInfo.Builder(paramString).setContentType(1).setPageCount(1).build();
          if (!paramAnonymousPrintAttributes2.equals(paramAnonymousPrintAttributes1)) {}
          for (;;)
          {
            paramAnonymousLayoutResultCallback.onLayoutFinished(paramAnonymousCancellationSignal, bool);
            return;
            bool = false;
          }
        }
        
        /* Error */
        public void onWrite(android.print.PageRange[] paramAnonymousArrayOfPageRange, android.os.ParcelFileDescriptor paramAnonymousParcelFileDescriptor, CancellationSignal paramAnonymousCancellationSignal, android.print.PrintDocumentAdapter.WriteResultCallback paramAnonymousWriteResultCallback)
        {
          // Byte code:
          //   0: new 80	android/print/pdf/PrintedPdfDocument
          //   3: dup
          //   4: aload_0
          //   5: getfield 25	android/support/v4/print/PrintHelperKitkat$1:this$0	Landroid/support/v4/print/PrintHelperKitkat;
          //   8: getfield 84	android/support/v4/print/PrintHelperKitkat:mContext	Landroid/content/Context;
          //   11: aload_0
          //   12: getfield 46	android/support/v4/print/PrintHelperKitkat$1:mAttributes	Landroid/print/PrintAttributes;
          //   15: invokespecial 87	android/print/pdf/PrintedPdfDocument:<init>	(Landroid/content/Context;Landroid/print/PrintAttributes;)V
          //   18: astore_3
          //   19: aload_0
          //   20: getfield 25	android/support/v4/print/PrintHelperKitkat$1:this$0	Landroid/support/v4/print/PrintHelperKitkat;
          //   23: aload_0
          //   24: getfield 29	android/support/v4/print/PrintHelperKitkat$1:val$bitmap	Landroid/graphics/Bitmap;
          //   27: aload_0
          //   28: getfield 46	android/support/v4/print/PrintHelperKitkat$1:mAttributes	Landroid/print/PrintAttributes;
          //   31: invokevirtual 91	android/print/PrintAttributes:getColorMode	()I
          //   34: invokestatic 95	android/support/v4/print/PrintHelperKitkat:access$000	(Landroid/support/v4/print/PrintHelperKitkat;Landroid/graphics/Bitmap;I)Landroid/graphics/Bitmap;
          //   37: astore_1
          //   38: aload_3
          //   39: iconst_1
          //   40: invokevirtual 99	android/print/pdf/PrintedPdfDocument:startPage	(I)Landroid/graphics/pdf/PdfDocument$Page;
          //   43: astore 5
          //   45: new 101	android/graphics/RectF
          //   48: astore 6
          //   50: aload 6
          //   52: aload 5
          //   54: invokevirtual 107	android/graphics/pdf/PdfDocument$Page:getInfo	()Landroid/graphics/pdf/PdfDocument$PageInfo;
          //   57: invokevirtual 113	android/graphics/pdf/PdfDocument$PageInfo:getContentRect	()Landroid/graphics/Rect;
          //   60: invokespecial 116	android/graphics/RectF:<init>	(Landroid/graphics/Rect;)V
          //   63: aload_0
          //   64: getfield 25	android/support/v4/print/PrintHelperKitkat$1:this$0	Landroid/support/v4/print/PrintHelperKitkat;
          //   67: aload_1
          //   68: invokevirtual 121	android/graphics/Bitmap:getWidth	()I
          //   71: aload_1
          //   72: invokevirtual 124	android/graphics/Bitmap:getHeight	()I
          //   75: aload 6
          //   77: aload_0
          //   78: getfield 31	android/support/v4/print/PrintHelperKitkat$1:val$fittingMode	I
          //   81: invokestatic 128	android/support/v4/print/PrintHelperKitkat:access$100	(Landroid/support/v4/print/PrintHelperKitkat;IILandroid/graphics/RectF;I)Landroid/graphics/Matrix;
          //   84: astore 6
          //   86: aload 5
          //   88: invokevirtual 132	android/graphics/pdf/PdfDocument$Page:getCanvas	()Landroid/graphics/Canvas;
          //   91: aload_1
          //   92: aload 6
          //   94: aconst_null
          //   95: invokevirtual 138	android/graphics/Canvas:drawBitmap	(Landroid/graphics/Bitmap;Landroid/graphics/Matrix;Landroid/graphics/Paint;)V
          //   98: aload_3
          //   99: aload 5
          //   101: invokevirtual 142	android/print/pdf/PrintedPdfDocument:finishPage	(Landroid/graphics/pdf/PdfDocument$Page;)V
          //   104: new 144	java/io/FileOutputStream
          //   107: astore 5
          //   109: aload 5
          //   111: aload_2
          //   112: invokevirtual 150	android/os/ParcelFileDescriptor:getFileDescriptor	()Ljava/io/FileDescriptor;
          //   115: invokespecial 153	java/io/FileOutputStream:<init>	(Ljava/io/FileDescriptor;)V
          //   118: aload_3
          //   119: aload 5
          //   121: invokevirtual 157	android/print/pdf/PrintedPdfDocument:writeTo	(Ljava/io/OutputStream;)V
          //   124: aload 4
          //   126: iconst_1
          //   127: anewarray 159	android/print/PageRange
          //   130: dup
          //   131: iconst_0
          //   132: getstatic 163	android/print/PageRange:ALL_PAGES	Landroid/print/PageRange;
          //   135: aastore
          //   136: invokevirtual 169	android/print/PrintDocumentAdapter$WriteResultCallback:onWriteFinished	([Landroid/print/PageRange;)V
          //   139: aload_3
          //   140: ifnull +7 -> 147
          //   143: aload_3
          //   144: invokevirtual 172	android/print/pdf/PrintedPdfDocument:close	()V
          //   147: aload_2
          //   148: ifnull +7 -> 155
          //   151: aload_2
          //   152: invokevirtual 173	android/os/ParcelFileDescriptor:close	()V
          //   155: aload_1
          //   156: aload_0
          //   157: getfield 29	android/support/v4/print/PrintHelperKitkat$1:val$bitmap	Landroid/graphics/Bitmap;
          //   160: if_acmpeq +7 -> 167
          //   163: aload_1
          //   164: invokevirtual 176	android/graphics/Bitmap:recycle	()V
          //   167: return
          //   168: astore 5
          //   170: ldc -78
          //   172: ldc -76
          //   174: aload 5
          //   176: invokestatic 186	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
          //   179: pop
          //   180: aload 4
          //   182: aconst_null
          //   183: invokevirtual 190	android/print/PrintDocumentAdapter$WriteResultCallback:onWriteFailed	(Ljava/lang/CharSequence;)V
          //   186: goto -47 -> 139
          //   189: astore 4
          //   191: aload_3
          //   192: ifnull +7 -> 199
          //   195: aload_3
          //   196: invokevirtual 172	android/print/pdf/PrintedPdfDocument:close	()V
          //   199: aload_2
          //   200: ifnull +7 -> 207
          //   203: aload_2
          //   204: invokevirtual 173	android/os/ParcelFileDescriptor:close	()V
          //   207: aload_1
          //   208: aload_0
          //   209: getfield 29	android/support/v4/print/PrintHelperKitkat$1:val$bitmap	Landroid/graphics/Bitmap;
          //   212: if_acmpeq +7 -> 219
          //   215: aload_1
          //   216: invokevirtual 176	android/graphics/Bitmap:recycle	()V
          //   219: aload 4
          //   221: athrow
          //   222: astore_2
          //   223: goto -68 -> 155
          //   226: astore_2
          //   227: goto -20 -> 207
          // Local variable table:
          //   start	length	slot	name	signature
          //   0	230	0	this	1
          //   0	230	1	paramAnonymousArrayOfPageRange	android.print.PageRange[]
          //   0	230	2	paramAnonymousParcelFileDescriptor	android.os.ParcelFileDescriptor
          //   0	230	3	paramAnonymousCancellationSignal	CancellationSignal
          //   0	230	4	paramAnonymousWriteResultCallback	android.print.PrintDocumentAdapter.WriteResultCallback
          //   43	77	5	localObject1	Object
          //   168	7	5	localIOException	IOException
          //   48	45	6	localObject2	Object
          // Exception table:
          //   from	to	target	type
          //   104	139	168	java/io/IOException
          //   38	104	189	finally
          //   104	139	189	finally
          //   170	186	189	finally
          //   151	155	222	java/io/IOException
          //   203	207	226	java/io/IOException
        }
      }, (PrintAttributes)localObject);
    }
  }
  
  public void printBitmap(final String paramString, final Uri paramUri, final OnPrintFinishCallback paramOnPrintFinishCallback)
    throws FileNotFoundException
  {
    paramUri = new PrintDocumentAdapter()
    {
      private PrintAttributes mAttributes;
      Bitmap mBitmap = null;
      AsyncTask<Uri, Boolean, Bitmap> mLoadBitmap;
      
      private void cancelLoad()
      {
        synchronized (PrintHelperKitkat.this.mLock)
        {
          if (PrintHelperKitkat.this.mDecodeOptions != null)
          {
            PrintHelperKitkat.this.mDecodeOptions.requestCancelDecode();
            PrintHelperKitkat.this.mDecodeOptions = null;
          }
          return;
        }
      }
      
      public void onFinish()
      {
        super.onFinish();
        cancelLoad();
        if (this.mLoadBitmap != null) {
          this.mLoadBitmap.cancel(true);
        }
        if (paramOnPrintFinishCallback != null) {
          paramOnPrintFinishCallback.onFinish();
        }
        if (this.mBitmap != null)
        {
          this.mBitmap.recycle();
          this.mBitmap = null;
        }
      }
      
      public void onLayout(final PrintAttributes paramAnonymousPrintAttributes1, final PrintAttributes paramAnonymousPrintAttributes2, final CancellationSignal paramAnonymousCancellationSignal, final PrintDocumentAdapter.LayoutResultCallback paramAnonymousLayoutResultCallback, Bundle paramAnonymousBundle)
      {
        boolean bool = true;
        this.mAttributes = paramAnonymousPrintAttributes2;
        if (paramAnonymousCancellationSignal.isCanceled()) {
          paramAnonymousLayoutResultCallback.onLayoutCancelled();
        }
        for (;;)
        {
          return;
          if (this.mBitmap != null)
          {
            paramAnonymousCancellationSignal = new PrintDocumentInfo.Builder(paramString).setContentType(1).setPageCount(1).build();
            if (!paramAnonymousPrintAttributes2.equals(paramAnonymousPrintAttributes1)) {}
            for (;;)
            {
              paramAnonymousLayoutResultCallback.onLayoutFinished(paramAnonymousCancellationSignal, bool);
              break;
              bool = false;
            }
          }
          this.mLoadBitmap = new AsyncTask()
          {
            protected Bitmap doInBackground(Uri... paramAnonymous2VarArgs)
            {
              try
              {
                paramAnonymous2VarArgs = PrintHelperKitkat.this.loadConstrainedBitmap(PrintHelperKitkat.2.this.val$imageFile, 3500);
                return paramAnonymous2VarArgs;
              }
              catch (FileNotFoundException paramAnonymous2VarArgs)
              {
                for (;;)
                {
                  paramAnonymous2VarArgs = null;
                }
              }
            }
            
            protected void onCancelled(Bitmap paramAnonymous2Bitmap)
            {
              paramAnonymousLayoutResultCallback.onLayoutCancelled();
              PrintHelperKitkat.2.this.mLoadBitmap = null;
            }
            
            protected void onPostExecute(Bitmap paramAnonymous2Bitmap)
            {
              boolean bool = true;
              super.onPostExecute(paramAnonymous2Bitmap);
              PrintHelperKitkat.2.this.mBitmap = paramAnonymous2Bitmap;
              if (paramAnonymous2Bitmap != null)
              {
                paramAnonymous2Bitmap = new PrintDocumentInfo.Builder(PrintHelperKitkat.2.this.val$jobName).setContentType(1).setPageCount(1).build();
                if (!paramAnonymousPrintAttributes2.equals(paramAnonymousPrintAttributes1)) {
                  paramAnonymousLayoutResultCallback.onLayoutFinished(paramAnonymous2Bitmap, bool);
                }
              }
              for (;;)
              {
                PrintHelperKitkat.2.this.mLoadBitmap = null;
                return;
                bool = false;
                break;
                paramAnonymousLayoutResultCallback.onLayoutFailed(null);
              }
            }
            
            protected void onPreExecute()
            {
              paramAnonymousCancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener()
              {
                public void onCancel()
                {
                  PrintHelperKitkat.2.this.cancelLoad();
                  PrintHelperKitkat.2.1.this.cancel(false);
                }
              });
            }
          }.execute(new Uri[0]);
        }
      }
      
      /* Error */
      public void onWrite(android.print.PageRange[] paramAnonymousArrayOfPageRange, android.os.ParcelFileDescriptor paramAnonymousParcelFileDescriptor, CancellationSignal paramAnonymousCancellationSignal, android.print.PrintDocumentAdapter.WriteResultCallback paramAnonymousWriteResultCallback)
      {
        // Byte code:
        //   0: new 141	android/print/pdf/PrintedPdfDocument
        //   3: dup
        //   4: aload_0
        //   5: getfield 34	android/support/v4/print/PrintHelperKitkat$2:this$0	Landroid/support/v4/print/PrintHelperKitkat;
        //   8: getfield 145	android/support/v4/print/PrintHelperKitkat:mContext	Landroid/content/Context;
        //   11: aload_0
        //   12: getfield 89	android/support/v4/print/PrintHelperKitkat$2:mAttributes	Landroid/print/PrintAttributes;
        //   15: invokespecial 148	android/print/pdf/PrintedPdfDocument:<init>	(Landroid/content/Context;Landroid/print/PrintAttributes;)V
        //   18: astore_3
        //   19: aload_0
        //   20: getfield 34	android/support/v4/print/PrintHelperKitkat$2:this$0	Landroid/support/v4/print/PrintHelperKitkat;
        //   23: aload_0
        //   24: getfield 47	android/support/v4/print/PrintHelperKitkat$2:mBitmap	Landroid/graphics/Bitmap;
        //   27: aload_0
        //   28: getfield 89	android/support/v4/print/PrintHelperKitkat$2:mAttributes	Landroid/print/PrintAttributes;
        //   31: invokevirtual 152	android/print/PrintAttributes:getColorMode	()I
        //   34: invokestatic 156	android/support/v4/print/PrintHelperKitkat:access$000	(Landroid/support/v4/print/PrintHelperKitkat;Landroid/graphics/Bitmap;I)Landroid/graphics/Bitmap;
        //   37: astore_1
        //   38: aload_3
        //   39: iconst_1
        //   40: invokevirtual 160	android/print/pdf/PrintedPdfDocument:startPage	(I)Landroid/graphics/pdf/PdfDocument$Page;
        //   43: astore 5
        //   45: new 162	android/graphics/RectF
        //   48: astore 6
        //   50: aload 6
        //   52: aload 5
        //   54: invokevirtual 168	android/graphics/pdf/PdfDocument$Page:getInfo	()Landroid/graphics/pdf/PdfDocument$PageInfo;
        //   57: invokevirtual 174	android/graphics/pdf/PdfDocument$PageInfo:getContentRect	()Landroid/graphics/Rect;
        //   60: invokespecial 177	android/graphics/RectF:<init>	(Landroid/graphics/Rect;)V
        //   63: aload_0
        //   64: getfield 34	android/support/v4/print/PrintHelperKitkat$2:this$0	Landroid/support/v4/print/PrintHelperKitkat;
        //   67: aload_0
        //   68: getfield 47	android/support/v4/print/PrintHelperKitkat$2:mBitmap	Landroid/graphics/Bitmap;
        //   71: invokevirtual 180	android/graphics/Bitmap:getWidth	()I
        //   74: aload_0
        //   75: getfield 47	android/support/v4/print/PrintHelperKitkat$2:mBitmap	Landroid/graphics/Bitmap;
        //   78: invokevirtual 183	android/graphics/Bitmap:getHeight	()I
        //   81: aload 6
        //   83: aload_0
        //   84: getfield 42	android/support/v4/print/PrintHelperKitkat$2:val$fittingMode	I
        //   87: invokestatic 187	android/support/v4/print/PrintHelperKitkat:access$100	(Landroid/support/v4/print/PrintHelperKitkat;IILandroid/graphics/RectF;I)Landroid/graphics/Matrix;
        //   90: astore 6
        //   92: aload 5
        //   94: invokevirtual 191	android/graphics/pdf/PdfDocument$Page:getCanvas	()Landroid/graphics/Canvas;
        //   97: aload_1
        //   98: aload 6
        //   100: aconst_null
        //   101: invokevirtual 197	android/graphics/Canvas:drawBitmap	(Landroid/graphics/Bitmap;Landroid/graphics/Matrix;Landroid/graphics/Paint;)V
        //   104: aload_3
        //   105: aload 5
        //   107: invokevirtual 201	android/print/pdf/PrintedPdfDocument:finishPage	(Landroid/graphics/pdf/PdfDocument$Page;)V
        //   110: new 203	java/io/FileOutputStream
        //   113: astore 5
        //   115: aload 5
        //   117: aload_2
        //   118: invokevirtual 209	android/os/ParcelFileDescriptor:getFileDescriptor	()Ljava/io/FileDescriptor;
        //   121: invokespecial 212	java/io/FileOutputStream:<init>	(Ljava/io/FileDescriptor;)V
        //   124: aload_3
        //   125: aload 5
        //   127: invokevirtual 216	android/print/pdf/PrintedPdfDocument:writeTo	(Ljava/io/OutputStream;)V
        //   130: aload 4
        //   132: iconst_1
        //   133: anewarray 218	android/print/PageRange
        //   136: dup
        //   137: iconst_0
        //   138: getstatic 222	android/print/PageRange:ALL_PAGES	Landroid/print/PageRange;
        //   141: aastore
        //   142: invokevirtual 228	android/print/PrintDocumentAdapter$WriteResultCallback:onWriteFinished	([Landroid/print/PageRange;)V
        //   145: aload_3
        //   146: ifnull +7 -> 153
        //   149: aload_3
        //   150: invokevirtual 231	android/print/pdf/PrintedPdfDocument:close	()V
        //   153: aload_2
        //   154: ifnull +7 -> 161
        //   157: aload_2
        //   158: invokevirtual 232	android/os/ParcelFileDescriptor:close	()V
        //   161: aload_1
        //   162: aload_0
        //   163: getfield 47	android/support/v4/print/PrintHelperKitkat$2:mBitmap	Landroid/graphics/Bitmap;
        //   166: if_acmpeq +7 -> 173
        //   169: aload_1
        //   170: invokevirtual 85	android/graphics/Bitmap:recycle	()V
        //   173: return
        //   174: astore 5
        //   176: ldc -22
        //   178: ldc -20
        //   180: aload 5
        //   182: invokestatic 242	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   185: pop
        //   186: aload 4
        //   188: aconst_null
        //   189: invokevirtual 246	android/print/PrintDocumentAdapter$WriteResultCallback:onWriteFailed	(Ljava/lang/CharSequence;)V
        //   192: goto -47 -> 145
        //   195: astore 4
        //   197: aload_3
        //   198: ifnull +7 -> 205
        //   201: aload_3
        //   202: invokevirtual 231	android/print/pdf/PrintedPdfDocument:close	()V
        //   205: aload_2
        //   206: ifnull +7 -> 213
        //   209: aload_2
        //   210: invokevirtual 232	android/os/ParcelFileDescriptor:close	()V
        //   213: aload_1
        //   214: aload_0
        //   215: getfield 47	android/support/v4/print/PrintHelperKitkat$2:mBitmap	Landroid/graphics/Bitmap;
        //   218: if_acmpeq +7 -> 225
        //   221: aload_1
        //   222: invokevirtual 85	android/graphics/Bitmap:recycle	()V
        //   225: aload 4
        //   227: athrow
        //   228: astore_2
        //   229: goto -68 -> 161
        //   232: astore_2
        //   233: goto -20 -> 213
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	236	0	this	2
        //   0	236	1	paramAnonymousArrayOfPageRange	android.print.PageRange[]
        //   0	236	2	paramAnonymousParcelFileDescriptor	android.os.ParcelFileDescriptor
        //   0	236	3	paramAnonymousCancellationSignal	CancellationSignal
        //   0	236	4	paramAnonymousWriteResultCallback	android.print.PrintDocumentAdapter.WriteResultCallback
        //   43	83	5	localObject1	Object
        //   174	7	5	localIOException	IOException
        //   48	51	6	localObject2	Object
        // Exception table:
        //   from	to	target	type
        //   110	145	174	java/io/IOException
        //   38	110	195	finally
        //   110	145	195	finally
        //   176	192	195	finally
        //   157	161	228	java/io/IOException
        //   209	213	232	java/io/IOException
      }
    };
    paramOnPrintFinishCallback = (PrintManager)this.mContext.getSystemService("print");
    PrintAttributes.Builder localBuilder = new PrintAttributes.Builder();
    localBuilder.setColorMode(this.mColorMode);
    if (this.mOrientation == 1) {
      localBuilder.setMediaSize(PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE);
    }
    for (;;)
    {
      paramOnPrintFinishCallback.print(paramString, paramUri, localBuilder.build());
      return;
      if (this.mOrientation == 2) {
        localBuilder.setMediaSize(PrintAttributes.MediaSize.UNKNOWN_PORTRAIT);
      }
    }
  }
  
  public void setColorMode(int paramInt)
  {
    this.mColorMode = paramInt;
  }
  
  public void setOrientation(int paramInt)
  {
    this.mOrientation = paramInt;
  }
  
  public void setScaleMode(int paramInt)
  {
    this.mScaleMode = paramInt;
  }
  
  public static abstract interface OnPrintFinishCallback
  {
    public abstract void onFinish();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\android\support\v4\print\PrintHelperKitkat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */