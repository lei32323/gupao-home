package com.gupaoedu.home.house;

public class TVHouseDecorator extends HouseDecorator {
    public TVHouseDecorator(House house) {
        super(house);
    }


    @Override
    String getMsg() {
        return super.getMsg()+" 包含了电视";
    }
}
