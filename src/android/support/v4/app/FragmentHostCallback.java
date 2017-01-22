package android.support.v4.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.SimpleArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public abstract class FragmentHostCallback<E>
  extends FragmentContainer
{
  private final Activity mActivity;
  private SimpleArrayMap<String, LoaderManager> mAllLoaderManagers;
  private boolean mCheckedForLoaderManager;
  final Context mContext;
  final FragmentManagerImpl mFragmentManager = new FragmentManagerImpl();
  private final Handler mHandler;
  private LoaderManagerImpl mLoaderManager;
  private boolean mLoadersStarted;
  final int mWindowAnimations;
  
  FragmentHostCallback(Activity paramActivity, Context paramContext, Handler paramHandler, int paramInt)
  {
    this.mActivity = paramActivity;
    this.mContext = paramContext;
    this.mHandler = paramHandler;
    this.mWindowAnimations = paramInt;
  }
  
  public FragmentHostCallback(Context paramContext, Handler paramHandler, int paramInt)
  {
    this(null, paramContext, paramHandler, paramInt);
  }
  
  FragmentHostCallback(FragmentActivity paramFragmentActivity)
  {
    this(paramFragmentActivity, paramFragmentActivity, paramFragmentActivity.mHandler, 0);
  }
  
  void doLoaderDestroy()
  {
    if (this.mLoaderManager == null) {}
    for (;;)
    {
      return;
      this.mLoaderManager.doDestroy();
    }
  }
  
  void doLoaderRetain()
  {
    if (this.mLoaderManager == null) {}
    for (;;)
    {
      return;
      this.mLoaderManager.doRetain();
    }
  }
  
  void doLoaderStart()
  {
    if (this.mLoadersStarted) {
      return;
    }
    this.mLoadersStarted = true;
    if (this.mLoaderManager != null) {
      this.mLoaderManager.doStart();
    }
    for (;;)
    {
      this.mCheckedForLoaderManager = true;
      break;
      if (!this.mCheckedForLoaderManager)
      {
        this.mLoaderManager = getLoaderManager("(root)", this.mLoadersStarted, false);
        if ((this.mLoaderManager != null) && (!this.mLoaderManager.mStarted)) {
          this.mLoaderManager.doStart();
        }
      }
    }
  }
  
  void doLoaderStop(boolean paramBoolean)
  {
    if (this.mLoaderManager == null) {}
    for (;;)
    {
      return;
      if (this.mLoadersStarted)
      {
        this.mLoadersStarted = false;
        if (paramBoolean) {
          this.mLoaderManager.doRetain();
        } else {
          this.mLoaderManager.doStop();
        }
      }
    }
  }
  
  void dumpLoaders(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mLoadersStarted=");
    paramPrintWriter.println(this.mLoadersStarted);
    if (this.mLoaderManager != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("Loader Manager ");
      paramPrintWriter.print(Integer.toHexString(System.identityHashCode(this.mLoaderManager)));
      paramPrintWriter.println(":");
      this.mLoaderManager.dump(paramString + "  ", paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    }
  }
  
  Activity getActivity()
  {
    return this.mActivity;
  }
  
  Context getContext()
  {
    return this.mContext;
  }
  
  FragmentManagerImpl getFragmentManagerImpl()
  {
    return this.mFragmentManager;
  }
  
  Handler getHandler()
  {
    return this.mHandler;
  }
  
  LoaderManagerImpl getLoaderManager(String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (this.mAllLoaderManagers == null) {
      this.mAllLoaderManagers = new SimpleArrayMap();
    }
    LoaderManagerImpl localLoaderManagerImpl = (LoaderManagerImpl)this.mAllLoaderManagers.get(paramString);
    if (localLoaderManagerImpl == null) {
      if (paramBoolean2)
      {
        localLoaderManagerImpl = new LoaderManagerImpl(paramString, this, paramBoolean1);
        this.mAllLoaderManagers.put(paramString, localLoaderManagerImpl);
      }
    }
    for (;;)
    {
      return localLoaderManagerImpl;
      localLoaderManagerImpl.updateHostController(this);
    }
  }
  
  LoaderManagerImpl getLoaderManagerImpl()
  {
    if (this.mLoaderManager != null) {}
    for (LoaderManagerImpl localLoaderManagerImpl = this.mLoaderManager;; localLoaderManagerImpl = this.mLoaderManager)
    {
      return localLoaderManagerImpl;
      this.mCheckedForLoaderManager = true;
      this.mLoaderManager = getLoaderManager("(root)", this.mLoadersStarted, true);
    }
  }
  
  void inactivateFragment(String paramString)
  {
    if (this.mAllLoaderManagers != null)
    {
      LoaderManagerImpl localLoaderManagerImpl = (LoaderManagerImpl)this.mAllLoaderManagers.get(paramString);
      if ((localLoaderManagerImpl != null) && (!localLoaderManagerImpl.mRetaining))
      {
        localLoaderManagerImpl.doDestroy();
        this.mAllLoaderManagers.remove(paramString);
      }
    }
  }
  
  void onAttachFragment(Fragment paramFragment) {}
  
  public void onDump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {}
  
  @Nullable
  public View onFindViewById(int paramInt)
  {
    return null;
  }
  
  @Nullable
  public abstract E onGetHost();
  
  public LayoutInflater onGetLayoutInflater()
  {
    return (LayoutInflater)this.mContext.getSystemService("layout_inflater");
  }
  
  public int onGetWindowAnimations()
  {
    return this.mWindowAnimations;
  }
  
  public boolean onHasView()
  {
    return true;
  }
  
  public boolean onHasWindowAnimations()
  {
    return true;
  }
  
  public void onRequestPermissionsFromFragment(@NonNull Fragment paramFragment, @NonNull String[] paramArrayOfString, int paramInt) {}
  
  public boolean onShouldSaveFragmentState(Fragment paramFragment)
  {
    return true;
  }
  
  public boolean onShouldShowRequestPermissionRationale(@NonNull String paramString)
  {
    return false;
  }
  
  public void onStartActivityFromFragment(Fragment paramFragment, Intent paramIntent, int paramInt)
  {
    if (paramInt != -1) {
      throw new IllegalStateException("Starting activity with a requestCode requires a FragmentActivity host");
    }
    this.mContext.startActivity(paramIntent);
  }
  
  public void onSupportInvalidateOptionsMenu() {}
  
  void reportLoaderStart()
  {
    if (this.mAllLoaderManagers != null)
    {
      int j = this.mAllLoaderManagers.size();
      LoaderManagerImpl[] arrayOfLoaderManagerImpl = new LoaderManagerImpl[j];
      for (int i = j - 1; i >= 0; i--) {
        arrayOfLoaderManagerImpl[i] = ((LoaderManagerImpl)this.mAllLoaderManagers.valueAt(i));
      }
      for (i = 0; i < j; i++)
      {
        LoaderManagerImpl localLoaderManagerImpl = arrayOfLoaderManagerImpl[i];
        localLoaderManagerImpl.finishRetain();
        localLoaderManagerImpl.doReportStart();
      }
    }
  }
  
  void restoreLoaderNonConfig(SimpleArrayMap<String, LoaderManager> paramSimpleArrayMap)
  {
    this.mAllLoaderManagers = paramSimpleArrayMap;
  }
  
  SimpleArrayMap<String, LoaderManager> retainLoaderNonConfig()
  {
    int k = 0;
    int j = 0;
    if (this.mAllLoaderManagers != null)
    {
      int m = this.mAllLoaderManagers.size();
      localObject1 = new LoaderManagerImpl[m];
      for (int i = m - 1; i >= 0; i--) {
        localObject1[i] = ((LoaderManagerImpl)this.mAllLoaderManagers.valueAt(i));
      }
      k = 0;
      i = j;
      j = k;
      k = i;
      if (j < m)
      {
        Object localObject2 = localObject1[j];
        if (((LoaderManagerImpl)localObject2).mRetaining) {
          i = 1;
        }
        for (;;)
        {
          j++;
          break;
          ((LoaderManagerImpl)localObject2).doDestroy();
          this.mAllLoaderManagers.remove(((LoaderManagerImpl)localObject2).mWho);
        }
      }
    }
    if (k != 0) {}
    for (Object localObject1 = this.mAllLoaderManagers;; localObject1 = null) {
      return (SimpleArrayMap<String, LoaderManager>)localObject1;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\android\support\v4\app\FragmentHostCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */