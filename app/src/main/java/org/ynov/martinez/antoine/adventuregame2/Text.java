package org.ynov.martinez.antoine.adventuregame2;

/**
 * Created by antoine on 14/02/18.
 */

public class Text {
    private int id;
    private String text;
    private int idstory;

    public Text(){}

    public Text(int id, int idstory, String text){
        this.id = id;
        this.text = text;
        this.idstory = idstory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getidstory() {
        return idstory;
    }

    public void setTitre(int idstory) {
        this.idstory = idstory;
    }

    public String toString(){
        return "ID : "+id+"\ntexte : "+text+"\nID Story Correspondante : "+idstory;
    }
}
