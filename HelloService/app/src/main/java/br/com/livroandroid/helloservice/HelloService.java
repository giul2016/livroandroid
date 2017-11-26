package br.com.livroandroid.helloservice;


import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class HelloService extends IntentService{
    private static final String TAG = "livroandroid";
    private static final int MAX = 10;
    protected int count;
    private boolean running;

    public HelloService() {
        super("LivroAndroid");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, ">> HelloService.onHandleIntent()");
        running = true;

        while(running && count < MAX){
            try{
                Thread.sleep(1000);
                Log.d(TAG, "HelloService executando..." + count);
                count++;
            }catch(InterruptedException e){

            }
        }
        Log.d(TAG, "<< HelloService.onHandleIntent()");
        NotificationUtil.notify(this, 1, intent, "Livro Android", "Fim do Serviço");
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "HelloService.onDestroy()");
        running = false;
        super.onDestroy();
    }
}
