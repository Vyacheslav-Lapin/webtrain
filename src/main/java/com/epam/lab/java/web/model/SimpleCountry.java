package com.epam.lab.java.web.model;

import lombok.Value;

@Value
public class SimpleCountry implements Country {
    String name;
    String codeName;
}
