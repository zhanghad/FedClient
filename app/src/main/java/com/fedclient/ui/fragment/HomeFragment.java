package com.fedclient.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.fedclient.R;
import com.fedclient.constants.UrlConstants;
import com.fedclient.domain.TaskPublished;
import com.fedclient.ui.adapter.TaskPublishedAdapter;
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

public class HomeFragment extends Fragment{
    private static final String TAG = "Home_Fragment";

    public ListView lv_project;
    private TaskPublishedAdapter list_item;
    List<TaskPublished> taskPublisheds= new ArrayList<TaskPublished>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        initComponents(view);
        return view;
    }


    private void initComponents(View view){
//        list.clear();
        lv_project = (ListView)view.findViewById(R.id.lv_project);
        Log.d(TAG, "initComponents: "+ lv_project);

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

                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Log.d(TAG, "onCreate: "+taskPublisheds.toString());
                                    list_item = new TaskPublishedAdapter(getActivity(),taskPublisheds,R.layout.view_tplist);
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


}
