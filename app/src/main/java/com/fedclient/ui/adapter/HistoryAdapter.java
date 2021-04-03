package com.fedclient.ui.adapter;

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
import androidx.fragment.app.FragmentActivity;

import com.fedclient.R;
import com.fedclient.domain.ClientLog;
import com.fedclient.domain.TaskPublished;
import com.fedclient.ui.activity.HistoryDetailActivity;
import com.fedclient.ui.activity.TaskDetailActivity;

import java.util.List;


public class HistoryAdapter extends BaseAdapter {
    private static final String TAG = "TaskPublishedAdapter";
    private List<ClientLog> dataList;
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
    public HistoryAdapter(Context context, List<ClientLog> dataList,
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
        HistoryUtil historyUtil = null;
        // 中间变量
        final int flag = index;
        /**
         * 根据listView工作原理，列表项的个数只创建屏幕第一次显示的个数。
         * 之后就不会再创建列表项xml文件的对象，以及xml内部的组件，优化内存，性能效率
         */
        if (view == null) {
            historyUtil = new HistoryUtil();
            // 给xml布局文件创建java对象
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(resource, null);
            // 指向布局文件内部组件
            historyUtil.dateTextView = (TextView) view.findViewById(R.id.tv_h_date);
            historyUtil.titleTextView = (TextView) view.findViewById(R.id.tv_h_title);
            historyUtil.infoButton = (Button) view.findViewById(R.id.btn_h_info);
            historyUtil.contentTextView= (TextView) view.findViewById(R.id.tv_h_content);

            // 增加额外变量
            view.setTag(historyUtil);
        } else {
            historyUtil = (HistoryUtil) view.getTag();
        }
        // 获取数据显示在各组件
        ClientLog data = dataList.get(index);

        //data.getCreateTime().toString()
        historyUtil.dateTextView.setText((String) "2021");
        historyUtil.contentTextView.setText((String)("任务id："+data.getTpId()));
        historyUtil.titleTextView.setText((String)("日志id："+data.getClId().toString()) );

        // 详情按钮，添加点击事件
        historyUtil.infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                ClientLog temp = dataList.get(flag);
                /*
                String str = "标题：" + temp.getTpName() + "\n状态："
                        + temp.getStatus()+"\n目前轮次"+temp.getCurEpoch()
                        +"\n目前用户"+temp.getCurClients();
                Toast.makeText(context, str, Toast.LENGTH_SHORT).show();*/

/*                Intent intent =new Intent(context, TaskDetailActivity.class);
                Log.d(TAG, "onClick: "+temp.getTpName());
                intent.putExtra("name",temp.getTpName());
                intent.putExtra("id",temp.getTpId());
                intent.putExtra("status",temp.getStatus());
                intent.putExtra("curclient",temp.getCurClients());
                intent.putExtra("curepoch",temp.getCurEpoch());
                ((AppCompatActivity)context).startActivity(intent);
                ((AppCompatActivity)context).finish();*/

                Intent intent = new Intent(context, HistoryDetailActivity.class);
                ((FragmentActivity)context).startActivity(intent);
                ((FragmentActivity)context).finish();


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
class HistoryUtil {
    ImageView imageView;
    TextView contentTextView, dateTextView, titleTextView;
    Button infoButton, deleteButton;
}