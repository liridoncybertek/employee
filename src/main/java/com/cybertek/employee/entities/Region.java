package com.cybertek.employee.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "regions")
@Getter
@Setter
@NoArgsConstructor
@ToString
@NamedQuery(name = "Region.findAllRegions", query = "SELECT r FROM Region r")
public class Region extends BaseEntity<Integer> {

    private String region;

    private String country;
}
