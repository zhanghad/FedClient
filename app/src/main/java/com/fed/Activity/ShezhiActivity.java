package com.fed.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.fedclient.R;
import com.sun.jna.platform.win32.Advapi32Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.BreakIterator;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class ShezhiActivity extends AppCompatActivity {

    private RadioButton RB_home;
    private RadioButton RB_history;
    private RadioButton RB_now;
    private RadioButton RB_mine;

    Switch s;
    public EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_shezhi);

        RB_home = (RadioButton) findViewById (R.id.RB_home);
        RB_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ShezhiActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
        RB_history = (RadioButton) findViewById (R.id.RB_history);
        RB_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ShezhiActivity.this, HistoryActivity.class);
                startActivity(intent);
                finish();
            }
        });
        RB_now = (RadioButton) findViewById (R.id.RB_now);
        RB_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ShezhiActivity.this, NowActivity.class);
                startActivity(intent);
                finish();
            }
        });
        RB_mine = (RadioButton) findViewById (R.id.RB_mine);
        RB_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ShezhiActivity.this, MineActivity.class);
                startActivity(intent);
                finish();
            }
        });

        s = findViewById(R.id.kaiguan);
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (s.isChecked()){
                    Toast.makeText(ShezhiActivity.this,"开启",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(ShezhiActivity.this,"关闭",Toast.LENGTH_LONG).show();
                }
            }
        });


        TextView textView1 = (TextView) findViewById(R.id.RELEASE);
        textView1.setText(android.os.Build.VERSION.RELEASE );
        TextView product = (TextView) findViewById(R.id.product);
        product.setText(android.os.Build.PRODUCT);
        TextView cpu_abi = (TextView) findViewById(R.id.cpu_abi);
        cpu_abi.setText(android.os.Build.CPU_ABI);
        TextView tags = (TextView) findViewById(R.id.tags );
        tags.setText(android.os.Build.TAGS );
        TextView sdk = (TextView) findViewById(R.id.sdk );
        sdk.setText(android.os.Build.VERSION.SDK);
        TextView device = (TextView) findViewById(R.id.device);
        device.setText(android.os.Build.DEVICE);
        TextView maker = (TextView) findViewById(R.id.maker);
        maker.setText(android.os.Build.MANUFACTURER);
        TextView hardware = (TextView) findViewById(R.id.hardware);
        hardware.setText(android.os.Build.MANUFACTURER);


        }

    public void babyname_onClick(View v) {
        EditText name_text=findViewById(R.id.name_text);
        PopupMenu pm=new PopupMenu(this, name_text);
        pm.getMenuInflater().inflate(R.menu.menu_able_pro, pm.getMenu());
        pm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                name_text.setText(item.getTitle());
                return false;
            }
        });
        pm.show();
    }

}