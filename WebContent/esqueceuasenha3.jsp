<!-- 
	Parte 3 da recuperação de senha 
	Escolher nova senha
-->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1" />

<title>Recuperar senha 3 - VT</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
</head>

<body class="aw-layout-simple-page">
	<div class="aw-layout-simple-page__container">
		<div class="text-center mb-1">
			<img class="img-fluid" src="img/logo-ctb.png" />
		</div>
		<br />
		<form action="sistema?logica=AlterarSenha" method="POST">
			<div class="aw-simple-panel">
				<div class="aw-simple-panel__box">
			
					Pronto! Agora pode inserir sua nova senha:
					<div class="form-group has-feedback">
						<input type="password" class="form-control input-lg" placeholder="Crie sua senha" name="senha" id="senha">
						<span class="form-control-feedback" aria-hidden="true"></span>
						<div id="mensagemsenha">
							<label>Senha deve ter mais que 6 caracteres</label>
						</div>
					</div>
		
					<div class="form-group has-feedback" id="confirmacaosenhadiv">
						<input type="password" class="form-control input-lg" placeholder="Confirme sua senha" name="confirmacaosenha" id="confirmacaosenha"> 
						<span class="form-control-feedback" aria-hidden="true"></span>
						<div id="mensagemconfirmacaosenha">
							<label>Senhas não conferem</label>
						</div>
					</div>
					<br />
					<div class="form-group" id="botaocadastrar">
						<button type="submit" class="btn btn-primary btn-lg aw-btn-full-width">Confirmar</button>
					</div>
					<div class="aw-simple-panel__footer"><br/></div>
				</div>
			</div>
		</form>
		
		<script type="text/javascript" src="js/validarsenha.js"></script>
	</div>
</body>
</html>