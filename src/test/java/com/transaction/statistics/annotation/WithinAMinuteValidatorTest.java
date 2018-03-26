package com.transaction.statistics.annotation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.validation.ConstraintValidatorContext;

import static java.time.temporal.ChronoUnit.SECONDS;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WithinAMinuteValidatorTest {

    @Mock
    private WithinAMinute annotation;

    @Mock
    private ConstraintValidatorContext context;

    private WithinAMinuteValidator validator;

    @Before
    public void setUp() throws Exception {
        WithinAMinuteValidator.NOW = System::currentTimeMillis;
        validator = new WithinAMinuteValidator();
        validator.initialize(annotation);
        when(annotation.duration()).thenReturn(1);
        when(annotation.unit()).thenReturn(SECONDS);
    }

    @Test
    public void shouldAcceptValidValue() throws Exception {
        boolean valid = validator.isValid(System.currentTimeMillis() - 500, context);
        assertThat(valid, is(true));
    }

    @Test
    public void shouldRejectInvalidValue() throws Exception {
        boolean valid = validator.isValid(System.currentTimeMillis() - 1500, context);
        assertThat(valid, is(false));
    }
}