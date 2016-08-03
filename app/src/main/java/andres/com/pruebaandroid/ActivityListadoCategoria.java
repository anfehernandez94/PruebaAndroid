package andres.com.pruebaandroid;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class ActivityListadoCategoria extends Activity {

    private FragmentManager fragmentManager;
    private FragmentListadoCategoriaPort fragmentListadoCategoriaPort = new FragmentListadoCategoriaPort();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_categoria);

        fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragment_listado_categoria, fragmentListadoCategoriaPort);

        fragmentTransaction.commit();
    }
}
