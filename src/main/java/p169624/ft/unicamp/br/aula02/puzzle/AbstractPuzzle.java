package p169624.ft.unicamp.br.aula02.puzzle;

import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;

public abstract class AbstractPuzzle {

    private Tabuleiro tabuleiro;
    private ArrayList<ImageView> imageViews;

    public AbstractPuzzle(Tabuleiro tabuleiro, ArrayList<ImageView> imageViews){
        this.tabuleiro = tabuleiro;
        this.imageViews = imageViews;
        startGame();
        redraw();
        for (int i = 0; i < tabuleiro.getNumLines(); i++){
            for (int j = 0; j < tabuleiro.getNumColumns(); j++){
                addListener(imageViews.get(i*tabuleiro.getNumColumns()+j),i,j);
            }
        }
    }

    protected void redraw(){
        int imagePos;

        for(int i = 0; i < tabuleiro.getNumLines(); i++){
            for(int j = 0; j < tabuleiro.getNumColumns(); j++){
                imagePos = (i*tabuleiro.getNumColumns())+j;
                imageViews.get(imagePos).setImageResource(tabuleiro.getGameIndex().get(imagePos));
            }
        }
    }

    public abstract void addListener(ImageView imageView, int line, int column);
    public abstract boolean endGame();

    public void startGame(){
        tabuleiro.startGame();
    }
}

