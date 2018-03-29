package com.hackathonNerds.entity;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Hospital")
public class Hospital {
	
		@Id
	    @Column(name = "hospitalId")
	    private Integer id = null;

	    @NotNull
	    @Column(name = "name")
	    private String name;

	    @NotNull
	    @Column(name = "adress")
	    private String adress;
	    
	    @NotNull
	    @Column(name = "gpsCoord")
	    private String coord;
	    
	    @NotNull
	    @Column(name = "totalBeds")
	    private Integer totalBeds;
	    
	    @NotNull
	    @Column(name = "rezervedBeds")
	    private Integer rezervedBeds;
	    
	    @NotNull
	    @Column(name = "occupiedBeds")
	    private Integer occupiedBeds;
	    
	    @OneToMany(mappedBy = "id")
	    private List<Need> needList;
	    
	    public Hospital() {

	    }

	    public Hospital(Integer id, String name) {
	        this.id = id;
	        this.name = name;
	    }

	    public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

		public String getAdress() {
			return adress;
		}

		public void setAdress(String adress) {
			this.adress = adress;
		}

		public String getCoord() {
			return coord;
		}

		public void setCoord(String coord) {
			this.coord = coord;
		}

		public Integer getTotalBeds() {
			return totalBeds;
		}

		public void setTotalBeds(Integer totalBeds) {
			this.totalBeds = totalBeds;
		}

		public Integer getRezervedBeds() {
			return rezervedBeds;
		}

		public void setRezervedBeds(Integer rezervedBeds) {
			this.rezervedBeds = rezervedBeds;
		}

		public Integer getOccupiedBeds() {
			return occupiedBeds;
		}

		public void setOccupiedBeds(Integer occupiedBeds) {
			this.occupiedBeds = occupiedBeds;
		}
	    
}
