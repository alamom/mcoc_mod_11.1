package com.amazon.device.iap.cpt;

import com.amazon.cptplugins.SdkEvent;

public class PurchaseReceipt
  implements Comparable<PurchaseReceipt>, SdkEvent
{
  private Long cancelDate;
  private String productType;
  private Long purchaseDate;
  private String receiptId;
  private String sku;
  
  public int compareTo(PurchaseReceipt paramPurchaseReceipt)
  {
    int i;
    if (paramPurchaseReceipt == null) {
      i = 1;
    }
    for (;;)
    {
      return i;
      if (paramPurchaseReceipt == this)
      {
        i = 0;
      }
      else
      {
        Object localObject1 = getReceiptId();
        Object localObject2 = paramPurchaseReceipt.getReceiptId();
        int j;
        if (localObject1 != localObject2)
        {
          if (localObject1 == null)
          {
            i = -1;
            continue;
          }
          if (localObject2 == null)
          {
            i = 1;
            continue;
          }
          if (!(localObject1 instanceof Comparable)) {
            break label113;
          }
          j = ((Comparable)localObject1).compareTo(localObject2);
          i = j;
          if (j != 0) {
            continue;
          }
        }
        label113:
        do
        {
          do
          {
            localObject2 = getCancelDate();
            localObject1 = paramPurchaseReceipt.getCancelDate();
            if (localObject2 == localObject1) {
              break label192;
            }
            if (localObject2 != null) {
              break label155;
            }
            i = -1;
            break;
          } while (localObject1.equals(localObject2));
          i = localObject1.hashCode();
          j = localObject2.hashCode();
          if (i < j)
          {
            i = -1;
            break;
          }
        } while (i <= j);
        i = 1;
        continue;
        label155:
        if (localObject1 == null)
        {
          i = 1;
        }
        else if ((localObject2 instanceof Comparable))
        {
          j = ((Comparable)localObject2).compareTo(localObject1);
          i = j;
          if (j != 0) {}
        }
        else
        {
          label192:
          do
          {
            do
            {
              localObject1 = getPurchaseDate();
              localObject2 = paramPurchaseReceipt.getPurchaseDate();
              if (localObject1 == localObject2) {
                break label300;
              }
              if (localObject1 != null) {
                break label263;
              }
              i = -1;
              break;
            } while (localObject2.equals(localObject1));
            j = localObject2.hashCode();
            i = localObject1.hashCode();
            if (j < i)
            {
              i = -1;
              break;
            }
          } while (j <= i);
          i = 1;
          continue;
          label263:
          if (localObject2 == null)
          {
            i = 1;
          }
          else if ((localObject1 instanceof Comparable))
          {
            j = ((Comparable)localObject1).compareTo(localObject2);
            i = j;
            if (j != 0) {}
          }
          else
          {
            label300:
            do
            {
              do
              {
                localObject2 = getSku();
                localObject1 = paramPurchaseReceipt.getSku();
                if (localObject2 == localObject1) {
                  break label408;
                }
                if (localObject2 != null) {
                  break label371;
                }
                i = -1;
                break;
              } while (localObject1.equals(localObject2));
              i = localObject1.hashCode();
              j = localObject2.hashCode();
              if (i < j)
              {
                i = -1;
                break;
              }
            } while (i <= j);
            i = 1;
            continue;
            label371:
            if (localObject1 == null)
            {
              i = 1;
            }
            else if ((localObject2 instanceof Comparable))
            {
              j = ((Comparable)localObject2).compareTo(localObject1);
              i = j;
              if (j != 0) {}
            }
            else
            {
              label408:
              do
              {
                do
                {
                  localObject1 = getProductType();
                  paramPurchaseReceipt = paramPurchaseReceipt.getProductType();
                  if (localObject1 == paramPurchaseReceipt) {
                    break label512;
                  }
                  if (localObject1 != null) {
                    break label477;
                  }
                  i = -1;
                  break;
                } while (localObject2.equals(localObject1));
                j = localObject2.hashCode();
                i = localObject1.hashCode();
                if (j < i)
                {
                  i = -1;
                  break;
                }
              } while (j <= i);
              i = 1;
              continue;
              label477:
              if (paramPurchaseReceipt == null)
              {
                i = 1;
              }
              else if ((localObject1 instanceof Comparable))
              {
                j = ((Comparable)localObject1).compareTo(paramPurchaseReceipt);
                i = j;
                if (j != 0) {}
              }
              else
              {
                label512:
                do
                {
                  do
                  {
                    i = 0;
                    break;
                  } while (localObject1.equals(paramPurchaseReceipt));
                  i = localObject1.hashCode();
                  j = paramPurchaseReceipt.hashCode();
                  if (i < j)
                  {
                    i = -1;
                    break;
                  }
                } while (i <= j);
                i = 1;
              }
            }
          }
        }
      }
    }
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (paramObject == this) {}
    for (;;)
    {
      return bool;
      if ((paramObject instanceof PurchaseReceipt))
      {
        if (compareTo((PurchaseReceipt)paramObject) != 0) {
          bool = false;
        }
      }
      else {
        bool = false;
      }
    }
  }
  
  public Long getCancelDate()
  {
    return this.cancelDate;
  }
  
  public String getProductType()
  {
    return this.productType;
  }
  
  public Long getPurchaseDate()
  {
    return this.purchaseDate;
  }
  
  public String getReceiptId()
  {
    return this.receiptId;
  }
  
  public String getSku()
  {
    return this.sku;
  }
  
  public int hashCode()
  {
    int n = 0;
    int i;
    int j;
    label21:
    int k;
    label30:
    int m;
    if (getReceiptId() == null)
    {
      i = 0;
      if (getCancelDate() != null) {
        break label86;
      }
      j = 0;
      if (getPurchaseDate() != null) {
        break label97;
      }
      k = 0;
      if (getSku() != null) {
        break label108;
      }
      m = 0;
      label40:
      if (getProductType() != null) {
        break label120;
      }
    }
    for (;;)
    {
      return 31 * (31 * (31 * (31 * (i + 527) + j) + k) + m) + n;
      i = getReceiptId().hashCode();
      break;
      label86:
      j = getCancelDate().hashCode();
      break label21;
      label97:
      k = getPurchaseDate().hashCode();
      break label30;
      label108:
      m = getSku().hashCode();
      break label40;
      label120:
      n = getProductType().hashCode();
    }
  }
  
  public void setCancelDate(Long paramLong)
  {
    this.cancelDate = paramLong;
  }
  
  public void setProductType(String paramString)
  {
    this.productType = paramString;
  }
  
  public void setPurchaseDate(Long paramLong)
  {
    this.purchaseDate = paramLong;
  }
  
  public void setReceiptId(String paramString)
  {
    this.receiptId = paramString;
  }
  
  public void setSku(String paramString)
  {
    this.sku = paramString;
  }
  
  public PurchaseReceipt withCancelDate(Long paramLong)
  {
    setCancelDate(paramLong);
    return this;
  }
  
  public PurchaseReceipt withProductType(String paramString)
  {
    setProductType(paramString);
    return this;
  }
  
  public PurchaseReceipt withPurchaseDate(Long paramLong)
  {
    setPurchaseDate(paramLong);
    return this;
  }
  
  public PurchaseReceipt withReceiptId(String paramString)
  {
    setReceiptId(paramString);
    return this;
  }
  
  public PurchaseReceipt withSku(String paramString)
  {
    setSku(paramString);
    return this;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\amazon\device\iap\cpt\PurchaseReceipt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */