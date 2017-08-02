<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


    <script type="text/javascript">
           var HOST = "http://localhost:8080"
           var ORDERS_URL = HOST + "/orders/";
           var ORDER_URL = HOST + "/orders/order/";
           var COUPON_URL = HOST + "/coupons/coupon/";


              $.dto = null;

              findAll();

           function findAll() {
               console.log('findAll');
               $.ajax({
                   type: 'GET',
                   url: ORDERS_URL + $('#client_username').val(),
                   dataType: "json", // data type of response
                   success: renderList,
                   error: function(jqXHR, textStatus, errorThrown) {
                               console.log(jqXHR, textStatus, errorThrown);
                               alert('findAll: ' + textStatus);
                          }
               });
           }

   $(document).on("click", "a", function() {
       var action = $(this).text();
       var selectedOrderId = $(this).data("id");
       if (action == "Delete") {
           deleteOrder(selectedOrderId);
       }
   });

           function renderList(data) {
               dto = data == null ? [] : (data instanceof Array ? data : [data]);
               $('#ordersTable tr').remove();
               $.each(dto, function (index, order) {
                   drawRow(order);
               });
           }

           function formatDate(inputStr) {
               		var timestamp = parseInt(inputStr, 10);
               		var date = new Date(timestamp);
               		return date.toISOString().substr(0, 10);
           }

           function drawRow(order) {
               var row = $("<tr />")
               $("#ordersTable").append(row);
               row.append($("<td hidden>" + order.orderId + '</td>'));
               row.append($("<td hidden>" + order.clientId + "</td>"));
               row.append($("<td>" + order.movieTitle + "</td>"));
               row.append($("<td hidden>" + order.categoryId + "</td>"));
               row.append($("<td>" + order.categoryTitle + "</td>"));
               row.append($("<td>" + order.rating + "/10</td>"));
               row.append($("<td>" + formatDate(order.orderDate) + "</td>"));
               row.append($("<td>" + order.price + "</td>"));
               row.append($("<td>" + '<a href="#" data-id="' + order.orderId + '">Delete</a></td>'));
               row.append($("<td></td>"));
           }


        function deleteOrder(orderId) {
            $.ajax({
                async: false,
                type: 'DELETE',
                contentType: 'application/json',
                url: ORDER_URL + orderId,
                success: function(){
                            alert('The order has been successfully deleted.');
                            findAll();
                         },
                error: function(jqXHR, textStatus, errorThrown) {
                         alert('deleteOrder error: ' + errorThrown);
                       }
            });
        }

        function activateCoupon() {
            var code = $('#codeField').val();
            var username = $('#client_username').val();
            $.ajax({
                async: false,
                type: 'POST',
                contentType: 'application/json',
                url: COUPON_URL + code + '/client/' + username,
                success: function(){
                              alert('The coupon has been successfully activated.');
                              findAll();
                         },
                error: function(jqXHR, textStatus, errorThrown) {
                            alert('Wrong promotional code!');
                       }
            });
        }
    </script>


<div class="container">

    <table width=100%>
        <tr>
            <td>
                <h2>Shopping cart</h2>
            </td>
            <td align="right">
                <h3>Activate a discount coupon.</h3>
                <input type="text" id="codeField" name="codeField" placeholder="Type a promotional code" />
                <input type="button" value="Activate" onClick="activateCoupon()"/>
            </td>
        </tr>
    </table>
    <br>

    <div class="table-responsive">
      <table class="table table-striped">
      <thead>
        <tr>
            <th hidden>ID</th>
            <th hidden>Client ID</th>
            <th hidden>Movie ID</th>
            <th>Title</th>
            <th hidden>Category ID</th>
            <th>Category</th>
            <th>Rating</th>
            <th>Order date</th>
            <th>Price (with discount)</th>
            <th></th>
        </tr>
        </thead>
        <tbody id="ordersTable">
        <tr>
            <td>test</td>
            <td>test</td>
            <td>test</td>
            <td>test</td>
            <td>test</td>
            <td>test</td>
        </tr>
        </tbody>
      </table>
    </div>

</div>
