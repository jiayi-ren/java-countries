package local.jren.countrysearch;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/names")
public class NamesController {

    // http://localhost:2019/names/all
    @GetMapping(value = "/all", produces = {"application/json"})
    public ResponseEntity<?> getAllCountries() {
        CountrySearchApplication.myCountryList.countryList.sort((c1, c2) -> c1.getName().compareTo(c2.getName()));
        return new ResponseEntity<>(CountrySearchApplication.myCountryList.countryList, HttpStatus.OK);
    }

    // http://localhost:2019/names/start/u
    @GetMapping(value = "/start/{letter}", produces = {"application/json"})
    public ResponseEntity<?> getCountriesByLetter(@PathVariable char letter) {
        List<Country> countries = CountrySearchApplication.myCountryList.
                findCountries(c -> c.getName().toUpperCase().charAt(0) == Character.toUpperCase(letter));
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    // http://localhost:2019/names/size/20
    @GetMapping(value = "/size/{size}", produces = {"application/json"})
    public ResponseEntity<?> getCountriesByNameSize(@PathVariable long size) {
        List<Country> countries = CountrySearchApplication.myCountryList.
                findCountries(c -> c.getName().length() >= 20 );
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }
}
