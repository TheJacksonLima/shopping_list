package com.jfl.shopping_list.repository;

import com.jfl.shopping_list.model.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ItemRepository extends CrudRepository<Item, Long>, PagingAndSortingRepository<Item, Long> {
}
