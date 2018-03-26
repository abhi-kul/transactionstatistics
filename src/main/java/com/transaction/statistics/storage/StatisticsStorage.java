package com.transaction.statistics.storage;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public interface StatisticsStorage<TransactionStatistic> {
    void update(long timestamp, UnaryOperator<TransactionStatistic> updater);

    TransactionStatistic reduce(BinaryOperator<TransactionStatistic> reducer);
}
