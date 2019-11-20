package p169624.ft.unicamp.br.aula02.jogo3;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import p169624.ft.unicamp.br.aula02.R;
import p169624.ft.unicamp.br.aula02.internet.MyViaCepAsyncTask;



/**
 * A simple {@link Fragment} subclass.
 */
public class Jogo3 extends Fragment {

    TextView nome;
    RadioButton radio1;
    RadioButton radio2;
    RadioButton radio3;
    RadioButton radio4;
    RadioButton radio5;
    String aux;
    Button btn;
    Button restart;
    TextView opcao;
    TextView teste;
    int cont = 0;
    int erros = 0;

    public Jogo3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_jogo3, container, false);

        nome = view.findViewById(R.id.nome);
        radio1 = view.findViewById(R.id.radio1);
        radio2 = view.findViewById(R.id.radio2);
        radio3 = view.findViewById(R.id.radio3);
        radio4 = view.findViewById(R.id.radio4);
        radio5 = view.findViewById(R.id.radio5);
        btn = view.findViewById(R.id.btnCheck);
        opcao = view.findViewById(R.id.opcao);
        teste = view.findViewById(R.id.teste);
        restart = view.findViewById(R.id.btnRestart);

        new MyFrasesAsyncTask(nome, radio1, radio2, radio3, radio4, radio5, teste).execute();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(teste.getText().toString());
                System.out.println(getSelection());
                if(teste.getText().toString().equals(getSelection())){
                    opcao.setText("CORRETO!");
                    String currentString = nome.getText().toString();
                    System.out.println("1");
                    String[] separated = currentString.split(" ");
                    System.out.println(separated[5]);
                    DatabaseReference raiz = FirebaseDatabase.getInstance().getReference(); //Esta variavel indica a raiz da nossa árvore JSON
                    raiz.child(separated[5]+"/Acertos").setValue(cont++);
                    raiz.child(separated[5]+"/Erros").setValue(erros);
                } else {
                    opcao.setText("INCORRETO!");
                    String currentString = nome.getText().toString();
                    System.out.println("1");
                    String[] separated = currentString.split(" ");
                    System.out.println(separated[5]);
                    DatabaseReference raiz = FirebaseDatabase.getInstance().getReference(); //Esta variavel indica a raiz da nossa árvore JSON
                    raiz.child(separated[5]+"/Acertos").setValue(cont);
                    raiz.child(separated[5]+"/Erros").setValue(erros++);
                }
            }
        });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Jogo3 fragment = (Jogo3)
                        getFragmentManager().findFragmentById(R.id.fragment);
                getFragmentManager().beginTransaction()
                        .detach(fragment)
                        .attach(fragment)
                        .commit();
            }
        });

        return view;
    }

    public String teste(String teste){
        return teste;
    }

    public String getSelection(){
        if(radio1.isChecked()){
            aux = radio1.getText().toString();
        }
        if(radio2.isChecked()){
            aux = radio2.getText().toString();
        }
        if(radio3.isChecked()){
            aux = radio3.getText().toString();
        }
        if(radio4.isChecked()){
            aux = radio4.getText().toString();
        }
        if(radio5.isChecked()){
            aux = radio5.getText().toString();
        }

        return aux;
    }
}
