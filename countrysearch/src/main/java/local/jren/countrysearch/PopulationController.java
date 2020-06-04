package local.jren.countrysearch;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/population")
public class PopulationController {

    // http://localhost:2019/population/size/1000000000
    @GetMapping(value = "/size/{population}", produces = "application/json")
    public ResponseEntity<?> getCountriesByPopulation(@PathVariable long population) {
        List<Country> countries = CountrySearchApplication.myCountryList.
                findCountries(c -> c.getPopulation() >= population);
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    // http://localhost:2019/population/min
    @GetMapping(value = "/min", produces = {"application/json"})
    public ResponseEntity<?> getCountriesByMinPopulation() {
        // Get Min population
        List<Long> populations = new ArrayList<>();
        for ( Country c : CountrySearchApplication.myCountryList.countryList) {
            populations.add(c.getPopulation());
        }
        Long minPopu = populations.
                stream().min(Comparator.comparing(c -> c)).get();
        // find country with min population
        Country minPopulation = CountrySearchApplication.myCountryList.
                findCountry(c -> c.getPopulation() == minPopu);
        return new ResponseEntity<>(minPopulation, HttpStatus.OK);
    }

    // http://localhost:2019/population/max
    @GetMapping(value = "/max", produces = {"application/json"})
    public ResponseEntity<?> getCountriesByMaxPopulation() {
        // Get Max population
        List<Long> populations = new ArrayList<>();
        for ( Country c : CountrySearchApplication.myCountryList.countryList) {
            populations.add(c.getPopulation());
        }
        Long maxPopu = populations.
                stream().max(Comparator.comparing(c -> c)).get();
        // find country with max population
        Country maxPopulation = CountrySearchApplication.myCountryList.
                findCountry(c -> c.getPopulation() == maxPopu);
        return new ResponseEntity<>(maxPopulation, HttpStatus.OK);
    }
}
