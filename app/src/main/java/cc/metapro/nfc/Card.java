package cc.metapro.nfc;

import java.util.UUID;

/**
 * Created by ASUS on 2017/7/15.
 */

public class Card {

    private String mTitle;
    private String mMessage;
    private String mNo;
    private UUID mId;

    public Card() {
        this(UUID.randomUUID());
    }

    public Card(UUID id) {
        mId = id;
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getNo() {
        return mNo;
    }

    public void setNo(String no) {
        mNo = no;
    }

}
