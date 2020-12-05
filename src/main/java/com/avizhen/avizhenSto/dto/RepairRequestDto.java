package com.avizhen.avizhenSto.dto;

import com.sun.istack.internal.NotNull;

import javax.persistence.Column;
import java.util.Date;

/**
 * Created by Александр on 12.11.2020.
 */
public class RepairRequestDto {

    private Date dateOfRepair;
    private String carRemark;
    private String repairDescription;

    private RepairRequestDto(Builder builder) {

        this.dateOfRepair = builder.dateOfRepair;
        this.carRemark = builder.carRemark;
        this.repairDescription = builder.repairDescription;

    }

    public Date getDateOfRepair() {
        return dateOfRepair;
    }

    public String getCarRemark() {
        return carRemark;
    }

    public String getRepairDescription() {
        return repairDescription;
    }

    public static class Builder {

        private Date dateOfRepair;
        private String carRemark;
        private String repairDescription;

        public Builder(Date dateOfRepair ) {
            this.dateOfRepair = dateOfRepair;
        }

        public Builder setDateOfRepair(Date dateOfRepair) {
            this.dateOfRepair = dateOfRepair;
            return this;
        }

        public Builder setCarRemark(String carRemark) {
            this.carRemark = carRemark;
            return this;
        }

        public Builder setRepairDescription(String repairDescription) {
            this.repairDescription = repairDescription;
            return this;
        }

        public RepairRequestDto build() {
            return new RepairRequestDto(this);
        }
    }
}
