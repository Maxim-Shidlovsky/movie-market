<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


    <script type="text/javascript">
        function deleteOrder(orderId) {
            alert($('#client_username').val());
            $.ajax({
                async: false,
                type: 'DELETE',
                contentType: 'application/json',
                url: 'http://localhost:8080/order?orderId=' + orderId + '&username=' + $('#client_username').val(),
                success: function(){
                            alert('The order has been successfully deleted.');
                         },
                error: function(jqXHR, textStatus, errorThrown) {
                         alert('deleteMovie error: ' + errorThrown);
                       }
            });
        }
    </script>


<div class="container">

    <h2>Shopping cart</h2>

    <div class="table-responsive">
      <table class="table table-striped" id="ordersTable">
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
        <c:forEach items="${orderDTOList}" var="orderDTO">
        <tr>
          <td hidden>
             <c:out value="${orderDTO.orderId}" />
          </td>
          <td hidden>
             <c:out value="${orderDTO.clientId}" />
          </td>
          <td hidden>
            <c:out value="${orderDTO.movieId}" />
          </td>
          <td>
            <c:out value="${orderDTO.movieTitle}" />
          </td>
          <td hidden>
            <c:out value="${orderDTO.categoryId}" />
          </td>
          <td>
            <c:out value="${orderDTO.categoryTitle}" />
          </td>
          <td>
            <c:out value="${orderDTO.rating}" />/10
          </td>
          <td>
            <c:out value="${orderDTO.orderDate}" />
          </td>
          <td>
            <c:out value="${orderDTO.price}" />
          </td>
          <td>
            <a href="" onClick="deleteOrder(${orderDTO.orderId})">Delete</a>
          </td>
        </tr>
        </c:forEach>
      </table>
    </div>

</div>
