package p169624.ft.unicamp.br.aula02;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import p169624.ft.unicamp.br.aula02.alunos.AlunosFragment;
import p169624.ft.unicamp.br.aula02.interfaces.OnBiografiaRequest;
import p169624.ft.unicamp.br.aula02.puzzle.PuzzleFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private EditText assunto;

    private FragmentManager fragmentManager;

    private EditText login;

    private EditText message;

    private EditText password;

    private Button send;

    private EditText to;

    public void chamaBio(int paramInt) {
        BiografiasFragment biografiasFragment = (BiografiasFragment)this.fragmentManager.findFragmentByTag("biografia");
        biografiasFragment = new BiografiasFragment();
        if (paramInt != -1)
            biografiasFragment.setPosition(paramInt);
        replaceFragment(biografiasFragment, "biografia");
    }

    public void doSomething(String paramString) {
        AutoresFragment autoresFragment2 = (AutoresFragment)this.fragmentManager.findFragmentByTag("autores");
        AutoresFragment autoresFragment1 = autoresFragment2;
        if (autoresFragment2 == null)
            autoresFragment1 = new AutoresFragment();
        autoresFragment1.setText(paramString);
        replaceFragment(autoresFragment1, "autores");
    }

    public void onBackPressed() {
        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
            return;
        }
        super.onBackPressed();
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_main);
        this.fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = this.fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment, new AutoresFragment(), "autores");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ((FloatingActionButton)findViewById(R.id.fab)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) { Snackbar.make(param1View, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show(); }
        });
        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    public boolean onCreateOptionsMenu(Menu paramMenu) {
        getMenuInflater().inflate(R.menu.main, paramMenu);
        return true;
    }

    public boolean onNavigationItemSelected(MenuItem paramMenuItem) {
        int i = paramMenuItem.getItemId();
        if (i == R.id.nav_autores) {
            setTitle("Autores");
            AutoresFragment autoresFragment = new AutoresFragment();
            this.fragmentManager.beginTransaction().replace(R.id.fragment, autoresFragment).addToBackStack(null).commit();
        } else if (i == R.id.nav_alunos) {
            setTitle("Alunos");
            AlunosFragment alunosFragment2 = (AlunosFragment)this.fragmentManager.findFragmentByTag("alunos");
            AlunosFragment alunosFragment1 = alunosFragment2;
            if (alunosFragment2 == null) {
                alunosFragment1 = new AlunosFragment();
                alunosFragment1.setOnBiografiaRequest(new OnBiografiaRequest() {
                    public void onRequest(int param1Int) { MainActivity.this.chamaBio(param1Int); }
                });
            }
            replaceFragment(alunosFragment1, "alunos");
        } else if (i == R.id.nav_biografias) {
            setTitle("Biografia");
            BiografiasFragment biografiasFragment = new BiografiasFragment();
            this.fragmentManager.beginTransaction().replace(R.id.fragment, biografiasFragment).addToBackStack(null).commit();
        } else if (i == R.id.nav_jogo1) {
            setTitle("Jogo 1");
            PuzzleFragment puzzleFragment = new PuzzleFragment();
            this.fragmentManager.beginTransaction().replace(R.id.fragment, puzzleFragment).addToBackStack(null).commit();
        } else if (i == R.id.nav_jogo2) {
            Toast.makeText(this, "Jogo 2 aberto", Toast.LENGTH_SHORT).show();
        }
        ((DrawerLayout)findViewById(R.id.drawer_layout)).closeDrawer(GravityCompat.START);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem paramMenuItem) {
        MailFragment mailFragment;
        int i = paramMenuItem.getItemId();
        if (i == R.id.action_settings)
            return true;
        if (i == R.id.action_contato) {
            setTitle("Contato");
            mailFragment = new MailFragment();
            this.fragmentManager.beginTransaction().replace(R.id.fragment, mailFragment).addToBackStack(null).commit();
            return true;
        }
        return super.onOptionsItemSelected(paramMenuItem);
    }

    public void replaceFragment(Fragment paramFragment, String paramString) {
        FragmentTransaction fragmentTransaction = this.fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, paramFragment, paramString);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}


