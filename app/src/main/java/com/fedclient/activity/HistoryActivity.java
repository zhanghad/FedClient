package com.fedclient.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
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
 * 用户参与历史记录页面
 */
public class HistoryActivity extends AppCompatActivity{
    private ImageView iv_back;
    public ListView lv_history;
    public ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    private Context context;
    private Mybaseadapter list_item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_history);
        initComponents();
        setListeners();
        init();
    }

    /**
     * 初始化组件
     */
    void initComponents(){
        iv_back = (ImageView) findViewById(R.id.iv_back);
    }

    /**
     * 设置监听
     */
    void setListeners() {
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    private void init(){
        list.clear();
        lv_history = (ListView)findViewById(R.id.lv_history);
        list_item = new Mybaseadapter();
        lv_history.setAdapter(list_item);

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
                    Log.d("HistoryActivity", "data+++" + resultJsonArray);
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

            ViewHolder viewHolder = new ViewHolder();

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.list_choose,null);
                viewHolder.Testname = (TextView) convertView.findViewById(R.id.tvTestname);
                convertView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.Testname.setText("项目名："+list.get(position).get("Testname").toString());

            return convertView;
        }
    }

    final static class ViewHolder {
        TextView Testname;
    }

}