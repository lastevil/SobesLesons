package org.homework.exceptions;

import org.hibernate.SQLQuery;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String s){
        super(s);
    }
}
