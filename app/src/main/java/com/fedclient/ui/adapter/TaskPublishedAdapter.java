package com.fedclient.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.fedclient.R;
import com.fedclient.domain.TaskPublished;
import com.fedclient.ui.activity.TaskDetailActivity;



import java.util.List;


public class TaskPublishedAdapter extends BaseAdapter {
    private static final String TAG = "TaskPublishedAdapter";
    private List<TaskPublished> dataList;
    private Context context;
    private int resource;

    /**
     * 有参构造
     *
     * @param context
     *            界面
     * @param dataList
     *            数据
     * @param resource
     *            列表项资源文件
     */
    public TaskPublishedAdapter(Context context, List<TaskPublished> dataList,
                     int resource) {
        this.context = context;
        this.dataList = dataList;
        this.resource = resource;
    }

    @Override
    public int getCount() {

        return dataList.size();
    }

    @Override
    public Object getItem(int index) {

        return dataList.get(index);
    }

    @Override
    public long getItemId(int index) {

        return index;
    }

    @Override
    public View getView(int index, View view, ViewGroup arg2) {
        // 声明内部类
        Util util = null;
        // 中间变量
        final int flag = index;
        /**
         * 根据listView工作原理，列表项的个数只创建屏幕第一次显示的个数。
         * 之后就不会再创建列表项xml文件的对象，以及xml内部的组件，优化内存，性能效率
         */
        if (view == null) {
            util = new Util();
            // 给xml布局文件创建java对象
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(resource, null);
            // 指向布局文件内部组件
            util.contentTextView = (TextView) view.findViewById(R.id.tv_tp_content);
            util.dateTextView = (TextView) view.findViewById(R.id.tv_tp_date);
            util.titleTextView = (TextView) view.findViewById(R.id.tv_tp_title);
            util.infoButton = (Button) view.findViewById(R.id.btn_tp_info);
            // 增加额外变量
            view.setTag(util);
        } else {
            util = (Util) view.getTag();
        }
        // 获取数据显示在各组件
        TaskPublished data = dataList.get(index);

        //data.getCreateTime().toString()
        util.dateTextView.setText((String) "2021");
        util.titleTextView.setText((String) data.getTpName());

//        util.contentTextView.setText((String) data.get("content"));
//        util.imageView.setImageResource((Integer) data.get("img"));

//        // 删除按钮，添加点击事件
//        util.deleteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
//                dataList.remove(flag);
//                notifyDataSetChanged();
//                Map<String, Object> map = dataList.get(flag);
//                String str = "已删除\n标题：" + map.get("title") + "\n内容："
//                        + map.get("content") + "\n日期：" + map.get("date");
//                Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
//            }
//        });

        // 详情按钮，添加点击事件
        util.infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                TaskPublished temp = dataList.get(flag);
                /*
                String str = "标题：" + temp.getTpName() + "\n状态："
                        + temp.getStatus()+"\n目前轮次"+temp.getCurEpoch()
                        +"\n目前用户"+temp.getCurClients();
                Toast.makeText(context, str, Toast.LENGTH_SHORT).show();*/

                Intent intent =new Intent(context, TaskDetailActivity.class);
                Log.d(TAG, "onClick: "+temp.getTpName());
                intent.putExtra("name",temp.getTpName());
                intent.putExtra("id",temp.getTpId());
                intent.putExtra("status",temp.getStatus());
                intent.putExtra("curclient",temp.getCurClients());
                intent.putExtra("curepoch",temp.getCurEpoch());
                ((AppCompatActivity)context).startActivity(intent);
                ((AppCompatActivity)context).finish();



            }
        });
        return view;
    }

}

/**
 * 内部类，用于辅助适配
 *
 * @author qiangzi
 *
 */
class Util {
    ImageView imageView;
    TextView contentTextView, dateTextView, titleTextView;
    Button infoButton, deleteButton;
}