package br.com.livroandroid.carros.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Carro implements Parcelable {
    public long id;

    public String tipo;

    public String nome;

    public String desc;

    @SerializedName("url_foto")
    public String urlFoto;

    @SerializedName("url_info")
    public String urlInfo;

    @SerializedName("url_video")
    public String urlVideo;

    public String latitude;

    public String longitude;

    public static final Creator<Carro> CREATOR = new Creator<Carro>() {
        @Override
        public Carro createFromParcel(Parcel in) {
            return new Carro().readFromParcel(in);
        }

        @Override
        public Carro[] newArray(int size) {
            return new Carro[size];
        }
    };

    @Override
    public String toString() {
        return "Carro{" +
                "nome='" + nome + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(tipo);
        dest.writeString(nome);
        dest.writeString(desc);
        dest.writeString(urlFoto);
        dest.writeString(urlInfo);
        dest.writeString(urlVideo);
        dest.writeString(latitude);
        dest.writeString(longitude);
    }

    public Carro readFromParcel(Parcel in) {
        id = in.readLong();
        tipo = in.readString();
        nome = in.readString();
        desc = in.readString();
        urlFoto = in.readString();
        urlInfo = in.readString();
        urlVideo = in.readString();
        latitude = in.readString();
        longitude = in.readString();
        return this;
    }


}
