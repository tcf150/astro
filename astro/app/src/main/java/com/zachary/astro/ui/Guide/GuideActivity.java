package com.zachary.astro.ui.Guide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.zachary.astro.R;
import com.zachary.astro.base.BaseAppCompatActivity;
import com.zachary.astro.model.annotation.SortType;

public class GuideActivity extends BaseAppCompatActivity {
    private GuidePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void setupContentView() {
        setContentView(R.layout.activity_guide);
    }

    @Override
    protected void setupUI() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        GuideFragment guideFragment = new GuideFragment();
        presenter = new GuidePresenter(guideFragment);

        getSupportFragmentManager().beginTransaction().add(R.id.flContainer,guideFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.guide_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_sort_name:
                presenter.getEventList(SortType.ByName);
                return true;
            case R.id.menu_sort_number:
                presenter.getEventList(SortType.ByNumber);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
