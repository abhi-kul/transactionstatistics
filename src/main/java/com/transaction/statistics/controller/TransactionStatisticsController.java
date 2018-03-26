package com.transaction.statistics.controller;

import com.transaction.statistics.domain.TransactionStatistic;
import com.transaction.statistics.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api/statistics")
public class TransactionStatisticsController {
    @Autowired
    private final StatisticsService statisticsService;

    @Autowired
    public TransactionStatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping(produces = "application/json")
    public TransactionStatistic getTransactionStatistics() {
        return statisticsService.getTransactionStatistics();
    }
}

