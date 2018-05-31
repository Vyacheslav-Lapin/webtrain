package com.epam.lab.java.web.jaxb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import javax.xml.bind.annotation.*;

import static lombok.AccessLevel.PRIVATE;

@Data
@XmlRootElement
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@FieldDefaults(level = PRIVATE)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"name", "price", "calories", "description"})
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