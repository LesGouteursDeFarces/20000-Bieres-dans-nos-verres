package fr.amu.vingtkbieres.vingtkbieresdansnosverres;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import fr.amu.vingtkbieres.vingtkbieresdansnosverres.geolocalisation.MapsActivity;
import fr.amu.vingtkbieres.vingtkbieresdansnosverres.menu.GlobalMenu;
import fr.amu.vingtkbieres.vingtkbieresdansnosverres.recherche.RechercheActivity;
import fr.amu.vingtkbieres.vingtkbieresdansnosverres.secondaire.ActiviteProfil;

public class mainPage extends ActionBarActivity {

    private ArrayList<String> quote = new ArrayList<>();
    private TextView note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        ImageButton boutonGeoloc = (ImageButton) findViewById(R.id.imageBoutonGeoloc);
        boutonGeoloc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(mainPage.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        ImageButton boutonRecherche = (ImageButton) findViewById(R.id.imageBoutonRecherche);
        boutonRecherche.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(mainPage.this, RechercheActivity.class);
                startActivity(intent);
            }
        });

        note = (TextView) findViewById(R.id.note);
        note.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFact();
            }
        });

        quote.add("Toutes les bières ne sont pas faites avec du houblon !");
        quote.add("La lumière est dangereuse pour vos bière! Gardez les a l'abri !");
        quote.add("Les travailleurs des pyramides égyptiennes ont été payés avec de la bière : 1 gallon (4L) par jour!");
        quote.add("La plus ancienne recette de bière  connue a plus de 4000 ans, créée par les Sumériens");
        quote.add("Au Moyen Age, la bière était plus consommée que l'eau, alcool la rendait plus sûre");
        quote.add("Les limaces aussi aiment la bière!");
        quote.add("Au Moyen Age, la bière était plus consommée que l'eau, alcool la rendait plus sûre");
        quote.add("La bière n'était pas considéré comme une boisson alcolisée en russie jusqu'en 2013 !");
        quote.add("Dionysos, le dieu grec du vin, a été inspiré par le dieu de la bière lydien et phrygien moins connu, Sabasios.");
        quote.add("Saviez-vous que le saké japonais est en fait un type de bière, et non un vin ?");
        quote.add("La bière ne contient pas de matières grasses.");
        quote.add("La bière contient des protéines et des glucides.");
        quote.add("En cas d’allaitement, la bière favorise la montée du lait (voir votre médecin traitant).");
        quote.add("Pour rendre les crêpes plus légères faire moitié bière moitié lait.");
        quote.add("Pour avoir de belles plantes d'appartement, nettoyez les feuilles avec de la bière.");
        quote.add("Les ingrédients contenus dans la bière préviennent la formation des caillots dans le sang.");
        quote.add("Des chercheurs de l’université de Montréal ont établi que deux verres de bière par jour diminuent le stress ou les angoisses liées au travail.");
        quote.add("Selon des études, les buveurs de bière auraient moins de risques de souffrir de la maladie d’Alzheimer");
        quote.add("La bière contient de l’acide nicotinique et du lactoflavin, tous deux connus pour favoriser les « Zzz Zzzzzzzzz »!");
        quote.add("Une étude de 2009 a conclu que les niveaux élevés de silicium dans la bière peuvent être bon pour votre densité osseuse.");
        quote.add("Un buveur de bières a 40 à 60% moins de risques de faire un infarctus que les gens qui n’en boivent jamais.");
        quote.add("Les buveurs de bière souffrent rarement du diabète dit sucré.");
        quote.add("Les ingrédients de la bière nous désaltèrent plus vite que l'eau.");

        changeFact();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        changeFact();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.global, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if( GlobalMenu.handle( this, item ) )
            return true;

        return super.onOptionsItemSelected( item );
    }

    private void changeFact(){
        Random random = new Random();
        int randomInt = random.nextInt(quote.size() - 1);
        note.setText(quote.get(randomInt));
    }
}
