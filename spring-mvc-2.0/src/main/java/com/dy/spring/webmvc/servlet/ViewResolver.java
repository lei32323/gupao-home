package com.dy.spring.webmvc.servlet;


import java.io.File;
import java.util.Locale;

public class ViewResolver {

    private File templateRootDir;

    private String DEFAULT_TEMPLATE_SUFFX = ".html";

    public ViewResolver(String templateRoot) {
        String filePath = this.getClass().getClassLoader().getResource(templateRoot).getFile();
        templateRootDir = new File(filePath);
    }

    public View resolveViewName(String viewName, Locale locale) {
        if (viewName == null || "".equals(viewName.trim())) {
            return null;
        }
        viewName = viewName.endsWith(DEFAULT_TEMPLATE_SUFFX) ? viewName : viewName + DEFAULT_TEMPLATE_SUFFX;
        File templateFile = new File((templateRootDir.getPath() + "/" + viewName).replaceAll("/+", "/"));
        return new View(templateFile);
    }
}
