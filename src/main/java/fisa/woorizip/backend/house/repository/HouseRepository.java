package fisa.woorizip.backend.house.repository;

import fisa.woorizip.backend.house.domain.House;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HouseRepository extends JpaRepository<House, Long>, HouseRepositoryCustom {}
