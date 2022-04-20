package com.site.blog.util;

import java.util.UUID;

public class RandomIdGenerator {

    public String generateUUID(){
        UUID key = UUID.randomUUID();
        return key.toString();
    }

}
