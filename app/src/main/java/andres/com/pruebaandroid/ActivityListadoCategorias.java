package andres.com.pruebaandroid;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class ActivityListadoCategorias extends Activity {

    FragmentManager fragmentManager;
    FragmentListadoCategoriaPort fragmentListadoCategoriaPort = new FragmentListadoCategoriaPort();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_categorias);

        fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragment_listado_categoria, fragmentListadoCategoriaPort);
        fragmentTransaction.show(fragmentListadoCategoriaPort);

        fragmentTransaction.commit();
    }


}
