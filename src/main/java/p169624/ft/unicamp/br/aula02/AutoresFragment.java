package p169624.ft.unicamp.br.aula02;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class AutoresFragment extends Fragment {
    private TextView email;

    private String mensagem;

    private View view;

    public void onActivityCreated(Bundle paramBundle) { super.onActivityCreated(paramBundle); }

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        if (this.view == null)
            this.view = paramLayoutInflater.inflate(R.layout.fragment_autores, paramViewGroup, false);
        this.email = (TextView)this.view.findViewById(R.id.textEmail);
        return this.view;
    }

    public void onStart() {
        super.onStart();
        Bundle bundle = getArguments();
        if (bundle != null) {
            String str = bundle.getString("mensagem");
            this.email.setText(str);
        }
    }

    public void setText(String paramString) { this.mensagem = paramString; }
}
