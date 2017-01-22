package android.support.v4.content;

import android.os.Build.VERSION;
import java.util.concurrent.Executor;

public class ParallelExecutorCompat
{
  public static Executor getParallelExecutor()
  {
    if (Build.VERSION.SDK_INT >= 11) {}
    for (Executor localExecutor = ExecutorCompatHoneycomb.getParallelExecutor();; localExecutor = ModernAsyncTask.THREAD_POOL_EXECUTOR) {
      return localExecutor;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\android\support\v4\content\ParallelExecutorCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */