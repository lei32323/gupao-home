package com.gupaoedu.home.service.strategy;

import com.gupaoedu.home.service.jsoncheck.BusiInfo;
import com.gupaoedu.home.service.jsoncheck.Item;
import com.gupaoedu.home.service.jsoncheck.PubInfo;
import com.gupaoedu.home.service.jsoncheck.Root;

import java.util.ArrayList;
import java.util.List;

/**
 * 场景分支的抽象类
 */
public abstract class AbstractScenes  {

    //提供初始化的方法
    public void init(Root root){
        this.pubInfo = root.getCont().getPub_info();
        this.cards = root.getCont().getBusi_info().getSale_orderPreChk().getItem_list().getItem();
        //获取主副卡
        this.findMasterOrSecondaryCards();
    }

    protected static List<String> rootObjNums = new ArrayList<String>();
    static{
        rootObjNums.add("467");
        rootObjNums.add("470");
        rootObjNums.add("483");
        rootObjNums.add("465");
        rootObjNums.add("466");
        rootObjNums.add("479");
        rootObjNums.add("528");
        rootObjNums.add("593");
        rootObjNums.add("638");
    }


    protected List<Item> mastercards = new ArrayList<Item>();// 主卡

    protected List<Item> vicecard = new ArrayList<Item>();// 副卡

    protected List<Item> cards =  new ArrayList<Item>(); //全部卡

    private PubInfo pubInfo;//基本信息


    //1.获取场景名称
    public abstract String getName();

    //2.场景编码
    public abstract String getSale_order_template_id();

    //3.某个行为的主卡的个数 行为(新装。互转，拆机）
    public int masterCardSize(String action){
        int count = 0;
        for (Item item : mastercards){
            if (action.equals(item.getOper_type())) {
                count++;
            }
        }
        return count;
    }

    //4.某个行为的副卡的个数 行为(新装。互转，拆机）
    public int vicecardSize(String action){
        int count = 0;
        for (Item item : vicecard){
            if (action.equals(item.getOper_type())) {
                count++;
            }
        }
        return count;
    }

    //5.判断设备号
    public abstract boolean containDeviceNumber(String rootObjNum);

    //获取主副卡
    public void findMasterOrSecondaryCards(){
        for (Item item : cards) {
            if (!containDeviceNumber(item.getRoot_obj_num())) {
                continue;
            }

            if (item.getPar_sn().equals("")) {// 主卡
                mastercards.add(item);
            }

            if (!item.getPar_sn().equals("")) {// 副卡
                vicecard.add(item);
            }
        }
    }

    //场景判断
    public abstract int decisionScenes();


}
