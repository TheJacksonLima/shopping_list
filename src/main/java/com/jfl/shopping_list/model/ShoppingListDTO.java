package com.jfl.shopping_list.model;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ShoppingListDTO {
    private Long itemId;
    private LocalDate purchaseDate;
    private LocalDate creationDate;
    private LocalDate lastUpdateDate;
    private String observation;
    private Double quantity;
}
