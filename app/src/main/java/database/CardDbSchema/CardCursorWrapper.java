package database.CardDbSchema;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import cc.metapro.nfc.Card;
import database.CardDbSchema.CardDbSchema.CardTable;

/**
 * Created by ASUS on 2017/7/9.
 */

public class CardCursorWrapper extends CursorWrapper {
    public CardCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Card getCard() {

        String uuidString = getString(getColumnIndex(CardTable.Cols.UUID));
        String title = getString(getColumnIndex(CardTable.Cols.TITLE));
        String message = getString(getColumnIndex(CardTable.Cols.MESSAGE));
        String originalInformation = getString(getColumnIndex(CardTable.Cols.OriginalInformation));
        String cardInformation = getString(getColumnIndex(CardTable.Cols.CardInformation));
        String transactionInformation = getString(getColumnIndex(CardTable.Cols.TransactionInformation));

        Card card = new Card(UUID.fromString(uuidString));
        card.setTitle(title);
        card.setMessage(message);
        card.setOriginalInformation(originalInformation);
        card.setCardInformation(cardInformation);
        card.setTransactionInformation(transactionInformation);

        return card;
    }
}
