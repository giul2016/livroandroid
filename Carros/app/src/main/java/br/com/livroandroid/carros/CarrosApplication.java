package br.com.livroandroid.carros;

import android.app.Application;
import android.util.Log;

import com.squareup.otto.Bus;

public class CarrosApplication extends Application {
    private static final String TAG = "CarrosApplication";
    private static CarrosApplication instance = null;

    private Bus bus = new Bus();

    public static CarrosApplication getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "CarrosApplication.onCreate()");
        instance = this;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d(TAG, "CarrosApplication.onTerminate()");
    }

    public Bus getBus(){
        return bus;
    }
}
