<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<script type="text/javascript">
   var HOST = "http://localhost:8080"
   var MOVIES_URL = HOST + "/movies";
   var MOVIE_URL = HOST + "/movies/movie";
   var MOVIE_TITLE_URL = HOST + "/movies/movie/title/";
   var MOVIES_CATEGORY_URL = HOST + "/movies/category/";
   var ORDER_URL = HOST + "/orders/";

   $.dto = null;

   findAll();

   $(document).ready(function () {
       findAll();
   });


   <sec:authorize access="hasRole('ROLE_ADMIN')">
   $(document).on("click", "a", function() {
       var action = $(this).text();
       var selectedMovieId = $(this).data("id");
       if (action == "Edit" || action != "Delete") {
           $.each(dto, function (index) {
               if (dto[index].movieId == selectedMovieId) {
                   $("#movieIdUpdateField").val(selectedMovieId);
                   $("#titleUpdateField").val(dto[index].title);
                   $("#releaseDateUpdateField").val(dto[index].releaseDate);
                   $("#categoryIdUpdateField").val(dto[index].categoryId);
                   $("#ratingUpdateField").val(dto[index].rating);
                   $("#priceUpdateField").val(dto[index].price);
               }
           });
       } else {
           deleteMovie(selectedMovieId);
       }
   });
   </sec:authorize>


   <sec:authorize access="hasRole('ROLE_CLIENT')">
   $(document).on("click", "a", function() {
       var action = $(this).text();
       var selectedMovieId = $(this).data("id");
       if (action == "Order") {
           orderMovie(selectedMovieId);
       }
   });
   </sec:authorize>


   function findAll() {
       console.log('findAll');
       $.ajax({
           type: 'GET',
           url: MOVIES_URL,
           dataType: "json", // data type of response
           success: renderList,
           error: function(jqXHR, textStatus, errorThrown) {
                       console.log(jqXHR, textStatus, errorThrown);
                       alert('findAll: ' + textStatus);
                  }
       });
   }


        <sec:authorize access="hasRole('ROLE_CLIENT')">
        function orderMovie(movieId) {
            var username = $('#client_username').val();
            $.ajax({
                async: false,
                type: 'POST',
                contentType: 'application/json',
                url: ORDER_URL + username + '/movie/' + movieId,
                success: function(){
                            alert('The movie has been successfully ordered.');
                         },
                error: function(jqXHR, textStatus, errorThrown) {
                            alert('orderMovie error: ' + errorThrown);
                       }
            });
        }
        </sec:authorize>


           function renderList(data) {
               dto = data == null ? [] : (data instanceof Array ? data : [data]);
               $('#moviesTable tr').remove();
               $.each(dto, function (index, movie) {
                   drawRow(movie);
               });
           }

           function formatDate(inputStr) {
               		var timestamp = parseInt(inputStr, 10);
               		var date = new Date(timestamp);
               		return date.toISOString().substr(0, 10);
           }

           function drawRow(movie) {
               var row = $("<tr />")
               $("#moviesTable").append(row);
               row.append($("<td hidden>" + movie.movieId + '</td>'));
               row.append($("<td>" + movie.title + "</td>"));
               row.append($("<td>" + formatDate(movie.releaseDate) + "</td>"));
               row.append($("<td hidden>" + movie.categoryId + "</td>"));
               row.append($("<td>" + movie.categoryTitle + "</td>"));
               row.append($("<td>" + movie.rating + "/10</td>"));
               row.append($("<td>" + movie.price + "</td>"));

               <sec:authorize access="hasRole('ROLE_ADMIN')">
                   row.append($("<td>" + '<a href="#" data-id="' + movie.movieId + '">Edit</a></td>'));
                   row.append($("<td>" + '<a href="#" data-id="' + movie.movieId + '">Delete</a></td>'));
               </sec:authorize>

               <sec:authorize access="hasRole('ROLE_CLIENT')">
                   row.append($("<td>" + '<a href="#" data-id="' + movie.movieId + '">Order</a></td>'));
                   row.append($("<td></td>"));
               </sec:authorize>
           }


