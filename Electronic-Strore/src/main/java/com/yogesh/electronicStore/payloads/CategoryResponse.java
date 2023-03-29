package com.yogesh.electronicStore.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {

    private List<CategoryDto> content;

    private int pageNumber;

    private int pageSize;

    private long totalElement;

    private int totalPage;

    private boolean lastPage;
}
