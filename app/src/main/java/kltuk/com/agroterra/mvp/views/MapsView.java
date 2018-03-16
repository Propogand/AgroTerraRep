package kltuk.com.agroterra.mvp.views;


import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.List;

import kltuk.com.agroterra.models.Poly;

public interface MapsView extends BaseView {

    // Отключаем добавление метода в стэк
    @StateStrategyType(OneExecutionStateStrategy.class)
    void addPolygon(PolygonOptions polygonOptions, Poly poly);

    // Отключаем добавление метода в стэк
    @StateStrategyType(OneExecutionStateStrategy.class)
    void showPosition(LatLngBounds.Builder builder);

}
