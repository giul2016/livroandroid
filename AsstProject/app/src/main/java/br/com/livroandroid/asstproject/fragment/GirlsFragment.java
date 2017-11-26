package br.com.livroandroid.asstproject.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.livroandroid.asstproject.R;
import br.com.livroandroid.asstproject.adapter.GirlAdapter;
import br.com.livroandroid.asstproject.entity.Girl;

import static android.R.attr.id;

public class GirlsFragment extends BaseFragment {

    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_girls, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view_girl);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView.setAdapter(new GirlAdapter(getContext(), buildGirl(getArguments().getString("nome"))));
    }

    public GirlsFragment setArgumentsAndReturn(Bundle args) {
        setArguments(args);
        return this;
    }

    public Girl buildGirl(String ident) {
        Girl girl = new Girl();
        List<String> imagens = new ArrayList<>();
        if(ident.equals(getString(R.string.christymack))){
            imagens.add("http://68.media.tumblr.com/56b24af48b8865f282449951e4e06c38/tumblr_mp3zb1LK3E1qfnh6so1_1280.jpg");
            imagens.add("https://bi.phncdn.com/videos/201707/23/125620261/original/(m=eaf8Ggaaaa)(mh=O0qcfeJ_CmBkcf4i)6.jpg");
            imagens.add("https://i.pinimg.com/originals/97/53/ac/9753ac75a0f1863ac5b5371a5b7578f1.jpg");
            imagens.add("https://pic.justporno.sex/images/1524/15242361_3.jpg");
            imagens.add("https://scontent.cdninstagram.com/t51.2885-15/e15/10251415_1382407748709421_177307227_n.jpg");
            imagens.add("http://img2.3movs.com/contents/videos_screenshots/6000/6501/240x180/2.jpg");
        }else if(ident.equals(getString(R.string.proxypaige))) {
            imagens.add("http://i.imgur.com/tsrKIWO.jpg");
            imagens.add("https://cdnp.kink.com/imagedb/38195/i/h/410/0.jpg");
            imagens.add("https://img2.adultdvdtalk.com/b0ecc6b6e15f48199e9abcaa6ab");
            imagens.add("http://thelordofporn.com/wp-content/uploads/2016/05/Proxy-Pagem10.jpg");
            imagens.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTTisOidFfFEplOoL28I3p1Pen0M-CtgRnqFhhur1tsu-9CozNSFg");
            imagens.add("http://68.media.tumblr.com/6943ec476528f304905ba4047395b4e5/tumblr_mzejy6BrCN1rwevcgo2_500.jpg");
        }else if(ident.equals(getString(R.string.remylacroix))) {
            imagens.add("http://cdn4.images.motherlessmedia.com/images/D01C4F9.jpg?fs=opencloud");
            imagens.add("http://www.boobsrealm.com/wp-content/uploads/2016/11/remy-lacroix-huge-ass.jpg");
            imagens.add("http://thelordofporn.com/wp-content/uploads/2014/03/Remy-LaCroix-10.jpg");
            imagens.add("http://thelordofporn.com/wp-content/uploads/2016/05/Proxy-Pagem10.jpg");
            imagens.add("https://thumb-v-ec.xhcdn.com/t/704/640/1_5346704.jpg");
            imagens.add("https://i.ytimg.com/vi/c5kvCSH87ok/hqdefault.jpg");
        }else if(ident.equals(getString(R.string.trillium))){
            imagens.add("https://cdn.pornpics.com/pics/2016-05-09/223774_11big.jpg");
            imagens.add("http://blog.gfrevenge.com/files/2015/05/Trillium-GF-Revenge-510x765.jpg");
            imagens.add("https://cdn.pornpics.com/pics/2016-10-10/242091_10big.jpg");
            imagens.add("https://cdn.pornpics.com/pics/2016-10-12/242271_04big.jpg");
            imagens.add("http://thelordofporn.com/wp-content/uploads/2016/08/Trillium-3.jpg");
            imagens.add("https://s8.trafficdeposit.com/blog/vid/3363005651/582ad8e8878ae/full.jpg");
        }else{
            girl = null;
        }
        if (girl != null) {
            girl.setNome(ident);
            girl.setImagens(imagens);
        }
        return girl;
    }
}
