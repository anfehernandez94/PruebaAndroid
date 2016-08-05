package andres.com.pruebaandroid;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Toast;

public class ActivityListadoCategoria extends Activity {

    private FragmentManager fragmentManager;
    private FragmentListadoCategoriaPort fragmentListadoCategoriaPort = new FragmentListadoCategoriaPort();
    private FragmentListadoCategoriaLand fragmentListadoCategoriaLand = new FragmentListadoCategoriaLand();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_categoria);


        fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        boolean isTablet = getResources().getBoolean(R.bool.isTablet);

        if(isTablet){
            fragmentTransaction.add(R.id.fragment_listado_categoria, fragmentListadoCategoriaLand);
        }else{
            fragmentTransaction.add(R.id.fragment_listado_categoria, fragmentListadoCategoriaPort);
        }

        fragmentTransaction.commit();
    }
}
