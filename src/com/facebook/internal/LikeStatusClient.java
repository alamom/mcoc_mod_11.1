package com.facebook.internal;

import android.content.Context;
import android.os.Bundle;

final class LikeStatusClient
  extends PlatformServiceClient
{
  private String objectId;
  
  LikeStatusClient(Context paramContext, String paramString1, String paramString2)
  {
    super(paramContext, 65542, 65543, 20141001, paramString1);
    this.objectId = paramString2;
  }
  
  protected void populateRequestBundle(Bundle paramBundle)
  {
    paramBundle.putString("com.facebook.platform.extra.OBJECT_ID", this.objectId);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\facebook\internal\LikeStatusClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */