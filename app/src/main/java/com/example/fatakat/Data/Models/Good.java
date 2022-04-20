package com.example.fatakat.Data.Models;

public class Good<T> extends Result{
    public Good(int code,T data){
        this.type = ResultType.GOOD;
        this.code = code;
        this.data = data;
    }

    public T data;
}
