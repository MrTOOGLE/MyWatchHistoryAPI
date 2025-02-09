package com.mywatchhistoryapi.Service;

import com.mywatchhistoryapi.entity.ContentType;
import com.mywatchhistoryapi.entity.TV;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервисный слой для выполнения операций с базой данных
 */
@Service
public class TvService {
    private final TvRepository tvRepository;

    public TvService(TvRepository tvRepository) {
        this.tvRepository = tvRepository;
    }

    // Получить все записи (фильмы + сериалы)
    public List<TV> getAllTv() {
        return tvRepository.findAll();
    }

    // Получить только фильмы
    public List<TV> getMovies() {
        return tvRepository.findByContentType(ContentType.MOVIE);
    }

    // Получить только сериалы
    public List<TV> getTVSeries() {
        return tvRepository.findByContentType(ContentType.TV_SERIES);
    }

    // Получить сериал/фильм по id
    public TV getTvById(int id) {
        return tvRepository.findById(id).orElse(null);
    }

    // Сохранить/обновить сериал/фильм
    public void saveTv(TV tv) {
        tvRepository.save(tv);
    }

    // Удалить сериал/фильм по id
    public void deleteTvById(int id) {
        tvRepository.deleteById(id);
    }
}
