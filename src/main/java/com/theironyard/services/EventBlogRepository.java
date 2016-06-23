package com.theironyard.services;

import com.theironyard.entities.EventBlog;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by hoseasandstrom on 6/23/16.
 */
public interface EventBlogRepository extends CrudRepository<EventBlog, Integer> {
        public EventBlog findByIdOrderByDate(int id);

}
