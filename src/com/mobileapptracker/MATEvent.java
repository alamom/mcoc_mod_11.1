package com.mobileapptracker;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class MATEvent
  implements Serializable
{
  public static final String ACHIEVEMENT_UNLOCKED = "achievement_unlocked";
  public static final String ADDED_PAYMENT_INFO = "added_payment_info";
  public static final String ADD_TO_CART = "add_to_cart";
  public static final String ADD_TO_WISHLIST = "add_to_wishlist";
  public static final String CHECKOUT_INITIATED = "checkout_initiated";
  public static final String CONTENT_VIEW = "content_view";
  public static final String DEVICE_FORM_WEARABLE = "wearable";
  public static final String INVITE = "invite";
  public static final String LEVEL_ACHIEVED = "level_achieved";
  public static final String LOGIN = "login";
  public static final String PURCHASE = "purchase";
  public static final String RATED = "rated";
  public static final String REGISTRATION = "registration";
  public static final String RESERVATION = "reservation";
  public static final String SEARCH = "search";
  public static final String SHARE = "share";
  public static final String SPENT_CREDITS = "spent_credits";
  public static final String TUTORIAL_COMPLETE = "tutorial_complete";
  private String a;
  private int b;
  private double c;
  private String d;
  private String e;
  private List<MATEventItem> f;
  private String g;
  private String h;
  private String i;
  private String j;
  private int k;
  private int l;
  private String m;
  private double n;
  private Date o;
  private Date p;
  private String q;
  private String r;
  private String s;
  private String t;
  private String u;
  private String v;
  
  public MATEvent(int paramInt)
  {
    this.b = paramInt;
  }
  
  public MATEvent(String paramString)
  {
    this.a = paramString;
  }
  
  public String getAttribute1()
  {
    return this.q;
  }
  
  public String getAttribute2()
  {
    return this.r;
  }
  
  public String getAttribute3()
  {
    return this.s;
  }
  
  public String getAttribute4()
  {
    return this.t;
  }
  
  public String getAttribute5()
  {
    return this.u;
  }
  
  public String getContentId()
  {
    return this.j;
  }
  
  public String getContentType()
  {
    return this.i;
  }
  
  public String getCurrencyCode()
  {
    return this.d;
  }
  
  public Date getDate1()
  {
    return this.o;
  }
  
  public Date getDate2()
  {
    return this.p;
  }
  
  public String getDeviceForm()
  {
    return this.v;
  }
  
  public int getEventId()
  {
    return this.b;
  }
  
  public List<MATEventItem> getEventItems()
  {
    return this.f;
  }
  
  public String getEventName()
  {
    return this.a;
  }
  
  public int getLevel()
  {
    return this.k;
  }
  
  public int getQuantity()
  {
    return this.l;
  }
  
  public double getRating()
  {
    return this.n;
  }
  
  public String getReceiptData()
  {
    return this.g;
  }
  
  public String getReceiptSignature()
  {
    return this.h;
  }
  
  public String getRefId()
  {
    return this.e;
  }
  
  public double getRevenue()
  {
    return this.c;
  }
  
  public String getSearchString()
  {
    return this.m;
  }
  
  public MATEvent withAdvertiserRefId(String paramString)
  {
    this.e = paramString;
    return this;
  }
  
  public MATEvent withAttribute1(String paramString)
  {
    this.q = paramString;
    return this;
  }
  
  public MATEvent withAttribute2(String paramString)
  {
    this.r = paramString;
    return this;
  }
  
  public MATEvent withAttribute3(String paramString)
  {
    this.s = paramString;
    return this;
  }
  
  public MATEvent withAttribute4(String paramString)
  {
    this.t = paramString;
    return this;
  }
  
  public MATEvent withAttribute5(String paramString)
  {
    this.u = paramString;
    return this;
  }
  
  public MATEvent withContentId(String paramString)
  {
    this.j = paramString;
    return this;
  }
  
  public MATEvent withContentType(String paramString)
  {
    this.i = paramString;
    return this;
  }
  
  public MATEvent withCurrencyCode(String paramString)
  {
    this.d = paramString;
    return this;
  }
  
  public MATEvent withDate1(Date paramDate)
  {
    this.o = paramDate;
    return this;
  }
  
  public MATEvent withDate2(Date paramDate)
  {
    this.p = paramDate;
    return this;
  }
  
  public MATEvent withDeviceForm(String paramString)
  {
    this.v = paramString;
    return this;
  }
  
  public MATEvent withEventItems(List<MATEventItem> paramList)
  {
    this.f = paramList;
    return this;
  }
  
  public MATEvent withLevel(int paramInt)
  {
    this.k = paramInt;
    return this;
  }
  
  public MATEvent withQuantity(int paramInt)
  {
    this.l = paramInt;
    return this;
  }
  
  public MATEvent withRating(double paramDouble)
  {
    this.n = paramDouble;
    return this;
  }
  
  public MATEvent withReceipt(String paramString1, String paramString2)
  {
    this.g = paramString1;
    this.h = paramString2;
    return this;
  }
  
  public MATEvent withRevenue(double paramDouble)
  {
    this.c = paramDouble;
    return this;
  }
  
  public MATEvent withSearchString(String paramString)
  {
    this.m = paramString;
    return this;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\mobileapptracker\MATEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */