package com.fed;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;
import com.fedclient.R;
import androidx.appcompat.app.AppCompatActivity;

public class NowActivity extends AppCompatActivity {
    private RadioButton RB_renwuguanli;
    private RadioButton RB_lishi;
    private RadioButton RB_now;
    private RadioButton RB_shezhi;
    private RadioButton RB_mine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_now);

        RB_renwuguanli = (RadioButton) findViewById (R.id.RB_renwuguanli);
        RB_renwuguanli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(NowActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
        RB_lishi = (RadioButton) findViewById (R.id.RB_lishi);
        RB_lishi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(NowActivity.this,HistoryActivity.class);
                startActivity(intent);
            }
        });
        RB_now = (RadioButton) findViewById (R.id.RB_now);
        RB_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(NowActivity.this,NowActivity.class);
                startActivity(intent);
            }
        });
        RB_shezhi = (RadioButton) findViewById (R.id.RB_shezhi);
        RB_shezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(NowActivity.this,ShezhiActivity.class);
                startActivity(intent);
            }
        });
        RB_mine = (RadioButton) findViewById (R.id.RB_mine);
        RB_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(NowActivity.this,MineActivity.class);
                startActivity(intent);
            }
        });
    }
}