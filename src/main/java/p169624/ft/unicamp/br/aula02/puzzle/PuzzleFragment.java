package p169624.ft.unicamp.br.aula02.puzzle;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import p169624.ft.unicamp.br.aula02.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PuzzleFragment extends Fragment {

    private LinearLayout view;
    private Spinner spinner;
    private ImageView imageView;
    private Tabuleiro tabuleiro;
    String selected;

    private int[] fotosGis = {R.drawable.g01, R.drawable.g02, R.drawable.g03, R.drawable.g04, R.drawable.g05, R.drawable.g06, R.drawable.g07,
            R.drawable.g08, R.drawable.g09, R.drawable.g10, R.drawable.g11, R.drawable.g12, R.drawable.g13, R.drawable.g14, R.drawable.g15,
            R.drawable.g16,
    };

    private int[] fotosRod = {R.drawable.r01, R.drawable.r02, R.drawable.r03, R.drawable.r04, R.drawable.r05, R.drawable.r06, R.drawable.r07,
            R.drawable.r08, R.drawable.r09, R.drawable.r10, R.drawable.r11, R.drawable.r12, R.drawable.r13, R.drawable.r14, R.drawable.r15,
            R.drawable.r16, R.drawable.r17, R.drawable.r18, R.drawable.r19, R.drawable.r20, R.drawable.r21, R.drawable.r22, R.drawable.r23,
            R.drawable.r24
    };

    public PuzzleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        if (view == null) {
            view = (LinearLayout) inflater.inflate(R.layout.fragment_puzzle, container, false);
            startPuzzle(0, view);
        }

        spinner = (Spinner) view.findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.alunos, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selected = (String) spinner.getSelectedItem();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        return view;
    }

    private void startPuzzle(int puzzle, LinearLayout view){
        Tabuleiro tabuleiro = Boards.getPuzzle(puzzle);

        ArrayList<ImageView> imageViews = new ArrayList();

        for(int i = 0; i < tabuleiro.getNumLines(); i++) {
            LinearLayout row = new LinearLayout(getContext());
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            for (int j = 0; j < tabuleiro.getNumColumns(); j++) {
                ImageView imageView = new ImageView(getContext());
                imageView.setAdjustViewBounds(true);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                Random r = new Random();

                int index = r.nextInt(16);

                imageView.setImageResource(fotosGis[index]);

                if(selected == "Gislaine"){
                    imageView.setImageResource(fotosGis[index]);
                }

                if(selected == "Rodrigo"){
                    imageView.setImageResource(fotosRod[index]);
                }
                imageView.setLayoutParams(new LinearLayout.LayoutParams(tabuleiro.getWidth(), tabuleiro.getHeight()));

                row.addView(imageView);
            }
            view.addView(row);
        }
        /*ImageView imageView = new ImageView(getContext());
        imageView.setAdjustViewBounds(true);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);*/
    }

}
