package com.fedclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import com.fedclient.activity.HomeActivity;

public class ChooseData extends Activity {

    Switch s;
    public EditText et;

    private RadioButton RB_back;
    private RadioButton RB_join;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.choose_data);

        RB_back = (RadioButton) findViewById (R.id.RB_back);
        RB_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ChooseData.this,Details.class);
                startActivity(intent);
            }
        });
        RB_join = (RadioButton) findViewById( R.id.RB_join);
        RB_join.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent (ChooseData.this,HomeActivity.class);
                Toast.makeText(ChooseData.this,"已成功加入！",Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });

    }

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



}
