package com.example.pruebarestapi;

import android.app.Application;

import com.example.pruebarestapi.model.API;
import com.example.pruebarestapi.model.APIListener;
import com.example.pruebarestapi.model.WebAPI;

public class Model {
    private static Model sInstance = null;

    private final API mApi;
    private User mUser;

    public static Model getInstance(Application application){
        if(sInstance == null){
            sInstance = new Model(application);
        }
        return sInstance;
    }
    private final Application mApplication;

    private Model(Application application){
        mApplication = application;
        mApi = new WebAPI(mApplication);
    }
    public Application getApplication(){ return mApplication;}


    public void login(String email, String password, final APIListener listener) {
        mApi.login(email, password, listener);
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User mUser) {
        this.mUser = mUser;
    }
}
