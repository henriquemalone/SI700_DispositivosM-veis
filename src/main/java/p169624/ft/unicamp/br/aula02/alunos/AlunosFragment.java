package p169624.ft.unicamp.br.aula02.alunos;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;

import p169624.ft.unicamp.br.aula02.R;
import p169624.ft.unicamp.br.aula02.interfaces.OnBiografiaRequest;

public class AlunosFragment extends Fragment {
    private MyFirstAdapter mAdapter;

    private RecyclerView mRecyclerView;

    private OnBiografiaRequest onBiografiaRequest;

    private View view;

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        if (this.view == null)
            this.view = paramLayoutInflater.inflate(R.layout.fragment_alunos, paramViewGroup, false);
        this.mRecyclerView = (RecyclerView)this.view.findViewById(R.id.recycler_view);
        this.mRecyclerView.setHasFixedSize(true);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.mAdapter = new MyFirstAdapter(new ArrayList(Arrays.asList(Alunos.alunos)));
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mAdapter.setMyOnLongClickListener(new MyFirstAdapter.MyOnLongItemClickListener() {
            public void myOnLongItemClick(int param1Int) {
                if (AlunosFragment.this.onBiografiaRequest != null)
                    AlunosFragment.this.onBiografiaRequest.onRequest(param1Int);
            }
        });
        return this.view;
    }

    public void setOnBiografiaRequest(OnBiografiaRequest paramOnBiografiaRequest) { this.onBiografiaRequest = paramOnBiografiaRequest; }
}