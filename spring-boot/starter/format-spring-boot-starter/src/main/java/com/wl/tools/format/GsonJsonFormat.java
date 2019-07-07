package com.wl.tools.format;

import com.google.gson.Gson;

public class GsonJsonFormat implements JsonFormat {

    public String jsonFormat(Object object) {

        Gson gson = new Gson();
        return gson.toJson(object);

    }
}
