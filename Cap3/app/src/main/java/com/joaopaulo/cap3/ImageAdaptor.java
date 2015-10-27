package com.joaopaulo.cap3;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by João Paulo on 16/10/2015.
 *
 * Classe que cria adpter para definir um grid com os avatares para seleção
 */


public class ImageAdaptor extends BaseAdapter{
    private Context ctx;
    private final int[] imagens = {
            R.drawable.dangerous,
            R.drawable.escudo,
            R.drawable.ironman,
            R.drawable.lanterna,
            R.drawable.martelo,
            R.drawable.marvel,
            R.drawable.mira
    };

    public ImageAdaptor(Context c){
        this.ctx = c;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(ctx);
            imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(10, 10, 10, 10);
        } else {
            imageView = (ImageView) convertView;
        }
        
        imageView.setImageResource(imagens[position]);
        return imageView;
    }

    @Override
    public int getCount() {
        return imagens.length;
    }

    @Override
    public Object getItem(int posicao) {
        return posicao;
    }

    @Override
    public long getItemId(int posicao) {
        return posicao;
    }

}
