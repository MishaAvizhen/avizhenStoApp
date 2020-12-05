$(function () {

    var repairRecordCtrl = (function () {
        var methods = {
            init: function () {
                this.initDeleteFromRepairRecordButtons();
            },
            initDeleteFromRepairRecordButtons: function () {
                $("#repairRequestAndRepairRecordsContainer").find(".deleteDetailFromRepairRecord").each(function() {
                    $(this).click(function() {
                        var clickedRepairRecordIdToDelete = $(this).attr('repairRecordIdToDelete');
                        RepairRecordService.removeFromRepairRecord(clickedRepairRecordIdToDelete, function (data) {
                            var detailContainerIdInRepairRecord = "#RepairRecordList"+ clickedRepairRecordIdToDelete;
                            $(detailContainerIdInRepairRecord).remove();
                            var $backendInfo = $("#repairRecordInfo");
                            $backendInfo.append("Repair record was deleted");
                            repairRecordCtrl.init();

                        });
                    });
                });
            }

        };


        return methods;
    }());

    repairRecordCtrl.init();

});