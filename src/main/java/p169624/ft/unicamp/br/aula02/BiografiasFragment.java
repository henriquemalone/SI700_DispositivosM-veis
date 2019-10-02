package p169624.ft.unicamp.br.aula02;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import p169624.ft.unicamp.br.aula02.alunos.Aluno;
import p169624.ft.unicamp.br.aula02.alunos.Alunos;

public class BiografiasFragment extends Fragment {
    private ImageView image;

    private Button next;

    private int position = 0;

    private Button prev;

    private TextView txtDescricao;

    private TextView txtNome;

    public BiografiasFragment() { this.position = 0; }

    public int getPosition() { return this.position; }

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        View view = paramLayoutInflater.inflate(R.layout.fragment_biografias, paramViewGroup, false);
        this.txtNome = (TextView)view.findViewById(R.id.txtNome);
        this.txtDescricao = (TextView)view.findViewById(R.id.txtDescricao);
        this.image = (ImageView)view.findViewById(R.id.imgFoto);
        this.next = (Button)view.findViewById(R.id.btnNext);
        this.prev = (Button)view.findViewById(R.id.btnPrev);
        this.next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                /*if (BiografiasFragment.this.position == Alunos.alunos.length - 1) {
                    BiografiasFragment.this.set(BiografiasFragment.this, 0);
                } else {
                    BiografiasFragment.access$008(BiografiasFragment.this);
                }*/
                BiografiasFragment.this.position += 1;
                BiografiasFragment.this.setDados();
            }
        });
        this.prev.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                /*if (BiografiasFragment.this.position == 0) {
                    BiografiasFragment.access$002(BiografiasFragment.this, Alunos.alunos.length - 1);
                } else {
                    BiografiasFragment.access$010(BiografiasFragment.this);
                }*/
                BiografiasFragment.this.position -= 1;
                BiografiasFragment.this.setDados();
            }
        });
        return view;
    }

    public void onStart() {
        super.onStart();
        setDados();
    }

    public void setDados() {
        Aluno aluno = Alunos.alunos[this.position % Alunos.alunos.length];
        this.txtNome.setText(aluno.getNome());
        this.txtDescricao.setText(aluno.getDescricao());
        this.image.setImageResource(aluno.getFoto());
    }

    public void setPosition(int paramInt) { this.position = paramInt; }
}
