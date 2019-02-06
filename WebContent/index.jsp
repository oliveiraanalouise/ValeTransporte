<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1" />

<title>Login - VT</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />

</head>

<body>
	<div class="container w-50 m-auto">
		<div class="text-center mb-1">
			<img class="img-fluid" src="img/logo-ctb.png" />
		</div>
		<form action="login" method="POST">

			<div class="form-group">
				<input type="email" class="form-control" id="email"
					aria-describedby="emailHelp" placeholder="Seu email">@ctb.ba.gov.br
			</div>
			<div class="form-group">
				<input type="password" class="form-control" id="senha"
					placeholder="Senha">
			</div>
			<div class="row justify-content-between">
				<div>
					<button type="submit" class="btn btn-primary">Entrar</button>
				</div>
				<div>
					<a href="esqueceuasenha.jsp">Esqueci minha senha</a>
				</div>
			</div>

		</form>
	</div>
	<jsp:include page="adds/rodape.jsp"></jsp:include>
</body>
</html>