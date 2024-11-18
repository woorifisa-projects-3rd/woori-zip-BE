package fisa.woorizip.backend.house.repository;

import fisa.woorizip.backend.house.domain.House;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<House, Long>, HouseRepositoryCustom {}
