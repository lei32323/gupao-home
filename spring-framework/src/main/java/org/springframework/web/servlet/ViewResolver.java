package org.springframework.web.servlet;

import java.io.File;
import java.util.Locale;

public class ViewResolver {

    private File templateRootFile;

    private String DEFAULT_TEMPLATE_SUFFX = ".html";

    public ViewResolver(String templateRoot) {
        templateRootFile = new File(this.getClass().getClassLoader().getResource(templateRoot).getFile());
    }

    public View resolveViewName(String viewName, Locale locale) {
        //返回一个View对象
        if (viewName == null || "".equals(viewName)) {
            return null;
        }
        viewName = viewName.endsWith(DEFAULT_TEMPLATE_SUFFX) ? viewName : viewName + DEFAULT_TEMPLATE_SUFFX;
        File viewFile = new File(templateRootFile.getPath() + "/" + viewName);
        return new View(viewFile);
    }
}
