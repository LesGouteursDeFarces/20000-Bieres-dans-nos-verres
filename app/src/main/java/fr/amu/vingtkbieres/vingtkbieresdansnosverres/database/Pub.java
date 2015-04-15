package fr.amu.vingtkbieres.vingtkbieresdansnosverres.database;

import android.os.Parcel;
import android.os.Parcelable;

public class Pub implements Parcelable {
    private final String name;
    private final String adress;
    private final String city;
    private final int postalCode;

    public Pub(String name, String adress, String city, int postalCode) {
        this.name = name;
        this.adress = adress;
        this.city = city;
        this.postalCode = postalCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(adress);
        dest.writeString(city);
        dest.writeInt(postalCode);
    }

    public static final Parcelable.Creator<Pub> CREATOR = new Parcelable.Creator<Pub>()
    {
        @Override
        public Pub createFromParcel(Parcel source){
            return new Pub(source);
        }

        @Override
        public Pub[] newArray(int size){
            return new Pub[size];
        }
    };

    public Pub(Parcel in) {
        this.name = in.readString();
        this.adress = in.readString();
        this.city = in.readString();
        this.postalCode = in.readInt();
    }

    public final String getName() {
        return name;
    }

    public final String getAdress() {
        return adress;
    }

    public final String getCity() {
        return city;
    }

    public final int getPostalCode() {
        return postalCode;
    }
}
