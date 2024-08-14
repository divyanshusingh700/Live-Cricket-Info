package com.truecodes.worldcuptv;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import com.facebook.ads.AudienceNetworkAds;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.ironsource.mediationsdk.ISBannerSize;
import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.IronSourceBannerLayout;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.sdk.InterstitialListener;

public class AdManager {
    Activity activity;
    Context context;
    private IronSourceBannerLayout ironSourceBannerLayout;

    public AdManager(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    public void loadBanner(FrameLayout frameLayout) {
        MobileAds.initialize(this.context, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AudienceNetworkAds.initialize(this.context);
        IronSource.init(this.activity, "19cac3add");
        IronSource.setMetaData("FACEBOOK_IS_CacheFlag", "IMAGE");
        IronSource.setMetaData("Pangle_COPPA", "0");
        this.ironSourceBannerLayout = IronSource.createBanner(this.activity, ISBannerSize.SMART);
        frameLayout.addView(this.ironSourceBannerLayout, 0, new FrameLayout.LayoutParams(-1, -2));
        IronSource.loadBanner(this.ironSourceBannerLayout, "DefaultBanner");
        this.ironSourceBannerLayout.setVisibility(View.VISIBLE);
        if (!IronSource.isInterstitialReady()) {
            loadInterstitialAds();
        }
    }

    public void showInterstitial() {
        if (IronSource.isInterstitialReady()) {
            IronSource.showInterstitial("DefaultInterstitial");
        } else {
            loadInterstitialAds();
        }
    }

    public void loadInterstitialAds() {
        IronSource.loadInterstitial();
        IronSource.setInterstitialListener(new InterstitialListener() {
            @Override
            public void onInterstitialAdClicked() {
            }

            @Override
            public void onInterstitialAdOpened() {
            }

            @Override
            public void onInterstitialAdShowSucceeded() {
            }

            @Override
            public void onInterstitialAdReady() {
                Log.d("ironSourceError", "onInterstitialAdReady: ");
            }

            @Override
            public void onInterstitialAdLoadFailed(IronSourceError ironSourceError) {
                Log.d("ironSourceError", ironSourceError.toString());
            }

            @Override
            public void onInterstitialAdClosed() {
                IronSource.loadInterstitial();
            }

            @Override
            public void onInterstitialAdShowFailed(IronSourceError ironSourceError) {
                Log.d("ironSourceError", ironSourceError.getErrorMessage());
            }
        });
    }

    public void destroyBanner() {
        IronSource.destroyBanner(this.ironSourceBannerLayout);
    }
}