package p169624.ft.unicamp.br.aula02.jogo3;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by ulisses on 9/27/17.textView
 */

public class MyFrasesAsyncTask extends AsyncTask<String, Void, String> {

    TextView textView;
    RadioButton radio1;
    RadioButton radio2;
    RadioButton radio3;
    RadioButton radio4;
    RadioButton radio5;
    String array1;
    String array2;
    String array3;
    String array4;
    String arrayCorreta;
    ArrayList<String> lista = new ArrayList();
    TextView teste;
    Jogo3 jogo3 = new Jogo3();


    public MyFrasesAsyncTask(TextView textView, RadioButton radio1,RadioButton radio2,RadioButton radio3,RadioButton radio4,
                             RadioButton radio5, TextView teste) {
        this.textView = textView;
        this.radio1 = radio1;
        this.radio2 = radio2;
        this.radio3 = radio3;
        this.radio4 = radio4;
        this.radio5 = radio5;
        this.teste = teste;
    }


    @Override
    protected void onPreExecute() {
        textView.append("####################### \n ");
        textView.append("Iniciando Jogo3 \n ");
    }

    @Override
    protected String doInBackground(String... args) {
        /*if (args.length == 0) {
            return "No Parameter";
        }*/

        HttpURLConnection httpURLConnection;
        try {
            /*
               Endereço que será acessado.
             */
            String HOST = "https://sa4a4dtiv4.execute-api.eu-west-1.amazonaws.com/default/PythonHTTP1?kind=frases&num_outros=4";

        /*
          Abrindo uma conexão com o servidor
        */

            URL url = new URL(HOST);

            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
        /*
          Lendo a resposta do servidor
        */
            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(httpURLConnection.getInputStream()));


            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            Log.v("Erro", e.getMessage());
            return "Exception\n" + e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String args) {
        // Via Cep

        try {
            JSONObject jsonObject = new JSONObject(args);
            textView.append(jsonObject.getString("nome"));
            lista.add(jsonObject.getString("frase"));
            lista.add(jsonObject.getJSONArray("outros").getString(0));
            lista.add(jsonObject.getJSONArray("outros").getString(1));
            lista.add(jsonObject.getJSONArray("outros").getString(2));
            lista.add(jsonObject.getJSONArray("outros").getString(3));
            teste.append(lista.get(0));
            Collections.shuffle(lista);
            radio1.append(lista.get(0));
            radio2.append(lista.get(1));
            radio3.append(lista.get(2));
            radio4.append(lista.get(3));
            radio5.append(lista.get(4));

        } catch(JSONException e) {
            textView.append("ERRO: Não foi possível converter em JSONObject: " + args+"\n");
        }
    }
}

