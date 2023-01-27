package streams;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args){
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
    }
}
