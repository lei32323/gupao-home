package com.gupaoedu.home.course;

public class NetrCourseTest {

    public static void main(String[] args) {
        //==========java
        System.out.println("---------------java课程");
        JavaNetCourse javaNetCourse = new JavaNetCourse();
        javaNetCourse.createCourse();


        //python
        System.out.println("----------------python课程");
        PythonNetCourse pythonNetCourse = new PythonNetCourse(false);
        pythonNetCourse.createCourse();
    }

}
