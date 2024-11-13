package com.mistergold.mistergold.adapters.web;

import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResponseDTO<T>{
    Long pageSize;
    int totalElements;
    int totalPages;
    int currentPage;
    int nextPage;
    int previousPage;
    Set<T> content;
}
