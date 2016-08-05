package clases;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by anfeh on 2/08/2016.
 */

public class Constante {

    static FirebaseDatabase database = FirebaseDatabase.getInstance();
    static public DatabaseReference ref = database.getReference("feed/entry");

    public Constante(){
        database.setPersistenceEnabled(true);
    }

}
