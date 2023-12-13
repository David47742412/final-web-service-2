package com.app.app.finalproject.dto;

import java.util.ArrayList;
import java.util.List;

public class ResponseDto<T> {
    public List<String> message = new ArrayList<>();
    public Iterable<T> body = new ArrayList<>();
    public int statusCode = 200;
}
