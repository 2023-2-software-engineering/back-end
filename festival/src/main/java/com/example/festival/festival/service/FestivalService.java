package com.example.festival.festival.service;

import com.example.festival.festival.dto.FestivalDTO;
import com.example.festival.festival.entity.Festival;

import java.util.List;
import java.util.Optional;


public interface FestivalService {

    String uploadFestival(FestivalDTO festivalDto);

    List<Festival> festivalList();

    List<Festival> festivalIngList();

    Optional<Festival> festivalDetail(Integer festivalId);

    List<Festival> searchFestival(String keyword, Integer state, String region);

    List<Festival> festivalListState(Integer state);

    Optional<Festival> topViewFestival();
}
