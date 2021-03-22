package com.fedclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.fedclient.ui.activity.HistoryActivity;

public class Details_history extends Activity {

    private RadioButton RB_back;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.details_history);

        Bundle bundle=getIntent().getExtras();
        int id=bundle.getInt("h_image");
        String message=bundle.getString("h_theme");
        String message1=bundle.getString("h_content");


        ImageView image=(ImageView) findViewById(R.id.detail_history_image);
        image.setImageResource(id);

        TextView title=(TextView) findViewById(R.id.detail_history_title);
        title.setText(message);

        TextView content=(TextView) findViewById(R.id.detail_history_content);
        content.setText(message1);


        RB_back = (RadioButton) findViewById (R.id.RB_back);
        RB_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Details_history.this,HistoryActivity.class);
                startActivity(intent);
            }
        });

    }
}
