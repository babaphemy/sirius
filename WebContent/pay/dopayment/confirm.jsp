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
                    <p class="lead">Pay Now.
                    </p>
                   	 <h1 class="page-section-heading"><i class="fa fa-credit-card"></i> Payment Confirm</h1>
                    <div class="panel panel-default">
                        <div class="panel-body">   
                       <img src="http://alpha.school/assets/img/thankyou.jpg" alt="payment ok" />          
                       	 <!--if live   <form name="contactform" method="post" action="https://webpay.interswitchng.com/paydirect/pay"> -->
            <form name="" method="post" action="https://stageserv.interswitchng.com/test_paydirect/pay"> 
			<input name="product_id" type="hidden" value="<%= session.getAttribute("pdtid") %>" />
			<input name="pay_item_id" type="hidden" value="101" />
			<input name="currency" type="hidden" value="566" />
			<input name="amount" type="hidden" value="${tranx.amount * 100}" />		
			<input name="site_redirect_url" type="hidden" value="<%= session.getAttribute("rurl") %>" />
			<input name="hash" type="hidden" value="<%= session.getAttribute("dhash") %>" />
			<input name="cust_name" type="hidden" value="${tranx.name}" />
			<input name="cust_id" type="hidden" value="${tranx.txnref}" />
			<div class="form-group">
			<label for="Ama">Amount</label>
			<input type="text" required="required" class="form-control" style="border:none" id="Ama" name="ama" type="text" value="${tranx.amount}">
            </div>
			<div class="form-group">
			<label for="txn_ref">Transaction Ref: (Keep for reference)</label> 
			<input type="text" style="border:none" class="form-control" id="txn_ref" name="txn_ref" type="text" value="${tranx.txnref}">
            </div>
                        
			<button type="submit">Pay</button>
		</form>
	
                                      
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
    
    
    
    
    
    
    
    
    
   