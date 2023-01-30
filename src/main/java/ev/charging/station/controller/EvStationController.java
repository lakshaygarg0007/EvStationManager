package ev.charging.station.controller;

import ev.charging.station.models.EvStation;
import ev.charging.station.service.EvStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EvStationController {

    @Autowired
    private EvStationService evStationService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<EvStation> getListOfAllStations(
            @RequestParam(required = false, value = "limit") Integer limit,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "param") String param) throws Exception {
       if(limit != null) {
           return evStationService.getLimitedEvStations(limit);
       } else if(sort != null || param != null) {
           return evStationService.getSortedEvStations(sort, param);
       } else {
           return evStationService.getListOfAllStations();
       }
    }

    @GetMapping("/show/{id}")
    public EvStation getDetailsOfStation(@PathVariable Integer id){
        return evStationService.getDetailsOfStation(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void addNewStation(@RequestBody EvStation evStation){
        evStationService.addNewStation(evStation);
    }

    @PutMapping("/{id}/edit")
    public EvStation editStationDetails(@PathVariable Integer id, @RequestBody EvStation evStation) throws Exception {
        return evStationService.editStationDetails(id, evStation);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEvStation(@PathVariable Integer id) {
        evStationService.deleteEvStation(id);
    }
}
