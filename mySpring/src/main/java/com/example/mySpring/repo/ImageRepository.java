package com.example.mySpring.repo;

import com.example.mySpring.models.Image;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<Image, Integer> {
}
