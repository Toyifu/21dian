package blackjackplus;

public class game {


     static boolean perfectpair(CARD c1, CARD c2) {
        if (c1.num == c2.num && c1.col == c2.col) {
            return true;
        } else {
            return false;
        }
    }

    static boolean pair(CARD c1, CARD c2) {
        if (c1.num == c2.num) {
            return true;
        } else {
            return false;
        }
    }

    static boolean twocardwin(CARD c1, CARD c2) {
        if (c1.num == CARD.NUMBER.A && (c2.num == CARD.NUMBER.J || c2.num == CARD.NUMBER.Q || c2.num == CARD.NUMBER.K)
                || c2.num == CARD.NUMBER.A && (c1.num == CARD.NUMBER.J || c1.num == CARD.NUMBER.Q || c1.num == CARD.NUMBER.K)) {
            return true;
        }
        return false;
    }

}
