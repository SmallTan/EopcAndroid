package app.ifox.com.eopcandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import app.ifox.com.eopcandroid.Adapter.NoticAdapter;
import app.ifox.com.eopcandroid.R;

/**
 * Created by 13118467271 on 2018/3/3.
 */

public class NoticeActivity extends AppCompatActivity implements View.OnClickListener {
    private Button back;
    private TextView textBack;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notice_system);
        initView();
    }

    private void initView() {
        back = (Button) findViewById(R.id.notice_back_button);
        textBack = (TextView) findViewById(R.id.notice_back_text);
        recyclerView = (RecyclerView) findViewById(R.id.notice_recycler);
        back.setOnClickListener(this);
        textBack.setOnClickListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        NoticAdapter feedback_adapter = new NoticAdapter(NoticeActivity.this);
        recyclerView.setAdapter(feedback_adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.notice_back_button:
            case R.id.notice_back_text:
                Intent backIntent = new Intent(NoticeActivity.this,MapActivity.class);
                startActivity(backIntent);
                finish();
                break;

        }
    }
}
