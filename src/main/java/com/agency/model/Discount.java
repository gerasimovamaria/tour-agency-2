package com.agency.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Fetch(FetchMode.JOIN)
    private User user;

    @ManyToOne
    @JoinColumn(name = "tour_id")
    @Fetch(FetchMode.JOIN)
    private Tour tour;

    private Boolean isPercent;

    private Integer amount;
}
