<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1" />

<title>Cadastrar usuário - VT</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
</head>

<body>
	<c:if test="${emailusado}">
		<div class="alert alert-danger show fade" role="alert">
			Esse email já foi utilizado. Ao invés de se cadastrar, por que não
			tenta <a href="esqueceuasenha.jsp">recuperar sua senha</a>?
		</div>
	</c:if>

	<div style="position: absolute; top: 20%; width: 100%;">
		<div class="container w-50">
			<div class="text-center mb-1">
				<img class="img-fluid" src="img/logo-ctb.png" /> <br>Cadastrar
				usuário.<br>Preencha os dados abaixo
			</div>
			<form action="cadastrarusuario" method="POST">
				<div class="form-group">
					<input type="email" class="form-control" name="email"
						placeholder="Seu email" required>
				</div>
				<div class="form-group">
					<input type="text" class="form-control" name="nome"
						placeholder="Seu nome" required>
				</div>
				<div class="form-group">
					<select class="custom-select" name="responsavel">
						<option style="display: none">Selecione o responsável:</option>
						<c:forEach var="supervisor" items="${supervisores}">
							<option value="${supervisor.id}">${supervisor.nome}</option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group">
					<input type="password" class="form-control"
						placeholder="Crie sua senha" name="senha" id="senha" required>
					<div id="mensagemsenha">
						<label>Senha deve ter mais que 6 caracteres</label>
					</div>
				</div>

				<div class="form-group" id="confirmacaosenhadiv">
					<input type="password" class="form-control"
						placeholder="Confirme sua senha" name="confirmacaosenha"
						id="confirmacaosenha" required>
					<div id="mensagemconfirmacaosenha">
						<span>Senhas não conferem</span>
					</div>
					<br>
				</div>

				<div class="form-group" id="botaocadastrar">
					<button type="submit" class="btn btn-primary btn-block">Cadastrar</button>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript" src="js/validarsenha.js"></script>
	<jsp:include page="adds/rodape.jsp"></jsp:include>
</body>
</html>