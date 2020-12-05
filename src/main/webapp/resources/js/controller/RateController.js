$(function () {

    var rateCtrl = (function () {
        var methods = {
            init: function () {
                var tableContentArr = this.fillTableContentArr();
                var currentDateTime = this.fillCurrentDate();

                this.showTable(tableContentArr);
            },
            showTable: function (inputArr) {

                var body = document.body,
                    tbl = document.createElement('table');
                tbl.style.width = '150px';
                tbl.style.border = '1px solid black';

                for (var i = 0; i < 3; i++) {
                    var tr = tbl.insertRow();
                    for (var j = 0; j < 3; j++) {
                        var td = tr.insertCell();
                        td.appendChild(document.createTextNode(inputArr[i][j]));
                        td.style.border = '1px solid black';

                    }
                }

                $('#rateContainer').append(tbl);
            },

            
            fillTableContentArr: function () {
                var tableContentArr = [
                    ["", "", ""],
                    ["", "", ""],
                    ["", "", ""]
                ];
                RateService.getRateByCurrencyCodeFromNbWebSite(145, function (responseFromNb) {
                    tableContentArr[0][0] = responseFromNb["Cur_Abbreviation"].toString();
                    tableContentArr[0][1] = responseFromNb["Cur_Scale"].toString();
                    tableContentArr[0][2] = responseFromNb["Cur_OfficialRate"].toString();
                });
                RateService.getRateByCurrencyCodeFromNbWebSite(292, function (responseFromNb) {
                    tableContentArr[1][0] = responseFromNb["Cur_Abbreviation"].toString();
                    tableContentArr[1][1] = responseFromNb["Cur_Scale"].toString();
                    tableContentArr[1][2] = responseFromNb["Cur_OfficialRate"].toString();
                });
                RateService.getRateByCurrencyCodeFromNbWebSite(298, function (responseFromNb) {
                    tableContentArr[2][0] = responseFromNb["Cur_Abbreviation"].toString();
                    tableContentArr[2][1] = responseFromNb["Cur_Scale"].toString();
                    tableContentArr[2][2] = responseFromNb["Cur_OfficialRate"].toString();
                });
                return tableContentArr;
            },

            fillCurrentDate: function () {
                var d = new Date();
                var formatDate = d.getFullYear() + "/" + (d.getMonth() + 1) + "/" + d.getDate();
                $("#currenDate").append(formatDate);

            }
            
        };


        return methods;
    }());

    rateCtrl.init();

});