/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.laerce.cinema.model;

import java.util.Objects;
import javax.persistence.*;


/**
 *
 * @author Gideon
 */
@Entity(name = "Role")
@Table
public class Role {
    @EmbeddedId
    private PlayPk id;
    @ManyToOne
    @MapsId("personPlay")
    private Personne person;
    @ManyToOne
    @MapsId("filmPlay")
    private Film film;
    @Column(name = "rank")
    private Integer rank;
    @Column(name = "name")
    private String name;

    public PlayPk getId() {
        return id;
    }

    public void setId(PlayPk id) {
        this.id = id;
    }

    public Personne getPerson() {
        return person;
    }

    public void setPerson(Personne person) {
        this.person = person;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film filmRole) {
        this.film = filmRole;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role(PlayPk id, Personne person, Film film) {
        this.id = id;
        this.person = person;
        this.film = film;
    }

 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return Objects.equals (id, role.id) &&
                Objects.equals (person, role.person) &&
                Objects.equals (film, role.film) &&
                Objects.equals (rank, role.rank) &&
                Objects.equals (name, role.name);
}

    @Override
    public int hashCode() {
return Objects.hash (id, person, film, rank, name);    }
    
    
}
