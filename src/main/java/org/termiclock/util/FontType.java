package org.termiclock.util;

import com.google.gson.Gson;

public class FontType {
    public String name;
    public String path;
    public String value;
    public String dotsPath;

    public String toJson(){
        return new Gson().toJson(this);
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public String getValue() {
        return value;
    }

    public void setDotsPath(String dotsPath) {
        this.dotsPath = dotsPath;
    }

    public String getDotsPath() {
        return dotsPath;
    }
}
