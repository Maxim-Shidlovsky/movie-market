<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


    <script type="text/javascript">
        function clean() {
            $('#movieId').val('');
            $('#title').val('');
            $('#releaseDate').val('');
            $('#categoryId').val('');
            $('#rating').val('');
            $('#price').val('');
        }

        function clearUpdateFields() {
            $('#fieldUpdateMovieId').val('');
            $('#fieldUpdateTitle').val('');
            $('#fieldUpdateReleaseDate').val('');
            $('#fieldUpdateCategoryId').val('');
            $('#fieldUpdateRating').val('');
            $('#fieldUpdatePrice').val('');
        }

        function deleteMovie(movieId) {
            $.ajax({
                async: false,
                type: 'DELETE',
                contentType: 'application/json',
                url: 'http://localhost:8080/movie/' + movieId,
                success: function(){
                            alert('The movie has been successfully deleted.');
                      },
                error: function(jqXHR, textStatus, errorThrown) {
                         alert('deleteMovie error: ' + errorThrown);
                      }
            });
        }

        function editMovie(movieId, title, releaseDate, categoryId, rating, price) {
            $('#fieldUpdateMovieId').val(movieId);
            $('#fieldUpdateTitle').val(title);
            $('#fieldUpdateReleaseDate').val(releaseDate);
            $('#fieldUpdateCategoryId').val(categoryId);
            $('#fieldUpdateRating').val(rating);
            $('#fieldUpdatePrice').val(price);
        }

        function updateMovie() {
            var movieId = $('#fieldUpdateMovieId').val();
            var title = $('#fieldUpdateTitle').val();
            var releaseDate = $('#fieldUpdateReleaseDate').val();
            var rating = $('#fieldUpdateRating').val();
            var price = $('#fieldUpdatePrice').val();
            var categoryId = $('#fieldUpdateCategoryId').val();

            $.ajax({
                async: false,
                type: 'PUT',
                contentType: 'application/json',
                url: 'http://localhost:8080/movie?movieId=' + movieId + '&title=' + title + '&releaseDate=' + releaseDate + '&rating=' + rating + '&price=' + price + '&categoryId=' + categoryId,
                success: function(){
                                alert('The movie has been successfully updated.');
                                location.reload();
                         },
                error: function(jqXHR, textStatus, errorThrown) {
                                alert('updateMovie error: ' + errorThrown);
                       }
            });
        }

        function getMoviesByCategoryId() {
            var categoryTitle = $('#findByCategoryField').val();
            var categoryId;
            var moviesTable = document.getElementById('moviesTable');
            var elements = moviesTable.getElementsByTagName('tr');
            var moviesLength = elements.length;
            for (var i = 1; i <= moviesLength; i++) {
                if (categoryTitle == moviesTable.rows[i].cells[4].innerText) {
                    categoryId = moviesTable.rows[i].cells[3].innerText;
                    break;
                }
            }
            categoryId = categoryId.replace(/\s+/g,'');
            $.ajax({
                async: false,
                type: 'GET',
                contentType: 'application/json',
                url: "http://localhost:8080/category/" + categoryId + "/movies",
                success: function() {
                                alert('Movies have been found.');
                                location.href = "http://localhost:8080/category/" + categoryId + "/movies";
                         },
                error: function(jqXHR, textStatus, errorThrown) {
                                console.log(jqXHR, textStatus, errorThrown);
                                alert('error getCarsByProducersId: ' + errorThrown);
                       }
            });
        }

        function getMovieById() {
            $.ajax({
                async: false,
                type: 'GET',
                contentType: 'application/json',
                url: 'http://localhost:8080/movie/' + $('#findByIdField').val(),
                success: function(){
                             alert('The movie has been successfully found.');
                             location.href = 'http://localhost:8080/movie/' + $('#findByIdField').val();
                         },
                error: function(jqXHR, textStatus, errorThrown) {
                             alert('getMovieById error: ' + errorThrown);
                       }
            });
        }

        function getMovieByTitle() {
            $.ajax({
                async: false,
                type: 'GET',
                contentType: 'application/json',
                url: 'http://localhost:8080/movie/title/' + $('#findByTitleField').val(),
                success: function(){
                            alert('The movie has been successfully found.');
                            location.href = 'http://localhost:8080/movie/title/' + $('#findByTitleField').val();
                         },
                error: function(jqXHR, textStatus, errorThrown) {
                             alert('getMovieByTitle error: ' + errorThrown);
                       }
            });
        }

        function orderMovie(movieId) {
            $.ajax({
                async: false,
                type: 'PUT',
                contentType: 'application/json',
                url: 'http://localhost:8080/order?clientId=' + $() + '&movieId=' + movieId,
                success: function(){
                            alert('The movie has been successfully ordered.');
                            location.reload();
                         },
                error: function(jqXHR, textStatus, errorThrown) {
                            alert('updateMovie error: ' + errorThrown);
                       }
            });
        }
    </script>


