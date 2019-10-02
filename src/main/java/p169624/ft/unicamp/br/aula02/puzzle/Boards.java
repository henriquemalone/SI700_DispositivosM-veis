package p169624.ft.unicamp.br.aula02.puzzle;

import java.util.ArrayList;
import java.util.Arrays;

import p169624.ft.unicamp.br.aula02.R;

public class Boards {
    private static Integer[] gislaine = {
            R.drawable.dummy,
            R.drawable.g02,
            R.drawable.g03,
            R.drawable.g04,
            R.drawable.g05,
            R.drawable.g06,
            R.drawable.g07,
            R.drawable.g08,
            R.drawable.g09,
            R.drawable.g10,
            R.drawable.g11,
            R.drawable.g12,
            R.drawable.g13,
            R.drawable.g14,
            R.drawable.g15,
            R.drawable.g16,
    };
    private static Integer[] rodrigo = {
            R.drawable.dummy,
            R.drawable.r02,
            R.drawable.r03,
            R.drawable.r04,
            R.drawable.r05,
            R.drawable.r06,
            R.drawable.r07,
            R.drawable.r08,
            R.drawable.r09,
            R.drawable.r10,
            R.drawable.r11,
            R.drawable.r12,
            R.drawable.r13,
            R.drawable.r14,
            R.drawable.r15,
            R.drawable.r16,
            R.drawable.r17,
            R.drawable.r18,
            R.drawable.r19,
            R.drawable.r20,
            R.drawable.r21,
            R.drawable.r22,
            R.drawable.r23,
            R.drawable.r24
    };

    public static Tabuleiro getPuzzle(int position){
        switch (position) {
            case 0 :
                return new Tabuleiro(4,4,
                        new ArrayList<Integer>(Arrays.asList(gislaine)),
                        90, 90);
            case 1 :
                return new Tabuleiro(6,4,
                        new ArrayList<Integer>(Arrays.asList(rodrigo)),
                        80,69
                );
            default :
                return new Tabuleiro(4,4,
                        new ArrayList<Integer>(Arrays.asList(gislaine)),
                        120,90);
        }

    }

}

