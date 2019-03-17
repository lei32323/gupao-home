package com.gupaoedu.home;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class CommonTemplateTest {

    public static void main(String[] args) {
        //1.Arrays.sort()
        List<User> users = new ArrayList<>();
        users.add(new User("张三",11));
        users.add(new User("李四",12));
        users.add(new User("王五",10));
        users.add(new User("孙七",19));
        users.add(new User("赵六",18));
        Object[] objects = users.toArray();
        Arrays.sort(objects,new Comparator(){

            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof User&&o2 instanceof User){
                    User u1 = (User)o1;
                    User u2 = (User)o2;
                    if(u1.getAge()<u2.getAge()){
                        return -1;
                    }
                }
                return 0;
            }
        });

        Stream.of(objects).forEach(user->{
            System.out.println(user);
        });

    }


}
