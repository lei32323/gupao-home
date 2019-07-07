package com.wl.tools.format;

import com.alibaba.fastjson.JSON;

public class FastJsonFormat implements JsonFormat {
    public String jsonFormat(Object object) {

        return JSON.toJSONString(object);

    }
}
