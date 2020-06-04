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

@RestController
@RequestMapping("/age")
public class AgeController {

    // http://localhost:2019/age/age/25
    @GetMapping(value = "/age/{median}", produces = {"application/json"})
    public ResponseEntity<?> getCountriesByAge(@PathVariable long median) {
        List<Country> countries = CountrySearchApplication.myCountryList.
                findCountries(c -> c.getMedianAge() >= median);
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    // http://localhost:2019/age/min
    @GetMapping(value = "/min", produces = {"application/json"})
    public ResponseEntity<?> getCountryByMinAge() {
        // Get Min Median Age
        List<Long> ages = new ArrayList<>();
        for ( Country c : CountrySearchApplication.myCountryList.countryList) {
            ages.add(c.getMedianAge());
        }
        Long minAge = ages.
                stream().min(Comparator.comparing(c -> c)).get();
        // find country with min median age
        Country match = CountrySearchApplication.myCountryList.
                findCountry(c -> c.getMedianAge() == minAge);
        return new ResponseEntity<>(match, HttpStatus.OK);
    }

    // http://localhost:2019/age/max
    @GetMapping(value = "/max", produces = {"application/json"})
    public ResponseEntity<?> getCountryByMaxAge() {
        // Get Max Median Age
        List<Long> ages = new ArrayList<>();
        for ( Country c : CountrySearchApplication.myCountryList.countryList) {
            ages.add(c.getMedianAge());
        }
        Long maxAge = ages.
                stream().max(Comparator.comparing(c -> c)).get();
        // find country with max median age
        Country match = CountrySearchApplication.myCountryList.
                findCountry(c -> c.getMedianAge() == maxAge);
        return new ResponseEntity<>(match, HttpStatus.OK);
    }

    // http://localhost:2019/age/median
    @GetMapping(value = "/median", produces = {"application/json"})
    public ResponseEntity<?> getCountryByMedianAge() {
        // sort
        CountrySearchApplication.myCountryList.countryList.sort((c1, c2) ->(int) (c1.getMedianAge()-c2.getMedianAge()));
        // find country with median age by index
        int listSize = CountrySearchApplication.myCountryList.countryList.size();
        Country match = CountrySearchApplication.myCountryList.countryList.get(listSize/2+1);

        return new ResponseEntity<>(match, HttpStatus.OK);
    }
}
