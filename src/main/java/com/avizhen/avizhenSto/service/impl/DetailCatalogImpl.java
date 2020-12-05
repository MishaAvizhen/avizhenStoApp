package com.avizhen.avizhenSto.service.impl;

import com.avizhen.avizhenSto.dao.DetailCatalogDao;
import com.avizhen.avizhenSto.dto.DetailCatalogDto;
import com.avizhen.avizhenSto.entity.DetailCatalog;
import com.avizhen.avizhenSto.entity.User;
import com.avizhen.avizhenSto.service.DetailCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DetailCatalogImpl implements DetailCatalogService {
    @Autowired
    private DetailCatalogDao detailCatalogDao;


    @Transactional
    @Override
    public List<DetailCatalog> getListOfAllDetailCatalogElements() {
        List<DetailCatalog> resultDetailCatalogList = new ArrayList<>();
        List<DetailCatalog> getAllDetailCatalogElements = detailCatalogDao.findAllCarts();
        for (DetailCatalog allDetailCatalogElement : getAllDetailCatalogElements) {
            resultDetailCatalogList.add(allDetailCatalogElement);
        }

        return resultDetailCatalogList;
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
