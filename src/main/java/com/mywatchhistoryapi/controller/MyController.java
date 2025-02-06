package com.mywatchhistoryapi.controller;

import com.mywatchhistoryapi.Service.TvService;
import com.mywatchhistoryapi.entity.TV;
import com.mywatchhistoryapi.exception.NoSuchTvException;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // Получить все фильмы и сериалы (так как не написали ссылку, то обрабатывает "/api"
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
        System.out.println(tv);
        Log logger = LogFactory.getLog(MyController.class);
        logger.info(tv);

        if (tv == null) {
            throw new NoSuchTvException("Такого кино/сериала с id = " + id + " нет!");
        }

        return tv;
    }
}
