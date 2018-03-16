package kltuk.com.agroterra.dagger.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import kltuk.com.agroterra.network.NetworkController;


@Module
public class NetworkControllerModule {

    @Singleton
    @Provides
    NetworkController provideNetworkController() {
        return new NetworkController();
    }

}
