package andres.com.pruebaandroid;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class ActivityListadoApp extends Activity {

    private FragmentManager fragmentManager;
    private FragmentListadoAppPort fragmentListadoAppPort = new FragmentListadoAppPort();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_app);

        String categoria = getIntent().getStringExtra("categoria");
        fragmentListadoAppPort.categoria = categoria;

        fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragment_listado_app, fragmentListadoAppPort);

        fragmentTransaction.commit();
    }


}
