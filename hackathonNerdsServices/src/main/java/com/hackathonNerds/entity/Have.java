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
@Table(name = "Have")
public class Have {
	
	@Id
    @Column(name = "id")
    private Integer id = null;

	@NotNull
    @Column(name = "quantity")
    private Integer quatity;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospitalid")
    private Hospital hospital;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resourseid")
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
}
