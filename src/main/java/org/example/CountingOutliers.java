package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A utility class for counting the number of outliers in a list of clothing items based on their price.
 */
public class CountingOutliers {

    /**
     * Counts the number of outliers in a list of clothes based on the interquartile range (IQR) method.
     *
     * @param list  the list of clothes to analyze
     * @return a list containing two values: the count of non-outliers and the count of outliers
     */
    public static List<Long> countOutliers(List<Clothes> list) {
        List<Double> prices = list.stream()
                .map(Clothes::getPrice)
                .sorted()
                .collect(Collectors.toList());

        List<Long> listResult = new ArrayList<>();

        double q1 = getPercentile(prices, 25);
        double q3 = getPercentile(prices, 75);
        double iqr = q3 - q1;

        double lowerBound = q1 - 1.5 * iqr;
        double upperBound = q3 + 1.5 * iqr;

        long outliersCount = prices.stream().filter(price -> price < lowerBound || price > upperBound).count();
        long dataCount = prices.size() - outliersCount;
        listResult.add(dataCount);
        listResult.add(outliersCount);

        return listResult;
    }

    /**
     * Calculates the specified percentile from a sorted list of values.
     *
     * @param sortedList  the sorted list of values
     * @param percentile  the percentile to calculate (0-100)
     * @return the value at the given percentile
     */
    private static double getPercentile(List<Double> sortedList, double percentile) {
        int index = (int) Math.ceil(percentile / 100.0 * sortedList.size());
        return sortedList.get(index - 1);
    }
}

