package com.wl.tools.format.config;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.wl.tools.format.FastJsonFormat;
import com.wl.tools.format.GsonJsonFormat;
import com.wl.tools.format.JsonFormat;
import com.wl.tools.format.SimpleJsonFormat;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class FormatAutoConfiguration {

    @ConditionalOnClass(name = "com.alibaba.fastjson.JSON")
    @Bean
    @Primary // 优先选择
    public JsonFormat fastJsonFormat() {
        return new FastJsonFormat();
    }

    @ConditionalOnClass(name = "com.google.gson.Gson")
    @Bean
    public JsonFormat gsonJsonFormat() {
        return new GsonJsonFormat();
    }

    @ConditionalOnMissingClass({"com.alibaba.fastjson.JSON", "com.google.gson.Gson"})
    @Bean
    public JsonFormat simpleJsonFormat() {
        return new SimpleJsonFormat();
    }
}
