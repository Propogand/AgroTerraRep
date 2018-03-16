package kltuk.com.agroterra.dagger;

import javax.inject.Singleton;

import dagger.Component;
import kltuk.com.agroterra.dagger.modules.NetworkControllerModule;
import kltuk.com.agroterra.network.NetworkController;


@Singleton
@Component(modules = {NetworkControllerModule.class})
public interface IAppComponent {

    NetworkController networkController();

}
