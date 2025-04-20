package edu.javacourse.student.domain;

import javax.persistence.*;

@Entity
@Table(name = "jc_country_struct")
public class CountryArea {

    @Id
    @Column(name = "area_id")
    private Long areaId;

    @Column(name = "area_name")
    private String areaName;

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
