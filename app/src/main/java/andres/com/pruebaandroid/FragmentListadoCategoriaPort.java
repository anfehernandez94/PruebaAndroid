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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class FragmentListadoCategoriaPort extends Fragment {

    private View view;

    public FragmentListadoCategoriaPort() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view == null){
            view = inflater.inflate(R.layout.fragment_listado_categoria_port, container, false);
        }

        final Activity activity = getActivity();
        if(activity != null)
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        try{
            database.setPersistenceEnabled(true);
        }catch (RuntimeException e){
            Toast.makeText(getActivity(), "No se pudo almacenar la información", Toast.LENGTH_SHORT).show();
        }

        DatabaseReference ref = database.getReference("feed/entry");


        final ListView lvAppCategoria = (ListView) view.findViewById(R.id.lv_categoria);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<String> arrayCategoria = new ArrayList<>();

                lvAppCategoria.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        TextView categoriaNombre = (TextView) view.findViewById(R.id.tv_categoria_nombre_lista);
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
                ListaCategoria adapter = new ListaCategoria(activity, arrayCategoria);
                lvAppCategoria.setAdapter(adapter);
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
