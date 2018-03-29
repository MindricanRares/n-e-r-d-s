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
@Table(name = "Need")
public class Need {

    @Id
    @Column(name = "id")
    private Integer id = null;

    @NotNull
    @Column(name = "quantity")
    private Integer quatity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospitalId")
    private Hospital hospital;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resourseId")
    private Resource resource;

    public Need() {

    }

    public Need(Integer id, @NotNull Integer quatity, @NotNull Integer hospitalId, @NotNull Integer resourcelId) {
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

}
