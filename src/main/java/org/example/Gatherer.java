package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A utility class for gathering and filtering clothing data based on various criteria such as city,
 * production date, and cloth type.
 */
class Gatherer {

    /**
     * Gathers a list of clothing items, filtering out the first N items produced in a specified city.
     *
     * @param N         the number of items to filter out based on city name
     * @param totalObj  the total number of clothing items to generate and collect
     * @param cityName  the name of the city to filter items by
     * @return a list of clothing items that do not match the specified city filter
     */
    public static List<Clothes> gather(int N, int totalObj, String cityName) {
        int[] skipCount = {N};
        List<Clothes> filteredOut = new ArrayList<>();

        List<Clothes> filteredListByCity = Stream.generate(ClothesGenerator::generateClothes)
                .filter(clothes -> {
                    if (clothes.getCityName().equals(cityName) && skipCount[0] > 0) {
                        skipCount[0]--;
                        filteredOut.add(clothes);
                        return false;
                    }
                    return true;
                })
                .limit(totalObj)
                .collect(Collectors.toList());

        System.out.println("Objects filtered out:");
        filteredOut.forEach(System.out::println);

        return filteredListByCity;
    }

    /**
     * Filters a list of clothes based on the age of the items (in months), keeping only those
     * within the specified range of months.
     *
     * @param clothesList  the list of clothes to filter
     * @param minMonths    the minimum number of months since production
     * @param maxMonths    the maximum number of months since production
     * @return a list of clothes that fall within the specified age range
     */
    public static List<Clothes> filterByMonth(List<Clothes> clothesList, int minMonths, int maxMonths) {
        return clothesList.stream()
                .filter(clothes -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate productionDateFormat = LocalDate.parse(clothes.getProductionDate(), formatter);

                    LocalDate currentDate = LocalDate.now();

                    long monthsBetween = ChronoUnit.MONTHS.between(productionDateFormat, currentDate);
                    return monthsBetween >= minMonths && monthsBetween <= maxMonths;
                })
                .collect(Collectors.toList());
    }

    /**
     * Groups a list of clothes by their cloth type.
     *
     * @param clothesList  the list of clothes to group
     * @return a map where the key is the cloth type and the value is a list of clothes of that type
     */
    public static Map<String, List<Clothes>> filterByClothType(List<Clothes> clothesList) {
        return clothesList.stream()
                .collect(Collectors.groupingBy(Clothes::getClothType));
    }
}
