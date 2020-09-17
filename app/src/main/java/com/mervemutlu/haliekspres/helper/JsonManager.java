package com.mervemutlu.haliekspres.helper;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;

public class JsonManager<T> {
    public String ObjectToJson(T data){
        Gson gson = new Gson();
        return gson.toJson(data);
    }
    public T JsonToObject(String json, Class<T> type){
        Gson gson = new Gson();
        return gson.fromJson(json,type);
    }
    public List<T> JsonArrayToObjectList(String json, Type listType){
        Gson gson = new Gson();
        return gson.fromJson(json, listType);
    }
}
