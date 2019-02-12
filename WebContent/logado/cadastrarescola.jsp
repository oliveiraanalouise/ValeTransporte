<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Cadastrar escola - VT</title>
</head>
<body>
	<jsp:include page="/adds/navbar.jsp"></jsp:include>
	<form action="cadastrarescola">
	
	</form>

	<div class="form-group input-group">
		<span class="input-group-addon"><i
			class=""></i></span> <input name="consulta"
			id="txt_consulta"
			placeholder="Cheque as escolas que já estão cadastradas" type="text"
			class="form-control">
	</div>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>#</th>
				<th scope="col">Nome</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach var="escola" items="${escolas}" varStatus="posicao">
				<tr>
					<td>${posicao}</td>
					<td>${escola.nome }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<jsp:include page="/adds/rodape.jsp"></jsp:include>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.js"></script>
	<script src="js/bootstrap.js" type="text/javascript"></script>
</body>
</html>