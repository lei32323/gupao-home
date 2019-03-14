package com.gupaoedu.home.service.strategy;


/**
 * 主副卡新装
 */
public class NewMasterOrViceScenes extends AbstractScenes{

    @Override
    public String getName() {
        return "主副卡新装";
    }

    @Override
    public String getSale_order_template_id() {
        return "NEW-4G";
    }

    @Override
    public boolean containDeviceNumber(String rootObjNum) {
        return rootObjNums.contains(rootObjNum);
    }

    //场景判断
    @Override
    public int decisionScenes() {
        //主卡
        int masterSize = this.masterCardSize("新建");
        //副卡
        int viceSize = this.vicecardSize("新建");
        //当有一个新建的主卡，副卡都是（新建状态的）就是主副卡新装
        if(masterSize>0 && viceSize==this.vicecard.size()){
            return 1;
        }
        return -1;
    }
}
