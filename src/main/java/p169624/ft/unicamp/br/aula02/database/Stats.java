package p169624.ft.unicamp.br.aula02.database;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import p169624.ft.unicamp.br.aula02.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Stats extends Fragment {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;

    private TextView nome1;
    private TextView nome2;
    private TextView porc;

    private String n1;
    private String n2;
    private float por;

    private FragmentManager fragmentManager;

    public Stats() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stats, container, false);

        nome1 = view.findViewById(R.id.pessoaMenos);
        nome2 = view.findViewById(R.id.nomeErro);
        porc = view.findViewById(R.id.porcentagem);

        return view;
    }

    public void onStart() {
        super.onStart();
        dbHelper = new DatabaseHelper(getActivity());
        sqLiteDatabase = dbHelper.getReadableDatabase();

    }

    public void onStop() {
        super.onStop();
        sqLiteDatabase.close();
        dbHelper.close();
    }

    public void onInserir(String nome, String nome2, float por) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("Nome", getN1());
        contentValues.put("Nome2", getN2());
        contentValues.put("Porcentagem", getPor());
        sqLiteDatabase.insert("tabela", null, contentValues);
    }


    /*public void onAtualizar() {
        int id = Integer.parseInt(edtId.getText().toString());
        String texto = edtTexto.getText().toString();

        ContentValues contentValues = new ContentValues();
        contentValues.put("texto", texto);

        String whereClause = "_id = ?";
        String[] whereArgs = new String[]{Integer.toString(id)};

        sqLiteDatabase.update("tabela", contentValues, whereClause, whereArgs);
    }

    public void onRemover() {
        int id = Integer.parseInt(edtId.getText().toString());

        String whereClause = "_id = ?";
        String[] whereArgs = new String[]{Integer.toString(id)};

        sqLiteDatabase.delete("tabela", whereClause, whereArgs);
    }*/

    public void onSelecionar() {
        String sql = "Select * from tabela";
        String nome;
        String n2;
        int p;

        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            String str = "";
            do {
                nome = cursor.getString(0);
                n2 = cursor.getString(1);
                p = cursor.getInt(2);

                //str = str + id + "," + texto + "\n";

            } while (cursor.moveToNext());
            nome1.setText(nome);
            nome2.setText(n2);
            porc.setText(p);
        }
        cursor.close();
    }

    public String getN1() {
        return n1;
    }

    public void setN1(String n1) {
        this.n1 = n1;
    }

    public String getN2() {
        return n2;
    }

    public void setN2(String n2) {
        this.n2 = n2;
    }

    public float getPor() {
        return por;
    }

    public void setPor(float por) {
        this.por = por;
    }
}
