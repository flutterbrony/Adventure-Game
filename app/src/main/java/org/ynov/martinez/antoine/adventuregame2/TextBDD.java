package org.ynov.martinez.antoine.adventuregame2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by antoine on 14/02/18.
 */

public class TextBDD {
    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "AdventureGame.db";

    private static final String TABLE_TEXT = "text";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_TEXT = "texte";
    private static final int NUM_COL_TEXT = 1;
    private static final String COL_ID_STORY = "story_id";
    private static final int NUM_COL_ID_STORY = 2;

    private SQLiteDatabase bdd;

    private DataBaseHandler maBaseSQLite;

    public TextBDD(Context context){
        //On crée la BDD et sa table
        maBaseSQLite = new DataBaseHandler(context, NOM_BDD, null, VERSION_BDD);
    }

    public void open(){
        //on ouvre la BDD en écriture
        bdd = maBaseSQLite.getWritableDatabase();
    }

    public void close(){
        //on ferme l'accès à la BDD
        bdd.close();
    }

    public SQLiteDatabase getBDD(){
        return bdd;
    }

    public long insertText(Text text){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_TEXT, text.getText());
        values.put(COL_ID_STORY, text.getidstory());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_TEXT, null, values);
    }

    public int updateText(int id, Text text){
        //La mise à jour d'un story dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simplement préciser quel story on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(COL_TEXT, text.getText());
        values.put(COL_ID_STORY, text.getidstory());
        return bdd.update(TABLE_TEXT, values, COL_ID + " = " +id, null);
    }

    public int removeTextWithID(int id){
        //Suppression d'un story de la BDD grâce à l'ID
        return bdd.delete(TABLE_TEXT, COL_ID + " = " +id, null);
    }

    public Story getTextWithTitre(String idstory){
        //Récupère dans un Cursor les valeurs correspondant à un story contenu dans la BDD (ici on sélectionne le story grâce à son titre)
        Cursor c = bdd.query(TABLE_TEXT, new String[] {COL_ID, COL_TEXT, COL_ID_STORY}, COL_ID_STORY + " LIKE \"" + idstory +"\"", null, null, null, null);
        return cursorToStory(c);
    }

    public Story getTextByID(int id){
        //Récupère dans un Cursor les valeurs correspondant à un story contenu dans la BDD (ici on sélectionne le story grâce à son titre)
        Cursor c = bdd.query(TABLE_TEXT, new String[] {COL_ID, COL_TEXT, COL_ID_STORY}, COL_ID + " IS \"" + id +"\"", null, null, null, null);
        return cursorToStory(c);
    }

    //Cette méthode permet de convertir un cursor en un story
    private Story cursorToStory(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();
        //On créé un story
        Story story = new Story();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        story.setId(c.getInt(NUM_COL_ID));
        story.setAuthor(c.getString(NUM_COL_TEXT));
        story.setTitre(c.getString(NUM_COL_ID_STORY));
        //On ferme le cursor
        c.close();

        //On retourne le story
        return story;
    }
}
