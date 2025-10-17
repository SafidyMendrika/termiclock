package org.termiclock.util;

import java.util.List;

public class ArrayUtils {
    public static void printArray(List<List<String>> lists){
        System.out.print("\033c");
        System.out.flush();
        for (List<String> list : lists){
            for (String s : list){
                System.out.print(s);
            }
            System.out.println();
        }
    }

     public static List<List<String>> mergeArray(List<List<String>> original,List<List<String>> toMegre){
        if (original == null) {
            return toMegre;
        }
        List<List<String>> result = original;
        ArrayUtils.addCharSpace(result, null);

        for(int i = 0 ; i < result.size(); i++){
            result.get(i).addAll(toMegre.get(i));
        }
        return result;
    }
    public static void addCharSpace(List<List<String>> list,String charSpace){
        if (charSpace == null) {
            charSpace = " ";
        }
        for(int i = 0 ; i < list.size(); i++){
            list.get(i).add(charSpace);
        }
    }

    public static void clearVariable(Object ...objects){
        for (Object object : objects) {
            object = null;
        }
    }
}
