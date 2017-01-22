package com.mobileapptracker;

import java.util.HashMap;
import org.json.JSONObject;

public class MATEventItem
{
  public String attribute_sub1;
  public String attribute_sub2;
  public String attribute_sub3;
  public String attribute_sub4;
  public String attribute_sub5;
  public String itemname;
  public int quantity;
  public double revenue;
  public double unitPrice;
  
  public MATEventItem(String paramString)
  {
    this.itemname = paramString;
  }
  
  public String getAttrStringByName(String paramString)
  {
    if (paramString.equals("itemname")) {
      paramString = this.itemname;
    }
    for (;;)
    {
      return paramString;
      if (paramString.equals("quantity")) {
        paramString = Integer.toString(this.quantity);
      } else if (paramString.equals("unitPrice")) {
        paramString = Double.toString(this.unitPrice);
      } else if (paramString.equals("revenue")) {
        paramString = Double.toString(this.revenue);
      } else if (paramString.equals("attribute_sub1")) {
        paramString = this.attribute_sub1;
      } else if (paramString.equals("attribute_sub2")) {
        paramString = this.attribute_sub2;
      } else if (paramString.equals("attribute_sub3")) {
        paramString = this.attribute_sub3;
      } else if (paramString.equals("attribute_sub4")) {
        paramString = this.attribute_sub4;
      } else if (paramString.equals("attribute_sub5")) {
        paramString = this.attribute_sub5;
      } else {
        paramString = null;
      }
    }
  }
  
  public JSONObject toJSON()
  {
    HashMap localHashMap = new HashMap();
    if (this.itemname != null) {
      localHashMap.put("item", this.itemname);
    }
    localHashMap.put("quantity", Integer.toString(this.quantity));
    localHashMap.put("unit_price", Double.toString(this.unitPrice));
    if (this.revenue != 0.0D) {
      localHashMap.put("revenue", Double.toString(this.revenue));
    }
    if (this.attribute_sub1 != null) {
      localHashMap.put("attribute_sub1", this.attribute_sub1);
    }
    if (this.attribute_sub2 != null) {
      localHashMap.put("attribute_sub2", this.attribute_sub2);
    }
    if (this.attribute_sub3 != null) {
      localHashMap.put("attribute_sub3", this.attribute_sub3);
    }
    if (this.attribute_sub4 != null) {
      localHashMap.put("attribute_sub4", this.attribute_sub4);
    }
    if (this.attribute_sub5 != null) {
      localHashMap.put("attribute_sub5", this.attribute_sub5);
    }
    return new JSONObject(localHashMap);
  }
  
  public MATEventItem withAttribute1(String paramString)
  {
    this.attribute_sub1 = paramString;
    return this;
  }
  
  public MATEventItem withAttribute2(String paramString)
  {
    this.attribute_sub2 = paramString;
    return this;
  }
  
  public MATEventItem withAttribute3(String paramString)
  {
    this.attribute_sub3 = paramString;
    return this;
  }
  
  public MATEventItem withAttribute4(String paramString)
  {
    this.attribute_sub4 = paramString;
    return this;
  }
  
  public MATEventItem withAttribute5(String paramString)
  {
    this.attribute_sub5 = paramString;
    return this;
  }
  
  public MATEventItem withQuantity(int paramInt)
  {
    this.quantity = paramInt;
    return this;
  }
  
  public MATEventItem withRevenue(double paramDouble)
  {
    this.revenue = paramDouble;
    return this;
  }
  
  public MATEventItem withUnitPrice(double paramDouble)
  {
    this.unitPrice = paramDouble;
    return this;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\mobileapptracker\MATEventItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */