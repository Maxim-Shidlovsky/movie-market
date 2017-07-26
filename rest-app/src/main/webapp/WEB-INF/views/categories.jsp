<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>


    <script type="text/javascript">
        function clean() {
            $('#categoryId').val('');
            $('#title').val('');
        }

        function clearUpdateFields() {
            $('#fieldUpdateCategoryId').val('');
            $('#fieldUpdateTitle').val('');
        }

        function deleteCategory(categoryId) {
            $.ajax({
                async: false,
                type: 'DELETE',
                contentType: 'application/json',
                url: 'http://localhost:8080/category/' + categoryId,
                success: function(){
                            alert('The category has been successfully deleted.');
                      },
                error: function(jqXHR, textStatus, errorThrown) {
                         alert('deleteCategory error: ' + errorThrown);
                      }
            });
        }

        function editCategory(categoryId, title) {
            $('#fieldUpdateCategoryId').val(categoryId);
            $('#fieldUpdateTitle').val(title);
        }

        function updateCategory() {
            var categoryId = $('#fieldUpdateCategoryId').val();
            var title = $('#fieldUpdateTitle').val();

            $.ajax({
                async: false,
                type: 'PUT',
                contentType: 'application/json',
                url: 'http://localhost:8080/category?categoryId=' + categoryId + '&title=' + title,
                success: function(){
                                alert('The category has been successfully updated.');
                                location.reload();
                         },
                error: function(jqXHR, textStatus, errorThrown) {
                                alert('updateCategory error: ' + errorThrown);
                       }
            });
        }

        function getCategoryById() {
            $.ajax({
                async: false,
                type: 'GET',
                contentType: 'application/json',
                url: 'http://localhost:8080/category/' + $('#findByIdField').val(),
                success: function(){
                             alert('The category has been successfully found.');
                             location.href = 'http://localhost:8080/category/' + $('#findByIdField').val();
                         },
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
                url: 'http://localhost:8080/category/title/' + $('#findByTitleField').val(),
                success: function(){
                            alert('The category has been successfully found.');
                            location.href = 'http://localhost:8080/category/title/' + $('#findByTitleField').val();
                         },
                error: function(jqXHR, textStatus, errorThrown) {
                             alert('getMovieByTitle error: ' + errorThrown);
                       }
            });
        }
    </script>


<div class="container">

    <h2>Categories</h2>

    <div class="table-responsive">
      <table class="table table-striped" id="categoriesTable">
        <tr>
            <th hidden>ID</th>
            <th>Title</th>
            <th>Amount</th>
            <th></th>
            <th></th>
        </tr>
        <c:forEach items="${categoryDTOList}" var="categoryDTO">
        <tr>
          <td hidden>
            <c:out value="${categoryDTO.categoryId}" />
          </td>
          <td>
            <c:out value="${categoryDTO.title}" />
          </td>
          <td>
            <c:out value="${categoryDTO.amount}" />
          </td>
          <td>
            <input type="button" onClick="editCategory(${categoryDTO.categoryId}, '${categoryDTO.title}', ${categoryDTO.amount})" value="Edit">
          </td>
          <td>
            <a href="" onClick="deleteCategory(${categoryDTO.categoryId})">Delete</a>
          </td>
        </tr>
        </c:forEach>
      </table>
    </div>


    <table width=100%>
    <tr>
    <td>
    <div class="mainArea">
        <h3>Add category</h3>
        <form method="post" action="/category">
            <input type="text" hidden id="categoryId" name="categoryId" placeholder="Category's ID"/>
            <input type="text" id="title" name="title" placeholder="Type a title"/>
            <br/>
            <input type="submit" value="Send" />
            <input type="button" value="Clear" noClick="clear()" />
        </form>
    </div>
    </td>

    <td>
    <div class="mainArea">
        <h3>Update category</h3>
        <input type="text" hidden id="fieldUpdateCategoryId" name="categoryId" placeholder="Category's ID"/>
        <input type="text" id="fieldUpdateTitle" name="title" placeholder="Type a title"/>
        <br/>
        <input type="button" value="Send" onClick="updateCategory()"/>
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
