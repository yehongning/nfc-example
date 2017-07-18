package database.CardDbSchema;

/**
 * Created by ASUS on 2017/7/16.
 */

public class CardDbSchema {
    public static final class CardTable {
        public static final String NAME = "cards";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String NO = "no";
            public static final String MESSAGE = "message";
        }
    }
}
