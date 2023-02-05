package streams;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> firstNames = List.of("John", "Marie", "Bob", "Amanda", "Will", "James");
        List<String> lastNames = List.of("Cabbage", "Carrot", "Tomato", "Lettuce", "Potato");
        Random rand = new Random();
        //## MAP
        Collection<String> fullNames = firstNames
                .stream()
                .map(item -> item + " "+ lastNames.get(rand.nextInt(lastNames.size()))).collect(Collectors.toList());

        System.out.println("Full names");
        fullNames.forEach(item -> System.out.println(item));

        //## FILTER
        Collection<String> filteredNames = fullNames
                .stream()
                .filter(item -> item.contains("Tomato"))
                .collect(Collectors.toList());

        System.out.println("\nFiltered names");
        filteredNames.forEach(item -> System.out.println(item));


        //## GROUPINGBY
        Map<String, List<String>> namesGroupedByLastName = fullNames
                .stream()
                .collect(
                        Collectors.groupingBy(element -> element.split(" ")[1])
                );

        namesGroupedByLastName.forEach((lastname, nameList) -> {
            System.out.println("\nLast name: "+ lastname+ "\n");
            nameList.forEach(name -> System.out.println(name));
        });

        //## REDUCE
        List<Integer> integers = List.of(1,2,3,4,5,6,7,8,9);
        int sum = integers.stream()
                .reduce(0, (accumulator, element) -> accumulator += element);
        System.out.println("Sum of numbers: "+ sum);


        //##REDUCE + COMBINER

        String resultString = integers.stream()
                        .reduce("",
                                (arg1, arg2) -> arg1.toString().concat(arg2.toString()),
                                (n1, n2) -> n1.concat(n2)
                        );
        System.out.println(resultString);


        integers.stream()
                .dropWhile((e) -> e < 4)
                .collect(Collectors.toList());


        integers.stream()
                .allMatch(e -> e == 2);


        //## SKIP AND LIMIT
        integers.stream()
                .skip(2)
                .limit(5)
                .forEach(e -> System.out.println(e));

        //## JOINING
        String joinedStringNumbers = integers.stream()
                .map(e -> String.valueOf(e))
                .collect(Collectors.joining(","));
        System.out.println(joinedStringNumbers);

        //## PEEK
        integers.stream()
                .peek(e ->  e.toString())
                .collect(Collectors.toList());

        //## FIND FIRST
        int foundInteger = integers.stream()
                .filter(e -> e > 6)
                .findFirst()
                .get();

        System.out.println("First integer found: "+foundInteger);

        //##FLATMAP
        List<List<Integer>> nestedIntegers = List.of(
                List.of(1),
                List.of(2),
                List.of(3),
                List.of(4)
        );

        List<Integer> listOfIntegers = nestedIntegers.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());


        long listOfIntegersSize = listOfIntegers.stream()
                .count();

        System.out.println("listOfIntegersSize: "+listOfIntegersSize);

        try {
            //## STREAM FROM FILE
            List<String> users = Files.lines(Paths.get("users.csv"))
                    .skip(1)
                    .map(line -> line.replace(",", " "))
                    .collect(Collectors.toList());
            users.forEach(element -> System.out.println(element));

        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }
}
