package app.ifox.com.eopcandroid.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import app.ifox.com.eopcandroid.R;
import app.ifox.com.eopcandroid.model.LoginJSON;
import app.ifox.com.eopcandroid.model.ParkUser;
import app.ifox.com.eopcandroid.model.TokenModel;
import app.ifox.com.eopcandroid.util.NetUtil;

/**
 * Created by 13118467271 on 2017/9/18.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Button btLogin;
    private Button btRegister;
    private Button btFindPassword;
    private Button btClose;

    private ProgressDialog progressDialog;
    private String result = null;
    private ParkUser user;
    private TextInputLayout tlEmail;
    private TextInputLayout tlPassword;
    private CheckBox checkRemeber;
    private EditText etEmail;
    private EditText etPassword;
    private SharedPreferences prePassword;
    private SharedPreferences.Editor preEditor;
    private String inputEmail,inputPassword;
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private boolean validateEmail(String loginEmail){
       Matcher matcher = pattern.matcher(loginEmail);
       return matcher.matches();
    }
    private boolean validatePassword(String password){
        return password.length() > 6;
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            if (msg.what == 0){
                //Log.d("result" ,result);
                if (result == null){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(1500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            progressDialog.dismiss();
                        }
                    }).start();
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "连接失败，请检查网络或者告知管理员", Toast.LENGTH_SHORT).show();
                } else if (result.equals("error")){
                    Log.d("error", result);
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                }else{
                    progressDialog.dismiss();
                    if (checkRemeber.isChecked()) { //检查复选框是否被选中，选中就存储账号和密码
                        preEditor.putString("account",inputEmail);
                        preEditor.putString("password",inputPassword);
                        preEditor.putBoolean("isRemeber",true);
                    }else {
                        preEditor.clear();
                    }
                    preEditor.commit();
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    Intent intentMainActivity = new Intent(LoginActivity.this, MapActivity.class);
                    startActivity(intentMainActivity);
                    finish();
                }
            }
        }
    };
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);
        init();
    }

    private void init() {
        tlEmail = (TextInputLayout) findViewById(R.id.tl_login_mail);
        tlPassword = (TextInputLayout) findViewById(R.id.tl_login_password);
        etEmail = (EditText) findViewById(R.id.et_login_mail);
        etPassword = (EditText) findViewById(R.id.et_login_password);
//        btClose = (Button) findViewById(R.id.close_login);
        btFindPassword = (Button) findViewById(R.id.find_password);
        btRegister = (Button) findViewById(R.id.intent_register);
        btLogin = (Button) findViewById(R.id.bt_login);
        checkRemeber = (CheckBox) findViewById(R.id.remeber_password);
        btLogin.setOnClickListener(this);
        btRegister.setOnClickListener(this);
        btFindPassword.setOnClickListener(this);
//        btClose.setOnClickListener(this);
        prePassword = getSharedPreferences("remeberAccount",MODE_PRIVATE);
        preEditor = prePassword.edit();
        setRemeberPassword();
    }
    private void setRemeberPassword(){
        boolean isRemeber = prePassword.getBoolean("isRemeber",false);
        if (isRemeber) {
            String account = prePassword.getString("account","");
            String password = prePassword.getString("password","");
            etEmail.setText(account);
            etPassword.setText(password);
            checkRemeber.setChecked(true);
            Intent intentMainActivity = new Intent(LoginActivity.this, MapActivity.class);
            startActivity(intentMainActivity);
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_login:
               // Toast.makeText(LoginActivity.this,"点击登录",Toast.LENGTH_SHORT).show();
                inputEmail = tlEmail.getEditText().getText().toString();
                inputPassword = tlPassword.getEditText().getText().toString();
                savePassword();
                login();
                break;
            case R.id.intent_register:
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            default:
                break;
        }
    }

    private void savePassword() {

    }

    public void login() {

        verifyEmail();
    }

    public void verifyEmail() {
        if (!validateEmail(inputEmail)){
            Toast.makeText(LoginActivity.this,"请输入正确的邮箱地址",Toast.LENGTH_SHORT).show();
        }else if (!validatePassword(inputPassword)){
            tlPassword.setErrorEnabled(true);
            Toast.makeText(LoginActivity.this,"密码字数过少",Toast.LENGTH_SHORT).show();
        }else{
            tlEmail.setErrorEnabled(false);
            tlPassword.setErrorEnabled(false);
            progressDialog = new ProgressDialog(LoginActivity.this);
            progressDialog.setTitle("等待连接...");
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            checkUser(inputEmail, inputPassword);
        }
    }
    private void checkUser(final String email, final String password) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://192.168.0.124:8080/login";
                String request = "email=" + email + "&password=" + password;
                NetUtil netUtil = new NetUtil();
                result = netUtil.upInfo(url, "", request, "utf-8");
                parseJSONWithGSON(result);
                Message message = new Message();
                message.what = 0;
                handler.sendMessage(message);
            }
        }).start();


    }
    public void parseJSONWithGSON(String jsonData){
        Gson gson = new Gson();
        if (jsonData == null) {
            return;
        }else {
            LoginJSON loginJSON = new LoginJSON();
            loginJSON = gson.fromJson(jsonData, LoginJSON.class);
            user = new ParkUser();
            user = loginJSON.getParkUser();
            TokenModel tokenModel = new TokenModel();
            tokenModel = loginJSON.getTokenModel();
            SharedPreferences.Editor editor = getSharedPreferences("per_information", MODE_PRIVATE).edit();
            editor.putString("name", user.getUserName());
            editor.putString("school", user.getSchool());
            editor.putString("password", user.getPassword());
            editor.putString("email", user.getEmail());
            editor.putInt("userId", user.getUserId());
            editor.putString("introduce","无");
            String tokenModeJson = gson.toJson(tokenModel);
            String userJson = gson.toJson(user);
            editor.putString("userJosn",userJson);
            editor.putString("token", tokenModeJson);
            editor.commit();
        }
    }
}
