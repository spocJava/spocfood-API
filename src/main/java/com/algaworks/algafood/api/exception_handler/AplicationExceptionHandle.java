package com.algaworks.algafood.api.exception_handler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.algaworks.algafood.domain.exeptions.NegocioException;
import com.algaworks.algafood.domain.exeptions.entity_in_used_exception.EntidadeEmUsoExeption;
import com.algaworks.algafood.domain.exeptions.entity_not_found_exception.EntidadeNaoEncontradaExecption;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

/**
 * Classe responsável por capturar todas as exceptions da aplicação para
 * custimizar e padronizar a resposta do erro para os clientes da API.
 * @author spoc
 * @version 1.0.0
 *
 */
@ControllerAdvice //--Classe para agrupar os ExceptionHandlers
public class AplicationExceptionHandle extends ResponseEntityExceptionHandler{
	
	@Autowired
	MessageSource messageSource; // resolve mensagens

	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		return handleValidationInternal(ex, headers, status, request, ex.getBindingResult());
	}

	//---Esse metodo customiza a exception MethodArgumentNotValidException
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return handleValidationInternal(ex, headers, status, request, ex.getBindingResult());
	}

	private ResponseEntity<Object> handleValidationInternal(Exception ex, HttpHeaders headers, HttpStatus status, WebRequest request,
					BindingResult bindingResult) {
		HandleErrorType type = HandleErrorType.DADOS_INVALIDOS;
		String detail = "Um ou mais campos estão inválido. faça o preenchimento correto e tente novamente";
		List<HandleErrorMensage.Field> problemFields = bindingResult.getFieldErrors().stream()
				.map(fieldError -> {
					String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			     return HandleErrorMensage.Field.builder() //            1ª - transforma uma list de fieldErro em uma list de objetos problemFields
						.name(fieldError.getField()) //                  2ª - pega o nome do campo
						.userMessage(message) //                         3ª - pega a mensagem do erro
						.build();//                                      4ª - cria o(s) objeto Field
	             })//                                                    5ª - coleta os objetos e forma uma list de Fields passada para problemFields
				.collect(Collectors.toList());

		HandleErrorMensage problem = createHandleErrorMensage(status, type, detail)
				.userMessage(detail)
				.timestamp(LocalDateTime.now())
				.fields(problemFields)
				.build();

		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	/**
	 * Esse ExceptionHandle pode capturar exceções com diferentes causas.
	 * EXEM:  InvalidFormatException
	 *        JsonMappinException
	 * Como queremos customizar a resposta de forma mais especifica, vamos verificar qual é
	 * a real causa da exception e trata-la separadamente de forma bem especifica.
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		//-----------Caso a exception seja causada por outra-----------------------------------------------------
		Throwable rootCause = ExceptionUtils.getRootCause(ex);
		
		if(rootCause instanceof InvalidFormatException) {
			return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
		}
		else if(rootCause instanceof UnrecognizedPropertyException) {
			return (ResponseEntity<Object>) handleUnrecognizedPropertyException((UnrecognizedPropertyException) rootCause, headers, status, request);
		}
		else if(rootCause instanceof IgnoredPropertyException) {
			return handleIgnoredPropertyException((IgnoredPropertyException) rootCause, headers, status, request);
		}
		
		//------------Customização padrão caso a exception não tenha outra causa----------------------------------
		HandleErrorType erroType = HandleErrorType.SINTASE_ERRO_REQUIZICAO;
		String detail = "A um erro inesperado em sua requizição, verifique os paramentros e faça outra requizição.";
		HandleErrorMensage problem = createHandleErrorMensage(status, erroType, detail).build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	

	//---Metodo para customizar o <InvalidFormatException>
	private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers
			,HttpStatus status, WebRequest request) {
		
		String path = joinPath(ex.getPath());
		
		HandleErrorType erroType = HandleErrorType.SINTASE_ERRO_REQUIZICAO;
		String detail = String.format("Foi atribuida á propriedade ['%s'] um valor inválido ['%s'], atribua um valor do tipo <'%s'>", 
				path, ex.getValue(), ex.getTargetType().getSimpleName());
		
		HandleErrorMensage problem = createHandleErrorMensage(status, erroType, detail)
				.userMessage(detail)
				.timestamp(LocalDateTime.now())
				.build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	
	//---Metodo para customizar o <UnrecognizedPropertyException>
	private ResponseEntity<?> handleUnrecognizedPropertyException(UnrecognizedPropertyException ex, HttpHeaders headers
			,HttpStatus status, WebRequest request) {
		
		String path = joinPath(ex.getPath());
		
		HandleErrorType erroType = HandleErrorType.SINTASE_ERRO_REQUIZICAO;
		String detail = String.format("A propriedade '%s' não foi reconhecida para essa requiziçao", path);
		HandleErrorMensage problem = createHandleErrorMensage(status, erroType, detail)
				.userMessage(String.format("A requizição Http possui uma propriedade não reconhecida pela API: -> prop=(%s)", path))
				.timestamp(LocalDateTime.now())
				.build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	
	//---Metodo para customizar o <IgnoredPropertyException>
	private ResponseEntity<Object> handleIgnoredPropertyException(IgnoredPropertyException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String path = joinPath(ex.getPath());
						
		HandleErrorType erroType = HandleErrorType.SINTASE_ERRO_REQUIZICAO;
		String detail = String.format("A propriedade ['%s'] não pode ser deserializada, pois está ignorada com o (JsonIgnore).", path);
		HandleErrorMensage problem = createHandleErrorMensage(status, erroType, detail)
				.userMessage(detail)
				.timestamp(LocalDateTime.now())
				.build();

		return handleExceptionInternal(ex, problem, headers, status, request);

	}
	
	
	//---metodo auxiliar para buscar a propriedade com erro...
	private String joinPath(List<Reference> references) {
		return references.stream()
			.map(Reference -> Reference.getFieldName())
			.collect(Collectors.joining("."));
	}
	
	
	//----HandleException para customizar as exceptions do tipo <TypeMismatchException> ---
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		if(ex instanceof MethodArgumentTypeMismatchException) {
			return handleMethodArgumentTypeMismatchException((MethodArgumentTypeMismatchException) ex , headers, status, request);
		}
		
		HandleErrorType erroType = HandleErrorType.PARAMETRO_INVALIDO;
		String detail = "O paramentro passado na URL não é de um tipo válido.";
		HandleErrorMensage problem = createHandleErrorMensage(status, erroType, detail).build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	
	//----HandleException para customizar as exceptions do tipo <MethodArgumentTypeMismatchException> ---
	private ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, HttpHeaders headers
			, HttpStatus status, WebRequest request ) {
		
		HandleErrorType erroType = HandleErrorType.PARAMETRO_INVALIDO;
		String detail = String.format("O parâmetro de URL '%s' recebeu o valor '%s', "
                        + "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.", ex.getName(), ex.getValue(), 
                           ex.getRequiredType().getSimpleName());
		
		HandleErrorMensage problem =  createHandleErrorMensage(status, erroType, detail)
				.userMessage(detail)
				.timestamp(LocalDateTime.now())
				.build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	
	//----HandleException para customizar as exceptions do tipo NoHandlerFoundException ---
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		HandleErrorType erroType = HandleErrorType.RECURSO_NAO_ENCONTRADO;
		String detail = String.format("O recurso ['%s'] não existe", ex.getRequestURL());
		
		HandleErrorMensage problem = createHandleErrorMensage(status, erroType, detail)
				.userMessage(String.format("Não existe um endpoint %s na API, verifique se està correta a requizição", ex.getRequestURL()))
				.timestamp(LocalDateTime.now())
				.build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	
	//----HandleException para customizar as exceptions do tipo EntidadeNaoEncontradaException ---
	@ExceptionHandler(EntidadeNaoEncontradaExecption.class)
	public ResponseEntity<?> handleEntidadeNãoEncontradaExeption(EntidadeNaoEncontradaExecption ex, WebRequest request){
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		HandleErrorType erroType = HandleErrorType.RECURSO_NAO_ENCONTRADO;
		String detail = ex.getMessage();
		
		HandleErrorMensage problem = createHandleErrorMensage(status, erroType, detail)
				.userMessage(detail)
				.timestamp(LocalDateTime.now())
				.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	
	//----ExceptionHandler para customizar as exceptions do tipo EntidadeEmUsoException ---
	@ExceptionHandler(EntidadeEmUsoExeption.class)
	public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoExeption ex, WebRequest request){
		
		HttpStatus status = HttpStatus.CONFLICT;
		HandleErrorType erroType = HandleErrorType.ENTIDADE_EM_USO;
		String detail = ex.getMessage();
		
		HandleErrorMensage problem = createHandleErrorMensage(status, erroType, detail)
				.userMessage(detail)
				.timestamp(LocalDateTime.now())
				.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.CONFLICT, request);
	} 
	
	
	//----ExceptionHandler para customizar as exceptions do tipo NegocioException ---
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> handleNegocioException(NegocioException ex, WebRequest request){
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		HandleErrorType erroType = HandleErrorType.ERRO_NEGOCIO;
		String detail = ex.getMessage();
		
		HandleErrorMensage problem = createHandleErrorMensage(status, erroType, detail)
				.userMessage(ex.getMessage())
				.timestamp(LocalDateTime.now())
				.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	
	//----Metedo central para customizar a representação das exceptionHandlers----
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		if(body == null) {
			body = HandleErrorMensage.builder().status(status.value())
				.title(status.getReasonPhrase())
				.build();
			
		}else if(body instanceof String)
		    body = HandleErrorMensage.builder().status(status.value())
		        .title((String) body)
				.build();
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	
	//---Metodo para ajudar a instanciar o HandleErrorMensage com problemas diferentes ---- 
	private HandleErrorMensage.HandleErrorMensageBuilder createHandleErrorMensage(HttpStatus status, 
			HandleErrorType erroType, String detail){
		
		return HandleErrorMensage.builder().status(status.value())
				.type(erroType.getType())
				.title(erroType.getTitle())
				.detail(detail);
	}
	
	
	//---ExceptionHandle geral ----
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleTopException(Exception ex, WebRequest request){
		
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		
		HandleErrorType erroType = HandleErrorType.ERRO_DE_SISTEMA;
		String detail = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se o problema persistir, entre em contato"
				  + "com o administrador do sistema";
		
		ex.printStackTrace();
		
		HandleErrorMensage problem = createHandleErrorMensage(status, erroType, detail).build();
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
}
