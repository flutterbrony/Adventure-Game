package org.ynov.martinez.antoine.adventuregame2;

import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by antoine on 14/02/18.
 */

public class ChoiceBDD {
    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "AdventureGame.db";

    private static final String TABLE_CHOICE = "choice";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_TEXTID = "text_id";
    private static final int NUM_COL_TEXTID = 1;
    private static final String COL_CHOICE = "choix";
    private static final int NUM_COL_CHOICE = 2;
    private static final String COL_TOID = "toid";
    private static final int NUM_COL_TOID = 3;

    private SQLiteDatabase bdd;

    private DataBaseHandler maBaseSQLite;

    public ChoiceBDD(Context context){
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

    public long insertChoice(Choice choice){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_TEXTID, choice.getTextId());
        values.put(COL_CHOICE, choice.getChoix());
        values.put(COL_TOID, choice.getToId());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_CHOICE, null, values);
    }

    public int updateChoice(int id, Choice choice){
        //La mise à jour d'un story dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simplement préciser quel story on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(COL_TEXTID, choice.getTextId());
        values.put(COL_CHOICE, choice.getChoix());
        values.put(COL_TOID, choice.getToId());
        return bdd.update(TABLE_CHOICE, values, COL_ID + " = " +id, null);
    }

    public int removeChoiceWithID(int id){
        //Suppression d'un story de la BDD grâce à l'ID
        return bdd.delete(TABLE_CHOICE, COL_ID + " = " +id, null);
    }

    public Choice getChoiceWithTitre(String titre){
        //Récupère dans un Cursor les valeurs correspondant à un story contenu dans la BDD (ici on sélectionne le story grâce à son titre)
        Cursor c = bdd.query(TABLE_CHOICE, new String[] {COL_ID, COL_TEXTID, COL_CHOICE, COL_TOID}, COL_CHOICE + " LIKE \"" + titre +"\"", null, null, null, null);
        return cursorToChoice(c);
    }

    public Choice getChoiceByStoryID(int storyid){
        //Récupère dans un Cursor les valeurs correspondant à un story contenu dans la BDD (ici on sélectionne le story grâce à son titre)
        Cursor c = bdd.query(TABLE_CHOICE, new String[] {COL_ID, COL_TEXTID, COL_CHOICE, COL_TOID}, COL_TEXTID + " IS \"" + storyid +"\"", null, null, null, null);
        return cursorToChoice(c);
    }

    public List getChoiceByTextID(int textid) {
        Cursor c = bdd.query(TABLE_CHOICE, new String[]{COL_ID, COL_TEXTID, COL_CHOICE, COL_TOID}, COL_TEXTID + " IS \"" + textid + "\"", null, null, null, null);
        return cursoradapter(c);
    }

    private List cursoradapter(Cursor c){
        List choices = new ArrayList();
        while (c.moveToNext()) {
            Choice choice = new Choice();
            choice.setId(c.getInt(NUM_COL_ID));
            choice.setTextId(c.getInt(NUM_COL_TEXTID));
            choice.setChoix(c.getString(NUM_COL_CHOICE));
            choice.setToId(c.getInt(NUM_COL_TOID));
            choices.add(choice.getChoix()); // add .getChoix() to only get my choice in a string
        }
        return choices;
    }

    //Cette méthode permet de convertir un cursor en un choice
    private Choice cursorToChoice(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();
        //On créé un story
        Choice choice = new Choice();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        choice.setId(c.getInt(NUM_COL_ID));
        choice.setTextId(c.getInt(NUM_COL_TEXTID));
        choice.setChoix(c.getString(NUM_COL_CHOICE));
        choice.setToId(c.getInt(NUM_COL_TOID));
        //On ferme le cursor
        c.close();

        //On retourne le story
        return choice;
    }
}
