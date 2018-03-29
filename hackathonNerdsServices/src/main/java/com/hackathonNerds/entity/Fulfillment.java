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
@Table(name = "fulfillment")
public class Fulfillment {

	@Id
    @Column(name = "id")
    private Integer id = null;

	@NotNull
    @Column(name = "quantity")
    private Integer quantity;
	
	@NotNull
	@Column(name = "fulfilled")
	private boolean fulfilled;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "needid")
    private Need need;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personid")
    private Person person;

    public Fulfillment() {
    	
    }

	public Fulfillment(Integer id, @NotNull Integer quantity, @NotNull boolean fulfilled, Need need, Person person) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.fulfilled = fulfilled;
		this.need = need;
		this.person = person;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setNrBeds(Integer quantity) {
		this.quantity = quantity;
	}

	public boolean isFulfilled() {
		return fulfilled;
	}

	public void setFulfilled(boolean fulfilled) {
		this.fulfilled = fulfilled;
	}

	public Need getNeed() {
		return need;
	}

	public void setNeed(Need need) {
		this.need = need;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}
