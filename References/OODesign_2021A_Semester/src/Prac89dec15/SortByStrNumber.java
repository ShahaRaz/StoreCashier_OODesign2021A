package Prac89dec15;

import java.util.Comparator;
import java.util.HashMap;


public class SortByStrNumber implements Comparator<String> {
    private HashMap<String,Integer> stringNumber;
    public SortByStrNumber() {
        stringNumber = new HashMap<>();
        String[] str = new String[] {"Zero" ,"One","Two","Three","Four","Five","Six","Seven","Eight", "Nine"};
        for (int i = 0; i < str.length; i++) {
            stringNumber.put(str[i],i+10);
        }
        System.out.println(stringNumber);


        }




    @Override
    public int compare(String s1, String s2) {
        return stringNumber.get(s1)-stringNumber.get(s2);
    }
}
