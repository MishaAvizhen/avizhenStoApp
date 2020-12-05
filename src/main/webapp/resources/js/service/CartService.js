var CartService = (function () {

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
        addDetailToCart: function (detailId, callback) {
            $.ajax({
                url: "/api/addToCart/" + detailId,
                method: "GET",
                success: function (data) {
                    callback(data);
                },
                error: function () {
                    console.error("cannot add detail to cart");
                }
            });
        },
        removeDetailFromCart: function (detailIdToRemove, callback) {
            $.ajax({
                url: "/api/removeFromCart/" + detailIdToRemove,
                method: "GET",
                success: function (data) {
                    callback(data);
                },
                error: function () {
                    console.error("cannot add detail to cart");
                }
            });
        },
        clearCart: function ( callback) {
            $.ajax({
                url: "/api/clearCart/",
                method: "GET",
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
