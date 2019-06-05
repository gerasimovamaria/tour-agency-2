package com.agency.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(of = "id")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "deptPlace")
    private String deptPlace;

    @Column(name = "destPlace")
    private String destPlace;

    @OneToMany(mappedBy = "places",
            cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH}
    )
    @JsonIgnore
    private Set<Tour> places = new HashSet<>();
}
