package com.avizhen.avizhenSto.service.impl;

import com.avizhen.avizhenSto.dao.DetailCatalogDao;
import com.avizhen.avizhenSto.dto.DetailCatalogDto;
import com.avizhen.avizhenSto.entity.DetailCatalog;
import com.avizhen.avizhenSto.service.DetailCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.MAX_VALUE;

@Service
public class DetailCatalogImpl implements DetailCatalogService {
    @Autowired
    private DetailCatalogDao detailCatalogDao;


    @Transactional
    @Override
    public List<DetailCatalog> getListOfAllDetailCatalogElements() {
        return getListOfAllDetailCatalogElementsFilteredByPrices(null, null);
    }
    @Transactional
    @Override
    public List<DetailCatalog> getListOfAllDetailCatalogElementsFilteredByPrices(Integer minPrice,Integer maxPrice) {
        List<DetailCatalog> detailCatalogList = new ArrayList<>();
        if (minPrice == null) {
            minPrice=0;
        }
        if (maxPrice == null) {
            maxPrice = MAX_VALUE;
        }
        for (DetailCatalog detailCatalog : detailCatalogDao.findAllDetails()) {
            Integer detailCatalogPrice = Integer.valueOf(detailCatalog.getPrice());

            if (minPrice <= detailCatalogPrice && maxPrice >= detailCatalogPrice) {
                detailCatalogList.add(detailCatalog);

            }
        }
        return detailCatalogList;
    }

    @Transactional
    @Override
    public List<DetailCatalog> getDetailsByIds(List<Integer> detailIds) {
        List<DetailCatalog> detailCatalogListById = new ArrayList<>();
        for (Integer detailId : detailIds) {
            DetailCatalog foundDetailById = detailCatalogDao.findById(detailId);
            if (foundDetailById != null) {
                detailCatalogListById.add(foundDetailById);
            }
        }
        return detailCatalogListById;
    }

    @Transactional
    @Override
    public DetailCatalog getDetailById(Integer detailId) {
        return detailCatalogDao.findById(detailId);
    }

    @Transactional
    @Override
    public List<DetailCatalog> deleteDetailsByIds(List<Integer> detailIdsForDelete) {
        List<DetailCatalog> resultDetailListForDelete = new ArrayList<>();
        for (Integer detailId : detailIdsForDelete) {
            DetailCatalog deleteDetailByid = detailCatalogDao.findById(detailId);
            resultDetailListForDelete.add(deleteDetailByid);
        }
        return resultDetailListForDelete;
    }


    @Transactional
    @Override
    public void createOrUpdateDetailInCatalog(DetailCatalogDto detailCatalogDto) {
        Integer detailIdInCatalog = detailCatalogDto.getDetailId();
        DetailCatalog detailCatalog;

        boolean isCreateCase = detailIdInCatalog == 0;
        if (isCreateCase) {
            detailCatalog = new DetailCatalog();
        } else {
            detailCatalog = detailCatalogDao.findById(detailIdInCatalog);
        }

        detailCatalog.setName(detailCatalogDto.getName());
        detailCatalog.setDescription(detailCatalogDto.getDescription());
        detailCatalog.setPrice(detailCatalogDto.getPrice());

//        if (isCreateCase) {
            detailCatalogDao.saveDetailCatalog(detailCatalog);
//        } else {
//            detailCatalogDao.updateDetailCatalog(detailCatalog);
//        }
    }

}
