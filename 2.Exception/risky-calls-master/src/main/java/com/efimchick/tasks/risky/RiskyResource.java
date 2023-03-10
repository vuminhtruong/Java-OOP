package com.efimchick.tasks.risky;

import com.efimchick.tasks.risky.util.CarelessResourceConsumer;

import java.io.Closeable;

public class RiskyResource {

    final int input;
    final CarelessResourceConsumer careless;
    final Closeable resource;

    public RiskyResource(final int input,
                         final CarelessResourceConsumer careless,
                         final Closeable resource) {
        this.input = input;
        this.careless = careless;
        this.resource = resource;
    }

    public void handleCarelessConsuming()  /*You may not add "throws" here*/  {
        // handle method call
        //careless.consume(input, resource);
        for(int i=-50;i<=50;i++){
            try{
                careless.consume(input+i,resource);
            }
            catch (Exception e){
                break;
            }
        }
    }
}
