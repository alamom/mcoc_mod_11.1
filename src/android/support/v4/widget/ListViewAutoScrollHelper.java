package android.support.v4.widget;

import android.view.View;
import android.widget.ListView;

public class ListViewAutoScrollHelper
  extends AutoScrollHelper
{
  private final ListView mTarget;
  
  public ListViewAutoScrollHelper(ListView paramListView)
  {
    super(paramListView);
    this.mTarget = paramListView;
  }
  
  public boolean canTargetScrollHorizontally(int paramInt)
  {
    return false;
  }
  
  public boolean canTargetScrollVertically(int paramInt)
  {
    boolean bool2 = false;
    ListView localListView = this.mTarget;
    int k = localListView.getCount();
    boolean bool1;
    if (k == 0) {
      bool1 = bool2;
    }
    for (;;)
    {
      return bool1;
      int i = localListView.getChildCount();
      int j = localListView.getFirstVisiblePosition();
      if (paramInt > 0) {
        if (j + i >= k)
        {
          bool1 = bool2;
          if (localListView.getChildAt(i - 1).getBottom() <= localListView.getHeight()) {
            continue;
          }
        }
      }
      do
      {
        bool1 = true;
        break;
        bool1 = bool2;
        if (paramInt >= 0) {
          break;
        }
      } while ((j > 0) || (localListView.getChildAt(0).getTop() < 0));
      bool1 = bool2;
    }
  }
  
  public void scrollTargetBy(int paramInt1, int paramInt2)
  {
    ListView localListView = this.mTarget;
    paramInt1 = localListView.getFirstVisiblePosition();
    if (paramInt1 == -1) {}
    for (;;)
    {
      return;
      View localView = localListView.getChildAt(0);
      if (localView != null) {
        localListView.setSelectionFromTop(paramInt1, localView.getTop() - paramInt2);
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\android\support\v4\widget\ListViewAutoScrollHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */