package com.mistergold.mistergold.adapters.web.in.category;

import com.mistergold.mistergold.adapters.web.in.category.dto.CategoryDTO;
import com.mistergold.mistergold.adapters.web.in.category.mapper.CategoryWebMapper;
import com.mistergold.mistergold.application.ports.in.category.DeleteCategoryUseCase;
import com.mistergold.mistergold.application.ports.in.category.SaveCategoryUseCase;
import com.mistergold.mistergold.application.ports.in.category.SearchCategoryUseCase;
import com.mistergold.mistergold.application.ports.in.category.UpdateCategoryUseCase;
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
@RequestMapping("/categorias")
@Tag(name = "categorias")
public class CategoryResource {
    private final SearchCategoryUseCase searchCategoryUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;
    private final DeleteCategoryUseCase deleteCategoryUseCase;
    private final SaveCategoryUseCase saveCategoryUseCase;
    private final CategoryWebMapper mapper;

    @Operation(summary = "Busca por uma categoria na base de dados pelo Id.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria encontrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos!"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos!"),
            @ApiResponse(responseCode = "500", description = "Falha no serviço de buscar categoria!"),
    })
    @GetMapping("/listartodos")
    public ResponseEntity<List<CategoryDTO>> findAll() {
        return ResponseEntity.ok().body(mapper.mapToDTO(searchCategoryUseCase.findAll()));
    }

    @Operation(summary = "Busca por uma categoria na base de dados pelo Id.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria encontrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos!"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos!"),
            @ApiResponse(responseCode = "500", description = "Falha no serviço de buscar categoria!"),
    })
    @GetMapping("/{id}/id")
    public ResponseEntity<CategoryDTO> findById(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok().body(mapper.mapToDTO(searchCategoryUseCase.findById(id)));
    }

    @Operation(summary = "Salva uma categoria na base de dados.", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria salvo com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos!"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos!"),
            @ApiResponse(responseCode = "500", description = "Falha no serviço de salvar categoria!"),
    })
    @PostMapping("/salvar")
    public ResponseEntity<CategoryDTO> save(@Valid @RequestBody CategoryDTO categoryDTO) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.mapToDTO(saveCategoryUseCase.save(mapper.mapToDomain(categoryDTO))));
    }

    @Operation(summary = "Atualiza uma categoria na base de dados pelo Id.", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria atualizado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos!"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos!"),
            @ApiResponse(responseCode = "500", description = "Falha no serviço de atualizar categoria!"),
    })
    @PutMapping("/{id}/atualizar")
    public ResponseEntity<CategoryDTO> update(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable(name = "id") String id) {
        return ResponseEntity.ok().body(mapper.mapToDTO(updateCategoryUseCase.update(mapper.mapToDomain(categoryDTO), id)));
    }

    @Operation(summary = "Desativa uma categoria na base de dados pelo Id.", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria desativado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos!"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos!"),
            @ApiResponse(responseCode = "500", description = "Falha no serviço de desativar categoria!"),
    })
    @DeleteMapping("/{id}/desativar")
    public ResponseEntity<CategoryDTO> inactivate(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok().body(mapper.mapToDTO(deleteCategoryUseCase.inactivate(id)));
    }

    @Operation(summary = "Deleta uma categoria na base de dados pelo Id.", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria deletado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos!"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos!"),
            @ApiResponse(responseCode = "500", description = "Falha no serviço de deletar categoria!"),
    })
    @DeleteMapping("/{id}/deletar")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") String id) {
        deleteCategoryUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
