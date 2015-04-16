package fr.amu.vingtkbieres.vingtkbieresdansnosverres.geolocalisation;
import fr.amu.vingtkbieres.vingtkbieresdansnosverres.database.Pub;

import android.location.Location;
import android.location.LocationListener;

import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;
import java.util.List;

public class LocationUpdatesManager implements LocationListener, GoogleMap.OnMyLocationChangeListener {

    public MapsActivity map;
    public boolean isBegin = true;

    List<Pub> tabPub = new ArrayList<>();

    public LocationUpdatesManager(MapsActivity map) {
        this.map = map;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onMyLocationChange(Location location) {

        // Update camera position when user's position founded
        if (isBegin) {

            isBegin = false;

            map.setMyLocation(location);
            map.zoomPosition(map.getMyLocation(), 14);

            //PubLoader loader = new PubLoader();
            //loader.execute();

            //tabPub = loader.getAllPub();

            if(!tabPub.isEmpty()) {
                for(int i = 0; i < tabPub.size(); ++i) {
                    String name = tabPub.get(i).getName();
                    String adress = tabPub.get(i).getAdress();
                    int postalCode = tabPub.get(i).getPostalCode();
                    String city = tabPub.get(i).getCity();

                    String buff = "";

                    if(!name.equals("null"))
                        buff = buff + name;
                    else
                        name = "Undefined";

                    if(!adress.equals("null"))
                        buff = buff + adress;

                    if(postalCode != 0)
                        buff = buff + Integer.toString(postalCode);

                    if(!city.equals("null"))
                        buff = buff + city;

                    map.addMarkerFromAdress(buff, name, MyMarker.COLOR_RED);
                }
            }
            else {
                // Create marker test without database
                map.addMarkerFromAdress("30 rue de la Verrerie 13100 Aix-en-Provence", "O Shannon", MyMarker.COLOR_RED);
                map.addMarkerFromAdress("13 Cours Sextius 13100 Aix-en-Provence", "Le Sextius Bar", MyMarker.COLOR_RED);
                map.addMarkerFromAdress("17 place Richelme 13100 Aix-en-Provence", "Le Brigand", MyMarker.COLOR_RED);
                map.addMarkerFromAdress("25 rue de la Verrerie 13100 Aix-en-Provence", "Le Manoir", MyMarker.COLOR_RED);
                map.addMarkerFromAdress("25 Rue du Bon Pasteur 13100 Aix-en-Provence", "L’Unplugged", MyMarker.COLOR_RED);
                map.addMarkerFromAdress("176 bd Chave 13005 Marseille", "The Black Unicorn", MyMarker.COLOR_RED);
                map.addMarkerFromAdress("17 quai de Rive Neuve 13007 Marseille", "The Shamrock", MyMarker.COLOR_RED);
                map.addMarkerFromAdress("7 rue de la Paix Marcel Paul 13001 Marseille", "Little Temple Bar", MyMarker.COLOR_RED);
                map.addMarkerFromAdress("127 rue Sainte 13007 Marseille", "U-percut", MyMarker.COLOR_RED);
                map.addMarkerFromAdress("378 ave de Mazargues 13008 Marseille", "O’Brady’s Irish Pub", MyMarker.COLOR_RED);
            }
        }
    }
}