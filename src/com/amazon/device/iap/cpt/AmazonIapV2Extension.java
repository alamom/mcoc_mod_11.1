package com.amazon.device.iap.cpt;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREExtension;

public class AmazonIapV2Extension
  implements FREExtension
{
  public FREContext createContext(String paramString)
  {
    return new AmazonIapV2ExtensionContext();
  }
  
  public void dispose() {}
  
  public void initialize() {}
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\amazon\device\iap\cpt\AmazonIapV2Extension.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */