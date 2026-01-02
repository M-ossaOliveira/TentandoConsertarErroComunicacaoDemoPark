package com.mballem.demoparkapi.web.controller;
import com.mballem.demoparkapi.entity.ClienteVaga;
import com.mballem.demoparkapi.service.EstacionamentoService;
import com.mballem.demoparkapi.web.dto.EstacionamentoCreateDto;
import com.mballem.demoparkapi.web.dto.EstacionamentoResponseDto;
import com.mballem.demoparkapi.web.dto.mapper.ClienteVagaMapper;
import com.mballem.demoparkapi.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/v1/estacionamentos")
@RequiredArgsConstructor
public class EstacionamentoController {
    //injetar estacionamento service
    private final EstacionamentoService estacionamentoService;

    //1o metodo

    @Operation(summary = "Operação de check-in",description = "Recurso para dar entrada em " +
            "veículo no estacionamento. Requisição exige uso de bearer token. Acesso restrito" +
            "a Role= 'ADMIN'",security = @SecurityRequirement(name="security"),
            responses={
                @ApiResponse(responseCode = "201",description = "Recurso criado com sucesso",
                    headers = @Header(name= HttpHeaders.LOCATION,description = "URL de acesso" +
                            "a recurso criado"),
                    content = @Content(mediaType = "application/json;charset=UTF-8",
                    schema = @Schema(implementation = EstacionamentoResponseDto.class))),
                    @ApiResponse(responseCode = "404",description = "CPF do cliente não cadastrado no sistema" +
                            "</br>-Nenhuma vaga livre foi localizada",
                            content = @Content(mediaType = "application/json;charset=UTF-8",
                                    schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "422",description = "Recurso não processado por falta de dados" +
                            "OU dados inválidos",
                            content = @Content(mediaType = "applications/json;charset=UTF-8",
                                    schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "403",description = "Recurso não permitido a Role=CLIENTE",
                            content = @Content(mediaType = "application/json;charset=UTF-8",
                                    schema = @Schema(implementation = ErrorMessage.class))),
            })

    @PostMapping("/check-in")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EstacionamentoResponseDto> checkin
            (@RequestBody @Valid EstacionamentoCreateDto dto){
        //instrução transforma objeto dto em clienteVaga
        ClienteVaga clienteVaga= ClienteVagaMapper.toClienteVaga(dto);
        estacionamentoService.checkIn(clienteVaga);
        //transforma o que for retornado pelo service em um responseDto
        EstacionamentoResponseDto responseDto = ClienteVagaMapper.toDto(clienteVaga);
        URI location= ServletUriComponentsBuilder
                .fromCurrentRequestUri().path("/{recibo}")
                .buildAndExpand(clienteVaga.getRecibo())
                .toUri();
        return ResponseEntity.created(location).body(responseDto);
    // retorna cabeçalho com url de acesso + corpo com infos referentes ao processo de criação
        }
}
