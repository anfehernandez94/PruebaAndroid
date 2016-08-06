package andres.com.pruebaandroid;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ActivityBase extends Activity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    void ordenar(ArrayList<String> array){
        Collections.sort(array, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        });
    }

    /**
     * Activa y desactiva la conexión que obtiene el estado de la red wifi y móvil
     */
    private static IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
    private static EstadoConeccion receiver = new EstadoConeccion();

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
        overridePendingTransition(0, R.anim.animation_translate_right);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, filter);
    }

}
