package com.hackathonNerds.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Booking")
public class Booking {

	@Id
    @Column(name = "id")
    private Integer id = null;

	@NotNull
    @Column(name = "nrbeds")
    private Integer nrBeds;
    
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospitalid")
    private Hospital hospital;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personid")
    private Person person;

    public Booking() {
    }

	public Booking(Integer id, @NotNull Integer nrBeds, Hospital hospital, Person person) {
		super();
		this.id = id;
		this.nrBeds = nrBeds;
		this.hospital = hospital;
		this.person = person;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNrBeds() {
		return nrBeds;
	}

	public void setNrBeds(Integer nrBeds) {
		this.nrBeds = nrBeds;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}
