package com.gupaoedu.home.house;

public class HouseTest {

    public static void main(String[] args) {
        House house = new House();
        System.out.println(house.getMsg());

        //安装电视后
        House tvhouse = new TVHouseDecorator(house);
        System.out.println(tvhouse.getMsg());

        //安装电视后
        House tvhouse1 = new TVHouseDecorator(tvhouse);
        System.out.println(tvhouse1.getMsg());

    }

}
