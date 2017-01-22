package android.support.v4.app;

import android.app.AppOpsManager;
import android.content.Context;

public class AppOpsManagerCompat23
{
  public static int noteOp(Context paramContext, String paramString1, int paramInt, String paramString2)
  {
    return ((AppOpsManager)paramContext.getSystemService(AppOpsManager.class)).noteOp(paramString1, paramInt, paramString2);
  }
  
  public static int noteProxyOp(Context paramContext, String paramString1, String paramString2)
  {
    return ((AppOpsManager)paramContext.getSystemService(AppOpsManager.class)).noteProxyOp(paramString1, paramString2);
  }
  
  public static String permissionToOp(String paramString)
  {
    return AppOpsManager.permissionToOp(paramString);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\android\support\v4\app\AppOpsManagerCompat23.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */