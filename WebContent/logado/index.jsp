<!-- index do usuário -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Vale Transporte</title>
</head>
<body>
	<jsp:include page="/adds/navbar.jsp"></jsp:include>
	<jsp:include page="modal.jsp"></jsp:include>
	
	<div class="container w-50">
		<c:choose>
		<c:when test="${sessionScope.turno.concluido}">
		<!-- conteúdo disponível antes de iniciar turno -->
		
			<button type="button" class="btn btn-success btn-block btn-lg"
				data-toggle="modal" data-target="#modalIniciarTurno">
				Iniciar turno</button>
		
		</c:when>
		
		<c:when test="${!sessionScope.turno.concluido}">
		<!-- conteúdo disponível após iniciar turno -->
		
			<button type="button" class="btn btn-primary btn-block btn-lg"
				data-toggle="modal" data-target="#modalRegistrarVenda">
				Registrar venda</button>
		</c:when>
		</c:choose>
	
	<button type="button" class="btn btn-primary btn-block btn-lg"
				data-toggle="modal" data-target="#modalCadastrarAluno">
				Cadastrar aluno</button>
	<a class="btn btn-primary btn-block btn-lg" href="telacadastrarescola">Cadastrar escola</a>
	<c:if test="${!sessionScope.turno.concluido}">
		<button type="button" class="btn btn-danger btn-block btn-lg"
			data-toggle="modal" data-target="#modalEncerrarTurno">
			Encerrar turno</button>
	</c:if>
	</div>
	<jsp:include page="/adds/rodape.jsp"></jsp:include>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.js"></script>
	<script src="js/bootstrap.js" type="text/javascript"></script>
</body>
</html>