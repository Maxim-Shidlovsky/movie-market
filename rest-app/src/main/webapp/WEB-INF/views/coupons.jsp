<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>


    <script type="text/javascript">
        function clean() {
            $('#couponId').val('');
            $('#code').val('');
            $('#discount').val('');
        }

        function clearUpdateFields() {
            $('#fieldUpdateCouponId').val('');
            $('#fieldUpdateCode').val('');
            $('#fieldUpdateDiscount').val('');
            $('#fieldUpdateReceivingDate').val('');
        }

        function deleteCoupon(couponId) {
            $.ajax({
                async: false,
                type: 'DELETE',
                contentType: 'application/json',
                url: 'http://localhost:8080/coupon/' + couponId,
                success: function(){
                            alert('The coupon has been successfully deleted.');
                      },
                error: function(jqXHR, textStatus, errorThrown) {
                         alert('deleteCoupon error: ' + errorThrown);
                      }
            });
        }

        function editCoupon(couponId, code, discount, receivingDate) {
            $('#fieldUpdateCouponId').val(couponId);
            $('#fieldUpdateCode').val(code);
            $('#fieldUpdateDiscount').val(discount);
            $('#fieldUpdateReceivingDate').val(receivingDate);
        }

        function updateCoupon() {
            var couponId = $('#fieldUpdateCouponId').val();
            var code = $('#fieldUpdateCode').val();
            var discount = $('#fieldUpdateDiscount').val();
            var receivingDate = $('#fieldUpdateReceivingDate').val();

            $.ajax({
                async: false,
                type: 'PUT',
                contentType: 'application/json',
                url: 'http://localhost:8080/coupon?couponId=' + couponId + '&code=' + code + '&discount=' + discount + '&receivingDate=' + receivingDate,
                success: function(){
                                alert('The coupon has been successfully updated.');
                                location.reload();
                         },
                error: function(jqXHR, textStatus, errorThrown) {
                                alert('updateCoupon error: ' + errorThrown);
                       }
            });
        }

        function getCouponById() {
            $.ajax({
                async: false,
                type: 'GET',
                contentType: 'application/json',
                url: 'http://localhost:8080/coupon/' + $('#findByIdField').val(),
                success: function(){
                             alert('The coupon has been successfully found.');
                             location.href = 'http://localhost:8080/coupon/' + $('#findByIdField').val();
                         },
                error: function(jqXHR, textStatus, errorThrown) {
                             alert('getCouponById error: ' + errorThrown);
                       }
            });
        }

        function getCouponByCode() {
            $.ajax({
                async: false,
                type: 'GET',
                contentType: 'application/json',
                url: 'http://localhost:8080/coupon/code/' + $('#findByCodeField').val(),
                success: function(){
                            alert('The coupon has been successfully found.');
                            location.href = 'http://localhost:8080/coupon/code/' + $('#findByCodeField').val();
                         },
                error: function(jqXHR, textStatus, errorThrown) {
                             alert('getCouponByCode error: ' + errorThrown);
                       }
            });
        }
    </script>


<div class="container">

    <h2>Coupons</h2>

    <div class="table-responsive">
      <table class="table table-striped" id="categoriesTable">
        <tr>
            <th hidden>ID</th>
            <th>Code</th>
            <th>Discount</th>
            <th>Activation date</th>
            <th></th>
            <th></th>
        </tr>
        <c:forEach items="${couponList}" var="coupon">
        <tr>
          <td hidden>
            <c:out value="${coupon.couponId}" />
          </td>
          <td>
            <c:out value="${coupon.code}" />
          </td>
          <td>
            <c:out value="${coupon.discount}" />
          </td>
          <td>
            <c:out value="${coupon.receivingDate}" />
          </td>
          <td>
            <input type="button" onClick="editCoupon(${coupon.couponId}, '${coupon.code}', ${coupon.discount}, '${coupon.receivingDate}')" value="Edit">
          </td>
          <td>
            <a href="" onClick="deleteCoupon(${coupon.couponId})">Delete</a>
          </td>
        </tr>
        </c:forEach>
      </table>
    </div>


    <table width=100%>
    <tr>
    <td>
    <div class="mainArea">
        <h3>Add coupon</h3>
        <form method="post" action="/coupon">
            <input type="text" hidden id="couponId" name="couponId" placeholder="Coupon's ID"/>
            <input type="text" id="code" name="code" placeholder="Type a code"/>
            <input type="text" id="discount" name="discount" placeholder="Type a discount"/>
            <br/>
            <input type="submit" value="Send" />
            <input type="button" value="Clear" noClick="clear()" />
        </form>
    </div>
    </td>

    <td>
    <div class="mainArea">
        <h3>Update coupon</h3>
        <input type="text" hidden id="fieldUpdateCouponId" name="couponId" placeholder="Coupon's ID"/>
        <input type="text" id="fieldUpdateCode" name="code" placeholder="Type a code"/>
        <input type="text" id="fieldUpdateDiscount" name="discount" placeholder="Type a discount"/>
        <input type="text" id="fieldUpdateReceivingDate" name="receivingDate" placeholder="Type a receivingDate"/>
        <br/>
        <input type="button" value="Send" onClick="updateCoupon()"/>
        <input type="button" value="Clear" noClick="clearUpdateFields()" />
    </div>
    </td>
    </tr>
    </table>

    <hr>

    <table width=100%>
    <tr>
    <td>
    <div class="mainArea">
        <h3>Search coupon by code:</h3>
        <input type="text" id="findByCodeField" name="findByCodeField" placeholder="Type coupon's code"/>
        <br/>
        <input type="button" value="Find" onClick="getCouponByCode()"/>
    </div>
    </td>

    <td>
    <div class="mainArea">
        <h3>Search coupon by ID:</h3>
        <input type="text" id="findByIdField" name="findByIdField" placeholder="Type coupon's ID"/>
        <br/>
        <input type="button" value="Find" onClick="getCouponById()"/>
    </div>
    </td>
    </tr>
    </table>

    <hr>

</div>
