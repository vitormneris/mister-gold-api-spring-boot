package com.mistergold.mistergold.adapters.web.in.order;

import com.mistergold.mistergold.adapters.web.PageResponseDTO;
import com.mistergold.mistergold.adapters.web.in.order.dto.OrderDTO;
import com.mistergold.mistergold.adapters.web.in.order.mapper.OrderWebMapper;
import com.mistergold.mistergold.application.ports.in.order.SaveOrderUseCase;
import com.mistergold.mistergold.application.ports.in.order.SearchOrderUseCase;
import com.mistergold.mistergold.application.ports.in.order.UpdateOrderUseCase;
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
@RequestMapping("/pedidos")
@Tag(name = "pedidos")
public class OrderResource {
    private final SearchOrderUseCase searchOrderUseCase;
    private final UpdateOrderUseCase updateOrderUseCase;
    private final SaveOrderUseCase saveOrderUseCase;
    private final OrderWebMapper mapper;

    @Operation(summary = "Lista todos as pedido por nome e por status de ativação de forma paginada.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido listados com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos!"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos!"),
            @ApiResponse(responseCode = "500", description = "Falha no serviço listar pedido!"),
    })
    @GetMapping
    public ResponseEntity<PageResponseDTO<OrderDTO>> findByPagination(
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
        return ResponseEntity.ok().body(mapper.mapToPageResponseDto(searchOrderUseCase.findByPagination(isActive, page, pageSize, name)));
    }

    @Operation(summary = "Busca por um pedido na base de dados pelo Id.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido encontrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos!"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos!"),
            @ApiResponse(responseCode = "500", description = "Falha no serviço de buscar pedido!"),
    })
    @GetMapping("/{id}/id")
    public ResponseEntity<OrderDTO> findById(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok().body(mapper.mapToDTO(searchOrderUseCase.findById(id)));
    }

    @Operation(summary = "Salva um pedido na base de dados.", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido salvo com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos!"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos!"),
            @ApiResponse(responseCode = "500", description = "Falha no serviço de salvar pedido!"),
    })
    @PostMapping("/salvar")
    public ResponseEntity<OrderDTO> save(@Valid @RequestBody OrderDTO orderDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.mapToDTO(saveOrderUseCase.save(mapper.mapToDomain(orderDTO))));
    }

    @Operation(summary = "Atualiza um pedido na base de dados pelo Id.", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido atualizado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos!"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos!"),
            @ApiResponse(responseCode = "500", description = "Falha no serviço de atualizar pedido!"),
    })
    @PutMapping("/{id}/atualizar")
    public ResponseEntity<OrderDTO> update(@RequestBody OrderDTO orderDTO, @PathVariable(name = "id") String id) {
        return ResponseEntity.ok().body(mapper.mapToDTO(updateOrderUseCase.update(mapper.mapToDomain(orderDTO), id)));
    }
}
