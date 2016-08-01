package com.learning;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by maruthi on 31/07/16.
 * https://www.hackerrank.com/challenges/journey-to-the-moon
 */
public class JourneyMoon {

    static private class Country {
        int id;

        @Override
        public String toString() {
            return "Country{" +
                    "id=" + id +
                    ", astronauts=" + astronauts +
                    '}';
        }

        Set<Astro> astronauts = new HashSet<>();
    }

    static private class Astro {
        @Override
        public String toString() {
            return "Astro{" +
                    "id=" + id +
                    '}';
        }

        Country country;
        int id;
    }

    static Map<Integer, Astro> astroMap = new HashMap<>();
    static Map<Country, Country> countryMap = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = bfr.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int I = Integer.parseInt(temp[1]);

        for (int i = 0; i < I; i++) {
            Country country = new Country();
            country.id = i;
            countryMap.put(country, country);

            temp = bfr.readLine().split(" ");
            int a = Integer.parseInt(temp[0]);
            int b = Integer.parseInt(temp[1]);
            if (astroMap.get(a) == null && astroMap.get(b) == null) {
                Astro astroa = new Astro();
                astroa.id = a;
                astroa.country = country;
                astroMap.put(a, astroa);

                Astro astrob = new Astro();
                astrob.id = b;
                astrob.country = country;
                astroMap.put(b, astrob);

                country.astronauts.add(astroa);
                country.astronauts.add(astrob);

            } else if (astroMap.get(a) != null && astroMap.get(b) != null) {
                Astro astrob = astroMap.get(b);
                Astro astroa = astroMap.get(a);

                Country cb = astrob.country;
                Country ca = astroa.country;

                if (cb == ca) {
                    continue;
                }

                Set<Astro> allAstros = cb.astronauts;
                for (Astro asb : allAstros) {
                    ca.astronauts.add(asb);
                    asb.country = ca;
                }
                cb.astronauts.clear();
            } else if (astroMap.get(a) == null) {
                Astro astroa = new Astro();
                astroa.id = a;
                Astro astrob = astroMap.get(b);
                astroa.country = astrob.country;
                astroa.country.astronauts.add(astroa);
                astroMap.put(a, astroa);

                //astroMap.get(b).country.astronauts.add(astroa);

            } else if (astroMap.get(b) == null) {
                Astro astrob = new Astro();
                astrob.id = b;
                astrob.country = astroMap.get(a).country;
                astrob.country.astronauts.add(astrob);

                astroMap.put(b, astrob);

                //astroMap.get(a).country.astronauts.add(astrob);
            }
        }

        long combinations = 0;

        //List<Integer> countrySizes = new ArrayList<>();
        int[] countrySizes = new int[N];
        int sizeIndex = 0;
        int total = 0;

        Set<Country> countries = countryMap.keySet();
        for (Country country : countries) {
            int size = country.astronauts.size();
            if (size > 0) {
                countrySizes[sizeIndex++] = size;
                total += size;
            }
        }

        for (int i = 0; i < N; i++) {
            if (astroMap.get(i) == null) {
                countrySizes[sizeIndex++] = 1;
                total += 1;
            }
        }

        // Compute the final answer - the number of combinations
        for (int i = 0; i < sizeIndex - 1; i++) {
            total -= countrySizes[i];
            combinations += countrySizes[i] * total;
        }

        System.out.println(combinations);

    }
}
