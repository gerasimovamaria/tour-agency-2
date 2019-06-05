package com.agency.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "email"
        })
})
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @NotBlank
    @Size(max = 40)
    private String name;

    @NaturalId
    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    @NotBlank
    @Size(max = 100)
    @JsonIgnore
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @JsonIgnore
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "buyer",
            cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonIgnore
    private Set<Tour> tours = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "user",
            cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonIgnore
    private List<Discount> discount;

    public User(@NotBlank @Size(max = 40) String name, @NotBlank @Size(max = 40) @Email String email, @NotBlank @Size(max = 100) String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
