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
        String cardNo = getString(getColumnIndex(CardTable.Cols.NO));
        String message = getString(getColumnIndex(CardTable.Cols.MESSAGE));

        Card card = new Card(UUID.fromString(uuidString));
        card.setTitle(title);
        card.setNo(cardNo);
        card.setMessage(message);

        return card;
    }
}
