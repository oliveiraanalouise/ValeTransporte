<!-- 
	Parte 1 da recuperação de senha 
	Inserir email
-->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1" />

<title>Recuperar senha - VT</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
</head>

<body>
	<div style="position: absolute; top: 20%; width: 100%;">
		<div class="container w-50">
			<div class="text-center mb-1">
				<img class="img-fluid" src="img/logo-ctb.png" />
			</div>
			<br />
			<form action="sistema?logica=GerarToken" method="POST">
				Por favor, informe seu email de acesso:
				<div class="form-group  has-feedback">
					<input type="email" class="form-control  input-lg"
						placeholder="Seu e-mail" name="email" required /> <span
						class="glyphicon  glyphicon-envelope  form-control-feedback"
						aria-hidden="true"></span>
				</div>
				<div class="form-group">
					<button type="submit"
						class="btn btn-primary btn-block">Enviar
						código</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>