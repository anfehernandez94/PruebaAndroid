package andres.com.pruebaandroid;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
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

public class ActivityApp extends Activity implements View.OnClickListener{

    private TextView tvCategoria, tvNombre, tvSummary, tvCurrency, tvAmount,
            tvReleaseDate, tvRights;
    private Button btnComprar;
    private ImageView ivAppLogo;
    private boolean flagTVSummary = false;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        ivAppLogo = (ImageView) findViewById(R.id.iv_app_logo);
        btnComprar = (Button)findViewById(R.id.btn_app_buy);
        tvCategoria = (TextView) findViewById(R.id.tv_app_categoria);
        tvNombre = (TextView) findViewById(R.id.tv_app_nombre);
        tvSummary = (TextView) findViewById(R.id.tv_app_summary);
        tvCurrency = (TextView) findViewById(R.id.tv_app_currency);
        tvAmount = (TextView) findViewById(R.id.tv_app_amount);
        tvReleaseDate = (TextView) findViewById(R.id.tv_app_releaseDate);
        tvRights = (TextView) findViewById(R.id.tv_app_rights);

        id = getIntent().getStringExtra("id");

        final ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions opts = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true).build();
        ImageLoaderConfiguration configImageLoader = new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(opts).build();
        imageLoader.init(configImageLoader);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("feed/entry/"+ id);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dss) {
                tvCategoria.setText(dss.child("category/attributes/label").getValue().toString());
                tvNombre.setText(dss.child("im:name/label").getValue().toString());
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

            }
        });

        btnComprar.setOnClickListener(this);
        tvSummary.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_app_buy:
                Toast.makeText(this, "App adquirida", Toast.LENGTH_SHORT).show();
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
