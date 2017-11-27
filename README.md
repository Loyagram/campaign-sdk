# campaign-sdk
Loyagram Android SDK

## Getting started

In your build.gradle:

```java
dependencies {
	compile 'com.loyagram.android:campaign-sdk:0.0.4'
}
```

## Usage

Firstly initialize the **api key** and **access secret**.
```java
import com.loyagram.android.campaignsdk.globals.LoyagramCampaignSdk;

LoyagramCampaignSdk.init(apiKey, accessSecret);
```

Loyagram campaign sdk can be implemented using any of the following methods:

1. Show as a new activity
2. Show as a dialog
3. Show as a custom view
4. Animate from bottom

**1. Show as a new activity**

To run campaigns as a new activity.
```java
import com.loyagram.android.campaignsdk.campaignbase.LoyagramCampaignManager;

LoyagramCampaignManager.showAsActivity(context, campaignId, primaryColor, customAttributes);
```
###### Parametres:-
  - context:- Activity's context
  - campaignId:- String campaignId
  - primaryColor:- Theme color(Hexadecimal)
  - customAtributes:- CustomAttributes of type Hasmap
  
**2. Show as a dialog**

To run campains as a DialogFragment
```java
import com.loyagram.android.campaignsdk.campaignbase.LoyagramCampaignManager;

LoyagramCampaignManager.showAsDialog(context, campaignId, primaryColor, customAttributes);
```
###### Parametres:-
  - context:- Activity's context
  - campaignId:- String campaignId
  - primaryColor:- Theme color(Hexadecimal)
  - customAtributes:- CustomAttributes of type Hasmap
  
**3. Show as a custom view**
```java
<com.loyagram.android.campaignsdk.ui.LoyagramCampaignView
        android:id="@+id/loaygramCampaignView"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:layout_width="match_parent"
        android:layout_height="330dp"
        android:layout_gravity="center"
        app:campaignColor="#1abc9c"
        app:campaignId="1020-49bd1a50-1c51-445d-8043-fa8f907a0078">
    </com.loyagram.android.campaignsdk.ui.LoyagramCampaignView>
```
###### Attributes:-
  - campaignColor:- Theme color(Hexadecimal)
  - campaignId:- String campaignId
  
**4. Animate from bottom.**

In this method campaign view will be animated from bottom. The button used to invoke campaign will be hide once the campaign view appeared.
```java
LinearLayout widgetContainer = (LinearLayout) findViewById(R.id.campaignContainer);
LoyagramCampaignManager.showFromBottom(context, campaignId, primaryColor, campaignContainer, campaignButton, customAttributes);
```
###### Parametres:-
  - context:- Activity's context
  - campaignId:- String campaignId
  - primaryColor:- Theme color(Hexadecimal)
  - campaignContainer:- Campaign view container.
  - campaignButton :- Button to start campaign
  - customAtributes:- CustomAttributes of type Hasmap
  
  ## Advanced Usage
  To add callback listner for successful or unsuccessful completion of campaigns.
  ```java
  import com.loyagram.android.campaignsdk.campaigncallback.CampaignCallback;
  
  LoyagramCampaignManager.showAsActivity(context, campaignId, primaryColor, customAttributes, new CampaignCallback() {
            @Override
            public void onSuccess() {
                Log.i("Campaign", " Campaign completed successfully");
            }

            @Override
            public void onError() {
                Log.i("Campaign", "Failed to submit campaign");
            }
  });
```

  
  
