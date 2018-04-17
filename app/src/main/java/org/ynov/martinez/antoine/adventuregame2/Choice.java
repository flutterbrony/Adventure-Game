package org.ynov.martinez.antoine.adventuregame2;

/**
 * Created by antoine on 14/02/18.
 */

public class Choice {
    private int id;
    private int text_id;
    private String choix;
    private int toid;

    public Choice(){}

    public Choice(int id, int text_id, int toid, String choix){
        this.id = id;
        this.choix = choix;
        this.text_id = text_id;
        this.toid = toid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTextId() {
        return text_id;
    }

    public void setTextId(int text_id) {
        this.text_id = text_id;
    }

    public String getChoix() {
        return choix;
    }

    public void setChoix(String choix) {
        this.choix = choix;
    }

    public int getToId() {
        return toid;
    }

    public void setToId(int toid) {
        this.toid = toid;
    }

    public String toString(){
        return "ID : "+id+"\ntext id : "+text_id+"\nChoix : "+choix+"\nto id : "+toid;
    }

    /*public String toString(){
        return choix;
    }*/

}
