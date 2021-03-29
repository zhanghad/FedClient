package com.fedclient.ui.activity;

import java.io.File;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.FileUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.fedclient.R;
import com.fedclient.constants.UrlConstants;
import com.fedclient.fed.websocket.WebSocketService;
import com.fedclient.manager.TaskServiceManager;

public class DataActivity extends AppCompatActivity {
    Switch s;
    public EditText et;
    private RadioButton RB_back;
    private RadioButton RB_join;
    private Button RB_choose;
    private Long tpId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.data_select);
        initComponents();
        setListeners();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent=getIntent();
        tpId=intent.getLongExtra("tpId",0L);
    }

    /**
     * 初始化组件及数据
     */
    private void initComponents(){
        RB_back = (RadioButton) findViewById (R.id.RB_back);
        RB_join = (RadioButton) findViewById( R.id.RB_join);
        RB_choose = (Button) findViewById( R.id.file_choose);
    }

    /**
     * 设置监听器
     */
    private void setListeners(){
        RB_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DataActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        RB_join.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(DataActivity.this,"已成功加入！",Toast.LENGTH_LONG).show();

//                WebSocketService webSocketService=new WebSocketService(UrlConstants.WS_TEST);
//                TaskServiceManager.setWebSocketService(webSocketService);
                startService(new Intent(DataActivity.this,WebSocketService.class));
                Intent intent = new Intent (DataActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        RB_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent getContentIntent = FileUtils.createGetContentIntent();
                Intent intent = Intent.createChooser(getContentIntent, "用aFileChooser选择文件");
                startActivityForResult(intent, REQUEST_CHOOSER);
            }
        });
    }

    /*
    public void babyname_onClick(View v) {
        EditText name_text=findViewById(R.id.name_text);
        PopupMenu pm=new PopupMenu(this, name_text);
        pm.getMenuInflater().inflate(R.menu.menu_data, pm.getMenu());
        pm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                name_text.setText(item.getTitle());
                return false;
            }
        });
        pm.show();
    }
    */



}
