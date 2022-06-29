package br.com.janiny.appdogs;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ListaDogAdapter extends ArrayAdapter<Produto> {

    private int resourceLayout;
    private Context mContext;

    public ListaDogAdapter(Context context, int resource, List<Produto> items) {
        super(context, resource, items);
        this.resourceLayout = resource;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, null);
        }

        Produto p = getItem(position);

        if (p != null) {
            ImageView tt1 = (ImageView) v.findViewById(R.id.dogImagem);
            TextView tt2 = (TextView) v.findViewById(R.id.dogRaca);
            TextView tt3 = (TextView) v.findViewById(R.id.listaCastrado);
            TextView tt4 = (TextView) v.findViewById(R.id.dogNome);
            TextView tt5 = (TextView) v.findViewById(R.id.dogPorte);
            TextView tt6 = (TextView) v.findViewById(R.id.dogIdade);

            if (tt1 != null) {
                Bitmap bmp = BitmapFactory.decodeByteArray(p.getFoto(), 0, p.getFoto().length);
                tt1.setImageBitmap(bmp);
            }

            if (tt2 != null) {
                tt2.setText(p.getRaca());
            }

            if (tt3 != null) {
                tt3.setText(p.getCastracao());
            }

            if (tt4 != null) {
                tt4.setText(p.getNome());
            }

            if (tt5 != null) {
                tt5.setText(p.getPorte());
            }

            if (tt6 != null) {
                tt6.setText(p.getIdade());
            }
        }

        return v;
    }

}
