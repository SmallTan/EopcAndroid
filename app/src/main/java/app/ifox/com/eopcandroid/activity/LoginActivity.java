package app.ifox.com.eopcandroid.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import app.ifox.com.eopcandroid.R;

/**
 * Created by 13118467271 on 2017/9/18.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Button btLogin;
    private Button btRegister;
    private Button btFindPassword;
    private Button btClose;
    private TextInputLayout tlEmail;
    private TextInputLayout tlPassword;
    private EditText etEmail;
    private EditText etPassword;
    private String inputEmail,inputPassword;
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private boolean validateEmail(String loginEmail){
       Matcher matcher = pattern.matcher(loginEmail);
       return matcher.matches();
    }
    private boolean validatePassword(String password){
        return password.length() > 6;
    }

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
        btLogin = (Button) findViewById(R.id.bt_login);

        btLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_login:
               // Toast.makeText(LoginActivity.this,"点击登录",Toast.LENGTH_SHORT).show();
                inputEmail = tlEmail.getEditText().getText().toString();
                inputPassword = tlPassword.getEditText().getText().toString();
                login();
                break;
            default:
                break;
        }
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
            Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
        }
    }
}
