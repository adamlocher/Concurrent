<%@ include file="tags.jsp"%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script> 
$(document).ready(function(){
    $("#flip").click(function(){
        $("#panel").slideToggle("slow");
    });
});

</script>

<style> 
#panel, #flip {
    padding: 5px;
    text-align: center;
    background-color: #e5eecc;
    border: solid 1px #c3c3c3;
}

#panel {
    padding: 50px;
    display: none;
    

  .carousel-inner > .item > img,
  .carousel-inner > .item > a > img {
      width: 95%;
      margin: auto;
  }
   
}
</style>

 

<div class="container">
 <div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
   		  <li data-target="#myCarousel" data-slide-to="0" class="active"></li> 
    	<s:iterator begin="0" end="userProjects.size-1" var="i">
      		<li data-target="#myCarousel" data-slide-to="${i}"></li>
      	 </s:iterator>
    </ol>
    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
     
       <div class="item active">
        <img src="http://www.rsp.ac.uk/images/uploaded/stats.jpg" alt="Chania" width="810" >
        <div class="carousel-caption">
          <h3 style="color: red;">PROJECT STATISTICS</h3>
         <!--  <p>The atmosphere in Chania has a touch of Florence and Venice.</p> -->
        </div>
      </div> 
       <s:iterator value="userProjects">
      	 <div class="item">
	        <img src="chart?id=${id}" alt="${name}" width="1200" height="540">
	        <div class="carousel-caption">
	         <!--  <h3>Chania</h3> -->
	         <%--  <p>${name}</p> --%>
	        </div>
	      </div>
       </s:iterator>
     </div>
      <!-- Left and right controls -->
    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
   </div>
  </div>  
    


