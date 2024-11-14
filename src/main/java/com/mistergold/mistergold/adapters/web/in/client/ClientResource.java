package com.mistergold.mistergold.adapters.web.in.client;

import com.mistergold.mistergold.adapters.web.PageResponseDTO;
import com.mistergold.mistergold.adapters.web.in.client.dto.ClientDTO;
import com.mistergold.mistergold.adapters.web.in.client.dto.RecoveryDTO;
import com.mistergold.mistergold.adapters.web.in.client.mapper.ClientWebMapper;
import com.mistergold.mistergold.application.ports.in.client.DeleteClientUseCase;
import com.mistergold.mistergold.application.ports.in.client.SaveClientUseCase;
import com.mistergold.mistergold.application.ports.in.client.SearchClientUseCase;
import com.mistergold.mistergold.application.ports.in.client.UpdateClientUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clientes")
@Tag(name = "clientes")
public class ClientResource {
    private final SearchClientUseCase searchClientUseCase;
    private final UpdateClientUseCase updateClientUseCase;
    private final DeleteClientUseCase deleteClientUseCase;
    private final SaveClientUseCase saveClientUseCase;
    private final ClientWebMapper mapper;

    @Operation(summary = "Lista todos as cliente por nome e por status de ativação de forma paginada.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente listados com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos!"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos!"),
            @ApiResponse(responseCode = "500", description = "Falha no serviço listar cliente!"),
    })
    @GetMapping
    public ResponseEntity<PageResponseDTO<ClientDTO>> findByPagination(
            @RequestParam(value = "isActive", defaultValue = "true", required = false)
            Boolean isActive,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false)
            @Min(value = 1, message = "O valor de pageSize deve ser no minimo 10")
            @Max(value = 30, message = "O valor de pageSize deve ser no maximo 30")
            Integer pageSize,
            @RequestParam(value = "page", defaultValue = "0", required = false)
            Integer page,
            @RequestParam(value = "name", required = false)
            String name) {
        return ResponseEntity.ok().body(mapper.mapToPageResponseDto(searchClientUseCase.findByPagination(isActive, page, pageSize, name)));
    }

    @Operation(summary = "Busca por um cliente na base de dados pelo Id.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos!"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos!"),
            @ApiResponse(responseCode = "500", description = "Falha no serviço de buscar cliente!"),
    })
    @GetMapping("/{id}/id")
    public ResponseEntity<ClientDTO> findById(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok().body(mapper.mapToDTO(searchClientUseCase.findById(id)));
    }

    @Operation(summary = "Método de recuperação de senha, gerando código aleatório e enviado pelo e-mail.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Código gerado e enviado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos!"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos!"),
            @ApiResponse(responseCode = "500", description = "Falha no serviço de recuperar senha!"),
    })
    @GetMapping("/{email}/recuperacao")
    public ResponseEntity<Void> recoveryPasswordGenerator(@PathVariable(name = "email") String email) {
        searchClientUseCase.recoveryPasswordGenerator(email);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Método de recuperação de senha, verificando código e trocando senha.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Código verificado e senha trocada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos!"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos!"),
            @ApiResponse(responseCode = "500", description = "Falha no serviço de recuperar senha!"),
    })
    @PostMapping("/{email}/recuperacao/codigo")
    public ResponseEntity<Void> recoveryPasswordSet(@PathVariable(name = "email") String email, @RequestBody RecoveryDTO recoveryDTO) {
        updateClientUseCase.recoveryPasswordSet(email, mapper.mapToDomain(recoveryDTO));
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Salva um cliente na base de dados.", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente salvo com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos!"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos!"),
            @ApiResponse(responseCode = "500", description = "Falha no serviço de salvar cliente!"),
    })
    @PostMapping("/salvar")
    public ResponseEntity<ClientDTO> save(@Valid @RequestBody ClientDTO clientDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.mapToDTO(saveClientUseCase.save(mapper.mapToDomain(clientDTO))));
    }

    @Operation(summary = "Atualiza um cliente na base de dados pelo Id.", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos!"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos!"),
            @ApiResponse(responseCode = "500", description = "Falha no serviço de atualizar cliente!"),
    })
    @PutMapping("/{id}/atualizar")
    public ResponseEntity<ClientDTO> update(@Valid @RequestBody ClientDTO clientDTO, @PathVariable(name = "id") String id) {
        return ResponseEntity.ok().body(mapper.mapToDTO(updateClientUseCase.update(mapper.mapToDomain(clientDTO), id)));
    }

    @Operation(summary = "Desativa um cliente na base de dados pelo Id.", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente desativado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos!"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos!"),
            @ApiResponse(responseCode = "500", description = "Falha no serviço de desativar cliente!"),
    })
    @DeleteMapping("/{id}/desativar")
    public ResponseEntity<ClientDTO> inactivate(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok().body(mapper.mapToDTO(deleteClientUseCase.inactivate(id)));
    }

    @Operation(summary = "Deleta um cliente na base de dados pelo Id.", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente deletado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos!"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos!"),
            @ApiResponse(responseCode = "500", description = "Falha no serviço de deletar cliente!"),
    })
    @DeleteMapping("/{id}/deletar")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") String id) {
        deleteClientUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
