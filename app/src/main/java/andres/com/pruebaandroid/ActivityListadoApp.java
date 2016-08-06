package andres.com.pruebaandroid;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class ActivityListadoApp extends ActivityBase {

    private FragmentManager fragmentManager;
    private FragmentListadoAppPort fragmentListadoAppPort = new FragmentListadoAppPort();
    private FragmentListadoAppLand fragmentListadoAppLand = new FragmentListadoAppLand();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.animation_translate_left2, R.anim.animation_empty);
        setContentView(R.layout.activity_listado_app);

        fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        String categoria = getIntent().getStringExtra("categoria");

        boolean isTablet = getResources().getBoolean(R.bool.isTablet);
        if(isTablet){
            fragmentListadoAppLand.categoria = categoria;
            fragmentTransaction.add(R.id.fragment_listado_app, fragmentListadoAppLand);
        }else{
            fragmentListadoAppPort.categoria = categoria;
            fragmentTransaction.add(R.id.fragment_listado_app, fragmentListadoAppPort);
        }

        fragmentTransaction.commit();
    }

}
