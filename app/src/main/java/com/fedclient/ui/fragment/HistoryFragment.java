package com.fedclient.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.fedclient.R;
import com.fedclient.constants.UrlConstants;
import com.fedclient.domain.ClientLog;
import com.fedclient.manager.ClientManager;
import com.fedclient.ui.adapter.HistoryAdapter;
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

public class HistoryFragment extends Fragment {
    public ListView lv_history;

    private static final String TAG = "HistoryActivity";

    public List<ClientLog> historyList = new ArrayList<ClientLog>();
    private HistoryAdapter historyAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history,container,false);
        initComponents(view);

        return view;
    }
    
    /**
     * 初始化组件及数据
     */
    private void initComponents(View view){
        lv_history=view.findViewById(R.id.lv_history);

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
                    historyList = new Gson().fromJson(data,userListType);

                    if(historyList==null){
                        historyList=new ArrayList<ClientLog>();
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            historyAdapter = new HistoryAdapter(getActivity(),historyList,R.layout.view_historylist);
                            lv_history.setAdapter(historyAdapter);
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

    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    historyAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };



}
