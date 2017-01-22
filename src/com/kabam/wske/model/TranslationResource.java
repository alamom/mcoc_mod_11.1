package com.kabam.wske.model;

import com.google.gson.annotations.SerializedName;

public class TranslationResource
{
  @SerializedName("date")
  private String date = null;
  @SerializedName("locale")
  private String locale = null;
  @SerializedName("phrase")
  private String phrase = null;
  @SerializedName("trans")
  private String trans = null;
  
  public String getDate()
  {
    return this.date;
  }
  
  public String getLocale()
  {
    return this.locale;
  }
  
  public String getPhrase()
  {
    return this.phrase;
  }
  
  public String getTrans()
  {
    return this.trans;
  }
  
  public void setDate(String paramString)
  {
    this.date = paramString;
  }
  
  public void setLocale(String paramString)
  {
    this.locale = paramString;
  }
  
  public void setPhrase(String paramString)
  {
    this.phrase = paramString;
  }
  
  public void setTrans(String paramString)
  {
    this.trans = paramString;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("class TranslationResource {\n");
    localStringBuilder.append("  date: ").append(this.date).append("\n");
    localStringBuilder.append("  locale: ").append(this.locale).append("\n");
    localStringBuilder.append("  phrase: ").append(this.phrase).append("\n");
    localStringBuilder.append("  trans: ").append(this.trans).append("\n");
    localStringBuilder.append("}\n");
    return localStringBuilder.toString();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\wske\model\TranslationResource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */