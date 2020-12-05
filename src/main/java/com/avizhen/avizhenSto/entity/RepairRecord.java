package com.avizhen.avizhenSto.entity;


import com.sun.istack.internal.NotNull;

import javax.persistence.*;



@Entity
@Table(name = "repair_record", schema = "avizhen_sto")
public class RepairRecord {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;


    public RepairRequest getRepairRequest() {
        return repairRequest;
    }

    public void setRepairRequest(RepairRequest repairRequest) {
        this.repairRequest = repairRequest;
    }

    @OneToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "repair_request_id")
    private RepairRequest repairRequest;

    @NotNull
    @Column(name = "repair_description", nullable = false)
    private String repairDescription;

    @NotNull
    @Column(name = "detail_price", nullable = true)
    private String detailPrice;

    @NotNull
    @Column(name = "work_price", nullable = true)
    private String workPrice;

    @NotNull
    @Column(name = "other_notes", nullable = true)
    private String otherNotes;


    public RepairRecord(){
    }


    public RepairRecord( String repairDescription, String detailPrice, String workPrice, String otherNotes) {

        this.repairDescription = repairDescription;
        this.detailPrice = detailPrice;
        this.workPrice = workPrice;
        this.otherNotes = otherNotes;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRepairDescription() {
        return repairDescription;
    }

    public void setRepairDescription(String repairDescription) {
        this.repairDescription = repairDescription;
    }

    public String getDetailPrice() {
        return detailPrice;
    }

    public void setDetailPrice(String detailPrice) {
        this.detailPrice = detailPrice;
    }

    public String getWorkPrice() {
        return workPrice;
    }

    public void setWorkPrice(String workPrice) {
        this.workPrice = workPrice;
    }

    public String getOtherNotes() {
        return otherNotes;
    }

    public void setOtherNotes(String otherNotes) {
        this.otherNotes = otherNotes;
    }

    @Override
    public String toString() {
        return "RepairRecord{" +
                "id=" + id +
                ", repairDescription='" + repairDescription + '\'' +
                ", detailPrice='" + detailPrice + '\'' +
                ", workPrice='" + workPrice + '\'' +
                ", otherNotes='" + otherNotes + '\'' +
                '}';
    }
}
