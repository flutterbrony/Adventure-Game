package org.ynov.martinez.antoine.adventuregame2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int selectedItem;

    public void getQuery(int ItemSelected){
        System.out.println(ItemSelected);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView stories = (TextView)findViewById(R.id.stories);

        //Création d'une instance de ma classe StoryBDD
        StoryBDD storyBdd = new StoryBDD(this);
        //Création d'une instance de ChoiceBDD
        ChoiceBDD choiceBdd = new ChoiceBDD(this);
        // Création d'une instance de TextBDD
        TextBDD textBdd = new TextBDD(this);

        //Création d'un story
        Story story = new Story(1,"flutt", "Adventure Game", "14Feb2018");
        //création d'un premier choix
        Choice choice1 = new Choice(1,1, 2, "choix 1 Je reste au lit");
        Choice choice2 = new Choice(2,1, 3, "choix 2 Je me lève");
        Choice choice3 = new Choice(3,1, 4, "choix 3 Retour au début");
        //Création d'un text
        Text text1 = new Text(1, 1, "Vous vous réveillez.\nQue faites vous ?");
        Text text2 = new Text(2, 1, "Vous restez au lit.\nFIN");
        Text text3 = new Text(3, 1, "Vous vous levez, autour de vous, il y a une fenêtre aux; volets fermé par lesquels filtre de la lumière, un lit défait, une table, une commode avec votre reveil, et une porte.");

        //On ouvre la base de données pour écrire dedans
        storyBdd.open();
        choiceBdd.open();
        textBdd.open();

        //On insère le story que l'on vient de créer
        storyBdd.insertStory(story);
        choiceBdd.insertChoice(choice1);
        choiceBdd.insertChoice(choice2);
        choiceBdd.insertChoice(choice3);
        textBdd.insertText(text1);
        textBdd.insertText(text2);
        textBdd.insertText(text3);

        //Pour vérifier que l'on a bien créé notre story dans la BDD
        //on extrait le story de la BDD grâce au titre du story que l'on a créé précédemment
        Story storyFromBdd = storyBdd.getStoryWithTitre(story.getTitre());
        //on extrait le choix grace au titre créé précédemmennt
        Choice choiceFromBdd = choiceBdd.getChoiceWithTitre(choice1.getChoix());
        //Si un story est retourné (donc si le story à bien été ajouté à la BDD)
        if(storyFromBdd !=null)

        {
            //On affiche les infos du story dans un Toast
            Toast.makeText(this, storyFromBdd.toString(), Toast.LENGTH_LONG).show();
            //On modifie le titre du story
            storyFromBdd.setTitre("J'ai modifié le titre du livre");
            //Puis on met à jour la BDD
            storyBdd.updateStory(storyFromBdd.getId(), storyFromBdd);
        }

        //On extrait le story de la BDD grâce au nouveau titre
        storyFromBdd = storyBdd.getStoryWithTitre("J'ai modifié le titre du livre");
        //S'il existe un story possédant ce titre dans la BDD
            if(storyFromBdd !=null)

        {
            //On affiche les nouvelles informations du story pour vérifier que le titre du story a bien été mis à jour
            Toast.makeText(this, storyFromBdd.toString(), Toast.LENGTH_LONG).show();
            //on supprime le story de la BDD grâce à son ID
            storyBdd.removeStoryWithID(storyFromBdd.getId());
        }

        stories.setText(textBdd.getTextByID(1).getAuthor());

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        List exempleList = new ArrayList();
        if (textBdd.getTextByID(1) != null) {
            //S'il existe un story possédant cet id dans la BDD
            //int i = 1;
            //while(i < 100)
            //{
            //    storyFromBdd = storyBdd.getStoryByID(i);
            //    //On affiche les nouvelles informations du story pour vérifier que le titre du story a bien été mis à jour
            //    if (storyFromBdd != null) {
            //        exempleList.add(storyFromBdd.getTitre());
            //    }
            //    i++;
            //}*/
            //while (choiceBdd.testing(1) != null){
            //    List choiceArrayFromBdd = choiceBdd.testing(1);
            //    if (choiceFromBdd != null) {
            //        exempleList.add(choiceBdd.testing(1));
            //        choiceFromBdd.NextOccurence(choiceFromBdd.getId(), choiceFromBdd.getTextId());
            //    }
            //    i++;
            //}
            exempleList.addAll(choiceBdd.getChoiceByTextID(1));
        }
        else
        {
            exempleList.add("None");
        }

        //exempleList.add(storyBdd.getStoryWithTitre("Adventure Game"));
        ArrayAdapter adapter = new ArrayAdapter( this,
                android.R.layout.simple_spinner_item, exempleList );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        selectedItem = spinner.getSelectedItemPosition();
        //selectedItem = choiceBdd.getChoiceByTextID(1).get(selectedItem);
        Log.d("selected choice", choiceBdd.getChoiceByTextID(1).get(selectedItem).toString());
        System.out.println("testing");
        //testing some stuff
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("click ?");
                System.out.println(position);
                getQuery(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                System.out.println("noclick");
            }
        });
        //end testing

        //On ferme la Bdd
        storyBdd.close();
    }
}
