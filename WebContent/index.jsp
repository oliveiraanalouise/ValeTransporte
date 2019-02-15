<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1" />

<title>Login - VT</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
</head>

<body>
	<div style="position: absolute; top: 20%; width: 100%;">
		<div class="container w-50">
			<div class="text-center mb-1">
				<img class="img-fluid" src="img/logo-ctb.png" />
			</div>
			<form action="login" method="POST">
				<div class="form-group">
					<input type="text" class="form-control" name="email"
						aria-describedby="emailHelp" placeholder="Seu email">
					<!-- <div class="row">
					<div>
						<input type="text" class="form-control" name="email"
							aria-describedby="emailHelp" placeholder="Seu email">
					</div>
					<div class="col-md-auto">@ctb.ba.gov.br</div>
				</div> -->
				</div>
				<div class="form-group">
					<input type="password" class="form-control" name="senha"
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
	</div>
	<jsp:include page="adds/rodape.jsp"></jsp:include>
</body>
</html>