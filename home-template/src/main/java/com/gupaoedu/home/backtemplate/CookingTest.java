package com.gupaoedu.home.backtemplate;

public class CookingTest {

    public static void main(String[] args) {
        Cooking cooking = new Cooking();
        cooking.cooking(new Cooking.Seasoning() {
            @Override
            public void seasoning() {
                System.out.println("放红烧肉调料");
            }
        });

        Cooking cooking2 = new Cooking();
        cooking.cooking(null);
    }

}
