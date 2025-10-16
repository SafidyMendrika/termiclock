package org.termiclock;

import org.termiclock.number.Number;

public class Main {
    public static void main(String[] args) {
        try {
            Number n = null;
            for(int i = 0 ; i < 10 ; i++){
                n = new Number(i,"star");
                n.print();
                Thread.sleep(1000);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}