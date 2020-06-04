package local.jren.countrysearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CountrySearchApplication {

    public static CountryList myCountryList;

    public static void main(String[] args) {

        myCountryList = new CountryList();

        SpringApplication.run(CountrySearchApplication.class, args);
    }

}
