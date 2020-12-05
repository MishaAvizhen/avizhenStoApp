$(function () {

    var cartCtrl = (function () {
        var methods = {
            init: function () {
                this.initRemoveFromCartButtons();
                this.initFullPriceBlock();
                this.initClearCartButton();
            },
            initFullPriceBlock: function () {
                var totalPrice = 0;
                var totalPriceInUsd = 0;
                var totalPriceInEuro = 0;
                var totalPriceInRub = 0;


                TotalPriceService.getFullPriceForDetailsInCart(function (data) {
                    totalPrice = data;
                });
                totalPriceInUsd = RateService.convertBYNIntoCurrency(totalPrice, "USD");
                totalPriceInEuro = RateService.convertBYNIntoCurrency(totalPrice, "EUR");
                totalPriceInRub = RateService.convertBYNIntoCurrency(totalPrice, "RUB");
                
                var $totalPriceBlock = $("#totalPrice");
                var $totalPriceBlockUsd = $("#totalUsdPrice");
                var $totalPriceBlockEuro = $("#totalEuroPrice");
                var $totalPriceBlockRub = $("#totalRubPrice");
                
                $totalPriceBlock.empty();
                $totalPriceBlockUsd.empty();
                $totalPriceBlockEuro.empty();
                $totalPriceBlockRub.empty();
                
                $totalPriceBlock.append("Total price: "+ totalPrice.toFixed(2) + " BYN");
                $totalPriceBlockUsd.append("Total USD price : "+ totalPriceInUsd.toFixed(2) + " USD");
                $totalPriceBlockEuro.append("Total EUR price : "+ totalPriceInEuro.toFixed(2) + " EUR");
                $totalPriceBlockRub.append("Total RUB price : "+ totalPriceInRub.toFixed(2) + " RUB");

            },
            initRemoveFromCartButtons: function () {
                $("#detailsInCart").find(".removeFromCart").each(function () {
                    $(this).click(function () {
                        var clickedDetailIdToDelete = $(this).attr('detailIdToRemove');
                        CartService.removeDetailFromCart(clickedDetailIdToDelete, function (data) {
                            var $cartSizeBadge = $("#cartSizeBadge");
                            $cartSizeBadge.empty();
                            $cartSizeBadge.append(data);
                            var detailContainerId = "#cartDetailContainer" + clickedDetailIdToDelete;
                            $(detailContainerId).remove();
                            cartCtrl.initFullPriceBlock();
                        });
                    });
                });
            },
            initClearCartButton: function () {
                $("#totalPriceContainer").find(".clearCart").each(function () {
                    $(this).click(function () {
                        CartService.clearCart( function (data) {
                            var $cartSizeBadge = $("#cartSizeBadge");
                            $cartSizeBadge.empty();
                            $cartSizeBadge.append(data);
                            var detailContainerId = "#detailsInCart";
                            $(detailContainerId).empty();
                            cartCtrl.initFullPriceBlock();

                        });
                    });
                });
            }
        };


        return methods;
    }());

    cartCtrl.init();

});