package android.support.v4.speech.tts;

import android.content.Context;
import android.os.Build.VERSION;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;

class TextToSpeechICS
{
  private static final String TAG = "android.support.v4.speech.tts";
  
  static TextToSpeech construct(Context paramContext, TextToSpeech.OnInitListener paramOnInitListener, String paramString)
  {
    if (Build.VERSION.SDK_INT < 14) {
      if (paramString == null) {
        paramContext = new TextToSpeech(paramContext, paramOnInitListener);
      }
    }
    for (;;)
    {
      return paramContext;
      Log.w("android.support.v4.speech.tts", "Can't specify tts engine on this device");
      paramContext = new TextToSpeech(paramContext, paramOnInitListener);
      continue;
      paramContext = new TextToSpeech(paramContext, paramOnInitListener, paramString);
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\android\support\v4\speech\tts\TextToSpeechICS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */