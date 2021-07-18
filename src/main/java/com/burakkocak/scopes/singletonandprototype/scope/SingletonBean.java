package com.burakkocak.scopes.singletonandprototype.scope;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@NoArgsConstructor
@Slf4j
public class SingletonBean {

    private String localDateTime = LocalDateTime.now().toString();

    public String getLocalDateTime() {
        return localDateTime;
    }

}
