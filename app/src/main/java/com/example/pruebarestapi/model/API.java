package com.example.pruebarestapi.model;

public interface API {
    void login(String email, String password, APIListener listener);
}
