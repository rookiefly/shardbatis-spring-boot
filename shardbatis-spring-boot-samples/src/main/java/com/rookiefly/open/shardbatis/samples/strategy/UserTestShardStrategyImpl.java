package com.rookiefly.open.shardbatis.samples.strategy;

import com.rookiefly.open.shardbatis.strategy.ShardStrategy;

public class UserTestShardStrategyImpl implements ShardStrategy {

    @Override
    public String getTargetTableName(String baseTableName, Object params,
                                     String mapperId) {
        if (params != null && (int) params < 180) {
            return baseTableName + "_0";
        }
        return baseTableName + "_1";
    }

}
