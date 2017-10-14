package com.loyagram.android.campaignsdk.globals;

import java.util.HashMap;

/**
 * Created by user1 on 17/5/17.
 */

public class LoyagramAttributes {
    public static LoyagramAttributes instance = null;
    HashMap<String, String> attributes = null;

    public static LoyagramAttributes getInstance() {
        if (instance == null) {
            instance = new LoyagramAttributes();
        }
        return instance;
    }

    public HashMap<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(HashMap<String, String> attributes) {
        this.attributes = attributes;
    }

    public void setAttribute(String key, String value) {
        if (this.attributes == null) {
            this.attributes = new HashMap<>();
            this.attributes.put(key, value);
            return;
        }
        this.attributes.put(key, value);
    }
}
