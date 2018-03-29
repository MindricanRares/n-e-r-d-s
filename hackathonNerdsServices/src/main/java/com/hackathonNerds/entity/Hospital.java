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
	    @Column(name = "id")
	    private Integer id = null;

	    @NotNull
	    @Column(name = "name")
	    private String name;

	    @NotNull
	    @Column(name = "adress")
	    private String adress;
	    
	    @NotNull
	    @Column(name = "gpscoord")
	    private String coord;
	    
	    @NotNull
	    @Column(name = "totalbeds")
	    private Integer totalBeds;
	    
	    @NotNull
	    @Column(name = "reservedbeds")
	    private Integer reservedbeds;
	    
	    @NotNull
	    @Column(name = "occupiedbeds")
	    private Integer occupiedbeds;
	    
	    @OneToMany(mappedBy = "id")
	    private List<Need> needlist;
	    
	    @OneToMany(mappedBy = "id")
	    private List<Have> haveList;
	    
	    @OneToMany(mappedBy = "id")
	    private List<Booking> bookinglist;
	    
	    public Hospital() {

	    }

	    public Hospital(Integer id, @NotNull String name, @NotNull String adress, @NotNull String coord,
				@NotNull Integer totalBeds, @NotNull Integer reservedbeds, @NotNull Integer occupiedbeds,
				List<Need> needList, List<Have> haveList, List<Booking> bookingList) {
			super();
			this.id = id;
			this.name = name;
			this.adress = adress;
			this.coord = coord;
			this.totalBeds = totalBeds;
			this.reservedbeds = reservedbeds;
			this.occupiedbeds = occupiedbeds;
			this.needlist = needList;
			this.haveList = haveList;
			this.bookinglist = bookingList;
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
			return reservedbeds;
		}

		public void setRezervedBeds(Integer rezervedBeds) {
			this.reservedbeds = rezervedBeds;
		}

		public Integer getOccupiedBeds() {
			return occupiedbeds;
		}

		public void setOccupiedBeds(Integer occupiedBeds) {
			this.occupiedbeds = occupiedBeds;
		}
	    
}
