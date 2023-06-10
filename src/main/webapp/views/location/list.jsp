<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String filtre = String.valueOf(request.getAttribute("filtre"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Les locations</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="css/fontawesome.min.css"/>
<link rel="stylesheet" type="text/css" href="css/all.min.css"/>
</head>
<body>
<%@include file="../header.jsp" %>
<div class="mx-5 mt-5 pt-3">
	<h3>Les locations</h3>
	<a href="nouveau.location" class="btn btn-primary">Nouveau</a>
	<form action="filtrer.location" class="mt-2 form-inline" method="get" id="filtrerLocation">
		<select name="filtre" class="custom-select" onchange="soummettreFormulaire()">
			<option value="all" <% if(filtre.equals("all")) {%> <%= "selected" %> <%} %> >Tout</option>
			<option value="finished" <% if(filtre.equals("finished")) {%> <%= "selected" %> <%} %> >Terminer</option>
			<option value="current" <% if(filtre.equals("current")) {%> <%= "selected" %> <%} %> >En cours</option>
			<option value="late" <% if(filtre.equals("late")) {%> <%= "selected" %> <%} %> >En retard</option>
		</select>
		<label for="filtre">Filtre</label>
	</form>
	<table class="mt-3 table table-striped table-bordered">
		<thead>
			<tr>
				<th>ID</th>
				<th>Voiture</th>
				<th>Client</th>
				<th>Date debut</th>
				<th>Date fin</th>
				<th>Prix</th>
				<th>Status</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${locations}" var="l" >
				<tr>
					<td>${l.id}</td>
					<td>${l.voiture}</td>
					<td>${l.client}</td>
					<td>${l.dateDebut}</td>
					<td>${l.dateFin}</td>
					<td>${l.prix_total}</td>
					<td>
						<c:choose>
							<c:when test="${l.status }">
								<span class="badge badge-success">Rendu</span>
							</c:when>
							<c:otherwise>
								<span class="badge badge-info">En location</span>
							</c:otherwise>
						</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${l.status }">
								<a href="supprimer.location?id=${l.id}" title="supprimer location" class="btn btn-sm btn-danger" onClick="return confirm('Attention cette action est irreversible')">
									<i class="fas fa-trash"></i>
								</a>
							</c:when>
							<c:otherwise>
								<a href="terminer.location?id=${l.id}" class="btn btn-sm btn-secondary">
									terminer
								</a>								
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<script type="text/javascript">
	function soummettreFormulaire(){
		document.forms["filtrerLocation"].submit();
	}
</script>
</body>
</html>