var senha = document.getElementById("senha"), 
	confirmasenha = document.getElementById("confirmacaosenha");

//esconde o botão cadastrar
document.getElementById("botaocadastrar").style.display = "none";
//esconde a caixa de confirmar senha
document.getElementById("confirmacaosenhadiv").style.display = "none";
//esconde mensagem de senha curta
document.getElementById("mensagemsenha").style.display = "none";
//esconde mensagem de confirmação de senha 
//document.getElementById("mensagemconfirmacaosenha").style.display = "none";

function compararSenha() {
	if (senha.value != confirmasenha.value) {
		document.getElementById("mensagemconfirmacaosenha").style.display = "inline";
		document.getElementById("botaocadastrar").style.display = "none";
	} else {
		document.getElementById("mensagemconfirmacaosenha").style.display = "none";
		document.getElementById("botaocadastrar").style.display = "inline";
	}
}

function validarTamanhoSenha(){
	if (senha.value.length > 5){
		document.getElementById("confirmacaosenhadiv").style.display = "inline";
		document.getElementById("mensagemsenha").style.display = "none";
		compararSenha();
	} else {
		document.getElementById("confirmacaosenhadiv").style.display = "none";
		document.getElementById("mensagemsenha").style.display = "inline";
	}
}

senha.onkeyup = validarTamanhoSenha;
confirmasenha.onkeyup = compararSenha;