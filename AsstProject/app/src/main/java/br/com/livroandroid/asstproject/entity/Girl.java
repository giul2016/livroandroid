package br.com.livroandroid.asstproject.entity;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Girl implements Parcelable{
    private String nome;
    private List<String> imagens;

    public static final Creator<Girl> CREATOR = new Creator<Girl>() {
        @Override
        public Girl createFromParcel(Parcel in) {
            Girl girl = new Girl();
            girl.readParcel(in);
            return girl;
        }

        @Override
        public Girl[] newArray(int size) {
            return new Girl[size];
        }
    };

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getImagens() {
        return imagens;
    }

    public void setImagens(List<String> imagens) {
        this.imagens = imagens;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nome);
        dest.writeStringList(imagens);
    }

    public void readParcel(Parcel in){
        nome = in.readString();
        imagens = in.createStringArrayList();
    }
}
