package org.termiclock;

import org.termiclock.clock.ClockProcess;
import org.termiclock.number.Number;

public class Main {
    public static void main(String[] args) {
        
        try {
            ClockProcess.handleArguments(args).launch();;
         
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}