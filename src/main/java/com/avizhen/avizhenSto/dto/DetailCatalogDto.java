package com.avizhen.avizhenSto.dto;

/**
 * Created by Александр on 20.11.2020.
 */
public class DetailCatalogDto {
    private String name;
    private String description;
    private String price;
    private Integer detailId;

    private DetailCatalogDto(Builder builder) {
        this.name = builder.name;
        this.description = builder.description;
        this.price = builder.price;
        this.detailId = builder.detailId;

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public Integer getDetailId() {
        return detailId;
    }

    public static class Builder {
        private String name;
        private String description;
        private String price;
        private Integer detailId = 0;

        public Builder(String name) {
            this.name = name;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setPrice(String price) {
            this.price = price;
            return this;
        }

        public Builder setDetailId(Integer detailId) {
            this.detailId = detailId;
            return this;
        }

        public DetailCatalogDto build() {
            return new DetailCatalogDto(this);
        }
    }
}
