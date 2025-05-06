package com.jfl.shopping_list.controller;

import com.jfl.shopping_list.exceptions.ResourceNotFoundException;
import com.jfl.shopping_list.model.Item;
import com.jfl.shopping_list.model.ShoppingList;
import com.jfl.shopping_list.repository.ItemRepository;
import com.jfl.shopping_list.repository.ListRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@RestController
@RequestMapping("/shopping_list")
@Data
public class ShoppingListController {
    @Autowired
    ItemRepository itemRepository;
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
                .orElseThrow( () -> new ResourceNotFoundException("No records found for this id!"));
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
/*
    @PostMapping("/list")
    private ShoppingList createShoppingList(@RequestBody ShoppingList list){
        validate_input_parameters(list);
        ShoppingList newList = new ShoppingList();
        ShoppingList savedList = listRepository.save(newList);
        return savedList;
    }

*/

}