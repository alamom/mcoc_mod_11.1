package com.google.android.gms.games.multiplayer;

import com.google.android.gms.common.internal.o;
import com.google.android.gms.games.Player;
import java.util.ArrayList;

public final class ParticipantUtils
{
  public static boolean bV(String paramString)
  {
    o.b(paramString, "Participant ID must not be null");
    return paramString.startsWith("p_");
  }
  
  public static String getParticipantId(ArrayList<Participant> paramArrayList, String paramString)
  {
    int j = paramArrayList.size();
    int i = 0;
    Participant localParticipant;
    if (i < j)
    {
      localParticipant = (Participant)paramArrayList.get(i);
      Player localPlayer = localParticipant.getPlayer();
      if ((localPlayer == null) || (!localPlayer.getPlayerId().equals(paramString))) {}
    }
    for (paramArrayList = localParticipant.getParticipantId();; paramArrayList = null)
    {
      return paramArrayList;
      i++;
      break;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\games\multiplayer\ParticipantUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */