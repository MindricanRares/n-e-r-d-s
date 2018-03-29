package com.hackathonNerds.entity;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.hackathonNerds.common.MeasurmentUnits;

@Entity
@Table(name = "Resource")
@NamedStoredProcedureQueries({

        @NamedStoredProcedureQuery(name = "in_only_test", procedureName = "test_pkg.in_only_test", parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "inParam1", type = String.class) }),

        @NamedStoredProcedureQuery(name = "in_and_out_test", procedureName = "test_pkg.in_and_out_test", parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "inParam1", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "outParam1", type = String.class) }) })
public class Resource {
	
	 	@Id
	    @Column(name = "id")
	    private Integer id = null;

	    @NotNull
	    @Column(name = "name")
	    private String name;
	    
	    @NotNull
	    @Column(name = "um")
	    private MeasurmentUnits measurmentunits;

	    @OneToMany(mappedBy = "id")
	    private List<Need> needList;
	    
	    @OneToMany(mappedBy = "id")
	    private List<Have> haveList;
	    
		public Resource() {

	    }

	    public Resource(Integer id, @NotNull String name, @NotNull MeasurmentUnits measurmentUnits, List<Need> needList,
				List<Have> haveList) {
			super();
			this.id = id;
			this.name = name;
			this.measurmentunits = measurmentUnits;
			this.needList = needList;
			this.haveList = haveList;
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

	    public MeasurmentUnits getMeasurmentUnits() {
			return measurmentunits;
		}

		public void setMeasurmentUnits(MeasurmentUnits measurmentUnits) {
			this.measurmentunits = measurmentUnits;
		}

		
		public List<Need> getNeedList() {
			return needList;
		}
		

		public void setNeedList(List<Need> needList) {
			this.needList = needList;
		}

		
		public List<Have> getHaveList() {
			return haveList;
		}

		
		public void setHaveList(List<Have> haveList) {
			this.haveList = haveList;
		}
}
