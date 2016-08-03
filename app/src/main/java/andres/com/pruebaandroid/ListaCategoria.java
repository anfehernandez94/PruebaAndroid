package andres.com.pruebaandroid;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import clases.App;

class ListaCategoria extends BaseAdapter {

    private Context context;
    private ArrayList categorias;
    private LayoutInflater inflater=null;
    private ViewHolder holder;

    ListaCategoria(Context mainActivity, ArrayList arrayList) {
        context = mainActivity;
        this.categorias = arrayList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return categorias.size();
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
        TextView tvCategoriaNombre;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            view = inflater.inflate(R.layout.lista_categoria, null);
            holder = new ViewHolder();
            holder.tvCategoriaNombre = (TextView) view.findViewById(R.id.tv_categoria_nombre);
            view.setTag( holder );
        }
        else {
            holder = (ViewHolder) view.getTag();
        }

        holder.tvCategoriaNombre.setText(String.valueOf(categorias.get( position )));

//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "Detalles App: "+ position, Toast.LENGTH_SHORT).show();
//            }
//        });

        return view;
    }
}
