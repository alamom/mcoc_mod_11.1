package com.google.android.gms.internal;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class po
{
  private static void a(String paramString, Object paramObject, StringBuffer paramStringBuffer1, StringBuffer paramStringBuffer2)
    throws IllegalAccessException, InvocationTargetException
  {
    if (paramObject == null) {
      return;
    }
    int m;
    Class localClass;
    int i;
    Object localObject3;
    Object localObject2;
    if ((paramObject instanceof pn))
    {
      m = paramStringBuffer1.length();
      if (paramString != null)
      {
        paramStringBuffer2.append(paramStringBuffer1).append(dj(paramString)).append(" <\n");
        paramStringBuffer1.append("  ");
      }
      localClass = paramObject.getClass();
      Object localObject1 = localClass.getFields();
      int n = localObject1.length;
      i = 0;
      if (i < n)
      {
        Object localObject4 = localObject1[i];
        j = ((Field)localObject4).getModifiers();
        localObject3 = ((Field)localObject4).getName();
        if (((j & 0x1) == 1) && ((j & 0x8) != 8) && (!((String)localObject3).startsWith("_")) && (!((String)localObject3).endsWith("_")))
        {
          localObject2 = ((Field)localObject4).getType();
          localObject4 = ((Field)localObject4).get(paramObject);
          if (!((Class)localObject2).isArray()) {
            break label231;
          }
          if (((Class)localObject2).getComponentType() != Byte.TYPE) {
            break label183;
          }
          a((String)localObject3, localObject4, paramStringBuffer1, paramStringBuffer2);
        }
        for (;;)
        {
          i++;
          break;
          label183:
          if (localObject4 == null) {}
          for (j = 0;; j = Array.getLength(localObject4))
          {
            for (int k = 0; k < j; k++) {
              a((String)localObject3, Array.get(localObject4, k), paramStringBuffer1, paramStringBuffer2);
            }
            break;
          }
          label231:
          a((String)localObject3, localObject4, paramStringBuffer1, paramStringBuffer2);
        }
      }
      localObject1 = localClass.getMethods();
      int j = localObject1.length;
      i = 0;
      label258:
      if (i < j)
      {
        localObject2 = localObject1[i].getName();
        if (((String)localObject2).startsWith("set")) {
          localObject2 = ((String)localObject2).substring(3);
        }
      }
    }
    for (;;)
    {
      try
      {
        localObject3 = new java/lang/StringBuilder;
        ((StringBuilder)localObject3).<init>();
        localObject3 = localClass.getMethod("has" + (String)localObject2, new Class[0]);
        if (((Boolean)((Method)localObject3).invoke(paramObject, new Object[0])).booleanValue()) {
          continue;
        }
        i++;
      }
      catch (NoSuchMethodException localNoSuchMethodException2)
      {
        continue;
      }
      break label258;
      try
      {
        localObject3 = new java/lang/StringBuilder;
        ((StringBuilder)localObject3).<init>();
        localObject3 = localClass.getMethod("get" + (String)localObject2, new Class[0]);
        a((String)localObject2, ((Method)localObject3).invoke(paramObject, new Object[0]), paramStringBuffer1, paramStringBuffer2);
      }
      catch (NoSuchMethodException localNoSuchMethodException1) {}
      if (paramString == null) {
        break;
      }
      paramStringBuffer1.setLength(m);
      paramStringBuffer2.append(paramStringBuffer1).append(">\n");
      break;
      paramString = dj(paramString);
      paramStringBuffer2.append(paramStringBuffer1).append(paramString).append(": ");
      if ((paramObject instanceof String))
      {
        paramString = dk((String)paramObject);
        paramStringBuffer2.append("\"").append(paramString).append("\"");
        paramStringBuffer2.append("\n");
        break;
      }
      if ((paramObject instanceof byte[])) {
        a((byte[])paramObject, paramStringBuffer2);
      } else {
        paramStringBuffer2.append(paramObject);
      }
    }
  }
  
  private static void a(byte[] paramArrayOfByte, StringBuffer paramStringBuffer)
  {
    if (paramArrayOfByte == null) {
      paramStringBuffer.append("\"\"");
    }
    for (;;)
    {
      return;
      paramStringBuffer.append('"');
      int i = 0;
      if (i < paramArrayOfByte.length)
      {
        int j = paramArrayOfByte[i] & 0xFF;
        if ((j == 92) || (j == 34)) {
          paramStringBuffer.append('\\').append((char)j);
        }
        for (;;)
        {
          i++;
          break;
          if ((j >= 32) && (j < 127)) {
            paramStringBuffer.append((char)j);
          } else {
            paramStringBuffer.append(String.format("\\%03o", new Object[] { Integer.valueOf(j) }));
          }
        }
      }
      paramStringBuffer.append('"');
    }
  }
  
  private static String bf(String paramString)
  {
    int j = paramString.length();
    StringBuilder localStringBuilder = new StringBuilder(j);
    int i = 0;
    if (i < j)
    {
      char c = paramString.charAt(i);
      if ((c >= ' ') && (c <= '~') && (c != '"') && (c != '\'')) {
        localStringBuilder.append(c);
      }
      for (;;)
      {
        i++;
        break;
        localStringBuilder.append(String.format("\\u%04x", new Object[] { Integer.valueOf(c) }));
      }
    }
    return localStringBuilder.toString();
  }
  
  private static String dj(String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    if (i < paramString.length())
    {
      char c = paramString.charAt(i);
      if (i == 0) {
        localStringBuffer.append(Character.toLowerCase(c));
      }
      for (;;)
      {
        i++;
        break;
        if (Character.isUpperCase(c)) {
          localStringBuffer.append('_').append(Character.toLowerCase(c));
        } else {
          localStringBuffer.append(c);
        }
      }
    }
    return localStringBuffer.toString();
  }
  
  private static String dk(String paramString)
  {
    String str = paramString;
    if (!paramString.startsWith("http"))
    {
      str = paramString;
      if (paramString.length() > 200) {
        str = paramString.substring(0, 200) + "[...]";
      }
    }
    return bf(str);
  }
  
  public static <T extends pn> String g(T paramT)
  {
    if (paramT == null) {
      paramT = "";
    }
    for (;;)
    {
      return paramT;
      StringBuffer localStringBuffer1 = new StringBuffer();
      try
      {
        StringBuffer localStringBuffer2 = new java/lang/StringBuffer;
        localStringBuffer2.<init>();
        a(null, paramT, localStringBuffer2, localStringBuffer1);
        paramT = localStringBuffer1.toString();
      }
      catch (IllegalAccessException paramT)
      {
        paramT = "Error printing proto: " + paramT.getMessage();
      }
      catch (InvocationTargetException paramT)
      {
        paramT = "Error printing proto: " + paramT.getMessage();
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\po.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */