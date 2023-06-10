<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Modifier voiture</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
</head>
<body>
<%@include file="../header.jsp" %>
	<div class="mx-5 mt-5 pt-3 ">
		<div class="card">
			<div class="card-header">
				<h3 class="card-title">Modifier voiture ID ${voiture.id } </h3>			
			</div>
			<div class="card-body">
				<form class="form" action="enregistrer.modifier.voiture" method="post">
					<div class="form-group">
						<input type="hidden" name="id" class="form-control" value="${voiture.id }"/>
					</div>
					<div class="form-group">
						<label for="marque">Marque</label>
						<input type="text" class="form-control" value="${voiture.marque }" name="marque"/>					
					</div>
					<div class="form-group">
						<label for="marque">Modele</label>
						<input type="text" class="form-control" value="${voiture.modele }" name="modele"/>					
					</div>
					<div class="form-group">
						<label for="marque">Couleur</label>
						<input type="text" class="form-control" value="${voiture.couleur }" name="couleur"/>					
					</div>
					<div class="form-group">
						<label for="marque">Année</label>
						<input type="text" class="form-control" value="${voiture.annee }" name="annee"/>					
					</div>
					<div class="form-group">
						<label for="marque">Prix journalière</label>
						<input type="text" class="form-control" value="${voiture.prixJournalier }" name="prixJournalier"/>					
					</div>
					<div class="form-group">
						<label for="nbPlace">Nombre de place</label>
						<input type="text" name="nbPlace" class="form-control" value="${voiture.nbPlace }"/>				
					</div>
					<div class="form-group">
						<label for="description">Description</label>
						<textarea name="description" class="form-control">${voiture.description }</textarea>				
					</div>
					<button type="submit" class="btn btn-primary">Enregistrer</button>
					<a href="voiture" class="btn btn-secondary">Annuler</a>
				</form>				
			</div>
		</div>
	</div>
</body>
</html>