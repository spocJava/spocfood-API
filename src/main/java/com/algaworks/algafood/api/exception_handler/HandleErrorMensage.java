package com.algaworks.algafood.api.exception_handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Classe para montar a resposta contendo as informações pertinentes ao erro
 * de acordo com a RFC 7807 (problem Details for HTTP APIs).
 * @author spoc
 * @version 1.0.0
 */
@JsonInclude(Include.NON_NULL)//---só inclui na representação se não for null
@Getter
@Builder
public class HandleErrorMensage {

	private Integer status;
	private String type;
	private String title;  // Padrão Problem detail
	private String detail;
	
	private String userMessage;
	private LocalDateTime timestamp;  // extenções do padrão
	private List<Field> fields;
	
	@Getter
	@Builder
	public static class Field {        // classe interna contendo o nome do campo que teve uma violaçao de validação
		private String name;           // com uma mensagem sobre detalhes da violação.
		private String userMessage;
	}
	
}
