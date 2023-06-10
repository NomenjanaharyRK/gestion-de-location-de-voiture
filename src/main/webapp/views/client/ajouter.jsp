<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Nouveau client</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
</head>
<body>
<%@include file="../header.jsp" %>
	<div class="mx-5 mt-5 pt-3">
		<h3>Formulaire client</h3>
		<form class="form" action="enregistrer.nouveau.client" method="post">
			<div class="form-group">
				<label for="nom">Nom</label>
				<input type="text" class="form-control" name="nom"/>					
			</div>
			<div class="form-group">
				<label for="prenom">Prenom</label>
				<input type="text" class="form-control" name="prenom"/>					
			</div>
			<div class="form-group">
				<label for="adresse">Adresse</label>
				<input type="text" class="form-control" name="adresse"/>					
			</div>
			<div class="form-group">
				<label for="telephone">Telephone</label>
				<input type="text" class="form-control" name="telephone"/>					
			</div>
			<div class="form-group">
				<label for="cin">CIN</label>
				<input type="text" class="form-control" name="cin"/>					
			</div>
			<button type="submit" class="btn btn-primary">Ajouter</button>
		</form>
	</div>
</body>
</html>