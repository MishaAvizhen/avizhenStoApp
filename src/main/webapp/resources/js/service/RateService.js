var RateService = (function () {
    var currencyNameToCode = {
        "USD": 145,
        "EUR": 292,
        "RUB": 298
    };

    var methods = {
        loadRates: function (callback) {
            $.ajax({
                url: "/api/rate",
                method: "GET",
                success: function (data) {
                    callback(data);
                },
                error: function () {
                    console.error("cannot load rates");
                }
            });
        },
        getRateByCurrencyCodeFromNbWebSite: function (currencyCode, callback) {
            $.ajax({
                url: "http://www.nbrb.by/API/ExRates/Rates/" + currencyCode,
                method: "GET",
                async: false,
                success: function (data) {
                    callback(data);
                },
                error: function () {
                    console.error("cannot load currencies");
                }
            });
        },
        convertBYNIntoCurrency: function (priceInBYN, toCurrency) {
            var toCurrencyCode = currencyNameToCode[toCurrency];
            var result = 0;
            methods.getRateByCurrencyCodeFromNbWebSite(toCurrencyCode, function (data) {
                result = priceInBYN * data["Cur_Scale"] / data["Cur_OfficialRate"];
            });
            return result;
        }

    };

    return methods;

}());
