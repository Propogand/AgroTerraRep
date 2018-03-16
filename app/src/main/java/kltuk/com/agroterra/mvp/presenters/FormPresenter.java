package kltuk.com.agroterra.mvp.presenters;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;

import java.util.Locale;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import kltuk.com.agroterra.C;
import kltuk.com.agroterra.R;
import kltuk.com.agroterra.utils.Utils;
import kltuk.com.agroterra.models.ActForm;
import kltuk.com.agroterra.models.Response;
import kltuk.com.agroterra.mvp.views.FormView;

@InjectViewState
public class FormPresenter extends BasePresenter<FormView> {

    public void createAct(int id) {
        act = new ActForm();
        act.field_id = id;
    }

    public void setCountWeeds(String countWeedsStr) {

        int countWeeds = 0;

        if (!countWeedsStr.isEmpty()) {
            countWeeds = Integer.parseInt(countWeedsStr);
        }

        act.count_weeds = countWeeds;
        calcTotalCountWeeds();
    }

    public void setFieldArea(String fieldAreaStr) {

        float fieldArea = 0.f;

        if (!fieldAreaStr.isEmpty()) {
            // Try catch нужен, чтобы обработать случай ввода числа с двумя точками и более
            try {
                fieldArea = Float.parseFloat(fieldAreaStr);
            } catch (Exception e){
                e.printStackTrace();
            }

        }

        act.field_area = fieldArea;
        calcTotalCountWeeds();

    }

    public void setComment(String comment) {
        act.comment = comment;
    }

    public void sendAct(final Context context) {
        getViewState().showLoader();

        // Проверяем наличие подключения к интернету
        if (!Utils.isNetworkAvailable(context)) {

            getViewState().hideLoader();

            getViewState().showMessage(R.string.attention, R.string.no_ethernet, (dialogInterface, i) -> sendAct(context));

            return;
        }

        // Выполняем запрос на создание акта
        networkController.createAct(act, new Observer<Response>() {
            Disposable disposable;
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(Response value) {
                getViewState().hideLoader();
                handleResponse(context, value);
                disposable.dispose();
            }

            @Override
            public void onError(Throwable e) {
                getViewState().hideLoader();
                getViewState().showMessage(R.string.attention, R.string.error, (dialogInterface, i) -> sendAct(context));

                disposable.dispose();
            }

            @Override
            public void onComplete() {

            }
        });

    }

    private void handleResponse(Context context, Response response) {
        if (response.getResult().equals(C.Network.RESULT_OK)) {
            getViewState().showSuccess(R.string.attention, R.string.success_create_act);
        } else {
            getViewState().showMessage(R.string.attention, R.string.error, (dialogInterface, i) -> sendAct(context));
        }
    }

    // Расчет общего количества сорняков на всю площадь
    // Считается по формуле(кол. сорняков нв 1 кв. метр * площадь поля)
    private void calcTotalCountWeeds() {

        int totalCountWeeds = (int) (act.count_weeds * act.field_area);

        act.total_count_weeds = totalCountWeeds;

        String totalCountWeedsStr = String.format(Locale.getDefault(), "%d", totalCountWeeds);

        getViewState().setTotalCountWeeds(totalCountWeedsStr);

    }

    private ActForm act;

}
