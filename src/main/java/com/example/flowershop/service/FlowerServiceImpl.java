package com.example.flowershop.service;

import com.example.flowershop.model.Flower;
import com.example.flowershop.model.FlowerDTO;
import com.example.flowershop.repository.FlowerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlowerServiceImpl implements FlowerService {

    private final FlowerRepository flowerRepository;

    public FlowerServiceImpl(FlowerRepository flowerRepository) {
        this.flowerRepository = flowerRepository;
    }

    @Override
    public List<FlowerDTO> getAllFlowers() {
        return flowerRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<FlowerDTO> getFlowerById(Long id) {
        return flowerRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public FlowerDTO createFlower(FlowerDTO flowerDTO) {
        Flower flower = convertToFlower(flowerDTO);
        Flower savedFlower = flowerRepository.save(flower);
        return convertToDTO(savedFlower);
    }

    @Override
    public FlowerDTO updateFlower(Long id, FlowerDTO flowerDTO) {
        Flower flower = flowerRepository.findById(id).orElseThrow();
        flower.setName(flowerDTO.name());
        flower.setDescription(flowerDTO.description());
        flower.setPrice(flowerDTO.price());
        Flower updateFlower = flowerRepository.save(flower);
        return convertToDTO(updateFlower);
    }

    @Override
    public void deleteFlower(Long id) {
        flowerRepository.deleteById(id);
    }

    private FlowerDTO convertToDTO(Flower flower) {
        return new FlowerDTO(flower.getId(), flower.getName(), flower.getDescription(), flower.getPrice());
    }

    private Flower convertToFlower(FlowerDTO dto) {
        Flower flower = new Flower();
        flower.setName(dto.name());
        flower.setDescription(dto.description());
        flower.setPrice(dto.price());
        return flower;
    }
}
