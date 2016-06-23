package com.theironyard.services;

import com.theironyard.entities.Message;
import org.springframework.data.repository.CrudRepository;

import java.awt.image.renderable.ContextualRenderedImageFactory;

/**
 * Created by hoseasandstrom on 6/23/16.
 */
public interface MessageRepository extends CrudRepository<Message, Integer> {
    public Message findById(int id);

}
