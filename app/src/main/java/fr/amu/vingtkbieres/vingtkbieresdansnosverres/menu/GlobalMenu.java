package fr.amu.vingtkbieres.vingtkbieresdansnosverres.menu;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.Toast;

import fr.amu.vingtkbieres.vingtkbieresdansnosverres.R;
import fr.amu.vingtkbieres.vingtkbieresdansnosverres.mainPage;
import fr.amu.vingtkbieres.vingtkbieresdansnosverres.secondaire.ActiviteProfil;

public class GlobalMenu {
    public static Boolean handle( Activity context, MenuItem item ){

        Intent intent = null;
        switch ( item.getItemId() ){
            case R.id.global_section_1:
                if( !context.getLocalClassName().equalsIgnoreCase( "mainPage" ) )
                    intent = new Intent( context, mainPage.class );

                break;

            case R.id.global_section_2:
                if( !context.getLocalClassName().equalsIgnoreCase( "secondaire.ActiviteProfil" ) )
                    intent = new Intent( context, ActiviteProfil.class );
                break;

            case R.id.global_section_3:
                Toast.makeText( context, "A bientôt !", Toast.LENGTH_SHORT).show();
                System.exit( 0 );
                break;

            default:
                return false;
        }

        if( intent != null ) {
            context.startActivity(intent);
            context.finish();
        }

        return true;
    }
}
