package br.com.livroandroid.hellocamera;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;

import livroandroid.lib.utils.ImageResizeUtils;
import livroandroid.lib.utils.SDCardUtils;

public class MainActivity extends AppCompatActivity {
    private File file;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView)findViewById(R.id.imagem);
        ImageButton imageButton = (ImageButton)findViewById(R.id.btAbrirCamera);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                file = SDCardUtils.getPrivateFile(getBaseContext(), "foto.jpg", Environment.DIRECTORY_PICTURES);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Context context = MainActivity.this;

                Uri uri = FileProvider.getUriForFile(context,
                        getApplicationContext().getPackageName() + ".provider", file);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(intent, 0);
            }
        });

        if(savedInstanceState != null){
            file = (File)savedInstanceState.getSerializable("file");
            showImage(file);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("file", file);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && file != null){
            showImage(file);
        }
    }

    private void showImage(File file) {
        if(file != null && file.exists()){
            Log.d("foto", file.getAbsolutePath());
            int w = imageView.getWidth();
            int h = imageView.getHeight();
            Bitmap bitmap = ImageResizeUtils.getResizedImage(Uri.fromFile(file), w, h, false);
            imageView.setImageBitmap(bitmap);
        }

    }
}
