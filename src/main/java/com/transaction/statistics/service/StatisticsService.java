package com.transaction.statistics.service;

import com.transaction.statistics.domain.Transaction;
import com.transaction.statistics.domain.TransactionStatistic;

public interface StatisticsService {
    void register(Transaction transaction);

    TransactionStatistic getTransactionStatistics();
}
