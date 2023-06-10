<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Modifier client</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
</head>
<body>
<%@include file="../header.jsp" %>
	<div class="mx-5 mt-5 pt-3">
		<h3>Formulaire client</h3>
		<form class="form" action="enregistrer.modifier.client" method="post">
			<div class="form-group">
				<label for="id" class="form-label">Client ID: ${client.id}</label>
				<input type="hidden" name="id" class="form-control" value="${client.id }"/>
			</div>
			<div class="form-group">
				<label for="nom">Nom</label>
				<input type="text" class="form-control" value="${client.nom }" name="nom"/>					
			</div>
			<div class="form-group">
				<label for="prenom">Prenom</label>
				<input type="text" class="form-control" value="${client.prenom }" name="prenom"/>					
			</div>
			<div class="form-group">
				<label for="adresse">Adresse</label>
				<input type="text" class="form-control" value="${client.adresse }" name="adresse"/>					
			</div>
			<div class="form-group">
				<label for="telephone">Telephone</label>
				<input type="text" class="form-control" value="${client.telephone }" name="telephone"/>					
			</div>
			<div class="form-group">
				<label for="cin">CIN</label>
				<input type="text" class="form-control" value="${client.cin }" name="cin"/>					
			</div>
			<button type="submit" class="btn btn-primary">Enregistrer</button>
		</form>
	</div>
</body>
</html>