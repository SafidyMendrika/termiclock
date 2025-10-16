package org.termiclock;

import org.termiclock.number.Number;

public class Main {
    public static void main(String[] args) {
        try {
            Number n = null;
            for(int i = 89 ; i < 108 ; i++){
                n = new Number(i,"hashtag");
                n.print();
                Thread.sleep(500);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}