package app.ifox.com.eopcandroid.model;

/**
 * Created by 13118467271 on 2017/10/16.
 */

public class ParkChatRoomMessage {
    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SEND = 1;

    private String content;
    private int type;

    public ParkChatRoomMessage(String content, int type) {
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public int getType() {
        return type;
    }
}
