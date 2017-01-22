package bolts;

public class Capture<T>
{
  private T value;
  
  public Capture() {}
  
  public Capture(T paramT)
  {
    this.value = paramT;
  }
  
  public T get()
  {
    return (T)this.value;
  }
  
  public void set(T paramT)
  {
    this.value = paramT;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\bolts\Capture.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */