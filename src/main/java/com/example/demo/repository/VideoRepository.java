package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.VideoModel;

@Repository
public interface VideoRepository extends CrudRepository<VideoModel, Long> {
    List<VideoModel> findByName(String name);
    List<VideoModel> findByDescription(String description);
}
