package com.algaworks.algafood.api.exception_handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;

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
	private String title;
	private String detail;
	
}
