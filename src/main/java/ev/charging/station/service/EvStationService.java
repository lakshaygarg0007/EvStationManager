package ev.charging.station.service;

import ev.charging.station.Repository.EvStationRepository;
import ev.charging.station.models.EvStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.xml.ws.Response;
import java.util.List;
import java.util.Optional;

@Service
public class EvStationService {
    @Autowired
    private EvStationRepository evStationRepository;

    // Returns List Of All Stations in the city
    public List<EvStation> getListOfAllStations() {
       List<EvStation> listOfEvStations =  evStationRepository.findAll();
       return listOfEvStations;
    }

    // Returns Details of Particular Station Based on Station Id
    public EvStation getDetailsOfStation(Integer stationId) {
        Optional<EvStation> stationDetails =  evStationRepository.findById(stationId);
        return stationDetails.orElse(null);
    }

    // Add New Station to Station Records
    public void addNewStation(EvStation evStation) {
        evStationRepository.save(evStation);
    }

    // Edit a Particular Station Details
    public EvStation editStationDetails(Integer stationId, EvStation evStation) throws Exception {
        Optional<EvStation> existingEvStation = evStationRepository.findById(stationId);
        if (existingEvStation.isPresent()) {
            EvStation updatedEvStation = existingEvStation.get();
            updatedEvStation.setStationName(evStation.getStationName());
            updatedEvStation.setStationImages(evStation.getStationImages());
            updatedEvStation.setStationPricing(evStation.getStationPricing());
            updatedEvStation.setStationAddress(evStation.getStationAddress());
            return evStationRepository.save(updatedEvStation);
        } else {
            throw new Exception("EvStation with id: " + stationId + " not found");
        }
    }

    // Delete a Particular Station Details
    public void deleteEvStation(Integer stationId) {
        if(evStationRepository.findById(stationId).isPresent()) {
            evStationRepository.deleteById(stationId);
        } else {
            System.out.println("Ev station Does Not Exits");
        }
    }

    // Limited Number of Station Details
    public List<EvStation> getLimitedEvStations(int limit) {
        return evStationRepository.findLimitedEvStations(limit);
    }

    //Sort By Filters
    public List<EvStation> getSortedEvStations(String sort, String param) {
        Sort.Direction direction = ("asc").equalsIgnoreCase(sort) ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort1 = Sort.by(direction, param);
        return evStationRepository.findAll(sort1);
    }

}
