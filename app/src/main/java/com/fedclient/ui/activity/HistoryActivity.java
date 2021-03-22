package com.fedclient.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
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

import com.fedclient.constants.UrlConstants;
import com.fedclient.domain.ClientLog;
import com.fedclient.manager.ClientManager;
import com.fedclient.R;
import com.fedclient.util.HttpUtil;
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

/**
 * 用户参与历史记录页面
 */
public class HistoryActivity extends AppCompatActivity{
    private ImageView iv_back;
    public ListView lv_history;
    public List<ClientLog> list= new ArrayList<ClientLog>();
    private MyBaseAdapter myBaseAdapter;
    private static final String TAG = "HistoryActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_history);
        initComponents();
        setListeners();
    }

    /**
     * 设置监听
     */
    void setListeners() {
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(HistoryActivity.this,HomeActivity.class);
                startActivity(intent);
                //finish();
            }
        });

    }

    /**
     * 初始化组件及数据
     */
    private void initComponents(){
        iv_back = (ImageView) findViewById(R.id.iv_back);
        lv_history = (ListView)findViewById(R.id.lv_history);


        /**
         * 从服务端获取数据
         */
        try {
            HttpUrl url =HttpUrl.parse(UrlConstants.URL_HISTORY_GET).newBuilder()
                    .addQueryParameter("loginName", ClientManager.getCurrentClient().getLoginName())
                    .build();
            HttpUtil.sendGet(url.toString(), new Callback() {
                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    String data = response.body().string();
                    Log.i(TAG, "onResponse: "+data);
                    Type userListType = new TypeToken<List<ClientLog>>(){}.getType();
                    list= new Gson().fromJson(data,userListType);
                }
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    Log.e(TAG, "onFailure: ", e);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }


        myBaseAdapter = new MyBaseAdapter(list);
        lv_history.setAdapter(myBaseAdapter);

        /*new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();*/


    }


    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    myBaseAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };


    /**
     * listview 适配器
     */
    private class MyBaseAdapter extends BaseAdapter {
        List<ClientLog> clientLogs;

        public MyBaseAdapter(List<ClientLog> clientLogs){
            super();
            this.clientLogs=clientLogs;
        }

        @Override
        public int getCount() {
            return clientLogs.size();
        }

        @Override
        public Object getItem(int position) {
            return clientLogs.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @SuppressLint("SetTextI18n")
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
            viewHolder.Testname.setText("任务id："+clientLogs.get(position).getTpId());
            return convertView;
        }
    }

    final static class ViewHolder {
        TextView Testname;
    }

}