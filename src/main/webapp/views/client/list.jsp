<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Les clients</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="css/fontawesome.min.css"/>
<link rel="stylesheet" type="text/css" href="css/all.min.css"/>
</head>
<body>
<%@include file="../header.jsp" %>
<div class="mx-5 mt-5 pt-3">
	<h3>Les Clients</h3>
	<a href="nouveau.client" class="btn btn-primary">Nouveau</a>
	<table class="mt-3 table table-striped table-bordered">
		<thead>
			<tr>
				<th>ID</th>
				<th>Nom</th>
				<th>Prenom</th>
				<th>Adresse</th>
				<th>Telephone</th>
				<th>CIN</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${clients}" var="c" >
				<tr>
					<td>${c.id}</td>
					<td>${c.nom}</td>
					<td>${c.prenom}</td>
					<td>${c.adresse}</td>
					<td>${c.telephone}</td>
					<td>${c.cin}</td>
					<td>
						<a href="regarder.client?id=${c.id}" title="consulter client" class="btn btn-sm btn-info">
							<i class="fas fa-eye"></i>
						</a>
						<a href="modifier.client?id=${c.id}" title="modifier client" class="btn btn-sm btn-secondary">
							<i class="fa fa-edit"></i>
						</a>
						<a href="supprimer.client?id=${c.id}" title="supprimer client" class="btn btn-sm btn-danger" onClick="return confirm('êtes vous sûr ?')">
							<i class="fas fa-trash"></i>
						</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
</body>
</html>