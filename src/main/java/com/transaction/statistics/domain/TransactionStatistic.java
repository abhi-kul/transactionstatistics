package com.transaction.statistics.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Nullable;

public class TransactionStatistic {

    private long count;
    private double sum;
    private double max;
    private double min;
    private double avg;

    public TransactionStatistic() {
    }

    private TransactionStatistic(long count, double sum, double max, double min, double avg) {
        this.count = count;
        this.sum = sum;
        this.max = max;
        this.min = min;
        this.avg = avg;
    }

    public long getCount() {
        return count;
    }

    public double getSum() {
        return sum;
    }

    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }

    public double getAvg() {
        return avg;
    }

    @Override
    public boolean equals(@Nullable Object another) {
        return this == another || another instanceof TransactionStatistic && equalTo((TransactionStatistic) another);
    }

    private boolean equalTo(TransactionStatistic another) {
        return count == another.count
                && Double.doubleToLongBits(sum) == Double.doubleToLongBits(another.sum)
                && Double.doubleToLongBits(max) == Double.doubleToLongBits(another.max)
                && Double.doubleToLongBits(min) == Double.doubleToLongBits(another.min)
                && Double.doubleToLongBits(avg) == Double.doubleToLongBits(another.avg);
    }

    @Override
    public int hashCode() {
        int h = 31;
        h += (h << 5) + Long.hashCode(count);
        h += (h << 5) + Double.hashCode(sum);
        h += (h << 5) + Double.hashCode(max);
        h += (h << 5) + Double.hashCode(min);
        h += (h << 5) + Double.hashCode(avg);
        return h;
    }

    @Override
    public String toString() {
        return "ITransactionStatistic{"
                + "count=" + count
                + ", sum=" + sum
                + ", max=" + max
                + ", min=" + min
                + ", avg=" + avg
                + "}";
    }

    public static TransactionStatistic.Builder builder() {
        return new TransactionStatistic.Builder();
    }

    public static final TransactionStatistic ZERO = TransactionStatistic.builder()
            .count(0)
            .sum(0.0)
            .max(Double.NaN)
            .min(Double.NaN)
            .avg(Double.NaN)
            .build();

    public TransactionStatistic merge(TransactionStatistic that) {
        if (this.equals(ZERO)) {
            return that;
        }
        if (that.equals(ZERO)) {
            return this;
        }
        return TransactionStatistic.builder()
                .count(this.getCount() + that.getCount())
                .sum(this.getSum() + that.getSum())
                .min(Math.min(this.getMin(), that.getMin()))
                .max(Math.max(this.getMax(), that.getMax()))
                .avg((this.getAvg() + that.getAvg()) / 2)
                .build();
    }

    public TransactionStatistic register(double amount) {
        return this.equals(ZERO) ?
                TransactionStatistic.builder()
                        .count(1)
                        .sum(amount)
                        .min(amount)
                        .max(amount)
                        .avg(amount)
                        .build() :
                TransactionStatistic.builder()
                        .count(getCount() + 1)
                        .sum(getSum() + amount)
                        .min(Math.min(getMin(), amount))
                        .max(Math.max(getMax(), amount))
                        .avg(getAvg())
                        .build();
    }

    public static final class Builder {
        private long count;
        private double sum;
        private double max;
        private double min;
        private double avg;

        private Builder() {
        }

        public final TransactionStatistic.Builder count(long count) {
            this.count = count;
            return this;
        }

        public final TransactionStatistic.Builder sum(double sum) {
            this.sum = sum;
            return this;
        }

        public final TransactionStatistic.Builder max(double max) {
            this.max = max;
            return this;
        }

        @JsonProperty
        public final TransactionStatistic.Builder min(double min) {
            this.min = min;
            return this;
        }

        public final TransactionStatistic.Builder avg(double avg) {
            this.avg = avg;
            return this;
        }

        public TransactionStatistic build() {
            return new TransactionStatistic(count, sum, max, min, avg);
        }
    }
}
