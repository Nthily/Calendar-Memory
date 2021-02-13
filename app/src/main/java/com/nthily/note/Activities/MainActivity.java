package com.nthily.note.Activities;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.nthily.note.Fragments.Schedule;
import com.nthily.note.Fragments.Calendar;
import com.nthily.note.Fragments.Setting;
import com.nthily.note.Fragments.Favorite;
import com.nthily.note.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FragmentManager fm;
    private FragmentTransaction ft;

    private ImageButton favorite, calendar, setting;

    private Favorite fav;
    private Calendar cal;
    private Setting set;
    private Schedule schedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { //设置状态栏透明
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }

        findByid();

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        initAllFragment();

    }

    private void findByid() {
        favorite = (ImageButton) this.findViewById(R.id.favorite_txt);
        calendar = (ImageButton) this.findViewById(R.id.calendar);
        setting = (ImageButton) this.findViewById(R.id.setting);

        favorite.setOnClickListener(this);
        calendar.setOnClickListener(this);
        setting.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch (v.getId()) {
            case R.id.favorite_txt:

                switchFragment(fav);
                favorite.setImageResource(R.drawable.ic_favorite_green_24dp);
                calendar.setImageResource(R.drawable.ic_date_range_white_24dp);
                setting.setImageResource(R.drawable.ic_settings);
                break;
            case R.id.calendar:

                switchFragment(cal);
                favorite.setImageResource(R.drawable.ic_favorite_white_24dp);
                calendar.setImageResource(R.drawable.ic_date_range_green_24dp);
                setting.setImageResource(R.drawable.ic_settings);
                break;
            case R.id.setting:

                switchFragment(set);
                favorite.setImageResource(R.drawable.ic_favorite_white_24dp);
                calendar.setImageResource(R.drawable.ic_date_range_white_24dp);
                setting.setImageResource(R.drawable.ic_settings_green);
                break;
        }
        ft.commit();
    }

    private void switchFragment(Fragment fragment) {
        for (Fragment test_fragment : fm.getFragments()) {
            if(test_fragment != fragment) {
                getSupportFragmentManager().beginTransaction().hide(test_fragment).commit();
            }
        }
        if(fragment == cal) getSupportFragmentManager().beginTransaction().show(schedule).commit();

        getSupportFragmentManager().beginTransaction().show(fragment).commit();
    }

    private void initAllFragment() {
        fav = new Favorite();
        cal = new Calendar();
        set = new Setting();
        schedule = new Schedule();
        getSupportFragmentManager().beginTransaction().add(R.id.main_frame, fav).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.main_frame, cal).hide(cal).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.main_frame, set).hide(set).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.cal_frame, schedule).commit();
    }

}