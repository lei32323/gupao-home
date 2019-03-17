package com.gupaoedu.home.course;

/**
 * 抽象类，抽取出公共的方法
 */
public abstract class NetCourse {

    public void createCourse(){

        //发布预习资料
        postPreResource();

        //准备ppt
        readyPPT();

        //直播
        liveVideo();

        //上传视频，源码，课件
        postResource();

        //有作业，批改作业
        if(needHomework()){
            checkHomework();
        }

    }


    //1.发布预习资料
    protected void postPreResource(){
        System.out.println("发布预习资料");
    }

    //2.准备PPT
    protected void readyPPT(){
        System.out.println("准备PPT");
    }

    //3.直播视频
    protected void liveVideo(){
        System.out.println("直播视频");
    }

    //4.上传视频，源码，课件
    protected void postResource(){
        System.out.println("上传视频，源码，课件");
    }

    //5.布置课后作业 有的课程需要课后作业，有的课程不需要课后作业，这个需要子类去根据自身的情况去处理 //默认是没有的
    protected  boolean needHomework(){
        return true;
    }

    //6.有布置作业就检查作业,没有就不检查作业
    protected  abstract void checkHomework();


}
