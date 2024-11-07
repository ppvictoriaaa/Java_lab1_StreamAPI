package org.example;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

/**
 * A utility class for generating random clothing items.
 * It randomly selects a name, cloth type, city, production date, and price
 * to create and return a new {@link Clothes} object.
 */
public class ClothesGenerator {
    private static final List<String> NAMES = List.of("t-shirt", "sweater", "jeans", "jacket", "coat", "shirt", "dress", "skirt", "shorts", "suit");
    private static final List<String> CLOTH_TYPES = List.of("cotton", "linen", "silk", "wool", "denim", "leather", "polyester", "velvet", "chiffon", "satin");
    private static final List<String> CITIES = List.of("Kyiv", "Lviv", "Odesa", "Kharkiv", "Dnipro", "Zaporizhzhia", "Vinnytsia", "Mykolaiv", "Kherson", "Chernivtsi");
    private static final Random RANDOM = new Random();

    /**
     * Generates a random {@link Clothes} object with random values for name, cloth type, city,
     * production date, and price.
     *
     * @return a randomly generated {@link Clothes} object
     */
    public static Clothes generateClothes() {
        String name = NAMES.get(RANDOM.nextInt(NAMES.size()));
        String clothType = CLOTH_TYPES.get(RANDOM.nextInt(CLOTH_TYPES.size()));
        String cityName = CITIES.get(RANDOM.nextInt(CITIES.size()));
        LocalDate productionDate = LocalDate.now().minusDays(RANDOM.nextInt(365 * 3));
        double price;
        if (RANDOM.nextDouble() < 0.05) {
            price = 3000 + RANDOM.nextDouble(6000); // to increase the amount of outliers
        } else {
            price = 100 + RANDOM.nextDouble(900);
        }
        price = Math.round(price * 100) / 100.0;

        return new Clothes(name, clothType, cityName, productionDate, price);
    }
}

