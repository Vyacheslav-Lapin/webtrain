package com.epam.lab.java.web.model;

import lombok.Value;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Value
public class SimpleContact implements Contact {
    String type;
    String value;
}
