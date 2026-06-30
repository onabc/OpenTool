package cn.luxio.opentool.designpattern.decorator;

import org.springframework.stereotype.Component;

@Component
public class SensitiveWordMessageDecorator implements Decorator<MessageContent> {

    @Override
    public int getOrder() {
        return 20;
    }

    @Override
    public MessageContent decorate(MessageContent target) {
        return new MessageContent(target.content().replace("bad", "***"));
    }
}
