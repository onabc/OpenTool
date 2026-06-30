package cn.luxio.opentool.designpattern.decorator;

import org.springframework.stereotype.Component;

@Component
public class TrimMessageDecorator implements Decorator<MessageContent> {

    @Override
    public int getOrder() {
        return 10;
    }

    @Override
    public MessageContent decorate(MessageContent target) {
        return new MessageContent(target.content().trim());
    }
}
