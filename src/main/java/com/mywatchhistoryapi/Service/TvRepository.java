package com.mywatchhistoryapi.Service;

import com.mywatchhistoryapi.entity.TV;
import com.mywatchhistoryapi.entity.ContentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TvRepository extends JpaRepository<TV, Integer> {
    List<TV> findByContentType(ContentType contentType);
}
