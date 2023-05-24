package PojoClasses;

import java.util.ArrayList;
import java.util.List;

public class demo {
    public static void main(String[] args) {
        List<Integer> Songs= new ArrayList<>();
        String a="'";
        String b=a.concat("abc");
        String c="'";
        String d=b.concat(c);
        System.out.println(d);
    }
}
