package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.VideoModel;
import com.example.demo.repository.VideoRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
public class VideoController {
    
    
    VideoRepository videoRepository;

    

    public VideoController(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @GetMapping("/video")
    public List<VideoModel> getAllVideo() {
        List<VideoModel> videos = new ArrayList<>();
        Streamable.of(videoRepository.findAll()).forEach(videoModel -> {
            videos.add(videoModel);
        });
        return videos;
    }

    @GetMapping("/findVideoById/{id}")
    public VideoModel findVideoById(@PathVariable Long id) {
        return videoRepository.findById(id).get();
    }

    @GetMapping("/findVideoByName/{name}")
    public List<VideoModel> findVideoByName(@PathVariable String name) {
        return videoRepository.findByName(name);
    }
    @GetMapping("/findVideoByDescription/{description}")
public ResponseEntity<List<VideoModel>> findVideoByDescription(@PathVariable String description) {
    List<VideoModel> videos = videoRepository.findByDescription(description);
    if (videos.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(videos);
    }
    return ResponseEntity.ok(videos);
}

    @PostMapping("/video")
    public VideoModel createVideo(@RequestBody VideoModel video) {
        
        return videoRepository.save(video);
    }

    @PostMapping("/video-all")
    public List<VideoModel> AddAllVideo(@RequestBody List<VideoModel> videos) {
        for (VideoModel video : videos) {
            videoRepository.save(video);
        }
        return videos;
    }

    @DeleteMapping("/video/{id}")
    public String deleteVideo(@PathVariable Long id) {
        videoRepository.deleteById(id);
        if (videoRepository.findById(id).isPresent()) {
            return "Failed to delete the specified video";
        }
        return "Video deleted successfully";
    }

    @DeleteMapping("/video-all")
    public String deleteAllVideo() {
        videoRepository.deleteAll();
        return "Video deleted successfully";
    }

    @PutMapping("video/{id}")
    public VideoModel updateVideoString(@PathVariable String id, @RequestBody VideoModel videoModel) {
        VideoModel video = videoRepository.findById(Long.parseLong(id)).get();
        video.setName(videoModel.getName());
        video.setUrl(videoModel.getUrl());
        video.setDescription(videoModel.getDescription());
        videoRepository.save(video);
        return videoModel;
    }

    
}