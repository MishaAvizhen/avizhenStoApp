package com.avizhen.avizhenSto.dto;


public class RepairRecordDto {
    private String repairDescription;
    private String detailPrice;
    private String workPrice;
    private String otherNotes;

    private RepairRecordDto(Builder builder) {
        this.repairDescription = builder.repairDescription;
        this.detailPrice = builder.detailPrice;
        this.workPrice = builder.workPrice;
        this.otherNotes = builder.otherNotes;
    }

    public String getRepairDescription() {
        return repairDescription;
    }

    public String getDetailPrice() {
        return detailPrice;
    }

    public String getWorkPrice() {
        return workPrice;
    }

    public String getOtherNotes() {
        return otherNotes;
    }

    public static class Builder {
        private String repairDescription;
        private String detailPrice;
        private String workPrice;
        private String otherNotes;

        public Builder(String repairDescription) {
            this.repairDescription = repairDescription;
        }

        public Builder setDetailPrice(String detailPrice) {
            this.detailPrice = detailPrice;
            return this;
        }

        public Builder setWorkPrice(String workPrice) {
            this.workPrice = workPrice;
            return this;
        }

        public Builder setOtherNotes(String otherNotes) {
            this.otherNotes = otherNotes;
            return this;
        }

        public RepairRecordDto build() {
            return new RepairRecordDto(this);
        }
    }

}
