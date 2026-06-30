package cn.luxio.opentool.designpattern.decorator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Configuration
@ComponentScan(basePackageClasses = TrimMessageDecorator.class)
@SpringJUnitConfig(DecoratorTest.class)
public class DecoratorTest {

    @Autowired
    private DefaultDecoratorExecutor<MessageContent> executor;

    @Test
    public void decorateShouldApplyDecoratorsByOrder() {
        MessageContent result = executor.decorate(new MessageContent("  hello bad  "));

        assertEquals("hello ***", result.content());
    }
}

