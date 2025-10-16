package org.termiclock.number;

import org.termiclock.util.JsonParser;

import java.util.HashMap;
import java.util.List;

public class Number {
    private int number;
    private String font; // hashtag / star
    private List<List<String>> text;

    public Number(int number, String font  )throws Exception {
        this.number = number;
        this.font = font;
        if (font == null){
            this.font = "hashtag";
        }
        this.initText();
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public  String getNumberAsString(){
        return String.valueOf(number);
    }

    private void initText()throws Exception{
        JsonParser jp = new JsonParser();
        HashMap<String,List<List<String>>> map = jp.getNumbersFont(this.font);

        this.text = map.get(this.getNumberAsString());
    }

    public List<List<String>> getText() {
        return text;
    }

    public  void print(){
        System.out.print("\033c");
        System.out.flush();
        for (List<String> list : this.text){
            for (String s : list){
                System.out.print(s);
            }
            System.out.println();
        }

    }
}
