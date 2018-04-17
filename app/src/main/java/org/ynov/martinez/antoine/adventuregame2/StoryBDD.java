package org.ynov.martinez.antoine.adventuregame2;

import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

import android.content.ContentValues;
import android.content.Context;

/**
 * Created by antoine on 31/01/18.
 */

public class StoryBDD {
    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "AdventureGame.db";

    private static final String TABLE_STORY = "story";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_AUTHOR = "Author";
    private static final int NUM_COL_AUTHOR = 1;
    private static final String COL_TITRE = "Titre";
    private static final int NUM_COL_TITRE = 2;
    private static final String COL_DATE = "date";
    private static final int NUM_COL_DATE = 3;

    private SQLiteDatabase bdd;

    private DataBaseHandler maBaseSQLite;

    public StoryBDD(Context context){
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

    public long insertStory(Story story){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_AUTHOR, story.getAuthor());
        values.put(COL_TITRE, story.getTitre());
        values.put(COL_DATE, story.getDate());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_STORY, null, values);
    }

    public int updateStory(int id, Story story){
        //La mise à jour d'un story dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simplement préciser quel story on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(COL_AUTHOR, story.getAuthor());
        values.put(COL_TITRE, story.getTitre());
        values.put(COL_DATE, story.getDate());
        return bdd.update(TABLE_STORY, values, COL_ID + " = " +id, null);
    }

    public int removeStoryWithID(int id){
        //Suppression d'un story de la BDD grâce à l'ID
        return bdd.delete(TABLE_STORY, COL_ID + " = " +id, null);
    }

    public Story getStoryWithTitre(String titre){
        //Récupère dans un Cursor les valeurs correspondant à un story contenu dans la BDD (ici on sélectionne le story grâce à son titre)
        Cursor c = bdd.query(TABLE_STORY, new String[] {COL_ID, COL_AUTHOR, COL_TITRE, COL_DATE}, COL_TITRE + " LIKE \"" + titre +"\"", null, null, null, null);
        return cursorToStory(c);
    }

    public Story getStoryByID(int id){
        //Récupère dans un Cursor les valeurs correspondant à un story contenu dans la BDD (ici on sélectionne le story grâce à son titre)
        Cursor c = bdd.query(TABLE_STORY, new String[] {COL_ID, COL_TITRE, COL_AUTHOR, COL_DATE}, COL_ID + " IS \"" + id +"\"", null, null, null, null);
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
        story.setAuthor(c.getString(NUM_COL_AUTHOR));
        story.setTitre(c.getString(NUM_COL_TITRE));
        story.setDate(c.getString(NUM_COL_DATE));
        //On ferme le cursor
        c.close();

        //On retourne le story
        return story;
    }
}
