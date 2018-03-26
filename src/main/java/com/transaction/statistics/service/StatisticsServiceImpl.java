package com.transaction.statistics.service;

import com.transaction.statistics.domain.Transaction;
import com.transaction.statistics.domain.TransactionStatistic;
import com.transaction.statistics.storage.StatisticsStorageImpl;
import com.transaction.statistics.storage.StatisticsStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.transaction.statistics.domain.TransactionStatistic.ZERO;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private final StatisticsStorage<TransactionStatistic> transactions;

    @Autowired
    public StatisticsServiceImpl() {
        this(StatisticsStorageImpl.lastMinute(() -> ZERO));
    }

    StatisticsServiceImpl(StatisticsStorage<TransactionStatistic> transactions) {
        this.transactions = transactions;
    }

    @Override
    public void register(Transaction transaction) {
        transactions.update(transaction.getTimestamp(), statistic -> statistic.register(transaction.getAmount()));
    }

    @Override
    public TransactionStatistic getTransactionStatistics() {
        return transactions.reduce(TransactionStatistic::merge);
    }
}
