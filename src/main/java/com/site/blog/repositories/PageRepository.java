package com.site.blog.repositories;

import com.site.blog.models.Page;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PageRepository extends MongoRepository<Page, String> {
    Page findByPostDate(String postDate);
}
