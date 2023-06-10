<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>client information</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
</head>
<body>
<%@include file="../header.jsp" %>
<div class="mx-5 mt-5 pt-3">
	<a href="client" class="btn btn-secondary mb-2">retour</a>
	<div class="card">
		<div class="card-header">
			<h3 class="card-title">Client information</h3>		
		</div>
		<div class="card-body">
			<table class="table">
				<tbody>
					<tr>
						<td>Nom</td>
						<td>${client.nom }</td>
					</tr>
					<tr>
						<td>Prenom</td>
						<td>${client.prenom }</td>
					</tr>
					<tr>
						<td>Adresse</td>
						<td>${client.adresse }</td>
					</tr>
					<tr>
						<td>Telephone</td>
						<td>${client.telephone }</td>
					</tr>
					<tr>
						<td>CIN</td>
						<td>${client.cin }</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="card-footer">
			<a href="modifier.client?id=${client.id }" class="btn btn-primary">Modifier</a>
			<a href="supprimer.client?id=${client.id }" class="btn btn-danger">Supprimer</a>
		</div>
	</div>
</div>
</body>
</html>