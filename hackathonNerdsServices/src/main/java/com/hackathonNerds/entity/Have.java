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
public class Have {
	
	@Id
    @Column(name = "id")
    private Integer id = null;

	@NotNull
    @Column(name = "quantity")
    private Integer quatity;
	
	@NotNull
    @Column(name = "hospitalId")
    private Integer hospitalId;
    
	@NotNull
    @Column(name = "resourcelId")
    private Integer resourcelId;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id")
	private Hospital hospital;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id")
	private Resource resource;
	
	public Have() {

    }

	public Have(Integer id, @NotNull Integer quatity, @NotNull Integer hospitalId, @NotNull Integer resourcelId) {
		super();
		this.id = id;
		this.quatity = quatity;
		this.hospitalId = hospitalId;
		this.resourcelId = resourcelId;
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

	public Integer getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}

	public Integer getResourcelId() {
		return resourcelId;
	}

	public void setResourcelId(Integer resourcelId) {
		this.resourcelId = resourcelId;
	}
	
}
