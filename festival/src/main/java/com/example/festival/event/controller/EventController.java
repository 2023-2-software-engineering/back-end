package com.example.festival.event.controller;


import com.example.festival.event.dto.EventDTO;
import com.example.festival.event.entity.EventEntity;
import com.example.festival.event.service.EventService;
import com.example.festival.festival.dto.FestivalDTO;
import com.example.festival.festival.service.UploadFileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/event")
public class EventController {

    private final UploadFileService uploadFileService;
    private final EventService eventService;

    @PostMapping("")
    public String uploadEvent(@RequestParam("event") String eventDTO, @RequestParam("image") MultipartFile multipartFile) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        EventDTO mapperEventDTO = mapper.readValue(eventDTO, EventDTO.class);

        String imageFile = uploadFileService.storeFile(multipartFile);
        mapperEventDTO.setImage(imageFile);

        String event = eventService.uploadEvent(mapperEventDTO);

        return event;
    }

    @GetMapping("")
    public List<EventEntity> getEventList() {

        List<EventEntity> list = eventService.eventList();

        return list;
    }

    @GetMapping("/{eventId}")
    public Optional<EventEntity> getEventDetail(@PathVariable("eventId") Integer eventId) {

        Optional<EventEntity> event = eventService.eventDetail(eventId);

        return event;
    }

    @GetMapping("/search")
    public List<EventEntity> searchEvent(@RequestParam("keyword") String keyword) {

        List<EventEntity> list = eventService.searchEvent(keyword);

        return list;
    }


}