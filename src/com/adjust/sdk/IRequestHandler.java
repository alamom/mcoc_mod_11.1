package com.adjust.sdk;

public abstract interface IRequestHandler
{
  public abstract void init(IPackageHandler paramIPackageHandler);
  
  public abstract void sendClickPackage(ActivityPackage paramActivityPackage);
  
  public abstract void sendPackage(ActivityPackage paramActivityPackage);
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\adjust\sdk\IRequestHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */