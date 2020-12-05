var TotalPriceService = (function () {

    var methods = {
        addToCart: function (callback) {
            $.ajax({
                url: "/addDetailToCart/${catalogDetail.id}",
                method: "GET",
                success: function (data) {
                    callback(data);
                },
                error: function () {
                    console.error("cannot add detail to cart");
                }
            });
        },
        getFullPriceForDetailsInCart: function (callback) {
            $.ajax({
                url: "/api/cart/fullPrice",
                method: "GET",
                async: false,
                success: function (data) {
                    callback(data);
                },
                error: function () {
                    console.error("cannot add detail to cart");
                }
            });
        }

        
    };

    return methods;

}());
