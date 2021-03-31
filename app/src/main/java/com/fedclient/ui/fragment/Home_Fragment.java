package com.fedclient.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.fedclient.R;
import com.fedclient.constants.UrlConstants;
import com.fedclient.domain.TaskPublished;
import com.fedclient.ui.activity.HistoryActivity;
import com.fedclient.ui.activity.HomeActivity;
import com.fedclient.ui.activity.LoginActivity;
import com.fedclient.ui.activity.RegisterActivity;
import com.fedclient.ui.adapter.TaskPublishedAdapter;
import com.fedclient.util.HttpUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Response;

public class Home_Fragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        return view;
    }


}
