package andres.com.pruebaandroid;


import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;


public class FragmentAppPort extends Fragment implements View.OnClickListener {

    View view;
    private TextView tvCategoria, tvNombre, tvSummary, tvCurrency, tvAmount,
            tvReleaseDate, tvRights, tvEmpresa;
    private Button btnComprar;
    private ImageView ivAppLogo;
    private boolean flagTVSummary = false;

    String id;

    public FragmentAppPort() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view == null){
            view = inflater.inflate(R.layout.fragment_app_port, container, false);
        }
        final Activity activity = getActivity();
        if(activity != null)
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ivAppLogo = (ImageView) view.findViewById(R.id.iv_app_logo);
        btnComprar = (Button)view.findViewById(R.id.btn_app_buy);
        tvCategoria = (TextView) view.findViewById(R.id.tv_app_categoria);
        tvNombre = (TextView) view.findViewById(R.id.tv_app_nombre);
        tvEmpresa = (TextView) view.findViewById(R.id.tv_app_empresa);
        tvSummary = (TextView) view.findViewById(R.id.tv_app_summary);
        tvCurrency = (TextView) view.findViewById(R.id.tv_app_currency);
        tvAmount = (TextView) view.findViewById(R.id.tv_app_amount);
        tvReleaseDate = (TextView) view.findViewById(R.id.tv_app_releaseDate);
        tvRights = (TextView) view.findViewById(R.id.tv_app_rights);

        final ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions opts = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true).build();
        ImageLoaderConfiguration configImageLoader = new ImageLoaderConfiguration.Builder(getActivity())
                .defaultDisplayImageOptions(opts).build();
        imageLoader.init(configImageLoader);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("feed/entry/"+ id);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dss) {
                tvCategoria.setText(dss.child("category/attributes/label").getValue().toString());
                tvNombre.setText(dss.child("im:name/label").getValue().toString());
                tvEmpresa.setText(dss.child("im:artist/label").getValue().toString());
                tvSummary.setText(dss.child("summary/label").getValue().toString());
                tvCurrency.setText(dss.child("im:price/attributes/currency").getValue().toString());
                tvAmount.setText(dss.child("im:price/attributes/amount").getValue().toString());
                tvReleaseDate.setText(dss.child("im:releaseDate/attributes/label").getValue().toString());
                tvRights.setText(dss.child("rights/label").getValue().toString());
                String urlLogo = dss.child("im:image/2/label").getValue().toString();
                imageLoader.displayImage(urlLogo, ivAppLogo);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getActivity(), "No se pudo descargar la información", Toast.LENGTH_SHORT).show();
            }
        });

        btnComprar.setOnClickListener(this);
        tvSummary.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_app_buy:
                Toast.makeText(getActivity(), "App adquirida", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_app_summary:
                if(flagTVSummary){
                    tvSummary.setMaxLines(5);
                    flagTVSummary = false;
                }
                else{
                    tvSummary.setMaxLines(Integer.MAX_VALUE);
                    flagTVSummary = true;
                }

                break;
        }
    }
}
