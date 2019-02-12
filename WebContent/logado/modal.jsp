<!-- Modals -->
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
							<label>Quantidade de vales do último turno: ${sessionScope.turno.quantVales }</label>
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
							<div class="col">
								<button type="button" class="btn btn-secondary btn-block"
									data-dismiss="modal">Cancelar</button>
							</div>
							<div class="col">
								<button type="submit" class="btn btn-primary btn-block">Iniciar</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="modalCadastrarEscola" tabindex="-1"
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
					<form action="cadastrarescola">
						<div class="form-group">
							<label>Quantidade de vales do último turno: ${sessionScope.turno.quantVales }</label>
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
							<div class="col">
								<button type="button" class="btn btn-secondary btn-block"
									data-dismiss="modal">Cancelar</button>
							</div>
							<div class="col">
								<button type="submit" class="btn btn-primary btn-block">Iniciar</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<!-- Modal encerrar turno -->
	<div class="modal fade" id="modalEncerrarTurno" tabindex="-1"
		role="dialog">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Tem certeza que deseja encerrar seu
						turno?</h5>
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
							<a class="btn btn-danger btn-block" href="encerrarturno">Sim</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>