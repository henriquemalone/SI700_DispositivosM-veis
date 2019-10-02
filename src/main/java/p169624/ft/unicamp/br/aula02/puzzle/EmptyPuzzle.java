package p169624.ft.unicamp.br.aula02.puzzle;

import android.widget.ImageView;

import java.util.ArrayList;

public class EmptyPuzzle extends AbstractPuzzle{

    public EmptyPuzzle(Tabuleiro tabuleiro, ArrayList<ImageView> imageViews){
        super(tabuleiro, imageViews);
    }

    public void addListener(ImageView imageView, int line, int column){}
    public boolean endGame(){
        return false;
    }
}

