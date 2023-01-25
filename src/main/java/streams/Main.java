package streams;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args){
        List<String> firstNames = List.of("John", "Marie", "Bob", "Amanda", "Will", "James");
        List<String> lastNames = List.of("Cabbage", "Carrot", "Tomato", "Lettuce", "Potato");
        Random rand = new Random();
        Collection<String> fullNames = firstNames
                .stream()
                .map(item -> item + " "+ lastNames.get(rand.nextInt(lastNames.size()))).collect(Collectors.toList());

        System.out.println("Full names");
        fullNames.forEach(item -> System.out.println(item));

        Collection<String> filteredNames = fullNames
                .stream()
                .filter(item -> item.contains("Tomato"))
                .collect(Collectors.toList());

        System.out.println("\nFiltered names");
        filteredNames.forEach(item -> System.out.println(item));


        Map<String, List<String>> namesGroupedByLastName = fullNames
                .stream()
                .collect(
                        Collectors.groupingBy(element -> element.split(" ")[1])
                );

        namesGroupedByLastName.forEach((lastname, nameList) -> {
            System.out.println("\nLast name: "+ lastname+ "\n");
            nameList.forEach(name -> System.out.println(name));
        });
    }
}
