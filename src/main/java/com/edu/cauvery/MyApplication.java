package com.edu.cauvery;

import com.edu.cauvery.home.Home;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

public class MyApplication extends WebApplication {

    public MyApplication(){
    }

    @Override
    public Class<? extends Page> getHomePage() {
        return Home.class;
    }
}
