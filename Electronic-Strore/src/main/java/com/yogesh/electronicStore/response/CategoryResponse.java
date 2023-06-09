package com.yogesh.electronicStore.response;

import com.yogesh.electronicStore.payloads.CategoryDto;
import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponse {

    private List<CategoryDto> content;

    private int pageNumber;

    private int pageSize;

    private long totalElement;

    private int totalPage;

    private boolean lastPage;
}
