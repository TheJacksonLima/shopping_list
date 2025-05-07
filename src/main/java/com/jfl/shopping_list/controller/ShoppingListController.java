package com.jfl.shopping_list.controller;

import com.jfl.shopping_list.exceptions.ResourceNotFoundException;
import com.jfl.shopping_list.exceptions.ValidationException;
import com.jfl.shopping_list.model.Item;
import com.jfl.shopping_list.model.ShoppingList;
import com.jfl.shopping_list.model.ShoppingListDTO;
import com.jfl.shopping_list.repository.ItemRepository;
import com.jfl.shopping_list.repository.ListRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/shopping_list")
@Data
public class ShoppingListController {
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ListRepository listRepository;

    @GetMapping("/items")
    private Page<Item> getAllItems(Pageable pageable){
        return itemRepository.findAll(pageable);
    }

    @GetMapping("/item/{id}")
    private Optional<Item> getItemById(@PathVariable(value = "id") Long id) {
        return Optional.ofNullable(findItemById(id));
    }

    public Item findItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("No item found for this id!"));
    }

    @GetMapping("/lists")
    private Page<ShoppingList> getAllShoppingLists(Pageable pageable){
        return listRepository.findAll(pageable);
    }

    @GetMapping("/list/{id}")
    private Optional<ShoppingList> getShoppingListById(@PathVariable(value = "id") Long id){
        return Optional.ofNullable(findShoppingListById(id));
    }

    public ShoppingList findShoppingListById(Long id) {
        return listRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("No records found for this id!"));
    }

    @PostMapping("/list")
    private ShoppingList createShoppingList(@RequestBody ShoppingListDTO list) throws ResourceNotFoundException,ValidationException{
        validate_input_parameters(list);
        Item item = findItemById(list.getItemId());
        ShoppingList newList =  ShoppingList.builder()
                                            .item(item)
                                            .purchaseDate(list.getPurchaseDate())
                                            .creationDate(list.getCreationDate())
                                            .lastUpdateDate(list.getLastUpdateDate())
                                            .observation(list.getObservation())
                                            .quantity(list.getQuantity())
                                            .build();

        return listRepository.save(newList);
    }

    private void validate_input_parameters(ShoppingListDTO list) throws ValidationException{

       if(list.getItemId() == null){
           throw new ValidationException("ItemId field can't be null");
       }

       if(list.getQuantity() == null){
            throw new ValidationException("Quantity field can't be null");
       }

       if(list.getPurchaseDate() == null){
           list.setPurchaseDate(LocalDate.now());
       }

       if(list.getLastUpdateDate() == null){
            list.setLastUpdateDate(LocalDate.now());
       }

    }


}