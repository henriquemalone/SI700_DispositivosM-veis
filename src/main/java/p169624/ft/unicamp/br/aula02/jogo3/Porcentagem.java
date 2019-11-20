package p169624.ft.unicamp.br.aula02.jogo3;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import p169624.ft.unicamp.br.aula02.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Porcentagem extends Fragment {

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference pontuacao = databaseReference.child("trabalhosi700");
    List<Pontuacao> list = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter ;
    TextView n;
    TextView e;
    TextView a;

    public Porcentagem() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_porcentagem, container, false);
        // Inflate the layout for this fragment

        n = v.findViewById(R.id.nomeP);
        a = v.findViewById(R.id.acertos);
        e = v.findViewById(R.id.erros);
        recyclerView = v.findViewById(R.id.rec);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        pontuacao.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    final Pontuacao p = snapshot.getValue(Pontuacao.class);
                    list.add(p);
                    n.setText(p.getNome());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return v;
    }

}
