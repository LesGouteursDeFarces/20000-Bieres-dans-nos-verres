package fr.amu.vingtkbieres.vingtkbieresdansnosverres.recherche;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import fr.amu.vingtkbieres.vingtkbieresdansnosverres.R;
import fr.amu.vingtkbieres.vingtkbieresdansnosverres.database.Beer;
import fr.amu.vingtkbieres.vingtkbieresdansnosverres.database.Database;
import fr.amu.vingtkbieres.vingtkbieresdansnosverres.database.JSONDataException;
import fr.amu.vingtkbieres.vingtkbieresdansnosverres.database.Style;

public class ResultatRechercheActivity extends Activity {
    private final int NUMBER_LIMIT = 6;

    protected ListView listBiere = null;
    private List<Beer> labelItems = new ArrayList<Beer>();
    private ResultatRechercheAdapter adapter;
    private ArrayList<Style> styles = new ArrayList<Style>();
    private String nameBeerSearch;
    private Button moreBtn;
    private int beginLimit;

    private class resultTask extends AsyncTask< Void, Void, Void > {

        private ProgressDialog progressDialog;
        List<Beer> tmpBeer;

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog( ResultatRechercheActivity.this );
            progressDialog.setMessage( "Recherche des bières..." );
            progressDialog.setIndeterminate( true );
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                for(int i = 0; i < styles.size(); ++i) {

                    if( nameBeerSearch == null )
                        tmpBeer = Database.searchBeerByStyle(styles.get(i).id, beginLimit, NUMBER_LIMIT);
                    else
                        tmpBeer = Database.searchBeerByStyleAndName( styles.get(i).id, nameBeerSearch, beginLimit, NUMBER_LIMIT );

                    if (tmpBeer != null)
                    {
                        for(int j = 0; j < tmpBeer.size(); ++j) {
                            labelItems.add(tmpBeer.get(j));
                        }
                    }

                }

                if( styles.isEmpty() ){
                    tmpBeer = Database.searchBeerByName( nameBeerSearch, beginLimit, NUMBER_LIMIT );

                    if (tmpBeer != null)
                    {
                        for(int j = 0; j < tmpBeer.size(); ++j) {
                            labelItems.add(tmpBeer.get(j));
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (JSONDataException e) {
                e.printStackTrace();
            } catch (UnknownHostException e) {
                Toast.makeText(getBaseContext(), getBaseContext().getString(R.string.internetProblem), Toast.LENGTH_LONG).show();
                finish();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();

            if(labelItems.isEmpty())
            {
                Toast.makeText(getBaseContext(), "Il n'y a pas de bière associée à cette recherche", Toast.LENGTH_SHORT).show();
                finish();
            }
            else
            {
                adapter = new ResultatRechercheAdapter(getBaseContext(),
                        R.layout.list_biere, labelItems);

                Collections.sort( labelItems );

                // Place les éléments
                listBiere.setAdapter(adapter);

                if( tmpBeer.size() < NUMBER_LIMIT ) {
                    moreBtn.setEnabled(false);
                    moreBtn.setText( "Toutes les bières sont là..." );
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_resultat_recherche);
            listBiere = (ListView) findViewById(R.id.listBiere);
            listBiere.setFastScrollEnabled(true);

            styles = this.getIntent().getParcelableArrayListExtra("style");
            nameBeerSearch = this.getIntent().getStringExtra( "nameBeer" );

            beginLimit = 0;

            new resultTask().execute();

        } catch (Exception e) {
            e.printStackTrace();
        }

        moreBtn = (Button) findViewById( R.id.button_more_beer );
        moreBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beginLimit += NUMBER_LIMIT;
                new resultTask().execute();
            }
        });

        listBiere.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                Intent detailBiere = new Intent(ResultatRechercheActivity.this, DetailBiere.class);
                Beer biere = (Beer) adapter.getItemAtPosition(position);

                detailBiere.putExtra("nomBiere", biere);

                startActivity(detailBiere);
                onPause();
            };
        });

    }
}


