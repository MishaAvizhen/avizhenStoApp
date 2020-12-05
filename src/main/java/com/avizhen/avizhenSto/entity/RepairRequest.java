package com.avizhen.avizhenSto.entity;



import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "repair_request", schema = "avizhen_sto")
public class RepairRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE}, mappedBy = "repairRequest", fetch = FetchType.EAGER)
    private RepairRecord repairRecord;



    @NotNull
    @Column(name = "date_of_repair", nullable = false)
    private Date dateOfRepair;

    @NotNull
    @Column(name = "status", nullable = false)
    private String status;

    @NotNull
    @Column(name = "car_remark", nullable = true)
    private String carRemark;

    @NotNull
    @Column(name = "repair_description", nullable = true)
    private String repairDescription;

    public RepairRequest(){
    }

    public RepairRequest( Date dateOfRepair, String status, String carRemark, String repairDescription) {

        this.dateOfRepair = dateOfRepair;
        this.status = status;
        this.carRemark = carRemark;
        this.repairDescription = repairDescription;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateOfRepair() {
        return dateOfRepair;
    }

    public void setDateOfRepair(Date dateOfRepair) {
        this.dateOfRepair = dateOfRepair;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCarRemark() {
        return carRemark;
    }

    public void setCarRemark(String carRemark) {
        this.carRemark = carRemark;
    }

    public String getRepairDescription() {
        return repairDescription;
    }

    public void setRepairDescription(String repairDescription) {
        this.repairDescription = repairDescription;
    }

    public User getUser() {
        return user;
    }

    public RepairRequest setUser(User user) {
        this.user = user;
        return this;
    }

    public RepairRecord getRepairRecord() {
        return repairRecord;
    }

    public RepairRequest setRepairRecord(RepairRecord repairRecord) {
        this.repairRecord = repairRecord;
        return this;
    }

    @Override
    public String toString() {
        return "RepairRequestDao{" +
                "id=" + id +
                ", user=" + user +
                ", repairRecord=" + repairRecord +
                ", dateOfRepair=" + dateOfRepair +
                ", status='" + status + '\'' +
                ", carRemark='" + carRemark + '\'' +
                ", repairDescription='" + repairDescription + '\'' +
                '}';
    }

}