<div class="container">

    <h2>Movies</h2>

    <div class="table-responsive">
      <table class="table table-striped" id="moviesTable">
        <tr>
            <th hidden>ID</th>
            <th>Title</th>
            <th>Release date</th>
            <th hidden>Category ID</th>
            <th>Category</th>
            <th>Rating</th>
            <th>Price</th>
            <th></th>
            <th></th>
        </tr>
        <c:forEach items="${movieDTOList}" var="movieDTO">
        <tr>
          <td hidden>
            <c:out value="${movieDTO.movieId}" />
          </td>
          <td>
            <c:out value="${movieDTO.title}" />
          </td>
          <td>
            <c:out value="${movieDTO.releaseDate}" />
          </td>
          <td hidden>
            <c:out value="${movieDTO.categoryId}" />
          </td>
          <td>
            <c:out value="${movieDTO.categoryTitle}" />
          </td>
          <td>
            <c:out value="${movieDTO.rating}" />/10
          </td>
          <td>
            <c:out value="${movieDTO.price}" />
          </td>

          <sec:authorize access="hasRole('ROLE_CLIENT')">
          <td>
            <a href="" onClick="orderMovie(${movieDTO.movieId})">Order</a>
          </td>
          </sec:authorize>

          <sec:authorize access="hasRole('ROLE_ADMIN')">
          <td>
            <input type="button" onClick="editMovie(${movieDTO.movieId}, '${movieDTO.title}', '${movieDTO.releaseDate}', ${movieDTO.categoryId}, ${movieDTO.rating}, ${movieDTO.price})" value="Edit">
          </td>
          <td>
            <a href="" onClick="deleteMovie(${movieDTO.movieId})">Delete</a>
          </td>
          </sec:authorize>

        </tr>
        </c:forEach>
      </table>
    </div>


    <sec:authorize access="hasRole('ROLE_ADMIN')">
    <div class="mainArea">
        <h3>Add movie</h3>
        <form method="post" action="/movie">
            <input type="text" hidden id="movieId" name="movieId" placeholder="Movie's ID"/>
            <input type="text" id="title" name="title" placeholder="Type a title"/>
            <input type="text" id="releaseDate" name="releaseDate" placeholder="Type a release date"/>
            <input type="text" id="categoryId" name="categoryId" placeholder="Type a category's ID"/>
            <input type="text" id="rating" name="rating" placeholder="Type a rating"/>
            <input type="text" id="price" name="price" placeholder="Type a price"/>
            <br/>
            <input type="submit" value="Send" />
            <input type="button" value="Clear" noClick="clear()" />
        </form>
    </div>

    <hr>
    <div class="mainArea">
        <h3>Update movie</h3>
        <input type="text" hidden id="fieldUpdateMovieId" name="movieId" placeholder="Movie's ID"/>
        <input type="text" id="fieldUpdateTitle" name="title" placeholder="Type a title"/>
        <input type="text" id="fieldUpdateReleaseDate" name="releaseDate" placeholder="Type a release date"/>
        <input type="text" id="fieldUpdateCategoryId" name="categoryId" placeholder="Type a category's ID"/>
        <input type="text" id="fieldUpdateRating" name="rating" placeholder="Type a rating"/>
        <input type="text" id="fieldUpdatePrice" name="price" placeholder="Type a price"/>
        <br/>
        <input type="button" value="Send" onClick="updateMovie()"/>
        <input type="button" value="Clear" noClick="clearUpdateFields()" />
    </div>
    </sec:authorize>

    <hr>
    <table width=100%>
    <tr>
    <td>
    <div class="mainArea">
        <h3>Sort movies by category:</h3>
        <input type="text" id="findByCategoryField" name="findByCategoryField" placeholder="Type category"/>
        <br/>
        <input type="button" value="Send" onClick="getMoviesByCategoryId()"/>
    </div>
    </td>

    <td>
    <div class="mainArea">
        <h3>Search movie by title:</h3>
        <input type="text" id="findByTitleField" name="findByTitleField" placeholder="Type movie's title"/>
        <br/>
        <input type="button" value="Find" onClick="getMovieByTitle()"/>
    </div>
    </td>

    <td>
    <div class="mainArea">
        <h3>Search movie by ID:</h3>
        <input type="text" id="findByIdField" name="findByIdField" placeholder="Type movie's ID"/>
        <br/>
        <input type="button" value="Find" onClick="getMovieById()"/>
    </div>
    </td>
    </tr>
    </table>
    <hr>

</div>
