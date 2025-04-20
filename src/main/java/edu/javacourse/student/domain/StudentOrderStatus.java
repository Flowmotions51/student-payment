package edu.javacourse.student.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "jc_order_status")
public class StudentOrderStatus {
    @Id
    @Column(name = "status_id")
    private Long studentOrderStatusId;

    @Column(name = "status_name")
    private String statusName;

    public Long getStudentOrderStatusId() {
        return studentOrderStatusId;
    }

    public void setStudentOrderStatusId(Long studentOrderStatusId) {
        this.studentOrderStatusId = studentOrderStatusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
