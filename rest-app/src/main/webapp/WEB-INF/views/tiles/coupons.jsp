<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>


    <script type="text/javascript">
        var COUPONS_URL = "http://localhost:8080/coupons";
        var COUPON_URL = "http://localhost:8080/coupons/coupon";
        var COUPON_CODE_URL = "http://localhost:8080/coupons/coupon/code/";


        $.dto = null;

        findAll();

           $(document).ready(function () {
               findAll();
           });

        function clean() {
            $("#code").val("");
            $("#discount").val("");
        }

        function clearUpdateFields() {
            $("#fieldUpdateCouponId").val("");
            $("#fieldUpdateCode").val("");
            $("#fieldUpdateDiscount").val("");
            $("#fieldUpdateReceivingDate").val("");
        }


   $(document).on("click", "a", function() {
       var action = $(this).text();
       var selectedCouponId = $(this).data("id");
       if (action == "Edit" || action != "Delete") {
           $.each(dto, function (index) {
               if (dto[index].couponId == selectedCouponId) {
                   $("#fieldUpdateCouponId").val(selectedCouponId);
                   $("#fieldUpdateCode").val(dto[index].code);
                   $("#fieldUpdateDiscount").val(dto[index].discount);
                   $("#fieldUpdateReceivingDate").val(dto[index].receivingDate);
               }
           });
       } else {
           deleteCoupon(selectedCouponId);
       }
   });


   function findAll() {
       console.log('findAll');
       $.ajax({
           type: 'GET',
           url: COUPONS_URL,
           dataType: "json",
           success: renderList,
           error: function(jqXHR, textStatus, errorThrown) {
                       console.log(jqXHR, textStatus, errorThrown);
                       alert('findAll: ' + textStatus);
                  }
       });
   }


           function renderList(data) {
               dto = data == null ? [] : (data instanceof Array ? data : [data]);
               $('#couponsTable tr').remove();
               $.each(dto, function (index, coupon) {
                   drawRow(coupon);
               });
           }

           function drawRow(coupon) {
               var row = $("<tr />")
               $("#couponsTable").append(row);
               row.append($("<td hidden>" + coupon.couponId + '</td>'));
               row.append($("<td>" + coupon.code + "</td>"));
               row.append($("<td>" + coupon.discount + "</td>"));
               row.append($("<td>" + coupon.receivingDate + "</td>"));
               row.append($("<td>" + '<a href="#" data-id="' + coupon.couponId + '">Edit</a></td>'));
               row.append($("<td>" + '<a href="#" data-id="' + coupon.couponId + '">Delete</a></td>'));
           }



   function addCoupon() {
   console.log('addCoupon');
       $.ajax({
           type: 'POST',
           contentType: 'application/json',
           url: COUPONS_URL,
           dataType: "json",
           data: JSON.stringify({"couponId" : null,
                  "code" : $('#code').val(),
                  "discount" : $('#discount').val(),
                  "receivingDate" : null}),
           success: function (data, textStatus, jqXHR) {
               alert('Coupon has been created successfully.');
               console.log('Coupon has been created successfully.');
               findAll();
               clean();
           },
           error: function (jqXHR, textStatus, errorThrown) {
               findAll();
               clean();
           }
       });
   }


        function deleteCoupon(couponId) {
            $.ajax({
                async: false,
                type: 'DELETE',
                contentType: 'application/json',
                url: COUPON_URL + '/' + couponId,
                success: function(){
                            alert('The coupon has been successfully deleted.');
                            findAll();
                      },
                error: function(jqXHR, textStatus, errorThrown) {
                         alert('deleteCoupon error: ' + errorThrown);
                      }
            });
        }


        function updateCoupon() {
            console.log('updateCoupon');
            $.ajax({
                type: 'PUT',
                contentType: 'application/json',
                url: COUPON_URL,
                data: JSON.stringify({"couponId" : $('#fieldUpdateCouponId').val(),
                                      "code" : $('#fieldUpdateCode').val(),
                                      "discount" : $('#fieldUpdateDiscount').val(),
                                      "receivingDate" : $('#fieldUpdateReceivingDate').val()}),
                success: function (data, textStatus, jqXHR) {
                    alert('Coupon has been updated successfully.');
                    console.log('Coupon has been updated successfully.');
                    findAll();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert('updateCoupon error: ' + errorThrown);
                    findAll();
                }
            });
        }

        function getCouponById() {
            $.ajax({
                async: false,
                type: 'GET',
                contentType: 'application/json',
                url: COUPON_URL + "/" + $('#findByIdField').val(),
                success: renderList,
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
                url: COUPON_CODE_URL + $('#findByCodeField').val(),
                success: renderList,
                error: function(jqXHR, textStatus, errorThrown) {
                             alert('getCouponByCode error: ' + errorThrown);
                       }
            });
        }
    </script>


<div class="container">

    <h2>Coupons</h2>

    <div class="table-responsive">
      <table class="table table-striped">
      <thead>
        <tr>
            <th hidden>ID</th>
            <th>Code</th>
            <th>Discount</th>
            <th>Activation date</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
                <tbody id="couponsTable">
                <tr>
                    <td>test</td>
                    <td>test</td>
                    <td>test</td>
                </tr>
                </tbody>
      </table>
    </div>


    <table width=100%>
    <tr>
    <td>
    <div class="mainArea">
        <h3>Add coupon</h3>
            <input type="text" id="code" name="code" placeholder="Type a code"/>
            <input type="text" id="discount" name="discount" placeholder="Type a discount"/>
            <br/>
            <button id="btnSave" onClick="addCoupon()">Save</button>
            <button id="btnClean" onClick="clean()">Clean</button>
    </div>
    </td>

    <td>
    <div class="mainArea">
        <h3>Update coupon</h3>
        <input type="text" hidden id="fieldUpdateCouponId" name="fieldUpdateCouponId" placeholder="Coupon's ID"/>
        <input type="text" id="fieldUpdateCode" name="fieldUpdateCode" placeholder="Type a code"/>
        <input type="text" id="fieldUpdateDiscount" name="fieldUpdateDiscount" placeholder="Type a discount"/>
        <input type="text" id="fieldUpdateReceivingDate" name="fieldUpdateReceivingDate" placeholder="Type a receivingDate"/>
        <br/>
        <button id="btnUpdate" onClick="updateCoupon()">Save</button>
        <button id="btnClearUpdateFields" noClick="clearUpdateFields()">Clean</button>
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
