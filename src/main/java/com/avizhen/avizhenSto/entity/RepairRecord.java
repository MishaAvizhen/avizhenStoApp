package com.avizhen.avizhenSto.entity;


import javax.persistence.*;


@Entity
@Table(name = "repair_record", schema = "avizhen_sto")
public class RepairRecord {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @OneToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "repair_request_id")
    private RepairRequest repairRequest;

    @Column(name = "repair_description", nullable = false)
    private String repairDescription;

    @Column(name = "detail_price", nullable = false)
    private String detailPrice;

    @Column(name = "work_price")
    private String workPrice;

    @Column(name = "other_notes")
    private String otherNotes;

    public RepairRecord() {
    }

    public RepairRecord(String repairDescription, String detailPrice, String workPrice, String otherNotes) {

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

    public RepairRequest getRepairRequest() {
        return repairRequest;
    }

    public void setRepairRequest(RepairRequest repairRequest) {
        this.repairRequest = repairRequest;
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
