package fr.amu.vingtkbieres.vingtkbieresdansnosverres.secondaire;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.net.UnknownHostException;

import fr.amu.vingtkbieres.vingtkbieresdansnosverres.R;
import fr.amu.vingtkbieres.vingtkbieresdansnosverres.database.Database;
import fr.amu.vingtkbieres.vingtkbieresdansnosverres.database.JSONDataException;
import fr.amu.vingtkbieres.vingtkbieresdansnosverres.database.User;

/**
 * Created by legeek on 15/04/15.
 */
public class MyProfile extends Fragment {

    private TextView firstnameView;
    private TextView lastnameView;
    private TextView adrView;


    private class infoTask extends AsyncTask< Void, Void, Void >{

        User usr = null;
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog( getActivity() );
            progressDialog.setMessage( "Chargement du profil..." );
            progressDialog.setIndeterminate( true );
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                usr = Database.getUserById( 4 );

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (JSONDataException e) {
                e.printStackTrace();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
            if( usr == null ){
                Toast.makeText( getActivity(), getText( R.string.internetProblem), Toast.LENGTH_LONG).show();
                return;
            }

            firstnameView.setText( usr.firstname );
            lastnameView.setText( usr.lastname );
            adrView.setText( usr.email );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_profile, container, false);

        firstnameView = (TextView) v.findViewById( R.id.textView_firstname_profile );
        lastnameView = (TextView) v.findViewById( R.id.textView_lastname_profile );
        adrView = (TextView) v.findViewById( R.id.textView_mail_profile );

        
        
        new infoTask().execute();

        return v;
    }
}
