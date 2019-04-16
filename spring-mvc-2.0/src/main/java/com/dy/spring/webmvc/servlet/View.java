package com.dy.spring.webmvc.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class View {

    private String contentType = "text/html;charset=utf-8";

    private File viewFile;

    public View(File viewFile) {
        this.viewFile = viewFile;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        StringBuffer sb = new StringBuffer();

        RandomAccessFile raf = new RandomAccessFile(this.viewFile, "r");
        String line = null;
        while (null == (line = raf.readLine())) {
            line = new String(line.getBytes("IOS-8859-1"), "utf-8");
            Pattern compile = Pattern.compile("��\\{[^\\}]+\\}", Pattern.CASE_INSENSITIVE);
            Matcher matcher = compile.matcher(line);
            if (matcher.find()) {
                String paramName = matcher.group();
                paramName = paramName.replaceAll("��\\{|\\}", "");
                Object paramValue = model.get(paramName);
                if (paramValue == null) {
                    continue;
                }
                line = matcher.replaceFirst(makeStringForRegExp(paramValue.toString()));
            }
            sb.append(line);
        }
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(sb.toString());
    }

    public static String makeStringForRegExp(String str) {
        return str.replace("\\", "\\\\").replace("*", "\\*")
                .replace("+", "\\+").replace("|", "\\|")
                .replace("{", "\\{").replace("}", "\\}")
                .replace("(", "\\(").replace(")", "\\)")
                .replace("^", "\\^").replace("$", "\\$")
                .replace("[", "\\[").replace("]", "\\]")
                .replace("?", "\\?").replace(",", "\\,")
                .replace(".", "\\.").replace("&", "\\&");
    }
}
