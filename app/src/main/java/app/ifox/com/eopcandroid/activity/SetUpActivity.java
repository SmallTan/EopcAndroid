package app.ifox.com.eopcandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import app.ifox.com.eopcandroid.R;

/**
 * Created by 13118467271 on 2018/3/3.
 */

public class SetUpActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout account;
    private RelativeLayout networkMonitoring;
    private RelativeLayout share;
    private RelativeLayout agreement;
    private RelativeLayout aboutUs;
    private RelativeLayout versionUpdate;
    private RelativeLayout clear;
    private TextView loginOut;
    private Button back;
    private TextView backText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup);
        init();
    }

    private void init() {
        back = (Button) findViewById(R.id.setup_back_button);
        backText = (TextView) findViewById(R.id.setup_back_text);
        account = (RelativeLayout) findViewById(R.id.setup_account);
        networkMonitoring = (RelativeLayout) findViewById(R.id.setup_network_monitoring);
        share = (RelativeLayout) findViewById(R.id.setup_share);
        agreement = (RelativeLayout) findViewById(R.id.setup_agreement);
        aboutUs = (RelativeLayout) findViewById(R.id.setup_about);
        versionUpdate = (RelativeLayout) findViewById(R.id.setup_version_update);
        clear = (RelativeLayout) findViewById(R.id.setup_claer);
        loginOut = (TextView) findViewById(R.id.setup_login_out);
        back.setOnClickListener(this);
        backText.setOnClickListener(this);
        account.setOnClickListener(this);
        networkMonitoring.setOnClickListener(this);
        share.setOnClickListener(this);
        agreement.setOnClickListener(this);
        aboutUs.setOnClickListener(this);
        versionUpdate.setOnClickListener(this);
        clear.setOnClickListener(this);
        loginOut.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.setup_account:
                Toast.makeText(this, "暂未提供此服务", Toast.LENGTH_SHORT).show();
                break;
            case R.id.setup_network_monitoring:
                Toast.makeText(this, "暂未提供此服务", Toast.LENGTH_SHORT).show();
                break;
            case R.id.setup_share:
                Toast.makeText(this, "暂未提供此服务", Toast.LENGTH_SHORT).show();
                break;
            case R.id.setup_agreement:
                Toast.makeText(this, "暂未提供此服务", Toast.LENGTH_SHORT).show();
                break;
            case R.id.setup_about:
                Toast.makeText(this, "暂未提供此服务", Toast.LENGTH_SHORT).show();
                break;
            case R.id.setup_claer:
                Toast.makeText(this, "暂未提供此服务", Toast.LENGTH_SHORT).show();
                break;
            case R.id.setup_login_out:
                Toast.makeText(this, "暂未提供此服务", Toast.LENGTH_SHORT).show();
                break;
            case R.id.setup_back_button:
            case R.id.setup_back_text:
                Intent backIntent = new Intent(SetUpActivity.this,MapActivity.class);
                startActivity(backIntent);
                finish();
                break;
            default:
                break;
        }
    }
}
