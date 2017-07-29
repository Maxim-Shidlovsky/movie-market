<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>


    <script type="text/javascript">
        var CATEGORIES_URL = "http://localhost:8080/categories";
        var CATEGORY_URL = "http://localhost:8080/categories/category";
        var CATEGORY_TITLE_URL = "http://localhost:8080/categories/category/title/";

   $.dto = null;

   findAll();

   $(document).ready(function () {
       findAll();
   });

   $(document).on("click", "a", function() {
       var action = $(this).text();
       var selectedCategoryId = $(this).data("id");
       if (action == "Edit" || action != "Delete") {
           $.each(dto, function (index) {
               if (dto[index].categoryId == selectedCategoryId) {
                   $("#fieldUpdateCategoryId").val(selectedCategoryId);
                   $("#fieldUpdateTitle").val(dto[index].title);
               }
           });
       } else {
           deleteCategory(selectedCategoryId);
       }
   });


        function clean() {
            $("#title").val("");
        }

        function clearUpdateFields() {
            $("#fieldUpdateCategoryId").val("");
            $("#fieldUpdateTitle").val("");
        }


   function findAll() {
       console.log('findAll');
       $.ajax({
           type: 'GET',
           url: CATEGORIES_URL,
           dataType: "json", // data type of response
           success: renderList,
           error: function(jqXHR, textStatus, errorThrown) {
                       console.log(jqXHR, textStatus, errorThrown);
                       alert('findAll: ' + textStatus);
                  }
       });
   }

           function renderList(data) {
               dto = data == null ? [] : (data instanceof Array ? data : [data]);
               $('#categoriesTable tr').remove();
               $.each(dto, function (index, category) {
                   drawRow(category);
               });
           }

           function drawRow(category) {
               var row = $("<tr />")
               $("#categoriesTable").append(row);
               row.append($("<td hidden>" + category.categoryId + '</td>'));
               row.append($("<td>" + category.title + "</td>"));
               row.append($("<td>" + category.amount + "</td>"));
               row.append($("<td>" + '<a href="#" data-id="' + category.categoryId + '">Edit</a></td>'));
               row.append($("<td>" + '<a href="#" data-id="' + category.categoryId + '">Delete</a></td>'));
           }


   function addCategory() {
   console.log('addCategory');
       $.ajax({
           type: 'POST',
           contentType: 'application/json',
           url: CATEGORIES_URL,
           dataType: "json",
           data: JSON.stringify({"categoryId" : null,
                  "title" : $('#title').val()}),
           success: function (data, textStatus, jqXHR) {
               alert('Category has been created successfully.');
               console.log('Category has been created successfully.');
               findAll();
               clean();
           },
           error: function (jqXHR, textStatus, errorThrown) {
               findAll();
               clean();
           }
       });
   }

        function deleteCategory(categoryId) {
            $.ajax({
                type: 'DELETE',
                contentType: 'application/json',
                url: CATEGORY_URL + "/" + categoryId,
                success: function(){
                            alert('The category has been successfully deleted.');
                            findAll();
                      },
                error: function(jqXHR, textStatus, errorThrown) {
                         alert('deleteCategory error: ' + errorThrown);
                      }
            });
        }


   function updateCategory() {
       console.log('updateCategory');
       $.ajax({
           type: 'PUT',
           contentType: 'application/json',
           url: CATEGORY_URL,
           data: JSON.stringify({"categoryId" : $('#fieldUpdateCategoryId').val(),
                                 "title" : $('#fieldUpdateTitle').val()}),
           success: function (data, textStatus, jqXHR) {
               alert('Category has been updated successfully.');
               console.log('Category has been updated successfully.');
               findAll();
           },
           error: function (jqXHR, textStatus, errorThrown) {
               alert('updateCategory error: ' + errorThrown);
               findAll();
           }
       });
   }

        function getCategoryById() {
            $.ajax({
                async: false,
                type: 'GET',
                contentType: 'application/json',
                url: CATEGORY_URL + "/" + $('#findByIdField').val(),
                success: renderList,
                error: function(jqXHR, textStatus, errorThrown) {
                             alert('getCategoryById error: ' + errorThrown);
                       }
            });
        }

        function getCategoryByTitle() {
            $.ajax({
                async: false,
                type: 'GET',
                contentType: 'application/json',
                url: CATEGORY_TITLE_URL + $('#findByTitleField').val(),
                success: renderList,
                error: function(jqXHR, textStatus, errorThrown) {
                             alert('getMovieByTitle error: ' + errorThrown);
                       }
            });
        }
    </script>


<div class="container">

    <h2>Categories</h2>

    <div class="table-responsive">
      <table class="table table-striped">
      <thead>
        <tr>
            <th hidden>ID</th>
            <th>Title</th>
            <th>Amount</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
                <tbody id="categoriesTable">
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
        <h3>Add category</h3>
            <input type="text" hidden id="categoryId" name="categoryId" placeholder="Category's ID"/>
            <input type="text" id="title" name="title" placeholder="Type a title"/>
            <br/>
            <button id="btnSave" onClick="addCategory()">Save</button>
            <button id="btnClean" onClick="clean()">Clean</button>
    </div>
    </td>

    <td>
    <div class="mainArea">
        <h3>Update category</h3>
        <input type="text" hidden id="fieldUpdateCategoryId" name="fieldUpdateCategoryId" placeholder="Category's ID"/>
        <input type="text" id="fieldUpdateTitle" name="fieldUpdateTitle" placeholder="Type a title"/>
        <br/>
        <button id="btnUpdate" onClick="updateCategory()">Save</button>
        <button id="btnCleanUpdateFields" noClick="clearUpdateFields()">Clean</button>
    </div>
    </td>
    </tr>
    </table>

    <hr>

    <table width=100%>
    <tr>
    <td>
    <div class="mainArea">
        <h3>Search category by title:</h3>
        <input type="text" id="findByTitleField" name="findByTitleField" placeholder="Type category's title"/>
        <br/>
        <input type="button" value="Find" onClick="getCategoryByTitle()"/>
    </div>
    </td>

    <td>
    <div class="mainArea">
        <h3>Search category by ID:</h3>
        <input type="text" id="findByIdField" name="findByIdField" placeholder="Type category's ID"/>
        <br/>
        <input type="button" value="Find" onClick="getCategoryById()"/>
    </div>
    </td>
    </tr>
    </table>

    <hr>

</div>
