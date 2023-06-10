<%
	int totalPages = (int) request.getAttribute("totalPages") ;
	int currentPage = (int) request.getAttribute("currentPage");	
	int nbElement = (int) request.getAttribute("nbElement");
	String motCle = String.valueOf(request.getAttribute("motCle"));
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Les voitures</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="css/fontawesome.min.css"/>
<link rel="stylesheet" type="text/css" href="css/all.min.css"/>
</head>
<body>
<%@include file="../header.jsp" %>
<div class="mx-5 mt-5 pt-3">
	<h3>Les Voitures</h3>
	
	<div class="mt-1">
		<a href="nouveau.voiture" class="btn btn-primary">Nouveau</a>	
		<form class="form-inline d-flex justify-content-between my-2" action="voiture" method="get" id="formNbElement">
			<div class="form-group">
				<select name="nbElement" class="custom-select" onChange="soummettreFormulaire()">
					<option value="3" <% if(nbElement == 3) {%> <%= "selected" %> <%} %> >3</option>
					<option value="5" <% if(nbElement == 5) {%> <%= "selected" %> <%} %> >5</option>
					<option value="10" <% if(nbElement == 10) {%> <%= "selected" %> <%} %> >10</option>
					<option value="25" <% if(nbElement == 25) {%> <%= "selected" %> <%} %> >25</option>
				</select>			
				<label for="nbElement" class="form-label ml-2">Affichage </label>
			</div>
			<div class="form-group">
				<input type="text" name="motCle" class="form-control" placeholder="Entrer votre recherche" value="${motCle }"/>				
			<button class="btn btn-sm btn-primary ml-1">rechercher</button>
			</div>
		</form>
	</div>
	<table class="table table-striped table-bordered">
		<thead>
			<tr>
				<th>ID</th>
				<th>Illustration</th>
				<th>Marque</th>
				<th>Modele</th>
				<th>Couleur</th>
				<th>Année</th>
				<th>Prix Journalière</th>
				<th>Status</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
				<c:forEach items="${voitures}" var="v" >
					<tr>
						<td>${v.id}</td>
						<td>
							<img alt="illustration" src="uploads/${v.illustration }" class="rounded-3" width="100px">
						</td>
						<td>${v.marque}</td>
						<td>${v.modele}</td>
						<td>${v.couleur}</td>
						<td>${v.annee}</td>
						<td>${v.prixJournalier}</td>
						<td>
							<c:choose>
								<c:when test="${v.status }">
									<span class="badge badge-success">disponible</span>
								</c:when>
								<c:otherwise>
									<span class="badge badge-danger">non disponible</span>
								</c:otherwise>
							</c:choose>
						</td>
						<td>
							<a href="regarder.voiture?id=${v.id}" title="consulter voiture" class="btn btn-sm btn-info">
								<i class="fas fa-eye"></i>
							</a>
							<a href="modifier.voiture?id=${v.id}" title="modifier voiture" class="btn btn-sm btn-secondary">
								<i class="fa fa-edit"></i>
							</a>
							<a href="supprimer.voiture?id=${v.id}" title="supprimer voiture" class="btn btn-sm btn-danger" onClick="return confirm('êtes vous sûr ?')">
								<i class="fas fa-trash"></i>
							</a>
						</td>
					</tr> 
				</c:forEach>
			</tbody>
	</table>
	
	<nav aria-label="...">
		  <ul class="pagination">
		    <c:if test="${currentPage > 1 }">
			    <li class="page-item">
			      <a class="page-link" href="voiture?page=<%= currentPage - 1 %>&nbElement=${nbElement}&motCle=${motCle}">précedant</a>
			    </li>
		    </c:if>
		    <%
		    	for(int i = 1; i <= totalPages; i++){
		    		if(i == currentPage){
		    			out.print("<li class='page-item active'>");
		    			out.print("<a class='page-link' href='voiture?page="+ i +"&nbElement="+nbElement+"'>"+ i + "</a>");
		    			out.print("</li>");
		    		}else{
		    			out.print("<li class='page-item'>");
		    			out.print("<a class='page-link' href='voiture?page="+ i+ "&nbElement="+nbElement+"'>"+ i +"</a>");
		    			out.print("</li>");	    			
		    		}
		    	}
		    %>
		    <c:if test="${totalPages - currentPage >= 1 }">
			    <li class="page-item">
			      <a class="page-link" href="voiture?page=<%= currentPage + 1 %>&nbElement=${nbElement}&motCle=${motCle}">suivant</a>
			    </li>		    
		    </c:if>
		  </ul>
		</nav>
</div>
<script type="text/javascript">
	function soummettreFormulaire(){
		document.forms["formNbElement"].submit();
	}
</script>
</body>
</html>