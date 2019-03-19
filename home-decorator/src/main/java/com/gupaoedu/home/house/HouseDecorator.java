package com.gupaoedu.home.house;

/**
 * 对房子进行装饰
 */
public class HouseDecorator extends House{

    private House house;

    public HouseDecorator(House house) {
        this.house = house;
    }

    @Override
    String getMsg() {
        return house.getMsg();
    }
}
