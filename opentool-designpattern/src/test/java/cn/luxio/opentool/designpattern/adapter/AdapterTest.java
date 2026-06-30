package cn.luxio.opentool.designpattern.adapter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Configuration
@ComponentScan(basePackageClasses = JsonOrderAdapter.class)
@SpringJUnitConfig(AdapterTest.class)
public class AdapterTest {

    @Autowired
    private DefaultAdapterExecutor<ExternalOrder, InternalOrder> executor ;

    @Test
    public void adaptShouldUseMatchedAdapter() {

        InternalOrder order = executor.adapt(new ExternalOrder("json", "NO1001"));

        assertEquals("JSON-NO1001", order.orderNo());
    }

    @Test
    public void adaptShouldThrowWhenAdapterDoesNotExist() {

        assertThrows(IllegalArgumentException.class, () -> executor.adapt(new ExternalOrder("xml", "NO1002")));
    }
}

record ExternalOrder(String type, String orderNo) {
}

record InternalOrder(String orderNo) {
}

@Component
class JsonOrderAdapter implements Adapter<ExternalOrder, InternalOrder> {

    @Override
    public boolean supports(ExternalOrder source) {
        return "json".equals(source.type());
    }

    @Override
    public InternalOrder adapt(ExternalOrder source) {
        return new InternalOrder("JSON-" + source.orderNo());
    }
}

@Component
class CsvOrderAdapter implements Adapter<ExternalOrder, InternalOrder> {

    @Override
    public boolean supports(ExternalOrder source) {
        return "csv".equals(source.type());
    }

    @Override
    public InternalOrder adapt(ExternalOrder source) {
        return new InternalOrder("CSV-" + source.orderNo());
    }
}
