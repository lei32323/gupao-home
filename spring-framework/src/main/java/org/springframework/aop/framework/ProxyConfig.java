package org.springframework.aop.framework;

import lombok.Data;

@Data
public class ProxyConfig {

    private String pointCut;

    private String aspectClass;

    private String aspectBefore;

    private String aspectAfter;

    private String aspectAfterThrow;

    private String aspectAfterThrowingName;

    private String aspectAround;

}
