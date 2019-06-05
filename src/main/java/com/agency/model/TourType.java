package com.agency.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class TourType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @NaturalId
    @Column(length = 60)
    private String tourTypeName;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "buyer",
            cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonIgnore
    private Set<Tour> tours;
}
