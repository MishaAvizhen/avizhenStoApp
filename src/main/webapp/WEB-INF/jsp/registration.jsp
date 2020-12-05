<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>


<head>

    <title>AvizhenSTO</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
</head>
<body>
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="/" class="navbar-brand">AvizhenSTO</a>
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
        </div>
    </div>
</div>
<br>
<br>
<br>
<br>
<div class="container">
    <div class="row centered-form">
        <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Please sign up for AvizhenSTO
                        <small>It's free!</small>
                    </h3>
                </div>
                <div class="panel-body">
                    <form role="form" method="post" action="/registerClient">
                        <div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="text" name="first_name" id="first_name" class="form-control input-sm"
                                           placeholder="First Name">
                                </div>
                            </div>
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="text" name="last_name" id="last_name" class="form-control input-sm"
                                           placeholder="Last Name">
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="text" name="login" id="login" class="form-control input-sm"
                                           placeholder="login">
                                </div>
                            </div>
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="text" name="phone_number" id="phone_number"
                                           class="form-control input-sm" placeholder="phone_number">
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="password" name="password" id="password" class="form-control input-sm"
                                           placeholder="Password">
                                </div>
                            </div>
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="password" name="password_confirmation" id="password_confirmation"
                                           class="form-control input-sm" placeholder="Confirm Password">
                                </div>
                            </div>
                        </div>

                        <input type="submit" value="Registration" class="btn btn-info btn-block">

                    </form>
                </div>
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