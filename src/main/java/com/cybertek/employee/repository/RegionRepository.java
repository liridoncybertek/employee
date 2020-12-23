package com.cybertek.employee.repository;

import com.cybertek.employee.entities.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {

    // List all distinct regions by country?
    List<Region> findDistinctByCountry(String country);

    // List all regions with a specific country or region?
    List<Region> findByCountryOrRegion(String country, String region);

    // Find all regions contains a specific country?
    List<Region> findByCountryContaining(String country);

    // Find all regions contains a specific country in order?
    List<Region> findByCountryContainingOrderByCountry(String country);

    // Find first 3 regions with a specific country?
    List<Region> findTop3ByCountry(String country);


    //NATIVE QUERIES....
    @Query(value = "SELECT DISTINCT country FROM regions", nativeQuery = true)
    List<Region> distinctByCountry();

    @Query(value = "SELECT * FROM regions ORDER BY region LIMIT 3", nativeQuery = true)
    List<Region> top3RegionsOrderByRegion();

    @Query(value = "SELECT * FROM regions WHERE id = :id", nativeQuery = true)
    Region fetchById(@Param("id") Integer id);

    @Query(value = "SELECT * FROM regions WHERE region ILIKE concat('%', :value, '%') OR country ILIKE concat('%', :value, '%')", nativeQuery = true)
    List<Region> fetchAllByCountryOrRegion(@Param("value") String value);


}
