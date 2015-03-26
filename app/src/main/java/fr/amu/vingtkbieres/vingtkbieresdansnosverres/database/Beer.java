package fr.amu.vingtkbieres.vingtkbieresdansnosverres.database;

/**
 * Created by legeek on 12/03/15.
 */
public class Beer {
    private int overallScore;
    private int styleScore;
    private float abv;
    private String name;
    private String brewers;
    private String style;
    private String address;
    private String country;

    public Beer(int overallScore, int styleScore, float abv, String name, String brewers, String style, String address) {
        this.overallScore = overallScore;
        this.styleScore = styleScore;
        this.abv = abv;
        this.name = name;
        this.brewers = brewers;
        this.style = style;

        if( address.contains( "USA" ) ){
            this.country = "USA";
        }
        else{
            this.country = address.substring( address.lastIndexOf( ',' ) + 1 ).trim();
        }

        this.address = address.replace( country, "" ).trim();
    }

    public int getOverallScore() {
        return overallScore;
    }

    public int getStyleScore() {
        return styleScore;
    }

    public float getAbv() {
        return abv;
    }

    public String getName() {
        return name;
    }

    public String getBrewers() {
        return brewers;
    }

    public String getStyle() {
        return style;
    }

    public String getAddress() {
        return address;
    }

    public String getCountry() {
        return country;
    }
}