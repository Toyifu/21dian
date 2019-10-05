package blackjackplus;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import static blackjackplus.SET.remove;
import static blackjackplus.SET.random;

public class PLAY {
    static SET set = new SET();
    static Scanner sc1 = new Scanner(System.in);//pair(int),bet(int)
    static int pair = sc1.nextInt();
    static int bet = sc1.nextInt();
    static int win = 0;
    //static int myscore = 0;
    // static int hescore=0;
    static CARD[] my = new CARD[5];
    static CARD[] he = new CARD[11];
    static double totalwin = 0;

    static ArrayList<Integer> myscore = new ArrayList<Integer>();//用于存放所有可能性
    static ArrayList<Integer> hescore = new ArrayList<Integer>();

    public static ArrayList<Integer> map(int i) {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        for (int j = 0; j < myscore.size(); j++) {
            if (myscore.get(j) + i <= 21) {
                ans.add(myscore.get(j) + i);
            }
        }
        return ans;
    }
    public static ArrayList<Integer> hemap(int i) {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        for (int j = 0; j < hescore.size(); j++) {
            if (hescore.get(j) + i <= 22) {
                ans.add(hescore.get(j) + i);
            }
        }
        return ans;
    }
    public static boolean ex21() {
        for (int j = 0; j < myscore.size(); j++) {
            if (myscore.get(j) == 21) {
                return true;
            }
        }
        return false;
    }

    public static boolean hex21() {
        for (int j = 0; j < hescore.size(); j++) {
            if (hescore.get(j) == 21) {
                return true;
            }
        }
        return false;
    }

    public static boolean ex22() {
        for (int j = 0; j < hescore.size(); j++) {
            if (hescore.get(j) == 22) {
                return true;
            }
        }
        return false;
    }

    public static void begin() {

        my[0] = random();
        remove(my[0]);

        he[0] = random();
        remove(he[0]);
        //hescore.add(he[0].getNum());

        my[1] = random();
        remove(my[1]);

        System.out.println("你的牌1是" + my[0].col + my[0].num + "--你的牌2是" + my[1].col + my[1].num + "--荷官的牌是" + he[0].col + he[0].num);
        if (my[0].num == CARD.NUMBER.A && my[1].num == CARD.NUMBER.A) {
            myscore.add(2);
            myscore.add(12);
            System.out.println("当前分数可为2或者12");
        } else if (my[0].num == CARD.NUMBER.A && my[1].num != CARD.NUMBER.A) {
            myscore.add(11 + my[1].getNum());
            myscore.add(1 + my[1].getNum());
            System.out.println("当前分数为" + myscore.get(0) + "或者" + myscore.get(1));
        } else if (my[1].num == CARD.NUMBER.A && my[0].num != CARD.NUMBER.A) {
            myscore.add(11 + my[0].getNum());
            myscore.add(1 + my[0].getNum());
            System.out.println("当前分数为" + myscore.get(0) + "或者" + myscore.get(1));
        } else {
            myscore.add(my[0].getNum() + my[1].getNum());
            System.out.println("当前分数" + myscore);
        }
        if(he[0].num==CARD.NUMBER.A){hescore.add(1);hescore.add(11);}
        else {hescore.add(he[0].getNum());}
    }

    public static void ifpair() {
        if (pair != 0) {
            System.out.println("你下了TIE来看看运气如何吧");
            if (game.perfectpair(my[0], my[1])) {
                win += pair * 25;
                System.out.println("PERFECTPAIR恭喜！！");
            } else if (game.pair(my[0], my[1])) {
                win += pair * 6;
                System.out.println("普通的PAIR恭喜！");
            } else {
                win = 0;
                System.out.println("一个都没有");
            }
        }
    }

    public static void twoCwin() {
        if (game.twocardwin(my[0], my[1])) {
            win += bet * 1.5;
            System.out.println("恭喜你两张卡就赢了！一共赢了" + win);
            System.exit(0);
        }
    }


