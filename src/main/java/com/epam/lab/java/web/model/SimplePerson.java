package com.epam.lab.java.web.model;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
public class SimplePerson implements Person {
    String firstName;
    String lastName;
    Country country;
    int age;
    float height;
    boolean isProgrammer;
    boolean broke;
    @Singular
    List<Contact> contacts;
}
