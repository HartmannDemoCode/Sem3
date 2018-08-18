/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threaddemos.atomicReadWrite;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author thomas
 */
public class TestStatic {
    private static String val1 = "value 1";
    private static Map<String, String> map = new HashMap();
    
    public static void main(String[] args) {
        String val2 = val1;
        val1 = "changed";
        System.out.println(val1);
        System.out.println(val2); // by value
        
        map.put("k1", "value1");
        Map<String, String> instanseMap = map;
        instanseMap.put("k2", "value2");
        System.out.println(map);
        System.out.println(instanseMap); // by reference
    }
}
