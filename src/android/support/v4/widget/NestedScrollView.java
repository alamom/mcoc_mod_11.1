package android.support.v4.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ScrollingView;
import android.support.v4.view.VelocityTrackerCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ScrollView;
import java.util.ArrayList;
import java.util.List;

public class NestedScrollView
  extends FrameLayout
  implements NestedScrollingParent, NestedScrollingChild, ScrollingView
{
  private static final AccessibilityDelegate ACCESSIBILITY_DELEGATE = new AccessibilityDelegate();
  static final int ANIMATED_SCROLL_GAP = 250;
  private static final int INVALID_POINTER = -1;
  static final float MAX_SCROLL_FACTOR = 0.5F;
  private static final int[] SCROLLVIEW_STYLEABLE = { 16843130 };
  private static final String TAG = "NestedScrollView";
  private int mActivePointerId = -1;
  private final NestedScrollingChildHelper mChildHelper;
  private View mChildToScrollTo = null;
  private EdgeEffectCompat mEdgeGlowBottom;
  private EdgeEffectCompat mEdgeGlowTop;
  private boolean mFillViewport;
  private boolean mIsBeingDragged = false;
  private boolean mIsLaidOut = false;
  private boolean mIsLayoutDirty = true;
  private int mLastMotionY;
  private long mLastScroll;
  private int mMaximumVelocity;
  private int mMinimumVelocity;
  private int mNestedYOffset;
  private OnScrollChangeListener mOnScrollChangeListener;
  private final NestedScrollingParentHelper mParentHelper;
  private SavedState mSavedState;
  private final int[] mScrollConsumed = new int[2];
  private final int[] mScrollOffset = new int[2];
  private ScrollerCompat mScroller;
  private boolean mSmoothScrollingEnabled = true;
  private final Rect mTempRect = new Rect();
  private int mTouchSlop;
  private VelocityTracker mVelocityTracker;
  private float mVerticalScrollFactor;
  
  public NestedScrollView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public NestedScrollView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public NestedScrollView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initScrollView();
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, SCROLLVIEW_STYLEABLE, paramInt, 0);
    setFillViewport(paramContext.getBoolean(0, false));
    paramContext.recycle();
    this.mParentHelper = new NestedScrollingParentHelper(this);
    this.mChildHelper = new NestedScrollingChildHelper(this);
    setNestedScrollingEnabled(true);
    ViewCompat.setAccessibilityDelegate(this, ACCESSIBILITY_DELEGATE);
  }
  
  private boolean canScroll()
  {
    boolean bool2 = false;
    View localView = getChildAt(0);
    boolean bool1 = bool2;
    if (localView != null)
    {
      int i = localView.getHeight();
      bool1 = bool2;
      if (getHeight() < getPaddingTop() + i + getPaddingBottom()) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  private static int clamp(int paramInt1, int paramInt2, int paramInt3)
  {
    int i;
    if ((paramInt2 >= paramInt3) || (paramInt1 < 0)) {
      i = 0;
    }
    for (;;)
    {
      return i;
      i = paramInt1;
      if (paramInt2 + paramInt1 > paramInt3) {
        i = paramInt3 - paramInt2;
      }
    }
  }
  
  private void doScrollY(int paramInt)
  {
    if (paramInt != 0)
    {
      if (!this.mSmoothScrollingEnabled) {
        break label18;
      }
      smoothScrollBy(0, paramInt);
    }
    for (;;)
    {
      return;
      label18:
      scrollBy(0, paramInt);
    }
  }
  
  private void endDrag()
  {
    this.mIsBeingDragged = false;
    recycleVelocityTracker();
    stopNestedScroll();
    if (this.mEdgeGlowTop != null)
    {
      this.mEdgeGlowTop.onRelease();
      this.mEdgeGlowBottom.onRelease();
    }
  }
  
  private void ensureGlows()
  {
    Context localContext;
    if (ViewCompat.getOverScrollMode(this) != 2) {
      if (this.mEdgeGlowTop == null)
      {
        localContext = getContext();
        this.mEdgeGlowTop = new EdgeEffectCompat(localContext);
      }
    }
    for (this.mEdgeGlowBottom = new EdgeEffectCompat(localContext);; this.mEdgeGlowBottom = null)
    {
      return;
      this.mEdgeGlowTop = null;
    }
  }
  
  private View findFocusableViewInBounds(boolean paramBoolean, int paramInt1, int paramInt2)
  {
    ArrayList localArrayList = getFocusables(2);
    Object localObject2 = null;
    int m = 0;
    int i1 = localArrayList.size();
    int k = 0;
    if (k < i1)
    {
      View localView = (View)localArrayList.get(k);
      int i2 = localView.getTop();
      int n = localView.getBottom();
      Object localObject1 = localObject2;
      int j = m;
      int i;
      if (paramInt1 < n)
      {
        localObject1 = localObject2;
        j = m;
        if (i2 < paramInt2)
        {
          if ((paramInt1 >= i2) || (n >= paramInt2)) {
            break label130;
          }
          i = 1;
          label103:
          if (localObject2 != null) {
            break label136;
          }
          localObject1 = localView;
          j = i;
        }
      }
      for (;;)
      {
        k++;
        localObject2 = localObject1;
        m = j;
        break;
        label130:
        i = 0;
        break label103;
        label136:
        if (((paramBoolean) && (i2 < ((View)localObject2).getTop())) || ((!paramBoolean) && (n > ((View)localObject2).getBottom()))) {}
        for (n = 1;; n = 0)
        {
          if (m == 0) {
            break label215;
          }
          localObject1 = localObject2;
          j = m;
          if (i == 0) {
            break;
          }
          localObject1 = localObject2;
          j = m;
          if (n == 0) {
            break;
          }
          localObject1 = localView;
          j = m;
          break;
        }
        label215:
        if (i != 0)
        {
          localObject1 = localView;
          j = 1;
        }
        else
        {
          localObject1 = localObject2;
          j = m;
          if (n != 0)
          {
            localObject1 = localView;
            j = m;
          }
        }
      }
    }
    return (View)localObject2;
  }
  
  private void flingWithNestedDispatch(int paramInt)
  {
    int i = getScrollY();
    if (((i > 0) || (paramInt > 0)) && ((i < getScrollRange()) || (paramInt < 0))) {}
    for (boolean bool = true;; bool = false)
    {
      if (!dispatchNestedPreFling(0.0F, paramInt))
      {
        dispatchNestedFling(0.0F, paramInt, bool);
        if (bool) {
          fling(paramInt);
        }
      }
      return;
    }
  }
  
  private int getScrollRange()
  {
    int i = 0;
    if (getChildCount() > 0) {
      i = Math.max(0, getChildAt(0).getHeight() - (getHeight() - getPaddingBottom() - getPaddingTop()));
    }
    return i;
  }
  
  private float getVerticalScrollFactorCompat()
  {
    if (this.mVerticalScrollFactor == 0.0F)
    {
      TypedValue localTypedValue = new TypedValue();
      Context localContext = getContext();
      if (!localContext.getTheme().resolveAttribute(16842829, localTypedValue, true)) {
        throw new IllegalStateException("Expected theme to define listPreferredItemHeight.");
      }
      this.mVerticalScrollFactor = localTypedValue.getDimension(localContext.getResources().getDisplayMetrics());
    }
    return this.mVerticalScrollFactor;
  }
  
  private boolean inChild(int paramInt1, int paramInt2)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (getChildCount() > 0)
    {
      int i = getScrollY();
      View localView = getChildAt(0);
      bool1 = bool2;
      if (paramInt2 >= localView.getTop() - i)
      {
        bool1 = bool2;
        if (paramInt2 < localView.getBottom() - i)
        {
          bool1 = bool2;
          if (paramInt1 >= localView.getLeft())
          {
            bool1 = bool2;
            if (paramInt1 < localView.getRight()) {
              bool1 = true;
            }
          }
        }
      }
    }
    return bool1;
  }
  
  private void initOrResetVelocityTracker()
  {
    if (this.mVelocityTracker == null) {
      this.mVelocityTracker = VelocityTracker.obtain();
    }
    for (;;)
    {
      return;
      this.mVelocityTracker.clear();
    }
  }
  
  private void initScrollView()
  {
    this.mScroller = new ScrollerCompat(getContext(), null);
    setFocusable(true);
    setDescendantFocusability(262144);
    setWillNotDraw(false);
    ViewConfiguration localViewConfiguration = ViewConfiguration.get(getContext());
    this.mTouchSlop = localViewConfiguration.getScaledTouchSlop();
    this.mMinimumVelocity = localViewConfiguration.getScaledMinimumFlingVelocity();
    this.mMaximumVelocity = localViewConfiguration.getScaledMaximumFlingVelocity();
  }
  
  private void initVelocityTrackerIfNotExists()
  {
    if (this.mVelocityTracker == null) {
      this.mVelocityTracker = VelocityTracker.obtain();
    }
  }
  
  private boolean isOffScreen(View paramView)
  {
    boolean bool = false;
    if (!isWithinDeltaOfScreen(paramView, 0, getHeight())) {
      bool = true;
    }
    return bool;
  }
  
  private static boolean isViewDescendantOf(View paramView1, View paramView2)
  {
    boolean bool = true;
    if (paramView1 == paramView2) {}
    for (;;)
    {
      return bool;
      paramView1 = paramView1.getParent();
      if ((!(paramView1 instanceof ViewGroup)) || (!isViewDescendantOf((View)paramView1, paramView2))) {
        bool = false;
      }
    }
  }
  
  private boolean isWithinDeltaOfScreen(View paramView, int paramInt1, int paramInt2)
  {
    paramView.getDrawingRect(this.mTempRect);
    offsetDescendantRectToMyCoords(paramView, this.mTempRect);
    if ((this.mTempRect.bottom + paramInt1 >= getScrollY()) && (this.mTempRect.top - paramInt1 <= getScrollY() + paramInt2)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private void onSecondaryPointerUp(MotionEvent paramMotionEvent)
  {
    int i = (paramMotionEvent.getAction() & 0xFF00) >> 8;
    if (MotionEventCompat.getPointerId(paramMotionEvent, i) == this.mActivePointerId) {
      if (i != 0) {
        break label64;
      }
    }
    label64:
    for (i = 1;; i = 0)
    {
      this.mLastMotionY = ((int)MotionEventCompat.getY(paramMotionEvent, i));
      this.mActivePointerId = MotionEventCompat.getPointerId(paramMotionEvent, i);
      if (this.mVelocityTracker != null) {
        this.mVelocityTracker.clear();
      }
      return;
    }
  }
  
  private void recycleVelocityTracker()
  {
    if (this.mVelocityTracker != null)
    {
      this.mVelocityTracker.recycle();
      this.mVelocityTracker = null;
    }
  }
  
  private boolean scrollAndFocus(int paramInt1, int paramInt2, int paramInt3)
  {
    boolean bool2 = true;
    int j = getHeight();
    int i = getScrollY();
    j = i + j;
    if (paramInt1 == 33) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      View localView = findFocusableViewInBounds(bool1, paramInt2, paramInt3);
      Object localObject = localView;
      if (localView == null) {
        localObject = this;
      }
      if ((paramInt2 < i) || (paramInt3 > j)) {
        break;
      }
      bool1 = false;
      if (localObject != findFocus()) {
        ((View)localObject).requestFocus(paramInt1);
      }
      return bool1;
    }
    if (bool1) {
      paramInt2 -= i;
    }
    for (;;)
    {
      doScrollY(paramInt2);
      bool1 = bool2;
      break;
      paramInt2 = paramInt3 - j;
    }
  }
  
  private void scrollToChild(View paramView)
  {
    paramView.getDrawingRect(this.mTempRect);
    offsetDescendantRectToMyCoords(paramView, this.mTempRect);
    int i = computeScrollDeltaToGetChildRectOnScreen(this.mTempRect);
    if (i != 0) {
      scrollBy(0, i);
    }
  }
  
  private boolean scrollToChildRect(Rect paramRect, boolean paramBoolean)
  {
    int i = computeScrollDeltaToGetChildRectOnScreen(paramRect);
    boolean bool;
    if (i != 0)
    {
      bool = true;
      if (bool)
      {
        if (!paramBoolean) {
          break label37;
        }
        scrollBy(0, i);
      }
    }
    for (;;)
    {
      return bool;
      bool = false;
      break;
      label37:
      smoothScrollBy(0, i);
    }
  }
  
  public void addView(View paramView)
  {
    if (getChildCount() > 0) {
      throw new IllegalStateException("ScrollView can host only one direct child");
    }
    super.addView(paramView);
  }
  
  public void addView(View paramView, int paramInt)
  {
    if (getChildCount() > 0) {
      throw new IllegalStateException("ScrollView can host only one direct child");
    }
    super.addView(paramView, paramInt);
  }
  
  public void addView(View paramView, int paramInt, ViewGroup.LayoutParams paramLayoutParams)
  {
    if (getChildCount() > 0) {
      throw new IllegalStateException("ScrollView can host only one direct child");
    }
    super.addView(paramView, paramInt, paramLayoutParams);
  }
  
  public void addView(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    if (getChildCount() > 0) {
      throw new IllegalStateException("ScrollView can host only one direct child");
    }
    super.addView(paramView, paramLayoutParams);
  }
  
  public boolean arrowScroll(int paramInt)
  {
    boolean bool = false;
    View localView2 = findFocus();
    View localView1 = localView2;
    if (localView2 == this) {
      localView1 = null;
    }
    localView2 = FocusFinder.getInstance().findNextFocus(this, localView1, paramInt);
    int k = getMaxScrollAmount();
    if ((localView2 != null) && (isWithinDeltaOfScreen(localView2, k, getHeight())))
    {
      localView2.getDrawingRect(this.mTempRect);
      offsetDescendantRectToMyCoords(localView2, this.mTempRect);
      doScrollY(computeScrollDeltaToGetChildRectOnScreen(this.mTempRect));
      localView2.requestFocus(paramInt);
      if ((localView1 != null) && (localView1.isFocused()) && (isOffScreen(localView1)))
      {
        paramInt = getDescendantFocusability();
        setDescendantFocusability(131072);
        requestFocus();
        setDescendantFocusability(paramInt);
      }
      bool = true;
      label145:
      return bool;
    }
    int j = k;
    int i;
    if ((paramInt == 33) && (getScrollY() < j))
    {
      i = getScrollY();
      label170:
      if (i == 0) {
        break label252;
      }
      if (paramInt != 130) {
        break label254;
      }
    }
    for (;;)
    {
      doScrollY(i);
      break;
      i = j;
      if (paramInt != 130) {
        break label170;
      }
      i = j;
      if (getChildCount() <= 0) {
        break label170;
      }
      int m = getChildAt(0).getBottom();
      int n = getScrollY() + getHeight() - getPaddingBottom();
      i = j;
      if (m - n >= k) {
        break label170;
      }
      i = m - n;
      break label170;
      label252:
      break label145;
      label254:
      i = -i;
    }
  }
  
  public int computeHorizontalScrollExtent()
  {
    return super.computeHorizontalScrollExtent();
  }
  
  public int computeHorizontalScrollOffset()
  {
    return super.computeHorizontalScrollOffset();
  }
  
  public int computeHorizontalScrollRange()
  {
    return super.computeHorizontalScrollRange();
  }
  
  public void computeScroll()
  {
    int j = 1;
    int m;
    int n;
    int i1;
    int i;
    if (this.mScroller.computeScrollOffset())
    {
      int i2 = getScrollX();
      m = getScrollY();
      int k = this.mScroller.getCurrX();
      n = this.mScroller.getCurrY();
      if ((i2 != k) || (m != n))
      {
        i1 = getScrollRange();
        int i3 = ViewCompat.getOverScrollMode(this);
        i = j;
        if (i3 != 0)
        {
          if ((i3 != 1) || (i1 <= 0)) {
            break label145;
          }
          i = j;
        }
        overScrollByCompat(k - i2, n - m, i2, m, 0, i1, 0, 0, false);
        if (i != 0)
        {
          ensureGlows();
          if ((n > 0) || (m <= 0)) {
            break label150;
          }
          this.mEdgeGlowTop.onAbsorb((int)this.mScroller.getCurrVelocity());
        }
      }
    }
    for (;;)
    {
      return;
      label145:
      i = 0;
      break;
      label150:
      if ((n >= i1) && (m < i1)) {
        this.mEdgeGlowBottom.onAbsorb((int)this.mScroller.getCurrVelocity());
      }
    }
  }
  
  protected int computeScrollDeltaToGetChildRectOnScreen(Rect paramRect)
  {
    if (getChildCount() == 0) {
      i = 0;
    }
    int n;
    int j;
    int k;
    do
    {
      int m;
      do
      {
        return i;
        n = getHeight();
        j = getScrollY();
        i = j + n;
        m = getVerticalFadingEdgeLength();
        k = j;
        if (paramRect.top > 0) {
          k = j + m;
        }
        j = i;
        if (paramRect.bottom < getChildAt(0).getHeight()) {
          j = i - m;
        }
        m = 0;
        if ((paramRect.bottom > j) && (paramRect.top > k))
        {
          if (paramRect.height() > n) {}
          for (i = 0 + (paramRect.top - k);; i = 0 + (paramRect.bottom - j))
          {
            i = Math.min(i, getChildAt(0).getBottom() - j);
            break;
          }
        }
        i = m;
      } while (paramRect.top >= k);
      i = m;
    } while (paramRect.bottom >= j);
    if (paramRect.height() > n) {}
    for (int i = 0 - (j - paramRect.bottom);; i = 0 - (k - paramRect.top))
    {
      i = Math.max(i, -getScrollY());
      break;
    }
  }
  
  public int computeVerticalScrollExtent()
  {
    return super.computeVerticalScrollExtent();
  }
  
  public int computeVerticalScrollOffset()
  {
    return Math.max(0, super.computeVerticalScrollOffset());
  }
  
  public int computeVerticalScrollRange()
  {
    int j = getChildCount();
    int i = getHeight() - getPaddingBottom() - getPaddingTop();
    if (j == 0) {
      return i;
    }
    j = getChildAt(0).getBottom();
    int k = getScrollY();
    int m = Math.max(0, j - i);
    if (k < 0) {
      i = j - k;
    }
    for (;;)
    {
      break;
      i = j;
      if (k > m) {
        i = j + (k - m);
      }
    }
  }
  
  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    if ((super.dispatchKeyEvent(paramKeyEvent)) || (executeKeyEvent(paramKeyEvent))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean dispatchNestedFling(float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    return this.mChildHelper.dispatchNestedFling(paramFloat1, paramFloat2, paramBoolean);
  }
  
  public boolean dispatchNestedPreFling(float paramFloat1, float paramFloat2)
  {
    return this.mChildHelper.dispatchNestedPreFling(paramFloat1, paramFloat2);
  }
  
  public boolean dispatchNestedPreScroll(int paramInt1, int paramInt2, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    return this.mChildHelper.dispatchNestedPreScroll(paramInt1, paramInt2, paramArrayOfInt1, paramArrayOfInt2);
  }
  
  public boolean dispatchNestedScroll(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt)
  {
    return this.mChildHelper.dispatchNestedScroll(paramInt1, paramInt2, paramInt3, paramInt4, paramArrayOfInt);
  }
  
  public void draw(Canvas paramCanvas)
  {
    super.draw(paramCanvas);
    if (this.mEdgeGlowTop != null)
    {
      int i = getScrollY();
      int j;
      int m;
      int k;
      if (!this.mEdgeGlowTop.isFinished())
      {
        j = paramCanvas.save();
        m = getWidth();
        int n = getPaddingLeft();
        k = getPaddingRight();
        paramCanvas.translate(getPaddingLeft(), Math.min(0, i));
        this.mEdgeGlowTop.setSize(m - n - k, getHeight());
        if (this.mEdgeGlowTop.draw(paramCanvas)) {
          ViewCompat.postInvalidateOnAnimation(this);
        }
        paramCanvas.restoreToCount(j);
      }
      if (!this.mEdgeGlowBottom.isFinished())
      {
        j = paramCanvas.save();
        k = getWidth() - getPaddingLeft() - getPaddingRight();
        m = getHeight();
        paramCanvas.translate(-k + getPaddingLeft(), Math.max(getScrollRange(), i) + m);
        paramCanvas.rotate(180.0F, k, 0.0F);
        this.mEdgeGlowBottom.setSize(k, m);
        if (this.mEdgeGlowBottom.draw(paramCanvas)) {
          ViewCompat.postInvalidateOnAnimation(this);
        }
        paramCanvas.restoreToCount(j);
      }
    }
  }
  
  public boolean executeKeyEvent(KeyEvent paramKeyEvent)
  {
    boolean bool2 = false;
    this.mTempRect.setEmpty();
    if (!canScroll())
    {
      bool1 = bool2;
      if (isFocused())
      {
        bool1 = bool2;
        if (paramKeyEvent.getKeyCode() != 4)
        {
          View localView = findFocus();
          paramKeyEvent = localView;
          if (localView == this) {
            paramKeyEvent = null;
          }
          paramKeyEvent = FocusFinder.getInstance().findNextFocus(this, paramKeyEvent, 130);
          bool1 = bool2;
          if (paramKeyEvent != null)
          {
            bool1 = bool2;
            if (paramKeyEvent != this)
            {
              bool1 = bool2;
              if (paramKeyEvent.requestFocus(130)) {
                bool1 = true;
              }
            }
          }
        }
      }
      return bool1;
    }
    bool2 = false;
    boolean bool1 = bool2;
    if (paramKeyEvent.getAction() == 0) {}
    switch (paramKeyEvent.getKeyCode())
    {
    default: 
      bool1 = bool2;
    case 19: 
    case 20: 
      for (;;)
      {
        break;
        if (!paramKeyEvent.isAltPressed())
        {
          bool1 = arrowScroll(33);
        }
        else
        {
          bool1 = fullScroll(33);
          continue;
          if (!paramKeyEvent.isAltPressed()) {
            bool1 = arrowScroll(130);
          } else {
            bool1 = fullScroll(130);
          }
        }
      }
    }
    if (paramKeyEvent.isShiftPressed()) {}
    for (int i = 33;; i = 130)
    {
      pageScroll(i);
      bool1 = bool2;
      break;
    }
  }
  
  public void fling(int paramInt)
  {
    if (getChildCount() > 0)
    {
      int j = getHeight() - getPaddingBottom() - getPaddingTop();
      int i = getChildAt(0).getHeight();
      this.mScroller.fling(getScrollX(), getScrollY(), 0, paramInt, 0, 0, 0, Math.max(0, i - j), 0, j / 2);
      ViewCompat.postInvalidateOnAnimation(this);
    }
  }
  
  public boolean fullScroll(int paramInt)
  {
    if (paramInt == 130) {}
    for (int i = 1;; i = 0)
    {
      int j = getHeight();
      this.mTempRect.top = 0;
      this.mTempRect.bottom = j;
      if (i != 0)
      {
        i = getChildCount();
        if (i > 0)
        {
          View localView = getChildAt(i - 1);
          this.mTempRect.bottom = (localView.getBottom() + getPaddingBottom());
          this.mTempRect.top = (this.mTempRect.bottom - j);
        }
      }
      return scrollAndFocus(paramInt, this.mTempRect.top, this.mTempRect.bottom);
    }
  }
  
  protected float getBottomFadingEdgeStrength()
  {
    float f;
    if (getChildCount() == 0) {
      f = 0.0F;
    }
    for (;;)
    {
      return f;
      int i = getVerticalFadingEdgeLength();
      int j = getHeight();
      int k = getPaddingBottom();
      j = getChildAt(0).getBottom() - getScrollY() - (j - k);
      if (j < i) {
        f = j / i;
      } else {
        f = 1.0F;
      }
    }
  }
  
  public int getMaxScrollAmount()
  {
    return (int)(0.5F * getHeight());
  }
  
  public int getNestedScrollAxes()
  {
    return this.mParentHelper.getNestedScrollAxes();
  }
  
  protected float getTopFadingEdgeStrength()
  {
    float f;
    if (getChildCount() == 0) {
      f = 0.0F;
    }
    for (;;)
    {
      return f;
      int i = getVerticalFadingEdgeLength();
      int j = getScrollY();
      if (j < i) {
        f = j / i;
      } else {
        f = 1.0F;
      }
    }
  }
  
  public boolean hasNestedScrollingParent()
  {
    return this.mChildHelper.hasNestedScrollingParent();
  }
  
  public boolean isFillViewport()
  {
    return this.mFillViewport;
  }
  
  public boolean isNestedScrollingEnabled()
  {
    return this.mChildHelper.isNestedScrollingEnabled();
  }
  
  public boolean isSmoothScrollingEnabled()
  {
    return this.mSmoothScrollingEnabled;
  }
  
  protected void measureChild(View paramView, int paramInt1, int paramInt2)
  {
    ViewGroup.LayoutParams localLayoutParams = paramView.getLayoutParams();
    paramView.measure(getChildMeasureSpec(paramInt1, getPaddingLeft() + getPaddingRight(), localLayoutParams.width), View.MeasureSpec.makeMeasureSpec(0, 0));
  }
  
  protected void measureChildWithMargins(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    ViewGroup.MarginLayoutParams localMarginLayoutParams = (ViewGroup.MarginLayoutParams)paramView.getLayoutParams();
    paramView.measure(getChildMeasureSpec(paramInt1, getPaddingLeft() + getPaddingRight() + localMarginLayoutParams.leftMargin + localMarginLayoutParams.rightMargin + paramInt2, localMarginLayoutParams.width), View.MeasureSpec.makeMeasureSpec(localMarginLayoutParams.topMargin + localMarginLayoutParams.bottomMargin, 0));
  }
  
  public void onAttachedToWindow()
  {
    this.mIsLaidOut = false;
  }
  
  public boolean onGenericMotionEvent(MotionEvent paramMotionEvent)
  {
    if ((MotionEventCompat.getSource(paramMotionEvent) & 0x2) != 0) {
      switch (paramMotionEvent.getAction())
      {
      }
    }
    label126:
    for (;;)
    {
      boolean bool = false;
      return bool;
      if (!this.mIsBeingDragged)
      {
        float f = MotionEventCompat.getAxisValue(paramMotionEvent, 9);
        if (f != 0.0F)
        {
          int i = (int)(getVerticalScrollFactorCompat() * f);
          int j = getScrollRange();
          int m = getScrollY();
          int k = m - i;
          if (k < 0) {
            i = 0;
          }
          for (;;)
          {
            if (i == m) {
              break label126;
            }
            super.scrollTo(getScrollX(), i);
            bool = true;
            break;
            i = k;
            if (k > j) {
              i = j;
            }
          }
        }
      }
    }
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool1 = true;
    boolean bool2 = false;
    int i = paramMotionEvent.getAction();
    if ((i == 2) && (this.mIsBeingDragged)) {
      return bool1;
    }
    switch (i & 0xFF)
    {
    }
    for (;;)
    {
      bool1 = this.mIsBeingDragged;
      break;
      i = this.mActivePointerId;
      if (i != -1)
      {
        int j = MotionEventCompat.findPointerIndex(paramMotionEvent, i);
        if (j == -1)
        {
          Log.e("NestedScrollView", "Invalid pointerId=" + i + " in onInterceptTouchEvent");
        }
        else
        {
          i = (int)MotionEventCompat.getY(paramMotionEvent, j);
          if ((Math.abs(i - this.mLastMotionY) > this.mTouchSlop) && ((getNestedScrollAxes() & 0x2) == 0))
          {
            this.mIsBeingDragged = true;
            this.mLastMotionY = i;
            initVelocityTrackerIfNotExists();
            this.mVelocityTracker.addMovement(paramMotionEvent);
            this.mNestedYOffset = 0;
            paramMotionEvent = getParent();
            if (paramMotionEvent != null)
            {
              paramMotionEvent.requestDisallowInterceptTouchEvent(true);
              continue;
              i = (int)paramMotionEvent.getY();
              if (!inChild((int)paramMotionEvent.getX(), i))
              {
                this.mIsBeingDragged = false;
                recycleVelocityTracker();
              }
              else
              {
                this.mLastMotionY = i;
                this.mActivePointerId = MotionEventCompat.getPointerId(paramMotionEvent, 0);
                initOrResetVelocityTracker();
                this.mVelocityTracker.addMovement(paramMotionEvent);
                bool1 = bool2;
                if (!this.mScroller.isFinished()) {
                  bool1 = true;
                }
                this.mIsBeingDragged = bool1;
                startNestedScroll(2);
                continue;
                this.mIsBeingDragged = false;
                this.mActivePointerId = -1;
                recycleVelocityTracker();
                if (this.mScroller.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                  ViewCompat.postInvalidateOnAnimation(this);
                }
                stopNestedScroll();
                continue;
                onSecondaryPointerUp(paramMotionEvent);
              }
            }
          }
        }
      }
    }
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    this.mIsLayoutDirty = false;
    if ((this.mChildToScrollTo != null) && (isViewDescendantOf(this.mChildToScrollTo, this))) {
      scrollToChild(this.mChildToScrollTo);
    }
    this.mChildToScrollTo = null;
    if (!this.mIsLaidOut)
    {
      if (this.mSavedState != null)
      {
        scrollTo(getScrollX(), this.mSavedState.scrollPosition);
        this.mSavedState = null;
      }
      if (getChildCount() <= 0) {
        break label153;
      }
      paramInt1 = getChildAt(0).getMeasuredHeight();
      paramInt1 = Math.max(0, paramInt1 - (paramInt4 - paramInt2 - getPaddingBottom() - getPaddingTop()));
      if (getScrollY() <= paramInt1) {
        break label158;
      }
      scrollTo(getScrollX(), paramInt1);
    }
    for (;;)
    {
      scrollTo(getScrollX(), getScrollY());
      this.mIsLaidOut = true;
      return;
      label153:
      paramInt1 = 0;
      break;
      label158:
      if (getScrollY() < 0) {
        scrollTo(getScrollX(), 0);
      }
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    if (!this.mFillViewport) {}
    for (;;)
    {
      return;
      if ((View.MeasureSpec.getMode(paramInt2) != 0) && (getChildCount() > 0))
      {
        View localView = getChildAt(0);
        paramInt2 = getMeasuredHeight();
        if (localView.getMeasuredHeight() < paramInt2)
        {
          FrameLayout.LayoutParams localLayoutParams = (FrameLayout.LayoutParams)localView.getLayoutParams();
          localView.measure(getChildMeasureSpec(paramInt1, getPaddingLeft() + getPaddingRight(), localLayoutParams.width), View.MeasureSpec.makeMeasureSpec(paramInt2 - getPaddingTop() - getPaddingBottom(), 1073741824));
        }
      }
    }
  }
  
  public boolean onNestedFling(View paramView, float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    if (!paramBoolean) {
      flingWithNestedDispatch((int)paramFloat2);
    }
    for (paramBoolean = true;; paramBoolean = false) {
      return paramBoolean;
    }
  }
  
  public boolean onNestedPreFling(View paramView, float paramFloat1, float paramFloat2)
  {
    return false;
  }
  
  public void onNestedPreScroll(View paramView, int paramInt1, int paramInt2, int[] paramArrayOfInt) {}
  
  public void onNestedScroll(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramInt1 = getScrollY();
    scrollBy(0, paramInt4);
    paramInt1 = getScrollY() - paramInt1;
    dispatchNestedScroll(0, paramInt1, 0, paramInt4 - paramInt1, null);
  }
  
  public void onNestedScrollAccepted(View paramView1, View paramView2, int paramInt)
  {
    this.mParentHelper.onNestedScrollAccepted(paramView1, paramView2, paramInt);
    startNestedScroll(2);
  }
  
  protected void onOverScrolled(int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2)
  {
    super.scrollTo(paramInt1, paramInt2);
  }
  
  protected boolean onRequestFocusInDescendants(int paramInt, Rect paramRect)
  {
    boolean bool = false;
    int i;
    View localView;
    if (paramInt == 2)
    {
      i = 130;
      if (paramRect != null) {
        break label48;
      }
      localView = FocusFinder.getInstance().findNextFocus(this, null, i);
      label27:
      if (localView != null) {
        break label62;
      }
    }
    for (;;)
    {
      return bool;
      i = paramInt;
      if (paramInt != 1) {
        break;
      }
      i = 33;
      break;
      label48:
      localView = FocusFinder.getInstance().findNextFocusFromRect(this, paramRect, i);
      break label27;
      label62:
      if (!isOffScreen(localView)) {
        bool = localView.requestFocus(i, paramRect);
      }
    }
  }
  
  protected void onRestoreInstanceState(Parcelable paramParcelable)
  {
    paramParcelable = (SavedState)paramParcelable;
    super.onRestoreInstanceState(paramParcelable.getSuperState());
    this.mSavedState = paramParcelable;
    requestLayout();
  }
  
  protected Parcelable onSaveInstanceState()
  {
    SavedState localSavedState = new SavedState(super.onSaveInstanceState());
    localSavedState.scrollPosition = getScrollY();
    return localSavedState;
  }
  
  protected void onScrollChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onScrollChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (this.mOnScrollChangeListener != null) {
      this.mOnScrollChangeListener.onScrollChange(this, paramInt1, paramInt2, paramInt3, paramInt4);
    }
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    View localView = findFocus();
    if ((localView == null) || (this == localView)) {}
    for (;;)
    {
      return;
      if (isWithinDeltaOfScreen(localView, 0, paramInt4))
      {
        localView.getDrawingRect(this.mTempRect);
        offsetDescendantRectToMyCoords(localView, this.mTempRect);
        doScrollY(computeScrollDeltaToGetChildRectOnScreen(this.mTempRect));
      }
    }
  }
  
  public boolean onStartNestedScroll(View paramView1, View paramView2, int paramInt)
  {
    if ((paramInt & 0x2) != 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void onStopNestedScroll(View paramView)
  {
    stopNestedScroll();
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    initVelocityTrackerIfNotExists();
    MotionEvent localMotionEvent = MotionEvent.obtain(paramMotionEvent);
    int i = MotionEventCompat.getActionMasked(paramMotionEvent);
    if (i == 0) {
      this.mNestedYOffset = 0;
    }
    localMotionEvent.offsetLocation(0.0F, this.mNestedYOffset);
    switch (i)
    {
    }
    for (;;)
    {
      if (this.mVelocityTracker != null) {
        this.mVelocityTracker.addMovement(localMotionEvent);
      }
      localMotionEvent.recycle();
      for (boolean bool = true;; bool = false)
      {
        return bool;
        if (getChildCount() != 0) {
          break;
        }
      }
      if (!this.mScroller.isFinished()) {}
      ViewParent localViewParent;
      for (bool = true;; bool = false)
      {
        this.mIsBeingDragged = bool;
        if (bool)
        {
          localViewParent = getParent();
          if (localViewParent != null) {
            localViewParent.requestDisallowInterceptTouchEvent(true);
          }
        }
        if (!this.mScroller.isFinished()) {
          this.mScroller.abortAnimation();
        }
        this.mLastMotionY = ((int)paramMotionEvent.getY());
        this.mActivePointerId = MotionEventCompat.getPointerId(paramMotionEvent, 0);
        startNestedScroll(2);
        break;
      }
      int k = MotionEventCompat.findPointerIndex(paramMotionEvent, this.mActivePointerId);
      if (k == -1)
      {
        Log.e("NestedScrollView", "Invalid pointerId=" + this.mActivePointerId + " in onTouchEvent");
      }
      else
      {
        int m = (int)MotionEventCompat.getY(paramMotionEvent, k);
        i = this.mLastMotionY - m;
        int j = i;
        if (dispatchNestedPreScroll(0, i, this.mScrollConsumed, this.mScrollOffset))
        {
          j = i - this.mScrollConsumed[1];
          localMotionEvent.offsetLocation(0.0F, this.mScrollOffset[1]);
          this.mNestedYOffset += this.mScrollOffset[1];
        }
        i = j;
        if (!this.mIsBeingDragged)
        {
          i = j;
          if (Math.abs(j) > this.mTouchSlop)
          {
            localViewParent = getParent();
            if (localViewParent != null) {
              localViewParent.requestDisallowInterceptTouchEvent(true);
            }
            this.mIsBeingDragged = true;
            if (j <= 0) {
              break label558;
            }
            i = j - this.mTouchSlop;
          }
        }
        label397:
        if (this.mIsBeingDragged)
        {
          this.mLastMotionY = (m - this.mScrollOffset[1]);
          int n = getScrollY();
          m = getScrollRange();
          j = ViewCompat.getOverScrollMode(this);
          if ((j == 0) || ((j == 1) && (m > 0))) {}
          for (j = 1;; j = 0)
          {
            if ((overScrollByCompat(0, i, 0, getScrollY(), 0, m, 0, 0, true)) && (!hasNestedScrollingParent())) {
              this.mVelocityTracker.clear();
            }
            int i1 = getScrollY() - n;
            if (!dispatchNestedScroll(0, i1, 0, i - i1, this.mScrollOffset)) {
              break label573;
            }
            this.mLastMotionY -= this.mScrollOffset[1];
            localMotionEvent.offsetLocation(0.0F, this.mScrollOffset[1]);
            this.mNestedYOffset += this.mScrollOffset[1];
            break;
            label558:
            i = j + this.mTouchSlop;
            break label397;
          }
          label573:
          if (j != 0)
          {
            ensureGlows();
            j = n + i;
            if (j < 0)
            {
              this.mEdgeGlowTop.onPull(i / getHeight(), MotionEventCompat.getX(paramMotionEvent, k) / getWidth());
              if (!this.mEdgeGlowBottom.isFinished()) {
                this.mEdgeGlowBottom.onRelease();
              }
            }
            while ((this.mEdgeGlowTop != null) && ((!this.mEdgeGlowTop.isFinished()) || (!this.mEdgeGlowBottom.isFinished())))
            {
              ViewCompat.postInvalidateOnAnimation(this);
              break;
              if (j > m)
              {
                this.mEdgeGlowBottom.onPull(i / getHeight(), 1.0F - MotionEventCompat.getX(paramMotionEvent, k) / getWidth());
                if (!this.mEdgeGlowTop.isFinished()) {
                  this.mEdgeGlowTop.onRelease();
                }
              }
            }
            if (this.mIsBeingDragged)
            {
              paramMotionEvent = this.mVelocityTracker;
              paramMotionEvent.computeCurrentVelocity(1000, this.mMaximumVelocity);
              i = (int)VelocityTrackerCompat.getYVelocity(paramMotionEvent, this.mActivePointerId);
              if (Math.abs(i) <= this.mMinimumVelocity) {
                break label790;
              }
              flingWithNestedDispatch(-i);
            }
            for (;;)
            {
              this.mActivePointerId = -1;
              endDrag();
              break;
              label790:
              if (this.mScroller.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                ViewCompat.postInvalidateOnAnimation(this);
              }
            }
            if ((this.mIsBeingDragged) && (getChildCount() > 0) && (this.mScroller.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange()))) {
              ViewCompat.postInvalidateOnAnimation(this);
            }
            this.mActivePointerId = -1;
            endDrag();
            continue;
            i = MotionEventCompat.getActionIndex(paramMotionEvent);
            this.mLastMotionY = ((int)MotionEventCompat.getY(paramMotionEvent, i));
            this.mActivePointerId = MotionEventCompat.getPointerId(paramMotionEvent, i);
            continue;
            onSecondaryPointerUp(paramMotionEvent);
            this.mLastMotionY = ((int)MotionEventCompat.getY(paramMotionEvent, MotionEventCompat.findPointerIndex(paramMotionEvent, this.mActivePointerId)));
          }
        }
      }
    }
  }
  
  boolean overScrollByCompat(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, boolean paramBoolean)
  {
    int k = ViewCompat.getOverScrollMode(this);
    int i;
    int j;
    label34:
    label53:
    label72:
    label131:
    boolean bool;
    if (computeHorizontalScrollRange() > computeHorizontalScrollExtent())
    {
      i = 1;
      if (computeVerticalScrollRange() <= computeVerticalScrollExtent()) {
        break label201;
      }
      j = 1;
      if ((k != 0) && ((k != 1) || (i == 0))) {
        break label207;
      }
      i = 1;
      if ((k != 0) && ((k != 1) || (j == 0))) {
        break label213;
      }
      j = 1;
      paramInt3 += paramInt1;
      if (i == 0) {
        paramInt7 = 0;
      }
      paramInt4 += paramInt2;
      if (j == 0) {
        paramInt8 = 0;
      }
      paramInt2 = -paramInt7;
      paramInt1 = paramInt7 + paramInt5;
      paramInt5 = -paramInt8;
      paramInt6 = paramInt8 + paramInt6;
      paramBoolean = false;
      if (paramInt3 <= paramInt1) {
        break label219;
      }
      paramBoolean = true;
      bool = false;
      if (paramInt4 <= paramInt6) {
        break label234;
      }
      paramInt2 = paramInt6;
      bool = true;
      label147:
      if (bool) {
        this.mScroller.springBack(paramInt1, paramInt2, 0, 0, 0, getScrollRange());
      }
      onOverScrolled(paramInt1, paramInt2, paramBoolean, bool);
      if ((!paramBoolean) && (!bool)) {
        break label253;
      }
    }
    label201:
    label207:
    label213:
    label219:
    label234:
    label253:
    for (paramBoolean = true;; paramBoolean = false)
    {
      return paramBoolean;
      i = 0;
      break;
      j = 0;
      break label34;
      i = 0;
      break label53;
      j = 0;
      break label72;
      paramInt1 = paramInt3;
      if (paramInt3 >= paramInt2) {
        break label131;
      }
      paramInt1 = paramInt2;
      paramBoolean = true;
      break label131;
      paramInt2 = paramInt4;
      if (paramInt4 >= paramInt5) {
        break label147;
      }
      paramInt2 = paramInt5;
      bool = true;
      break label147;
    }
  }
  
  public boolean pageScroll(int paramInt)
  {
    int i;
    int j;
    if (paramInt == 130)
    {
      i = 1;
      j = getHeight();
      if (i == 0) {
        break label121;
      }
      this.mTempRect.top = (getScrollY() + j);
      i = getChildCount();
      if (i > 0)
      {
        View localView = getChildAt(i - 1);
        if (this.mTempRect.top + j > localView.getBottom()) {
          this.mTempRect.top = (localView.getBottom() - j);
        }
      }
    }
    for (;;)
    {
      this.mTempRect.bottom = (this.mTempRect.top + j);
      return scrollAndFocus(paramInt, this.mTempRect.top, this.mTempRect.bottom);
      i = 0;
      break;
      label121:
      this.mTempRect.top = (getScrollY() - j);
      if (this.mTempRect.top < 0) {
        this.mTempRect.top = 0;
      }
    }
  }
  
  public void requestChildFocus(View paramView1, View paramView2)
  {
    if (!this.mIsLayoutDirty) {
      scrollToChild(paramView2);
    }
    for (;;)
    {
      super.requestChildFocus(paramView1, paramView2);
      return;
      this.mChildToScrollTo = paramView2;
    }
  }
  
  public boolean requestChildRectangleOnScreen(View paramView, Rect paramRect, boolean paramBoolean)
  {
    paramRect.offset(paramView.getLeft() - paramView.getScrollX(), paramView.getTop() - paramView.getScrollY());
    return scrollToChildRect(paramRect, paramBoolean);
  }
  
  public void requestDisallowInterceptTouchEvent(boolean paramBoolean)
  {
    if (paramBoolean) {
      recycleVelocityTracker();
    }
    super.requestDisallowInterceptTouchEvent(paramBoolean);
  }
  
  public void requestLayout()
  {
    this.mIsLayoutDirty = true;
    super.requestLayout();
  }
  
  public void scrollTo(int paramInt1, int paramInt2)
  {
    if (getChildCount() > 0)
    {
      View localView = getChildAt(0);
      paramInt1 = clamp(paramInt1, getWidth() - getPaddingRight() - getPaddingLeft(), localView.getWidth());
      paramInt2 = clamp(paramInt2, getHeight() - getPaddingBottom() - getPaddingTop(), localView.getHeight());
      if ((paramInt1 != getScrollX()) || (paramInt2 != getScrollY())) {
        super.scrollTo(paramInt1, paramInt2);
      }
    }
  }
  
  public void setFillViewport(boolean paramBoolean)
  {
    if (paramBoolean != this.mFillViewport)
    {
      this.mFillViewport = paramBoolean;
      requestLayout();
    }
  }
  
  public void setNestedScrollingEnabled(boolean paramBoolean)
  {
    this.mChildHelper.setNestedScrollingEnabled(paramBoolean);
  }
  
  public void setOnScrollChangeListener(OnScrollChangeListener paramOnScrollChangeListener)
  {
    this.mOnScrollChangeListener = paramOnScrollChangeListener;
  }
  
  public void setSmoothScrollingEnabled(boolean paramBoolean)
  {
    this.mSmoothScrollingEnabled = paramBoolean;
  }
  
  public boolean shouldDelayChildPressedState()
  {
    return true;
  }
  
  public final void smoothScrollBy(int paramInt1, int paramInt2)
  {
    if (getChildCount() == 0) {
      return;
    }
    if (AnimationUtils.currentAnimationTimeMillis() - this.mLastScroll > 250L)
    {
      int j = getHeight();
      paramInt1 = getPaddingBottom();
      int i = getPaddingTop();
      i = Math.max(0, getChildAt(0).getHeight() - (j - paramInt1 - i));
      paramInt1 = getScrollY();
      paramInt2 = Math.max(0, Math.min(paramInt1 + paramInt2, i));
      this.mScroller.startScroll(getScrollX(), paramInt1, 0, paramInt2 - paramInt1);
      ViewCompat.postInvalidateOnAnimation(this);
    }
    for (;;)
    {
      this.mLastScroll = AnimationUtils.currentAnimationTimeMillis();
      break;
      if (!this.mScroller.isFinished()) {
        this.mScroller.abortAnimation();
      }
      scrollBy(paramInt1, paramInt2);
    }
  }
  
  public final void smoothScrollTo(int paramInt1, int paramInt2)
  {
    smoothScrollBy(paramInt1 - getScrollX(), paramInt2 - getScrollY());
  }
  
  public boolean startNestedScroll(int paramInt)
  {
    return this.mChildHelper.startNestedScroll(paramInt);
  }
  
  public void stopNestedScroll()
  {
    this.mChildHelper.stopNestedScroll();
  }
  
  static class AccessibilityDelegate
    extends AccessibilityDelegateCompat
  {
    public void onInitializeAccessibilityEvent(View paramView, AccessibilityEvent paramAccessibilityEvent)
    {
      super.onInitializeAccessibilityEvent(paramView, paramAccessibilityEvent);
      paramView = (NestedScrollView)paramView;
      paramAccessibilityEvent.setClassName(ScrollView.class.getName());
      paramAccessibilityEvent = AccessibilityEventCompat.asRecord(paramAccessibilityEvent);
      if (paramView.getScrollRange() > 0) {}
      for (boolean bool = true;; bool = false)
      {
        paramAccessibilityEvent.setScrollable(bool);
        paramAccessibilityEvent.setScrollX(paramView.getScrollX());
        paramAccessibilityEvent.setScrollY(paramView.getScrollY());
        paramAccessibilityEvent.setMaxScrollX(paramView.getScrollX());
        paramAccessibilityEvent.setMaxScrollY(paramView.getScrollRange());
        return;
      }
    }
    
    public void onInitializeAccessibilityNodeInfo(View paramView, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat)
    {
      super.onInitializeAccessibilityNodeInfo(paramView, paramAccessibilityNodeInfoCompat);
      paramView = (NestedScrollView)paramView;
      paramAccessibilityNodeInfoCompat.setClassName(ScrollView.class.getName());
      if (paramView.isEnabled())
      {
        int i = paramView.getScrollRange();
        if (i > 0)
        {
          paramAccessibilityNodeInfoCompat.setScrollable(true);
          if (paramView.getScrollY() > 0) {
            paramAccessibilityNodeInfoCompat.addAction(8192);
          }
          if (paramView.getScrollY() < i) {
            paramAccessibilityNodeInfoCompat.addAction(4096);
          }
        }
      }
    }
    
    public boolean performAccessibilityAction(View paramView, int paramInt, Bundle paramBundle)
    {
      boolean bool = true;
      if (super.performAccessibilityAction(paramView, paramInt, paramBundle)) {}
      for (;;)
      {
        return bool;
        paramView = (NestedScrollView)paramView;
        if (!paramView.isEnabled())
        {
          bool = false;
        }
        else
        {
          int j;
          int i;
          switch (paramInt)
          {
          default: 
            bool = false;
            break;
          case 4096: 
            j = paramView.getHeight();
            paramInt = paramView.getPaddingBottom();
            i = paramView.getPaddingTop();
            paramInt = Math.min(paramView.getScrollY() + (j - paramInt - i), paramView.getScrollRange());
            if (paramInt != paramView.getScrollY()) {
              paramView.smoothScrollTo(0, paramInt);
            } else {
              bool = false;
            }
            break;
          case 8192: 
            j = paramView.getHeight();
            i = paramView.getPaddingBottom();
            paramInt = paramView.getPaddingTop();
            paramInt = Math.max(paramView.getScrollY() - (j - i - paramInt), 0);
            if (paramInt != paramView.getScrollY()) {
              paramView.smoothScrollTo(0, paramInt);
            } else {
              bool = false;
            }
            break;
          }
        }
      }
    }
  }
  
  public static abstract interface OnScrollChangeListener
  {
    public abstract void onScrollChange(NestedScrollView paramNestedScrollView, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  }
  
  static class SavedState
    extends View.BaseSavedState
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator()
    {
      public NestedScrollView.SavedState createFromParcel(Parcel paramAnonymousParcel)
      {
        return new NestedScrollView.SavedState(paramAnonymousParcel);
      }
      
      public NestedScrollView.SavedState[] newArray(int paramAnonymousInt)
      {
        return new NestedScrollView.SavedState[paramAnonymousInt];
      }
    };
    public int scrollPosition;
    
    public SavedState(Parcel paramParcel)
    {
      super();
      this.scrollPosition = paramParcel.readInt();
    }
    
    SavedState(Parcelable paramParcelable)
    {
      super();
    }
    
    public String toString()
    {
      return "HorizontalScrollView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " scrollPosition=" + this.scrollPosition + "}";
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeInt(this.scrollPosition);
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\android\support\v4\widget\NestedScrollView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */