package com.burakkocak.scopes.singletonandprototype.scope;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@NoArgsConstructor
@Slf4j
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PrototypeNoProxyBean {

    private String localDateTime = LocalDateTime.now().toString();

    public String getLocalDateTime() {
        return localDateTime;
    }

}
