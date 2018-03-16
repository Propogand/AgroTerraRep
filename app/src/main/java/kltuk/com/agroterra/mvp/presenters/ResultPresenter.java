package kltuk.com.agroterra.mvp.presenters;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import kltuk.com.agroterra.R;
import kltuk.com.agroterra.sql.DBHelper;
import kltuk.com.agroterra.utils.Utils;
import kltuk.com.agroterra.models.Act;
import kltuk.com.agroterra.mvp.views.ResultView;

@InjectViewState
public class ResultPresenter extends BasePresenter<ResultView> {

    public void getActs(Context context) {

        getViewState().showLoader();

        // Проверяем наличие подключения к интернету
        if (!Utils.isNetworkAvailable(context)) {

            getViewState().hideLoader();

            getViewState().showMessage(R.string.attention, R.string.no_ethernet_load_saved_data, (dialogInterface, i) -> loadSavedActs(context));

            return;

        }

        // Выполняем запрос на создание акта
        networkController.getActs(new Observer<List<Act>>() {
            Disposable disposable;
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(List<Act> value) {
                getViewState().hideLoader();
                getViewState().onLoadedActs(value);

                saveActs(context, value);

                disposable.dispose();
            }

            @Override
            public void onError(Throwable e) {
                getViewState().hideLoader();
                getViewState().showMessage(R.string.attention, R.string.error, (dialogInterface, i) -> getActs(context));

                disposable.dispose();
            }

            @Override
            public void onComplete() {

            }
        });


    }

    private void saveActs(Context context, List<Act> acts) {

        DBHelper sqlHelper = new DBHelper(context);

        for (Act act:acts) {
            sqlHelper.writeAct(act);
        }

    }

    private void loadSavedActs(Context context) {

        DBHelper sqlHelper = new DBHelper(context);

        List<Act> acts = sqlHelper.getActs();

        getViewState().onLoadedActs(acts);

    }

}
