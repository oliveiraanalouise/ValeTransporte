<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Editar aluno - VT</title>
</head>
<body>
	<jsp:include page="/adds/navbar.jsp"></jsp:include>
	<c:if test="${idademinima}">
		<div class="alert alert-danger show fade" role="alert">
			Aluno não tem idade suficiente para ter vale transporte
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
				<th>ID</th>
				<th>Nome</th>
				<th>Bairro</th>
				<th>RG</th>
				<th>Escola</th>
				<th>Nascimento</th>
				<th></th>
				<th></th>

			</tr>
		</thead>

		<tbody>
			<c:forEach var="aluno" items="${sessionScope.alunos}"
				varStatus="posicao">
				<tr>
					<td>${aluno.stringId}</td>
					<td>${aluno.nome}</td>
					<td>${aluno.bairro}</td>
					<td>${aluno.rg}</td>
					<td>${aluno.escola.nome}</td>
					<td>${aluno.stringDataNascimento}</td>
					<td><a class="btn btn-outline-primary btn-block btn-sm"
						data-toggle="modal"
						data-target="#modalEditarAluno${posicao.index}"> <img
							class="img-fluid" src="img/editar.png">
					</a>

						<div class="modal fade" id="modalEditarAluno${posicao.index}"
							tabindex="-1" role="dialog">
							<div class="modal-dialog modal-dialog-centered" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title">Editando aluno</h5>
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Fechar">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>


									<div class="modal-body">
										<form action="editaraluno" method="post">
											<div style="display:none"><input value="${aluno.id}" name="id"></div>
											<div class="form-group">
												<input type="text" class="form-control" placeholder="RG"
													id="rg" name="rg" value="${aluno.rg}">
											</div>
											<div class="form-group">
												<input type="text" class="form-control" name="nome"
													placeholder="Nome" value="${aluno.nome}">
											</div>
											<div class="form-group">
												<div class="row">
													<div class="col">
														<input type="date" class="form-control" name="nascimento"
															value="${aluno.stringDataNascimento}"> <span>Data
															de nascimento</span>
													</div>
													<div class="col">
														<input type="text" class="form-control"
															placeholder="Bairro" name="bairro"
															value="${aluno.bairro}">
													</div>
												</div>
											</div>
											<div class="form-group">
												<select name="escola" class="custom-select">
													<option style="display: none">Selecione a escola</option>
													<c:forEach var="e" items="${sessionScope.escolas}"
														varStatus="posicaoEscola">
														<option value="${posicaoEscola.index}">${e.nome}-
															${e.bairro}</option>
													</c:forEach>
												</select>
											</div>
											<div class="modal-footer">
												<div class="col">
													<button type="button" class="btn btn-secondary btn-block"
														data-dismiss="modal">Cancelar</button>
												</div>
												<div class="col">
													<button type="submit" class="btn btn-primary btn-block">Cadastrar</button>
												</div>
											</div>
										</form>
									</div>
								</div>
							</div>
						</div></td>
					<td><a class="btn btn-outline-danger btn-block btn-sm"
						data-toggle="modal"
						data-target="#modalDeletarAluno${posicao.index}"> <img
							class="img-fluid" src="img/lixeira.png">
					</a>

						<div class="modal fade" id="modalDeletarAluno${posicao.index}"
							tabindex="-1" role="dialog">
							<div class="modal-dialog modal-dialog-centered" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title">Deseja deletar esse registro?</h5>
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Fechar">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>

									<div class="modal-body">
										<div class="row">
											<div class="col">
												<button type="button" class="btn btn-primary btn-block"
													data-dismiss="modal">Não</button>
											</div>
											<div class="col">
												<a class="btn btn-danger btn-block" href="deletaraluno?ialuno=${posicao.index}">Sim</a>
													
											</div>
										</div>
									</div>
								</div>
							</div>
						</div></td>
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