package com.transaction.statistics.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.transaction.statistics.domain.Transaction;
import com.transaction.statistics.exception.InvalidTimestampException;
import com.transaction.statistics.service.StatisticsService;
import com.transaction.statistics.annotation.WithinAMinuteValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TransactionsController.class)
public class TransactionsControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StatisticsService statisticsService;

    @Before
    public void setUp() throws Exception {
        WithinAMinuteValidator.NOW = () -> 1478192204000L;
    }

    @Test
    public void shouldAcceptValidRequest() throws Exception {
        Transaction transaction = Transaction.builder()
                .amount(12.3)
                .timestamp(1478192204000L)
                .build();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(transaction);

        mvc.perform(post("/transactions")
                .contentType("application/json")
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(content().bytes(new byte[0]));

        verify(statisticsService).register(transaction);
    }

    @Test
    public void shouldHandleInvalidTimestampException() throws Exception {
        Mockito.doThrow(new InvalidTimestampException(""))
                .when(statisticsService).register(any(Transaction.class));

        Transaction transaction = Transaction.builder()
                .amount(12.3)
                .timestamp(1478192204000L)
                .build();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(transaction);

        mvc.perform(post("/transactions")
                .contentType("application/json")
                .content(json))
                .andExpect(status().isNoContent())
                .andExpect(content().bytes(new byte[0]));
    }

}
