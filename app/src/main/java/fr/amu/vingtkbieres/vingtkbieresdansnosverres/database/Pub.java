package fr.amu.vingtkbieres.vingtkbieresdansnosverres.database;

import android.os.Parcel;
import android.os.Parcelable;

public class Pub implements Parcelable {
    public final String name;
    public final String adress;
    public final String city;
    public final String postalCode;

    public Pub(String name, String adress, String city, String postalCode) {
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
        dest.writeString(postalCode);
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
        this.postalCode = in.readString();
    }
}
