<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="ISO-8859-1" />
</head>
<nav class="navbar navbar-expand-sm navbar-dark bg-secondary mb-1">
	<a class="navbar-item" href="voltarpagina"><img
		src="img/3b9a523e0eb6840e359da020a0d9d01a.png" class="img-fluid" width="35" height="35"></a>
		
	<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
		<li class="nav-item active"><a class="nav-link" href="telaprincipal">Home</a></li>
	</ul>
	<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
		<li class="nav-item active text-white">${sessionScope.usuario.nome }</li>
	</ul>
	<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
		<li class="nav-item active text-white">Vales: ${sessionScope.quantVales }</li>
	</ul>
	<ul class="navbar-nav">
		<li class="nav-item active"><a class="nav-link" href="logout">Sair</a></li>
	</ul>
	<%-- <div class="container-fluid">
		<ul class="navbar-nav">
			<li class="text-center nav-item col-md-1"><h3>
					<i>${sessionScope.usuario.nome} </i>
				</h3></li>
			<li class="text-right nav-item col-md-1"><h3>
					<i>${sessionScope.usuario.supervisor } </i>
				</h3></li>
		</ul>

	</div> --%>
</nav>
</html>