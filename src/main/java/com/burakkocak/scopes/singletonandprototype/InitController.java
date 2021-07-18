package com.burakkocak.scopes.singletonandprototype;

import com.burakkocak.scopes.singletonandprototype.scope.PrototypeBean;
import com.burakkocak.scopes.singletonandprototype.scope.PrototypeNoProxyBean;
import com.burakkocak.scopes.singletonandprototype.scope.SingletonBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController @Slf4j
@RequestMapping("/")
public class InitController implements CommandLineRunner {

    private SingletonBean singletonBeanExample;

    private PrototypeNoProxyBean prototypeNoProxyBean;
    private ObjectProvider<PrototypeNoProxyBean> prototypeNoProxyBeanObjectProvider;
    private ObjectFactory<PrototypeNoProxyBean> prototypeNoProxyBeanObjectFactory;

    private PrototypeBean prototypeBeanExample;

    private ApplicationContext applicationContext;

    @Autowired
    public InitController(SingletonBean singletonBeanExample, PrototypeNoProxyBean prototypeNoProxyBean,
                          ObjectProvider<PrototypeNoProxyBean> prototypeNoProxyBeanObjectProvider,
                          ObjectFactory<PrototypeNoProxyBean> prototypeNoProxyBeanObjectFactory,
                          PrototypeBean prototypeBeanExample, ApplicationContext applicationContext) {
        this.singletonBeanExample = singletonBeanExample;
        this.prototypeNoProxyBean = prototypeNoProxyBean;
        this.prototypeNoProxyBeanObjectProvider = prototypeNoProxyBeanObjectProvider;
        this.prototypeNoProxyBeanObjectFactory = prototypeNoProxyBeanObjectFactory;
        this.prototypeBeanExample = prototypeBeanExample;
        this.applicationContext = applicationContext;
    }

    @Override
    @Order(1)
    public void run(String... args) {

        try {
            // Example 1
            String first = singletonBeanExample.getLocalDateTime();
            Thread.sleep(1000);
            String second = singletonBeanExample.getLocalDateTime();
            log.info("List1: " + Arrays.asList(first, second));

            // Example 2
            first = prototypeNoProxyBean.getLocalDateTime();
            Thread.sleep(1000);
            second = prototypeNoProxyBean.getLocalDateTime();
            log.info("List2: " + Arrays.asList(first, second));

            first = prototypeNoProxyBeanObjectProvider.getObject().getLocalDateTime();
            Thread.sleep(1000);
            second = prototypeNoProxyBeanObjectProvider.getObject().getLocalDateTime();
            log.info("List3: " + Arrays.asList(first, second));

            first = prototypeNoProxyBeanObjectFactory.getObject().getLocalDateTime();
            Thread.sleep(1000);
            second = prototypeNoProxyBeanObjectFactory.getObject().getLocalDateTime();
            log.info("List4: " + Arrays.asList(first, second));

            first = prototypeBeanWithLookup().getLocalDateTime();
            Thread.sleep(1000);
            second = prototypeBeanWithLookup().getLocalDateTime();
            log.info("List5: " + Arrays.asList(first, second));

            first = applicationContext.getBean(PrototypeNoProxyBean.class).getLocalDateTime();
            Thread.sleep(1000);
            second = applicationContext.getBean(PrototypeNoProxyBean.class).getLocalDateTime();
            log.info("List6: " + Arrays.asList(first, second));

            // Example 3
            first = prototypeBeanExample.getLocalDateTime();
            Thread.sleep(1000);
            second = prototypeBeanExample.getLocalDateTime();
            log.info("List7: " + Arrays.asList(first, second));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * This time we use @Lookup bean that returns PrototypeBeanExample just like a method to inject PrototypeBean
     * instead of using "@Autowired private PrototypeBean prototypeBean;" the to top where we inject all the dependencies for this class.
     * @return
     */
    @Lookup
    public PrototypeBean prototypeBeanWithLookup() {
        return null;
    }
}
