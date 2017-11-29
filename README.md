# campaign-sdk
Loyagram Android SDK

### Getting started

In your build.gradle:

```java
dependencies {
	compile 'com.loyagram.android:campaign-sdk:0.0.4'
}
```

### Usage

Firstly initialize the **api key** and **access secret**. In you Application class add the following lines of codes.
```java
import com.loyagram.android.campaignsdk.globals.LoyagramCampaignSdk;

public class LoyagramApplication extends Application {
    @Override public void onCreate() {
        super.onCreate();
        String apiKey = "############9bbb0";
        String accessSecret = "############b6359";
        LoyagramCampaignSdk.init(apiKey, accessSecret);
    }
}

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
###### parameters:-
  - context:- Activity's context
  - campaignId:- String campaignId
  - primaryColor:- Theme color(Hexadecimal)
  - customAttributes:- CustomAttributes of type HashMap

**2. Show as a dialog**

To run campaigns as a DialogFragment
```java
import com.loyagram.android.campaignsdk.campaignbase.LoyagramCampaignManager;

LoyagramCampaignManager.showAsDialog(context, campaignId, primaryColor, customAttributes);
```
###### parameters:-
  - context:- Activity's context
  - campaignId:- String campaignId
  - primaryColor:- Theme color(Hexadecimal)
  - customAttributes:- CustomAttributes of type HashMap

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

  To set call back listener, add the following code in your activity class .
  ```java
  LoyagramCampaignView loyagramCampaignView = findViewById(R.id.loaygramCampaignView);
        loyagramCampaignView.setLoyagramCampaignListener(new CampaignCallback() {
            @Override
            public void onSuccess() {
                Log.i("Campaign", "Successfully completed campaign");
            }

            @Override
            public void onError() {
                Log.i("Campaign", "Failed to submit campaign");
            }
        });
  ```

**4. Animate from bottom.**

In this method campaign view will be animated from bottom. The button used to invoke campaign will be hide once the campaign view appeared.
```java
LinearLayout widgetContainer = (LinearLayout) findViewById(R.id.campaignContainer);
LoyagramCampaignManager.showFromBottom(context, campaignId, primaryColor, campaignContainer, campaignButton, customAttributes);
```
###### Parameters:-
  - context:- Activity's context
  - campaignId:- String campaignId
  - primaryColor:- Theme color(Hexadecimal)
  - campaignContainer:- Campaign view container.
  - campaignButton :- Button to start campaign
  - customAttributes:- CustomAttributes of type Hashmap

  ### Advanced Usage
  - To add callbacks for successful or unsuccessful completion of campaigns.
  ```java
  import com.loyagram.android.campaignsdk.campaigncallback.CampaignCallback;
  import com.loyagram.android.campaignsdk.campaignbase.LoyagramCampaignManager;
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
- To set custom attributes.

```java
import com.loyagram.android.campaignsdk.campaignbase.LoyagramCampaignManager;
HashMap<String, String> customAttributes = new HashMap<>();
customAttributes.put("userId", "123456");
customAttributes.put("productId", "1234");
LoyagramCampaignManager.showAsActivity(context, campaignId, primaryColor, customAttributes);
```
- Set attributes using the addAttribute() method.
```java
LoyagramCampaignManager.addAttribute(key1, value1);
LoyagramCampaignManager.addAttribute(key2, value2);

```

- Set attributes using the addAttributes() method.
```java
import com.loyagram.android.campaignsdk.campaignbase.LoyagramCampaignManager;
HashMap<String, String> customAttributes = new HashMap<>();
customAttributes.put("userId", "123456");
customAttributes.put("productId", "1234");
LoyagramCampaignManager.addAttributes(customAttributes);

```

### License

Loyagram campaign-sdk is released under the [Apache 2.0 license](LICENSE).

```
Copyright 2017 DataFactors Software India Pvt. Ltd.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 ```