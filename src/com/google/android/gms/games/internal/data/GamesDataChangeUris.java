package com.google.android.gms.games.internal.data;

import android.net.Uri;
import android.net.Uri.Builder;

public final class GamesDataChangeUris
{
  private static final Uri aan = Uri.parse("content://com.google.android.gms.games/").buildUpon().appendPath("data_change").build();
  public static final Uri aao = aan.buildUpon().appendPath("invitations").build();
  public static final Uri aap = aan.buildUpon().appendEncodedPath("players").build();
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\games\internal\data\GamesDataChangeUris.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */