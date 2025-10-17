package org.termiclock.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

public class JsonParser {
    public  JsonParser() {

    }
    private  final String fontPath = "src/main/java/org/termiclock/font";
    public List<FontType> getFontTypes() throws  Exception{
        Gson gson = new Gson();
        List<FontType> types = null;
        try (Reader reader = new FileReader(fontPath + "/types.json")) {
            Type listFontType = new TypeToken<List<FontType>>() {}.getType();

            types = gson.fromJson(reader, listFontType);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return types;
    }

    public FontType getFontType(String fontTypeName) throws Exception{
        List<FontType> types = this.getFontTypes();
        if (types == null) throw  new Exception("No Font yet");

        return types.stream()
                .filter(t -> t.getName().equalsIgnoreCase(fontTypeName))
                .findFirst()
                .orElseThrow(() -> new Exception("Font type not found: " + fontTypeName));
    }

    public HashMap<String,List<List<String>>> getNumbersFont(String fontName)throws  Exception{
        Gson gson = new Gson();
        HashMap<String, List<List<String>>> map = null;

        FontType fontType = this.getFontType(fontName);

        try (FileReader reader = new FileReader(this.fontPath +"/"+ fontType.getPath())) {

            Type type = new TypeToken<HashMap<String, List<List<String>>>>() {}.getType();

             map = gson.fromJson(reader, type);
        }catch (Exception e){
            e.printStackTrace();
            throw  e;
        }
        return map;
    }

    public String objectToJson(Object object) throws Exception{
        Gson gson = new Gson();
        String json = gson.toJson(object);
        return json;
    }

    public List<List<String>> getTimeDots(String fontName)throws Exception{
        Gson gson = new Gson();
        List<List<String>> dots = null;

        FontType fontType = this.getFontType(fontName);

        try (FileReader reader = new FileReader(this.fontPath +"/"+ fontType.getDotsPath())) {

            Type type = new TypeToken<List<List<String>>>() {}.getType();

            dots = gson.fromJson(reader, type);
        }catch (Exception e){
            e.printStackTrace();
            throw  e;
        }
        return dots;
    }
    
    public List<List<String>> getDateDots(String fontName)throws Exception{
        Gson gson = new Gson();
        List<List<String>> dots = null;

        FontType fontType = this.getFontType(fontName);

        try (FileReader reader = new FileReader(this.fontPath +"/"+ fontType.getDatePath())) {

            Type type = new TypeToken<List<List<String>>>() {}.getType();

            dots = gson.fromJson(reader, type);
        }catch (Exception e){
            e.printStackTrace();
            throw  e;
        }
        return dots;
    }
}
