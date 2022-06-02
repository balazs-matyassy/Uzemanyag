package hu.progmatic;

import hu.progmatic.model.FuelPriceChange;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            List<FuelPriceChange> fuelPriceChanges = loadFuelPriceChanges("uzemanyag.txt");

            System.out.print("3. feladat: ");
            System.out.println("Változások száma: " + fuelPriceChanges.size());

            System.out.print("4. feladat: ");
            int min = Integer.MAX_VALUE;

            for (FuelPriceChange fuelPriceChange : fuelPriceChanges) {
                if (fuelPriceChange.getPriceDifference() < min) {
                    min = fuelPriceChange.getPriceDifference();
                }
            }

            System.out.println("A legkisebb különbség: " + min);

            System.out.print("5. feladat: ");
            int counter = 0;

            for (FuelPriceChange fuelPriceChange : fuelPriceChanges) {
                if (fuelPriceChange.getPriceDifference() == min) {
                    counter++;
                }
            }

            System.out.println("A legkisebb különbség előfordulása: " + counter);

            System.out.print("6. feladat: ");
            boolean leapDayFound = false;

            for (FuelPriceChange fuelPriceChange : fuelPriceChanges) {
                if (fuelPriceChange.isLeapDay()) {
                    leapDayFound = true;
                    break;
                }
            }

            if (leapDayFound) {
                System.out.println("Volt változás szökőnapon!");
            } else {
                System.out.println("Nem volt változás szökőnapon!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<FuelPriceChange> loadFuelPriceChanges(String path) throws IOException {
        List<FuelPriceChange> fuelPriceChanges = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;

            while ((line = reader.readLine()) != null) {
                fuelPriceChanges.add(new FuelPriceChange(line));
            }
        }

        return fuelPriceChanges;
    }
}
