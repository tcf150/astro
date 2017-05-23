package com.zachary.astro.ui.Main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.zachary.astro.R;
import com.zachary.astro.base.BaseAppCompatActivity;
import com.zachary.astro.model.annotation.SortType;
import com.zachary.astro.ui.Guide.GuideActivity;

public class MainActivity extends BaseAppCompatActivity {
    private MainPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setupContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void setupUI() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MainFragment mainFragment = new MainFragment();
        presenter = new MainPresenter(mainFragment);

        getSupportFragmentManager().beginTransaction().add(R.id.flContainer,mainFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_logout:
                logout();
                return true;
            case R.id.menu_favourite:
                presenter.onFavouriteMenuClick();
                return true;
            case R.id.menu_guide:
                Intent intent = new Intent(this, GuideActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_sort_name:
                presenter.updateChannelSort(SortType.ByName);
                return true;
            case R.id.menu_sort_number:
                presenter.updateChannelSort(SortType.ByNumber);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void logout(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.logout);
        builder.setMessage(R.string.logout_desc);
        builder.setPositiveButton(R.string.no, null);
        builder.setNegativeButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                presenter.logout();
            }
        });
        builder.create().show();
    }
}
