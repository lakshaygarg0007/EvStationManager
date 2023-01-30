package ev.charging.station.Repository;

import ev.charging.station.models.EvStation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvStationRepository extends JpaRepository<EvStation, Integer> {
    @Query(value = "SELECT * FROM ev_station LIMIT :limit", nativeQuery = true)
    List<EvStation> findLimitedEvStations(@Param("limit") int limit);
}


