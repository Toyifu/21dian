package blackjackplus;

import com.sun.source.doctree.StartElementTree;

import java.util.ArrayList;
import java.util.Random;

public class SET {
   public static ArrayList<CARD> S = new ArrayList<CARD>();

    public SET() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 13; j++) {
                for (int k = 0; k < 4; k++) {
                    CARD c = new CARD(CARD.getint(j), CARD.getcol(k));
                    S.add(c);
                }
            }
        }
    }

    public static void remove(CARD c) {
        for (int i = 0; i < S.size(); i++) {
            if (S.get(i) == c) {
                S.remove(i);
                break;
            }
        }
    }

    public static CARD random() {
        Random r = new Random();
        int i = r.nextInt(S.size());
        return S.get(i);

    }

    public static void main(String[] args) {
SET set = new SET();
CARD c = random();
remove(c);
System.out.println(c.col);

    }
}
