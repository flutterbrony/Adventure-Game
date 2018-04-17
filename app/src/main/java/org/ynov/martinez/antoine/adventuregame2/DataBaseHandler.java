package org.ynov.martinez.antoine.adventuregame2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by antoine on 31/01/18.
 */

public class DataBaseHandler extends SQLiteOpenHelper {
    public static final String TABLE_STORY = "story";
    public static final String COL_ID_STORY = "ID";
    public static final String COL_AUTHOR_STORY = "Author";
    public static final String COL_TITRE_STORY = "Titre";
    public static final String COL_DATE_STORY = "date";

    public static final String TABLE_CHOICE = "choice";
    public static final String COL_ID_CHOICE = "ID";
    public static final String COL_TEXTID_CHOICE = "text_id";
    public static final String COL_CHOIX_CHOICE = "choix";
    public static final String COL_TOID_CHOICE = "toid";

    public static final String TABLE_TEXT = "text";
    public static final String COL_ID_TEXT = "ID";
    public static final String COL_TEXTE_TEXT = "texte";
    public static final String COL_STORYID_TEXT = "story_id";

    private static final String CREATE_STORY_BDD = "CREATE TABLE " + TABLE_STORY + " ("
            + COL_ID_STORY + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_AUTHOR_STORY + " TEXT NOT NULL, "
            + COL_TITRE_STORY + " TEXT NOT NULL, " + COL_DATE_STORY + " DATE NOT NULL);";

    private static final String CREATE_CHOICE_BDD = "CREATE TABLE " + TABLE_CHOICE + " ("
            + COL_ID_CHOICE + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_TEXTID_CHOICE + " INTEGER NOT NULL, "
            + COL_CHOIX_CHOICE + " TEXT NOT NULL, " + COL_TOID_CHOICE + " INTEGER NOT NULL);";

    private static final String CREATE_TEXT_BDD = "CREATE TABLE " + TABLE_TEXT + " ("
            + COL_ID_TEXT + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_TEXTE_TEXT + " TEXT NOT NULL, "
            + COL_STORYID_TEXT + " INTEGER NOT NULL);";

    public DataBaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //on crée la table à partir de la requête écrite dans la variable CREATE_BDD
        db.execSQL(CREATE_STORY_BDD);
        db.execSQL(CREATE_CHOICE_BDD);
        db.execSQL(CREATE_TEXT_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //On peut faire ce qu'on veut ici moi j'ai décidé de supprimer la table et de la recréer
        //comme ça lorsque je change la version les id repartent de 0
        db.execSQL("DROP TABLE " + TABLE_STORY + ";");
        db.execSQL("DROP TABLE " + TABLE_CHOICE + ";");
        db.execSQL("DROP TABLE " + TABLE_TEXT + ";");
        onCreate(db);
    }
}
