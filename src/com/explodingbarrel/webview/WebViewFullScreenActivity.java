package com.explodingbarrel.webview;

import android.app.Activity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.google.android.vending.expansion.downloader.Helpers;
import com.unity3d.player.UnityPlayer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WebViewFullScreenActivity
  extends Activity
{
  private static final String TAG = "WebView";
  private WebView FullScreenWebView = null;
  private List<String> RootPages = new ArrayList();
  private List<TabView> Tabs = new ArrayList();
  private ProgressBar WebViewProgress = null;
  
  private RelativeLayout CreateURLTab(final String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2, String paramString4, int paramInt3, int paramInt4)
  {
    RelativeLayout localRelativeLayout3 = new RelativeLayout(this);
    ImageButton localImageButton = new ImageButton(this);
    localImageButton.setBackgroundColor(paramInt1);
    localImageButton.setImageResource(Helpers.getIdResource(this, paramString2));
    Object localObject1 = new RelativeLayout.LayoutParams(-2, -2);
    ((RelativeLayout.LayoutParams)localObject1).addRule(13);
    localImageButton.setLayoutParams((ViewGroup.LayoutParams)localObject1);
    localImageButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Log.d("WebView", "FullScreenWebViewActivity Switching to '" + paramString1 + "' : Success");
        WebViewFullScreenActivity.this.loadPage(paramString1);
      }
    });
    localRelativeLayout3.addView(localImageButton);
    RelativeLayout localRelativeLayout2 = null;
    Object localObject2 = null;
    RelativeLayout localRelativeLayout1 = localRelativeLayout2;
    localObject1 = localObject2;
    if (paramString4 != null)
    {
      localRelativeLayout1 = localRelativeLayout2;
      localObject1 = localObject2;
      if (paramString4.length() > 0)
      {
        localRelativeLayout1 = new RelativeLayout(this);
        localObject1 = new RelativeLayout.LayoutParams(-2, -2);
        paramInt1 = paramInt3;
        if (paramInt3 < 0) {
          paramInt1 = 0;
        }
        paramInt3 = paramInt4;
        if (paramInt4 < 0) {
          paramInt3 = 0;
        }
        ((RelativeLayout.LayoutParams)localObject1).leftMargin = paramInt1;
        ((RelativeLayout.LayoutParams)localObject1).topMargin = paramInt3;
        localRelativeLayout1.setLayoutParams((ViewGroup.LayoutParams)localObject1);
        localRelativeLayout2 = new RelativeLayout(this);
        localRelativeLayout2.setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
        localObject1 = new ImageView(this);
        ((ImageView)localObject1).setImageResource(Helpers.getIdResource(this, paramString4));
        paramString4 = new RelativeLayout.LayoutParams(-2, -2);
        paramString4.addRule(13);
        ((ImageView)localObject1).setLayoutParams(paramString4);
        localRelativeLayout2.addView((View)localObject1);
        localObject1 = new TextView(this);
        ((TextView)localObject1).setText("0");
        ((TextView)localObject1).setTextColor(-1);
        paramString4 = new RelativeLayout.LayoutParams(-2, -2);
        paramString4.addRule(13);
        ((TextView)localObject1).setLayoutParams(paramString4);
        localRelativeLayout2.addView((View)localObject1);
        localRelativeLayout1.addView(localRelativeLayout2);
        localRelativeLayout3.addView(localRelativeLayout1);
      }
    }
    this.Tabs.add(new TabView(paramString1, paramString2, paramString3, localImageButton, paramInt2, localRelativeLayout1, (TextView)localObject1));
    return localRelativeLayout3;
  }
  
  private boolean isRootPage(String paramString)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramString != null)
    {
      Iterator localIterator = this.RootPages.iterator();
      String str;
      do
      {
        bool1 = bool2;
        if (!localIterator.hasNext()) {
          break;
        }
        str = (String)localIterator.next();
      } while ((str == null) || ((paramString.equals(str) != true) && (paramString.equals(str + "/") != true)));
      bool1 = true;
    }
    return bool1;
  }
  
  private void loadPage(String paramString)
  {
    Log.d("WebView", "FullScreenWebViewActivity Switching to '" + paramString + "' : Success");
    this.FullScreenWebView.loadUrl(paramString);
    Iterator localIterator = this.Tabs.iterator();
    if (localIterator.hasNext())
    {
      TabView localTabView = (TabView)localIterator.next();
      String str2 = localTabView.Image;
      if ((localTabView.ImageSelected != null) && (localTabView.ImageSelected.length() > 0)) {}
      for (int i = 1;; i = 0)
      {
        String str1 = str2;
        if (localTabView.Url.equals(paramString) == true)
        {
          str1 = str2;
          if (i == 1) {
            str1 = localTabView.ImageSelected;
          }
        }
        Log.d("WebView", "FullScreenWebViewActivity setting '" + localTabView.Url + "' to " + str1);
        localTabView.TabImage.setImageResource(Helpers.getIdResource(this, str1));
        break;
      }
    }
  }
  
  private void terminateWebView()
  {
    UnityPlayer.UnitySendMessage("WebViewCallbacks", "WebViewWillHide", "");
    finish();
  }
  
  private void updateBadge(int paramInt, final String paramString)
  {
    Log.d("WebView", "FullScreenWebViewActivity updateBadge : id = " + paramInt + " value = " + paramString);
    Iterator localIterator = this.Tabs.iterator();
    while (localIterator.hasNext())
    {
      final TabView localTabView = (TabView)localIterator.next();
      if (localTabView.BadgeId == paramInt) {
        localTabView.BadgeLayout.post(new Runnable()
        {
          public void run()
          {
            j = 0;
            i = j;
            for (;;)
            {
              try
              {
                if (paramString != null)
                {
                  k = paramString.length();
                  i = j;
                  if (k <= 0) {}
                }
              }
              catch (Exception localException)
              {
                int k;
                Log.d("WebView", "FullScreenWebViewActivity Badges exception: '" + localException.getMessage());
                continue;
              }
              try
              {
                k = Integer.parseInt(paramString);
                i = j;
                if (k != 0) {
                  i = 1;
                }
              }
              catch (NumberFormatException localNumberFormatException)
              {
                i = j;
              }
            }
            if (i == 1)
            {
              localTabView.BadgeText.setText(paramString);
              localTabView.BadgeLayout.setVisibility(0);
            }
            for (;;)
            {
              return;
              localTabView.BadgeText.setText("");
              localTabView.BadgeLayout.setVisibility(4);
            }
          }
        });
      }
    }
  }
  
  public void onBackPressed()
  {
    String str = this.FullScreenWebView.getOriginalUrl();
    boolean bool2 = isRootPage(str);
    boolean bool1 = this.FullScreenWebView.canGoBack();
    Log.d("WebView", "FullScreenWebViewActivity onBackPressed : current = " + str + " isRoot = " + bool2 + " canBack " + bool1);
    if ((bool1 == true) && (!bool2)) {
      this.FullScreenWebView.goBack();
    }
    for (;;)
    {
      return;
      terminateWebView();
    }
  }
  
  /* Error */
  public void onCreate(android.os.Bundle paramBundle)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokespecial 284	android/app/Activity:onCreate	(Landroid/os/Bundle;)V
    //   5: new 286	android/util/DisplayMetrics
    //   8: dup
    //   9: invokespecial 287	android/util/DisplayMetrics:<init>	()V
    //   12: astore 27
    //   14: aload_0
    //   15: invokevirtual 291	com/explodingbarrel/webview/WebViewFullScreenActivity:getWindowManager	()Landroid/view/WindowManager;
    //   18: invokeinterface 297 1 0
    //   23: aload 27
    //   25: invokevirtual 303	android/view/Display:getMetrics	(Landroid/util/DisplayMetrics;)V
    //   28: aload_0
    //   29: invokevirtual 307	com/explodingbarrel/webview/WebViewFullScreenActivity:getIntent	()Landroid/content/Intent;
    //   32: invokevirtual 313	android/content/Intent:getExtras	()Landroid/os/Bundle;
    //   35: ldc_w 315
    //   38: invokevirtual 321	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   41: astore 26
    //   43: aload_0
    //   44: invokevirtual 307	com/explodingbarrel/webview/WebViewFullScreenActivity:getIntent	()Landroid/content/Intent;
    //   47: invokevirtual 313	android/content/Intent:getExtras	()Landroid/os/Bundle;
    //   50: ldc_w 323
    //   53: invokevirtual 321	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   56: astore_1
    //   57: aload_0
    //   58: getfield 51	com/explodingbarrel/webview/WebViewFullScreenActivity:RootPages	Ljava/util/List;
    //   61: invokeinterface 326 1 0
    //   66: ldc 29
    //   68: new 178	java/lang/StringBuilder
    //   71: dup
    //   72: invokespecial 179	java/lang/StringBuilder:<init>	()V
    //   75: ldc_w 328
    //   78: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   81: aload 26
    //   83: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   86: ldc_w 330
    //   89: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   92: aload_1
    //   93: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   96: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   99: invokestatic 199	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   102: pop
    //   103: ldc -29
    //   105: astore 24
    //   107: ldc -29
    //   109: astore 23
    //   111: ldc -29
    //   113: astore 19
    //   115: ldc_w 331
    //   118: istore 8
    //   120: ldc_w 331
    //   123: istore 9
    //   125: iconst_1
    //   126: istore 11
    //   128: iconst_1
    //   129: istore 16
    //   131: iconst_1
    //   132: istore 14
    //   134: iconst_1
    //   135: istore 12
    //   137: iconst_1
    //   138: istore 15
    //   140: iconst_1
    //   141: istore 13
    //   143: sipush 1024
    //   146: istore 10
    //   148: aconst_null
    //   149: astore 21
    //   151: new 333	org/json/JSONObject
    //   154: astore 25
    //   156: aload 25
    //   158: aload_1
    //   159: invokespecial 335	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   162: iload 8
    //   164: istore 5
    //   166: iload 9
    //   168: istore_3
    //   169: iload 10
    //   171: istore 7
    //   173: aload 23
    //   175: astore 18
    //   177: aload 24
    //   179: astore 17
    //   181: iload 15
    //   183: istore 12
    //   185: aload 19
    //   187: astore_1
    //   188: iload 16
    //   190: istore 11
    //   192: aload 25
    //   194: ldc_w 337
    //   197: invokevirtual 341	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   200: astore 28
    //   202: iload 8
    //   204: istore 4
    //   206: iload 9
    //   208: istore 6
    //   210: aload 23
    //   212: astore 21
    //   214: aload 24
    //   216: astore 22
    //   218: aload 19
    //   220: astore 20
    //   222: aload 28
    //   224: ifnull +371 -> 595
    //   227: iload 8
    //   229: istore 5
    //   231: iload 9
    //   233: istore_3
    //   234: iload 10
    //   236: istore 7
    //   238: aload 23
    //   240: astore 18
    //   242: aload 24
    //   244: astore 17
    //   246: iload 15
    //   248: istore 12
    //   250: aload 19
    //   252: astore_1
    //   253: iload 16
    //   255: istore 11
    //   257: aload 28
    //   259: ldc_w 343
    //   262: invokevirtual 344	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   265: astore 22
    //   267: iload 8
    //   269: istore 5
    //   271: iload 9
    //   273: istore_3
    //   274: iload 10
    //   276: istore 7
    //   278: aload 23
    //   280: astore 18
    //   282: aload 22
    //   284: astore 17
    //   286: iload 15
    //   288: istore 12
    //   290: aload 19
    //   292: astore_1
    //   293: iload 16
    //   295: istore 11
    //   297: aload 28
    //   299: ldc_w 346
    //   302: invokevirtual 350	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   305: istore 4
    //   307: iload 4
    //   309: istore 6
    //   311: iload 4
    //   313: istore 5
    //   315: iload 6
    //   317: istore_3
    //   318: iload 10
    //   320: istore 7
    //   322: aload 23
    //   324: astore 18
    //   326: aload 22
    //   328: astore 17
    //   330: iload 15
    //   332: istore 12
    //   334: aload 19
    //   336: astore_1
    //   337: iload 16
    //   339: istore 11
    //   341: aload 28
    //   343: ldc_w 352
    //   346: invokevirtual 350	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   349: pop
    //   350: iload 4
    //   352: istore 5
    //   354: iload 6
    //   356: istore_3
    //   357: iload 10
    //   359: istore 7
    //   361: aload 23
    //   363: astore 18
    //   365: aload 22
    //   367: astore 17
    //   369: iload 15
    //   371: istore 12
    //   373: aload 19
    //   375: astore_1
    //   376: iload 16
    //   378: istore 11
    //   380: aload 28
    //   382: ldc_w 354
    //   385: invokevirtual 357	org/json/JSONObject:getBoolean	(Ljava/lang/String;)Z
    //   388: istore 14
    //   390: iload 4
    //   392: istore 5
    //   394: iload 6
    //   396: istore_3
    //   397: iload 10
    //   399: istore 7
    //   401: aload 23
    //   403: astore 18
    //   405: aload 22
    //   407: astore 17
    //   409: iload 15
    //   411: istore 12
    //   413: aload 19
    //   415: astore_1
    //   416: iload 14
    //   418: istore 11
    //   420: aload 28
    //   422: ldc_w 359
    //   425: invokevirtual 344	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   428: astore 21
    //   430: iload 4
    //   432: istore 5
    //   434: iload 6
    //   436: istore_3
    //   437: iload 10
    //   439: istore 7
    //   441: aload 21
    //   443: astore 18
    //   445: aload 22
    //   447: astore 17
    //   449: iload 15
    //   451: istore 12
    //   453: aload 19
    //   455: astore_1
    //   456: iload 14
    //   458: istore 11
    //   460: aload 28
    //   462: ldc_w 361
    //   465: invokevirtual 357	org/json/JSONObject:getBoolean	(Ljava/lang/String;)Z
    //   468: istore 13
    //   470: aload 19
    //   472: astore 20
    //   474: iload 4
    //   476: istore 5
    //   478: iload 6
    //   480: istore_3
    //   481: iload 10
    //   483: istore 7
    //   485: aload 21
    //   487: astore 18
    //   489: aload 22
    //   491: astore 17
    //   493: iload 13
    //   495: istore 12
    //   497: aload 19
    //   499: astore_1
    //   500: iload 14
    //   502: istore 11
    //   504: aload 28
    //   506: ldc_w 363
    //   509: invokevirtual 366	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   512: iconst_1
    //   513: if_icmpne +43 -> 556
    //   516: iload 4
    //   518: istore 5
    //   520: iload 6
    //   522: istore_3
    //   523: iload 10
    //   525: istore 7
    //   527: aload 21
    //   529: astore 18
    //   531: aload 22
    //   533: astore 17
    //   535: iload 13
    //   537: istore 12
    //   539: aload 19
    //   541: astore_1
    //   542: iload 14
    //   544: istore 11
    //   546: aload 28
    //   548: ldc_w 363
    //   551: invokevirtual 344	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   554: astore 20
    //   556: iload 4
    //   558: istore 5
    //   560: iload 6
    //   562: istore_3
    //   563: iload 10
    //   565: istore 7
    //   567: aload 21
    //   569: astore 18
    //   571: aload 22
    //   573: astore 17
    //   575: iload 13
    //   577: istore 12
    //   579: aload 20
    //   581: astore_1
    //   582: iload 14
    //   584: istore 11
    //   586: aload 28
    //   588: ldc_w 368
    //   591: invokevirtual 344	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   594: pop
    //   595: iload 4
    //   597: istore 5
    //   599: iload 6
    //   601: istore_3
    //   602: iload 10
    //   604: istore 7
    //   606: aload 21
    //   608: astore 18
    //   610: aload 22
    //   612: astore 17
    //   614: iload 13
    //   616: istore 12
    //   618: aload 20
    //   620: astore_1
    //   621: iload 14
    //   623: istore 11
    //   625: aload 25
    //   627: ldc_w 370
    //   630: invokevirtual 350	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   633: istore 9
    //   635: iload 6
    //   637: istore 8
    //   639: iload 4
    //   641: istore 5
    //   643: iload 6
    //   645: istore_3
    //   646: iload 9
    //   648: istore 7
    //   650: aload 21
    //   652: astore 18
    //   654: aload 22
    //   656: astore 17
    //   658: iload 13
    //   660: istore 12
    //   662: aload 20
    //   664: astore_1
    //   665: iload 14
    //   667: istore 11
    //   669: aload 25
    //   671: ldc_w 346
    //   674: invokevirtual 366	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   677: iconst_1
    //   678: if_icmpne +43 -> 721
    //   681: iload 4
    //   683: istore 5
    //   685: iload 6
    //   687: istore_3
    //   688: iload 9
    //   690: istore 7
    //   692: aload 21
    //   694: astore 18
    //   696: aload 22
    //   698: astore 17
    //   700: iload 13
    //   702: istore 12
    //   704: aload 20
    //   706: astore_1
    //   707: iload 14
    //   709: istore 11
    //   711: aload 25
    //   713: ldc_w 346
    //   716: invokevirtual 350	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   719: istore 8
    //   721: aload 25
    //   723: astore 19
    //   725: iload 14
    //   727: istore 11
    //   729: aload 20
    //   731: astore_1
    //   732: iload 13
    //   734: istore 12
    //   736: aload 22
    //   738: astore 17
    //   740: iload 9
    //   742: istore 7
    //   744: iload 8
    //   746: istore_3
    //   747: fconst_0
    //   748: fstore_2
    //   749: iload 12
    //   751: iconst_1
    //   752: if_icmpne +5 -> 757
    //   755: fconst_1
    //   756: fstore_2
    //   757: new 58	android/widget/RelativeLayout
    //   760: dup
    //   761: aload_0
    //   762: invokespecial 61	android/widget/RelativeLayout:<init>	(Landroid/content/Context;)V
    //   765: astore 22
    //   767: new 79	android/widget/RelativeLayout$LayoutParams
    //   770: dup
    //   771: iconst_m1
    //   772: iconst_m1
    //   773: invokespecial 82	android/widget/RelativeLayout$LayoutParams:<init>	(II)V
    //   776: astore 23
    //   778: new 372	android/widget/LinearLayout
    //   781: dup
    //   782: aload_0
    //   783: invokespecial 373	android/widget/LinearLayout:<init>	(Landroid/content/Context;)V
    //   786: astore 24
    //   788: aload 24
    //   790: iconst_1
    //   791: invokevirtual 376	android/widget/LinearLayout:setOrientation	(I)V
    //   794: aload 24
    //   796: new 378	android/widget/LinearLayout$LayoutParams
    //   799: dup
    //   800: iconst_m1
    //   801: iconst_m1
    //   802: invokespecial 379	android/widget/LinearLayout$LayoutParams:<init>	(II)V
    //   805: invokevirtual 380	android/widget/LinearLayout:setLayoutParams	(Landroid/view/ViewGroup$LayoutParams;)V
    //   808: new 372	android/widget/LinearLayout
    //   811: dup
    //   812: aload_0
    //   813: invokespecial 373	android/widget/LinearLayout:<init>	(Landroid/content/Context;)V
    //   816: astore 20
    //   818: aload 20
    //   820: iconst_0
    //   821: invokevirtual 376	android/widget/LinearLayout:setOrientation	(I)V
    //   824: aload 20
    //   826: iload 4
    //   828: invokevirtual 381	android/widget/LinearLayout:setBackgroundColor	(I)V
    //   831: aload 20
    //   833: new 378	android/widget/LinearLayout$LayoutParams
    //   836: dup
    //   837: iconst_m1
    //   838: bipush -2
    //   840: invokespecial 379	android/widget/LinearLayout$LayoutParams:<init>	(II)V
    //   843: invokevirtual 380	android/widget/LinearLayout:setLayoutParams	(Landroid/view/ViewGroup$LayoutParams;)V
    //   846: new 63	android/widget/ImageButton
    //   849: dup
    //   850: aload_0
    //   851: invokespecial 64	android/widget/ImageButton:<init>	(Landroid/content/Context;)V
    //   854: astore 18
    //   856: aload 18
    //   858: iload 4
    //   860: invokevirtual 68	android/widget/ImageButton:setBackgroundColor	(I)V
    //   863: aload 18
    //   865: aload_0
    //   866: aload 17
    //   868: invokestatic 74	com/google/android/vending/expansion/downloader/Helpers:getIdResource	(Landroid/content/Context;Ljava/lang/String;)I
    //   871: invokevirtual 77	android/widget/ImageButton:setImageResource	(I)V
    //   874: aload 18
    //   876: new 378	android/widget/LinearLayout$LayoutParams
    //   879: dup
    //   880: bipush -2
    //   882: bipush -2
    //   884: fload_2
    //   885: invokespecial 384	android/widget/LinearLayout$LayoutParams:<init>	(IIF)V
    //   888: invokevirtual 89	android/widget/ImageButton:setLayoutParams	(Landroid/view/ViewGroup$LayoutParams;)V
    //   891: aload 18
    //   893: new 10	com/explodingbarrel/webview/WebViewFullScreenActivity$3
    //   896: dup
    //   897: aload_0
    //   898: invokespecial 386	com/explodingbarrel/webview/WebViewFullScreenActivity$3:<init>	(Lcom/explodingbarrel/webview/WebViewFullScreenActivity;)V
    //   901: invokevirtual 96	android/widget/ImageButton:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   904: aload 20
    //   906: aload 18
    //   908: invokevirtual 387	android/widget/LinearLayout:addView	(Landroid/view/View;)V
    //   911: aload 19
    //   913: ifnull +563 -> 1476
    //   916: aload 19
    //   918: ldc_w 389
    //   921: invokevirtual 393	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   924: astore 19
    //   926: aload 19
    //   928: ifnull +548 -> 1476
    //   931: new 178	java/lang/StringBuilder
    //   934: astore 17
    //   936: aload 17
    //   938: invokespecial 179	java/lang/StringBuilder:<init>	()V
    //   941: ldc 29
    //   943: aload 17
    //   945: ldc_w 395
    //   948: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   951: aload 19
    //   953: invokevirtual 398	org/json/JSONArray:length	()I
    //   956: invokevirtual 241	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   959: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   962: invokestatic 199	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   965: pop
    //   966: iconst_0
    //   967: istore 5
    //   969: iload 5
    //   971: aload 19
    //   973: invokevirtual 398	org/json/JSONArray:length	()I
    //   976: if_icmpge +500 -> 1476
    //   979: aload 19
    //   981: iload 5
    //   983: invokevirtual 401	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   986: astore 29
    //   988: aload 29
    //   990: ldc_w 402
    //   993: invokevirtual 344	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   996: astore 28
    //   998: aload 29
    //   1000: ldc_w 403
    //   1003: invokevirtual 344	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   1006: astore 25
    //   1008: aconst_null
    //   1009: astore 18
    //   1011: aload 18
    //   1013: astore 17
    //   1015: aload_1
    //   1016: ifnull +40 -> 1056
    //   1019: aload 18
    //   1021: astore 17
    //   1023: aload_1
    //   1024: invokevirtual 106	java/lang/String:length	()I
    //   1027: ifle +29 -> 1056
    //   1030: new 178	java/lang/StringBuilder
    //   1033: astore 17
    //   1035: aload 17
    //   1037: invokespecial 179	java/lang/StringBuilder:<init>	()V
    //   1040: aload 17
    //   1042: aload 25
    //   1044: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1047: aload_1
    //   1048: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1051: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1054: astore 17
    //   1056: iconst_m1
    //   1057: istore 6
    //   1059: iconst_0
    //   1060: istore 9
    //   1062: iconst_0
    //   1063: istore 8
    //   1065: aload 29
    //   1067: ldc_w 405
    //   1070: invokevirtual 341	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   1073: astore 18
    //   1075: aload 18
    //   1077: ifnull +33 -> 1110
    //   1080: aload 18
    //   1082: ldc_w 407
    //   1085: invokevirtual 350	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   1088: istore 6
    //   1090: aload 18
    //   1092: ldc_w 409
    //   1095: invokevirtual 350	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   1098: istore 9
    //   1100: aload 18
    //   1102: ldc_w 411
    //   1105: invokevirtual 350	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   1108: istore 8
    //   1110: ldc -29
    //   1112: astore 18
    //   1114: iload 6
    //   1116: iconst_m1
    //   1117: if_icmpeq +7 -> 1124
    //   1120: aload 21
    //   1122: astore 18
    //   1124: iload 9
    //   1126: i2f
    //   1127: aload 27
    //   1129: getfield 415	android/util/DisplayMetrics:density	F
    //   1132: fmul
    //   1133: ldc_w 416
    //   1136: fadd
    //   1137: f2i
    //   1138: istore 9
    //   1140: iload 8
    //   1142: i2f
    //   1143: aload 27
    //   1145: getfield 415	android/util/DisplayMetrics:density	F
    //   1148: fmul
    //   1149: ldc_w 416
    //   1152: fadd
    //   1153: f2i
    //   1154: istore 8
    //   1156: new 178	java/lang/StringBuilder
    //   1159: astore 30
    //   1161: aload 30
    //   1163: invokespecial 179	java/lang/StringBuilder:<init>	()V
    //   1166: ldc 29
    //   1168: aload 30
    //   1170: ldc_w 418
    //   1173: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1176: aload 28
    //   1178: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1181: ldc_w 420
    //   1184: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1187: aload 25
    //   1189: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1192: ldc_w 422
    //   1195: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1198: aload 17
    //   1200: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1203: ldc_w 424
    //   1206: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1209: iload 6
    //   1211: invokevirtual 241	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1214: ldc_w 426
    //   1217: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1220: aload 18
    //   1222: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1225: ldc_w 428
    //   1228: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1231: iload 9
    //   1233: invokevirtual 241	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1236: ldc_w 430
    //   1239: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1242: iload 8
    //   1244: invokevirtual 241	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1247: ldc_w 432
    //   1250: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1253: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1256: invokestatic 199	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   1259: pop
    //   1260: aload_0
    //   1261: getfield 51	com/explodingbarrel/webview/WebViewFullScreenActivity:RootPages	Ljava/util/List;
    //   1264: aload 28
    //   1266: invokeinterface 141 2 0
    //   1271: pop
    //   1272: aload 29
    //   1274: ldc_w 434
    //   1277: invokevirtual 366	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   1280: iconst_1
    //   1281: if_icmpne +25 -> 1306
    //   1284: aload 29
    //   1286: ldc_w 434
    //   1289: invokevirtual 344	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   1292: astore 29
    //   1294: aload_0
    //   1295: getfield 51	com/explodingbarrel/webview/WebViewFullScreenActivity:RootPages	Ljava/util/List;
    //   1298: aload 29
    //   1300: invokeinterface 141 2 0
    //   1305: pop
    //   1306: aload_0
    //   1307: aload 28
    //   1309: aload 25
    //   1311: aload 17
    //   1313: iload 4
    //   1315: iload 6
    //   1317: aload 18
    //   1319: iload 9
    //   1321: iload 8
    //   1323: invokespecial 436	com/explodingbarrel/webview/WebViewFullScreenActivity:CreateURLTab	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IILjava/lang/String;II)Landroid/widget/RelativeLayout;
    //   1326: astore 17
    //   1328: new 378	android/widget/LinearLayout$LayoutParams
    //   1331: astore 18
    //   1333: aload 18
    //   1335: bipush -2
    //   1337: bipush -2
    //   1339: fload_2
    //   1340: invokespecial 384	android/widget/LinearLayout$LayoutParams:<init>	(IIF)V
    //   1343: aload 17
    //   1345: aload 18
    //   1347: invokevirtual 114	android/widget/RelativeLayout:setLayoutParams	(Landroid/view/ViewGroup$LayoutParams;)V
    //   1350: aload 20
    //   1352: aload 17
    //   1354: invokevirtual 387	android/widget/LinearLayout:addView	(Landroid/view/View;)V
    //   1357: iload 6
    //   1359: iconst_m1
    //   1360: if_icmpeq +11 -> 1371
    //   1363: aload_0
    //   1364: iload 6
    //   1366: ldc 124
    //   1368: invokespecial 147	com/explodingbarrel/webview/WebViewFullScreenActivity:updateBadge	(ILjava/lang/String;)V
    //   1371: iinc 5 1
    //   1374: goto -405 -> 969
    //   1377: astore 20
    //   1379: aload 19
    //   1381: astore_1
    //   1382: aload 24
    //   1384: astore 17
    //   1386: aload 23
    //   1388: astore 18
    //   1390: iload 10
    //   1392: istore 7
    //   1394: iload 9
    //   1396: istore_3
    //   1397: aload 21
    //   1399: astore 19
    //   1401: iload 8
    //   1403: istore 5
    //   1405: ldc 29
    //   1407: new 178	java/lang/StringBuilder
    //   1410: dup
    //   1411: invokespecial 179	java/lang/StringBuilder:<init>	()V
    //   1414: ldc_w 438
    //   1417: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1420: aload 20
    //   1422: invokevirtual 441	org/json/JSONException:getMessage	()Ljava/lang/String;
    //   1425: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1428: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1431: invokestatic 199	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   1434: pop
    //   1435: iload 5
    //   1437: istore 4
    //   1439: aload 18
    //   1441: astore 21
    //   1443: goto -696 -> 747
    //   1446: astore_1
    //   1447: ldc 29
    //   1449: new 178	java/lang/StringBuilder
    //   1452: dup
    //   1453: invokespecial 179	java/lang/StringBuilder:<init>	()V
    //   1456: ldc_w 443
    //   1459: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1462: aload_1
    //   1463: invokevirtual 441	org/json/JSONException:getMessage	()Ljava/lang/String;
    //   1466: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1469: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1472: invokestatic 199	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   1475: pop
    //   1476: new 58	android/widget/RelativeLayout
    //   1479: dup
    //   1480: aload_0
    //   1481: invokespecial 61	android/widget/RelativeLayout:<init>	(Landroid/content/Context;)V
    //   1484: astore_1
    //   1485: aload_1
    //   1486: new 378	android/widget/LinearLayout$LayoutParams
    //   1489: dup
    //   1490: iconst_m1
    //   1491: bipush -2
    //   1493: fconst_1
    //   1494: invokespecial 384	android/widget/LinearLayout$LayoutParams:<init>	(IIF)V
    //   1497: invokevirtual 114	android/widget/RelativeLayout:setLayoutParams	(Landroid/view/ViewGroup$LayoutParams;)V
    //   1500: aload_0
    //   1501: new 445	android/widget/ProgressBar
    //   1504: dup
    //   1505: aload_0
    //   1506: aconst_null
    //   1507: ldc_w 446
    //   1510: invokespecial 449	android/widget/ProgressBar:<init>	(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    //   1513: putfield 46	com/explodingbarrel/webview/WebViewFullScreenActivity:WebViewProgress	Landroid/widget/ProgressBar;
    //   1516: new 79	android/widget/RelativeLayout$LayoutParams
    //   1519: dup
    //   1520: bipush -2
    //   1522: bipush -2
    //   1524: invokespecial 82	android/widget/RelativeLayout$LayoutParams:<init>	(II)V
    //   1527: astore 17
    //   1529: aload 17
    //   1531: bipush 13
    //   1533: invokevirtual 85	android/widget/RelativeLayout$LayoutParams:addRule	(I)V
    //   1536: aload_0
    //   1537: getfield 46	com/explodingbarrel/webview/WebViewFullScreenActivity:WebViewProgress	Landroid/widget/ProgressBar;
    //   1540: aload 17
    //   1542: invokevirtual 450	android/widget/ProgressBar:setLayoutParams	(Landroid/view/ViewGroup$LayoutParams;)V
    //   1545: aload_0
    //   1546: new 201	android/webkit/WebView
    //   1549: dup
    //   1550: aload_0
    //   1551: invokespecial 451	android/webkit/WebView:<init>	(Landroid/content/Context;)V
    //   1554: putfield 44	com/explodingbarrel/webview/WebViewFullScreenActivity:FullScreenWebView	Landroid/webkit/WebView;
    //   1557: aload_0
    //   1558: getfield 44	com/explodingbarrel/webview/WebViewFullScreenActivity:FullScreenWebView	Landroid/webkit/WebView;
    //   1561: iload_3
    //   1562: invokevirtual 452	android/webkit/WebView:setBackgroundColor	(I)V
    //   1565: aload_0
    //   1566: getfield 44	com/explodingbarrel/webview/WebViewFullScreenActivity:FullScreenWebView	Landroid/webkit/WebView;
    //   1569: new 378	android/widget/LinearLayout$LayoutParams
    //   1572: dup
    //   1573: iconst_m1
    //   1574: iconst_m1
    //   1575: invokespecial 379	android/widget/LinearLayout$LayoutParams:<init>	(II)V
    //   1578: invokevirtual 453	android/webkit/WebView:setLayoutParams	(Landroid/view/ViewGroup$LayoutParams;)V
    //   1581: aload_0
    //   1582: getfield 44	com/explodingbarrel/webview/WebViewFullScreenActivity:FullScreenWebView	Landroid/webkit/WebView;
    //   1585: new 15	com/explodingbarrel/webview/WebViewFullScreenActivity$InternalWebChromeClient
    //   1588: dup
    //   1589: aload_0
    //   1590: aload_0
    //   1591: getfield 46	com/explodingbarrel/webview/WebViewFullScreenActivity:WebViewProgress	Landroid/widget/ProgressBar;
    //   1594: invokespecial 456	com/explodingbarrel/webview/WebViewFullScreenActivity$InternalWebChromeClient:<init>	(Lcom/explodingbarrel/webview/WebViewFullScreenActivity;Landroid/widget/ProgressBar;)V
    //   1597: invokevirtual 460	android/webkit/WebView:setWebChromeClient	(Landroid/webkit/WebChromeClient;)V
    //   1600: aload_0
    //   1601: getfield 44	com/explodingbarrel/webview/WebViewFullScreenActivity:FullScreenWebView	Landroid/webkit/WebView;
    //   1604: new 18	com/explodingbarrel/webview/WebViewFullScreenActivity$InternalWebViewClient
    //   1607: dup
    //   1608: aload_0
    //   1609: aconst_null
    //   1610: invokespecial 463	com/explodingbarrel/webview/WebViewFullScreenActivity$InternalWebViewClient:<init>	(Lcom/explodingbarrel/webview/WebViewFullScreenActivity;Lcom/explodingbarrel/webview/WebViewFullScreenActivity$1;)V
    //   1613: invokevirtual 467	android/webkit/WebView:setWebViewClient	(Landroid/webkit/WebViewClient;)V
    //   1616: aload_0
    //   1617: getfield 44	com/explodingbarrel/webview/WebViewFullScreenActivity:FullScreenWebView	Landroid/webkit/WebView;
    //   1620: invokevirtual 471	android/webkit/WebView:getSettings	()Landroid/webkit/WebSettings;
    //   1623: astore 17
    //   1625: aload 17
    //   1627: iconst_1
    //   1628: invokevirtual 477	android/webkit/WebSettings:setJavaScriptEnabled	(Z)V
    //   1631: aload 17
    //   1633: iconst_1
    //   1634: invokevirtual 480	android/webkit/WebSettings:setLoadWithOverviewMode	(Z)V
    //   1637: aload 17
    //   1639: iconst_1
    //   1640: invokevirtual 483	android/webkit/WebSettings:setUseWideViewPort	(Z)V
    //   1643: aload_0
    //   1644: getfield 44	com/explodingbarrel/webview/WebViewFullScreenActivity:FullScreenWebView	Landroid/webkit/WebView;
    //   1647: new 12	com/explodingbarrel/webview/WebViewFullScreenActivity$BadgeJavaScriptInterface
    //   1650: dup
    //   1651: aload_0
    //   1652: aload_0
    //   1653: invokespecial 486	com/explodingbarrel/webview/WebViewFullScreenActivity$BadgeJavaScriptInterface:<init>	(Lcom/explodingbarrel/webview/WebViewFullScreenActivity;Lcom/explodingbarrel/webview/WebViewFullScreenActivity;)V
    //   1656: ldc_w 488
    //   1659: invokevirtual 492	android/webkit/WebView:addJavascriptInterface	(Ljava/lang/Object;Ljava/lang/String;)V
    //   1662: aload_0
    //   1663: getfield 44	com/explodingbarrel/webview/WebViewFullScreenActivity:FullScreenWebView	Landroid/webkit/WebView;
    //   1666: new 24	com/explodingbarrel/webview/WebViewFullScreenActivity$WebViewScriptInterface
    //   1669: dup
    //   1670: aload_0
    //   1671: aload_0
    //   1672: invokespecial 493	com/explodingbarrel/webview/WebViewFullScreenActivity$WebViewScriptInterface:<init>	(Lcom/explodingbarrel/webview/WebViewFullScreenActivity;Lcom/explodingbarrel/webview/WebViewFullScreenActivity;)V
    //   1675: ldc 29
    //   1677: invokevirtual 492	android/webkit/WebView:addJavascriptInterface	(Ljava/lang/Object;Ljava/lang/String;)V
    //   1680: iload 7
    //   1682: i2f
    //   1683: aload 27
    //   1685: getfield 496	android/util/DisplayMetrics:widthPixels	I
    //   1688: i2f
    //   1689: fdiv
    //   1690: fstore_2
    //   1691: aload_0
    //   1692: getfield 44	com/explodingbarrel/webview/WebViewFullScreenActivity:FullScreenWebView	Landroid/webkit/WebView;
    //   1695: ldc_w 497
    //   1698: fload_2
    //   1699: fmul
    //   1700: f2i
    //   1701: invokevirtual 500	android/webkit/WebView:setInitialScale	(I)V
    //   1704: aload_0
    //   1705: aload 26
    //   1707: invokespecial 157	com/explodingbarrel/webview/WebViewFullScreenActivity:loadPage	(Ljava/lang/String;)V
    //   1710: aload_1
    //   1711: aload_0
    //   1712: getfield 44	com/explodingbarrel/webview/WebViewFullScreenActivity:FullScreenWebView	Landroid/webkit/WebView;
    //   1715: invokevirtual 100	android/widget/RelativeLayout:addView	(Landroid/view/View;)V
    //   1718: aload_1
    //   1719: aload_0
    //   1720: getfield 46	com/explodingbarrel/webview/WebViewFullScreenActivity:WebViewProgress	Landroid/widget/ProgressBar;
    //   1723: invokevirtual 100	android/widget/RelativeLayout:addView	(Landroid/view/View;)V
    //   1726: iload 11
    //   1728: iconst_1
    //   1729: if_icmpne +42 -> 1771
    //   1732: aload 24
    //   1734: aload 20
    //   1736: invokevirtual 387	android/widget/LinearLayout:addView	(Landroid/view/View;)V
    //   1739: aload 24
    //   1741: aload_1
    //   1742: invokevirtual 387	android/widget/LinearLayout:addView	(Landroid/view/View;)V
    //   1745: aload 22
    //   1747: aload 24
    //   1749: invokevirtual 100	android/widget/RelativeLayout:addView	(Landroid/view/View;)V
    //   1752: aload_0
    //   1753: aload 22
    //   1755: aload 23
    //   1757: invokevirtual 504	com/explodingbarrel/webview/WebViewFullScreenActivity:setContentView	(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V
    //   1760: ldc -33
    //   1762: ldc_w 506
    //   1765: ldc -29
    //   1767: invokestatic 233	com/unity3d/player/UnityPlayer:UnitySendMessage	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1770: return
    //   1771: aload 24
    //   1773: aload_1
    //   1774: invokevirtual 387	android/widget/LinearLayout:addView	(Landroid/view/View;)V
    //   1777: aload 24
    //   1779: aload 20
    //   1781: invokevirtual 387	android/widget/LinearLayout:addView	(Landroid/view/View;)V
    //   1784: goto -39 -> 1745
    //   1787: astore 20
    //   1789: aload 25
    //   1791: astore 19
    //   1793: goto -388 -> 1405
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1796	0	this	WebViewFullScreenActivity
    //   0	1796	1	paramBundle	android.os.Bundle
    //   748	951	2	f	float
    //   168	1394	3	i	int
    //   204	1234	4	j	int
    //   164	1272	5	k	int
    //   208	1157	6	m	int
    //   171	1510	7	n	int
    //   118	1284	8	i1	int
    //   123	1272	9	i2	int
    //   146	1245	10	i3	int
    //   126	1604	11	i4	int
    //   135	618	12	i5	int
    //   141	592	13	bool1	boolean
    //   132	594	14	bool2	boolean
    //   138	312	15	i6	int
    //   129	248	16	i7	int
    //   179	1459	17	localObject1	Object
    //   175	1265	18	localObject2	Object
    //   113	1679	19	localObject3	Object
    //   220	1131	20	localObject4	Object
    //   1377	403	20	localJSONException1	org.json.JSONException
    //   1787	1	20	localJSONException2	org.json.JSONException
    //   149	1293	21	localObject5	Object
    //   216	1538	22	localObject6	Object
    //   109	1647	23	localObject7	Object
    //   105	1673	24	localObject8	Object
    //   154	1636	25	localObject9	Object
    //   41	1665	26	str	String
    //   12	1672	27	localDisplayMetrics	android.util.DisplayMetrics
    //   200	1108	28	localObject10	Object
    //   986	313	29	localObject11	Object
    //   1159	10	30	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   151	162	1377	org/json/JSONException
    //   916	926	1446	org/json/JSONException
    //   931	966	1446	org/json/JSONException
    //   969	1008	1446	org/json/JSONException
    //   1023	1056	1446	org/json/JSONException
    //   1065	1075	1446	org/json/JSONException
    //   1080	1110	1446	org/json/JSONException
    //   1124	1306	1446	org/json/JSONException
    //   1306	1357	1446	org/json/JSONException
    //   1363	1371	1446	org/json/JSONException
    //   192	202	1787	org/json/JSONException
    //   257	267	1787	org/json/JSONException
    //   297	307	1787	org/json/JSONException
    //   341	350	1787	org/json/JSONException
    //   380	390	1787	org/json/JSONException
    //   420	430	1787	org/json/JSONException
    //   460	470	1787	org/json/JSONException
    //   504	516	1787	org/json/JSONException
    //   546	556	1787	org/json/JSONException
    //   586	595	1787	org/json/JSONException
    //   625	635	1787	org/json/JSONException
    //   669	681	1787	org/json/JSONException
    //   711	721	1787	org/json/JSONException
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramKeyEvent.getAction() == 0) {
      switch (paramInt)
      {
      }
    }
    for (boolean bool = super.onKeyDown(paramInt, paramKeyEvent);; bool = true)
    {
      return bool;
      onBackPressed();
    }
  }
  
  protected void onPause()
  {
    super.onPause();
    terminateWebView();
  }
  
  public class BadgeJavaScriptInterface
  {
    WebViewFullScreenActivity WebView;
    
    BadgeJavaScriptInterface(WebViewFullScreenActivity paramWebViewFullScreenActivity)
    {
      this.WebView = paramWebViewFullScreenActivity;
    }
    
    @JavascriptInterface
    public void setBadge(int paramInt, String paramString)
    {
      this.WebView.updateBadge(paramInt, paramString);
    }
  }
  
  private class InternalWebChromeClient
    extends WebChromeClient
  {
    private ProgressBar WebViewProgress = null;
    
    public InternalWebChromeClient(ProgressBar paramProgressBar)
    {
      this.WebViewProgress = paramProgressBar;
    }
    
    public void onProgressChanged(WebView paramWebView, int paramInt)
    {
      if (paramInt == 100) {
        this.WebViewProgress.setVisibility(4);
      }
      for (;;)
      {
        return;
        this.WebViewProgress.setVisibility(0);
      }
    }
  }
  
  private class InternalWebViewClient
    extends WebViewClient
  {
    private InternalWebViewClient() {}
    
    public void onScaleChanged(WebView paramWebView, float paramFloat1, float paramFloat2) {}
    
    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
      return false;
    }
  }
  
  class TabView
  {
    public int BadgeId = -1;
    public RelativeLayout BadgeLayout = null;
    public TextView BadgeText = null;
    public String Image = null;
    public String ImageSelected = null;
    public ImageButton TabImage = null;
    public String Url = null;
    
    public TabView(String paramString1, String paramString2, String paramString3, ImageButton paramImageButton, int paramInt, RelativeLayout paramRelativeLayout, TextView paramTextView)
    {
      this.Url = paramString1;
      this.Image = paramString2;
      this.ImageSelected = paramString3;
      this.TabImage = paramImageButton;
      this.BadgeId = paramInt;
      this.BadgeLayout = paramRelativeLayout;
      this.BadgeText = paramTextView;
    }
  }
  
  public class WebViewScriptInterface
  {
    WebViewFullScreenActivity WebView;
    
    WebViewScriptInterface(WebViewFullScreenActivity paramWebViewFullScreenActivity)
    {
      this.WebView = paramWebViewFullScreenActivity;
    }
    
    @JavascriptInterface
    public void close()
    {
      this.WebView.terminateWebView();
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\explodingbarrel\webview\WebViewFullScreenActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */