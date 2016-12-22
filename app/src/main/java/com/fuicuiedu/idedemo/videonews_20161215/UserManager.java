package com.fuicuiedu.idedemo.videonews_20161215;

/**
 * 很简单的用户管理类。
 */
public class UserManager {

    private static UserManager sInstance;

    public static UserManager getInstance(){
        if (sInstance == null) {
            sInstance = new UserManager();
        }
        return sInstance;
    }

    private String username;
    private String objectId;

    private UserManager(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public boolean isOffline(){
        return username == null || objectId == null;
    }

    public void clear(){
        username = null;
        objectId = null;
    }
}
