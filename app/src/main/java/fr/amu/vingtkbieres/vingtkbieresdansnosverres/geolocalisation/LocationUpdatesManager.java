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

            if(tabPub.isEmpty()) {
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
                map.addMarkerFromAdress("413 Avenue Gaston Berger 13625 Aix-en-Provence", "IUT AIX", MyMarker.COLOR_RED);
                map.addMarkerFromAdress("Marseille", "Marseille", MyMarker.COLOR_RED);
            }
        }
    }
}