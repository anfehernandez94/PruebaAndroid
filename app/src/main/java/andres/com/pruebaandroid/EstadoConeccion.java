package andres.com.pruebaandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class EstadoConeccion extends BroadcastReceiver{

    private static boolean isConnect = false;

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager conManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if ( conManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED
            || conManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTING ) {
            if(!isConnect) {
                isConnect = true;
                Toast.makeText(context, "¡Enhorabuena! Estás conectado", Toast.LENGTH_LONG).show();
            }
        }
        else if ( conManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.DISCONNECTED
            || conManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.DISCONNECTED) {
            if(isConnect) {
                isConnect = false;
                Toast.makeText(context, "Mmm no hay conexión :(", Toast.LENGTH_LONG).show();
            }
        }
    }
}
