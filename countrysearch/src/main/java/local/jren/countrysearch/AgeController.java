package local.jren.countrysearch;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
