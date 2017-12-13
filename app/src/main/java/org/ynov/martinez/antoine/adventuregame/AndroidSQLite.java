/*package org.ynov.martinez.antoine.adventuregame;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by antoine on 13/12/17.
 */

/*public class AndroidSQLite {
    public class DatabaseHandler extends SQLiteOpenHelper {
        public static final String ID_STORY = "id";
        public static final String NAME_STORY = "name";
        public static final String DATE_STORY = "date";
        public static final String AUTHOR_STORY = "author";
        public static final String ID_CHOOSE = "id";
        public static final String TEXTID_CHOOSE = "text_id";
        public static final String CHOIX_CHOOSE = "choix";
        public static final String TOID_CHOOSE = "toid";
        public static final String ID_TEXT = "id";
        public static final String TEXTE_TEXT = "texte";
        public static final String STORYID_TEXT = "story_id";

        static final String STORY = "Story";
        static final String CHOOSE = "choose";
        static final String TEXT = "text";
        private static final String TABLE_CREATE =
                "CREATE TABLE IF NOT EXISTS " + STORY + " ("
                        + ID_STORY + " INTEGER PRIMARY KEY NOT NULL, "
                        + NAME_STORY + "varchar(255),"
                        + DATE_STORY + "date,"
                        + AUTHOR_STORY +"varchar(255));"
                        + "CREATE TABLE IF NOT EXISTS " + CHOOSE + " ("
                        + ID_CHOOSE + " INTEGER PRIMARY KEY NOT NULL, "
                        + TEXTID_CHOOSE + "int(10),"
                        + CHOIX_CHOOSE + "varcharr(255),"
                        + TOID_CHOOSE +"int(10));"
                        + "CREATE TABLE IF NOT EXISTS " + TEXT + " ("
                        + ID_TEXT + " INTEGER PRIMARY KEY NOT NULL, "
                        + TEXTE_TEXT + "int(10),"
                        + STORYID_TEXT + "varcharr(255);";
        // Constructeur
        DatabaseHandler(Context context, String name,
                        SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }
        @Override
        public void onCreate (SQLiteDatabase db) {
            db.execSQL(TABLE_CREATE);
        }
        @Override
        public void onUpgrade (SQLiteDatabase db, int oldVersion,
                               int newVersion) {
            switch (oldVersion) {
                case 1: // Passage de 1.00 à Version 2.00
                    //Même principe que dans le onCreate pour executer des requêtes.
                    break;
            }
        }
    }

    abstract class DAO {
        private final static int VERSION = 1;
        private final static String NOM = "AdventureGame.db" ;
        private SQLiteDatabase mDb = null;
        private MainActivity.DatabaseHandler mHandler = null;

        private void DAOBase(Context pContext) {
            this.mHandler = new MainActivity.DatabaseHandler(pContext, NOM, null, VERSION);
        }

        private void open() {
            mDb = mHandler.getWritableDatabase();
        }

        SQLiteDatabase getDB() {//Accès à la base en mode singleton
            if (mDb == null)
                open();
            return mDb;
        }
    }
}*/
