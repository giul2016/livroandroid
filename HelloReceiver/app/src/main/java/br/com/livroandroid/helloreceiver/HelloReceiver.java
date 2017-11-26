package br.com.livroandroid.helloreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class HelloReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context  context, Intent intent) {
        Log.d("livroandroid", " Hello Receiver !!!");

        Intent intent1 = new Intent(context, MainActivity.class);
        NotificationUtils.create(context, 1, intent1, "Livro Android", "Hello Receiver!");
    }
}
