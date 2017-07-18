package cc.metapro.nfc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import database.CardDbSchema.CardBaseHelper;
import database.CardDbSchema.CardCursorWrapper;
import database.CardDbSchema.CardDbSchema.CardTable;

/**
 * Created by Boollean on 2017/7/15.
 */

public class CardLab {

    private static CardLab sCardLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    private CardLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new CardBaseHelper(mContext).getWritableDatabase();
    }

    public static CardLab get(Context context) {
        if (sCardLab == null) {
            sCardLab = new CardLab(context);
        }
        return sCardLab;
    }

    private static ContentValues getContentValues(Card card) {
        ContentValues values = new ContentValues();
        values.put(CardTable.Cols.UUID, card.getId().toString());
        values.put(CardTable.Cols.TITLE, card.getTitle());
        values.put(CardTable.Cols.NO, card.getNo());
        values.put(CardTable.Cols.MESSAGE, card.getMessage());

        return values;
    }

    public void addCard(Card c) {
        ContentValues values = getContentValues(c);

        mDatabase.insert(CardTable.NAME, null, values);
    }

    public List<Card> getCards() {
        List<Card> cards = new ArrayList<>();

        CardCursorWrapper cursor = queryCards(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                cards.add(cursor.getCard());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return cards;
    }

    public Card getCard(UUID id) {
        CardCursorWrapper cursor = queryCards(
                CardTable.Cols.UUID + " = ?",
                new String[]{id.toString()}
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getCard();
        } finally {
            cursor.close();
        }
    }

    public void updateCard(Card card) {
        String uuidString = card.getId().toString();
        ContentValues values = getContentValues(card);

        mDatabase.update(CardTable.NAME, values,
                CardTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    public void deleteCard(Card card){
        String uuidString = card.getId().toString();

        mDatabase.delete(CardTable.NAME,CardTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    private CardCursorWrapper queryCards(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                CardTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new CardCursorWrapper(cursor);
    }
}
