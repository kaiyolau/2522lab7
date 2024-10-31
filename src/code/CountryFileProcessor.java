import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author kylelau
 * @version 1.0
 * CountryFileProcessor class processes a list of country names from a text file,
 * applies various filtering and mapping operations, and writes the results to an output file.
 *
 * <p>This program performs the following operations on the list of country names:
 * <ul>
 *     <li>Finds countries with names longer than 10 characters</li>
 *     <li>Finds countries with names shorter than 5 characters</li>
 *     <li>Finds countries starting with "A"</li>
 *     <li>Finds countries ending with "land"</li>
 *     <li>Finds countries containing "United"</li>
 *     <li>Sorts countries alphabetically and in reverse order</li>
 *     <li>Finds unique first letters of country names</li>
 *     <li>Counts the total number of countries</li>
 *     <li>Finds the longest and shortest country names</li>
 *     <li>Converts all country names to uppercase</li>
 *     <li>Finds countries with more than one word</li>
 *     <li>Maps each country name to its character count</li>
 *     <li>Checks if any country name starts with "Z"</li>
 *     <li>Checks if all country names are longer than 3 characters</li>
 * </ul>
 * </p>
 *
 * <p>The results are saved in "data.txt" inside the "matches" directory.</p>
 */


public class CountryFileProcessor {

    /**
     * The main method, which serves as the entry point for the program.
     *
     * <p>This method performs the following steps:</p>
     * <ol>
     *     <li>Checks if the "matches" directory exists; creates it if it doesn't.</li>
     *     <li>Reads all country names from "week8countries.txt" into a list.</li>
     *     <li>Processes the list of countries using Stream operations based on specified criteria.</li>
     *     <li>Writes the filtered and processed results to "data.txt" in the "matches" directory.</li>
     * </ol>
     *
     * @param args Command-line arguments (not used in this program).
     */
    public static void main(String[] args) {
        Path coutriesFile = Paths.get("week8countries.txt");
        Path matchesDir = Paths.get("matches");
        Path outputFile = matchesDir.resolve("data.txt");

       try {
           if (Files.notExists(matchesDir)) {
               Files.createDirectory(matchesDir);
           }

           List <String> countries = Files.readAllLines(coutriesFile);

           List<String> outputLines = new ArrayList<>();


           outputLines.add("\nCountry nasmes longer than 10 characters:");
           countries.stream()
                   .filter(country -> country.length() > 10)
                   .forEach(outputLines::add);

           countries.add("\nCountries names shorter than the length of 5:");
           countries.stream()
                   .filter(country -> country.length() > 5)
                   .forEach(outputLines::add);

           outputLines.add("\nCountry names starting with 'A':");
           countries.stream()
                   .filter(country -> country.startsWith("A"))
                   .forEach(outputLines::add);

           outputLines.add("\nCountry names ending with 'land':");
           countries.stream()
                   .filter(country -> country.endsWith("land"))
                   .forEach(outputLines::add);

           outputLines.add("\nCountry names containing 'United':");
           countries.stream()
                   .filter(country -> country.contains("United"))
                   .forEach(outputLines::add);

           outputLines.add("\nCountry names in alphabetical order:");
           countries.stream()
                   .sorted()
                   .forEach(outputLines::add);

           outputLines.add("\nCountry names in reverse alphabetical order:");
           countries.stream()
                   .sorted(Comparator.reverseOrder())
                   .forEach(outputLines::add);

           outputLines.add("\nUnique first letters of country names:");
           countries.stream()
                   .map(country -> country.substring(0, 1))
                   .distinct()
                   .forEach(outputLines::add);

           outputLines.add("\nTotal count of country names:");
           outputLines.add(String.valueOf(countries.size()));

           outputLines.add("\nLongest country name:");
           countries.stream()
                           .max(Comparator.comparingInt(String::length))
                                   .ifPresent(outputLines::add);

           outputLines.add("\nShortest country name:");
           countries.stream()
                   .min(Comparator.comparingInt(String::length))
                   .ifPresent(outputLines::add);

           outputLines.add("\nCountry names in uppercase:");
           countries.stream()
                   .map(String::toUpperCase)
                   .forEach(outputLines::add);

           outputLines.add("\nCountries with more than one word:");
           countries.stream()
                   .filter(country -> country.split(" ").length > 1)
                   .forEach(outputLines::add);

           outputLines.add("\nCountry names to character count:");
           countries.stream()
                   .forEach(country -> outputLines.add(country + ": " + country.length() + " characters"));

           outputLines.add("\nAny country name starts with 'Z':");
           outputLines.add(String.valueOf(countries.stream().anyMatch(country -> country.startsWith("Z"))));

           outputLines.add("\nAll country names longer than 3 characters:");
           outputLines.add(String.valueOf(countries.stream().allMatch(country -> country.length() > 3)));

           Files.write(outputFile, outputLines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

       } catch (IOException e) {
           System.err.println("Error processing file: " + e.getMessage());
       }


    }
}
