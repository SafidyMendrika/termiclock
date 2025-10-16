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
        String strNumber = String.valueOf(this.number);

        char[] chars = strNumber.toCharArray();
        JsonParser jp = new JsonParser();

        HashMap<String,List<List<String>>> tempMap = null;
        List<List<String>> tempList = null;
        List<List<String>> reslutList = null;

        for(char c : chars){
            System.out.println(c);
            tempMap = jp.getNumbersFont(this.font);

            tempList = tempMap.get(String.valueOf(c));

            reslutList = this.merge(reslutList, tempList);
        }

        this.text = reslutList;
        System.out.println("done");
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

    private List<List<String>> merge(List<List<String>> original,List<List<String>> toMegre){
        if (original == null) {
            return toMegre;
        }
        List<List<String>> result = original;
        this.addCharSpace(result, null);

        for(int i = 0 ; i < result.size(); i++){
            result.get(i).addAll(toMegre.get(i));
        }
        return result;
    }
    private void addCharSpace(List<List<String>> list,String charSpace){
        if (charSpace == null) {
            charSpace = " ";
        }
        for(int i = 0 ; i < list.size(); i++){
            list.get(i).add(charSpace);
        }
    }
}
