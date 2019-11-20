package p169624.ft.unicamp.br.aula02.alunos;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import p169624.ft.unicamp.br.aula02.R;

public class MyFirstAdapter extends RecyclerView.Adapter {
    ArrayList<Aluno> alunos;
    private FragmentManager fragmentManager;

    MyOnItemClickListener myOnItemClickListener;

    MyOnLongItemClickListener myOnLongClickListener;

    public MyFirstAdapter(ArrayList paramArrayList) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("");
        stringBuilder.append(paramArrayList.size());
        Log.v("Ulisses", stringBuilder.toString());
        this.alunos = paramArrayList;
    }

    public int getItemCount() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("");
        stringBuilder.append(this.alunos.size());
        Log.v("Ulisses", stringBuilder.toString());
        return this.alunos.size();
    }

    public void onBindViewHolder(RecyclerView.ViewHolder paramViewHolder, final int position) {
        ((MyFirstViewHolder)paramViewHolder).onBind((Aluno)this.alunos.get(position));
        paramViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View param1View) {
                if (MyFirstAdapter.this.myOnLongClickListener != null)
                    MyFirstAdapter.this.myOnLongClickListener.myOnLongItemClick(position);
                return false;
            }
        });
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt) {
        View view = LayoutInflater.from(paramViewGroup.getContext()).inflate(R.layout.adapter_layout, paramViewGroup, false);
        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                TextView textView = (TextView)param1View.findViewById(R.id.nome);
                Log.v("Ulisses", textView.getText().toString());
                Toast.makeText(param1View.getContext(), textView.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });
        return new MyFirstViewHolder(view);
    }

    public void setMyOnLongClickListener(MyOnLongItemClickListener paramMyOnLongItemClickListener) {
        this.myOnLongClickListener = paramMyOnLongItemClickListener;
    }

    class MyFirstViewHolder extends RecyclerView.ViewHolder {
        private TextView descricao;

        private ImageView imageView;

        private TextView nome;

        public MyFirstViewHolder(View param1View) {
            super(param1View);
            this.imageView = (ImageView)param1View.findViewById(R.id.foto);
            this.nome = (TextView)param1View.findViewById(R.id.nome);
            this.descricao = (TextView)param1View.findViewById(R.id.descricao);
        }

        public void onBind(Aluno param1Aluno) {
            this.nome.setText(param1Aluno.getNome());
            this.descricao.setText(param1Aluno.getDescricao());
            this.imageView.setImageResource(param1Aluno.getFoto());
            System.out.println("my first adapter: ");
        }
    }

    public static interface MyOnItemClickListener {
        void myOnItemClick(String param1String);
    }

    public static interface MyOnLongItemClickListener {
        void myOnLongItemClick(int param1Int);
    }
}