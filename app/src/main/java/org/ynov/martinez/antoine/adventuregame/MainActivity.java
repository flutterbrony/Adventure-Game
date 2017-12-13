package org.ynov.martinez.antoine.adventuregame;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;

import static org.ynov.martinez.antoine.adventuregame.MainActivity.DatabaseHandler.ID_STORY;

public class MainActivity extends Activity {

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
        private DatabaseHandler mHandler = null;

        public DAO(Context pContext) {

        }

        private void DAOBase(Context pContext) {
            this.mHandler = new DatabaseHandler(pContext, NOM, null, VERSION);
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

    public class test extends DAO{
        public test(Context pContext) {
            super(pContext);
        }
        // Ajouter une occurrence dans la base
        public void insertOccurence(ID_STORY objet){
            ContentValues value = new ContentValues();
            try {value.put(ID_STORY, objet.getNomChamp());
            }
            catch (JSONException e){
                e.printStackTrace();
            }
            getDB().insert(ID_STORY, null, 1);
        }
    }

    private TextView stories;

    private Spinner spinner;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        //stories.setText("stories");

        //Récupération du Spinner déclaré dans le fichier main.xml de res/layout
        spinner = (Spinner) findViewById(R.id.spinner);
        //Création d'une liste d'élément à mettre dans le Spinner(pour l'exemple)
        List exempleList = new ArrayList();
        exempleList.add("choix 1");
        exempleList.add("choix 2");
        exempleList.add("choix 3");

		/*Le Spinner a besoin d'un adapter pour sa presentation alors on lui passe le context(this) et
                un fichier de presentation par défaut( android.R.layout.simple_spinner_item)
		Avec la liste des elements (exemple) */
        ArrayAdapter adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                exempleList
        );


               /* On definit une présentation du spinner quand il est déroulé         (android.R.layout.simple_spinner_dropdown_item) */
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Enfin on passe l'adapter au Spinner et c'est tout
        spinner.setAdapter(adapter);

    }
}