    public static void latter() {
        int ii = 0;
        for (int i = 2; i < 5; i++) {
            ii = i + 1;
            System.out.println("还要不要第--" + ii + "--张牌");
            Scanner sc2 = new Scanner(System.in);
            if (sc2.nextLine().equals("Y")) {
                my[i] = random();
                set.remove(my[i]);
                System.out.println("你的第--"+ii+"--张牌是"+my[i].col+my[i].num);
                if (my[i].num != CARD.NUMBER.A) {
                    myscore = map(my[i].getNum());
                    if (ex21()) {
                        win += bet;
                        System.out.println("你在第" + ii + "张牌时到了21点" + "一共赢了" + win);
                        System.exit(0);
                    }
                    if (myscore.size() == 0) {
                        System.out.println("你在第" + ii + "张牌时涨死了" + "一共赢了" + win);
                        System.exit(0);
                    }
                } else {
                    ArrayList<Integer> temp = new ArrayList<>();
                    for (int j = 0; j < myscore.size(); j++) {
                        temp.add(myscore.get(j) + 10);
                    }
                    myscore.addAll(temp);
                    myscore = map(1);
                    if (ex21()) {
                        win += bet;
                        System.out.println("你在第" + ii + "张牌时到了21点" + "一共赢了" + win);
                        System.exit(0);
                    }
                    if (myscore.size() == 0) {
                        System.out.println("你在第" + ii + "张牌时涨死了" + "一共赢了" + win);
                        System.exit(0);
                    }
                }
            } else break;
        }
        if (ii == 5) {
            win += bet;
            System.out.println("5张牌都没有涨死，赢了" + win);
            System.exit(0);
        }
    }


    public static boolean exless17() {
        for (int i = 0; i < hescore.size(); i++) {
            if (hescore.get(i) < 17) {
                return true;
            }
        }
        return false;
    }

    public static void Heguan() {
        int ii = 0;
        for (int i = 1; i < 11; i++) {
            ii = i + 1;
            if (exless17()) {
                he[i] = random();
                remove(he[i]);
                System.out.println("荷官的第"+ii+"张牌是"+he[i].col + he[i].num);
                if (he[i].num != CARD.NUMBER.A) {
                    hescore = hemap(he[i].getNum());
                    if (hex21()) {
                        System.out.println("荷官先到21点你输了，你赢了" + win);
                        System.exit(0);
                    }
                    if (ex22()) {
                        win += bet / 2;
                        System.out.println("荷官先到22点平局，你赢了" + win);
                        System.exit(0);
                    }
                    if (hescore.size() == 0) {
                        win += bet;
                        System.out.println("荷官在第" + ii + "张牌时涨死了" + "你赢了" + win);
                        System.exit(0);
                    }
                } else {
                    ArrayList<Integer> temp2 = new ArrayList<>();
                    for (int j = 0; j < hescore.size(); j++) {
                        temp2.add(hescore.get(j) + 10);
                    }
                    hescore.addAll(temp2);
                    hescore = hemap(1);
                }
                if (hex21()) {
                    System.out.println("荷官先到21点你输了，你赢了" + win);
                    System.exit(0);
                }
                if (ex22()) {
                    win += bet / 2;
                    System.out.println("荷官先到22点平局，你赢了" + win);
                    System.exit(0);
                }
                if (hescore.size() == 0) {
                    win += bet;
                    System.out.println("荷官在第" + ii + "张牌时涨死了" + "你赢了" + win);
                    System.exit(0);
                }
            } else break;
        }

    }

    public  static void comp() {
        int mymax = 0;
        int hemax = 0;
        for (int i = 0; i < myscore.size(); i++) {
            if (mymax < myscore.get(i)) {
                mymax = myscore.get(i);
            }
        }
        System.out.println("我的总分"+mymax);

        for (int i = 0; i < hescore.size(); i++) {
            if (hemax < hescore.get(i)) {
                hemax = hescore.get(i);
            }
        }
        System.out.println("荷官总分"+hemax);
        if (mymax > hemax) {
            win += bet;
            System.out.println("你的总分比荷官多，你赢了" + win);
            System.exit(0);
        } else if (mymax < hemax) {
            System.out.println("你的总分比荷官少，你赢了" + win);
            System.exit(0);
        } else {
            System.out.println("平局，你赢了" + win);
            System.exit(0);
        }
    }
    public static void main(String[] args) {

begin();
ifpair();
twoCwin();
latter();
Heguan();
comp();
    }
}
