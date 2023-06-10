<%
	String message = String.valueOf(request.getAttribute("message"));
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Nouveau location</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
</head>
<body>
<%@include file="../header.jsp" %>
	<div class="mx-5 mt-5 pt-3">
		<h3>Formulaire location</h3>
		<c:if test="${message }">
			<div class="alert alert-danger">
				${message }
			</div>
		</c:if>
		<form class="form" action="enregistrer.nouveau.location" method="post">
			<div class="form-group">
				<label for="voiture">Voiture</label>
				<select class="custom-select" aria-label="Default select example" name="voiture">
					<option selected>choisir une voiture</option>
					<c:forEach items="${voitures }" var="voiture">
						<option value="${voiture.id }">${voiture.marque } - ${voiture.modele }</option>					
					</c:forEach>
				</select>				
			</div>
			<div class="form-group">
				<label for="client">Client</label>
				<select class="custom-select" aria-label="Default select example" name="client">
					<option selected>Choisir un client</option>
					<c:forEach items="${clients }" var="client">
						<option value="${client.id }">${client.nom } - ${client.prenom }</option>					
					</c:forEach>
				</select>				
			</div>
			<div class="form-group">
				<label for="dateDebut">Date debut</label>
				<input type="date" class="form-control" name="dateDebut"/>					
			</div>
			<div class="form-group">
				<label for="dateFin">Date Fin location</label>
				<input type="date" class="form-control" name="dateFin"/>					
			</div>
			<button type="submit" class="btn btn btn-primary">Ajouter</button>
		</form>
	</div>
</body>
</html>