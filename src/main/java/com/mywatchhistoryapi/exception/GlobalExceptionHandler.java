package com.mywatchhistoryapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Глобальный обработчик исключений приложения
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    // Обрабатываем ошибку: только NoSuchTvException
    public ResponseEntity<TvIncorrectData> handleException(NoSuchTvException e) {
        TvIncorrectData tvIncorrectData = new TvIncorrectData();
        tvIncorrectData.setMessage(e.getMessage());

        return new ResponseEntity<>(tvIncorrectData, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    // Обрабатываем ошибку: любую (Exception)
    public ResponseEntity<TvIncorrectData> handleException(Exception e) {
        TvIncorrectData tvIncorrectData = new TvIncorrectData();
        tvIncorrectData.setMessage(e.getMessage());

        return new ResponseEntity<>(tvIncorrectData, HttpStatus.NOT_FOUND);
    }
}
