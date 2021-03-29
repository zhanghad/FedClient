package com.fedclient.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;


import com.fedclient.constants.UrlConstants;
import com.fedclient.domain.TaskPublished;
import com.fedclient.R;
import com.fedclient.ui.adapter.AppFragment;
import com.fedclient.ui.adapter.TaskPublishedAdapter;
import com.fedclient.ui.fragment.Config_Fragment;
import com.fedclient.ui.fragment.History_Fragment;
import com.fedclient.ui.fragment.Home_Fragment;
import com.fedclient.ui.fragment.Me_Fragment;
import com.fedclient.ui.fragment.Now_Fragment;
import com.fedclient.util.HttpUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Response;

/**
 * 主页,用于展示已发布任务的信息
 */
public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";
    private ViewPager viewPager;
    private MenuItem menuItem;
    private BottomNavigationView bottomNavigationView;

    public ListView lv_project;
    private TaskPublishedAdapter list_item;
    List<TaskPublished> taskPublisheds= new ArrayList<TaskPublished>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        viewPager = (ViewPager) findViewById(R.id.mainview);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bv_home_navigation);
        //BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.menu_home:
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.home_history:
                                viewPager.setCurrentItem(1);
                                break;
                            case R.id.home_now:
                                viewPager.setCurrentItem(2);
                                break;
                            case R.id.home_config:
                                viewPager.setCurrentItem(3);
                                break;
                            case R.id.home_me:
                                viewPager.setCurrentItem(4);
                                break;
                        }
                        return false;
                    }
                }
        );

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                menuItem = bottomNavigationView.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        //禁止ViewPager滑动
//        viewPager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
 //           public boolean onTouch(View v, MotionEvent event) {
 //               return true;
 //           }
 //       });

        setupViewPager(viewPager);

        initComponents();
    }

    /**
     * 初始化组件及数据
     */
    private void initComponents(){
//        list.clear();
        lv_project = (ListView)findViewById(R.id.lv_project);


        /**
         * 从服务端获取数据
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    HttpUrl url =HttpUrl.parse(UrlConstants.URL_TASK_GET_ALL_TASK).newBuilder()
                            .build();

                    HttpUtil.sendGet(url.toString(), new Callback() {
                        @Override
                        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                            String data = response.body().string();

                            Log.i(TAG, "onResponse: "+data);
                            Type userListType = new TypeToken<List<TaskPublished>>(){}.getType();
                            taskPublisheds= new Gson().fromJson(data,userListType);

                            if (taskPublisheds.isEmpty()){
                                Log.e(TAG, "onResponse: "+"taskPublisheds isEmpty");
                                taskPublisheds= new ArrayList<TaskPublished>();
                            }

                            Log.d(TAG, "onResponse: "+taskPublisheds.toString());

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Log.d(TAG, "onCreate: "+taskPublisheds.toString());
                                    //getApplicationContext()
                                    list_item = new TaskPublishedAdapter(HomeActivity.this,taskPublisheds,R.layout.view_tplist);
                                    lv_project.setAdapter(list_item);
                                }
                            });

                        }
                        @Override
                        public void onFailure(@NotNull Call call, @NotNull IOException e) {
                            Log.e(TAG, "onFailure: ", e);
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(BaseFragment.newInstance("首页"));
        adapter.addFragment(BaseFragment.newInstance("搜索"));
        adapter.addFragment(BaseFragment.newInstance("我的"));
        adapter.addFragment(BaseFragment.newInstance("主页"));
        viewPager.setAdapter(adapter);
    }
}