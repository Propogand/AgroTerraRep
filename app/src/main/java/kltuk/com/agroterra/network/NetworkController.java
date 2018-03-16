package kltuk.com.agroterra.network;


import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kltuk.com.agroterra.C;
import kltuk.com.agroterra.models.ActForm;
import kltuk.com.agroterra.models.Poly;
import kltuk.com.agroterra.models.Response;
import kltuk.com.agroterra.models.Act;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkController {

    public NetworkController() {
        init();
    }

    /**
     * Метод запрашивает список доступных полей
     * @param observer - наблюдатель для получения списка полей
     */
    public void getPolygons(Observer<List<Poly>> observer) {

        api.getPolygons()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }

    /**
     * Метод запрашивает список заполненных актов
     * @param observer - наблюдатель для получения списка актов
     */
    public void getActs(Observer<List<Act>> observer) {

        api.getActs()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }

    /**
     * Метод отправляет заполенную форму на сервер
     * @param act - форма
     * @param observer - наблюдатель
     */
    public void createAct(ActForm act, Observer<Response> observer) {

        api.createAct(act)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }

    private Retrofit retrofit;

    private AgroTerraApi api;

    private void init() {

        retrofit = new Retrofit.Builder()
                .baseUrl(C.Network.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(AgroTerraApi.class);

    }

}
