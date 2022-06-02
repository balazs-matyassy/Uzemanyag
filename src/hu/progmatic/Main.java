package hu.progmatic;

import hu.progmatic.model.FuelPriceChange;

import java.io.*;
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

            // 7. feladat
            saveFuelPriceChanges(fuelPriceChanges, "euro.txt");

            // 8. feladat
            // Scanner pontosan ennyire jó megoldás
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int year;

            do {
                System.out.print("8. feladat: Kérem adja meg az évszámot [2011..2016]: ");
                year = Integer.parseInt(reader.readLine());
            } while (year < 2011 || year > 2016);

            System.out.print("10. feladat: ");

            FuelPriceChange lastChange = null;
            int max = Integer.MIN_VALUE;

            for (FuelPriceChange fuelPriceChange : fuelPriceChanges) {
                if (fuelPriceChange.getYear() == year) {
                    // csak második 2016-os adattól van értelme különbségről beszélni
                    if (lastChange != null) {
                        int days = FuelPriceChange.getDaysBetween(lastChange, fuelPriceChange);

                        if (days > max) {
                            max = days;
                        }
                    }

                    lastChange = fuelPriceChange;
                }
            }

            System.out.printf("%d évben a leghosszabb időszak %d nap volt.\n", year, max);
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

    private static void saveFuelPriceChanges(List<FuelPriceChange> fuelPriceChanges, String path)
            throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path))) {
            for (FuelPriceChange fuelPriceChange : fuelPriceChanges) {
                writer.printf("%s;%.2f;%.2f\n",
                        fuelPriceChange.getDate(),
                        fuelPriceChange.getGasPriceEUR(),
                        fuelPriceChange.getDieselPriceEUR());
            }
        }
    }
}
