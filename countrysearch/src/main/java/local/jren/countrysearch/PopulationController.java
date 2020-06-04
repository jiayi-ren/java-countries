package local.jren.countrysearch;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
