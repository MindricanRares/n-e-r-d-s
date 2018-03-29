package com.hackathonNerds.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Have")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler" })
public class Have implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private Integer id = null;

    @NotNull
    @Column(name = "quantity")
    private Integer quatity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospitalid")
    private Hospital hospital;

    @ManyToOne // (fetch = FetchType.LAZY)
    @JoinColumn(name = "resourceid")
    private Resource resource;

    public Have() {

    }

    public Have(Integer id, @NotNull Integer quatity) {
        super();
        this.id = id;
        this.quatity = quatity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuatity() {
        return quatity;
    }

    public void setQuatity(Integer quatity) {
        this.quatity = quatity;
    }

    @JsonIgnore
    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

}
