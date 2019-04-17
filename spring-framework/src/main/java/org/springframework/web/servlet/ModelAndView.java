package org.springframework.web.servlet;

import java.util.Map;

public class ModelAndView {

    private String view;

    private Map<String,?> model;

    public ModelAndView(String view, Map<String, ?> model) {
        this.view = view;
        this.model = model;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public Map<String, ?> getModel() {
        return model;
    }

    public void setModel(Map<String, ?> model) {
        this.model = model;
    }
}
