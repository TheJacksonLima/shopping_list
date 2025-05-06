package com.jfl.shopping_list.repository;

import com.jfl.shopping_list.model.ShoppingList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ListRepository extends CrudRepository<ShoppingList, Long>, PagingAndSortingRepository<ShoppingList, Long> {}
