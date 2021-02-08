package com.fed.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.fed.Details_history;
import com.fedclient.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HistoryActivity extends AppCompatActivity {
    private RadioButton RB_home;
    private RadioButton RB_now;
    private RadioButton RB_shezhi;
    private RadioButton RB_mine;

    private List<Map<String, Object>> lists;
    private SimpleAdapter adapter;
    private ListView choose_list;

    private String[] theme = {"project000", "project001", "project002"};
    private String[] content = {"图像分类", "图像识别", "情感分析"};
    private int[] imageViews = {R.drawable.c0, R.drawable.c1,R.drawable.c2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_history);

        lists = new ArrayList<>();
        for(int i=0;i< theme.length ;i++){
            Map<String, Object> map = new HashMap<>();
            map.put("h_image" , imageViews[i]);
            map.put("h_theme", theme[i]);
            map.put("h_content", content[i]);
            lists.add(map);
        }

        adapter = new SimpleAdapter(HistoryActivity.this, lists, R.layout.list_choose, new String[]{"h_image", "h_theme", "h_content"}, new int[]{R.id.image1, R.id.text1, R.id.text2});
        choose_list = (ListView) findViewById(R.id.history_list);
        choose_list.setAdapter(adapter);

        choose_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Bundle bundle = new Bundle();
                bundle.putInt("h_image", imageViews[arg2]);
                bundle.putString("h_theme", theme[arg2]);
                bundle.putString("h_content",content[arg2]);
                Intent intent = new Intent();
                intent.putExtras(bundle);
                intent.setClass(HistoryActivity.this, Details_history.class);
                Log.i("h_theme", theme[arg2]);
                startActivity(intent);
            }
        });
        

        RB_home = (RadioButton) findViewById (R.id.RB_home);
        RB_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HistoryActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
        RB_now = (RadioButton) findViewById (R.id.RB_now);
        RB_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HistoryActivity.this,NowActivity.class);
                startActivity(intent);
                finish();
            }
        });
        RB_shezhi = (RadioButton) findViewById (R.id.RB_shezhi);
        RB_shezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HistoryActivity.this,ShezhiActivity.class);
                startActivity(intent);
                finish();
            }
        });
        RB_mine = (RadioButton) findViewById (R.id.RB_mine);
        RB_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HistoryActivity.this,MineActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}