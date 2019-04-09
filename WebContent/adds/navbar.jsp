<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" type="text/css" href="../css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="../css/style.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.js"></script>
<script src="js/bootstrap.js" type="text/javascript"></script>
</head>

<nav class="navbar navbar-expand-sm navbar-dark bg-bar mb-1">
	<a class="navbar-item" href="/valetransporte/voltarpagina"><img
		src="img/voltar.png" class="img-fluid"></a>
		
	<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
		<li class="nav-item active"><a class="nav-link" href="/valetransporte/telaprincipal">Home</a></li>
	</ul>
	<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
		<li class="nav-item active text-white">${sessionScope.usuario.nome }</li>
	</ul>
	<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
		<li class="nav-item active text-white">Turno: ${sessionScope.turno.id }/${sessionScope.turno.turno }</li>
	</ul>
	<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
		<li class="nav-item active text-white">Vales: ${sessionScope.turno.quantVales }</li>
	</ul>
	<ul class="navbar-nav">
		<li class="nav-item active">
		<c:choose>
			<c:when test="${!sessionScope.turno.concluido}">
			<button type="button" class="btn nav-link"
					data-toggle="modal" data-target="#modalSairEncerrarTurno">
					<img src="img/logout.png" class="img-fluid"></button>
			</c:when>
			<c:when test="${sessionScope.turno.concluido}">
				<a class="nav-link" href="/valetransporte/logout"><img src="img/logout.png" class="img-fluid"></a>
			</c:when>
		</c:choose>
		</li>
	</ul>
	
	<!-- Modal encerrar turno -->
	<div class="modal fade" id="modalSairEncerrarTurno" tabindex="-1"
		role="dialog">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Deseja sair sem encerrar seu turno?</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Fechar"></button>
				</div>

				<div class="modal-body">
					<div class="row">
						<div class="col">
							<button type="button" class="btn btn-primary btn-block"
								data-dismiss="modal">Não</button>
						</div>
						<div class="col">
							<a class="btn btn-danger btn-block" href="logout">Sim</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</nav>
</html>