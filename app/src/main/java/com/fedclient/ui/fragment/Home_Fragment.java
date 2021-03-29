package com.fedclient.ui.fragment;

import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.fedclient.domain.TaskPublished;
import com.fedclient.ui.activity.HomeActivity;
import com.fedclient.ui.adapter.TaskPublishedAdapter;

import java.util.ArrayList;
import java.util.List;

public class Home_Fragment extends Fragment{

    public ListView lv_project;
    private TaskPublishedAdapter list_item;
    List<TaskPublished> taskPublisheds= new ArrayList<TaskPublished>();

    public static Home_Fragment newInstance() {
        return new Home_Fragment();
    }


}
