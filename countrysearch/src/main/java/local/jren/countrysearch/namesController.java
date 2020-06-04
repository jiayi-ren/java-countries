package local.jren.countrysearch;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/names")
public class namesController {

    // http://localhost:2019/names/all
    @GetMapping(value = "/all",produces = {"application/json"})
    public ResponseEntity<?> getAllCountries() {
        CountrySearchApplication.myCountryList.countryList.sort((c1, c2) -> c1.getName().compareTo(c2.getName()));
        return new ResponseEntity<>(CountrySearchApplication.myCountryList.countryList, HttpStatus.OK);
    }

}
