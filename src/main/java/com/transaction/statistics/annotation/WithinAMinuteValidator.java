package com.transaction.statistics.annotation;


import javax.annotation.Nullable;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.Duration;
import java.util.function.Supplier;

public class WithinAMinuteValidator implements ConstraintValidator<WithinAMinute, Long> {

    public static Supplier<Long> NOW = System::currentTimeMillis;

    private WithinAMinute annotation;

    @Override
    public void initialize(WithinAMinute annotation) {
        this.annotation = annotation;
    }

    @Override
    public boolean isValid(@Nullable Long value, ConstraintValidatorContext context) {
        Duration age = Duration.of(annotation.duration(), annotation.unit());
        return value == null || NOW.get() - value <= age.toMillis();
    }
}
