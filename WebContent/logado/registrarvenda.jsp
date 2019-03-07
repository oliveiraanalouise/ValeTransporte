<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Venda - VT</title>
</head>
<body>
	<jsp:include page="/adds/navbar.jsp"></jsp:include>
	<div class="container-fluid">
		<c:if test="${quantMax}">
			<div class="alert alert-danger show fade" role="alert">
				Quantidade máxima de vales excedida</div>
		</c:if>

		<c:if test="${alunoAusente}">
			<div class="alert alert-danger show fade" role="alert">Escolha
				um aluno na lista</div>
		</c:if>

		<c:if test="${ok}">
			<div class="alert alert-success show fade" role="alert">Venda
				efetuada</div>
		</c:if>

		<c:if test="${sessionScope.turno.quantVales == 0}">
			<div class="alert alert-warning show fade" role="alert">
				Não há mais vales para venda. Favor iniciar um novo turno para
				adicionar mais. <a href="encerrarturno" data-toggle="modal"
					data-target="#modalEncerrarTurno">Encerre</a> seu turno agora
			</div>
		</c:if>
		
		<form action="registrarvenda">
			<div class="container-fluid w-50">
				<div class="form-group">
					<input disabled required class="form-control" type="text"
						name="nomeAluno" value="${sessionScope.aluno.stringId} - ${sessionScope.aluno.nome}"> <span>Escolha
						o aluno na lista abaixo</span>
				</div>
				<div class="form-group">
					<input type="number" required class="form-control"
						placeholder="Quantidade de vales" name="quantidade">
				</div>

				<div style="display: none">
					<input type="text" name="idAluno" value="${sessionScope.aluno.id}">
				</div>

				<button type="submit" class="btn btn-primary btn-block btn-lg">Registrar
					venda</button>
			</div>
		</form>
		<br>

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
						<td><a href="telaregistrarvenda?ialuno=${posicao.index}"
							class="btn btn-outline-primary btn-sm">Escolher</a></td>
						<td>${aluno.stringId}</td>
						<td>${aluno.nome}</td>
						<td>${aluno.bairro}</td>
						<td>${aluno.rg}</td>
						<td>${aluno.escola.nome}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>



	</div>
	<jsp:include page="/adds/rodape.jsp"></jsp:include>
	<jsp:include page="modal.jsp"></jsp:include>

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