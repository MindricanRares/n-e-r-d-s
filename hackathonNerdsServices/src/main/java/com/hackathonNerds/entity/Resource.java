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
	    @Column(name = "resourseId")
	    private Integer id = null;

	    @NotNull
	    @Column(name = "name")
	    private String name;
	    
	    @NotNull
	    @Column(name = "um")
	    private MeasurmentUnits measurmentUnits;

	    @OneToMany(mappedBy = "id")
	    private List<Need> needList;
	    
		public Resource() {

	    }

	    public Resource(Integer id, String name, MeasurmentUnits measurementUnits) {
	        this.id = id;
	        this.name = name;
	        this.measurmentUnits = measurementUnits;
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
			return measurmentUnits;
		}

		public void setMeasurmentUnits(MeasurmentUnits measurmentUnits) {
			this.measurmentUnits = measurmentUnits;
		}
}
