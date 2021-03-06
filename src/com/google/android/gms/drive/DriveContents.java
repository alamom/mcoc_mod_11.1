package com.google.android.gms.drive;

import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import java.io.InputStream;
import java.io.OutputStream;

public abstract interface DriveContents
{
  public abstract PendingResult<Status> commit(GoogleApiClient paramGoogleApiClient, MetadataChangeSet paramMetadataChangeSet);
  
  public abstract PendingResult<Status> commit(GoogleApiClient paramGoogleApiClient, MetadataChangeSet paramMetadataChangeSet, ExecutionOptions paramExecutionOptions);
  
  public abstract void discard(GoogleApiClient paramGoogleApiClient);
  
  public abstract Contents getContents();
  
  public abstract DriveId getDriveId();
  
  public abstract InputStream getInputStream();
  
  public abstract int getMode();
  
  public abstract OutputStream getOutputStream();
  
  public abstract ParcelFileDescriptor getParcelFileDescriptor();
  
  public abstract PendingResult<DriveApi.DriveContentsResult> reopenForWrite(GoogleApiClient paramGoogleApiClient);
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\drive\DriveContents.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */