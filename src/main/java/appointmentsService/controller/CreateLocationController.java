package appointmentsService.controller;


import appointmentsService.model.Location;
import appointmentsService.repository.LocationRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/createLocation")
public class CreateLocationController {

    private LocationRepository locationRepository;

    public CreateLocationController(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @PostMapping("")
    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }
}
