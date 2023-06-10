<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Nouveau voiture</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
</head>
<body>
<%@include file="../header.jsp" %>
	<div class="mx-5 mt-5 pt-3">
		<div class="card mt-5">
			<div class="card-header">
			<h3>Formulaire voiture</h3>		
			</div>
			<div class="card-body">
				<form class="form" action="enregistrer.nouveau.voiture" enctype="multipart/form-data" method="post">
					<div class="row">
						<div class="col-6">
							<div class="form-group">
								<label for="marque">Marque</label>
								<input type="text" class="form-control" name="marque"/>					
							</div>				
						</div>
						<div class="col-6">
							<div class="form-group">
								<label for="marque">Modele</label>
								<input type="text" class="form-control" name="modele"/>					
							</div>				
						</div>
						<div class="col-6">
							<div class="form-group">
								<label for="marque">Couleur</label>
								<input type="text" class="form-control" name="couleur"/>					
							</div>				
						</div>
						<div class="col-6">
							<div class="form-group">
								<label for="marque">Année</label>
								<input type="text" class="form-control" name="annee"/>					
							</div>				
						</div>
						<div class="col-6">
							<div class="form-group">
								<label for="marque">Prix journalière</label>
								<input type="text" class="form-control" name="prixJournalier"/>					
							</div>				
						</div>
						<div class="col-6">
							<div class="form-group">
								<label for="nbPlace">Nombre de place</label>
								<input type="text" class="form-control" name="nbPlace"/>
							</div>				
						</div>
						<div class="col-12">
							<div class="form-group">
								<label for="illustration">Illustration</label> 
								<input type="file" class="form-control" name="illustration" required="required"/>					
							</div>		
						</div>
						<div class="col-12">
							<div class="form-group">
								<label for="description">Description</label>
								<textarea class="form-control" name="description"></textarea>
							</div>				
						</div>
					</div>
					<button type="submit" class="btn btn-primary">Ajouter</button>
					<a href="voiture" class="btn btn-secondary">Annuler</a>
				</form>
				
			</div>
		</div>
	</div>
</body>
</html>