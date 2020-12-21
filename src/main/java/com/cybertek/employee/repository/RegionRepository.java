package com.cybertek.employee.repository;

import com.cybertek.employee.entities.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {

    List<Region> findDistinctByCountry(String country); //1

    List<Region> findByCountryOrRegion(String country, String region); //2

    List<Region> findByCountryContaining(String country); //4

    List<Region> findByCountryContainingOrderByCountry(String country); //6

    List<Region> findTop3ByCountry(String country); //7
}
