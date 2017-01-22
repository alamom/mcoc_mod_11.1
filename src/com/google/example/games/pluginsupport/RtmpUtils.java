package com.google.example.games.pluginsupport;

import android.os.Bundle;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessageReceivedListener;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig.Builder;
import com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener;
import com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener;
import java.util.ArrayList;

public class RtmpUtils
{
  public static void accept(GoogleApiClient paramGoogleApiClient, String paramString, RoomUpdateListener paramRoomUpdateListener, RoomStatusUpdateListener paramRoomStatusUpdateListener, RealTimeMessageReceivedListener paramRealTimeMessageReceivedListener)
  {
    paramRoomUpdateListener = makeRoomConfigBuilder(paramRoomUpdateListener, paramRoomStatusUpdateListener, paramRealTimeMessageReceivedListener);
    paramRoomUpdateListener.setInvitationIdToAccept(paramString);
    Games.RealTimeMultiplayer.join(paramGoogleApiClient, paramRoomUpdateListener.build());
  }
  
  public static void create(GoogleApiClient paramGoogleApiClient, ArrayList<String> paramArrayList, int paramInt, Bundle paramBundle, RoomUpdateListener paramRoomUpdateListener, RoomStatusUpdateListener paramRoomStatusUpdateListener, RealTimeMessageReceivedListener paramRealTimeMessageReceivedListener)
  {
    paramRoomUpdateListener = makeRoomConfigBuilder(paramRoomUpdateListener, paramRoomStatusUpdateListener, paramRealTimeMessageReceivedListener);
    if (paramInt > 0) {
      paramRoomUpdateListener.setVariant(paramInt);
    }
    if (paramArrayList != null) {
      paramRoomUpdateListener.addPlayersToInvite(paramArrayList);
    }
    paramRoomUpdateListener.setAutoMatchCriteria(paramBundle);
    Games.RealTimeMultiplayer.create(paramGoogleApiClient, paramRoomUpdateListener.build());
  }
  
  public static void createQuickGame(GoogleApiClient paramGoogleApiClient, int paramInt1, int paramInt2, int paramInt3, RoomUpdateListener paramRoomUpdateListener, RoomStatusUpdateListener paramRoomStatusUpdateListener, RealTimeMessageReceivedListener paramRealTimeMessageReceivedListener)
  {
    create(paramGoogleApiClient, null, paramInt3, RoomConfig.createAutoMatchCriteria(paramInt1, paramInt2, 0L), paramRoomUpdateListener, paramRoomStatusUpdateListener, paramRealTimeMessageReceivedListener);
  }
  
  private static RoomConfig.Builder makeRoomConfigBuilder(RoomUpdateListener paramRoomUpdateListener, RoomStatusUpdateListener paramRoomStatusUpdateListener, RealTimeMessageReceivedListener paramRealTimeMessageReceivedListener)
  {
    paramRoomUpdateListener = RoomConfig.builder(paramRoomUpdateListener);
    paramRoomUpdateListener.setMessageReceivedListener(paramRealTimeMessageReceivedListener);
    paramRoomUpdateListener.setRoomStatusUpdateListener(paramRoomStatusUpdateListener);
    return paramRoomUpdateListener;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\example\games\pluginsupport\RtmpUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */