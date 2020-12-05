package com.avizhen.avizhenSto.dao;

import com.avizhen.avizhenSto.entity.DetailCatalog;

import java.util.List;


public interface DetailCatalogDao {
    void saveDetailCatalog(DetailCatalog detailCatalog);

    List<DetailCatalog> findAllDetails();

    void deleteDetailCatalogById(int id);

    DetailCatalog findById(int id);

    DetailCatalog findByName(String name);

    void updateDetailCatalog(DetailCatalog detailCatalog);
}
