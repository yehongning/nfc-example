package cc.metapro.nfc;

import java.util.UUID;

/**
 * Created by Boollean on 2017/7/15.
 */

public class Card {
    private UUID mId;
    private String mTitle;
    private String mMessage;
    private String mOriginalInformation;
    private String mCardInformation;
    private String mTransactionInformation;

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

    public void setTitle(String Title) {
        mTitle = Title;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getOriginalInformation() {
        return mOriginalInformation;
    }

    public void setOriginalInformation(String originalInformation) {
        mOriginalInformation = originalInformation;
    }

    public String getCardInformation() {
        return mCardInformation;
    }

    public void setCardInformation(String cardInformation) {
        mCardInformation = cardInformation;
    }

    public String getTransactionInformation() {
        return mTransactionInformation;
    }

    public void setTransactionInformation(String transactionInformation) {
        mTransactionInformation = transactionInformation;
    }

}
