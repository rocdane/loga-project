package com.loga.microservices.wms.repository;

import com.loga.microservices.wms.entity.Assets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetsRepository extends JpaRepository<Assets,Long> {
}
