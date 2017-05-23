package com.zachary.astro.ui.Main;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.zachary.astro.R;
import com.zachary.astro.base.BaseAppCompatActivity;
import com.zachary.astro.base.BaseFragment;
import com.zachary.astro.data.UserManager;
import com.zachary.astro.model.ChannelList;
import com.zachary.astro.ui.Favourite.FavouriteActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 22/5/2017.
 */

public class MainFragment extends BaseFragment implements MainContract.View,ChannelAdapter.OnFavouriteClickListener{
    private final static int RC_SIGN_IN = 125;
    private final static int PERMISSION_CODE = 123;
    private MainContract.Presenter presenter;

    private RecyclerView rvChannel;
    private ChannelAdapter adapter;
    private ProgressDialog dialog;

    private CallbackManager callbackManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callbackManager = CallbackManager.Factory.create();
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main,container,false);

        dialog = new ProgressDialog(getContext());
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage(getString(R.string.loading_message));
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);

        rvChannel = getViewByView(rootView,R.id.rvChannel);

        //setup layout manager for recyclerview
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvChannel.setLayoutManager(linearLayoutManager);

        //add divider for recyclerview
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), linearLayoutManager.getOrientation());
        rvChannel.addItemDecoration(dividerItemDecoration);

        adapter = new ChannelAdapter(new ArrayList<ChannelList>(0));
        adapter.setOnFavouriteClickListener(this);
        rvChannel.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Signed in successfully, show authenticated UI.
                GoogleSignInAccount acct = result.getSignInAccount();
                presenter.googleLogin(acct.getId());
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                performanceGoogleLogin();
            } else {
                showAppPermissionDialog(R.string.permission_request_user_detail);
            }
        }
    }

    @Override
    public void displayChannel(List<ChannelList> channelList) {
        adapter.replaceData(channelList);
    }

    @Override
    public void displayLoginDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.dialog_login_title);
        builder.setMessage(R.string.dialog_login_desc);
        builder.setNeutralButton(R.string.dialog_login_later, null);
        builder.setNegativeButton(R.string.dialog_login_facebook, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                presenter.facebookLogin(MainFragment.this,callbackManager);
            }
        });
        builder.setPositiveButton(R.string.dialog_login_google, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                googleLogin();
            }
        });
        builder.create().show();
    }

    @Override
    public void gotoFavourite() {
        Intent intent = new Intent(getContext(), FavouriteActivity.class);
        startActivity(intent);
    }

    public void googleLogin(){
        if (checkAppPermission(Manifest.permission.GET_ACCOUNTS)) {
            performanceGoogleLogin();
        }else{
            if (checkAppPermissionRationale(Manifest.permission.GET_ACCOUNTS)){
                showAppPermissionDialog(R.string.permission_request_user_detail);
            }else{
                requestAppPermission(Manifest.permission.GET_ACCOUNTS,PERMISSION_CODE);
            }
        }
    }

    public void performanceGoogleLogin(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .enableAutoManage(getActivity(), new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        showLoading(false);
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void showLoading(boolean show) {
        if (show){
            if (!dialog.isShowing()) dialog.show();
        }else{
            dialog.dismiss();
        }
    }

    @Override
    public void displayErrorToast(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFavouriteClick(ChannelList channel) {
        presenter.favouriteChannel(channel);
    }


}
