package cn.luxio.opentool.designpattern.chain.action;

import cn.luxio.opentool.designpattern.chain.executor.Action;
import cn.luxio.opentool.designpattern.chain.model.ChainContext;
import cn.luxio.opentool.designpattern.chain.model.ChainRequest;
import cn.luxio.opentool.designpattern.chain.model.ChainResult;

public abstract class AbstractChainAction implements Action<ChainRequest, ChainContext, ChainResult> {
}
