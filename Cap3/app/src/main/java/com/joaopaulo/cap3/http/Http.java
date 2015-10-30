package com.joaopaulo.cap3.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by João Paulo on 27/10/2015.
 */
public class Http {

    public static final String USERS_URL_JSON =
            "https://raw.githubusercontent.com/tabulti/" +
                    "Perguntageis/master/users.json";

    private static HttpURLConnection conectar(String urlArquivo) throws IOException{
        final int SEGUNDOS = 1000;
        URL url = new URL(urlArquivo);
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        conexao.setReadTimeout(10 * SEGUNDOS);
        conexao.setConnectTimeout(15 * SEGUNDOS);
        conexao.setRequestMethod("GET");
        conexao.setDoInput(true);
        conexao.setDoOutput(false);
        conexao.connect();
        return conexao;
    }

    public static boolean temConexao(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        return (info != null && info.isConnected());
    }


/*    public static List<Usuario> carregarUsuariosJson(){
        try{
            HttpURLConnection conexao = conectar(USERS_URL_JSON);

            int resposta = conexao.getResponseCode();
            if(resposta == HttpURLConnection.HTTP_OK){
                InputStream is = conexao.getInputStream();
                JSONObject json = new JSONObject(bytesParaString(is));
                return lerJsonUsers(json);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }


   *//* public static List<Usuario> lerJsonUsers(JSONObject json) throws JSONException{
        List<Usuario> listaDeUsers = new ArrayList<>();

        JSONArray jsonUsers = json.getJSONArray("users");
        for(int i = 0; i < jsonUsers.length(); i++){
            JSONObject json
        }
    }
*/
}
