package cn.luxio.opentool.designpattern.chain.action;

import cn.luxio.opentool.core.response.Result;
import cn.luxio.opentool.designpattern.chain.enums.ActionOrder;
import cn.luxio.opentool.designpattern.chain.annotation.OrderedComponent;
import cn.luxio.opentool.designpattern.chain.model.ChainContext;
import cn.luxio.opentool.designpattern.chain.model.ChainRequest;
import cn.luxio.opentool.designpattern.chain.model.ChainResult;

@OrderedComponent(ActionOrder.THIRD)
public class ThirdAction extends AbstractChainAction{
    @Override
    public Result<ChainResult> process(ChainRequest request, ChainContext context) {
        System.out.println("ThirdAction");
        return Result.success(new ChainResult());
    }
}
