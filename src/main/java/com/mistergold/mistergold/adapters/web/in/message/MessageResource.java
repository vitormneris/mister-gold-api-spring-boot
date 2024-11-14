package com.mistergold.mistergold.adapters.web.in.message;

import com.mistergold.mistergold.adapters.web.PageResponseDTO;
import com.mistergold.mistergold.adapters.web.in.message.dto.MessageDTO;
import com.mistergold.mistergold.adapters.web.in.message.mapper.MessageWebMapper;
import com.mistergold.mistergold.application.ports.in.message.DeleteMessageUseCase;
import com.mistergold.mistergold.application.ports.in.message.SaveMessageUseCase;
import com.mistergold.mistergold.application.ports.in.message.SearchMessageUseCase;
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

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mensagens")
@Tag(name = "mensagens")
public class MessageResource {
    private final DeleteMessageUseCase deleteMessageUseCase;
    private final SearchMessageUseCase searchMessageUseCase;
    private final SaveMessageUseCase saveMessageUseCase;
    private final MessageWebMapper mapper;

    @Operation(summary = "Lista todas as mensagens.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido listados com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos!"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos!"),
            @ApiResponse(responseCode = "500", description = "Falha no serviço listar pedido!"),
    })
    @GetMapping
    public ResponseEntity<Set<MessageDTO>> findByPagination() {
        return ResponseEntity.ok().body(mapper.mapToListDTO(searchMessageUseCase.findAll()));
    }

    @Operation(summary = "Salva uma mensagem na base de dados.", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mensagem salva com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos!"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos!"),
            @ApiResponse(responseCode = "500", description = "Falha no serviço de salvar mensagem!"),
    })
    @PostMapping("/salvar")
    public ResponseEntity<MessageDTO> save(@Valid @RequestBody MessageDTO messageDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.mapToDTO(saveMessageUseCase.save(mapper.mapToDomain(messageDTO))));
    }

    @Operation(summary = "Deleta uma mensagem na base de dados pelo Id.", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mensagem deletado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos!"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos!"),
            @ApiResponse(responseCode = "500", description = "Falha no serviço de deletar mensagem!"),
    })
    @DeleteMapping("/{id}/deletar")
    public ResponseEntity<Void> deleteById(@PathVariable(name = "id") String id) {
        deleteMessageUseCase.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}