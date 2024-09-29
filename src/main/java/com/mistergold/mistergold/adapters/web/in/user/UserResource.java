package com.mistergold.mistergold.adapters.web.in.user;

import com.mistergold.mistergold.adapters.web.in.user.dto.UserDTO;
import com.mistergold.mistergold.adapters.web.in.user.mapper.UserWebMapper;
import com.mistergold.mistergold.application.ports.in.user.DeleteUserUseCase;
import com.mistergold.mistergold.application.ports.in.user.SaveUserUseCase;
import com.mistergold.mistergold.application.ports.in.user.SearchUserUseCase;
import com.mistergold.mistergold.application.ports.in.user.UpdateUserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios")
@Tag(name = "usuarios")
public class UserResource {
    private final SearchUserUseCase searchUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final SaveUserUseCase saveUserUseCase;
    private final UserWebMapper mapper;

    @Operation(summary = "Busca por um usuário na base de dados pelo Id.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos!"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos!"),
            @ApiResponse(responseCode = "500", description = "Falha no serviço de buscar usuário!"),
    })
    @GetMapping("/listartodos")
    public ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity.ok().body(mapper.mapToDTO(searchUserUseCase.findAll()));
    }

    @Operation(summary = "Busca por um usuário na base de dados pelo Id.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos!"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos!"),
            @ApiResponse(responseCode = "500", description = "Falha no serviço de buscar usuário!"),
    })
    @GetMapping("/{id}/id")
    public ResponseEntity<UserDTO> findById(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok().body(mapper.mapToDTO(searchUserUseCase.findById(id)));
    }

    @Operation(summary = "Busca por um usuário na base de dados pelo E-mail.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos!"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos!"),
            @ApiResponse(responseCode = "500", description = "Falha no serviço de buscar usuário!"),
    })
    @GetMapping("/{email}/email")
    public ResponseEntity<UserDTO> findByEmail(@PathVariable(name = "email") String email) {
        return ResponseEntity.ok().body(mapper.mapToDTO(searchUserUseCase.findByEmail(email)));
    }

    @Operation(summary = "Salva um usuário na base de dados.", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário salvo com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos!"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos!"),
            @ApiResponse(responseCode = "500", description = "Falha no serviço de salvar usuário!"),
    })
    @PostMapping("/salvar")
    public ResponseEntity<UserDTO> save(@RequestBody UserDTO userDTO) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.mapToDTO(saveUserUseCase.save(mapper.mapToDomain(userDTO))));
    }

    @Operation(summary = "Atualiza um usuário na base de dados pelo Id.", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos!"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos!"),
            @ApiResponse(responseCode = "500", description = "Falha no serviço de atualizar usuário!"),
    })
    @PutMapping("/{id}/atualizar")
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDTO, @PathVariable(name = "id") String id) {
        return ResponseEntity.ok().body(mapper.mapToDTO(updateUserUseCase.update(mapper.mapToDomain(userDTO), id)));
    }

    @Operation(summary = "Desativa um usuário na base de dados pelo Id.", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário desativado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos!"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos!"),
            @ApiResponse(responseCode = "500", description = "Falha no serviço de desativar usuário!"),
    })
    @DeleteMapping("/{id}/desativar")
    public ResponseEntity<UserDTO> inactivate(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok().body(mapper.mapToDTO(deleteUserUseCase.inactivate(id)));
    }

    @Operation(summary = "Deleta um usuário na base de dados pelo Id.", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos!"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos!"),
            @ApiResponse(responseCode = "500", description = "Falha no serviço de deletar usuário!"),
    })
    @DeleteMapping("/{id}/deletar")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") String id) {
        deleteUserUseCase.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
