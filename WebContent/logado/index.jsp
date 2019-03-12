<!-- index do usuário -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Vale Transporte</title>
</head>
<body>
	<jsp:include page="/adds/navbar.jsp"></jsp:include>
	<div class="container-fluid">
		<c:if
			test="${sessionScope.turno.quantVales == 0 && !sessionScope.turno.concluido}">
			<div class="alert alert-warning show fade" role="alert">
				Não há mais vales para venda. Favor iniciar um novo turno para
				adicionar mais. <a href="encerrarturno" data-toggle="modal"
					data-target="#modalEncerrarTurno">Encerre</a> seu turno agora
			</div>
		</c:if>
		
		<c:if test="${relatorio}">
			<div class="alert alert-info show fade" role="alert">
				Turno encerrado. Veja <a href="RelatorioTurno${sessionScope.turno.id}.pdf" target="_blank">aqui</a> o relatório.
			</div>
		</c:if>
	</div>

	<div class="container-fluid w-50">
		<c:choose>
			<c:when test="${sessionScope.turno.concluido}">
				<!-- conteúdo disponível antes de iniciar turno -->

				<button type="button" class="btn btn-success btn-block btn-lg"
					data-toggle="modal" data-target="#modalIniciarTurno">
					Iniciar turno</button>

			</c:when>

			<c:when test="${!sessionScope.turno.concluido}">
				<!-- conteúdo disponível após iniciar turno -->

				<!-- <button type="button" class="btn btn-primary btn-block btn-lg"
				data-toggle="modal" data-target="#modalRegistrarVenda">
				Registrar venda</button> -->
				<a class="btn btn-primary btn-block btn-lg"
					href="telaregistrarvenda">Registrar venda</a>
			</c:when>
		</c:choose>

		<a class="btn btn-primary btn-block btn-lg" href="telacadastraraluno">Cadastrar aluno</a> 
		<a class="btn btn-primary btn-block btn-lg"	href="telacadastrarescola">Cadastrar/consultar escola</a>
		<!-- <a class="btn btn-primary btn-block btn-lg" href="vertodosturnos">Ver turnos</a>
		<a class="btn btn-primary btn-block btn-lg" href="">Ver vendas desse turno</a> -->
		
		<c:if test="${!sessionScope.turno.concluido}">
			<button type="button" class="btn btn-danger btn-block btn-lg"
				data-toggle="modal" data-target="#modalEncerrarTurno">
				Encerrar turno</button>
		</c:if>
	</div>
	
	<jsp:include page="modal.jsp"></jsp:include>
	<jsp:include page="/adds/rodape.jsp"></jsp:include>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.js"></script>
	<script src="js/bootstrap.js" type="text/javascript"></script>
</body>
</html>