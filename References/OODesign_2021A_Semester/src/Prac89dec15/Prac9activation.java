package Prac89dec15;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class Prac9activation {

    public Prac9activation() {

        Q1();
        Q2();

    }

    private void Q2() {
        ArrayList<Object> oArrLst = new ArrayList<>();
        oArrLst.add(5);
        oArrLst.add("str1");
        oArrLst.add(6);
        oArrLst.add('c');

        Collections.sort(oArrLst, new SortObjectsQ2());




    }

    private void Q1() {

        HashMap<Integer,String> hm = new HashMap<>();
        hm.put(100,"Hundred");
        hm.put(1,"One");
        hm.put(8,"Eight");
        hm.put(6,"Six");
        hm.put(7,"Seven");



        System.out.println(hm);
        hm.remove(100); // removes 100

        System.out.println(hm);

        //print all the keys
        System.out.println(hm.keySet()); // prints only the values

        System.out.println("____Pretty print____");
        for(Integer key: hm.keySet())
            System.out.println("key=" + key + "\t value=" + hm.get(key));


        System.out.println("is 6 exists? " + hm.containsKey(6) + "\t is Five exists? " + hm.containsValue("Five"));



    }


}
