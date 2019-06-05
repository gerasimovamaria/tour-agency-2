package com.agency.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tour_type_id")
    @Fetch(FetchMode.JOIN)
    private TourType tourType;

    @ManyToOne
    @JoinColumn(name = "route")
    @Fetch(FetchMode.JOIN)
    private Route places;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Fetch(FetchMode.JOIN)
    @JsonIgnore
    private User buyer;

    @Column
    private String name;

    @Column
    private Integer price;

    @Column
    private Boolean isHot;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "tour",
            cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonIgnore
    private List<Discount> discount;
}
