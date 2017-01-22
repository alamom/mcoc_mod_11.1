package com.google.ads.mediation;

import com.google.android.gms.internal.gs;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@Deprecated
public abstract class MediationServerParameters
{
  protected void a() {}
  
  public void load(Map<String, String> paramMap)
    throws MediationServerParameters.MappingException
  {
    Object localObject1 = new HashMap();
    Object localObject3;
    for (localObject2 : getClass().getFields())
    {
      localObject3 = (Parameter)((Field)localObject2).getAnnotation(Parameter.class);
      if (localObject3 != null) {
        ((Map)localObject1).put(((Parameter)localObject3).name(), localObject2);
      }
    }
    if (((Map)localObject1).isEmpty()) {
      gs.W("No server options fields detected. To suppress this message either add a field with the @Parameter annotation, or override the load() method.");
    }
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      localObject2 = (Map.Entry)paramMap.next();
      localObject3 = (Field)((Map)localObject1).remove(((Map.Entry)localObject2).getKey());
      if (localObject3 != null) {
        try
        {
          ((Field)localObject3).set(this, ((Map.Entry)localObject2).getValue());
        }
        catch (IllegalAccessException localIllegalAccessException)
        {
          gs.W("Server option \"" + (String)((Map.Entry)localObject2).getKey() + "\" could not be set: Illegal Access");
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          gs.W("Server option \"" + (String)((Map.Entry)localObject2).getKey() + "\" could not be set: Bad Type");
        }
      } else {
        gs.S("Unexpected server option: " + (String)((Map.Entry)localObject2).getKey() + " = \"" + (String)((Map.Entry)localObject2).getValue() + "\"");
      }
    }
    paramMap = new StringBuilder();
    Object localObject2 = ((Map)localObject1).values().iterator();
    while (((Iterator)localObject2).hasNext())
    {
      localObject1 = (Field)((Iterator)localObject2).next();
      if (((Parameter)((Field)localObject1).getAnnotation(Parameter.class)).required())
      {
        gs.W("Required server option missing: " + ((Parameter)((Field)localObject1).getAnnotation(Parameter.class)).name());
        if (paramMap.length() > 0) {
          paramMap.append(", ");
        }
        paramMap.append(((Parameter)((Field)localObject1).getAnnotation(Parameter.class)).name());
      }
    }
    if (paramMap.length() > 0) {
      throw new MappingException("Required server option(s) missing: " + paramMap.toString());
    }
    a();
  }
  
  public static final class MappingException
    extends Exception
  {
    public MappingException(String paramString)
    {
      super();
    }
  }
  
  @Retention(RetentionPolicy.RUNTIME)
  @Target({java.lang.annotation.ElementType.FIELD})
  protected static @interface Parameter
  {
    String name();
    
    boolean required() default true;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\ads\mediation\MediationServerParameters.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */