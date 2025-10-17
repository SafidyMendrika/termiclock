package org.termiclock;

import org.termiclock.clock.ClockProcess;
import org.termiclock.number.Number;

public class Main {
    public static void main(String[] args) {
        
        try {
            ClockProcess clockProcess = new ClockProcess();
            clockProcess.launch();
         
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}