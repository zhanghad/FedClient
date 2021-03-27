package com.fedclient.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

import com.fedclient.domain.Client;
import com.fedclient.util.CommonRequest;
import com.fedclient.util.CommonResponse;
import com.fedclient.trash.Consts;
import com.fedclient.util.HttpUtil;
import com.fedclient.util.SharedPreferencesUtil;
import com.fedclient.manager.ClientManager;
import com.fedclient.util.CommonUtil;
import com.fedclient.R;
import org.deeplearning4j.nn.workspace.LayerWorkspaceMgr;

import com.fedclient.util.PhotoUtil;
import com.fedclient.util.ACache;
import com.fedclient.util.Server;
import com.fedclient.manager.ActivityManager;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 个人信息页面
 */
public class ClientInfoActivity extends Activity {
    private LayerWorkspaceMgr.Builder Glide;

    public static final int TAKE_PHOTO = 1;
    public static final int CROP_PHOTO = 2;

    private CircleImageView iv_head;//头像显示
    private Button btn_takephoto;//拍照
    private Button btn_fromalbum;//相册
    private Uri imageUri;

    private TextView tx_account;
    private TextView tx_Userscore;
    private TextView tx_Nickname;

    private Button btn_nickname;
    private Button btn_password;
    private Button btn_exit;

    private PhotoUtil photoUtil = new PhotoUtil();
    private ACache aCache;
    private Handler handler;
    private Server server = new Server(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientinfo);
        initView();
        setListeners();
        initData();
    }

    /**
     * 初始化组件
     */
    void initView(){
        aCache = ACache.get(ClientInfoActivity.this);
        HomeActivity activity = (HomeActivity) ActivityManager.getActivity(HomeActivity.class);
        handler = activity.handler;
        tx_account = findViewById(R.id.tx_account);
        tx_Userscore = findViewById(R.id.tx_Userscore);
        tx_Nickname = (TextView)findViewById(R.id.tx_nickname);
        btn_nickname = findViewById(R.id.btn_nickname);
        btn_password = findViewById(R.id.btn_password);
        btn_exit = findViewById(R.id.btn_exit);
        iv_head= findViewById(R.id.iv_head);
    }

    /**
     * 设置监听
     */
    void setListeners(){

        // 修改昵称
        btn_nickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View nicknameView = LayoutInflater.from(ClientInfoActivity.this).inflate(R.layout.dialog_edittext,null);
                final EditText nicknameEdit = nicknameView.findViewById(R.id.edit);
                // 弹昵称修改框
                AlertDialog.Builder builder = new AlertDialog.Builder(ClientInfoActivity.this);
                builder.setView(nicknameView);
                builder.setTitle(getResources().getString(R.string.Nickname));
                builder.setNegativeButton(getResources().getString(R.string.cancel),null);
                builder.setPositiveButton(getResources().getString(R.string.confirm), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // 保存昵称
                        String nickname = nicknameEdit.getText().toString();
                        Client client = ClientManager.getCurrentClient();
                        client.setClientName(nickname);
                        //user.save();
                        server.setNickname(nickname);
                        // 更改显示
                        CommonUtil.makeToast(ClientInfoActivity.this,getResources().getString(R.string.modify_success));
                        // 通知MainActivity更新昵称
                        Message message = new Message();
                        message.what = 2;
                        message.obj = nickname;
                        handler.sendMessage(message);
                    }
                });
                builder.show();
            }
        });

        // 修改密码
        btn_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Client user = ClientManager.getCurrentClient();
                Intent intent = new Intent(ClientInfoActivity.this,ModifyPwdActivity.class);
                startActivity(intent);
            }
        });

        // 退出登录
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferencesUtil spu = new SharedPreferencesUtil(ClientInfoActivity.this);
                spu.setParam("isAutoLogin",false);
                ActivityManager.finishAll(LoginActivity.class);
                ActivityManager.clearAcache();
                ClientManager.clear();
            }
        });

        //btn_fromalbum.setOnClickListener();

    }

    void initData(){
        // 初始化用户记录
        Client user = ClientManager.getCurrentClient();

        /*
        List<Record> recordList = Database.findRecords(user);
        if(!recordList.isEmpty() || !(recordList = server.getRecords()).isEmpty()){
            user.getRecordList().addAll(recordList);
            //user.save();
        }
         */

        // 初始化昵称
        String nickname = user.getClientName();
        if(nickname != null || !(nickname = server.getNickname()).equals("null")){
            Log.e("INIT",nickname);
            user.setClientName(nickname);
            //user.save();
        }else {
            nickname = "Nickname";
        }
        tx_Nickname.setText(nickname);

        //初始化积分
        /*
        String userscore = user.getUserscore();
        if(userscore != null || !(userscore = server.getUserscore()).equals("null")){
            Log.e("INIT",userscore);
            user.setUserscore(userscore);
            //user.save();
        }else {
            userscore = "Userscore";
        }
        tx_Userscore.setText(userscore);
         */


        // 初始化头像：缓存->本地数据库->服务器
        Bitmap bitmap;
        if((bitmap = aCache.getAsBitmap("head")) == null){// cache加载失败
            // 数据库加载失败则从服务器加载
            if(user.getAvatarImage() == null){
                CommonRequest request = new CommonRequest();
                request.addRequestParam("username",user.getClientName());
                HttpUtil.sendPost(Consts.URL_DownloadImg,request.getJsonStr(),new okhttp3.Callback() {
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        CommonResponse res = new CommonResponse(response.body().string());
                        String resCode = res.getResCode();
                        String resMsg = res.getResMsg();
                        HashMap<String,String> property = res.getPropertyMap();
                        String imgStr = property.get("head");
                        loadAvatar(resCode,imgStr);
                    }
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                        showResponse("Network ERROR");
                    }
                });
            }else{// 数据库加载
                bitmap = BitmapFactory.decodeByteArray(user.getAvatarImage(),0,
                        user.getAvatarImage().length);
                iv_head.setImageBitmap(bitmap);
            }
        }else {
            iv_head.setImageBitmap(bitmap);
        }

    }


    private void saveAvatar(final Bitmap bitmap){
        // 设置头像
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                iv_head.setImageBitmap(bitmap);
            }
        });
        // 保存头像到本地数据库
        Client client = ClientManager.getCurrentClient();
        //client.setAvatarImage(PhotoUtil.bitmap2Bytes(bitmap));
        //user.save();
        // 写入缓存
        aCache.put("head",bitmap);
    }

    private void loadAvatar(String resCode,String imgStr){
        if (resCode.equals(Consts.SUCCESSCODE_DOWNLOADIMG) && !imgStr.equals("null") && !imgStr.equals("")) {
            // 获取头像成功
            byte[] bytes = Base64.decode(imgStr, Base64.DEFAULT);
            final Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    iv_head.setImageBitmap(bitmap);
                }
            });
        }else{
            // 使用默认头像
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    iv_head.setImageResource(R.drawable.c0);
                }
            });
        }
    }

    private void showResponse(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ClientInfoActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }


}