package com.steam;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * ArrayList Stream的使用
 */
public class StreamApplicationMain {

    public static void main(String[] args){
        List<User> userList = new ArrayList<>(){{
            add(new User("Jack",21,true));
            add(new User("Rick",65,true));
            add(new User("Faker",24,true));
            add(new User("Jessica",180,false));
            add(new User("Adam",90,true));
            add(new User("Adam",90,true));
        }};

        //添加条件并且算出过滤后的元素总数
        Long userCount1 = userList.stream().filter(u -> u.getAge() > 20 && u.getSex()).count();
        //转为为集合[Collection/List/Set/Map]
        List<User> userList1 = userList.stream().collect(Collectors.toList());
        //转换为Object数组
        Object[] userArr1 =  userList.stream().toArray();
        //去除重复的元素(去重逻辑依赖元素的equals方法)
        List<User> userList2 = userList.stream().distinct().collect(Collectors.toList());
        //消费函数:生成一个包含原Stream的所有元素的新Stream，同时会提供一个消费函数（Consumer实例），新Stream每个元素被消费的时候都会执行给定的消费函数
        /** 注意这里设置了user的age会影响到原来的List里面的元素(因为是修改的地址) **/
        List<User> userList3 = userList.stream().peek(System.out::println).collect(Collectors.toList());
        //"截取" 前2个元素(从前面开始)
        List<User> userList4 = userList.stream().limit(2).collect(Collectors.toList());
        //"跳过" 前2个元素(从后面开始)
        List<User> userList5 = userList.stream().skip(2).collect(Collectors.toList());

        //判断 [所有] 的元素是否满足条件(所有User的age是否大于100)
        Boolean b1 = userList.stream().allMatch(u -> u.getAge() > 100);
        //判断是否 [任何一个] 元素满足条件
        Boolean b2 = userList.stream().anyMatch(u -> u.getAge() == 90);
        //是不是Stream中的 [所有元素都不满足] 给定的匹配条件
        Boolean b3 = userList.stream().noneMatch(u -> u.getUsername().equals("Liang"));
        //返回第一个元素(如果没有,则返回空Optional)
        Optional<User> o1 = userList.stream().findFirst();
        //返回Age最大的元素(如果没有,则返回空Optional)
        Optional<User> o2 = userList.stream().max(Comparator.comparingInt(User::getAge)); //等价于: (a,b)->a.getAge()-b.getAge()
        //返回Age最小的元素(如果没有,则返回空Optional)
        Optional<User> o3 = userList.stream().min(Comparator.comparingInt(User::getAge)); //等价于: (a,b)->a.getAge()-b.getAge()

        //这里会无限循环输出随机数
        Stream.generate(Math::random).forEach(System.out::println);
        //通过静态方法生成Stream
        List<Integer> streamList = Stream.of(0,1,2,3,4,5,6,7,8,9).collect(Collectors.toList());

    }

    @Data
    @AllArgsConstructor
    static class User{
        private String username;
        private Integer age;
        private Boolean sex;

        public static Integer getData(){
            return 123;
        }
    }

}
