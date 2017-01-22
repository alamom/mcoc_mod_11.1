package com.facebook.model;

import com.facebook.FacebookGraphObjectException;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract interface GraphObject
{
  public abstract Map<String, Object> asMap();
  
  public abstract <T extends GraphObject> T cast(Class<T> paramClass);
  
  public abstract JSONObject getInnerJSONObject();
  
  public abstract Object getProperty(String paramString);
  
  public abstract <T extends GraphObject> T getPropertyAs(String paramString, Class<T> paramClass);
  
  public abstract <T extends GraphObject> GraphObjectList<T> getPropertyAsList(String paramString, Class<T> paramClass);
  
  public abstract void removeProperty(String paramString);
  
  public abstract void setProperty(String paramString, Object paramObject);
  
  public static final class Factory
  {
    private static final SimpleDateFormat[] dateFormats = { new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US), new SimpleDateFormat("yyyy-MM-dd", Locale.US) };
    private static final HashSet<Class<?>> verifiedGraphObjectClasses = new HashSet();
    
    static <U> U coerceValueToExpectedType(Object paramObject, Class<U> paramClass, ParameterizedType paramParameterizedType)
    {
      if (paramObject == null) {
        if (Boolean.TYPE.equals(paramClass)) {
          paramObject = Boolean.valueOf(false);
        }
      }
      Class localClass;
      for (;;)
      {
        return (U)paramObject;
        if (Character.TYPE.equals(paramClass))
        {
          paramObject = Character.valueOf('\000');
        }
        else if (paramClass.isPrimitive())
        {
          paramObject = Integer.valueOf(0);
        }
        else
        {
          paramObject = null;
          continue;
          localClass = paramObject.getClass();
          if ((!paramClass.isAssignableFrom(localClass)) && (!paramClass.isPrimitive())) {
            if (GraphObject.class.isAssignableFrom(paramClass))
            {
              if (JSONObject.class.isAssignableFrom(localClass)) {
                paramObject = createGraphObjectProxy(paramClass, (JSONObject)paramObject);
              } else if (GraphObject.class.isAssignableFrom(localClass)) {
                paramObject = ((GraphObject)paramObject).cast(paramClass);
              } else {
                throw new FacebookGraphObjectException("Can't create GraphObject from " + localClass.getName());
              }
            }
            else if ((Iterable.class.equals(paramClass)) || (Collection.class.equals(paramClass)) || (List.class.equals(paramClass)) || (GraphObjectList.class.equals(paramClass)))
            {
              if (paramParameterizedType == null) {
                throw new FacebookGraphObjectException("can't infer generic type of: " + paramClass.toString());
              }
              paramClass = paramParameterizedType.getActualTypeArguments();
              if ((paramClass == null) || (paramClass.length != 1) || (!(paramClass[0] instanceof Class))) {
                throw new FacebookGraphObjectException("Expect collection properties to be of a type with exactly one generic parameter.");
              }
              paramClass = (Class)paramClass[0];
              if (JSONArray.class.isAssignableFrom(localClass)) {
                paramObject = createList((JSONArray)paramObject, paramClass);
              } else {
                throw new FacebookGraphObjectException("Can't create Collection from " + localClass.getName());
              }
            }
            else
            {
              if (!String.class.equals(paramClass)) {
                break;
              }
              if ((Double.class.isAssignableFrom(localClass)) || (Float.class.isAssignableFrom(localClass)))
              {
                paramObject = String.format("%f", new Object[] { paramObject });
              }
              else
              {
                if (!Number.class.isAssignableFrom(localClass)) {
                  break label479;
                }
                paramObject = String.format("%d", new Object[] { paramObject });
              }
            }
          }
        }
      }
      if ((Date.class.equals(paramClass)) && (String.class.isAssignableFrom(localClass)))
      {
        SimpleDateFormat[] arrayOfSimpleDateFormat = dateFormats;
        int j = arrayOfSimpleDateFormat.length;
        int i = 0;
        for (;;)
        {
          if (i >= j) {
            break label479;
          }
          paramParameterizedType = arrayOfSimpleDateFormat[i];
          try
          {
            paramParameterizedType = paramParameterizedType.parse((String)paramObject);
            if (paramParameterizedType != null) {
              paramObject = paramParameterizedType;
            }
          }
          catch (ParseException paramParameterizedType)
          {
            i++;
          }
        }
      }
      label479:
      throw new FacebookGraphObjectException("Can't convert type" + localClass.getName() + " to " + paramClass.getName());
    }
    
    static String convertCamelCaseToLowercaseWithUnderscores(String paramString)
    {
      return paramString.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase(Locale.US);
    }
    
    public static GraphObject create()
    {
      return create(GraphObject.class);
    }
    
    public static <T extends GraphObject> T create(Class<T> paramClass)
    {
      return createGraphObjectProxy(paramClass, new JSONObject());
    }
    
    public static GraphObject create(JSONObject paramJSONObject)
    {
      return create(paramJSONObject, GraphObject.class);
    }
    
    public static <T extends GraphObject> T create(JSONObject paramJSONObject, Class<T> paramClass)
    {
      return createGraphObjectProxy(paramClass, paramJSONObject);
    }
    
    private static <T extends GraphObject> T createGraphObjectProxy(Class<T> paramClass, JSONObject paramJSONObject)
    {
      verifyCanProxyClass(paramClass);
      paramJSONObject = new GraphObjectProxy(paramJSONObject, paramClass);
      return (GraphObject)Proxy.newProxyInstance(GraphObject.class.getClassLoader(), new Class[] { paramClass }, paramJSONObject);
    }
    
    private static Map<String, Object> createGraphObjectProxyForMap(JSONObject paramJSONObject)
    {
      paramJSONObject = new GraphObjectProxy(paramJSONObject, Map.class);
      return (Map)Proxy.newProxyInstance(GraphObject.class.getClassLoader(), new Class[] { Map.class }, paramJSONObject);
    }
    
    public static <T> GraphObjectList<T> createList(Class<T> paramClass)
    {
      return createList(new JSONArray(), paramClass);
    }
    
    public static <T> GraphObjectList<T> createList(JSONArray paramJSONArray, Class<T> paramClass)
    {
      return new GraphObjectListImpl(paramJSONArray, paramClass);
    }
    
    private static Object getUnderlyingJSONObject(Object paramObject)
    {
      if (paramObject == null) {
        paramObject = null;
      }
      for (;;)
      {
        return paramObject;
        Object localObject = paramObject.getClass();
        if (GraphObject.class.isAssignableFrom((Class)localObject))
        {
          paramObject = ((GraphObject)paramObject).getInnerJSONObject();
        }
        else if (GraphObjectList.class.isAssignableFrom((Class)localObject))
        {
          paramObject = ((GraphObjectList)paramObject).getInnerJSONArray();
        }
        else if (Iterable.class.isAssignableFrom((Class)localObject))
        {
          localObject = new JSONArray();
          Iterator localIterator = ((Iterable)paramObject).iterator();
          for (;;)
          {
            paramObject = localObject;
            if (!localIterator.hasNext()) {
              break;
            }
            paramObject = localIterator.next();
            if (GraphObject.class.isAssignableFrom(paramObject.getClass())) {
              ((JSONArray)localObject).put(((GraphObject)paramObject).getInnerJSONObject());
            } else {
              ((JSONArray)localObject).put(paramObject);
            }
          }
        }
      }
    }
    
    private static <T extends GraphObject> boolean hasClassBeenVerified(Class<T> paramClass)
    {
      try
      {
        boolean bool = verifiedGraphObjectClasses.contains(paramClass);
        return bool;
      }
      finally
      {
        paramClass = finally;
        throw paramClass;
      }
    }
    
    public static boolean hasSameId(GraphObject paramGraphObject1, GraphObject paramGraphObject2)
    {
      boolean bool2 = false;
      boolean bool1 = bool2;
      if (paramGraphObject1 != null)
      {
        bool1 = bool2;
        if (paramGraphObject2 != null)
        {
          bool1 = bool2;
          if (paramGraphObject1.asMap().containsKey("id"))
          {
            if (paramGraphObject2.asMap().containsKey("id")) {
              break label54;
            }
            bool1 = bool2;
          }
        }
      }
      for (;;)
      {
        return bool1;
        label54:
        if (paramGraphObject1.equals(paramGraphObject2))
        {
          bool1 = true;
        }
        else
        {
          paramGraphObject1 = paramGraphObject1.getProperty("id");
          paramGraphObject2 = paramGraphObject2.getProperty("id");
          bool1 = bool2;
          if (paramGraphObject1 != null)
          {
            bool1 = bool2;
            if (paramGraphObject2 != null)
            {
              bool1 = bool2;
              if ((paramGraphObject1 instanceof String))
              {
                bool1 = bool2;
                if ((paramGraphObject2 instanceof String)) {
                  bool1 = paramGraphObject1.equals(paramGraphObject2);
                }
              }
            }
          }
        }
      }
    }
    
    private static <T extends GraphObject> void recordClassHasBeenVerified(Class<T> paramClass)
    {
      try
      {
        verifiedGraphObjectClasses.add(paramClass);
        return;
      }
      finally
      {
        paramClass = finally;
        throw paramClass;
      }
    }
    
    private static <T extends GraphObject> void verifyCanProxyClass(Class<T> paramClass)
    {
      if (hasClassBeenVerified(paramClass)) {}
      for (;;)
      {
        return;
        if (!paramClass.isInterface()) {
          throw new FacebookGraphObjectException("Factory can only wrap interfaces, not class: " + paramClass.getName());
        }
        Method[] arrayOfMethod = paramClass.getMethods();
        int j = arrayOfMethod.length;
        int i = 0;
        if (i < j)
        {
          Method localMethod = arrayOfMethod[i];
          String str = localMethod.getName();
          int k = localMethod.getParameterTypes().length;
          Class localClass = localMethod.getReturnType();
          boolean bool = localMethod.isAnnotationPresent(PropertyName.class);
          if (localMethod.getDeclaringClass().isAssignableFrom(GraphObject.class)) {}
          for (;;)
          {
            i++;
            break;
            if ((k == 1) && (localClass == Void.TYPE)) {
              if (bool) {
                if (!Utility.isNullOrEmpty(((PropertyName)localMethod.getAnnotation(PropertyName.class)).value())) {
                  continue;
                }
              }
            }
            label256:
            do
            {
              do
              {
                do
                {
                  do
                  {
                    throw new FacebookGraphObjectException("Factory can't proxy method: " + localMethod.toString());
                  } while ((!str.startsWith("set")) || (str.length() <= 3));
                  break;
                } while ((k != 0) || (localClass == Void.TYPE));
                if (!bool) {
                  break label256;
                }
              } while (Utility.isNullOrEmpty(((PropertyName)localMethod.getAnnotation(PropertyName.class)).value()));
              break;
            } while ((!str.startsWith("get")) || (str.length() <= 3));
          }
        }
        recordClassHasBeenVerified(paramClass);
      }
    }
    
    private static final class GraphObjectListImpl<T>
      extends AbstractList<T>
      implements GraphObjectList<T>
    {
      private final Class<?> itemType;
      private final JSONArray state;
      
      public GraphObjectListImpl(JSONArray paramJSONArray, Class<?> paramClass)
      {
        Validate.notNull(paramJSONArray, "state");
        Validate.notNull(paramClass, "itemType");
        this.state = paramJSONArray;
        this.itemType = paramClass;
      }
      
      private void checkIndex(int paramInt)
      {
        if ((paramInt < 0) || (paramInt >= this.state.length())) {
          throw new IndexOutOfBoundsException();
        }
      }
      
      private void put(int paramInt, T paramT)
      {
        paramT = GraphObject.Factory.getUnderlyingJSONObject(paramT);
        try
        {
          this.state.put(paramInt, paramT);
          return;
        }
        catch (JSONException paramT)
        {
          throw new IllegalArgumentException(paramT);
        }
      }
      
      public void add(int paramInt, T paramT)
      {
        if (paramInt < 0) {
          throw new IndexOutOfBoundsException();
        }
        if (paramInt < size()) {
          throw new UnsupportedOperationException("Only adding items at the end of the list is supported.");
        }
        put(paramInt, paramT);
      }
      
      public final <U extends GraphObject> GraphObjectList<U> castToListOf(Class<U> paramClass)
      {
        if (GraphObject.class.isAssignableFrom(this.itemType))
        {
          if (paramClass.isAssignableFrom(this.itemType)) {}
          for (paramClass = this;; paramClass = GraphObject.Factory.createList(this.state, paramClass)) {
            return paramClass;
          }
        }
        throw new FacebookGraphObjectException("Can't cast GraphObjectCollection of non-GraphObject type " + this.itemType);
      }
      
      public void clear()
      {
        throw new UnsupportedOperationException();
      }
      
      public boolean equals(Object paramObject)
      {
        boolean bool = false;
        if (paramObject == null) {}
        for (;;)
        {
          return bool;
          if (this == paramObject)
          {
            bool = true;
          }
          else if (getClass() == paramObject.getClass())
          {
            paramObject = (GraphObjectListImpl)paramObject;
            bool = this.state.equals(((GraphObjectListImpl)paramObject).state);
          }
        }
      }
      
      public T get(int paramInt)
      {
        checkIndex(paramInt);
        return (T)GraphObject.Factory.coerceValueToExpectedType(this.state.opt(paramInt), this.itemType, null);
      }
      
      public final JSONArray getInnerJSONArray()
      {
        return this.state;
      }
      
      public int hashCode()
      {
        return this.state.hashCode();
      }
      
      public boolean remove(Object paramObject)
      {
        throw new UnsupportedOperationException();
      }
      
      public boolean removeAll(Collection<?> paramCollection)
      {
        throw new UnsupportedOperationException();
      }
      
      public boolean retainAll(Collection<?> paramCollection)
      {
        throw new UnsupportedOperationException();
      }
      
      public T set(int paramInt, T paramT)
      {
        checkIndex(paramInt);
        Object localObject = get(paramInt);
        put(paramInt, paramT);
        return (T)localObject;
      }
      
      public int size()
      {
        return this.state.length();
      }
      
      public String toString()
      {
        return String.format("GraphObjectList{itemType=%s, state=%s}", new Object[] { this.itemType.getSimpleName(), this.state });
      }
    }
    
    private static final class GraphObjectProxy
      extends GraphObject.Factory.ProxyBase<JSONObject>
    {
      private static final String CASTTOMAP_METHOD = "asMap";
      private static final String CAST_METHOD = "cast";
      private static final String CLEAR_METHOD = "clear";
      private static final String CONTAINSKEY_METHOD = "containsKey";
      private static final String CONTAINSVALUE_METHOD = "containsValue";
      private static final String ENTRYSET_METHOD = "entrySet";
      private static final String GETINNERJSONOBJECT_METHOD = "getInnerJSONObject";
      private static final String GETPROPERTYASLIST_METHOD = "getPropertyAsList";
      private static final String GETPROPERTYAS_METHOD = "getPropertyAs";
      private static final String GETPROPERTY_METHOD = "getProperty";
      private static final String GET_METHOD = "get";
      private static final String ISEMPTY_METHOD = "isEmpty";
      private static final String KEYSET_METHOD = "keySet";
      private static final String PUTALL_METHOD = "putAll";
      private static final String PUT_METHOD = "put";
      private static final String REMOVEPROPERTY_METHOD = "removeProperty";
      private static final String REMOVE_METHOD = "remove";
      private static final String SETPROPERTY_METHOD = "setProperty";
      private static final String SIZE_METHOD = "size";
      private static final String VALUES_METHOD = "values";
      private final Class<?> graphObjectClass;
      
      public GraphObjectProxy(JSONObject paramJSONObject, Class<?> paramClass)
      {
        super();
        this.graphObjectClass = paramClass;
      }
      
      private Object createGraphObjectsFromParameters(CreateGraphObject paramCreateGraphObject, Object paramObject)
      {
        Object localObject1 = paramObject;
        if (paramCreateGraphObject != null)
        {
          localObject1 = paramObject;
          if (!Utility.isNullOrEmpty(paramCreateGraphObject.value()))
          {
            paramCreateGraphObject = paramCreateGraphObject.value();
            if (!List.class.isAssignableFrom(paramObject.getClass())) {
              break label101;
            }
            localObject1 = GraphObject.Factory.createList(GraphObject.class);
            paramObject = ((List)paramObject).iterator();
            while (((Iterator)paramObject).hasNext())
            {
              Object localObject2 = ((Iterator)paramObject).next();
              GraphObject localGraphObject = GraphObject.Factory.create();
              localGraphObject.setProperty(paramCreateGraphObject, localObject2);
              ((GraphObjectList)localObject1).add(localGraphObject);
            }
          }
        }
        for (;;)
        {
          return localObject1;
          label101:
          localObject1 = GraphObject.Factory.create();
          ((GraphObject)localObject1).setProperty(paramCreateGraphObject, paramObject);
        }
      }
      
      private final Object proxyGraphObjectGettersAndSetters(Method paramMethod, Object[] paramArrayOfObject)
        throws JSONException
      {
        Object localObject2 = paramMethod.getName();
        int i = paramMethod.getParameterTypes().length;
        Object localObject1 = (PropertyName)paramMethod.getAnnotation(PropertyName.class);
        if (localObject1 != null)
        {
          localObject1 = ((PropertyName)localObject1).value();
          if (i != 0) {
            break label106;
          }
          localObject1 = ((JSONObject)this.state).opt((String)localObject1);
          paramArrayOfObject = paramMethod.getReturnType();
          localObject2 = paramMethod.getGenericReturnType();
          paramMethod = null;
          if ((localObject2 instanceof ParameterizedType)) {
            paramMethod = (ParameterizedType)localObject2;
          }
          paramMethod = GraphObject.Factory.coerceValueToExpectedType(localObject1, paramArrayOfObject, paramMethod);
        }
        for (;;)
        {
          return paramMethod;
          localObject1 = GraphObject.Factory.convertCamelCaseToLowercaseWithUnderscores(((String)localObject2).substring(3));
          break;
          label106:
          if (i == 1)
          {
            paramMethod = GraphObject.Factory.getUnderlyingJSONObject(createGraphObjectsFromParameters((CreateGraphObject)paramMethod.getAnnotation(CreateGraphObject.class), paramArrayOfObject[0]));
            ((JSONObject)this.state).putOpt((String)localObject1, paramMethod);
            paramMethod = null;
          }
          else
          {
            paramMethod = throwUnexpectedMethodSignature(paramMethod);
          }
        }
      }
      
      private final Object proxyGraphObjectMethods(Object paramObject, Method paramMethod, Object[] paramArrayOfObject)
      {
        String str = paramMethod.getName();
        if (str.equals("cast"))
        {
          paramMethod = (Class)paramArrayOfObject[0];
          if ((paramMethod == null) || (!paramMethod.isAssignableFrom(this.graphObjectClass))) {}
        }
        for (;;)
        {
          return paramObject;
          paramObject = GraphObject.Factory.createGraphObjectProxy(paramMethod, (JSONObject)this.state);
          continue;
          if (str.equals("getInnerJSONObject"))
          {
            paramObject = ((GraphObjectProxy)Proxy.getInvocationHandler(paramObject)).state;
          }
          else if (str.equals("asMap"))
          {
            paramObject = GraphObject.Factory.createGraphObjectProxyForMap((JSONObject)this.state);
          }
          else if (str.equals("getProperty"))
          {
            paramObject = ((JSONObject)this.state).opt((String)paramArrayOfObject[0]);
          }
          else if (str.equals("getPropertyAs"))
          {
            paramObject = GraphObject.Factory.coerceValueToExpectedType(((JSONObject)this.state).opt((String)paramArrayOfObject[0]), (Class)paramArrayOfObject[1], null);
          }
          else if (str.equals("getPropertyAsList"))
          {
            paramObject = GraphObject.Factory.coerceValueToExpectedType(((JSONObject)this.state).opt((String)paramArrayOfObject[0]), GraphObjectList.class, new ParameterizedType()
            {
              public Type[] getActualTypeArguments()
              {
                return new Type[] { this.val$expectedType };
              }
              
              public Type getOwnerType()
              {
                return null;
              }
              
              public Type getRawType()
              {
                return GraphObjectList.class;
              }
            });
          }
          else if (str.equals("setProperty"))
          {
            paramObject = setJSONProperty(paramArrayOfObject);
          }
          else if (str.equals("removeProperty"))
          {
            ((JSONObject)this.state).remove((String)paramArrayOfObject[0]);
            paramObject = null;
          }
          else
          {
            paramObject = throwUnexpectedMethodSignature(paramMethod);
          }
        }
      }
      
      private final Object proxyMapMethods(Method paramMethod, Object[] paramArrayOfObject)
      {
        String str = paramMethod.getName();
        if (str.equals("clear"))
        {
          JsonUtil.jsonObjectClear((JSONObject)this.state);
          paramMethod = null;
        }
        for (;;)
        {
          return paramMethod;
          if (str.equals("containsKey"))
          {
            paramMethod = Boolean.valueOf(((JSONObject)this.state).has((String)paramArrayOfObject[0]));
          }
          else if (str.equals("containsValue"))
          {
            paramMethod = Boolean.valueOf(JsonUtil.jsonObjectContainsValue((JSONObject)this.state, paramArrayOfObject[0]));
          }
          else if (str.equals("entrySet"))
          {
            paramMethod = JsonUtil.jsonObjectEntrySet((JSONObject)this.state);
          }
          else if (str.equals("get"))
          {
            paramMethod = ((JSONObject)this.state).opt((String)paramArrayOfObject[0]);
          }
          else
          {
            if (str.equals("isEmpty"))
            {
              if (((JSONObject)this.state).length() == 0) {}
              for (boolean bool = true;; bool = false)
              {
                paramMethod = Boolean.valueOf(bool);
                break;
              }
            }
            if (str.equals("keySet"))
            {
              paramMethod = JsonUtil.jsonObjectKeySet((JSONObject)this.state);
            }
            else if (str.equals("put"))
            {
              paramMethod = setJSONProperty(paramArrayOfObject);
            }
            else if (str.equals("putAll"))
            {
              if ((paramArrayOfObject[0] instanceof Map)) {}
              for (paramMethod = (Map)paramArrayOfObject[0];; paramMethod = ((GraphObject)paramArrayOfObject[0]).asMap())
              {
                JsonUtil.jsonObjectPutAll((JSONObject)this.state, paramMethod);
                paramMethod = null;
                break;
                if (!(paramArrayOfObject[0] instanceof GraphObject)) {
                  break label294;
                }
              }
              label294:
              paramMethod = null;
            }
            else if (str.equals("remove"))
            {
              ((JSONObject)this.state).remove((String)paramArrayOfObject[0]);
              paramMethod = null;
            }
            else if (str.equals("size"))
            {
              paramMethod = Integer.valueOf(((JSONObject)this.state).length());
            }
            else if (str.equals("values"))
            {
              paramMethod = JsonUtil.jsonObjectValues((JSONObject)this.state);
            }
            else
            {
              paramMethod = throwUnexpectedMethodSignature(paramMethod);
            }
          }
        }
      }
      
      private Object setJSONProperty(Object[] paramArrayOfObject)
      {
        String str = (String)paramArrayOfObject[0];
        paramArrayOfObject = GraphObject.Factory.getUnderlyingJSONObject(paramArrayOfObject[1]);
        try
        {
          ((JSONObject)this.state).putOpt(str, paramArrayOfObject);
          return null;
        }
        catch (JSONException paramArrayOfObject)
        {
          throw new IllegalArgumentException(paramArrayOfObject);
        }
      }
      
      public final Object invoke(Object paramObject, Method paramMethod, Object[] paramArrayOfObject)
        throws Throwable
      {
        Class localClass = paramMethod.getDeclaringClass();
        if (localClass == Object.class) {
          paramObject = proxyObjectMethods(paramObject, paramMethod, paramArrayOfObject);
        }
        for (;;)
        {
          return paramObject;
          if (localClass == Map.class) {
            paramObject = proxyMapMethods(paramMethod, paramArrayOfObject);
          } else if (localClass == GraphObject.class) {
            paramObject = proxyGraphObjectMethods(paramObject, paramMethod, paramArrayOfObject);
          } else if (GraphObject.class.isAssignableFrom(localClass)) {
            paramObject = proxyGraphObjectGettersAndSetters(paramMethod, paramArrayOfObject);
          } else {
            paramObject = throwUnexpectedMethodSignature(paramMethod);
          }
        }
      }
      
      public String toString()
      {
        return String.format("GraphObject{graphObjectClass=%s, state=%s}", new Object[] { this.graphObjectClass.getSimpleName(), this.state });
      }
    }
    
    private static abstract class ProxyBase<STATE>
      implements InvocationHandler
    {
      private static final String EQUALS_METHOD = "equals";
      private static final String TOSTRING_METHOD = "toString";
      protected final STATE state;
      
      protected ProxyBase(STATE paramSTATE)
      {
        this.state = paramSTATE;
      }
      
      protected final Object proxyObjectMethods(Object paramObject, Method paramMethod, Object[] paramArrayOfObject)
        throws Throwable
      {
        paramObject = paramMethod.getName();
        if (((String)paramObject).equals("equals"))
        {
          paramObject = paramArrayOfObject[0];
          if (paramObject == null) {
            paramObject = Boolean.valueOf(false);
          }
        }
        for (;;)
        {
          return paramObject;
          paramObject = Proxy.getInvocationHandler(paramObject);
          if (!(paramObject instanceof GraphObject.Factory.GraphObjectProxy))
          {
            paramObject = Boolean.valueOf(false);
          }
          else
          {
            paramObject = (GraphObject.Factory.GraphObjectProxy)paramObject;
            paramObject = Boolean.valueOf(this.state.equals(((GraphObject.Factory.GraphObjectProxy)paramObject).state));
            continue;
            if (((String)paramObject).equals("toString")) {
              paramObject = toString();
            } else {
              paramObject = paramMethod.invoke(this.state, paramArrayOfObject);
            }
          }
        }
      }
      
      protected final Object throwUnexpectedMethodSignature(Method paramMethod)
      {
        throw new FacebookGraphObjectException(getClass().getName() + " got an unexpected method signature: " + paramMethod.toString());
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\facebook\model\GraphObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */