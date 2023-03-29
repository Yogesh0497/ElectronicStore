package com.yogesh.electronicStore.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto extends BaseEntityDtoClass {

    private Long categoryId;

    @NotBlank(message = "title required")
    @Size(min = 3, message = "Title size should be minimum 3 character")
    private String title;

    @NotBlank(message = "Description required")
    @Size(min=10,message = "Minimum size of category description is 10")
    private String description;

    private String coverImage;
}
