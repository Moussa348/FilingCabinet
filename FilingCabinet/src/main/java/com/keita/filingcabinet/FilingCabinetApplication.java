package com.keita.filingcabinet;

import com.keita.filingcabinet.meta.ExcludeFromGeneratedCoverage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FilingCabinetApplication {

    @ExcludeFromGeneratedCoverage
    public static void main(String[] args) {
        SpringApplication.run(FilingCabinetApplication.class, args);
    }

}
