package com.rrybalkin;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Roman Rybalkin
 * 13.10.16
 */
public class MultipleSort {

    public static void main(String[] arg) {
        Person[] persons = {
                new Person("Jack", 1000), new Person("Bob", 2000), new Person("Sara", 1000), new Person("Billy", 2000),
                new Person("Martin", 1000), new Person("Alex", 2000), new Person("Tomas", 3000), new Person("Bady", 4000)
        };
        Arrays.sort(persons, new Comparator<Person>() {
            public int compare(final Person o1, final Person o2) {
                return o1.getName().compareTo(o2.getName());

            }
        });

        for (int i = 0; i < persons.length; i++) {
            System.out.println(persons[i].getName() + " " + persons[i].getSalary());
        }

        System.out.println(
                Stream.of(persons)
                .sorted((o1, o2) -> o1.getSalary() - o2.getSalary())
                        .sorted((o1, o2) -> o1.getName().compareTo(o2.getName()))
                .collect(Collectors.toList())
        );

        System.out.println(
                Stream.of(persons)
                        .sorted((p1,p2) -> !p1.getName().equals(p2.getName()) ? p1.getName().compareTo(p2.getName()) : p1.getSalary().compareTo(p2.getSalary()))
                        .collect(Collectors.toList())
        );

        System.out.println(Stream.of(persons).collect(Collectors.toMap(Person::getName, Function.<Person>identity())));
        System.out.println(Stream.of(persons).collect(Collectors.groupingBy(Person::getSalary)));

        System.out.println("--------------------------");

        System.out.println(Optional.empty().get());

        Optional.of(persons[0]).map(Person::getName).get();

        for (int i = 0; i < persons.length; i++) {
            System.out.println(persons[i].getSalary() + " " + persons[i].getName());
        }
    }
}

class Person {
    String name;
    int salary;

    public Person(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public Integer getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return name + " " + salary;
    }
}