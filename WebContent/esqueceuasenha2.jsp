<!-- 
	Parte 2 da recuperação de senha 
	Confirmar código enviado por email
-->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1" />

<title>Recuperar senha 2 - VT</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
</head>

<body class="aw-layout-simple-page">
	<div class="aw-layout-simple-page__container">
		<div class="text-center mb-1">
			<img class="img-fluid" src="img/logo-ctb.png" />
		</div>
		<br />
		<form action="sistema?logica=ValidarToken" method="POST"> 
			<div class="aw-simple-panel">
				<div class="aw-simple-panel__box">
					Cheque a sua caixa de entrada, enviamos seu código de segurança para lá. Insira esse código aqui:
					<div class="form-group  has-feedback">
						<input type="number" class="form-control  input-lg" placeholder="Código" name="codigo" required/> 
						<span class="glyphicon  glyphicon-envelope  form-control-feedback" aria-hidden="true"></span>
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-primary btn-lg aw-btn-full-width">Confirmar</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>