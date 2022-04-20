package com.example.fatakat.Data.Models;

public class Bad extends Result {
    public Bad (int code, String state){
        this.type = ResultType.BAD;
        this.code = code;
        this.state = state;
    }
    public String state;
}
