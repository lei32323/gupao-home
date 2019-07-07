package com.wl.tools.format;

import org.springframework.util.ObjectUtils;

public class SimpleJsonFormat implements JsonFormat {
    public String jsonFormat(Object object) {
        return ObjectUtils.identityToString(object);
    }
}
