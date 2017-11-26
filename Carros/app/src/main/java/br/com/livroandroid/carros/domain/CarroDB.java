package br.com.livroandroid.carros.domain;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class CarroDB extends SQLiteOpenHelper {
    private static final String TAG = "sql";

    public static final String NOME_BANCO = "livro_android.sqlite";
    private static final int VERSAO_BANCO = 1;

    public CarroDB(Context context){
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Criando tabela do carro...");
        db.execSQL("create table if not exists carro(" +
                   "_id integer primary key autoincrement, " +
                   "nome text, desc text, url_foto text, url_video text," +
                   "latitude text, longitude text, tipo text);");
        Log.d(TAG, "Tabela carro criada com sucesso.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Caso mude a versão do banco podemos executa SQL aqui.
    }

    public long save(Carro carro){
        long id = carro.id;
        boolean exists = exists(carro.nome);
        SQLiteDatabase db = getWritableDatabase();
        try{
            ContentValues values = new ContentValues();
            values.put("nome", carro.nome);
            values.put("desc", carro.desc);
            values.put("url_foto", carro.urlFoto);
            values.put("url_video", carro.urlVideo);
            values.put("latitude", carro.latitude);
            values.put("longitude", carro.longitude);
            values.put("tipo", carro.tipo);
            if(exists){
                String _id = String.valueOf(carro.id);
                String[] whereArgs = new String[]{_id};
                int count = db.update("carro", values, "_id=?", whereArgs);
                return count;
            }else{
                id = db.insert("carro", "", values);
                return id;
            }
        }finally{
            db.close();
        }
    }

    public int delete(Carro carro){
        SQLiteDatabase db = getWritableDatabase();
        try{
            int count = db.delete("carro", "_id=?", new String[]{String.valueOf(carro.id)});
            Log.i(TAG, "Deletou [" + count + "] registro!");
            return count;
        }finally {
            db.close();
        }
    }

    public List<Carro> findAll(){
        SQLiteDatabase db = getWritableDatabase();
        try{
            Cursor query = db.query("carro", null, null, null, null, null, null, null);
            return toList(query);
        }finally {
            db.close();
        }
    }

    public boolean exists(String nome){
        SQLiteDatabase db = getWritableDatabase();
        try{
            Cursor c = db.query("carro", null, "nome=?", new String[]{nome}, null, null, null, null);
            boolean exists = c.getCount() > 0;
            return exists;
        }finally {
            db.close();
        }
    }

    public void execSQL(String sql){
        SQLiteDatabase db = getWritableDatabase();
        try{
            db.execSQL(sql);
        }finally {
            db.close();
        }
    }

    public void execSQL(String sql, Object[]args){
        SQLiteDatabase db = getWritableDatabase();
        try{
            db.execSQL(sql, args);
        }finally {
            db.close();
        }
    }

    private List<Carro> toList(Cursor query) {
        List<Carro> carros = new ArrayList<>();
        if(query.moveToFirst()){
            do{
                Carro carro = new Carro();
                carro.id = query.getLong(query.getColumnIndex("_id"));
                carro.nome = query.getString(query.getColumnIndex("nome"));
                carro.desc = query.getString(query.getColumnIndex("desc"));
                carro.urlFoto = query.getString(query.getColumnIndex("url_foto"));
                carro.urlVideo = query.getString(query.getColumnIndex("url_video"));
                carro.latitude = query.getString(query.getColumnIndex("latitude"));
                carro.longitude = query.getString(query.getColumnIndex("longitude"));
                carro.tipo = query.getString(query.getColumnIndex("tipo"));
                carros.add(carro);
            }while(query.moveToNext());
        }
        return carros;
    }

}
