package blackjackplus;

import java.util.ArrayList;

public class CARD {
    enum NUMBER {two, three, four, five, six, seven, eight, nine, ten, J, Q, K, A}

    enum COLOR {S, C, H, D}

    NUMBER num;
    COLOR col;

    public CARD(NUMBER NUM, COLOR COL) {
        this.num = NUM;
        this.col = COL;
    }

    public COLOR getCol() {
        return col;
    }

    public int getNum() {
        switch (num) {
            case two:
                return 2;
            case three:
                return 3;
            case four:
                return 4;
            case five:
                return 5;
            case six:
                return 6;
            case seven:
                return 7;
            case eight:
                return 8;
            case nine:
                return 9;
            case ten:
                return 10;
            case J:
                return 10;
            case Q:
                return 10;
            case K:
                return 10;
            default:
                break;
        }
        return 0;
    }

    public static NUMBER getint(int i) {
        switch (i) {
            case 0:
                return NUMBER.two;
            case 1:
                return NUMBER.three;
            case 2:
                return NUMBER.four;
            case 3:
                return NUMBER.five;
            case 4:
                return NUMBER.six;
            case 5:
                return NUMBER.seven;
            case 6:
                return NUMBER.eight;
            case 7:
                return NUMBER.nine;
            case 8:
                return NUMBER.ten;
            case 9:
                return NUMBER.J;
            case 10:
                return NUMBER.Q;
            case 11:
                return NUMBER.K;
            default:
                break;
        }
        return NUMBER.A;
    }

    public static COLOR getcol(int i) {
        switch (i) {
            case 0:
                return COLOR.C;
            case 1:
                return COLOR.D;
            case 2:
                return COLOR.H;
            default:
                break;
        }
        return COLOR.S;
    }


}
