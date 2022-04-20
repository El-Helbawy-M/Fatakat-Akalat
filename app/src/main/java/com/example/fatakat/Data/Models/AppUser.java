package com.example.fatakat.Data.Models;

import java.io.Serializable;
import java.util.HashMap;

public class AppUser implements Serializable {
    public String name,email;
    public AppUser(String name,String email){
        this.name=name;
        this.email=email;
    }

    public HashMap<String,Object> getDataiInMap(){
        HashMap<String,Object> data = new HashMap<String,Object>();
        data.put("Name",this.name);
        data.put("Email",this.email);
        return data;
    }
}
