package bolts;

import java.util.List;

public class AggregateException
  extends Exception
{
  private static final long serialVersionUID = 1L;
  private List<Exception> errors;
  
  public AggregateException(List<Exception> paramList)
  {
    super("There were multiple errors.");
    this.errors = paramList;
  }
  
  public List<Exception> getErrors()
  {
    return this.errors;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\bolts\AggregateException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */