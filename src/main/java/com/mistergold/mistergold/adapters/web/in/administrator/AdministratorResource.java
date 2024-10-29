package com.mistergold.mistergold.adapters.web.in.administrator;

import com.mistergold.mistergold.adapters.web.in.administrator.dto.AdministratorDTO;
import com.mistergold.mistergold.adapters.web.in.administrator.mapper.AdministratorWebMapper;
import com.mistergold.mistergold.application.ports.in.administrator.DeleteAdministratorUseCase;
import com.mistergold.mistergold.application.ports.in.administrator.SaveAdministratorUseCase;
import com.mistergold.mistergold.application.ports.in.administrator.SearchAdministratorUseCase;
import com.mistergold.mistergold.application.ports.in.administrator.UpdateAdministratorUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/administradores")
@Tag(name = "administradores")
public class AdministratorResource {
    private final SearchAdministratorUseCase searchAdministratorUseCase;
    private final UpdateAdministratorUseCase updateAdministratorUseCase;
    private final DeleteAdministratorUseCase deleteAdministratorUseCase;
    private final SaveAdministratorUseCase saveAdministratorUseCase;
    private final AdministratorWebMapper mapper;

    @Operation(summary = "Busca por um administrador na base de dados pelo Id.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Administrador encontrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos!"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos!"),
            @ApiResponse(responseCode = "500", description = "Falha no serviço de buscar administrador!"),
    })
    @GetMapping("/listartodos")
    public ResponseEntity<List<AdministratorDTO>> findAll() {
        return ResponseEntity.ok().body(mapper.mapToDTO(searchAdministratorUseCase.findAll()));
    }

    @Operation(summary = "Busca por um administrador na base de dados pelo Id.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Administrador encontrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos!"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos!"),
            @ApiResponse(responseCode = "500", description = "Falha no serviço de buscar administrador!"),
    })
    @GetMapping("/{id}/id")
    public ResponseEntity<AdministratorDTO> findById(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok().body(mapper.mapToDTO(searchAdministratorUseCase.findById(id)));
    }

    @Operation(summary = "Busca por um administrador na base de dados pelo E-mail.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Administrador encontrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos!"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos!"),
            @ApiResponse(responseCode = "500", description = "Falha no serviço de buscar administrador!"),
    })
    @GetMapping("/{email}/email")
    public ResponseEntity<AdministratorDTO> findByEmail(@PathVariable(name = "email") String email) {
        return ResponseEntity.ok().body(mapper.mapToDTO(searchAdministratorUseCase.findByEmail(email)));
    }

    @Operation(summary = "Salva um administrador na base de dados.", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Administrador salvo com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos!"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos!"),
            @ApiResponse(responseCode = "500", description = "Falha no serviço de salvar administrador!"),
    })
    @PostMapping("/salvar")
    public ResponseEntity<AdministratorDTO> save(@Valid @RequestBody AdministratorDTO administratorDTO) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.mapToDTO(saveAdministratorUseCase.save(mapper.mapToDomain(administratorDTO))));
    }

    @Operation(summary = "Atualiza um administrador na base de dados pelo Id.", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Administrador atualizado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos!"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos!"),
            @ApiResponse(responseCode = "500", description = "Falha no serviço de atualizar administrador!"),
    })
    @PutMapping("/{id}/atualizar")
    public ResponseEntity<AdministratorDTO> update(@Valid @RequestBody AdministratorDTO administratorDTO, @PathVariable(name = "id") String id) {
        return ResponseEntity.ok().body(mapper.mapToDTO(updateAdministratorUseCase.update(mapper.mapToDomain(administratorDTO), id)));
    }

    @Operation(summary = "Desativa um administrador na base de dados pelo Id.", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Administrador desativado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos!"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos!"),
            @ApiResponse(responseCode = "500", description = "Falha no serviço de desativar administrador!"),
    })
    @DeleteMapping("/{id}/desativar")
    public ResponseEntity<AdministratorDTO> inactivate(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok().body(mapper.mapToDTO(deleteAdministratorUseCase.inactivate(id)));
    }

    @Operation(summary = "Deleta um administrador na base de dados pelo Id.", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Administrador deletado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos!"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos!"),
            @ApiResponse(responseCode = "500", description = "Falha no serviço de deletar administrador!"),
    })
    @DeleteMapping("/{id}/deletar")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") String id) {
        deleteAdministratorUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
