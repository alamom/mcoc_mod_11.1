package bolts;

public abstract interface Continuation<TTaskResult, TContinuationResult>
{
  public abstract TContinuationResult then(Task<TTaskResult> paramTask)
    throws Exception;
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\bolts\Continuation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */