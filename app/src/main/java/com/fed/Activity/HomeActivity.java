package com.fed.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import androidx.appcompat.app.AppCompatActivity;
import com.fedclient.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {
    private RadioButton RB_renwuguanli;
    private RadioButton RB_lishi;
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
        requestWindowFeature(Window.FEATURE_NO_TITLE);
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

        RB_renwuguanli = (RadioButton) findViewById (R.id.RB_renwuguanli);
        RB_renwuguanli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
        RB_lishi = (RadioButton) findViewById (R.id.RB_lishi);
        RB_lishi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,HistoryActivity.class);
                startActivity(intent);
            }
        });
        RB_now = (RadioButton) findViewById (R.id.RB_now);
        RB_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,NowActivity.class);
                startActivity(intent);
            }
        });
        RB_shezhi = (RadioButton) findViewById (R.id.RB_shezhi);
        RB_shezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,ShezhiActivity.class);
                startActivity(intent);
            }
        });
        RB_mine = (RadioButton) findViewById (R.id.RB_mine);
        RB_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,MineActivity.class);
                startActivity(intent);
            }
        });
        
    }
}