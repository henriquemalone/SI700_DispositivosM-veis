package p169624.ft.unicamp.br.aula02.database;


import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import p169624.ft.unicamp.br.aula02.R;
import p169624.ft.unicamp.br.aula02.alunos.Aluno;
import p169624.ft.unicamp.br.aula02.alunos.Alunos;
import p169624.ft.unicamp.br.aula02.interfaces.OnBiografiaRequest;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class NameFragment extends Fragment {

    private View lview;

    private Random random = new Random();
    private String nomeCorreto;
    private int positionAluno;
    private int numTentativas;

    private ImageView imageView;
    private TextView txtTentativas;
    private TextView txtFeedback;
    private ArrayList<Button> arrayListButton;

    ArrayList<String> nomes = new ArrayList();

    Stats stats = new Stats();
    int erros = 0;
    int acertos = 0;

    private DatabaseReference mFirebaseDatabaseReference;

    private OnBiografiaRequest onBiografiaRequest;

    public void setOnBiografiaRequest(OnBiografiaRequest onBiografiaRequest) {
        this.onBiografiaRequest = onBiografiaRequest;
    }

    public NameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (lview == null) {
            lview = inflater.inflate(R.layout.fragment_name, container, false);
        }

        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();

        imageView = lview.findViewById(R.id.imageFoto);
        txtTentativas = lview.findViewById(R.id.txtTentativas);
        txtFeedback = lview.findViewById(R.id.txtFeedback);

        arrayListButton = new ArrayList<>();
        arrayListButton.add((Button) lview.findViewById(R.id.button1));
        arrayListButton.add((Button) lview.findViewById(R.id.button2));
        arrayListButton.add((Button) lview.findViewById(R.id.button3));
        arrayListButton.add((Button) lview.findViewById(R.id.button4));
        arrayListButton.add((Button) lview.findViewById(R.id.button5));
        arrayListButton.add((Button) lview.findViewById(R.id.button6));
        arrayListButton.add((Button) lview.findViewById(R.id.button7));
        arrayListButton.add((Button) lview.findViewById(R.id.button8));
        arrayListButton.add((Button) lview.findViewById(R.id.button9));

        startGame();

        mFirebaseDatabaseReference.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
                 for (DataSnapshot remoteRespostas : dataSnapshot.getChildren()) {
                     for (DataSnapshot remoteResposta: remoteRespostas.getChildren()) {
                         Resposta resposta = remoteResposta.getValue(Resposta.class);
                         Log.v("DATASET",resposta.getAnswer()+"-"+resposta.getChosen());
                         }
                     }
                 }

         @Override
        public void onCancelled(DatabaseError error) {
                 // Failed to read value
                 Log.w(TAG, "Failed to read value.", error.toException());
                 }
        });

        View.OnClickListener guessButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeEscolhido = ((Button) v).getText().toString();
                Resposta resposta = new Resposta("NomeJogo", nomeCorreto, nomeEscolhido);
                mFirebaseDatabaseReference.child("respostas")
                        .push().setValue(resposta);

                if (nomeEscolhido.equals( nomeCorreto) ){
                    txtFeedback.setText("Correto!!");
                    acertos++;
                    new Handler().postDelayed(
                            new Runnable() {
                                @Override
                                public void run() {
                                    startGame();
                                }
                            }, 2000);
                } else {
                    txtFeedback.setText("Incorreto!!");
                    numTentativas--;
                    txtTentativas.setText("Tentativas: " + numTentativas);
                    nomes.add(nomeCorreto);
                    erros++;

                    if (numTentativas == 0) {
                        txtFeedback.setText("Você Perdeu!!");
                        nomes.add(nomeCorreto);
                        erros++;
                        //System.out.println(nomeCorreto + nomeEscolhido + (erros/(erros+acertos))*100);
                        stats.setN1(nomeCorreto);
                        stats.setN2(nomeEscolhido);
                        stats.setPor((erros/(erros+acertos))*100);
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                        fragmentTransaction.replace(R.id.fragment, new Stats()).commit();

                        new Handler().postDelayed(
                            new Runnable() {
                                @Override
                                public void run() {
                                    if (onBiografiaRequest != null) {
                                        onBiografiaRequest.onRequest(positionAluno);
                                    }
                                }
                            }, 2000);


                    }
                }
            }
        };

        for (int i = 0; i < 9; i++) {
            arrayListButton.get(i).setOnClickListener(guessButtonListener);
        }

        return lview;
    }

    private void startGame() {
        int guess = random.nextInt(Alunos.alunos.length);
        positionAluno = guess;
        Aluno aluno = Alunos.alunos[guess];
        nomeCorreto = aluno.getNome().split(" ")[0].toLowerCase();
        imageView.setImageResource(aluno.getFoto());
        numTentativas = 3;
        txtTentativas.setText("Tentativas: " + numTentativas);
        txtFeedback.setText("");

        ArrayList<String> arrayList = new ArrayList<String>();
        for (int i = 0; i < 9; i++) {
            Aluno candidate = Alunos.alunos[(guess + i) % Alunos.alunos.length];
            arrayList.add(candidate.getNome().split(" ")[0].toLowerCase());
        }
        Collections.shuffle(arrayList);
        for (int i = 0; i < 9; i++) {
            arrayListButton.get(i).setText(arrayList.get(i));
        }
    }

    /*public void contaRepetido(){
        Set<String> numerosSemRepeticoes = new HashSet<String>(nomes);

        Iterator<String> iteradorDeNumerosSemRepeticoes = numerosSemRepeticoes
                .iterator();
        while (iteradorDeNumerosSemRepeticoes.hasNext()) {

            System.out.println(Collections.frequency(nomes,
                    iteradorDeNumerosSemRepeticoes.next()));
        }
    }*/
}
