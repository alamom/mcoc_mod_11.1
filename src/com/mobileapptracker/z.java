package com.mobileapptracker;

final class z
  implements Runnable
{
  z(MobileAppTracker paramMobileAppTracker, String paramString) {}
  
  public final void run()
  {
    String str = this.a.replaceAll("\\D+", "");
    StringBuilder localStringBuilder = new StringBuilder();
    for (int i = 0; i < str.length(); i++) {
      localStringBuilder.append(Integer.parseInt(String.valueOf(str.charAt(i))));
    }
    this.b.params.setPhoneNumber(localStringBuilder.toString());
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\mobileapptracker\z.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */