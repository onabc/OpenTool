package cn.luxio.opentool.designpattern.chain;

import cn.luxio.opentool.designpattern.chain.executor.ChainAction;
import cn.luxio.opentool.designpattern.chain.model.ChainContext;
import cn.luxio.opentool.designpattern.chain.model.ChainRequest;
import cn.luxio.opentool.designpattern.chain.model.ChainResult;

public abstract class AbstractChainAction implements ChainAction<ChainRequest, ChainContext, ChainResult> {
}
