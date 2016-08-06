package andres.com.pruebaandroid;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragmentListadoCategoriaLand extends Fragment {

    View view;

    public FragmentListadoCategoriaLand() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view == null){
            view = inflater.inflate(R.layout.fragment_listado_categoria_land, container, false);
        }
        final Activity activity = getActivity();
        if(activity != null)
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        try{
            database.setPersistenceEnabled(true);
        }catch (RuntimeException e){
            Toast.makeText(getActivity(), "No se pudo almacenar la información", Toast.LENGTH_SHORT).show();
        }

        DatabaseReference ref = database.getReference("feed/entry");


        final GridView gvAppCategoria = (GridView) view.findViewById(R.id.gv_categoria);



        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<String> arrayCategoria = new ArrayList<>();

                gvAppCategoria.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        TextView categoriaNombre = (TextView) view.findViewById(R.id.tv_categoria_nombre_grid);
                        Intent i = new Intent(getActivity(), ActivityListadoApp.class);
                        i.putExtra("categoria", categoriaNombre.getText());
                        startActivity(i);
                    }
                });

                for(DataSnapshot dss: dataSnapshot.getChildren()) {
                    String categoria = dss.child("category/attributes/label").getValue().toString();
                    boolean estaRepetido = true;
                    for(String str : arrayCategoria){
                        if(str.equals(categoria))
                            estaRepetido = false;
                    }
                    if(estaRepetido)
                        arrayCategoria.add(categoria);
                }
                ((ActivityBase)getActivity()).ordenar(arrayCategoria);
                GridCategoria adapter = new GridCategoria(activity, arrayCategoria);
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
