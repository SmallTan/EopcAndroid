package app.ifox.com.eopcandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import app.ifox.com.eopcandroid.Adapter.ParkUserMessageAdapter;
import app.ifox.com.eopcandroid.R;
import app.ifox.com.eopcandroid.model.ParkChatRoomMessage;

/**
 * Created by 13118467271 on 2017/10/16.
 */

public class ChatRoomActivity extends AppCompatActivity implements OnClickListener {
    private static WebSocketClient webSocketClient;
    private ListView msgListView;
    private EditText inputText;
    private Button send;
    private ParkUserMessageAdapter adapter;
    private Button chatRoomIntentMap;

    private List<ParkChatRoomMessage> msgList = new ArrayList<ParkChatRoomMessage>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();
        setContentView(R.layout.chat_room);
        initMsgs();
        adapter = new ParkUserMessageAdapter(ChatRoomActivity.this, R.layout.chat_room_message_item, msgList);
        inputText = (EditText) findViewById(R.id.input_text);
        send = (Button) findViewById(R.id.send);
        chatRoomIntentMap = (Button) findViewById(R.id.chat_room_intent_map);
        msgListView = (ListView) findViewById(R.id.msg_list_view);
        msgListView.setAdapter(adapter);
        chatRoomIntentMap.setOnClickListener(this);
        send.setOnClickListener(this);
//        try {
//            testDemo();
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }

    }

    private void initMsgs() {
        ParkChatRoomMessage msg1 = new ParkChatRoomMessage("Hello, how are you?", ParkChatRoomMessage.TYPE_RECEIVED);
        msgList.add(msg1);
        ParkChatRoomMessage msg2 = new ParkChatRoomMessage("Fine, thank you, and you?", ParkChatRoomMessage.TYPE_SEND);
        msgList.add(msg2);
        ParkChatRoomMessage msg3 = new ParkChatRoomMessage("I am fine, too!", ParkChatRoomMessage.TYPE_RECEIVED);
        msgList.add(msg3);
    }

    public void testDemo() throws URISyntaxException {


        webSocketClient = new WebSocketClient(new URI("ws://localhost:8080atRoom/CS-ZLF/1-1")) {

            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                System.out.println("open");


            }

            @Override
            public void onMessage(String s) {
                System.out.println(s);
            }

            @Override
            public void onClose(int i, String s, boolean b) {

            }

            @Override
            public void onError(Exception e) {

            }
        };
        webSocketClient.connect();
        while (!webSocketClient.getReadyState().equals(WebSocket.READYSTATE.OPEN)) {
            Log.d("ssssss", "链接成功");
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chat_room_intent_map:
                Intent intent = new Intent(ChatRoomActivity.this, MapActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.send:
                String content = inputText.getText().toString();
                if (!"".equals(content)) {
                    ParkChatRoomMessage msg = new ParkChatRoomMessage(content, ParkChatRoomMessage.TYPE_SEND);
                    msgList.add(msg);

//                    webSocketClient.send(content);

                    adapter.notifyDataSetChanged();//刷新显示
                    msgListView.setSelection(msgList.size());
                    inputText.setText("");

                }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
