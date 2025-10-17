package org.termiclock.number;

import org.termiclock.util.ArrayUtils;
import org.termiclock.util.JsonParser;

import java.util.HashMap;
import java.util.List;

public class Number {
    private String number;
    private String font; // hashtag / star
    private List<List<String>> text;

    public Number(String number, String font  )throws Exception {
        this.number = number;
        this.font = font;
        if (font == null){
            this.font = "hashtag";
        }
        this.initText();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public  String getNumberAsString(){
        return String.valueOf(number);
    }

    private void initText()throws Exception{

        char[] chars = this.number.toCharArray();
        JsonParser jp = new JsonParser();

        HashMap<String,List<List<String>>> tempMap = null;
        List<List<String>> tempList = null;
        List<List<String>> reslutList = null;

        for(char c : chars){
            tempMap = jp.getNumbersFont(this.font);

            tempList = tempMap.get(String.valueOf(c));

            reslutList = ArrayUtils.mergeArray(reslutList, tempList);
        }

        this.text = reslutList;
    }

    public List<List<String>> getText() {
        return text;
    }

    public  void print(){
        ArrayUtils.printArray(this.text);
    }
}
