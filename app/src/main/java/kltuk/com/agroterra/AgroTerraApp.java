package kltuk.com.agroterra;

import android.app.Application;

import kltuk.com.agroterra.dagger.DaggerIAppComponent;
import kltuk.com.agroterra.dagger.IAppComponent;
import kltuk.com.agroterra.dagger.modules.NetworkControllerModule;
import kltuk.com.agroterra.network.NetworkController;


public class AgroTerraApp extends Application {

    public static AgroTerraApp instance;

    public NetworkController getNetworkController() {
        return appComponent.networkController();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        appComponent = DaggerIAppComponent.builder()
                .networkControllerModule(new NetworkControllerModule())
                .build();


    }

    private IAppComponent appComponent;

}

