package android.support.v4.util;

public final class Pools
{
  public static abstract interface Pool<T>
  {
    public abstract T acquire();
    
    public abstract boolean release(T paramT);
  }
  
  public static class SimplePool<T>
    implements Pools.Pool<T>
  {
    private final Object[] mPool;
    private int mPoolSize;
    
    public SimplePool(int paramInt)
    {
      if (paramInt <= 0) {
        throw new IllegalArgumentException("The max pool size must be > 0");
      }
      this.mPool = new Object[paramInt];
    }
    
    private boolean isInPool(T paramT)
    {
      int i = 0;
      if (i < this.mPoolSize) {
        if (this.mPool[i] != paramT) {}
      }
      for (boolean bool = true;; bool = false)
      {
        return bool;
        i++;
        break;
      }
    }
    
    public T acquire()
    {
      Object localObject;
      if (this.mPoolSize > 0)
      {
        int i = this.mPoolSize - 1;
        localObject = this.mPool[i];
        this.mPool[i] = null;
        this.mPoolSize -= 1;
      }
      for (;;)
      {
        return (T)localObject;
        localObject = null;
      }
    }
    
    public boolean release(T paramT)
    {
      if (isInPool(paramT)) {
        throw new IllegalStateException("Already in the pool!");
      }
      if (this.mPoolSize < this.mPool.length)
      {
        this.mPool[this.mPoolSize] = paramT;
        this.mPoolSize += 1;
      }
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
  }
  
  public static class SynchronizedPool<T>
    extends Pools.SimplePool<T>
  {
    private final Object mLock = new Object();
    
    public SynchronizedPool(int paramInt)
    {
      super();
    }
    
    public T acquire()
    {
      synchronized (this.mLock)
      {
        Object localObject2 = super.acquire();
        return (T)localObject2;
      }
    }
    
    public boolean release(T paramT)
    {
      synchronized (this.mLock)
      {
        boolean bool = super.release(paramT);
        return bool;
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\android\support\v4\util\Pools.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */