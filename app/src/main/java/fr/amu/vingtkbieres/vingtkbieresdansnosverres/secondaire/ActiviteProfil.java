package fr.amu.vingtkbieres.vingtkbieresdansnosverres.secondaire;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;
import java.util.Vector;

import fr.amu.vingtkbieres.vingtkbieresdansnosverres.R;
import fr.amu.vingtkbieres.vingtkbieresdansnosverres.menu.GlobalMenu;

public class ActiviteProfil extends ActionBarActivity {

	private PagerAdapter mPagerAdapter;
	private ViewPager mPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.viewpager);

		// Création de la liste de Fragments que fera défiler le PagerAdapter
		List fragments = new Vector();

		// Ajout des Fragments dans la liste
		fragments.add(Fragment.instantiate(this, MyBeersPage.class.getName()));
		fragments.add(Fragment.instantiate(this,AchievementsPage.class.getName()));

		// Création de l'adapter qui s'occupera de l'affichage de la liste de
		// Fragments
		this.mPagerAdapter = new ProfilPagerAdapter(super.getSupportFragmentManager(), fragments);

		mPager = (ViewPager) super.findViewById(R.id.viewpager);

		// Affectation de l'adapter au ViewPager
		mPager.setAdapter(this.mPagerAdapter);
	}

	public void swithToAchiev (View v) {
		mPager.setCurrentItem(1);
	}

	public void switchToMyBeers(View v) {
		mPager.setCurrentItem(0);
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.global, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if( GlobalMenu.handle(this, item) )
            return true;

        return super.onOptionsItemSelected( item );
    }
}