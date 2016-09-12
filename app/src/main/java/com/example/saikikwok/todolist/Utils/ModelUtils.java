package com.example.saikikwok.todolist.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

/**
 * Created by saikikwok on 9/11/16.
 */

public class ModelUtils {

    private static Gson gson = new Gson();
    private static String PREF_NAME = "model";

    public static void save (Context context, String key, Object ob) {
        SharedPreferences sp = context.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String jsonString = gson.toJson(ob);
        sp.edit().putString(key, jsonString).apply();
    }

    public static <T> T read(Context context, String key, TypeToken<T> typeToken) {
        SharedPreferences sp = context.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        try {
            return gson.fromJson(sp.getString(key, ""), typeToken.getType());
        } catch (JsonSyntaxException e) {
            return null;
        }
    }

    public static <T> T toObject(String json, TypeToken<T> typeToken) {
        return gson.fromJson(json, typeToken.getType());
    }

    public static <T> String toString(T object, TypeToken<T> typeToken) {
        return gson.toJson(object, typeToken.getType());
    }

}
