package com.hackathonNerds.common;

public class HospitalResponse extends BaseResponse {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;

    public HospitalResponse() {
    }

    public HospitalResponse(Integer id, String name) {
        super();
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

}
