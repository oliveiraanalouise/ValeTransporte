<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>2ª via - VT</title>
</head>
<body>
	<jsp:include page="/adds/navbar.jsp"></jsp:include>
	
	<c:if test="${ok}">
			<div class="alert alert-success show fade" role="alert">
				2ª via comprovante de cadastro de ${aluno.nome} <a href="logado/comprovantes/ComprovanteCadastro${aluno.id}.pdf" target="_blank">aqui</a>.
			</div>
		</c:if>
		
<div class="form-group input-group">
			<span class="input-group-addon"><i class=""></i></span> <input
				name="consulta" id="txt_consulta" placeholder="Procurar aluno"
				type="text" class="form-control">
		</div>
		<table id="tabela" class="table table-hover">
			<thead>
				<tr>
					<th></th>
					<th>ID</th>
					<th>Nome</th>
					<th>Bairro</th>
					<th>RG</th>
					<th>Escola</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="aluno" items="${sessionScope.alunos}"
					varStatus="posicao">
					<tr>
						<td><a href="segviacomprovante?ialuno=${posicao.index}"
							class="btn btn-outline-primary btn-sm"><img src="img/escolher.png" class="img-fluid"></a></td>
						<td>${aluno.stringId}</td>
						<td>${aluno.nome}</td>
						<td>${aluno.bairro}</td>
						<td>${aluno.rg}</td>
						<td>${aluno.escola.nome}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	<jsp:include page="/adds/rodape.jsp"></jsp:include>
	
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.quicksearch/2.3.1/jquery.quicksearch.js"></script>
	<script src="js/bootstrap.js" type="text/javascript"></script>
	<script>
		$('input#txt_consulta').quicksearch('table#tabela tbody tr');
	</script>
</body>
</html>