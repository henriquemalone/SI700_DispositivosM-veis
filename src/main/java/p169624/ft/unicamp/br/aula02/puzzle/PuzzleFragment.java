package p169624.ft.unicamp.br.aula02.puzzle;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import p169624.ft.unicamp.br.aula02.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PuzzleFragment extends Fragment {

    private LinearLayout view;
    private ImageView imageView;
    private Tabuleiro tabuleiro;

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
        //imageView = view.findViewById(R.id.foto);
        //imageView.setImageResource(R.drawable.ic_launcher_background);

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

                //imageView.setImageResource(R.drawable.g02);
                imageView.setLayoutParams(new LinearLayout.LayoutParams(tabuleiro.getWidth(), tabuleiro.getHeight()));

                row.addView(imageView);
            }
            view.addView(row);
        }
        /*ImageView imageView = new ImageView(getContext());
        imageView.setAdjustViewBounds(true);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);*/
    }

    public void armazenaImagens(){

    }

}
