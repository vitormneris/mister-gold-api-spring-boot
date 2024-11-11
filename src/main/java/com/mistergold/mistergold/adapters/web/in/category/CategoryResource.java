package com.mistergold.mistergold.adapters.web.in.category;

import com.mistergold.mistergold.adapters.web.PageResponseDTO;
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
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @Operation(summary = "Lista todos as categoria por nome e por status de ativação de forma paginada.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria listados com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos!"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos!"),
            @ApiResponse(responseCode = "500", description = "Falha no serviço listar categoria!"),
    })
    @GetMapping
    public ResponseEntity<PageResponseDTO<CategoryDTO>> findByPagination(
            @RequestParam(value = "isActive", defaultValue = "true", required = false)
            Boolean isActive,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false)
            @Min(value = 10, message = "O valor de pageSize deve ser no minimo 10")
            @Max(value = 30, message = "O valor de pageSize deve ser no maximo 30")
            Integer pageSize,
            @RequestParam(value = "page", defaultValue = "0", required = false)
            Integer page,
            @RequestParam(value = "name", required = false)
            String name) {
        return ResponseEntity.ok().body(mapper.mapToPageResponseDto(searchCategoryUseCase.findByPagination(isActive, page, pageSize, name)));
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
    public ResponseEntity<CategoryDTO> save(@Valid @RequestPart(value = "category") CategoryDTO categoryDTO, @RequestPart(value = "file") MultipartFile file) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.mapToDTO(saveCategoryUseCase.save(mapper.mapToDomain(categoryDTO), file)));
    }

    @Operation(summary = "Atualiza uma categoria na base de dados pelo Id.", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria atualizado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos!"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos!"),
            @ApiResponse(responseCode = "500", description = "Falha no serviço de atualizar categoria!"),
    })
    @PutMapping("/{id}/atualizar")
    public ResponseEntity<CategoryDTO> update(@Valid @RequestPart(value = "category") CategoryDTO categoryDTO, @RequestPart(value = "file", required = false) MultipartFile file, @PathVariable(name = "id") String id) {
        return ResponseEntity.ok().body(mapper.mapToDTO(updateCategoryUseCase.update(mapper.mapToDomain(categoryDTO), file, id)));
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
