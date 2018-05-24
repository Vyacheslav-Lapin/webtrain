package com.epam.lab.java.web.jaxb;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import javax.xml.bind.annotation.*;

import java.util.Objects;

import static lombok.AccessLevel.PRIVATE;

@Data
@Accessors(chain = true)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Food", propOrder = { "name", "price", "calories", "description"})
@FieldDefaults(level = PRIVATE)
//@NoArgsConstructor
public class Food {
    @XmlAttribute(required = true)
    int id;
    @XmlElement(required = true)
    String name;
    @XmlElement(required = true)
    String price;
    @XmlElement(required = true)
    String description;
    @XmlElement(required = true)
    int calories;
}