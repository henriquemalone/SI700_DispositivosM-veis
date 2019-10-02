package p169624.ft.unicamp.br.aula02.puzzle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tabuleiro {
    private int numLines;
    private int numColumns;
    private List<Integer> blocks;
    private int width;
    private int height;
    private ArrayList<Integer> gameIndex;

    public Tabuleiro(int numLines, int numColumns, List blocks,
                     int width, int height){
        this.numLines = numLines;
        this.numColumns = numColumns;
        this.blocks = blocks;
        this.width = width;
        this.height = height;

        gameIndex = new ArrayList<>();
        for (int i = 0; i < blocks.size(); i++){
            gameIndex.add(i);
        }
        this.startGame();
    }

    public void startGame(){
        Collections.shuffle(this.gameIndex);
    }

    public int getCorrectBlock(int line, int column){

        return blocks.get((line*numColumns)+column);
    }

    public int getGameBlock(int line, int column){

        return gameIndex.get((line*numColumns)+column);
    }

    public void swap(int line1, int column1, int line2, int column2){
        int pos1 = (line1*numColumns)+column1;
        int pos2 = (line2*numColumns)+column2;

        gameIndex.set(pos1, pos2);
        gameIndex.set(pos2, pos1);
    }

    public int getNumLines() {
        return numLines;
    }

    public void setNumLines(int numLines) {
        this.numLines = numLines;
    }

    public int getNumColumns() {
        return numColumns;
    }

    public void setNumColumns(int numColumns) {
        this.numColumns = numColumns;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public ArrayList<Integer> getGameIndex() {
        return gameIndex;
    }

    public void setGameIndex(ArrayList<Integer> gameIndex) {
        this.gameIndex = gameIndex;
    }
}

