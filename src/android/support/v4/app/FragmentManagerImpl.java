package android.support.v4.app;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.annotation.CallSuper;
import android.support.v4.util.DebugUtils;
import android.support.v4.util.LogWriter;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

final class FragmentManagerImpl
  extends FragmentManager
  implements LayoutInflaterFactory
{
  static final Interpolator ACCELERATE_CUBIC = new AccelerateInterpolator(1.5F);
  static final Interpolator ACCELERATE_QUINT;
  static final int ANIM_DUR = 220;
  public static final int ANIM_STYLE_CLOSE_ENTER = 3;
  public static final int ANIM_STYLE_CLOSE_EXIT = 4;
  public static final int ANIM_STYLE_FADE_ENTER = 5;
  public static final int ANIM_STYLE_FADE_EXIT = 6;
  public static final int ANIM_STYLE_OPEN_ENTER = 1;
  public static final int ANIM_STYLE_OPEN_EXIT = 2;
  static boolean DEBUG = false;
  static final Interpolator DECELERATE_CUBIC;
  static final Interpolator DECELERATE_QUINT;
  static final boolean HONEYCOMB;
  static final String TAG = "FragmentManager";
  static final String TARGET_REQUEST_CODE_STATE_TAG = "android:target_req_state";
  static final String TARGET_STATE_TAG = "android:target_state";
  static final String USER_VISIBLE_HINT_TAG = "android:user_visible_hint";
  static final String VIEW_STATE_TAG = "android:view_state";
  static Field sAnimationListenerField;
  ArrayList<Fragment> mActive;
  ArrayList<Fragment> mAdded;
  ArrayList<Integer> mAvailBackStackIndices;
  ArrayList<Integer> mAvailIndices;
  ArrayList<BackStackRecord> mBackStack;
  ArrayList<FragmentManager.OnBackStackChangedListener> mBackStackChangeListeners;
  ArrayList<BackStackRecord> mBackStackIndices;
  FragmentContainer mContainer;
  FragmentController mController;
  ArrayList<Fragment> mCreatedMenus;
  int mCurState = 0;
  boolean mDestroyed;
  Runnable mExecCommit = new Runnable()
  {
    public void run()
    {
      FragmentManagerImpl.this.execPendingActions();
    }
  };
  boolean mExecutingActions;
  boolean mHavePendingDeferredStart;
  FragmentHostCallback mHost;
  boolean mNeedMenuInvalidate;
  String mNoTransactionsBecause;
  Fragment mParent;
  ArrayList<Runnable> mPendingActions;
  SparseArray<Parcelable> mStateArray = null;
  Bundle mStateBundle = null;
  boolean mStateSaved;
  Runnable[] mTmpActions;
  
  static
  {
    boolean bool = false;
    DEBUG = false;
    if (Build.VERSION.SDK_INT >= 11) {
      bool = true;
    }
    HONEYCOMB = bool;
    sAnimationListenerField = null;
    DECELERATE_QUINT = new DecelerateInterpolator(2.5F);
    DECELERATE_CUBIC = new DecelerateInterpolator(1.5F);
    ACCELERATE_QUINT = new AccelerateInterpolator(2.5F);
  }
  
  private void checkStateLoss()
  {
    if (this.mStateSaved) {
      throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
    }
    if (this.mNoTransactionsBecause != null) {
      throw new IllegalStateException("Can not perform this action inside of " + this.mNoTransactionsBecause);
    }
  }
  
  static Animation makeFadeAnimation(Context paramContext, float paramFloat1, float paramFloat2)
  {
    paramContext = new AlphaAnimation(paramFloat1, paramFloat2);
    paramContext.setInterpolator(DECELERATE_CUBIC);
    paramContext.setDuration(220L);
    return paramContext;
  }
  
  static Animation makeOpenCloseAnimation(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    paramContext = new AnimationSet(false);
    Object localObject = new ScaleAnimation(paramFloat1, paramFloat2, paramFloat1, paramFloat2, 1, 0.5F, 1, 0.5F);
    ((ScaleAnimation)localObject).setInterpolator(DECELERATE_QUINT);
    ((ScaleAnimation)localObject).setDuration(220L);
    paramContext.addAnimation((Animation)localObject);
    localObject = new AlphaAnimation(paramFloat3, paramFloat4);
    ((AlphaAnimation)localObject).setInterpolator(DECELERATE_CUBIC);
    ((AlphaAnimation)localObject).setDuration(220L);
    paramContext.addAnimation((Animation)localObject);
    return paramContext;
  }
  
  static boolean modifiesAlpha(Animation paramAnimation)
  {
    boolean bool2 = true;
    boolean bool1;
    if ((paramAnimation instanceof AlphaAnimation)) {
      bool1 = bool2;
    }
    for (;;)
    {
      return bool1;
      if ((paramAnimation instanceof AnimationSet))
      {
        paramAnimation = ((AnimationSet)paramAnimation).getAnimations();
        for (int i = 0;; i++)
        {
          if (i >= paramAnimation.size()) {
            break label61;
          }
          bool1 = bool2;
          if ((paramAnimation.get(i) instanceof AlphaAnimation)) {
            break;
          }
        }
      }
      label61:
      bool1 = false;
    }
  }
  
  public static int reverseTransit(int paramInt)
  {
    int i = 0;
    switch (paramInt)
    {
    default: 
      paramInt = i;
    }
    for (;;)
    {
      return paramInt;
      paramInt = 8194;
      continue;
      paramInt = 4097;
      continue;
      paramInt = 4099;
    }
  }
  
  private void setHWLayerAnimListenerIfAlpha(View paramView, Animation paramAnimation)
  {
    if ((paramView == null) || (paramAnimation == null)) {}
    for (;;)
    {
      return;
      Object localObject;
      if (shouldRunOnHWLayer(paramView, paramAnimation)) {
        localObject = null;
      }
      try
      {
        if (sAnimationListenerField == null)
        {
          sAnimationListenerField = Animation.class.getDeclaredField("mListener");
          sAnimationListenerField.setAccessible(true);
        }
        Animation.AnimationListener localAnimationListener = (Animation.AnimationListener)sAnimationListenerField.get(paramAnimation);
        localObject = localAnimationListener;
      }
      catch (NoSuchFieldException localNoSuchFieldException)
      {
        for (;;)
        {
          Log.e("FragmentManager", "No field with the name mListener is found in Animation class", localNoSuchFieldException);
        }
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        for (;;)
        {
          Log.e("FragmentManager", "Cannot access Animation's mListener field", localIllegalAccessException);
        }
      }
      paramAnimation.setAnimationListener(new AnimateOnHWLayerIfNeededListener(paramView, paramAnimation, (Animation.AnimationListener)localObject));
    }
  }
  
  static boolean shouldRunOnHWLayer(View paramView, Animation paramAnimation)
  {
    if ((Build.VERSION.SDK_INT >= 19) && (ViewCompat.getLayerType(paramView) == 0) && (ViewCompat.hasOverlappingRendering(paramView)) && (modifiesAlpha(paramAnimation))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private void throwException(RuntimeException paramRuntimeException)
  {
    Log.e("FragmentManager", paramRuntimeException.getMessage());
    Log.e("FragmentManager", "Activity state:");
    PrintWriter localPrintWriter = new PrintWriter(new LogWriter("FragmentManager"));
    if (this.mHost != null) {}
    for (;;)
    {
      try
      {
        this.mHost.onDump("  ", null, localPrintWriter, new String[0]);
        throw paramRuntimeException;
      }
      catch (Exception localException1)
      {
        Log.e("FragmentManager", "Failed dumping state", localException1);
        continue;
      }
      try
      {
        dump("  ", null, localException1, new String[0]);
      }
      catch (Exception localException2)
      {
        Log.e("FragmentManager", "Failed dumping state", localException2);
      }
    }
  }
  
  public static int transitToStyleIndex(int paramInt, boolean paramBoolean)
  {
    int i = -1;
    switch (paramInt)
    {
    default: 
      paramInt = i;
      return paramInt;
    case 4097: 
      if (paramBoolean) {}
      for (paramInt = 1;; paramInt = 2) {
        break;
      }
    case 8194: 
      if (paramBoolean) {}
      for (paramInt = 3;; paramInt = 4) {
        break;
      }
    }
    if (paramBoolean) {}
    for (paramInt = 5;; paramInt = 6) {
      break;
    }
  }
  
  void addBackStackState(BackStackRecord paramBackStackRecord)
  {
    if (this.mBackStack == null) {
      this.mBackStack = new ArrayList();
    }
    this.mBackStack.add(paramBackStackRecord);
    reportBackStackChanged();
  }
  
  public void addFragment(Fragment paramFragment, boolean paramBoolean)
  {
    if (this.mAdded == null) {
      this.mAdded = new ArrayList();
    }
    if (DEBUG) {
      Log.v("FragmentManager", "add: " + paramFragment);
    }
    makeActive(paramFragment);
    if (!paramFragment.mDetached)
    {
      if (this.mAdded.contains(paramFragment)) {
        throw new IllegalStateException("Fragment already added: " + paramFragment);
      }
      this.mAdded.add(paramFragment);
      paramFragment.mAdded = true;
      paramFragment.mRemoving = false;
      if ((paramFragment.mHasMenu) && (paramFragment.mMenuVisible)) {
        this.mNeedMenuInvalidate = true;
      }
      if (paramBoolean) {
        moveToState(paramFragment);
      }
    }
  }
  
  public void addOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener paramOnBackStackChangedListener)
  {
    if (this.mBackStackChangeListeners == null) {
      this.mBackStackChangeListeners = new ArrayList();
    }
    this.mBackStackChangeListeners.add(paramOnBackStackChangedListener);
  }
  
  /* Error */
  public int allocBackStackIndex(BackStackRecord paramBackStackRecord)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 396	android/support/v4/app/FragmentManagerImpl:mAvailBackStackIndices	Ljava/util/ArrayList;
    //   6: ifnull +13 -> 19
    //   9: aload_0
    //   10: getfield 396	android/support/v4/app/FragmentManagerImpl:mAvailBackStackIndices	Ljava/util/ArrayList;
    //   13: invokevirtual 397	java/util/ArrayList:size	()I
    //   16: ifgt +88 -> 104
    //   19: aload_0
    //   20: getfield 399	android/support/v4/app/FragmentManagerImpl:mBackStackIndices	Ljava/util/ArrayList;
    //   23: ifnonnull +16 -> 39
    //   26: new 338	java/util/ArrayList
    //   29: astore_3
    //   30: aload_3
    //   31: invokespecial 339	java/util/ArrayList:<init>	()V
    //   34: aload_0
    //   35: aload_3
    //   36: putfield 399	android/support/v4/app/FragmentManagerImpl:mBackStackIndices	Ljava/util/ArrayList;
    //   39: aload_0
    //   40: getfield 399	android/support/v4/app/FragmentManagerImpl:mBackStackIndices	Ljava/util/ArrayList;
    //   43: invokevirtual 397	java/util/ArrayList:size	()I
    //   46: istore_2
    //   47: getstatic 111	android/support/v4/app/FragmentManagerImpl:DEBUG	Z
    //   50: ifeq +41 -> 91
    //   53: new 167	java/lang/StringBuilder
    //   56: astore_3
    //   57: aload_3
    //   58: invokespecial 168	java/lang/StringBuilder:<init>	()V
    //   61: ldc 54
    //   63: aload_3
    //   64: ldc_w 401
    //   67: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   70: iload_2
    //   71: invokevirtual 404	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   74: ldc_w 406
    //   77: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   80: aload_1
    //   81: invokevirtual 355	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   84: invokevirtual 178	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   87: invokestatic 358	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   90: pop
    //   91: aload_0
    //   92: getfield 399	android/support/v4/app/FragmentManagerImpl:mBackStackIndices	Ljava/util/ArrayList;
    //   95: aload_1
    //   96: invokevirtual 343	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   99: pop
    //   100: aload_0
    //   101: monitorexit
    //   102: iload_2
    //   103: ireturn
    //   104: aload_0
    //   105: getfield 396	android/support/v4/app/FragmentManagerImpl:mAvailBackStackIndices	Ljava/util/ArrayList;
    //   108: aload_0
    //   109: getfield 396	android/support/v4/app/FragmentManagerImpl:mAvailBackStackIndices	Ljava/util/ArrayList;
    //   112: invokevirtual 397	java/util/ArrayList:size	()I
    //   115: iconst_1
    //   116: isub
    //   117: invokevirtual 409	java/util/ArrayList:remove	(I)Ljava/lang/Object;
    //   120: checkcast 411	java/lang/Integer
    //   123: invokevirtual 414	java/lang/Integer:intValue	()I
    //   126: istore_2
    //   127: getstatic 111	android/support/v4/app/FragmentManagerImpl:DEBUG	Z
    //   130: ifeq +41 -> 171
    //   133: new 167	java/lang/StringBuilder
    //   136: astore_3
    //   137: aload_3
    //   138: invokespecial 168	java/lang/StringBuilder:<init>	()V
    //   141: ldc 54
    //   143: aload_3
    //   144: ldc_w 416
    //   147: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   150: iload_2
    //   151: invokevirtual 404	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   154: ldc_w 418
    //   157: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   160: aload_1
    //   161: invokevirtual 355	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   164: invokevirtual 178	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   167: invokestatic 358	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   170: pop
    //   171: aload_0
    //   172: getfield 399	android/support/v4/app/FragmentManagerImpl:mBackStackIndices	Ljava/util/ArrayList;
    //   175: iload_2
    //   176: aload_1
    //   177: invokevirtual 422	java/util/ArrayList:set	(ILjava/lang/Object;)Ljava/lang/Object;
    //   180: pop
    //   181: aload_0
    //   182: monitorexit
    //   183: goto -81 -> 102
    //   186: astore_1
    //   187: aload_0
    //   188: monitorexit
    //   189: aload_1
    //   190: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	191	0	this	FragmentManagerImpl
    //   0	191	1	paramBackStackRecord	BackStackRecord
    //   46	130	2	i	int
    //   29	115	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	19	186	finally
    //   19	39	186	finally
    //   39	91	186	finally
    //   91	102	186	finally
    //   104	171	186	finally
    //   171	183	186	finally
    //   187	189	186	finally
  }
  
  public void attachController(FragmentHostCallback paramFragmentHostCallback, FragmentContainer paramFragmentContainer, Fragment paramFragment)
  {
    if (this.mHost != null) {
      throw new IllegalStateException("Already attached");
    }
    this.mHost = paramFragmentHostCallback;
    this.mContainer = paramFragmentContainer;
    this.mParent = paramFragment;
  }
  
  public void attachFragment(Fragment paramFragment, int paramInt1, int paramInt2)
  {
    if (DEBUG) {
      Log.v("FragmentManager", "attach: " + paramFragment);
    }
    if (paramFragment.mDetached)
    {
      paramFragment.mDetached = false;
      if (!paramFragment.mAdded)
      {
        if (this.mAdded == null) {
          this.mAdded = new ArrayList();
        }
        if (this.mAdded.contains(paramFragment)) {
          throw new IllegalStateException("Fragment already added: " + paramFragment);
        }
        if (DEBUG) {
          Log.v("FragmentManager", "add from attach: " + paramFragment);
        }
        this.mAdded.add(paramFragment);
        paramFragment.mAdded = true;
        if ((paramFragment.mHasMenu) && (paramFragment.mMenuVisible)) {
          this.mNeedMenuInvalidate = true;
        }
        moveToState(paramFragment, this.mCurState, paramInt1, paramInt2, false);
      }
    }
  }
  
  public FragmentTransaction beginTransaction()
  {
    return new BackStackRecord(this);
  }
  
  public void detachFragment(Fragment paramFragment, int paramInt1, int paramInt2)
  {
    if (DEBUG) {
      Log.v("FragmentManager", "detach: " + paramFragment);
    }
    if (!paramFragment.mDetached)
    {
      paramFragment.mDetached = true;
      if (paramFragment.mAdded)
      {
        if (this.mAdded != null)
        {
          if (DEBUG) {
            Log.v("FragmentManager", "remove from detach: " + paramFragment);
          }
          this.mAdded.remove(paramFragment);
        }
        if ((paramFragment.mHasMenu) && (paramFragment.mMenuVisible)) {
          this.mNeedMenuInvalidate = true;
        }
        paramFragment.mAdded = false;
        moveToState(paramFragment, 1, paramInt1, paramInt2, false);
      }
    }
  }
  
  public void dispatchActivityCreated()
  {
    this.mStateSaved = false;
    moveToState(2, false);
  }
  
  public void dispatchConfigurationChanged(Configuration paramConfiguration)
  {
    if (this.mAdded != null) {
      for (int i = 0; i < this.mAdded.size(); i++)
      {
        Fragment localFragment = (Fragment)this.mAdded.get(i);
        if (localFragment != null) {
          localFragment.performConfigurationChanged(paramConfiguration);
        }
      }
    }
  }
  
  public boolean dispatchContextItemSelected(MenuItem paramMenuItem)
  {
    int i;
    if (this.mAdded != null)
    {
      i = 0;
      if (i < this.mAdded.size())
      {
        Fragment localFragment = (Fragment)this.mAdded.get(i);
        if ((localFragment == null) || (!localFragment.performContextItemSelected(paramMenuItem))) {}
      }
    }
    for (boolean bool = true;; bool = false)
    {
      return bool;
      i++;
      break;
    }
  }
  
  public void dispatchCreate()
  {
    this.mStateSaved = false;
    moveToState(1, false);
  }
  
  public boolean dispatchCreateOptionsMenu(Menu paramMenu, MenuInflater paramMenuInflater)
  {
    boolean bool2 = false;
    boolean bool1 = false;
    Object localObject2 = null;
    Object localObject1 = null;
    int i;
    if (this.mAdded != null)
    {
      i = 0;
      for (;;)
      {
        localObject2 = localObject1;
        bool2 = bool1;
        if (i >= this.mAdded.size()) {
          break;
        }
        Fragment localFragment = (Fragment)this.mAdded.get(i);
        localObject2 = localObject1;
        bool2 = bool1;
        if (localFragment != null)
        {
          localObject2 = localObject1;
          bool2 = bool1;
          if (localFragment.performCreateOptionsMenu(paramMenu, paramMenuInflater))
          {
            bool2 = true;
            localObject2 = localObject1;
            if (localObject1 == null) {
              localObject2 = new ArrayList();
            }
            ((ArrayList)localObject2).add(localFragment);
          }
        }
        i++;
        localObject1 = localObject2;
        bool1 = bool2;
      }
    }
    if (this.mCreatedMenus != null) {
      for (i = 0; i < this.mCreatedMenus.size(); i++)
      {
        paramMenu = (Fragment)this.mCreatedMenus.get(i);
        if ((localObject2 == null) || (!((ArrayList)localObject2).contains(paramMenu))) {
          paramMenu.onDestroyOptionsMenu();
        }
      }
    }
    this.mCreatedMenus = ((ArrayList)localObject2);
    return bool2;
  }
  
  public void dispatchDestroy()
  {
    this.mDestroyed = true;
    execPendingActions();
    moveToState(0, false);
    this.mHost = null;
    this.mContainer = null;
    this.mParent = null;
  }
  
  public void dispatchDestroyView()
  {
    moveToState(1, false);
  }
  
  public void dispatchLowMemory()
  {
    if (this.mAdded != null) {
      for (int i = 0; i < this.mAdded.size(); i++)
      {
        Fragment localFragment = (Fragment)this.mAdded.get(i);
        if (localFragment != null) {
          localFragment.performLowMemory();
        }
      }
    }
  }
  
  public boolean dispatchOptionsItemSelected(MenuItem paramMenuItem)
  {
    int i;
    if (this.mAdded != null)
    {
      i = 0;
      if (i < this.mAdded.size())
      {
        Fragment localFragment = (Fragment)this.mAdded.get(i);
        if ((localFragment == null) || (!localFragment.performOptionsItemSelected(paramMenuItem))) {}
      }
    }
    for (boolean bool = true;; bool = false)
    {
      return bool;
      i++;
      break;
    }
  }
  
  public void dispatchOptionsMenuClosed(Menu paramMenu)
  {
    if (this.mAdded != null) {
      for (int i = 0; i < this.mAdded.size(); i++)
      {
        Fragment localFragment = (Fragment)this.mAdded.get(i);
        if (localFragment != null) {
          localFragment.performOptionsMenuClosed(paramMenu);
        }
      }
    }
  }
  
  public void dispatchPause()
  {
    moveToState(4, false);
  }
  
  public boolean dispatchPrepareOptionsMenu(Menu paramMenu)
  {
    boolean bool2 = false;
    boolean bool1 = false;
    if (this.mAdded != null)
    {
      int i = 0;
      for (;;)
      {
        bool2 = bool1;
        if (i >= this.mAdded.size()) {
          break;
        }
        Fragment localFragment = (Fragment)this.mAdded.get(i);
        bool2 = bool1;
        if (localFragment != null)
        {
          bool2 = bool1;
          if (localFragment.performPrepareOptionsMenu(paramMenu)) {
            bool2 = true;
          }
        }
        i++;
        bool1 = bool2;
      }
    }
    return bool2;
  }
  
  public void dispatchReallyStop()
  {
    moveToState(2, false);
  }
  
  public void dispatchResume()
  {
    this.mStateSaved = false;
    moveToState(5, false);
  }
  
  public void dispatchStart()
  {
    this.mStateSaved = false;
    moveToState(4, false);
  }
  
  public void dispatchStop()
  {
    this.mStateSaved = true;
    moveToState(3, false);
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    String str = paramString + "    ";
    int j;
    int i;
    Object localObject;
    if (this.mActive != null)
    {
      j = this.mActive.size();
      if (j > 0)
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("Active Fragments in ");
        paramPrintWriter.print(Integer.toHexString(System.identityHashCode(this)));
        paramPrintWriter.println(":");
        for (i = 0; i < j; i++)
        {
          localObject = (Fragment)this.mActive.get(i);
          paramPrintWriter.print(paramString);
          paramPrintWriter.print("  #");
          paramPrintWriter.print(i);
          paramPrintWriter.print(": ");
          paramPrintWriter.println(localObject);
          if (localObject != null) {
            ((Fragment)localObject).dump(str, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
          }
        }
      }
    }
    if (this.mAdded != null)
    {
      j = this.mAdded.size();
      if (j > 0)
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.println("Added Fragments:");
        for (i = 0; i < j; i++)
        {
          localObject = (Fragment)this.mAdded.get(i);
          paramPrintWriter.print(paramString);
          paramPrintWriter.print("  #");
          paramPrintWriter.print(i);
          paramPrintWriter.print(": ");
          paramPrintWriter.println(((Fragment)localObject).toString());
        }
      }
    }
    if (this.mCreatedMenus != null)
    {
      j = this.mCreatedMenus.size();
      if (j > 0)
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.println("Fragments Created Menus:");
        for (i = 0; i < j; i++)
        {
          localObject = (Fragment)this.mCreatedMenus.get(i);
          paramPrintWriter.print(paramString);
          paramPrintWriter.print("  #");
          paramPrintWriter.print(i);
          paramPrintWriter.print(": ");
          paramPrintWriter.println(((Fragment)localObject).toString());
        }
      }
    }
    if (this.mBackStack != null)
    {
      j = this.mBackStack.size();
      if (j > 0)
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.println("Back Stack:");
        for (i = 0; i < j; i++)
        {
          localObject = (BackStackRecord)this.mBackStack.get(i);
          paramPrintWriter.print(paramString);
          paramPrintWriter.print("  #");
          paramPrintWriter.print(i);
          paramPrintWriter.print(": ");
          paramPrintWriter.println(((BackStackRecord)localObject).toString());
          ((BackStackRecord)localObject).dump(str, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
        }
      }
    }
    try
    {
      if (this.mBackStackIndices != null)
      {
        j = this.mBackStackIndices.size();
        if (j > 0)
        {
          paramPrintWriter.print(paramString);
          paramPrintWriter.println("Back Stack Indices:");
          for (i = 0; i < j; i++)
          {
            paramFileDescriptor = (BackStackRecord)this.mBackStackIndices.get(i);
            paramPrintWriter.print(paramString);
            paramPrintWriter.print("  #");
            paramPrintWriter.print(i);
            paramPrintWriter.print(": ");
            paramPrintWriter.println(paramFileDescriptor);
          }
        }
      }
      if ((this.mAvailBackStackIndices != null) && (this.mAvailBackStackIndices.size() > 0))
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("mAvailBackStackIndices: ");
        paramPrintWriter.println(Arrays.toString(this.mAvailBackStackIndices.toArray()));
      }
      if (this.mPendingActions != null)
      {
        j = this.mPendingActions.size();
        if (j > 0)
        {
          paramPrintWriter.print(paramString);
          paramPrintWriter.println("Pending Actions:");
          for (i = 0; i < j; i++)
          {
            paramFileDescriptor = (Runnable)this.mPendingActions.get(i);
            paramPrintWriter.print(paramString);
            paramPrintWriter.print("  #");
            paramPrintWriter.print(i);
            paramPrintWriter.print(": ");
            paramPrintWriter.println(paramFileDescriptor);
          }
        }
      }
      paramPrintWriter.print(paramString);
    }
    finally {}
    paramPrintWriter.println("FragmentManager misc state:");
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("  mHost=");
    paramPrintWriter.println(this.mHost);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("  mContainer=");
    paramPrintWriter.println(this.mContainer);
    if (this.mParent != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("  mParent=");
      paramPrintWriter.println(this.mParent);
    }
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("  mCurState=");
    paramPrintWriter.print(this.mCurState);
    paramPrintWriter.print(" mStateSaved=");
    paramPrintWriter.print(this.mStateSaved);
    paramPrintWriter.print(" mDestroyed=");
    paramPrintWriter.println(this.mDestroyed);
    if (this.mNeedMenuInvalidate)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("  mNeedMenuInvalidate=");
      paramPrintWriter.println(this.mNeedMenuInvalidate);
    }
    if (this.mNoTransactionsBecause != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("  mNoTransactionsBecause=");
      paramPrintWriter.println(this.mNoTransactionsBecause);
    }
    if ((this.mAvailIndices != null) && (this.mAvailIndices.size() > 0))
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("  mAvailIndices: ");
      paramPrintWriter.println(Arrays.toString(this.mAvailIndices.toArray()));
    }
  }
  
  public void enqueueAction(Runnable paramRunnable, boolean paramBoolean)
  {
    if (!paramBoolean) {
      checkStateLoss();
    }
    try
    {
      if ((this.mDestroyed) || (this.mHost == null))
      {
        paramRunnable = new java/lang/IllegalStateException;
        paramRunnable.<init>("Activity has been destroyed");
        throw paramRunnable;
      }
    }
    finally {}
    if (this.mPendingActions == null)
    {
      ArrayList localArrayList = new java/util/ArrayList;
      localArrayList.<init>();
      this.mPendingActions = localArrayList;
    }
    this.mPendingActions.add(paramRunnable);
    if (this.mPendingActions.size() == 1)
    {
      this.mHost.getHandler().removeCallbacks(this.mExecCommit);
      this.mHost.getHandler().post(this.mExecCommit);
    }
  }
  
  public boolean execPendingActions()
  {
    if (this.mExecutingActions) {
      throw new IllegalStateException("Recursive entry to executePendingTransactions");
    }
    if (Looper.myLooper() != this.mHost.getHandler().getLooper()) {
      throw new IllegalStateException("Must be called from main thread of process");
    }
    boolean bool2;
    for (boolean bool3 = false;; bool3 = true) {
      try
      {
        if ((this.mPendingActions == null) || (this.mPendingActions.size() == 0))
        {
          if (!this.mHavePendingDeferredStart) {
            break label274;
          }
          bool2 = false;
          i = 0;
          while (i < this.mActive.size())
          {
            Fragment localFragment = (Fragment)this.mActive.get(i);
            boolean bool1 = bool2;
            if (localFragment != null)
            {
              bool1 = bool2;
              if (localFragment.mLoaderManager != null) {
                bool1 = bool2 | localFragment.mLoaderManager.hasRunningLoaders();
              }
            }
            i++;
            bool2 = bool1;
          }
        }
        int j = this.mPendingActions.size();
        if ((this.mTmpActions == null) || (this.mTmpActions.length < j)) {
          this.mTmpActions = new Runnable[j];
        }
        this.mPendingActions.toArray(this.mTmpActions);
        this.mPendingActions.clear();
        this.mHost.getHandler().removeCallbacks(this.mExecCommit);
        this.mExecutingActions = true;
        for (int i = 0; i < j; i++)
        {
          this.mTmpActions[i].run();
          this.mTmpActions[i] = null;
        }
        this.mExecutingActions = false;
      }
      finally {}
    }
    if (!bool2)
    {
      this.mHavePendingDeferredStart = false;
      startPendingDeferredFragments();
    }
    label274:
    return bool3;
  }
  
  public boolean executePendingTransactions()
  {
    return execPendingActions();
  }
  
  public Fragment findFragmentById(int paramInt)
  {
    int i;
    Object localObject;
    if (this.mAdded != null)
    {
      i = this.mAdded.size() - 1;
      if (i >= 0)
      {
        localObject = (Fragment)this.mAdded.get(i);
        if ((localObject == null) || (((Fragment)localObject).mFragmentId != paramInt)) {}
      }
    }
    for (;;)
    {
      return (Fragment)localObject;
      i--;
      break;
      if (this.mActive != null) {
        for (i = this.mActive.size() - 1;; i--)
        {
          if (i < 0) {
            break label110;
          }
          Fragment localFragment = (Fragment)this.mActive.get(i);
          if (localFragment != null)
          {
            localObject = localFragment;
            if (localFragment.mFragmentId == paramInt) {
              break;
            }
          }
        }
      }
      label110:
      localObject = null;
    }
  }
  
  public Fragment findFragmentByTag(String paramString)
  {
    int i;
    Object localObject;
    if ((this.mAdded != null) && (paramString != null))
    {
      i = this.mAdded.size() - 1;
      if (i >= 0)
      {
        localObject = (Fragment)this.mAdded.get(i);
        if ((localObject == null) || (!paramString.equals(((Fragment)localObject).mTag))) {}
      }
    }
    for (;;)
    {
      return (Fragment)localObject;
      i--;
      break;
      if ((this.mActive != null) && (paramString != null)) {
        for (i = this.mActive.size() - 1;; i--)
        {
          if (i < 0) {
            break label124;
          }
          Fragment localFragment = (Fragment)this.mActive.get(i);
          if (localFragment != null)
          {
            localObject = localFragment;
            if (paramString.equals(localFragment.mTag)) {
              break;
            }
          }
        }
      }
      label124:
      localObject = null;
    }
  }
  
  public Fragment findFragmentByWho(String paramString)
  {
    int i;
    Fragment localFragment;
    if ((this.mActive != null) && (paramString != null))
    {
      i = this.mActive.size() - 1;
      if (i >= 0)
      {
        localFragment = (Fragment)this.mActive.get(i);
        if (localFragment != null)
        {
          localFragment = localFragment.findFragmentByWho(paramString);
          if (localFragment == null) {}
        }
      }
    }
    for (paramString = localFragment;; paramString = null)
    {
      return paramString;
      i--;
      break;
    }
  }
  
  public void freeBackStackIndex(int paramInt)
  {
    try
    {
      this.mBackStackIndices.set(paramInt, null);
      Object localObject1;
      if (this.mAvailBackStackIndices == null)
      {
        localObject1 = new java/util/ArrayList;
        ((ArrayList)localObject1).<init>();
        this.mAvailBackStackIndices = ((ArrayList)localObject1);
      }
      if (DEBUG)
      {
        localObject1 = new java/lang/StringBuilder;
        ((StringBuilder)localObject1).<init>();
        Log.v("FragmentManager", "Freeing back stack index " + paramInt);
      }
      this.mAvailBackStackIndices.add(Integer.valueOf(paramInt));
      return;
    }
    finally {}
  }
  
  public FragmentManager.BackStackEntry getBackStackEntryAt(int paramInt)
  {
    return (FragmentManager.BackStackEntry)this.mBackStack.get(paramInt);
  }
  
  public int getBackStackEntryCount()
  {
    if (this.mBackStack != null) {}
    for (int i = this.mBackStack.size();; i = 0) {
      return i;
    }
  }
  
  public Fragment getFragment(Bundle paramBundle, String paramString)
  {
    int i = paramBundle.getInt(paramString, -1);
    if (i == -1) {
      paramBundle = null;
    }
    for (;;)
    {
      return paramBundle;
      if (i >= this.mActive.size()) {
        throwException(new IllegalStateException("Fragment no longer exists for key " + paramString + ": index " + i));
      }
      Fragment localFragment = (Fragment)this.mActive.get(i);
      paramBundle = localFragment;
      if (localFragment == null)
      {
        throwException(new IllegalStateException("Fragment no longer exists for key " + paramString + ": index " + i));
        paramBundle = localFragment;
      }
    }
  }
  
  public List<Fragment> getFragments()
  {
    return this.mActive;
  }
  
  LayoutInflaterFactory getLayoutInflaterFactory()
  {
    return this;
  }
  
  public void hideFragment(Fragment paramFragment, int paramInt1, int paramInt2)
  {
    if (DEBUG) {
      Log.v("FragmentManager", "hide: " + paramFragment);
    }
    if (!paramFragment.mHidden)
    {
      paramFragment.mHidden = true;
      if (paramFragment.mView != null)
      {
        Animation localAnimation = loadAnimation(paramFragment, paramInt1, false, paramInt2);
        if (localAnimation != null)
        {
          setHWLayerAnimListenerIfAlpha(paramFragment.mView, localAnimation);
          paramFragment.mView.startAnimation(localAnimation);
        }
        paramFragment.mView.setVisibility(8);
      }
      if ((paramFragment.mAdded) && (paramFragment.mHasMenu) && (paramFragment.mMenuVisible)) {
        this.mNeedMenuInvalidate = true;
      }
      paramFragment.onHiddenChanged(true);
    }
  }
  
  public boolean isDestroyed()
  {
    return this.mDestroyed;
  }
  
  Animation loadAnimation(Fragment paramFragment, int paramInt1, boolean paramBoolean, int paramInt2)
  {
    Animation localAnimation = paramFragment.onCreateAnimation(paramInt1, paramBoolean, paramFragment.mNextAnim);
    if (localAnimation != null) {
      paramFragment = localAnimation;
    }
    for (;;)
    {
      return paramFragment;
      if (paramFragment.mNextAnim != 0)
      {
        paramFragment = AnimationUtils.loadAnimation(this.mHost.getContext(), paramFragment.mNextAnim);
        if (paramFragment != null) {}
      }
      else if (paramInt1 == 0)
      {
        paramFragment = null;
      }
      else
      {
        paramInt1 = transitToStyleIndex(paramInt1, paramBoolean);
        if (paramInt1 < 0) {
          paramFragment = null;
        } else {
          switch (paramInt1)
          {
          default: 
            paramInt1 = paramInt2;
            if (paramInt2 == 0)
            {
              paramInt1 = paramInt2;
              if (this.mHost.onHasWindowAnimations()) {
                paramInt1 = this.mHost.onGetWindowAnimations();
              }
            }
            if (paramInt1 == 0) {
              paramFragment = null;
            }
            break;
          case 1: 
            paramFragment = makeOpenCloseAnimation(this.mHost.getContext(), 1.125F, 1.0F, 0.0F, 1.0F);
            break;
          case 2: 
            paramFragment = makeOpenCloseAnimation(this.mHost.getContext(), 1.0F, 0.975F, 1.0F, 0.0F);
            break;
          case 3: 
            paramFragment = makeOpenCloseAnimation(this.mHost.getContext(), 0.975F, 1.0F, 0.0F, 1.0F);
            break;
          case 4: 
            paramFragment = makeOpenCloseAnimation(this.mHost.getContext(), 1.0F, 1.075F, 1.0F, 0.0F);
            break;
          case 5: 
            paramFragment = makeFadeAnimation(this.mHost.getContext(), 0.0F, 1.0F);
            break;
          case 6: 
            paramFragment = makeFadeAnimation(this.mHost.getContext(), 1.0F, 0.0F);
            continue;
            paramFragment = null;
          }
        }
      }
    }
  }
  
  void makeActive(Fragment paramFragment)
  {
    if (paramFragment.mIndex >= 0) {}
    label146:
    for (;;)
    {
      return;
      if ((this.mAvailIndices == null) || (this.mAvailIndices.size() <= 0))
      {
        if (this.mActive == null) {
          this.mActive = new ArrayList();
        }
        paramFragment.setIndex(this.mActive.size(), this.mParent);
        this.mActive.add(paramFragment);
      }
      for (;;)
      {
        if (!DEBUG) {
          break label146;
        }
        Log.v("FragmentManager", "Allocated fragment index " + paramFragment);
        break;
        paramFragment.setIndex(((Integer)this.mAvailIndices.remove(this.mAvailIndices.size() - 1)).intValue(), this.mParent);
        this.mActive.set(paramFragment.mIndex, paramFragment);
      }
    }
  }
  
  void makeInactive(Fragment paramFragment)
  {
    if (paramFragment.mIndex < 0) {}
    for (;;)
    {
      return;
      if (DEBUG) {
        Log.v("FragmentManager", "Freeing fragment index " + paramFragment);
      }
      this.mActive.set(paramFragment.mIndex, null);
      if (this.mAvailIndices == null) {
        this.mAvailIndices = new ArrayList();
      }
      this.mAvailIndices.add(Integer.valueOf(paramFragment.mIndex));
      this.mHost.inactivateFragment(paramFragment.mWho);
      paramFragment.initState();
    }
  }
  
  void moveToState(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    if ((this.mHost == null) && (paramInt1 != 0)) {
      throw new IllegalStateException("No host");
    }
    if ((!paramBoolean) && (this.mCurState == paramInt1)) {}
    for (;;)
    {
      return;
      this.mCurState = paramInt1;
      if (this.mActive != null)
      {
        boolean bool2 = false;
        int i = 0;
        while (i < this.mActive.size())
        {
          Fragment localFragment = (Fragment)this.mActive.get(i);
          boolean bool1 = bool2;
          if (localFragment != null)
          {
            moveToState(localFragment, paramInt1, paramInt2, paramInt3, false);
            bool1 = bool2;
            if (localFragment.mLoaderManager != null) {
              bool1 = bool2 | localFragment.mLoaderManager.hasRunningLoaders();
            }
          }
          i++;
          bool2 = bool1;
        }
        if (!bool2) {
          startPendingDeferredFragments();
        }
        if ((this.mNeedMenuInvalidate) && (this.mHost != null) && (this.mCurState == 5))
        {
          this.mHost.onSupportInvalidateOptionsMenu();
          this.mNeedMenuInvalidate = false;
        }
      }
    }
  }
  
  void moveToState(int paramInt, boolean paramBoolean)
  {
    moveToState(paramInt, 0, 0, paramBoolean);
  }
  
  void moveToState(Fragment paramFragment)
  {
    moveToState(paramFragment, this.mCurState, 0, 0, false);
  }
  
  void moveToState(final Fragment paramFragment, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    int j;
    if (paramFragment.mAdded)
    {
      j = paramInt1;
      if (!paramFragment.mDetached) {}
    }
    else
    {
      j = paramInt1;
      if (paramInt1 > 1) {
        j = 1;
      }
    }
    int i = j;
    if (paramFragment.mRemoving)
    {
      i = j;
      if (j > paramFragment.mState) {
        i = paramFragment.mState;
      }
    }
    paramInt1 = i;
    if (paramFragment.mDeferStart)
    {
      paramInt1 = i;
      if (paramFragment.mState < 4)
      {
        paramInt1 = i;
        if (i > 3) {
          paramInt1 = 3;
        }
      }
    }
    int k;
    if (paramFragment.mState < paramInt1)
    {
      if ((paramFragment.mFromLayout) && (!paramFragment.mInLayout)) {
        return;
      }
      if (paramFragment.mAnimatingAway != null)
      {
        paramFragment.mAnimatingAway = null;
        moveToState(paramFragment, paramFragment.mStateAfterAnimating, 0, 0, true);
      }
      i = paramInt1;
      k = paramInt1;
      j = paramInt1;
      switch (paramFragment.mState)
      {
      default: 
        i = paramInt1;
      }
    }
    for (;;)
    {
      paramFragment.mState = i;
      break;
      if (DEBUG) {
        Log.v("FragmentManager", "moveto CREATED: " + paramFragment);
      }
      j = paramInt1;
      if (paramFragment.mSavedFragmentState != null)
      {
        paramFragment.mSavedFragmentState.setClassLoader(this.mHost.getContext().getClassLoader());
        paramFragment.mSavedViewState = paramFragment.mSavedFragmentState.getSparseParcelableArray("android:view_state");
        paramFragment.mTarget = getFragment(paramFragment.mSavedFragmentState, "android:target_state");
        if (paramFragment.mTarget != null) {
          paramFragment.mTargetRequestCode = paramFragment.mSavedFragmentState.getInt("android:target_req_state", 0);
        }
        paramFragment.mUserVisibleHint = paramFragment.mSavedFragmentState.getBoolean("android:user_visible_hint", true);
        j = paramInt1;
        if (!paramFragment.mUserVisibleHint)
        {
          paramFragment.mDeferStart = true;
          j = paramInt1;
          if (paramInt1 > 3) {
            j = 3;
          }
        }
      }
      paramFragment.mHost = this.mHost;
      paramFragment.mParentFragment = this.mParent;
      if (this.mParent != null) {}
      for (Object localObject1 = this.mParent.mChildFragmentManager;; localObject1 = this.mHost.getFragmentManagerImpl())
      {
        paramFragment.mFragmentManager = ((FragmentManagerImpl)localObject1);
        paramFragment.mCalled = false;
        paramFragment.onAttach(this.mHost.getContext());
        if (paramFragment.mCalled) {
          break;
        }
        throw new SuperNotCalledException("Fragment " + paramFragment + " did not call through to super.onAttach()");
      }
      if (paramFragment.mParentFragment == null) {
        this.mHost.onAttachFragment(paramFragment);
      }
      if (!paramFragment.mRetaining) {
        paramFragment.performCreate(paramFragment.mSavedFragmentState);
      }
      paramFragment.mRetaining = false;
      i = j;
      label548:
      label580:
      Object localObject2;
      if (paramFragment.mFromLayout)
      {
        paramFragment.mView = paramFragment.performCreateView(paramFragment.getLayoutInflater(paramFragment.mSavedFragmentState), null, paramFragment.mSavedFragmentState);
        if (paramFragment.mView == null) {
          break label1046;
        }
        paramFragment.mInnerView = paramFragment.mView;
        if (Build.VERSION.SDK_INT >= 11)
        {
          ViewCompat.setSaveFromParentEnabled(paramFragment.mView, false);
          if (paramFragment.mHidden) {
            paramFragment.mView.setVisibility(8);
          }
          paramFragment.onViewCreated(paramFragment.mView, paramFragment.mSavedFragmentState);
          i = j;
        }
      }
      else
      {
        k = i;
        if (i > 1)
        {
          if (DEBUG) {
            Log.v("FragmentManager", "moveto ACTIVITY_CREATED: " + paramFragment);
          }
          if (!paramFragment.mFromLayout)
          {
            localObject1 = null;
            if (paramFragment.mContainerId != 0)
            {
              localObject2 = (ViewGroup)this.mContainer.onFindViewById(paramFragment.mContainerId);
              localObject1 = localObject2;
              if (localObject2 == null)
              {
                localObject1 = localObject2;
                if (!paramFragment.mRestored)
                {
                  throwException(new IllegalArgumentException("No view found for id 0x" + Integer.toHexString(paramFragment.mContainerId) + " (" + paramFragment.getResources().getResourceName(paramFragment.mContainerId) + ") for fragment " + paramFragment));
                  localObject1 = localObject2;
                }
              }
            }
            paramFragment.mContainer = ((ViewGroup)localObject1);
            paramFragment.mView = paramFragment.performCreateView(paramFragment.getLayoutInflater(paramFragment.mSavedFragmentState), (ViewGroup)localObject1, paramFragment.mSavedFragmentState);
            if (paramFragment.mView == null) {
              break label1072;
            }
            paramFragment.mInnerView = paramFragment.mView;
            if (Build.VERSION.SDK_INT < 11) {
              break label1058;
            }
            ViewCompat.setSaveFromParentEnabled(paramFragment.mView, false);
            label805:
            if (localObject1 != null)
            {
              localObject2 = loadAnimation(paramFragment, paramInt2, true, paramInt3);
              if (localObject2 != null)
              {
                setHWLayerAnimListenerIfAlpha(paramFragment.mView, (Animation)localObject2);
                paramFragment.mView.startAnimation((Animation)localObject2);
              }
              ((ViewGroup)localObject1).addView(paramFragment.mView);
            }
            if (paramFragment.mHidden) {
              paramFragment.mView.setVisibility(8);
            }
            paramFragment.onViewCreated(paramFragment.mView, paramFragment.mSavedFragmentState);
          }
        }
      }
      for (;;)
      {
        paramFragment.performActivityCreated(paramFragment.mSavedFragmentState);
        if (paramFragment.mView != null) {
          paramFragment.restoreViewState(paramFragment.mSavedFragmentState);
        }
        paramFragment.mSavedFragmentState = null;
        k = i;
        j = k;
        if (k > 3)
        {
          if (DEBUG) {
            Log.v("FragmentManager", "moveto STARTED: " + paramFragment);
          }
          paramFragment.performStart();
          j = k;
        }
        i = j;
        if (j <= 4) {
          break;
        }
        if (DEBUG) {
          Log.v("FragmentManager", "moveto RESUMED: " + paramFragment);
        }
        paramFragment.mResumed = true;
        paramFragment.performResume();
        paramFragment.mSavedFragmentState = null;
        paramFragment.mSavedViewState = null;
        i = j;
        break;
        paramFragment.mView = NoSaveStateFrameLayout.wrap(paramFragment.mView);
        break label548;
        label1046:
        paramFragment.mInnerView = null;
        i = j;
        break label580;
        label1058:
        paramFragment.mView = NoSaveStateFrameLayout.wrap(paramFragment.mView);
        break label805;
        label1072:
        paramFragment.mInnerView = null;
      }
      i = paramInt1;
      if (paramFragment.mState > paramInt1) {
        switch (paramFragment.mState)
        {
        default: 
          i = paramInt1;
          break;
        case 1: 
        case 5: 
        case 4: 
        case 3: 
        case 2: 
          for (;;)
          {
            i = paramInt1;
            if (paramInt1 >= 1) {
              break;
            }
            if ((this.mDestroyed) && (paramFragment.mAnimatingAway != null))
            {
              localObject1 = paramFragment.mAnimatingAway;
              paramFragment.mAnimatingAway = null;
              ((View)localObject1).clearAnimation();
            }
            if (paramFragment.mAnimatingAway == null) {
              break label1515;
            }
            paramFragment.mStateAfterAnimating = paramInt1;
            i = 1;
            break;
            if (paramInt1 < 5)
            {
              if (DEBUG) {
                Log.v("FragmentManager", "movefrom RESUMED: " + paramFragment);
              }
              paramFragment.performPause();
              paramFragment.mResumed = false;
            }
            if (paramInt1 < 4)
            {
              if (DEBUG) {
                Log.v("FragmentManager", "movefrom STARTED: " + paramFragment);
              }
              paramFragment.performStop();
            }
            if (paramInt1 < 3)
            {
              if (DEBUG) {
                Log.v("FragmentManager", "movefrom STOPPED: " + paramFragment);
              }
              paramFragment.performReallyStop();
            }
            if (paramInt1 < 2)
            {
              if (DEBUG) {
                Log.v("FragmentManager", "movefrom ACTIVITY_CREATED: " + paramFragment);
              }
              if ((paramFragment.mView != null) && (this.mHost.onShouldSaveFragmentState(paramFragment)) && (paramFragment.mSavedViewState == null)) {
                saveFragmentViewState(paramFragment);
              }
              paramFragment.performDestroyView();
              if ((paramFragment.mView != null) && (paramFragment.mContainer != null))
              {
                localObject2 = null;
                localObject1 = localObject2;
                if (this.mCurState > 0)
                {
                  localObject1 = localObject2;
                  if (!this.mDestroyed) {
                    localObject1 = loadAnimation(paramFragment, paramInt2, false, paramInt3);
                  }
                }
                if (localObject1 != null)
                {
                  paramFragment.mAnimatingAway = paramFragment.mView;
                  paramFragment.mStateAfterAnimating = paramInt1;
                  ((Animation)localObject1).setAnimationListener(new AnimateOnHWLayerIfNeededListener(paramFragment.mView, (Animation)localObject1)
                  {
                    public void onAnimationEnd(Animation paramAnonymousAnimation)
                    {
                      super.onAnimationEnd(paramAnonymousAnimation);
                      if (paramFragment.mAnimatingAway != null)
                      {
                        paramFragment.mAnimatingAway = null;
                        FragmentManagerImpl.this.moveToState(paramFragment, paramFragment.mStateAfterAnimating, 0, 0, false);
                      }
                    }
                  });
                  paramFragment.mView.startAnimation((Animation)localObject1);
                }
                paramFragment.mContainer.removeView(paramFragment.mView);
              }
              paramFragment.mContainer = null;
              paramFragment.mView = null;
              paramFragment.mInnerView = null;
            }
          }
          label1515:
          if (DEBUG) {
            Log.v("FragmentManager", "movefrom CREATED: " + paramFragment);
          }
          if (!paramFragment.mRetaining) {
            paramFragment.performDestroy();
          }
          paramFragment.mCalled = false;
          paramFragment.onDetach();
          if (!paramFragment.mCalled) {
            throw new SuperNotCalledException("Fragment " + paramFragment + " did not call through to super.onDetach()");
          }
          i = paramInt1;
          if (!paramBoolean) {
            if (!paramFragment.mRetaining)
            {
              makeInactive(paramFragment);
              i = paramInt1;
            }
            else
            {
              paramFragment.mHost = null;
              paramFragment.mParentFragment = null;
              paramFragment.mFragmentManager = null;
              paramFragment.mChildFragmentManager = null;
              i = paramInt1;
            }
          }
          break;
        }
      }
    }
  }
  
  public void noteStateNotSaved()
  {
    this.mStateSaved = false;
  }
  
  public View onCreateView(View paramView, String paramString, Context paramContext, AttributeSet paramAttributeSet)
  {
    Object localObject = null;
    if (!"fragment".equals(paramString)) {
      paramString = (String)localObject;
    }
    for (;;)
    {
      return paramString;
      paramString = paramAttributeSet.getAttributeValue(null, "class");
      TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, FragmentTag.Fragment);
      String str1 = paramString;
      if (paramString == null) {
        str1 = localTypedArray.getString(0);
      }
      int k = localTypedArray.getResourceId(1, -1);
      String str2 = localTypedArray.getString(2);
      localTypedArray.recycle();
      paramString = (String)localObject;
      if (Fragment.isSupportFragmentClass(this.mHost.getContext(), str1))
      {
        if (paramView != null) {}
        for (int i = paramView.getId(); (i == -1) && (k == -1) && (str2 == null); i = 0) {
          throw new IllegalArgumentException(paramAttributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + str1);
        }
        int j;
        if (k != -1)
        {
          paramString = findFragmentById(k);
          paramView = paramString;
          if (paramString == null)
          {
            paramView = paramString;
            if (str2 != null) {
              paramView = findFragmentByTag(str2);
            }
          }
          paramString = paramView;
          if (paramView == null)
          {
            paramString = paramView;
            if (i != -1) {
              paramString = findFragmentById(i);
            }
          }
          if (DEBUG) {
            Log.v("FragmentManager", "onCreateView: id=0x" + Integer.toHexString(k) + " fname=" + str1 + " existing=" + paramString);
          }
          if (paramString != null) {
            break label441;
          }
          paramView = Fragment.instantiate(paramContext, str1);
          paramView.mFromLayout = true;
          if (k == 0) {
            break label434;
          }
          j = k;
          label304:
          paramView.mFragmentId = j;
          paramView.mContainerId = i;
          paramView.mTag = str2;
          paramView.mInLayout = true;
          paramView.mFragmentManager = this;
          paramView.mHost = this.mHost;
          paramView.onInflate(this.mHost.getContext(), paramAttributeSet, paramView.mSavedFragmentState);
          addFragment(paramView, true);
          label363:
          if ((this.mCurState >= 1) || (!paramView.mFromLayout)) {
            break label562;
          }
          moveToState(paramView, 1, 0, 0, false);
        }
        for (;;)
        {
          if (paramView.mView != null) {
            break label570;
          }
          throw new IllegalStateException("Fragment " + str1 + " did not create a view.");
          paramString = null;
          break;
          label434:
          j = i;
          break label304;
          label441:
          if (paramString.mInLayout) {
            throw new IllegalArgumentException(paramAttributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(k) + ", tag " + str2 + ", or parent id 0x" + Integer.toHexString(i) + " with another fragment for " + str1);
          }
          paramString.mInLayout = true;
          paramView = paramString;
          if (paramString.mRetaining) {
            break label363;
          }
          paramString.onInflate(this.mHost.getContext(), paramAttributeSet, paramString.mSavedFragmentState);
          paramView = paramString;
          break label363;
          label562:
          moveToState(paramView);
        }
        label570:
        if (k != 0) {
          paramView.mView.setId(k);
        }
        if (paramView.mView.getTag() == null) {
          paramView.mView.setTag(str2);
        }
        paramString = paramView.mView;
      }
    }
  }
  
  public void performPendingDeferredStart(Fragment paramFragment)
  {
    if (paramFragment.mDeferStart)
    {
      if (!this.mExecutingActions) {
        break label20;
      }
      this.mHavePendingDeferredStart = true;
    }
    for (;;)
    {
      return;
      label20:
      paramFragment.mDeferStart = false;
      moveToState(paramFragment, this.mCurState, 0, 0, false);
    }
  }
  
  public void popBackStack()
  {
    enqueueAction(new Runnable()
    {
      public void run()
      {
        FragmentManagerImpl.this.popBackStackState(FragmentManagerImpl.this.mHost.getHandler(), null, -1, 0);
      }
    }, false);
  }
  
  public void popBackStack(final int paramInt1, final int paramInt2)
  {
    if (paramInt1 < 0) {
      throw new IllegalArgumentException("Bad id: " + paramInt1);
    }
    enqueueAction(new Runnable()
    {
      public void run()
      {
        FragmentManagerImpl.this.popBackStackState(FragmentManagerImpl.this.mHost.getHandler(), null, paramInt1, paramInt2);
      }
    }, false);
  }
  
  public void popBackStack(final String paramString, final int paramInt)
  {
    enqueueAction(new Runnable()
    {
      public void run()
      {
        FragmentManagerImpl.this.popBackStackState(FragmentManagerImpl.this.mHost.getHandler(), paramString, -1, paramInt);
      }
    }, false);
  }
  
  public boolean popBackStackImmediate()
  {
    checkStateLoss();
    executePendingTransactions();
    return popBackStackState(this.mHost.getHandler(), null, -1, 0);
  }
  
  public boolean popBackStackImmediate(int paramInt1, int paramInt2)
  {
    checkStateLoss();
    executePendingTransactions();
    if (paramInt1 < 0) {
      throw new IllegalArgumentException("Bad id: " + paramInt1);
    }
    return popBackStackState(this.mHost.getHandler(), null, paramInt1, paramInt2);
  }
  
  public boolean popBackStackImmediate(String paramString, int paramInt)
  {
    checkStateLoss();
    executePendingTransactions();
    return popBackStackState(this.mHost.getHandler(), paramString, -1, paramInt);
  }
  
  boolean popBackStackState(Handler paramHandler, String paramString, int paramInt1, int paramInt2)
  {
    if (this.mBackStack == null) {}
    for (boolean bool = false;; bool = false)
    {
      return bool;
      if ((paramString != null) || (paramInt1 >= 0) || ((paramInt2 & 0x1) != 0)) {
        break label104;
      }
      paramInt1 = this.mBackStack.size() - 1;
      if (paramInt1 >= 0) {
        break;
      }
    }
    paramString = (BackStackRecord)this.mBackStack.remove(paramInt1);
    SparseArray localSparseArray1 = new SparseArray();
    paramHandler = new SparseArray();
    paramString.calculateBackFragments(localSparseArray1, paramHandler);
    paramString.popFromBackStack(true, null, localSparseArray1, paramHandler);
    reportBackStackChanged();
    for (;;)
    {
      bool = true;
      break;
      label104:
      int i = -1;
      if ((paramString != null) || (paramInt1 >= 0))
      {
        for (int j = this.mBackStack.size() - 1;; j--)
        {
          if (j >= 0)
          {
            paramHandler = (BackStackRecord)this.mBackStack.get(j);
            if ((paramString == null) || (!paramString.equals(paramHandler.getName()))) {
              break label170;
            }
          }
          label170:
          while ((paramInt1 >= 0) && (paramInt1 == paramHandler.mIndex))
          {
            if (j >= 0) {
              break label188;
            }
            bool = false;
            break;
          }
        }
        label188:
        i = j;
        if ((paramInt2 & 0x1) != 0) {
          for (paramInt2 = j - 1;; paramInt2--)
          {
            i = paramInt2;
            if (paramInt2 < 0) {
              break;
            }
            paramHandler = (BackStackRecord)this.mBackStack.get(paramInt2);
            if ((paramString == null) || (!paramString.equals(paramHandler.getName())))
            {
              i = paramInt2;
              if (paramInt1 < 0) {
                break;
              }
              i = paramInt2;
              if (paramInt1 != paramHandler.mIndex) {
                break;
              }
            }
          }
        }
      }
      if (i == this.mBackStack.size() - 1)
      {
        bool = false;
        break;
      }
      paramString = new ArrayList();
      for (paramInt1 = this.mBackStack.size() - 1; paramInt1 > i; paramInt1--) {
        paramString.add(this.mBackStack.remove(paramInt1));
      }
      paramInt2 = paramString.size() - 1;
      SparseArray localSparseArray2 = new SparseArray();
      localSparseArray1 = new SparseArray();
      for (paramInt1 = 0; paramInt1 <= paramInt2; paramInt1++) {
        ((BackStackRecord)paramString.get(paramInt1)).calculateBackFragments(localSparseArray2, localSparseArray1);
      }
      paramHandler = null;
      paramInt1 = 0;
      if (paramInt1 <= paramInt2)
      {
        if (DEBUG) {
          Log.v("FragmentManager", "Popping back stack state: " + paramString.get(paramInt1));
        }
        BackStackRecord localBackStackRecord = (BackStackRecord)paramString.get(paramInt1);
        if (paramInt1 == paramInt2) {}
        for (bool = true;; bool = false)
        {
          paramHandler = localBackStackRecord.popFromBackStack(bool, paramHandler, localSparseArray2, localSparseArray1);
          paramInt1++;
          break;
        }
      }
      reportBackStackChanged();
    }
  }
  
  public void putFragment(Bundle paramBundle, String paramString, Fragment paramFragment)
  {
    if (paramFragment.mIndex < 0) {
      throwException(new IllegalStateException("Fragment " + paramFragment + " is not currently in the FragmentManager"));
    }
    paramBundle.putInt(paramString, paramFragment.mIndex);
  }
  
  public void removeFragment(Fragment paramFragment, int paramInt1, int paramInt2)
  {
    if (DEBUG) {
      Log.v("FragmentManager", "remove: " + paramFragment + " nesting=" + paramFragment.mBackStackNesting);
    }
    if (!paramFragment.isInBackStack())
    {
      i = 1;
      if ((!paramFragment.mDetached) || (i != 0))
      {
        if (this.mAdded != null) {
          this.mAdded.remove(paramFragment);
        }
        if ((paramFragment.mHasMenu) && (paramFragment.mMenuVisible)) {
          this.mNeedMenuInvalidate = true;
        }
        paramFragment.mAdded = false;
        paramFragment.mRemoving = true;
        if (i == 0) {
          break label137;
        }
      }
    }
    label137:
    for (int i = 0;; i = 1)
    {
      moveToState(paramFragment, i, paramInt1, paramInt2, false);
      return;
      i = 0;
      break;
    }
  }
  
  public void removeOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener paramOnBackStackChangedListener)
  {
    if (this.mBackStackChangeListeners != null) {
      this.mBackStackChangeListeners.remove(paramOnBackStackChangedListener);
    }
  }
  
  void reportBackStackChanged()
  {
    if (this.mBackStackChangeListeners != null) {
      for (int i = 0; i < this.mBackStackChangeListeners.size(); i++) {
        ((FragmentManager.OnBackStackChangedListener)this.mBackStackChangeListeners.get(i)).onBackStackChanged();
      }
    }
  }
  
  void restoreAllState(Parcelable paramParcelable, List<Fragment> paramList)
  {
    if (paramParcelable == null) {}
    for (;;)
    {
      return;
      paramParcelable = (FragmentManagerState)paramParcelable;
      if (paramParcelable.mActive != null)
      {
        Fragment localFragment;
        Object localObject;
        if (paramList != null) {
          for (i = 0; i < paramList.size(); i++)
          {
            localFragment = (Fragment)paramList.get(i);
            if (DEBUG) {
              Log.v("FragmentManager", "restoreAllState: re-attaching retained " + localFragment);
            }
            localObject = paramParcelable.mActive[localFragment.mIndex];
            ((FragmentState)localObject).mInstance = localFragment;
            localFragment.mSavedViewState = null;
            localFragment.mBackStackNesting = 0;
            localFragment.mInLayout = false;
            localFragment.mAdded = false;
            localFragment.mTarget = null;
            if (((FragmentState)localObject).mSavedFragmentState != null)
            {
              ((FragmentState)localObject).mSavedFragmentState.setClassLoader(this.mHost.getContext().getClassLoader());
              localFragment.mSavedViewState = ((FragmentState)localObject).mSavedFragmentState.getSparseParcelableArray("android:view_state");
              localFragment.mSavedFragmentState = ((FragmentState)localObject).mSavedFragmentState;
            }
          }
        }
        this.mActive = new ArrayList(paramParcelable.mActive.length);
        if (this.mAvailIndices != null) {
          this.mAvailIndices.clear();
        }
        int i = 0;
        if (i < paramParcelable.mActive.length)
        {
          localObject = paramParcelable.mActive[i];
          if (localObject != null)
          {
            localFragment = ((FragmentState)localObject).instantiate(this.mHost, this.mParent);
            if (DEBUG) {
              Log.v("FragmentManager", "restoreAllState: active #" + i + ": " + localFragment);
            }
            this.mActive.add(localFragment);
            ((FragmentState)localObject).mInstance = null;
          }
          for (;;)
          {
            i++;
            break;
            this.mActive.add(null);
            if (this.mAvailIndices == null) {
              this.mAvailIndices = new ArrayList();
            }
            if (DEBUG) {
              Log.v("FragmentManager", "restoreAllState: avail #" + i);
            }
            this.mAvailIndices.add(Integer.valueOf(i));
          }
        }
        if (paramList != null)
        {
          i = 0;
          if (i < paramList.size())
          {
            localObject = (Fragment)paramList.get(i);
            if (((Fragment)localObject).mTargetIndex >= 0) {
              if (((Fragment)localObject).mTargetIndex >= this.mActive.size()) {
                break label469;
              }
            }
            for (((Fragment)localObject).mTarget = ((Fragment)this.mActive.get(((Fragment)localObject).mTargetIndex));; ((Fragment)localObject).mTarget = null)
            {
              i++;
              break;
              label469:
              Log.w("FragmentManager", "Re-attaching retained fragment " + localObject + " target no longer exists: " + ((Fragment)localObject).mTargetIndex);
            }
          }
        }
        if (paramParcelable.mAdded != null)
        {
          this.mAdded = new ArrayList(paramParcelable.mAdded.length);
          for (i = 0; i < paramParcelable.mAdded.length; i++)
          {
            paramList = (Fragment)this.mActive.get(paramParcelable.mAdded[i]);
            if (paramList == null) {
              throwException(new IllegalStateException("No instantiated fragment for index #" + paramParcelable.mAdded[i]));
            }
            paramList.mAdded = true;
            if (DEBUG) {
              Log.v("FragmentManager", "restoreAllState: added #" + i + ": " + paramList);
            }
            if (this.mAdded.contains(paramList)) {
              throw new IllegalStateException("Already added!");
            }
            this.mAdded.add(paramList);
          }
        }
        this.mAdded = null;
        if (paramParcelable.mBackStack != null)
        {
          this.mBackStack = new ArrayList(paramParcelable.mBackStack.length);
          for (i = 0; i < paramParcelable.mBackStack.length; i++)
          {
            paramList = paramParcelable.mBackStack[i].instantiate(this);
            if (DEBUG)
            {
              Log.v("FragmentManager", "restoreAllState: back stack #" + i + " (index " + paramList.mIndex + "): " + paramList);
              paramList.dump("  ", new PrintWriter(new LogWriter("FragmentManager")), false);
            }
            this.mBackStack.add(paramList);
            if (paramList.mIndex >= 0) {
              setBackStackIndex(paramList.mIndex, paramList);
            }
          }
        }
        else
        {
          this.mBackStack = null;
        }
      }
    }
  }
  
  ArrayList<Fragment> retainNonConfig()
  {
    Object localObject2 = null;
    Object localObject1 = null;
    if (this.mActive != null)
    {
      int i = 0;
      localObject2 = localObject1;
      if (i < this.mActive.size())
      {
        Fragment localFragment = (Fragment)this.mActive.get(i);
        Object localObject3 = localObject1;
        if (localFragment != null)
        {
          localObject3 = localObject1;
          if (localFragment.mRetainInstance)
          {
            localObject2 = localObject1;
            if (localObject1 == null) {
              localObject2 = new ArrayList();
            }
            ((ArrayList)localObject2).add(localFragment);
            localFragment.mRetaining = true;
            if (localFragment.mTarget == null) {
              break label163;
            }
          }
        }
        label163:
        for (int j = localFragment.mTarget.mIndex;; j = -1)
        {
          localFragment.mTargetIndex = j;
          localObject3 = localObject2;
          if (DEBUG)
          {
            Log.v("FragmentManager", "retainNonConfig: keeping retained " + localFragment);
            localObject3 = localObject2;
          }
          i++;
          localObject1 = localObject3;
          break;
        }
      }
    }
    return (ArrayList<Fragment>)localObject2;
  }
  
  Parcelable saveAllState()
  {
    Object localObject2 = null;
    execPendingActions();
    if (HONEYCOMB) {
      this.mStateSaved = true;
    }
    Object localObject1 = localObject2;
    if (this.mActive != null)
    {
      if (this.mActive.size() > 0) {
        break label47;
      }
      localObject1 = localObject2;
    }
    for (;;)
    {
      return (Parcelable)localObject1;
      label47:
      int m = this.mActive.size();
      FragmentState[] arrayOfFragmentState = new FragmentState[m];
      int j = 0;
      int i = 0;
      Object localObject3;
      if (i < m)
      {
        localObject3 = (Fragment)this.mActive.get(i);
        int k;
        if (localObject3 != null)
        {
          if (((Fragment)localObject3).mIndex < 0) {
            throwException(new IllegalStateException("Failure saving state: active " + localObject3 + " has cleared index: " + ((Fragment)localObject3).mIndex));
          }
          k = 1;
          localObject1 = new FragmentState((Fragment)localObject3);
          arrayOfFragmentState[i] = localObject1;
          if ((((Fragment)localObject3).mState <= 0) || (((FragmentState)localObject1).mSavedFragmentState != null)) {
            break label372;
          }
          ((FragmentState)localObject1).mSavedFragmentState = saveFragmentBasicState((Fragment)localObject3);
          if (((Fragment)localObject3).mTarget != null)
          {
            if (((Fragment)localObject3).mTarget.mIndex < 0) {
              throwException(new IllegalStateException("Failure saving state: " + localObject3 + " has target not in fragment manager: " + ((Fragment)localObject3).mTarget));
            }
            if (((FragmentState)localObject1).mSavedFragmentState == null) {
              ((FragmentState)localObject1).mSavedFragmentState = new Bundle();
            }
            putFragment(((FragmentState)localObject1).mSavedFragmentState, "android:target_state", ((Fragment)localObject3).mTarget);
            if (((Fragment)localObject3).mTargetRequestCode != 0) {
              ((FragmentState)localObject1).mSavedFragmentState.putInt("android:target_req_state", ((Fragment)localObject3).mTargetRequestCode);
            }
          }
        }
        for (;;)
        {
          j = k;
          if (DEBUG)
          {
            Log.v("FragmentManager", "Saved state of " + localObject3 + ": " + ((FragmentState)localObject1).mSavedFragmentState);
            j = k;
          }
          i++;
          break;
          label372:
          ((FragmentState)localObject1).mSavedFragmentState = ((Fragment)localObject3).mSavedFragmentState;
        }
      }
      if (j == 0)
      {
        localObject1 = localObject2;
        if (DEBUG)
        {
          Log.v("FragmentManager", "saveAllState: no fragments!");
          localObject1 = localObject2;
        }
      }
      else
      {
        localObject2 = null;
        localObject3 = null;
        localObject1 = localObject2;
        if (this.mAdded != null)
        {
          j = this.mAdded.size();
          localObject1 = localObject2;
          if (j > 0)
          {
            localObject2 = new int[j];
            for (i = 0;; i++)
            {
              localObject1 = localObject2;
              if (i >= j) {
                break;
              }
              localObject2[i] = ((Fragment)this.mAdded.get(i)).mIndex;
              if (localObject2[i] < 0) {
                throwException(new IllegalStateException("Failure saving state: active " + this.mAdded.get(i) + " has cleared index: " + localObject2[i]));
              }
              if (DEBUG) {
                Log.v("FragmentManager", "saveAllState: adding fragment #" + i + ": " + this.mAdded.get(i));
              }
            }
          }
        }
        localObject2 = localObject3;
        if (this.mBackStack != null)
        {
          j = this.mBackStack.size();
          localObject2 = localObject3;
          if (j > 0)
          {
            localObject3 = new BackStackState[j];
            for (i = 0;; i++)
            {
              localObject2 = localObject3;
              if (i >= j) {
                break;
              }
              localObject3[i] = new BackStackState((BackStackRecord)this.mBackStack.get(i));
              if (DEBUG) {
                Log.v("FragmentManager", "saveAllState: adding back stack #" + i + ": " + this.mBackStack.get(i));
              }
            }
          }
        }
        localObject3 = new FragmentManagerState();
        ((FragmentManagerState)localObject3).mActive = arrayOfFragmentState;
        ((FragmentManagerState)localObject3).mAdded = ((int[])localObject1);
        ((FragmentManagerState)localObject3).mBackStack = ((BackStackState[])localObject2);
        localObject1 = localObject3;
      }
    }
  }
  
  Bundle saveFragmentBasicState(Fragment paramFragment)
  {
    Object localObject2 = null;
    if (this.mStateBundle == null) {
      this.mStateBundle = new Bundle();
    }
    paramFragment.performSaveInstanceState(this.mStateBundle);
    if (!this.mStateBundle.isEmpty())
    {
      localObject2 = this.mStateBundle;
      this.mStateBundle = null;
    }
    if (paramFragment.mView != null) {
      saveFragmentViewState(paramFragment);
    }
    Object localObject1 = localObject2;
    if (paramFragment.mSavedViewState != null)
    {
      localObject1 = localObject2;
      if (localObject2 == null) {
        localObject1 = new Bundle();
      }
      ((Bundle)localObject1).putSparseParcelableArray("android:view_state", paramFragment.mSavedViewState);
    }
    localObject2 = localObject1;
    if (!paramFragment.mUserVisibleHint)
    {
      localObject2 = localObject1;
      if (localObject1 == null) {
        localObject2 = new Bundle();
      }
      ((Bundle)localObject2).putBoolean("android:user_visible_hint", paramFragment.mUserVisibleHint);
    }
    return (Bundle)localObject2;
  }
  
  public Fragment.SavedState saveFragmentInstanceState(Fragment paramFragment)
  {
    Object localObject2 = null;
    if (paramFragment.mIndex < 0) {
      throwException(new IllegalStateException("Fragment " + paramFragment + " is not currently in the FragmentManager"));
    }
    Object localObject1 = localObject2;
    if (paramFragment.mState > 0)
    {
      paramFragment = saveFragmentBasicState(paramFragment);
      localObject1 = localObject2;
      if (paramFragment != null) {
        localObject1 = new Fragment.SavedState(paramFragment);
      }
    }
    return (Fragment.SavedState)localObject1;
  }
  
  void saveFragmentViewState(Fragment paramFragment)
  {
    if (paramFragment.mInnerView == null) {
      return;
    }
    if (this.mStateArray == null) {
      this.mStateArray = new SparseArray();
    }
    for (;;)
    {
      paramFragment.mInnerView.saveHierarchyState(this.mStateArray);
      if (this.mStateArray.size() <= 0) {
        break;
      }
      paramFragment.mSavedViewState = this.mStateArray;
      this.mStateArray = null;
      break;
      this.mStateArray.clear();
    }
  }
  
  /* Error */
  public void setBackStackIndex(int paramInt, BackStackRecord paramBackStackRecord)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 399	android/support/v4/app/FragmentManagerImpl:mBackStackIndices	Ljava/util/ArrayList;
    //   6: ifnonnull +19 -> 25
    //   9: new 338	java/util/ArrayList
    //   12: astore 5
    //   14: aload 5
    //   16: invokespecial 339	java/util/ArrayList:<init>	()V
    //   19: aload_0
    //   20: aload 5
    //   22: putfield 399	android/support/v4/app/FragmentManagerImpl:mBackStackIndices	Ljava/util/ArrayList;
    //   25: aload_0
    //   26: getfield 399	android/support/v4/app/FragmentManagerImpl:mBackStackIndices	Ljava/util/ArrayList;
    //   29: invokevirtual 397	java/util/ArrayList:size	()I
    //   32: istore 4
    //   34: iload 4
    //   36: istore_3
    //   37: iload_1
    //   38: iload 4
    //   40: if_icmpge +63 -> 103
    //   43: getstatic 111	android/support/v4/app/FragmentManagerImpl:DEBUG	Z
    //   46: ifeq +44 -> 90
    //   49: new 167	java/lang/StringBuilder
    //   52: astore 5
    //   54: aload 5
    //   56: invokespecial 168	java/lang/StringBuilder:<init>	()V
    //   59: ldc 54
    //   61: aload 5
    //   63: ldc_w 401
    //   66: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   69: iload_1
    //   70: invokevirtual 404	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   73: ldc_w 406
    //   76: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   79: aload_2
    //   80: invokevirtual 355	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   83: invokevirtual 178	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   86: invokestatic 358	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   89: pop
    //   90: aload_0
    //   91: getfield 399	android/support/v4/app/FragmentManagerImpl:mBackStackIndices	Ljava/util/ArrayList;
    //   94: iload_1
    //   95: aload_2
    //   96: invokevirtual 422	java/util/ArrayList:set	(ILjava/lang/Object;)Ljava/lang/Object;
    //   99: pop
    //   100: aload_0
    //   101: monitorexit
    //   102: return
    //   103: iload_3
    //   104: iload_1
    //   105: if_icmpge +90 -> 195
    //   108: aload_0
    //   109: getfield 399	android/support/v4/app/FragmentManagerImpl:mBackStackIndices	Ljava/util/ArrayList;
    //   112: aconst_null
    //   113: invokevirtual 343	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   116: pop
    //   117: aload_0
    //   118: getfield 396	android/support/v4/app/FragmentManagerImpl:mAvailBackStackIndices	Ljava/util/ArrayList;
    //   121: ifnonnull +19 -> 140
    //   124: new 338	java/util/ArrayList
    //   127: astore 5
    //   129: aload 5
    //   131: invokespecial 339	java/util/ArrayList:<init>	()V
    //   134: aload_0
    //   135: aload 5
    //   137: putfield 396	android/support/v4/app/FragmentManagerImpl:mAvailBackStackIndices	Ljava/util/ArrayList;
    //   140: getstatic 111	android/support/v4/app/FragmentManagerImpl:DEBUG	Z
    //   143: ifeq +34 -> 177
    //   146: new 167	java/lang/StringBuilder
    //   149: astore 5
    //   151: aload 5
    //   153: invokespecial 168	java/lang/StringBuilder:<init>	()V
    //   156: ldc 54
    //   158: aload 5
    //   160: ldc_w 1300
    //   163: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   166: iload_3
    //   167: invokevirtual 404	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   170: invokevirtual 178	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   173: invokestatic 358	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   176: pop
    //   177: aload_0
    //   178: getfield 396	android/support/v4/app/FragmentManagerImpl:mAvailBackStackIndices	Ljava/util/ArrayList;
    //   181: iload_3
    //   182: invokestatic 681	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   185: invokevirtual 343	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   188: pop
    //   189: iinc 3 1
    //   192: goto -89 -> 103
    //   195: getstatic 111	android/support/v4/app/FragmentManagerImpl:DEBUG	Z
    //   198: ifeq +44 -> 242
    //   201: new 167	java/lang/StringBuilder
    //   204: astore 5
    //   206: aload 5
    //   208: invokespecial 168	java/lang/StringBuilder:<init>	()V
    //   211: ldc 54
    //   213: aload 5
    //   215: ldc_w 416
    //   218: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   221: iload_1
    //   222: invokevirtual 404	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   225: ldc_w 418
    //   228: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   231: aload_2
    //   232: invokevirtual 355	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   235: invokevirtual 178	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   238: invokestatic 358	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   241: pop
    //   242: aload_0
    //   243: getfield 399	android/support/v4/app/FragmentManagerImpl:mBackStackIndices	Ljava/util/ArrayList;
    //   246: aload_2
    //   247: invokevirtual 343	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   250: pop
    //   251: goto -151 -> 100
    //   254: astore_2
    //   255: aload_0
    //   256: monitorexit
    //   257: aload_2
    //   258: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	259	0	this	FragmentManagerImpl
    //   0	259	1	paramInt	int
    //   0	259	2	paramBackStackRecord	BackStackRecord
    //   36	154	3	i	int
    //   32	9	4	j	int
    //   12	202	5	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	25	254	finally
    //   25	34	254	finally
    //   43	90	254	finally
    //   90	100	254	finally
    //   100	102	254	finally
    //   108	140	254	finally
    //   140	177	254	finally
    //   177	189	254	finally
    //   195	242	254	finally
    //   242	251	254	finally
    //   255	257	254	finally
  }
  
  void setRetainLoader(boolean paramBoolean)
  {
    if (this.mActive != null) {
      for (int i = 0; i < this.mActive.size(); i++)
      {
        Fragment localFragment = (Fragment)this.mActive.get(i);
        if (localFragment != null) {
          localFragment.mRetainLoader = paramBoolean;
        }
      }
    }
  }
  
  public void showFragment(Fragment paramFragment, int paramInt1, int paramInt2)
  {
    if (DEBUG) {
      Log.v("FragmentManager", "show: " + paramFragment);
    }
    if (paramFragment.mHidden)
    {
      paramFragment.mHidden = false;
      if (paramFragment.mView != null)
      {
        Animation localAnimation = loadAnimation(paramFragment, paramInt1, true, paramInt2);
        if (localAnimation != null)
        {
          setHWLayerAnimListenerIfAlpha(paramFragment.mView, localAnimation);
          paramFragment.mView.startAnimation(localAnimation);
        }
        paramFragment.mView.setVisibility(0);
      }
      if ((paramFragment.mAdded) && (paramFragment.mHasMenu) && (paramFragment.mMenuVisible)) {
        this.mNeedMenuInvalidate = true;
      }
      paramFragment.onHiddenChanged(false);
    }
  }
  
  void startPendingDeferredFragments()
  {
    if (this.mActive == null) {}
    for (;;)
    {
      return;
      for (int i = 0; i < this.mActive.size(); i++)
      {
        Fragment localFragment = (Fragment)this.mActive.get(i);
        if (localFragment != null) {
          performPendingDeferredStart(localFragment);
        }
      }
    }
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(128);
    localStringBuilder.append("FragmentManager{");
    localStringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    localStringBuilder.append(" in ");
    if (this.mParent != null) {
      DebugUtils.buildShortClassTag(this.mParent, localStringBuilder);
    }
    for (;;)
    {
      localStringBuilder.append("}}");
      return localStringBuilder.toString();
      DebugUtils.buildShortClassTag(this.mHost, localStringBuilder);
    }
  }
  
  static class AnimateOnHWLayerIfNeededListener
    implements Animation.AnimationListener
  {
    private Animation.AnimationListener mOrignalListener = null;
    private boolean mShouldRunOnHWLayer = false;
    private View mView = null;
    
    public AnimateOnHWLayerIfNeededListener(View paramView, Animation paramAnimation)
    {
      if ((paramView == null) || (paramAnimation == null)) {}
      for (;;)
      {
        return;
        this.mView = paramView;
      }
    }
    
    public AnimateOnHWLayerIfNeededListener(View paramView, Animation paramAnimation, Animation.AnimationListener paramAnimationListener)
    {
      if ((paramView == null) || (paramAnimation == null)) {}
      for (;;)
      {
        return;
        this.mOrignalListener = paramAnimationListener;
        this.mView = paramView;
      }
    }
    
    @CallSuper
    public void onAnimationEnd(Animation paramAnimation)
    {
      if ((this.mView != null) && (this.mShouldRunOnHWLayer)) {
        this.mView.post(new Runnable()
        {
          public void run()
          {
            ViewCompat.setLayerType(FragmentManagerImpl.AnimateOnHWLayerIfNeededListener.this.mView, 0, null);
          }
        });
      }
      if (this.mOrignalListener != null) {
        this.mOrignalListener.onAnimationEnd(paramAnimation);
      }
    }
    
    public void onAnimationRepeat(Animation paramAnimation)
    {
      if (this.mOrignalListener != null) {
        this.mOrignalListener.onAnimationRepeat(paramAnimation);
      }
    }
    
    @CallSuper
    public void onAnimationStart(Animation paramAnimation)
    {
      if (this.mView != null)
      {
        this.mShouldRunOnHWLayer = FragmentManagerImpl.shouldRunOnHWLayer(this.mView, paramAnimation);
        if (this.mShouldRunOnHWLayer) {
          this.mView.post(new Runnable()
          {
            public void run()
            {
              ViewCompat.setLayerType(FragmentManagerImpl.AnimateOnHWLayerIfNeededListener.this.mView, 2, null);
            }
          });
        }
      }
      if (this.mOrignalListener != null) {
        this.mOrignalListener.onAnimationStart(paramAnimation);
      }
    }
  }
  
  static class FragmentTag
  {
    public static final int[] Fragment = { 16842755, 16842960, 16842961 };
    public static final int Fragment_id = 1;
    public static final int Fragment_name = 0;
    public static final int Fragment_tag = 2;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\android\support\v4\app\FragmentManagerImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */