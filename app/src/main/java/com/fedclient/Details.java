package com.fedclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.fedclient.activity.HomeActivity;

public class Details extends Activity {

    private RadioButton RB_back;
    private RadioButton RB_join;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.details);

        Bundle bundle=getIntent().getExtras();
        int id=bundle.getInt("image");
        String message=bundle.getString("theme");
        String message1=bundle.getString("content");



        ImageView image=(ImageView) findViewById(R.id.detail_image);
        image.setImageResource(id);

        TextView title=(TextView) findViewById(R.id.detail_title);
        title.setText(message);

        TextView content=(TextView) findViewById(R.id.detail_content);
        content.setText(message1);



        RB_back = (RadioButton) findViewById (R.id.RB_back);
        RB_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Details.this,HomeActivity.class);
                startActivity(intent);
            }
        });
        RB_join = (RadioButton) findViewById( R.id.RB_join);
        RB_join.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent (Details.this,ChooseData.class);
                startActivity(intent);
            }
        });

    }
}
