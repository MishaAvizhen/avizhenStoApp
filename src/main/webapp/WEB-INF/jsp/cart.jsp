
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>


<head>

    <title>AvizhenSTO</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/service/TotalPriceService.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/service/RateService.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/service/CartService.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/controller/RateController.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/controller/CartController.js" />"></script>


</head>
<body>
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button  class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="/" class="navbar-brand" >AvizhenSTO</a>
            <ul class="nav navbar-nav">
                <li><a href="/">Home</a></li>
                <li><a href="/allUserOrders">My Repairs</a></li>
                <li><a href="/allRepairRequests">All repairs Requests</a></li>
                <li><a href="/catalog">Catalog</a></li>
                <c:if test="${currentUser.admin}">
                    <li><a href="/allRepRecordAndRepRequestList" style="color:rgb(248, 239, 53);">All Repair Records</a></li>
                </c:if>
            </ul>
        </div>
        <div class=" navbar-collapse collapse">
            <c:if test="${currentUser.id == 0}">
                <form class="navbar-form navbar-right" method="post" action="/login">
                    <div class="form-group">
                        <input type="text" placeholder="login" name="login" class="form-control" size="10">
                    </div>
                    <div class="form-group">
                        <input type="password" placeholder="password" name="password" class="form-control" size="10">
                    </div>
                    <button type="submit" class="btn btn-success"> Log in</button>
                        <span><a href="/registration" class="btn btn-info pull-right"
                                 role="button">Sign up</a></span>
                </form>
            </c:if>
            <c:if test="${currentUser.id != 0}">
                <div class="text-white">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="/repairRequest" style="color:rgb(70, 234, 14);">Create repair request</a></li>
                        <li><a href="/userProfile" style="color:rgb(7, 246, 184 );">Hello, ${currentUser.login}</a></li>
                        <li><a href="/logout" class="glyphicon glyphicon-log-out pull-right">Logout</a></li>
                        <li class="active"><a href="/cart">Cart<span class="badge" id="cartSizeBadge">${cart.size()}</span></a>
                        </li>
                    </ul>
                </div>
            </c:if>
        </div>
    </div>
</div>
<br>
<br>
<br>
<br>
<div class="container">
    <input type="hidden" id="detailsInCartInput" value="${detailsInCart}"/>
    <div class="row centered-form">
        <div class="col-md-8 col-lg-9"
             id="detailsInCart">

            <c:forEach items="${detailsInCart}" var="catalogDetailInCarById">
                <div class="panel panel-default" id="cartDetailContainer${catalogDetailInCarById.id}">
                    <div class="panel-heading">
                        <h3 class="panel-title">Orders: </h3>
                    </div>

                    <div class="panel-body">
                        <div class="row">
                            <div class="form-group">
                                <div class="list-group">
                                    <a href="#" class="list-group-item active">Name: ${catalogDetailInCarById.name}</a>
                                    <a href="#" class="list-group-item ">Description:${catalogDetailInCarById.description}</a>
                                    <a href="#" class="list-group-item active price" priceVal="${catalogDetailInCarById.price}">Price: ${catalogDetailInCarById.price}  BYN</a>
                                    <a href="#" class="btn btn-success pull-right " role="button">Buy </a>
                                    <a href="/removeFromCart/${catalogDetailInCarById.id}" class="btn btn-warning pull-right " role="button">Remove from cart</a>
                                    <button type="button" class="btn btn-success removeFromCart"
                                            detailIdToRemove="${catalogDetailInCarById.id}">Remove from cart with ajax</button>
                                </div>
                                <hr>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="col-md-4 col-lg-3">
            <div id="totalPriceContainer">

                <h4 id="totalPrice">Total BYN price: </h4>
                <h4 id="totalUsdPrice">Total USD price: </h4>
                <h4 id="totalEuroPrice">Total EUR price: </h4>
                <h4 id="totalRubPrice">Total RUB price: </h4>
                <button type="button" class="btn btn-info clearCart">Clear cart</button>

            </div>
        </div>
    </div>
</div>
<hr>
<footer>
    <p>&copy; All rights reserved 2020</p>
</footer>

<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
</body>
</html>