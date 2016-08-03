package andres.com.pruebaandroid;

import android.content.Context;
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

import java.util.ArrayList;

import clases.App;

class ListaApp extends BaseAdapter {

    private Context context;
    private ArrayList apps;
    private LayoutInflater inflater=null;
    private ImageLoader imageLoader;
    private ViewHolder holder;

    ListaApp(Context mainActivity, ArrayList arrayList) {
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

        if(holder.ivAppLogo != null){
            imageLoader.displayImage(app.urlLogo, holder.ivAppLogo);
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Detalles App: "+ position, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}
