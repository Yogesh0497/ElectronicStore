package com.yogesh.electronicStore.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductDto extends BaseEntityDtoClass{

    private Long productId;

    @NotBlank
    @Size(min = 5, message = "Title should be more than 5 character")
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private int price;

    @NotBlank
    private int discountedPrice;

    @NotBlank
    private String quantity;

    @NotBlank
    private Date addedDate;

    private boolean live;

    private boolean stock;


}
