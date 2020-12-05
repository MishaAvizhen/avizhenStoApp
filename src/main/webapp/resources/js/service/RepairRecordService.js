var RepairRecordService = (function () {

    var methods = {

        removeFromRepairRecord: function (repairRecordIdToDelete, callback) {
            $.ajax({
                url: "/api/deleteFromRRAndRReqForm/" + repairRecordIdToDelete,
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