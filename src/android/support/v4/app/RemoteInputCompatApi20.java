package android.support.v4.app;

import android.app.RemoteInput;
import android.app.RemoteInput.Builder;
import android.content.Intent;
import android.os.Bundle;

class RemoteInputCompatApi20
{
  static void addResultsToIntent(RemoteInputCompatBase.RemoteInput[] paramArrayOfRemoteInput, Intent paramIntent, Bundle paramBundle)
  {
    RemoteInput.addResultsToIntent(fromCompat(paramArrayOfRemoteInput), paramIntent, paramBundle);
  }
  
  static RemoteInput[] fromCompat(RemoteInputCompatBase.RemoteInput[] paramArrayOfRemoteInput)
  {
    Object localObject;
    if (paramArrayOfRemoteInput == null)
    {
      localObject = null;
      return (RemoteInput[])localObject;
    }
    RemoteInput[] arrayOfRemoteInput = new RemoteInput[paramArrayOfRemoteInput.length];
    for (int i = 0;; i++)
    {
      localObject = arrayOfRemoteInput;
      if (i >= paramArrayOfRemoteInput.length) {
        break;
      }
      localObject = paramArrayOfRemoteInput[i];
      arrayOfRemoteInput[i] = new RemoteInput.Builder(((RemoteInputCompatBase.RemoteInput)localObject).getResultKey()).setLabel(((RemoteInputCompatBase.RemoteInput)localObject).getLabel()).setChoices(((RemoteInputCompatBase.RemoteInput)localObject).getChoices()).setAllowFreeFormInput(((RemoteInputCompatBase.RemoteInput)localObject).getAllowFreeFormInput()).addExtras(((RemoteInputCompatBase.RemoteInput)localObject).getExtras()).build();
    }
  }
  
  static Bundle getResultsFromIntent(Intent paramIntent)
  {
    return RemoteInput.getResultsFromIntent(paramIntent);
  }
  
  static RemoteInputCompatBase.RemoteInput[] toCompat(RemoteInput[] paramArrayOfRemoteInput, RemoteInputCompatBase.RemoteInput.Factory paramFactory)
  {
    Object localObject;
    if (paramArrayOfRemoteInput == null)
    {
      localObject = null;
      return (RemoteInputCompatBase.RemoteInput[])localObject;
    }
    RemoteInputCompatBase.RemoteInput[] arrayOfRemoteInput = paramFactory.newArray(paramArrayOfRemoteInput.length);
    for (int i = 0;; i++)
    {
      localObject = arrayOfRemoteInput;
      if (i >= paramArrayOfRemoteInput.length) {
        break;
      }
      localObject = paramArrayOfRemoteInput[i];
      arrayOfRemoteInput[i] = paramFactory.build(((RemoteInput)localObject).getResultKey(), ((RemoteInput)localObject).getLabel(), ((RemoteInput)localObject).getChoices(), ((RemoteInput)localObject).getAllowFreeFormInput(), ((RemoteInput)localObject).getExtras());
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\android\support\v4\app\RemoteInputCompatApi20.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */