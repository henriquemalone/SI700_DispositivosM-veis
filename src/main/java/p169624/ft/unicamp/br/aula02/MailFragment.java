package p169624.ft.unicamp.br.aula02;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MailFragment extends Fragment {
    private EditText assunto;

    private EditText login;

    private EditText message;

    private EditText password;

    private Button send;

    private EditText to;

    private View view;

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        if (this.view == null)
            this.view = paramLayoutInflater.inflate(R.layout.fragment_mail, paramViewGroup, false);
        this.send = (Button)this.view.findViewById(R.id.send);
        this.login = (EditText)this.view.findViewById(R.id.login);
        this.password = (EditText)this.view.findViewById(R.id.senha);
        this.to = (EditText)this.view.findViewById(R.id.to);
        this.assunto = (EditText)this.view.findViewById(R.id.assunto);
        this.message = (EditText)this.view.findViewById(R.id.message);
        this.send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                String str = MailFragment.this.message.getText().toString();
                AutoresFragment autoresFragment = new AutoresFragment();
                Bundle bundle = new Bundle();
                FragmentTransaction fragmentTransaction = MailFragment.this.getFragmentManager().beginTransaction();
                bundle.putString("mensagem", str);
                autoresFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragment, autoresFragment, "autoresFragment");
                fragmentTransaction.commit();
            }
        });
        return this.view;
    }
}
