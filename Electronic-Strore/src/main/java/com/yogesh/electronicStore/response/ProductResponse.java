package com.yogesh.electronicStore.response;

import com.yogesh.electronicStore.payloads.CategoryDto;
import com.yogesh.electronicStore.payloads.ProductDto;
import lombok.*;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {

    private List<ProductDto> content;

    private int pageNumber;

    private int pageSize;

    private long totalElement;

    private int totalPage;

    private boolean lastPage;
}
