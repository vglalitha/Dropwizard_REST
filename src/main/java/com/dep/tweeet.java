package com.dep;

import com.dep.config.BRSConfiguration;
import com.dep.resource.DropResource;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;

public class tweeet extends Application<BRSConfiguration> {
    public static  void main(String[] args) throws Exception {
        new tweeet().run(args);
    }


    @Override
    public void run(BRSConfiguration brsConfiguration,
                    Environment environment) throws Exception {
        System.out.println("hlo");
        environment.jersey().register(new DropResource());

    }
}
