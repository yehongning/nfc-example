package database.CardDbSchema;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import database.CardDbSchema.CardDbSchema.CardTable;

/**
 * Created by ASUS on 2017/7/9.
 */

public class CardBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "cardBase.db";

    public CardBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + CardTable.NAME + "(" + " _id integer primary key autoincrement," +
                CardTable.Cols.UUID + ", " +
                CardTable.Cols.TITLE + ", " +
                CardTable.Cols.MESSAGE + ", " +
                CardTable.Cols.OriginalInformation + ", " +
                CardTable.Cols.CardInformation + ", " +
                CardTable.Cols.TransactionInformation +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
