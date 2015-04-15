package fr.amu.vingtkbieres.vingtkbieresdansnosverres.geolocalisation;

import android.os.AsyncTask;
import android.widget.Toast;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import fr.amu.vingtkbieres.vingtkbieresdansnosverres.R;
import fr.amu.vingtkbieres.vingtkbieresdansnosverres.database.Database;
import fr.amu.vingtkbieres.vingtkbieresdansnosverres.database.JSONDataException;
import fr.amu.vingtkbieres.vingtkbieresdansnosverres.database.Pub;

public class PubLoader extends AsyncTask< Void, Void, Void > {

    List<Pub> tabPub = new ArrayList<>();

    @Override
    protected Void doInBackground(Void... params) {
        try {
            tabPub = Database.loadPub();
        } catch (JSONDataException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            //Toast.makeText(getBaseContext(), getBaseContext().getString(R.string.internetProblem), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    public List<Pub> getAllPub() {
        return this.tabPub;
    }
}
