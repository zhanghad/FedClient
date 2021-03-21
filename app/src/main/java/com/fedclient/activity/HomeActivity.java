package com.fedclient.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.fedclient.trash.Consts;
import com.fedclient.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 主页
 */
public class HomeActivity extends AppCompatActivity {
    private RadioButton RB_history;
    private RadioButton RB_now;
    private RadioButton RB_shezhi;
    private RadioButton RB_mine;

    public ListView lv_project;
    public ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();

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
                Intent intent=new Intent(HomeActivity.this, TrainStatusActivity.class);
                startActivity(intent);
                finish();
            }
        });
        RB_shezhi = (RadioButton) findViewById (R.id.RB_shezhi);
        RB_shezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this, ConfigActivity.class);
                startActivity(intent);
                finish();
            }
        });
        RB_mine = (RadioButton) findViewById (R.id.RB_mine);
        RB_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this, ClientInfoActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private HomeActivity.Mybaseadapter list_item;

    private void init(){
        list.clear();
        lv_project = (ListView)findViewById(R.id.lv_project);
        list_item = new HomeActivity.Mybaseadapter();
        lv_project.setAdapter(list_item);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    //服务端访问地址
                    Request request = new Request
                            .Builder()
                            .url(Consts.URL_Login).build();
                    Response response = client.newCall(request).execute();
                    //得到服务器返回的数据后，调用parseJSONWithJSONObject进行解析
                    String responseData = response.body().string();
                    parseJSONWithJSONObject(responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseJSONWithJSONObject(String jsonData) {
        if (jsonData != null) {
            try {
                JSONObject jsonObject = new JSONObject(jsonData);
                //获取数据中的code值，如果是0则正确
                String resultCode = jsonObject.getString("code");
                if (resultCode.equals("0")) {
                    //获取到json数据中里的data内容
                    JSONArray resultJsonArray = jsonObject.getJSONArray("data");
                    Log.d("HomeActivity", "data+++" + resultJsonArray);
                    for (int i = 0; i < resultJsonArray.length(); i++) {
                        //循环遍历，获取json数据中data数组里的内容
                        JSONObject Object = resultJsonArray.getJSONObject(i);
                        Map<String, Object> map = new HashMap<String, Object>();
                        try {
                            String Testname = Object.getString("Testname");

                            map.put("Testname", Testname);

                            //保存到ArrayList集合中
                            list.add(map);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    handler.sendEmptyMessageDelayed(1, 100);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    list_item.notifyDataSetChanged();
                    break;
            }
        }
    };


    //listview适配器
    public class Mybaseadapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            HistoryActivity.ViewHolder viewHolder = new HistoryActivity.ViewHolder();

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.list_choose,null);
                viewHolder.Testname = (TextView) convertView.findViewById(R.id.tvTestname);

                convertView.setTag(viewHolder);

            } else {
                viewHolder = (HistoryActivity.ViewHolder) convertView.getTag();
            }

            viewHolder.Testname.setText("项目名："+list.get(position).get("Testname").toString());

            return convertView;
        }
    }



}