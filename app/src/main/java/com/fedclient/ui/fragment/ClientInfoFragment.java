package com.fedclient.ui.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fedclient.R;
import com.fedclient.domain.Client;
import com.fedclient.manager.ActivityManager;
import com.fedclient.manager.ClientManager;
import com.fedclient.ui.activity.LoginActivity;
import com.fedclient.ui.activity.MainActivity;
import com.fedclient.ui.activity.ModifyPwdActivity;
import com.fedclient.util.CommonUtil;
import com.fedclient.util.Server;

import org.deeplearning4j.nn.workspace.LayerWorkspaceMgr;

import de.hdodenhof.circleimageview.CircleImageView;

public class ClientInfoFragment extends Fragment {

    public static final int TAKE_PHOTO = 1;
    public static final int CROP_PHOTO = 2;

    private CircleImageView iv_head;//头像显示
    private Button btn_takephoto;//拍照
    private Button btn_fromalbum;//相册
    private Uri imageUri;
    private Handler handler;
    private Server server = new Server(getActivity());

    private Button btn_Account;
    private Button btn_Nickname;
    private Button btn_Phone;
    private Button btn_Email;
    private Button btn_Sex;
    private Button btn_password;
    private Button btn_exit;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clientinfo,container,false);
        initView(view);
        setListeners();
        initData();
        return view;
    }

    void initView(View view) {

        btn_Account = view.findViewById(R.id.btn_account);
        btn_Nickname = view.findViewById(R.id.btn_nickname);
        btn_Phone = view.findViewById(R.id.btn_ci_phone);
        btn_Email = view.findViewById(R.id.btn_ci_emile);
        btn_Sex = view.findViewById(R.id.btn_ci_sex);
        btn_password = view.findViewById(R.id.btn_password);
        btn_exit = view.findViewById(R.id.btn_exit);
        iv_head= view.findViewById(R.id.iv_head);

    }


    void initData(){

    }
    /**
     * 设置监听
     */
    void setListeners(){

        // 修改昵称
        btn_Nickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View nicknameView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_edittext,null);
                final EditText nicknameEdit = nicknameView.findViewById(R.id.edit);
                // 弹昵称修改框
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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
                        CommonUtil.makeToast(getActivity(),getResources().getString(R.string.modify_success));
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

        //修改登陆名
        btn_Account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View accountView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_edittext,null);
                final EditText accountEdit = accountView.findViewById(R.id.edit);
                // 弹昵称修改框
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setView(accountView);
                builder.setTitle(getResources().getString(R.string.Account));
                builder.setNegativeButton(getResources().getString(R.string.cancel),null);
                builder.setPositiveButton(getResources().getString(R.string.confirm), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // 保存登录名
                        String account = accountEdit.getText().toString();
                        Client client = ClientManager.getCurrentClient();
                        client.setLoginName(account);
                        //user.save();
                        server.setLoginName(account);
                        // 更改显示
                        CommonUtil.makeToast(getActivity(),getResources().getString(R.string.modify_success));
                        // 通知MainActivity更新登录名
                        Message message = new Message();
                        message.what = 2;
                        message.obj = account;
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
                Intent intent = new Intent(getActivity(), ModifyPwdActivity.class);
                startActivity(intent);
            }
        });




        // 修改手机号
        btn_Phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View phoneView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_edittext,null);
                final EditText phoneEdit = phoneView.findViewById(R.id.edit);
                // 弹昵称修改框
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setView(phoneView);
                builder.setTitle(getResources().getString(R.string.Phone));
                builder.setNegativeButton(getResources().getString(R.string.cancel),null);
                builder.setPositiveButton(getResources().getString(R.string.confirm), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // 保存手机号
                        String phone = phoneEdit.getText().toString();
                        Client client = ClientManager.getCurrentClient();
                        client.setPhonenumber(phone);
                        //user.save();
                        server.setPhonenumber(phone);
                        // 更改显示
                        CommonUtil.makeToast(getActivity(),getResources().getString(R.string.modify_success));
                        // 通知MainActivity更新手机号
                        Message message = new Message();
                        message.what = 2;
                        message.obj = phone;
                        handler.sendMessage(message);
                    }
                });
                builder.show();
            }
        });

        //修改性别
        btn_Sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View sexView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_edittext,null);
                final EditText sexEdit = view.findViewById(R.id.edit);
                // 弹修改框
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setView(sexView);
                builder.setTitle(getResources().getString(R.string.Sex));
                builder.setNegativeButton(getResources().getString(R.string.cancel),null);
                builder.setPositiveButton(getResources().getString(R.string.confirm), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // 保存信息
                        String sex = sexEdit.getText().toString();
                        Client client = ClientManager.getCurrentClient();
                        client.setSex(sex);
                        //user.save();
                        server.setSex(sex);
                        // 更改显示
                        CommonUtil.makeToast(getActivity(),getResources().getString(R.string.modify_success));
                        // 通知MainActivity更新性别
                        Message message = new Message();
                        message.what = 2;
                        message.obj = sex;
                        handler.sendMessage(message);
                    }
                });
                builder.show();
            }
        });

        //修改邮箱
        btn_Email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View emailView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_edittext,null);
                final EditText emailEdit = emailView.findViewById(R.id.edit);
                // 弹修改框
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setView(emailView);
                builder.setTitle(getResources().getString(R.string.Email));
                builder.setNegativeButton(getResources().getString(R.string.cancel),null);
                builder.setPositiveButton(getResources().getString(R.string.confirm), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // 保存信息
                        String email = emailEdit.getText().toString();
                        Client client = ClientManager.getCurrentClient();
                        client.setEmail(email);
                        //user.save();
                        server.setEmail(email);
                        // 更改显示
                        CommonUtil.makeToast(getActivity(),getResources().getString(R.string.modify_success));
                        // 通知MainActivity更新邮箱
                        Message message = new Message();
                        message.what = 2;
                        message.obj = email;
                        handler.sendMessage(message);
                    }
                });
                builder.show();
            }
        });

        // 退出系统
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityManager.finishAll(LoginActivity.class);
                ActivityManager.clearAcache();
                ClientManager.clear();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

    }


}
