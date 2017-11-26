package br.com.livroandroid.carros.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import br.com.livroandroid.carros.CarrosApplication;
import br.com.livroandroid.carros.R;
import br.com.livroandroid.carros.domain.Carro;
import br.com.livroandroid.carros.domain.CarroDB;


public class CarroFragment extends BaseFragment {

    private Carro carro;
    private FloatingActionButton fab;
    private VideoView videoView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carro, container, false);

        carro = getArguments().getParcelable("carro");
        TextView tDesc = (TextView)view.findViewById(R.id.tDesc);
        tDesc.setText(carro.desc);

        fab = (FloatingActionButton)getActivity().findViewById(R.id.fabfavoritar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTask("favorito", taskFavoritar());
            }
        });

        videoView = (VideoView)view.findViewById(R.id.video);
        videoView.setMediaController(new MediaController(view.getContext()));
        videoView.setVideoURI(Uri.parse(carro.urlVideo));

        final ImageView imgPlay = (ImageView)view.findViewById(R.id.imgPlayVideo);
        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(videoView.isPlaying()){
                    videoView.stopPlayback();
                    imgPlay.setVisibility(View.VISIBLE);
                }else{
                    videoView.start();
                    imgPlay.setVisibility(View.INVISIBLE);
                }
            }
        });

        //Mapa
        MapaFragment mapaFragment = new MapaFragment();
        mapaFragment.setArguments(getArguments());
        getChildFragmentManager().beginTransaction().replace(R.id.mapaFragment, mapaFragment).commit();

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        startTask("checkFavorito", checkFavorito());
    }

    private TaskListener checkFavorito() {
        return new BaseTask<Boolean>(){
            @Override
            public Boolean execute() throws Exception {
                CarroDB db = new CarroDB(getContext());
                boolean exists = db.exists(carro.nome);
                return exists;
            }

            @Override
            public void updateView(Boolean response) {
                setFabColor(response);
            }
        };
    }

    private void setFabColor(Boolean response) {
        if(response){
            //fab.setImageTintList(ContextCompat.getColorStateList(getContext(), R.color.accent));
            fab.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.yellow));
        }else{
            //fab.setImageTintList(ContextCompat.getColorStateList(getContext(), R.color.accent));
            fab.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.gray));
        }
    }

    private TaskListener taskFavoritar() {
        return new BaseTask<Boolean>(){
            @Override
            public Boolean execute() throws Exception {
                CarroDB db = new CarroDB(getContext());
                boolean exists = db.exists(carro.nome);
                if(!exists){
                    db.save(carro);
                    return true;
                }else{
                    db.delete(carro);
                    return false;
                }
            }

            @Override
            public void updateView(Boolean response) {
                if(response){
                    snack(getView(), "Carro adicionado aos favritos");
                }else{
                    snack(getView(), "Carro removido dos favoritos");
                }
                setFabColor(response);
                CarrosApplication.getInstance().getBus().post("refresh");
            }
        };
    }
}
