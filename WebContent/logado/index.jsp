<!-- index do usuário -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Vale Transporte</title>
</head>
<body>
	<jsp:include page="/adds/navbar.jsp"></jsp:include>

	<div class="container w-50">
		<!-- conteúdo disponível antes de iniciar turno -->
		<c:if test="${sessionScope.estadoTurno}">
			<button type="button" class="btn btn-primary btn-block btn-lg"
				data-toggle="modal" data-target="#modalIniciarTurno">
				Iniciar turno</button>
		</c:if>
		
		<!-- conteúdo disponível após iniciar turno -->
		<c:if test="${!sessionScope.estadoTurno}">
			<button type="button" class="btn btn-primary btn-block btn-lg"
				data-toggle="modal" data-target="#modalRegistrarVenda">
				Registrar venda</button>
			<button type="button" class="btn btn-primary btn-block btn-lg"
				data-toggle="modal" data-target="#modalCadastrarAluno">
				Cadastrar aluno</button>
			<button type="button" class="btn btn-danger btn-block btn-lg"
				data-toggle="modal" data-target="#modalCadastrarAluno">
				Encerrar turno</button>
		</c:if>

	</div>

	<!-- Modal -->
	<div class="modal fade" id="modalIniciarTurno" tabindex="-1"
		role="dialog">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Fechar">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>

				<div class="modal-body">
					<form action="iniciarturno">
						<div class="form-group">
							<input type="number" class="form-control"
								placeholder="Quantidade de vales" name="quantVales"
								value="${sessionScope.quantVales }">
						</div>
						<div class="form-group">
							<input type="text" class="form-control" placeholder="Responsável"
								name="responsavel">
						</div>
						<div class="form-group">
							<select class="custom-select" name="periodo">
								<option style="display: none">Selecione o período:</option>
								<option value="Matutino">Matutino</option>
								<option value="Vespertino">Vespertino</option>
							</select>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Cancelar</button>
							<button type="submit" class="btn btn-primary">Iniciar</button>
						</div>

					</form>
				</div>


			</div>
		</div>
	</div>
	<jsp:include page="/adds/rodape.jsp"></jsp:include>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="js/bootstrap.js" type="text/javascript"></script>
</body>
</html>