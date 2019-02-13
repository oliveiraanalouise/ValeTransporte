<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Cadastrar escola - VT</title>
</head>
<body>
	<jsp:include page="/adds/navbar.jsp"></jsp:include>
	<div class="container-fluid">
	
	<c:if test="${cadastrada}">
		<div class="alert alert-primary show fade" role="alert">
			${escolaRegistrada.nome} cadastrada
		</div>
	</c:if>
	
	<form action="cadastrarescola">
		<div class="form-group">
			<input type="text" placeholder="Nome" class="form-control"
				name="nome">
		</div>
		<div class="form-group container-fluid">
			<button type="submit" class="btn btn-primary btn-block btn-lg">Cadastrar</button>
		</div>
	</form>

	<br />
	<br />
	<div class="form-group input-group">
		<span class="input-group-addon"><i class=""></i></span> <input
			name="consulta" id="txt_consulta"
			placeholder="Cheque as escolas que já estão cadastradas" type="text"
			class="form-control">
	</div>
	<table id="tabela" class="table table-hover">
		<thead>
			<tr>
				<th>#</th>
				<th scope="col">Nome</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach var="escola" items="${sessionScope.escolas}"
				varStatus="posicao">
				<tr>
					<td>${posicao.index+1}</td>
					<td>${escola.nome}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

       </div>
	<jsp:include page="/adds/rodape.jsp"></jsp:include>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.quicksearch/2.3.1/jquery.quicksearch.js"></script>
	<script src="js/bootstrap.js" type="text/javascript"></script>
	<script>
		$('input#txt_consulta').quicksearch('table#tabela tbody tr');
	</script>
</body>
</html>