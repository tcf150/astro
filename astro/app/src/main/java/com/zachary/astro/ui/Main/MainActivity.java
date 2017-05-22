package com.zachary.astro.ui.Main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.zachary.astro.R;
import com.zachary.astro.base.BaseAppCompatActivity;

import java.util.Arrays;

public class MainActivity extends BaseAppCompatActivity {
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callbackManager = CallbackManager.Factory.create();
    }

    @Override
    protected void setupContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void setupUI() {

    }

    private void facebookLogin(){
        if (AccessToken.getCurrentAccessToken() != null){
            getFacebookGraph(AccessToken.getCurrentAccessToken());
            return;
        }

        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile","email"));
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                getFacebookGraph(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    private void getFacebookGraph(AccessToken accessToken){
        Bundle paramter = new Bundle();
        paramter.putString("fields","id,email,name");
        GraphRequest request = new GraphRequest(accessToken,"me",paramter, HttpMethod.GET,
                new GraphRequest.Callback(){

                    @Override
                    public void onCompleted(GraphResponse response) {
//                        response.getJSONObject().get("me").toString()
//                        response.getJSONObject().has("email")
//                        response.getJSONObject().get("id").toString()
                    }
                });
        request.executeAsync();
    }
}
