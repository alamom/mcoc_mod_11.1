package com.kabam.soda.wske;

import com.kabam.soda.Settings;
import com.kabam.wske.api.TranslationsApi;
import com.kabam.wske.model.TranslationResource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GetTranslationsAsyncTask
  extends WSKEAsyncTask<Void, Void, List<TranslationResource>>
{
  public GetTranslationsAsyncTask(Settings paramSettings, WSKECallback<List<TranslationResource>> paramWSKECallback)
  {
    super(paramSettings, paramWSKECallback);
  }
  
  protected List<TranslationResource> doWork(Void... paramVarArgs)
    throws Throwable
  {
    paramVarArgs = new ArrayList();
    Object localObject = new TranslationsApi();
    ((TranslationsApi)localObject).setBasePath(getBasePath());
    localObject = ((TranslationsApi)localObject).translate(null);
    if (localObject != null) {
      paramVarArgs.addAll((Collection)localObject);
    }
    return paramVarArgs;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\soda\wske\GetTranslationsAsyncTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */