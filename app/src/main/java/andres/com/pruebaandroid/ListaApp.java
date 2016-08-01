package andres.com.pruebaandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import clases.App;

public class ListaApp extends BaseAdapter {


    private Context context;
    private ArrayList apps;
    private LayoutInflater inflater=null;
    private View view;
    private Holder holder;

    public ListaApp(Context mainActivity, ArrayList arrayList) {
        context=mainActivity;
        this.apps=arrayList;
        this.inflater = LayoutInflater.from(context);
        holder=new Holder();

        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private class Holder
    {
        TextView tvAppNombre;
        ImageView ivAppLogo;
    }

    @Override
    public int getCount() {
        return apps.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder{

        public TextView tvAppName;
        public ImageView ivAppLogo;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;
        App app = null;

        if(convertView==null){
            view = inflater.inflate(R.layout.lista_app, null);

            holder = new ViewHolder();
            holder.tvAppName = (TextView) view.findViewById(R.id.tv_app_nombre);
            holder.ivAppLogo=(ImageView) view.findViewById(R.id.iv_app_logo);
            view.setTag( holder );
        }
        else
            holder=(ViewHolder)view.getTag();

        if(apps.size() < 1)
        {
            holder.tvAppName.setText(R.string.sin_app);
        }
        else
        {
            app = (App) apps.get( position );

            ConnectivityManager connMgr = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

//                return ();

//            if(networkInfo != null && networkInfo.isConnected()) {
                URL url = null;
                try {
                    url = new URL(app.urlLogo);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
//                try {
//                    if(url != null){
////                        Bitmap bit = BitmapFactory.decodeStream((InputStream)url.getContent());
////                        holder.ivAppLogo.setImageBitmap(bit);
//                    }
//                } catch (IOException | NullPointerException e) {
//                    e.printStackTrace();
//                }
//            }else{
//                ImageLoader imageLoader = ImageLoader.getInstance();
//                File file = imageLoader.getDiscCache().get(app.urlLogo);
//                holder.ivAppLogo.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
//            }
            holder.tvAppName.setText( app.nombre);
            Log.d("view", app.nombre);

//            holder.ivAppLogo.setImageResource(
//                    res.getIdentifier(
//                            "com.androidexample.customlistview:drawable/"+tempValues.getImage()
//                            ,null,null));

            /******** Set Item Click Listner for LayoutInflater for each row *******/

//            view.setOnClickListener(new OnItemClickListener( position ));

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Detalles App: "+ position, Toast.LENGTH_SHORT).show();
                }
            });
        }
        return view;


//        if(view == null){
//            view = inflater.inflate(R.layout.lista_app, parent);
//
//        }
//
//        holder.tvAppNombre=(TextView) view.findViewById(R.id.tv_client_name);
//        holder.tvAppNombre.setText(listApp[position]);
//
//        holder.ivAppLogo=(ImageView) view.findViewById(R.id.iv_app_logo);
////        holder.ivClientLogo.setImageResource(imageId[position]);
//
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "Detalles App: "+listApp[position], Toast.LENGTH_LONG).show();
//            }
//        });
//        return view;
    }
}
