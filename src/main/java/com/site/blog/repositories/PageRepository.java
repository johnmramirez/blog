package com.site.blog.repositories;

import com.site.blog.models.Page;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PageRepository extends MongoRepository<Page, String> {
    List<Page> findByPostDateIsNotNullOrderByPostDateDesc();
}
