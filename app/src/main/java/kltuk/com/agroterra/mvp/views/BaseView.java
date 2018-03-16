package kltuk.com.agroterra.mvp.views;

import android.content.DialogInterface;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

// Отключаем добавление методов в стэк
@StateStrategyType(OneExecutionStateStrategy.class)
public interface BaseView extends MvpView {

    void showLoader();

    void hideLoader();

    void showMessage(int title, int message, DialogInterface.OnClickListener onClickListenerOk);

    void showSuccess(int title, int message);


}
