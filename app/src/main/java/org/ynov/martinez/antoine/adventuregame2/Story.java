package org.ynov.martinez.antoine.adventuregame2;

/**
 * Created by antoine on 31/01/18.
 */

public class Story {
    private int id;
    private String author;
    private String titre;
    private String date;

    public Story(){}

    public Story(int id, String author, String titre, String date){
        this.id = id;
        this.titre = titre;
        this.author = author;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String isbn) {
        this.author = isbn;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String toString(){
        return "ID : "+id+"\nAuteur : "+author+"\nTitre : "+titre+"\nDate : "+date;
    }
}
