package org.java4web.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Specialty {
    private Long id;
    private String name;

    public Specialty(){
    }

    public Specialty(String name){
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "specialty", cascade = CascadeType.ALL)
    private Set<Doctor> doctors = new HashSet<>();


}
