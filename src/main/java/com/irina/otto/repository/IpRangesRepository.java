package com.irina.otto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.irina.otto.model.IpRange;

@Repository
public interface IpRangesRepository extends JpaRepository<IpRange, Long> {
	
    @Query(value = "SELECT * from iprange where region LIKE :region%", nativeQuery = true)
    List<IpRange> getByRegion(@Param("region") String region);

}

