package andres.com.pruebaandroid;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import clases.App;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentListadoCategoriaPort extends Fragment {


    View view;


    public FragmentListadoCategoriaPort() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view == null){
            view = inflater.inflate(R.layout.fragment_listado_categoria_port, container, false);
        }
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.setPersistenceEnabled(true);
        DatabaseReference ref = database.getReference("feed/entry");

        final ListView lvAppCategoria = (ListView) view.findViewById(R.id.lv_app_categoria);
        lvAppCategoria.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String  itemValue    = (String) lvClient.getItemAtPosition(position);
//                ((ActivityMain)getActivity()).changeClient(itemValue);

                Toast.makeText(getActivity(), "Position :"+position+"  ListItem : "  , Toast.LENGTH_LONG).show();
            }
        });

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<App> arrayApp = new ArrayList<>();

                for(DataSnapshot dss: dataSnapshot.getChildren()) {
                    App app = new App();
                    app.id = dss.child("id/attributes/im:id").getValue().toString();
                    app.nombre = dss.child("title/label").getValue().toString();
                    app.categoria = dss.child("category/attributes/label").getValue().toString();
                    app.urlLogo = dss.child("im:image/0/label").getValue().toString();
                    arrayApp.add(app);
                }
                ListaApp adapter = new ListaApp(getActivity(), arrayApp);
                lvAppCategoria.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;


        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_listado_categoria_port, container, false);
    }

}
