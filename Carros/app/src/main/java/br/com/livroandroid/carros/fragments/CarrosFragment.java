package br.com.livroandroid.carros.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import br.com.livroandroid.carros.CarrosApplication;
import br.com.livroandroid.carros.R;
import br.com.livroandroid.carros.activity.CarroActivity;
import br.com.livroandroid.carros.adapter.CarroAdapter;
import br.com.livroandroid.carros.domain.Carro;
import br.com.livroandroid.carros.domain.CarroService;
import livroandroid.lib.utils.AndroidUtils;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class CarrosFragment extends BaseFragment {
    private ProgressDialog dialog;
    private int tipo;
    private RecyclerView recyclerView;
    private List<Carro> carros;


    public static CarrosFragment newInstance(int tipo){
        Bundle args = new Bundle();
        args.putInt("tipo", tipo);
        CarrosFragment carrosFragment = new CarrosFragment();
        carrosFragment.setArguments(args);
        return carrosFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            this.tipo = getArguments().getInt("tipo");
        }
        CarrosApplication.getInstance().getBus().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        CarrosApplication.getInstance().getBus().unregister(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carros, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        taskCarros();
    }

    private void taskCarros() {
       new GetCarrosTask().execute();
    }

    private class GetCarrosTask extends AsyncTask<Void, Void, List<Carro>>{

        @Override
        protected List<Carro> doInBackground(Void... params) {
            try {
                return CarroService.getCarros(getContext(), tipo);
            }catch(IOException e){
                Log.e(TAG, "Erro: " + e.getMessage());
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Carro> carros) {
            if(carros != null){
                CarrosFragment.this.carros = carros;
                recyclerView.setAdapter(new CarroAdapter(getContext(), carros, onClickCarro()));
            }
        }
    }

    private CarroAdapter.CarroOnClickListener onClickCarro() {
        return new CarroAdapter.CarroOnClickListener() {
            @Override
            public void onClickCarro(View view, int idx) {
                Carro carro = carros.get(idx);
                Intent intent = new Intent();
                intent.setClass(getContext(), CarroActivity.class);
                intent.putExtra("carro", carro);
                startActivity(intent);
            }
        };
    }

    @Subscribe
    public void onBusAtualizarListaCarros(String refresh){
        taskCarros();
    }
}