package app.ifox.com.eopcandroid.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.net.HttpURLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import app.ifox.com.eopcandroid.R;
import app.ifox.com.eopcandroid.model.ParkUser;
import app.ifox.com.eopcandroid.util.NetUtil;

/**
 * Created by 13118467271 on 2017/9/27.
 */

public class RegisterActivity extends Activity implements View.OnClickListener {
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private EditText eMail;
    private EditText password;
    private EditText passwordAgain;
    private EditText verificationCode;//验证码
    private Button sendVerificationCode;//发送验证码
    private Button back;
    private Button register;
    private String result;
    private String codeResult;
     ParkUser parkUser;
    private String codeRequest;
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private boolean validateEmail(String loginEmail){
        Matcher matcher = pattern.matcher(loginEmail);
        return matcher.matches();
    }
    private boolean validatePassword(String password){
        return password.length() > 3;
    }
    @SuppressLint("HandlerLeak")
    private Handler handle = new Handler() {
        public void handleMessage(Message msg){
            if (msg.what == 0){
                if (result == null) {
                    Toast.makeText(RegisterActivity.this,"连接失败,请检查网络或者联系管理员",Toast.LENGTH_SHORT).show();
                }else if (result.equals("317")){
//
                    Toast.makeText(RegisterActivity.this,"验证码错误或者验证码过时",Toast.LENGTH_SHORT).show();
                }else if(result.equals("200")){
//
                    Toast.makeText(RegisterActivity.this,"注册成功，请返回登录",Toast.LENGTH_SHORT).show();
                    Intent intentLoginActivity = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(intentLoginActivity);
                    finish();
                }else if (result.equals("318")){
                    Toast.makeText(RegisterActivity.this,"没有接受过验证码",Toast.LENGTH_SHORT).show();
                }
            }else if (msg.what == 1){//进行发送验证码时候判断
                if (codeResult == null) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(1500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                    Toast.makeText(RegisterActivity.this, "发送验证失败，请检查网络或者联系管理员", Toast.LENGTH_SHORT).show();

                }else if (codeResult.equals("316")){
                    Toast.makeText(RegisterActivity.this,"邮箱已被注册",Toast.LENGTH_SHORT).show();
                }else if(codeResult.equals("200")){
                    Toast.makeText(RegisterActivity.this, "验证码发送成功，请注意查收", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.register);
        eMail = (EditText)findViewById(R.id.e_mail);
        password = (EditText)findViewById(R.id.register_password);
        passwordAgain = (EditText)findViewById(R.id.register_password_again);
        back = (Button)findViewById(R.id.back_login);
        register = (Button)findViewById(R.id.register);
        verificationCode = (EditText) findViewById(R.id.verification_code);
        sendVerificationCode = (Button) findViewById(R.id.send_verification_code);
        back.setOnClickListener(this);
        register.setOnClickListener(this);
        sendVerificationCode.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                String emailText = eMail.getText().toString();
                String passwordText = password.getText().toString();
                String passwordAgainText = passwordAgain.getText().toString();
                codeRequest = verificationCode.getText().toString();
                if (  "".equals(emailText) || "".equals(passwordText) || "".equals(passwordAgainText) || "".equals(codeRequest)){
                    Toast.makeText(this,"请完整填入以上的信息",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!passwordText.equals(passwordAgainText)) {
                    Toast.makeText(this,"两次输入的密码不同，请修改",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!validateEmail(emailText)){
                    Toast.makeText(RegisterActivity.this,"请输入正确的邮箱地址",Toast.LENGTH_SHORT).show();
                }else if (!validatePassword(passwordText)){
                    Toast.makeText(RegisterActivity.this,"密码字数过少",Toast.LENGTH_SHORT).show();
                }else {
                createUser(verificationCode.getText().toString(),eMail.getText().toString(),password.getText().toString());
                }
                break;
            case R.id.back_login:
                Intent intentLoginActivity = new Intent(RegisterActivity.this,LoginActivity.class);
                if (!eMail.getText().toString().equals("")) {
                    intentLoginActivity.putExtra("userEmail", eMail.getText().toString());
                }
                intentLoginActivity.putExtra("login_from_register",true);
                startActivity(intentLoginActivity);
                finish();
                break;
            case R.id.send_verification_code:
                if (eMail.getText().toString().equals("")) {//邮箱为空的情况
                    Toast.makeText(this, "邮箱不能为空", Toast.LENGTH_SHORT).show();
                }else if (!validateEmail(eMail.getText().toString())){
                    Toast.makeText(this, "邮箱格式不正确", Toast.LENGTH_SHORT).show();
                }else{
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String url = "http://192.168.0.124:8080/checkEmail";
                            String request =  "&email=" + eMail.getText().toString();
                            NetUtil netUtil = new NetUtil();
                            codeResult = netUtil.upInfo(url, "", request, "utf-8");
                            Message message = new Message();
                            message.what = 1;
                            handle.sendMessage(message);
                        }
                    }).start();

                }
        }
    }

    private void createUser( String code, String emailText, String passwordText) {
        if (emailText == null) {
            Log.d("aaaaaaa", "createUser: emailText is nul");
        }else {
            Log.d("aaaaaaa", "createUser: emailText is not nul");
        }
        if (code == null) {
            Log.d("aaaaaaa", "createUser: code is nul");
        }if (passwordText == null) {
            Log.d("aaaaaaa", "createUser: passwordText is nul");
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://192.168.0.124:8080/register";
                parkUser = new ParkUser();
                parkUser.setEmail(emailText);
                parkUser.setPassword(passwordText);
                Gson gson = new Gson();
                String parkUserJson = "parkUserJson=" + gson.toJson(parkUser) + "&checkCode=" + code;
                NetUtil netUtil = new NetUtil();
                result = netUtil.upInfo(url, "", parkUserJson, "utf-8");
                if (result == null) {
                    Log.d("aaaaaaa", "run: result is null");
                }else {
                    Log.d("aaaaaaa", "run: result is not null " + result);
                }

                Message message = new Message();
                message.what = 0;
                handle.sendMessage(message);
            }
        }).start();
    }

}
