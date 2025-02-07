package com.example.flowershop.service;

import com.example.flowershop.model.FlowerDTO;

import java.util.List;
import java.util.Optional;

public interface FlowerService {
    List<FlowerDTO> getAllFlowers();
    Optional<FlowerDTO> getFlowerById(Long id);
    FlowerDTO createFlower(FlowerDTO flowerDTO);
    FlowerDTO updateFlower(Long id, FlowerDTO flowerDTO);
    void deleteFlower(Long id);
}
