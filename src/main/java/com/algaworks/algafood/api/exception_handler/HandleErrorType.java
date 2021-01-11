package com.algaworks.algafood.api.exception_handler;

import lombok.Getter;

/**
 * Esse enum e serve pra ajudar na hora de instanciar um HandleErrorMensage
 * que pode ter varios tipos de erros.
 * EXEMPLO: abaixo temos um HandleErrorMensage do tipo "entidade-nao-encontrada".
 * @see HandleErrorMensage errorMensage = HandleErrorMensage.builder()
				.status(status.value())
				.type("https://spocfood.com.br/entidade-nao-encontrada")
				.title("entidade-nao-encontrada")
				.detail(ex.getMessage())
				.build();
 * @author spoc
 * {@link spoc-java.com.br}
 */
@Getter
public enum HandleErrorType {

	RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso nao encontrado"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
	ERRO_NEGOCIO("/erro-negocio", "Violação de uma regra de negócio"),
	SINTASE_ERRO_REQUIZICAO("/erro-na-requizicao", "Formato de requizição inválida"),
	PARAMETRO_INVALIDO("/parametro-invalido", "Parametro inválido"),
	ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema");
	
	private String title;
	private String type;
	
	private HandleErrorType(String path, String title) {
		this.type = "http://spocfood.com.br" + path;
		this.title = title;
	}
}
