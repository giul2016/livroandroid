package br.com.livroandroid.asstproject.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import br.com.livroandroid.asstproject.R;
import br.com.livroandroid.asstproject.entity.Girl;


public class GirlAdapter extends RecyclerView.Adapter<GirlAdapter.GirlViewHolder>{
    private final Girl girl;
    private final Context context;

    public GirlAdapter(Context context, Girl girl){
        this.context = context;
        this.girl = girl;
    }

    @Override
    public GirlViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_girl, parent, false);
        GirlViewHolder girlViewHolder = new GirlViewHolder(view);
        return girlViewHolder;
    }

    @Override
    public void onBindViewHolder(final GirlViewHolder holder, final int position) {
        holder.textViewNome.setText(girl.getNome());
        holder.progressBar.setVisibility(View.VISIBLE);
        String url = girl.getImagens().get(position);
        Picasso.with(context).load(url).fit().into(holder.imageViewImagem, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.GONE);
                Log.i("PICASSO", "DEU CERTO");
            }

            @Override
            public void onError() {
                holder.progressBar.setVisibility(View.GONE);
                Log.e("PICASSO", "Erro mesmo");
            }
        });

    }

    @Override
    public int getItemCount() {
        return girl == null ? 0 :
                (girl.getImagens() == null ? 0 : girl.getImagens().size());
    }

    public static class GirlViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNome;
        ProgressBar progressBar;
        ImageView imageViewImagem;
        public GirlViewHolder(View itemView) {
            super(itemView);
            this.textViewNome = (TextView)itemView.findViewById(R.id.nome);
            this.progressBar = (ProgressBar)itemView.findViewById(R.id.progress_bar_wait_image);
            this.imageViewImagem = (ImageView)itemView.findViewById(R.id.imagem);
        }
    }
}
