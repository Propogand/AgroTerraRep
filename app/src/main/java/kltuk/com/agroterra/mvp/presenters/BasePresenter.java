package kltuk.com.agroterra.mvp.presenters;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.internal.subscriptions.ArrayCompositeSubscription;
import kltuk.com.agroterra.AgroTerraApp;
import kltuk.com.agroterra.network.NetworkController;

public class BasePresenter<BaseView extends MvpView> extends MvpPresenter<BaseView> {

    public BasePresenter() {
        networkController = AgroTerraApp.instance.getNetworkController();
    }

    protected NetworkController networkController;


}
