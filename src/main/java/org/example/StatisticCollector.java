package org.example;

import java.util.DoubleSummaryStatistics;

import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * A utility class for collecting and calculating statistical data (min, max, average, and standard deviation)
 * from a stream of price values.
 */
public class StatisticCollector {
    /**
     * Returns a collector that collects price data and calculates statistical information
     * (min, max, average, and standard deviation).
     *
     * @return a Collector that produces a StatisticForPriceData object
     */
    public static Collector<Double, ?, StatisticForPriceData> toStatisticsData() {
        return Collectors.collectingAndThen(
                Collectors.toList(),
                list -> {
                    DoubleSummaryStatistics stats = list.stream()
                            .mapToDouble(Double::doubleValue)
                            .summaryStatistics();

                    double average = stats.getAverage();

                    double standardDeviation = Math.sqrt(list.stream()
                            .mapToDouble(price -> Math.pow(price - average, 2))
                            .average()
                            .orElse(0.0));

                    return new StatisticForPriceData(stats.getMin(), stats.getMax(), average, standardDeviation);
                }
        );
    }
}