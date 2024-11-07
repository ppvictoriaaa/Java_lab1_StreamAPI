package org.example;

import java.util.stream.IntStream;

import static org.example.Gatherer.filterByClothType;
import static org.example.Gatherer.filterByMonth;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
/**
 * Main class to demonstrate the use of the Clothes class and perform various operations such as
 * generating, filtering, grouping, and analyzing clothing data.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Get user input for total number of objects to generate.
        System.out.print("Enter total amount of objects: ");
        int totalObjects = scan.nextInt();

        // Get user input for the number of objects to skip by city.
        System.out.print("Enter N for skipping object by city: ");
        int skipCount = scan.nextInt();

        // Clear buffer after nextInt
        scan.nextLine();

        // Get user input for the city name.
        System.out.print("Enter City name: ");
        String inputCity = scan.nextLine();

        // Capitalize first letter of the city name.
        String cityName = inputCity.isEmpty() ? "" : inputCity.substring(0, 1).toUpperCase() + inputCity.substring(1).toLowerCase();
        System.out.println("cityName = " + cityName);

        // Generate the list of Clothes objects using the Gatherer class based on user input.
        List<Clothes> clothesList = Gatherer.gather(skipCount, totalObjects, cityName);

        // Print all generated Clothes objects.
        System.out.println("\nA list of objects of the Clothes class created using Stream API:");
        IntStream.range(0, clothesList.size())
                .forEach((i -> System.out.println((i + 1) + ") " + clothesList.get(i))));

        // Get user input for filtering by the range of months since production.
        System.out.print("\nEnter value for min month: ");
        int minMonths = scan.nextInt();
        System.out.print("Enter value for max month: ");
        int maxMonths = scan.nextInt();

        // Filter the clothes list by the specified month range.
        List<Clothes> groupedByMonth = filterByMonth(clothesList, minMonths, maxMonths);

        // Print the filtered Clothes objects by month range.
        System.out.println("\n\nFiltered from " + minMonths + " to " + maxMonths + " month from now:");
        IntStream.range(0, groupedByMonth.size())
                .forEach((i -> System.out.println((i + 1) + ") " + groupedByMonth.get(i))));

        // Group the filtered list of clothes by their cloth type.
        Map<String, List<Clothes>> groupedByClothType = filterByClothType(groupedByMonth);

        // Print the Clothes objects grouped by cloth type.
        System.out.println("\nFiltered by Cloth Type");
        groupedByClothType.forEach((clothType, clothes) -> {
            System.out.println("\nCloth Type: " + clothType);
            clothes.forEach(System.out::println);
        });

        // Calculate and display statistics (min, max, average, standard deviation) of the price field.
        System.out.println("\nStatistics for all objects by the PRICE field:");
        StatisticForPriceData statisticPrices = clothesList.stream()
                .map(Clothes::getPrice)
                .collect(StatisticCollector.toStatisticsData());
        System.out.println(statisticPrices);

        // Count and display outliers in the prices of the Clothes objects.
        List<Long> listResult = CountingOutliers.countOutliers(clothesList);
        long dataCount = listResult.get(0);
        long outliersCount = listResult.get(1);

        System.out.println("\nOutliers in the resulting list:");
        System.out.println("{\"data\": " + dataCount + ", \"outliers\": " + outliersCount + "}");
    }
}

