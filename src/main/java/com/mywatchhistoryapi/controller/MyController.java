package com.mywatchhistoryapi.controller;

import com.mywatchhistoryapi.Service.TvService;
import com.mywatchhistoryapi.entity.TV;
import com.mywatchhistoryapi.exception.NoSuchTvException;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * API для CRUD методов для работы со списком просмотренных фильмов/сериалов
 */
@RestController
@RequestMapping("/api/tv")
public class MyController {
    private final TvService tvService;

    public MyController(TvService tvService) {
        this.tvService = tvService;
    }

    @PostMapping()
    public TV addNewTV(@RequestBody TV tv) {
        tvService.saveTv(tv);
        return tv;
    }

    @PostMapping("/{id}/image")
    public TV uploadImage(@PathVariable int id, @RequestParam("image") MultipartFile file) {
        TV tv = tvService.getTvById(id);
        if (tv == null) {
            throw new NoSuchTvException("Такого кино/сериала с id = " + id + " нет!");
        }

        try {
            tv.setImageType(file.getContentType());
            tv.setImageData(file.getBytes());
            tvService.saveTv(tv);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при загрузке изображения." + e.getMessage());
        }
        return tv;
    }

    @PutMapping
    public TV updateTV(@RequestBody TV tv) {
        tvService.saveTv(tv);
        return tv;
    }

    @DeleteMapping("/{id}")
    public String deleteTV(@PathVariable int id) {
        TV tv = tvService.getTvById(id);
        if (tv == null) {
            throw new NoSuchTvException("Такого фильма/сериала нет!");
        }
        tvService.deleteTvById(id);
        return "Фильм/сериал с id = " + id + " был удалён!";
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getImage(@PathVariable int id) {
        TV tv = tvService.getTvById(id);
        if (tv == null || tv.getImageData() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(tv.getImageType()))
                .body(tv.getImageData());
    }

    // Получить все фильмы и сериалы (так как не написали ссылку, то обрабатывает "/api")
    @GetMapping
    public List<TV> getAllMedia() {
        System.out.println(tvService.getAllTv());
        return tvService.getAllTv();
    }

    // Получить только фильмы
    @GetMapping("/movies")
    public List<TV> getMovies() {
        return tvService.getMovies();
    }

    // Получить только сериалы
    @GetMapping("/series")
    public List<TV> getTVSeries() {
        return tvService.getTVSeries();
    }

    // Получить фильм/сериал по id
    @GetMapping("/{id}")
    // Получаем id из url   ↓
    public TV getTVById(@PathVariable int id) {
        TV tv = tvService.getTvById(id);
        if (tv == null) {
            throw new NoSuchTvException("Такого кино/сериала с id = " + id + " нет!");
        }

        return tv;
    }
}