<sec:authorize access="hasRole('ROLE_ADMIN')">

   function clean() {
       $("#movieId").val("");
       $("#title").val("");
       $("#releaseDate").val("");
       $("#rating").val("");
       $("#price").val("");
       $("#categoryId").val("");
   }

   function cleanUpdateFields() {
       $("#movieIdUpdateField").val("");
       $("#titleUpdateField").val("");
       $("#releaseDateUpdateField").val("");
       $("#ratingUpdateField").val("");
       $("#priceUpdateField").val("");
       $("#categoryIdUpdateField").val("");
   }


   function addMovie() {
   console.log('addMovie');
       $.ajax({
           type: 'POST',
               contentType: 'application/json',
           url: MOVIES_URL,
           dataType: "json",
           data: JSON.stringify({"movieId" : null,
                  "title" : $('#title').val(),
                  "releaseDate" : $('#releaseDate').val(),
                  "rating" : $('#rating').val(),
                  "price" : $('#price').val(),
                  "categoryId" : $('#categoryId').val()}),
           success: function (data, textStatus, jqXHR) {
               alert('Movie has been created successfully.');
               console.log('Movie has been created successfully.');
               findAll();
               clean();
           },
           error: function (jqXHR, textStatus, errorThrown) {
               findAll();
               clean();
           }
       });
   }

   function updateMovie() {
       console.log('updateMovie');
       $.ajax({
           type: 'PUT',
           contentType: 'application/json',
           url: MOVIE_URL,
           data: JSON.stringify({"movieId" : $('#movieIdUpdateField').val(),
                                 "title" : $('#titleUpdateField').val(),
                                 "releaseDate" : $('#releaseDateUpdateField').val(),
                                 "rating" : $('#ratingUpdateField').val(),
                                 "price" : $('#priceUpdateField').val(),
                                 "categoryId" : $('#categoryIdUpdateField').val()}),
           success: function (data, textStatus, jqXHR) {
               alert('Movie has been updated successfully.');
               console.log('Movie has been updated successfully.');
               findAll();
           },
           error: function (jqXHR, textStatus, errorThrown) {
               alert('updateMovie error: ' + errorThrown);
               findAll();
           }
       });
   }

   function deleteMovie(movieId) {
       console.log('deleteMovie');
       $.ajax({
           type: 'DELETE',
           contentType: 'application/json',
           url: MOVIE_URL + '/' + movieId,
           success: function (data, textStatus, jqXHR) {
               alert('Movie has been deleted successfully.');
               findAll();
           },
           error: function (jqXHR, textStatus, errorThrown) {
               alert('deleteMovie error: ' + errorThrown);
           }
       });
   }
</sec:authorize>


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
                url: MOVIES_CATEGORY_URL + categoryId + "/movies",
                success: renderList,
                error: function(jqXHR, textStatus, errorThrown) {
                                console.log(jqXHR, textStatus, errorThrown);
                                alert('error getMoviesByCategoryId: ' + errorThrown);
                       }
            });
   }

   <sec:authorize access="hasRole('ROLE_ADMIN')">
   function getMovieById() {
            $.ajax({
                async: false,
                type: 'GET',
                contentType: 'application/json',
                url: MOVIE_URL + "/" + $('#findByIdField').val(),
                success: renderList,
                error: function(jqXHR, textStatus, errorThrown) {
                             alert('getMovieById error: ' + errorThrown);
                       }
            });
   }
   </sec:authorize>

   function getMovieByTitle() {
            $.ajax({
                async: false,
                type: 'GET',
                contentType: 'application/json',
                url: MOVIE_TITLE_URL + $('#findByTitleField').val(),
                success: renderList,
                error: function(jqXHR, textStatus, errorThrown) {
                             alert('getMovieByTitle error: ' + errorThrown);
                       }
            })
   }
</script>



<div class="container">

    <h2>Movies</h2>

    <div class="table-responsive">
      <table class="table table-striped">
      <thead>
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
        </thead>
        <tbody id="moviesTable">
        <tr>
            <td>test</td>
            <td>test</td>
            <td>test</td>
        </tr>
        </tbody>
      </table>
    </div>

    <sec:authorize access="hasRole('ROLE_ADMIN')">
    <div class="mainArea">
        <h3>Add movie</h3>
            <input type="text" id="title" name="title" placeholder="Type a title"/>
            <input type="text" id="releaseDate" name="releaseDate" placeholder="Type a release date"/>
            <input type="text" id="categoryId" name="categoryId" placeholder="Type a category's ID"/>
            <input type="text" id="rating" name="rating" placeholder="Type a rating"/>
            <input type="text" id="price" name="price" placeholder="Type a price"/>
            <br/>
            <button id="btnSave" onClick="addMovie()">Save</button>
            <button id="btnClean" onClick="clean()">Clean</button>
    </div>
    <hr>

    <div class="mainArea">
        <h3>Update movie</h3>
            <input type="text" hidden id="movieIdUpdateField" name="movieIdUpdateField" placeholder="Movie's ID"/>
            <input type="text" id="titleUpdateField" name="titleUpdateField" placeholder="Type a title"/>
            <input type="text" id="releaseDateUpdateField" name="releaseDateUpdateField" placeholder="Type a release date"/>
            <input type="text" id="categoryIdUpdateField" name="categoryIdUpdateField" placeholder="Type a category's ID"/>
            <input type="text" id="ratingUpdateField" name="ratingUpdateField" placeholder="Type a rating"/>
            <input type="text" id="priceUpdateField" name="priceUpdateField" placeholder="Type a price"/>
            <br/>
            <button id="btnUpdate" onClick="updateMovie()">Save</button>
            <button id="btnCleanUpdateFields" onClick="cleanUpdateFields()">Clean</button>
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

    <sec:authorize access="hasRole('ROLE_ADMIN')">
    <td>
    <div class="mainArea">
        <h3>Search movie by ID:</h3>
        <input type="text" id="findByIdField" name="findByIdField" placeholder="Type movie's ID"/>
        <br/>
        <input type="button" value="Find" onClick="getMovieById()"/>
    </div>
    </td>
    </sec:authorize>

    </tr>
    </table>
    <hr>

</div>
