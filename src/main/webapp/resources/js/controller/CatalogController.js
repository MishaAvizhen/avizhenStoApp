$(function () {

    var catalogCtrl = (function () {
        var methods = {
            init: function () {
                this.initAddToCartButtons();
                this.initDeleteDetailFromCatalogButtons();
            },
            initAddToCartButtons: function () {
                $("#catalogList").find(".addToCart").each(function() {
                    $(this).click(function() {
                        var clickedDetailId = $(this).attr('detailId');
                        CartService.addDetailToCart(clickedDetailId, function (data) {
                            var $cartSizeBadge = $("#cartSizeBadge");
                            $cartSizeBadge.empty();
                            $cartSizeBadge.append(data);
                        });
                    });
                });
            },
            initDeleteDetailFromCatalogButtons: function () {
                $("#catalogList").find(".deleteDetailFromCatalog").each(function() {
                    $(this).click(function() {
                        var clickedDetailIdToDelete = $(this).attr('detailIdToDelete');
                        CatalogService.removeDetailFromCatalog(clickedDetailIdToDelete, function (data) {
                            var detailContainerIdInCatalog = "#detailCatalogList"+ clickedDetailIdToDelete;
                            $(detailContainerIdInCatalog).remove();
                            catalogCtrl.init();
            
                        });
                    });
                });
            }
            
        };


        return methods;
    }());

    catalogCtrl.init();

});