package andres.com.pruebaandroid;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

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
    ImageLoader imageLoader;
    ViewHolder holder;

    public ListaApp(Context mainActivity, ArrayList arrayList) {
        context = mainActivity;
        this.apps = arrayList;
        this.inflater = LayoutInflater.from(context);
        imageLoader = ImageLoader.getInstance();
        DisplayImageOptions opts = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true).build();
        ImageLoaderConfiguration configImageLoader = new ImageLoaderConfiguration.Builder(context)
                .defaultDisplayImageOptions(opts).build();

        imageLoader.init(configImageLoader);


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

    private static class ViewHolder{
        TextView tvAppName;
        ImageView ivAppLogo;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            view = inflater.inflate(R.layout.lista_app, null);
            holder = new ViewHolder();
            holder.tvAppName = (TextView) view.findViewById(R.id.tv_app_nombre);
            holder.ivAppLogo=(ImageView) view.findViewById(R.id.iv_app_logo);
            view.setTag( holder );
        }
        else {
            holder = (ViewHolder) view.getTag();
        }

        App app = (App) apps.get( position );

        holder.tvAppName.setText( app.nombre);
        Log.d("view", app.nombre);

        ConnectivityManager connMgr = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

//        if(networkInfo != null && networkInfo.isConnected()) {
            if(holder.ivAppLogo != null){
                imageLoader.displayImage(app.urlLogo, holder.ivAppLogo);
//                try {
//                    new DownloadImageTask(holder.ivAppLogo).execute(app.urlLogo);
//                } catch (NullPointerException e) {
//                    e.printStackTrace();
//                }
            }
//        }else{
//            imageLoader.displayImage(app.urlLogo, holder.ivAppLogo);
////            File file = imageLoader.getDiscCache().get(app.urlLogo);
////            holder.ivAppLogo.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
//        }


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Detalles App: "+ position, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        private ProgressDialog progressDialog;
        private ImageView ivAppLogo;

        DownloadImageTask(ImageView bmImage) {
            this.ivAppLogo = bmImage;
        }

        protected void onPreExecute() {
//            progressDialog = ProgressDialog.show(context, "Favor espere", "Cargando datos ...", true);
        }

        protected Bitmap doInBackground(String... urls) {
            String strUrl = urls[0];
            Bitmap bitmap = null;
            try {
                InputStream in = new java.net.URL(strUrl).openStream();
                bitmap = BitmapFactory.decodeStream(in);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap bitmap) {

            ivAppLogo.setImageBitmap(bitmap);
//            progressDialog.dismiss();
        }
    }
}
