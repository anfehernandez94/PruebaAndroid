package andres.com.pruebaandroid;


import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import clases.App;


public class FragmentListadoAppLand extends Fragment {


    private View view;
    String categoria;


    public FragmentListadoAppLand() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view == null){
            view = inflater.inflate(R.layout.fragment_listado_app_land, container, false);

        }
        final Activity activity = getActivity();
        if(activity != null)
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("feed/entry");

        final GridView gvAppCategoria = (GridView) view.findViewById(R.id.gv_app_categoria);
        gvAppCategoria.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "Position :"+position+"  ListItem : "  , Toast.LENGTH_LONG).show();
            }
        });

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<App> arrayApp = new ArrayList<>();
//                ImageView ivAux = new ImageView(getActivity());
//                imageLoader.displayImage(app.urlLogo, holder.ivAppLogo);
                for(DataSnapshot dss: dataSnapshot.getChildren()) {
                    String categoriaNombre = dss.child("category/attributes/label").getValue().toString();
                    if(categoriaNombre.equals(categoria)) {
                        App app = new App();
                        app.id = dss.child("id/attributes/im:id").getValue().toString();
                        app.nombre = dss.child("im:name/label").getValue().toString();
                        app.urlLogo = dss.child("im:image/2/label").getValue().toString();
                        arrayApp.add(app);
                    }
                }
                GridApp adapter = new GridApp(activity, arrayApp);
                gvAppCategoria.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}
