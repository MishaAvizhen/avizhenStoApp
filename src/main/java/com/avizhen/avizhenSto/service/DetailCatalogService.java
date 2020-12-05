package com.avizhen.avizhenSto.service;

import com.avizhen.avizhenSto.dto.DetailCatalogDto;
import com.avizhen.avizhenSto.entity.DetailCatalog;

import java.util.List;

/**
 * Created by Александр on 20.11.2020.
 */
public interface DetailCatalogService {
    List<DetailCatalog> getListOfAllDetailCatalogElements();

    List<DetailCatalog> getDetailsByIds(List<Integer> detailIds);


    DetailCatalog getDetailById(Integer detailId);


    List<DetailCatalog> deleteDetailsByIds(List<Integer> detailIdsForDelete);

    void createOrUpdateDetailInCatalog(DetailCatalogDto detailCatalogDto);





}

