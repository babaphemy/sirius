<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="date" class="java.util.Date" />
<!DOCTYPE html>
<html>
<%@ include file="header.jsp" %>

<body> 
    
        <!-- Navigation -->
    <nav class="navbar navbar-default ">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                   <a class="navbar-brand" href="http://alpha.school/#/welcome">Alpha Schools</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
           
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>
    
    
      <div class="content">
            <div class="container">    
                <div class="success-body" style="text-align: center">
                    <span class="alerticon"><img src="<c:url value='http://alpha.school/assets/img/logo.png'/>"  /></span>
                    <h1>Yay!</h1>
                    <p class="lead">Your transaction was successful.
                    </p>
                   	 <h1 class="page-section-heading"><i class="fa fa-credit-card"></i> Payment Complete</h1>
                    <div class="panel panel-default">
                        <div class="panel-body">   
                       <img src="http://alpha.school/assets/img/thankyou.jpg" alt="payment ok" />             
						<h1>${callback.body.ResponseCode}</h1>
						<p>Reason: ${callback.body.ResponseDescription}</p>
						<p>Transaction reference: ${callback.body.PaymentReference}</p>
					
                                      
                                </div>
                               
                              
                        </div>

                    <br>

                    <hr>

                    
                </div> 
            </div>    
        </div>
    
    
    

  
    <!-- Footer -->
    <footer class="text-center">
   
        <div class="footer-below">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        Copyright &copy; <a href="http://alpha.school/#/welcome">ESSL <fmt:formatDate value="${date}" pattern="yyyy" /></a>
                    </div>
                </div>
            </div>
        </div>
    </footer>


   

</body>

</html>
    
    
    
    
    
    
    
    
    
   