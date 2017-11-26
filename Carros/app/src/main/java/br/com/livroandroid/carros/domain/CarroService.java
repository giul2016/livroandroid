package br.com.livroandroid.carros.domain;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import br.com.livroandroid.carros.R;
import livroandroid.lib.utils.FileUtils;
import livroandroid.lib.utils.HttpHelper;
import livroandroid.lib.utils.XMLUtils;

import static br.com.livroandroid.carros.R.string.carros;

public class CarroService {
    private static final String URL = "http://www.livroandroid.com.br/livro/carros/v2/carros_{tipo}.json";

    public static List<Carro> getCarros(Context context, int tipo) throws IOException {
        List<Carro> carros = null;
        if(tipo == R.string.favoritos){
            CarroDB db = new CarroDB(context);
            carros = db.findAll();
        }else {
            String url = URL.replace("{tipo}", getTipo(tipo));
            HttpHelper http = new HttpHelper();

            String json = http.doGet(url);
            carros = parserJSON(context, json);
        }
        return carros;
    }

    private static String getTipo(int tipo) {
        if(tipo == R.string.classicos){
            return "classicos";
        }else if(tipo == R.string.esportivos){
            return "esportivos";
        }
        return "luxo";
    }


    private static String readFile(Context context, int tipo) throws IOException {
        if(tipo == R.string.classicos){
            return FileUtils.readRawFileString(context, R.raw.carros_classicos, "UTF-8");
        }else if(tipo == R.string.esportivos){
            return FileUtils.readRawFileString(context, R.raw.carros_esportivos, "UTF-8");
        }
        return FileUtils.readRawFileString(context, R.raw.carros_luxo, "UTF-8");
    }

    private static List<Carro> parserJSON(Context context, String json) {
        Type listType = new TypeToken<ArrayList<Carro>>(){}.getType();
        List<Carro> carros = new Gson().fromJson(json, listType);
        return carros;
    }
/*
    private static List<Carro> parserXML(Context context, String xml) {
        List<Carro> carros = new ArrayList<>();
        Element root = XMLUtils.getRoot(xml,  "UTF-8");
        List<Node> nodeCarros = XMLUtils.getChildren(root, "carro");

        for(Node node : nodeCarros){
            Carro carro = new Carro();

            carro.nome = XMLUtils.getText(node, "nome");
            carro.desc = XMLUtils.getText(node, "desc");
            carro.urlFoto = XMLUtils.getText(node, "url_foto");
            carro.urlInfo = XMLUtils.getText(node, "url_info");
            carro.urlVideo = XMLUtils.getText(node, "url_video");
            carro.latitude = XMLUtils.getText(node, "latitude");
            carro.longitude = XMLUtils.getText(node, "longitude");
            if(LOG_ON){
                Log.d(TAG, carros.size() + " encontrados.");
            }
            carros.add(carro);
        }

        if(LOG_ON){
            Log.d(TAG, carros.size() + " encontrados.");
        }
        return carros;
    }*/
}
