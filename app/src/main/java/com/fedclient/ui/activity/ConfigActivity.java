package com.fedclient.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.fedclient.R;

import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 训练配置页面
 */
public class ConfigActivity extends AppCompatActivity {


    Switch s;
    public EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_config);
        initComponents();
        setListeners();
        }


    /**
     * 初始化组件及数据
     */
    private void initComponents(){

        s = findViewById(R.id.kaiguan);
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

    /**
     * 设置监听器
     */
    private void setListeners(){
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (s.isChecked()){
                    Toast.makeText(ConfigActivity.this,"开启",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(ConfigActivity.this,"关闭",Toast.LENGTH_LONG).show();
                }
            }
        });
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