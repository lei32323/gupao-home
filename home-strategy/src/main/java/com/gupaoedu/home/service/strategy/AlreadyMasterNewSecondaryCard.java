package com.gupaoedu.home.service.strategy;

/**
 * 已有主卡新装副卡
 */
public class AlreadyMasterNewSecondaryCard extends AbstractScenes {

    @Override
    public String getName() {
        return "已有主卡,新装副卡";
    }

    @Override
    public String getSale_order_template_id() {
        return "NEW-4G";
    }

    @Override
    public boolean containDeviceNumber(String rootObjNum) {
        return rootObjNums.contains(rootObjNum);
    }

    @Override
    public int decisionScenes() {
        int masterSize = this.masterCardSize("新建");
        int viceSize = this.vicecardSize("新建");
        //当主卡为0，副卡大于0的时候，
        if(masterSize==0 && viceSize>0){
            return 2;
        }

        return -1;
    }
}
