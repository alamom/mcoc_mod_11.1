package com.facebook.widget;

import android.database.CursorIndexOutOfBoundsException;
import com.facebook.model.GraphObject;
import java.util.ArrayList;
import java.util.Collection;

class SimpleGraphObjectCursor<T extends GraphObject>
  implements GraphObjectCursor<T>
{
  private boolean closed = false;
  private boolean fromCache = false;
  private ArrayList<T> graphObjects = new ArrayList();
  private boolean moreObjectsAvailable = false;
  private int pos = -1;
  
  SimpleGraphObjectCursor() {}
  
  SimpleGraphObjectCursor(SimpleGraphObjectCursor<T> paramSimpleGraphObjectCursor)
  {
    this.pos = paramSimpleGraphObjectCursor.pos;
    this.closed = paramSimpleGraphObjectCursor.closed;
    this.graphObjects = new ArrayList();
    this.graphObjects.addAll(paramSimpleGraphObjectCursor.graphObjects);
    this.fromCache = paramSimpleGraphObjectCursor.fromCache;
  }
  
  public void addGraphObjects(Collection<T> paramCollection, boolean paramBoolean)
  {
    this.graphObjects.addAll(paramCollection);
    this.fromCache |= paramBoolean;
  }
  
  public boolean areMoreObjectsAvailable()
  {
    return this.moreObjectsAvailable;
  }
  
  public void close()
  {
    this.closed = true;
  }
  
  public int getCount()
  {
    return this.graphObjects.size();
  }
  
  public T getGraphObject()
  {
    if (this.pos < 0) {
      throw new CursorIndexOutOfBoundsException("Before first object.");
    }
    if (this.pos >= this.graphObjects.size()) {
      throw new CursorIndexOutOfBoundsException("After last object.");
    }
    return (GraphObject)this.graphObjects.get(this.pos);
  }
  
  public int getPosition()
  {
    return this.pos;
  }
  
  public boolean isAfterLast()
  {
    int i = getCount();
    if ((i == 0) || (this.pos == i)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean isBeforeFirst()
  {
    if ((getCount() == 0) || (this.pos == -1)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean isClosed()
  {
    return this.closed;
  }
  
  public boolean isFirst()
  {
    if ((this.pos == 0) && (getCount() != 0)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean isFromCache()
  {
    return this.fromCache;
  }
  
  public boolean isLast()
  {
    int i = getCount();
    if ((this.pos == i - 1) && (i != 0)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean move(int paramInt)
  {
    return moveToPosition(this.pos + paramInt);
  }
  
  public boolean moveToFirst()
  {
    return moveToPosition(0);
  }
  
  public boolean moveToLast()
  {
    return moveToPosition(getCount() - 1);
  }
  
  public boolean moveToNext()
  {
    return moveToPosition(this.pos + 1);
  }
  
  public boolean moveToPosition(int paramInt)
  {
    boolean bool = false;
    int i = getCount();
    if (paramInt >= i) {
      this.pos = i;
    }
    for (;;)
    {
      return bool;
      if (paramInt < 0)
      {
        this.pos = -1;
      }
      else
      {
        this.pos = paramInt;
        bool = true;
      }
    }
  }
  
  public boolean moveToPrevious()
  {
    return moveToPosition(this.pos - 1);
  }
  
  public void setFromCache(boolean paramBoolean)
  {
    this.fromCache = paramBoolean;
  }
  
  public void setMoreObjectsAvailable(boolean paramBoolean)
  {
    this.moreObjectsAvailable = paramBoolean;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\facebook\widget\SimpleGraphObjectCursor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */