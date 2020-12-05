var CatalogService = (function () {

    var methods = {
        
        removeDetailFromCatalog: function (detailIdToDelete, callback) {
            $.ajax({
                url: "/api/removeFromCatalog/" + detailIdToDelete,
                method: "GET",
                success: function (data) {
                    callback(data);
                },
                error: function () {
                    console.error("cannot delete detail to catalog");
                }
            });
        }
        
    };

    return methods;

}());
