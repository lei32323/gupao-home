package org.springframework.aop2;

import lombok.Data;

/**
 * @Auther: wanglei
 * @Date: 2019/4/21 08:43
 * @Description:
 */
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
