package com.joaopaulo.cap3.dados;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by joao.paulo.d.ribeiro on 21/10/2015.
 */
public class Prefs {

    //Identificador do banco de dados destas preferencias
    public static final String PREF_ID = "livroandroid";
    public static void setBoolean(Context context, String chave, Boolean on){
        SharedPreferences pref = context.getSharedPreferences(PREF_ID, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(chave, on);
        editor.commit();
    }

    public static  boolean getBoolean(Context context, String chave){
        SharedPreferences pref = context.getSharedPreferences(PREF_ID, 0);
        boolean b = pref.getBoolean(chave, true);
        return b;
    }

    public static void setInteger(Context context, String chave, int valor){
        SharedPreferences pref = context.getSharedPreferences(PREF_ID, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(chave, valor);
        editor.commit();
    }

    public static int getInteger(Context context, String chave){
        SharedPreferences pref = context.getSharedPreferences(PREF_ID, 0);
        int i = pref.getInt(chave, 0);
        return i;
    }

    public static void setString(Context context, String chave, String valor){
        SharedPreferences pref = context.getSharedPreferences(PREF_ID, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(chave, valor);
        editor.commit();
    }

    public static String getString(Context context, String chave){
        SharedPreferences pref = context.getSharedPreferences(PREF_ID, 0);
        String s = pref.getString(chave, "");
        return s;
    }
}
