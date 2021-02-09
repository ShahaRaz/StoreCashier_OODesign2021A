package Prac89dec15;

import java.util.Comparator;

public class SortObjectsQ2 implements Comparator {
    public SortObjectsQ2() {

    }


    @Override
    public int compare(Object o1, Object o2) {
        String o1Type = o1.getClass().getSimpleName();
        String o2Type = o2.getClass().getSimpleName();

        return o1Type.indexOf(o1Type) - o2Type.indexOf(o2Type);

    }
}
