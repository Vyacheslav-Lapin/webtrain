package lab.controllers;

import lab.dao.CountryDao;
import lab.model.Country;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;

@RestController
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@RequestMapping("/api/hw/{hwid}")
public class HelloWorldController {

    CountryDao countryJpaDao;

    @GetMapping(value = "hello/{id}")
    public String hello(@PathVariable String hwid,
                        @PathVariable long id) {
        Country ru = countryJpaDao.getByCodeName("RU")
                .orElseThrow(() -> new RuntimeException("Нет такой страны"));
        return format("Мама мыла раму %d раз! В стране %s", id, ru);
    }

    @PostMapping("/hello")
    public String hello() {
        return "";
    }
}
