package com.app.wallet.Service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
public class ValidationService {
    public ResponseEntity<?> validate(BindingResult result)
    {
        if(result.hasErrors())
        {
            Map<String, String> m = new HashMap<>();

            for(FieldError i : result.getFieldErrors())
            {
                m.put(i.getField(), i.getDefaultMessage());
            }
            System.out.println("error for name :");
            return new ResponseEntity<Map<String, String>>(m, HttpStatus.BAD_REQUEST);
        }
        return null;
    }
}
