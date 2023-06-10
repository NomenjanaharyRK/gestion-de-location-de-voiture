<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Les voitures</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
</head>
<body>
<%@include file="../header.jsp" %>
<div class="mx-5 mt-5 pt-3">
	<a href="voiture" class="btn btn-secondary my-4">retour</a>
	<div class="card">
		<div class="card-header">
			<h3 class="card-title">Voiture information</h3>
		</div>
		<div class="card-body">
			<div class="row">
				<div class="col-md-6 col-sm-12">
					<img src="uploads/${voiture.illustration }" class="img-fluid"/>				
				</div>
				<div class="col-md-6 col-sm-12">
					<table class="table">
						<tr>
							<td>Status</td>
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
						</tr>
						<tr>
							<td>Marque</td>
							<td>${voiture.marque }</td>
						</tr>
						<tr>
							<td>Modèle</td>
							<td>${voiture.modele }</td>
						</tr>
						<tr>
							<td>Nombre de place </td>
							<td>${voiture.nbPlace }</td>
						</tr>
						<tr>
							<td>Couleur</td>
							<td>${voiture.couleur }</td>
						</tr>
						<tr>
							<td>Année</td>
							<td>${voiture.annee }</td>
						</tr>
						<tr>
							<td>Prix journalière</td>
							<td>${voiture.prixJournalier }</td>
						</tr>
						<tr>
							<td>Description</td>
							<td>${voiture.description }</td>
						</tr>
					</table>		
					<a href="modifier.voiture?id=${voiture.id }" class="btn btn-primary">Modifier</a>
					<a href="supprimer.voiture?id=${voiture.id }" class="btn btn-danger">Supprimer</a>		
				</div>
			</div>
		</div>
	</div>
</div>
<%-- <div class="mx-5 mt-5 pt-3">
	<a href="voiture" class="btn btn-secondary my-4">retour</a>
	<div class="row">
		<div class="col-6">
			<img alt="illustration" src="uploads/${voiture.illustration }" class="img-fluid">
		</div>
		<div class="col-6">
			<div class="card">
				<div class="card-header">
					<h3>Voiture information</h3>					
				</div>
				<div class="card-body">
					<h4>${voiture.marque } - ${voiture.modele }</h4>
					<p class="text-lead"><strong>Prix Journalière:</strong> ${voiture.prixJournalier } Ariary </p>
					<p class="text-lead"><strong>Année:</strong> ${voiture.annee } </p>
					<p class="text-lead"><strong>Couleur:</strong> ${voiture.couleur } </p>
				</div>
				<div class="card-footer">
					<a href="modifier.voiture?id=${voiture.id }" class="btn btn-primary">Modifier</a>
					<a href="supprimer.voiture?id=${voiture.id }" class="btn btn-danger">Supprimer</a>
				</div>
			</div>
		</div>
	</div>
</div> --%>
</body>
</html>