package cn.luxio.opentool.designpattern.observer;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderEventRecorder {

    private final List<String> records = new ArrayList<>();

    void add(String record) {
        records.add(record);
    }

    void clear() {
        records.clear();
    }

    List<String> records() {
        return records;
    }
}
