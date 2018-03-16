package kltuk.com.agroterra.mvp.presenters;

import android.content.Context;
import android.graphics.Color;

import com.arellomobile.mvp.InjectViewState;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import kltuk.com.agroterra.R;
import kltuk.com.agroterra.utils.Utils;
import kltuk.com.agroterra.models.Poly;
import kltuk.com.agroterra.mvp.views.MapsView;

@InjectViewState
public class MapsPresenter extends BasePresenter<MapsView> {

    // Метод для загрузки полигонов с сервера
    public void getPolygons(final Context context) {
        getViewState().showLoader();

        // Проверяем наличие подключения к интернету
        if (!Utils.isNetworkAvailable(context)) {

            getViewState().hideLoader();

            getViewState().showMessage(R.string.attention, R.string.no_ethernet, (dialogInterface, i) -> getPolygons(context));

            return;

        }

        // Запускаем загрузку со слушателем, получения данных в происходит в отдельном потоке
        // А обработка данных происходит в главном потоке
        networkController.getPolygons(new Observer<List<Poly>>() {
            Disposable d;
            @Override
            public void onSubscribe(Disposable d) {
                this.d = d;
            }

            @Override
            public void onNext(List<Poly> value) {
                getViewState().hideLoader();

                onPolygonsLoaded(value);

                d.dispose();

            }

            @Override
            public void onError(Throwable e) {
                getViewState().hideLoader();
                getViewState().showMessage(R.string.attention, R.string.error, (dialogInterface, i) -> getPolygons(context));

                d.dispose();

            }


            @Override
            public void onComplete() {

            }
        });

    }


    private void onPolygonsLoaded(List<Poly> polyList) {

        List<LatLng> points = new ArrayList<>();

        for (Poly poly : polyList) {

            PolygonOptions polygonOptions = new PolygonOptions();
            polygonOptions.addAll(poly.getPolygons());
            polygonOptions.fillColor(poly.getColor());
            polygonOptions.strokeColor(Color.BLUE);
            polygonOptions.strokeWidth(3);

            points.addAll(poly.getPolygons());

            getViewState().addPolygon(polygonOptions, poly);

        }

        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        for (LatLng latLng :
                points) {
            builder.include(latLng);
        }

        getViewState().showPosition(builder);

    }

}
