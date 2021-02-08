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

import com.fed.Details;
import com.fedclient.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {
    private RadioButton RB_history;
    private RadioButton RB_now;
    private RadioButton RB_shezhi;
    private RadioButton RB_mine;

    private List<Map<String, Object>> lists;
    private SimpleAdapter adapter;
    private ListView choose_list;

    private String[] theme = {"project000", "project001", "project002", "project003"};
    private String[] content = {"图像分类", "图像识别", "情感分析", "文本识别"};
    private int[] imageViews = {R.drawable.c0, R.drawable.c1,R.drawable.c2,R.drawable.c3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        lists = new ArrayList<>();
        for(int i=0;i< theme.length ;i++){
            Map<String, Object> map = new HashMap<>();
            map.put("image" , imageViews[i]);
            map.put("theme", theme[i]);
            map.put("content", content[i]);
            lists.add(map);
        }

        adapter = new SimpleAdapter(HomeActivity.this, lists, R.layout.list_choose, new String[]{"image", "theme", "content"}, new int[]{R.id.image1, R.id.text1, R.id.text2});
        choose_list = (ListView) findViewById(R.id.choose_list);
        choose_list.setAdapter(adapter);

        choose_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Bundle bundle = new Bundle();
                bundle.putInt("image", imageViews[arg2]);
                bundle.putString("theme", theme[arg2]);
                bundle.putString("content", content[arg2]);
                Intent intent = new Intent();
                intent.putExtras(bundle);
                intent.setClass(HomeActivity.this, Details.class);
                Log.i("theme", theme[arg2]);
                startActivity(intent);
            }
        });

        RB_history = (RadioButton) findViewById (R.id.RB_history);
        RB_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,HistoryActivity.class);
                startActivity(intent);
                finish();
            }
        });
        RB_now = (RadioButton) findViewById (R.id.RB_now);
        RB_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,NowActivity.class);
                startActivity(intent);
                finish();
            }
        });
        RB_shezhi = (RadioButton) findViewById (R.id.RB_shezhi);
        RB_shezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,ShezhiActivity.class);
                startActivity(intent);
                finish();
            }
        });
        RB_mine = (RadioButton) findViewById (R.id.RB_mine);
        RB_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,MineActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}