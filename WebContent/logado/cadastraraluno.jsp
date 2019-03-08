<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
<title>Cadastrar Aluno - VT</title>
</head>
<body>
	<jsp:include page="/adds/navbar.jsp"></jsp:include>
	
	<div class="container-fluid">
		<c:if test="${cpfCadastrado}">
			<div class="alert alert-danger show fade" role="alert">
				CPF ou RG já cadastrado
			</div>
		</c:if>
		
		<c:if test="${ok}">
			<div class="alert alert-success show fade" role="alert">
				Cadastro feito com sucesso. Veja o comprovante de cadastro <a href="ComprovanteCadastro${id}.pdf" target="_blank">aqui</a>.
			</div>
		</c:if>
		
		<form action="cadastraraluno" method="post">
			<div class="form-group">
				<div class="row">
					<div class="col">
						<input type="text" class="form-control" placeholder="RG" id="rg" name="rg" value="${aluno.rg}">
					</div>
					<%-- <div class="col">
						<input type="text" class="form-control" placeholder="CPF" id="cpf" name="cpf" value="${aluno.cpf}">
					</div> --%>
				</div>
			</div>
			<div class="form-group">
				<input type="text" class="form-control" name="nome" placeholder="Nome" value="${aluno.nome}">
			</div>
			<div class="form-group">
				<div class="row">
					<div class="col">
						<input type="date" class="form-control" name="nascimento" value="${aluno.dataNascimento}">
						<span>Data de nascimento</span>
					</div>
					<div class="col">
						<input type="text" class="form-control" placeholder="Bairro" name="bairro" value="${aluno.endereco}">
					</div>
					<%-- <div class="col-2">
						<input type="text" class="form-control" placeholder="CEP" name="cep" id="cep" value="${aluno.cep}">
					</div> --%>
				</div>
			</div>
			<div class="form-group">
				<select name="escola" class="custom-select">
					<option style="display:none">Selecione a escola</option>
					<c:forEach var="e" items="${sessionScope.escolas}" varStatus="posicao">
						<option value="${posicao.index}">${e.nome} - ${e.bairro}</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-primary btn-block">Cadastrar</button>
			</div>
		</form>
	</div>
	<jsp:include page="/adds/rodape.jsp"></jsp:include>

	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.0/jquery.mask.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.0/jquery.mask.js"></script>
	<script>
		$(document).ready(function() {
			var $seuCampo = $("#cep");
			$seuCampo.mask('00.000-000', {
				reverse : true
			});
		});
	</script>
	<script>
		$(document).ready(function() {
			var $seuCampo = $("#rg");
			$seuCampo.mask('00.000.000-00', {
				reverse : true
			});
		});
	</script>
	<script>
		$(document).ready(function() {
			var $seuCampo = $("#cpf");
			$seuCampo.mask('000.000.000-00', {
				reverse : true
			});
		});
	</script>
</body>
</html>