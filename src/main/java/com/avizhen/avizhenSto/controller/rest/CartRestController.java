package com.avizhen.avizhenSto.controller.rest;

import com.avizhen.avizhenSto.dao.DetailCatalogDao;
import com.avizhen.avizhenSto.entity.DetailCatalog;
import com.avizhen.avizhenSto.entity.User;
import com.avizhen.avizhenSto.service.DetailCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
@SessionAttributes({"cart"})
public class CartRestController {
    @Autowired
    DetailCatalogService detailCatalogService;
    @Autowired
    DetailCatalogDao detailCatalogDao;

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public ResponseEntity getCartInfo(@ModelAttribute("cart") List<Integer> cartDetailIds) {
        return new ResponseEntity<List<Integer>>(cartDetailIds, HttpStatus.OK);
    }

    @RequestMapping(value = "/addToCart/{detailId}", method = RequestMethod.GET)
    public ResponseEntity addDetailToCart(@PathVariable Integer detailId,
                                          @ModelAttribute("cart") List<Integer> cartDetailIds) {
        cartDetailIds.add(detailId);
        return new ResponseEntity<Integer>(cartDetailIds.size(), HttpStatus.OK);
    }

    @RequestMapping(value = "/detail/{detailId}", method = RequestMethod.GET)
    public ResponseEntity getDetailInfo(@PathVariable Integer detailId) {
        DetailCatalog resultDetail = null;
        List<DetailCatalog> detailsByIds = detailCatalogService.getDetailsByIds(Collections.singletonList(detailId));
        if (!detailsByIds.isEmpty()) {
            resultDetail = detailsByIds.get(0);
        }
        return new ResponseEntity<DetailCatalog>(resultDetail, HttpStatus.OK);
    }

    @RequestMapping(value = "/cart/fullPrice", method = RequestMethod.GET)
    public ResponseEntity calculateFullPrice(@ModelAttribute("cart") List<Integer> cartDetailIds) {
        List<DetailCatalog> detailsByIds = detailCatalogService.getDetailsByIds(cartDetailIds);
        Double res = 0.0;
        for (DetailCatalog detailsById : detailsByIds) {
            res += Double.valueOf(detailsById.getPrice());
        }
        return new ResponseEntity<Double>(res, HttpStatus.OK);
    }
    @RequestMapping(value = "/removeFromCart/{detailId}", method = RequestMethod.GET)
    public ResponseEntity removeDetailFromCart(@PathVariable Integer detailId,
                                          @ModelAttribute("cart") List<Integer> cartDetailIds) {
        cartDetailIds.remove(detailId);
        return new ResponseEntity<Integer>(cartDetailIds.size(), HttpStatus.OK);
    }
    @RequestMapping(value = "/clearCart", method = RequestMethod.GET)
    public ResponseEntity clearCart( @ModelAttribute("cart") List<Integer> cartDetailIds) {
        cartDetailIds.clear();
        return new ResponseEntity<Integer>(cartDetailIds.size(), HttpStatus.OK);
    }

    @RequestMapping(value = "/removeFromCatalog/{detailId}", method = RequestMethod.GET)
    public ResponseEntity removeDetailFromCatalog(@PathVariable Integer detailId,
                                                  @ModelAttribute("currentUser") User currentUser) {
        DetailCatalogDao detailCatalogDaoToDelete = this.detailCatalogDao;
        detailCatalogDaoToDelete.deleteDetailCatalogById(detailId);
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }
}
