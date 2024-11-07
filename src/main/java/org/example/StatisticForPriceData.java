package org.example;

/**
 * Represents statistical data for prices, including minimum, maximum, average, and standard deviation values.
 */
public class StatisticForPriceData {
    private double min;
    private double max;
    private double average;
    private double standardDeviation;

    /**
     * Constructs a StatisticForPriceData object with the given statistical values.
     *
     * @param min               the minimum price
     * @param max               the maximum price
     * @param average           the average price
     * @param standardDeviation the standard deviation of prices
     */
    public StatisticForPriceData(double min, double max, double average, double standardDeviation) {
        this.min = min;
        this.max = max;
        this.average = average;
        this.standardDeviation = standardDeviation;
    }

    /**
     * Returns a string representation of the statistical data with rounded values.
     *
     * @return a string representing the statistics for price data
     */
    @Override
    public String toString() {
        return "StatisticsDataByPrice{" +
                "min=" + (Math.round(min * 100) / 100.0) +
                ", max=" + (Math.round(max * 100) / 100.0) +
                ", average=" + (Math.round(average * 100) / 100.0) +
                ", standardDeviation=" + (Math.round(standardDeviation * 100) / 100.0) +
                '}';
    }
}

