package com.gupaoedu.home.course;

public class PythonNetCourse extends NetCourse {

    private boolean needHomeWord = false;

    public PythonNetCourse(boolean needHomeWord) {
        this.needHomeWord = needHomeWord;
    }

    @Override
    protected boolean needHomework() {
        return this.needHomeWord;
    }

    @Override
    protected void checkHomework() {
        System.out.println("检查python作业");
    }

}
