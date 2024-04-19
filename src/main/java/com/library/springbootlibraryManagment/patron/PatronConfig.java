package com.library.springbootlibraryManagment.patron;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*@Configuration
public class PatronConfig {

    @Bean
    CommandLineRunner commandLineRunner(PatronRepository patronRepository) {
        return args -> {
            Patron usseif = new Patron(
                    "Usseif",
                    "usseif@gmail.com",
                    "Madinet Nasr - Egypt",
                    "+201116135358",
                    LocalDate.of(1997, Month.JANUARY, 5));

            Patron eslam = new Patron(
                    "Eslam",
                    "eslam@gmail.com",
                    "Maadi - Egypt",
                    "+201003675965",
                    LocalDate.of(2000, Month.JANUARY, 5));

            patronRepository.saveAll(List.of(usseif, eslam));
        };
    }

}*/
