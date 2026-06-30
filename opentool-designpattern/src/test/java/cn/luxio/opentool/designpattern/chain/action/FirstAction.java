package cn.luxio.opentool.designpattern.chain.action;

import cn.luxio.opentool.core.response.Result;
import cn.luxio.opentool.designpattern.chain.AbstractChainAction;
import cn.luxio.opentool.designpattern.chain.enums.ActionOrder;
import cn.luxio.opentool.designpattern.chain.annotation.OrderedComponent;
import cn.luxio.opentool.designpattern.chain.model.ChainContext;
import cn.luxio.opentool.designpattern.chain.model.ChainRequest;
import cn.luxio.opentool.designpattern.chain.model.ChainResult;

@OrderedComponent(ActionOrder.FIRST)
public class FirstAction extends AbstractChainAction {
    @Override
    public Result<ChainResult> process(ChainRequest request, ChainContext context) {
        System.out.println("FirstAction");
        return Result.success();
    }
}
