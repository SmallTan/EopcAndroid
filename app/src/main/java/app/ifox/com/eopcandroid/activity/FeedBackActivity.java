package app.ifox.com.eopcandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import app.ifox.com.eopcandroid.R;

/**
 * Created by 13118467271 on 2018/3/3.
 */

public class FeedBackActivity extends AppCompatActivity implements View.OnClickListener {

    private Button back;
    private TextView backText;
    private EditText editText;
    private Button send;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);
        init();
    }

    private void init() {
        back = (Button) findViewById(R.id.feedback_back_button);
        backText = (TextView) findViewById(R.id.feedback_back_text);
        editText = (EditText) findViewById(R.id.feedback_edit);
        send = (Button) findViewById(R.id.feedback_send);
        backText.setOnClickListener(this);
        backText.setOnClickListener(this);
        send.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.feedback_back_button:
            case R.id.feedback_back_text:
                Intent backIntent = new Intent(FeedBackActivity.this,MapActivity.class);
                startActivity(backIntent);
                finish();
                break;
            case R.id.feedback_send:
                Toast.makeText(this, "感谢您的宝贵意见", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
