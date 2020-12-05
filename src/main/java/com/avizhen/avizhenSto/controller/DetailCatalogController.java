package com.avizhen.avizhenSto.controller;

import com.avizhen.avizhenSto.dao.DetailCatalogDao;
import com.avizhen.avizhenSto.dto.DetailCatalogDto;
import com.avizhen.avizhenSto.entity.DetailCatalog;
import com.avizhen.avizhenSto.entity.User;
import com.avizhen.avizhenSto.service.DetailCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@SessionAttributes({"currentUser", "cart"})
public class DetailCatalogController extends CommonInitSessionControler {
    @Autowired
    private DetailCatalogService detailCatalogService;
    @Autowired
    private DetailCatalogDao detailCatalogDao;


    @RequestMapping(value = "/catalog", method = RequestMethod.GET)
    public ModelAndView goToCatalog(ModelMap model) {
        initSession(model);
        List<DetailCatalog> detailCatalogList = detailCatalogService.getListOfAllDetailCatalogElements();
        return goToCatalogWithDetails(model, detailCatalogList);
    }

    @RequestMapping(value = "/addDetailToCart/{detailId}", method = RequestMethod.GET)
    public ModelAndView addDetailToCart(ModelMap model, @PathVariable Integer detailId,
                                        @ModelAttribute("cart") List<Integer> cartDetailIds,
                                        @ModelAttribute("currentUser") User currentUser) {
        initSession(model);
        if (currentUser.getId() != 0) {
            cartDetailIds.add(detailId);
        } else {
            model.addAttribute("catalogMsg", "Please, login to continue");
        }
        return goToCatalog(model);
    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public ModelAndView showDetailsInCart(ModelMap model,
                                          @ModelAttribute("cart") List<Integer> cartDetailIds) {
        initSession(model);
        List<DetailCatalog> detailsByIds = detailCatalogService.getDetailsByIds(cartDetailIds);
        model.addAttribute("detailsInCart", detailsByIds);
        return new ModelAndView("cart", model);
    }


    @RequestMapping(value = "/removeFromCart/{detailId}", method = RequestMethod.GET)
    public ModelAndView removeFromCart(ModelMap model, @PathVariable Integer detailId,
                                       @ModelAttribute("cart") List<Integer> cartDetailIds) {
        initSession(model);
        cartDetailIds.remove(detailId);
        return showDetailsInCart(model, cartDetailIds);
    }

    @RequestMapping(value = "/removeFromCatalog/{detailId}", method = RequestMethod.GET)
    public ModelAndView removeFromCatalog(ModelMap model, @ModelAttribute("currentUser") User currentUser,
                                          @PathVariable Integer detailId) {
        initSession(model);
        if (currentUser.isAdmin()) {
            detailCatalogDao.deleteDetailCatalogById(detailId);
            model.addAttribute("catalogMsg", "Detail was remove from catalog");

        } else {
            model.addAttribute("catalogMsg", "Error, please login as admin!");
        }
        return goToCatalog(model);
    }

    @RequestMapping(value = "/addDetailToCatalog", method = RequestMethod.GET)
    public String goToAddDetailToCatalogPage(ModelMap model) {
        initSession(model);
        return "addDetailToCatalog";
    }

    @RequestMapping(value = "/addDetailToCatalog", method = RequestMethod.POST)
    public ModelAndView addDetailToCatalog(ModelMap model, @RequestParam("name") String name, @RequestParam("description")
            String description, @RequestParam("price") String price) {
        DetailCatalogDto detailCatalogDto = new DetailCatalogDto.Builder(name)
                .setDescription(description)
                .setPrice(price)
                .build();
        detailCatalogService.createOrUpdateDetailInCatalog(detailCatalogDto);
        model.addAttribute("catalogMsg", "Detail was added to catalog");

        return goToCatalog(model);
    }

    @RequestMapping(value = "/createRepairRecordByRepReqId/{repairRequestId}", method = RequestMethod.GET)
    public ModelAndView goToRepairRecordPage(ModelMap model, @PathVariable Integer repairRequestId) {
        initSession(model);
        model.addAttribute("repair_request_id", repairRequestId);
        return new ModelAndView("repairRecord", model);
    }

    @RequestMapping(value = "/editDetailInCatalog", method = RequestMethod.POST)
    public ModelAndView editCatalogMark(ModelMap model,
                                        @RequestParam("name") String name,
                                        @RequestParam("description") String description,
                                        @RequestParam("price") String price,
                                        @RequestParam("detailIdInCatalogInput") Integer detailId) {

        DetailCatalogDto detailCatalogDto = new DetailCatalogDto.Builder(name)
                .setPrice(price)
                .setDescription(description)
                .setDetailId(detailId)
                .build();

        detailCatalogService.createOrUpdateDetailInCatalog(detailCatalogDto);
        model.addAttribute("catalogMsg", "Detail catalog was updated ");

        return goToCatalog(model);
    }

    @RequestMapping(value = "/editDetailInCatalog/{detailId}", method = RequestMethod.GET)
    public ModelAndView editCatalogMarkGet(ModelMap model, @PathVariable Integer detailId) {
        initSession(model);
        DetailCatalog detailById = detailCatalogService.getDetailById(detailId);
        if (detailById != null) {
            model.addAttribute("detailInCatalog", detailById);
        }
        return new ModelAndView("editDetailInCatalog", model);
    }

    @RequestMapping(value = "/filterByPrice", method = RequestMethod.POST)
    public ModelAndView filterByMinMaxPrice(ModelMap model, @RequestParam("minPrice") Integer minPrice, @RequestParam("maxPrice") Integer maxPrice) {
        initSession(model);
        List<DetailCatalog> detailsToShow = detailCatalogService.getListOfAllDetailCatalogElementsFilteredByPrices(minPrice,maxPrice);
        return goToCatalogWithDetails(model, detailsToShow);
    }

    private ModelAndView goToCatalogWithDetails(ModelMap model, List<DetailCatalog> detailsToShow) {
        model.addAttribute("catalog", detailsToShow);
        return new ModelAndView("catalog", model);
    }

}
