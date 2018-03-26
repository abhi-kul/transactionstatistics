package com.transaction.statistics.domain;

import com.transaction.statistics.annotation.WithinAMinute;

import javax.annotation.Nullable;
import javax.validation.constraints.Min;

import static java.time.temporal.ChronoUnit.SECONDS;

public class Transaction {

    private double amount;
    private long timestamp;

    public Transaction() {
    }

    private Transaction(double amount, long timestamp) {
        this.amount = amount;
        this.timestamp = timestamp;
    }

    @Min(0)
    public double getAmount() {
        return amount;
    }

    @WithinAMinute(duration = 60, unit = SECONDS)
    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(@Nullable Object another) {
        return this == another || another instanceof Transaction && equalTo((Transaction) another);
    }

    private boolean equalTo(Transaction another) {
        return Double.doubleToLongBits(amount) == Double.doubleToLongBits(another.amount)
                && timestamp == another.timestamp;
    }

    @Override
    public int hashCode() {
        int h = 31;
        h += (h << 5) + Double.hashCode(amount);
        h += (h << 5) + Long.hashCode(timestamp);
        return h;
    }

    @Override
    public String toString() {
        return "ITransaction{"
                + "amount=" + amount
                + ", timestamp=" + timestamp
                + "}";
    }

    public static Transaction.Builder builder() {
        return new Transaction.Builder();
    }

    public static final class Builder {
        private double amount;
        private long timestamp;

        private Builder() {
        }

        public final Builder amount(double amount) {
            this.amount = amount;
            return this;
        }

        public final Builder timestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Transaction build() {
            return new Transaction(amount, timestamp);
        }
    }
}
