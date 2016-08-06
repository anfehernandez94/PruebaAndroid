package andres.com.pruebaandroid;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class ActivityApp extends ActivityBase{

    private boolean flagTVSummary = false;

    private FragmentManager fragmentManager;
    private FragmentAppPort fragmentAppPort = new FragmentAppPort();
    private FragmentAppLand fragmentAppLand = new FragmentAppLand();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.animation_activity_app_port, R.anim.animation_empty);
        setContentView(R.layout.activity_app);

        fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        String id = getIntent().getStringExtra("id");

        boolean isTablet = getResources().getBoolean(R.bool.isTablet);
        if(isTablet){
            fragmentAppLand.id = id;
            fragmentTransaction.add(R.id.fragment_app, fragmentAppLand);
        }else{
            fragmentAppPort.id = id;
            fragmentTransaction.add(R.id.fragment_app, fragmentAppPort);
        }

        fragmentTransaction.commit();

    }


}
