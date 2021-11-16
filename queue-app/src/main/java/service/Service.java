package service;

import java.util.ArrayList;
import java.util.List;

public class Service {

    public List<Float> values(List<String> list){
        List<Float> values = new ArrayList<>();
        int i=1;
        float past = Float.parseFloat(list.get(0));
        values.add(past);
        while(i < list.size() - 1){
            float current = Float.parseFloat(list.get(i));
            values.add(current-past);
            past = current;
            i++;
        }
        return values;
    }
}
