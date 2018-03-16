package kltuk.com.agroterra.mvp.views;

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface FormView extends BaseView{

    @StateStrategyType(OneExecutionStateStrategy.class)
    void setTotalCountWeeds(String totalCountWeeds);

}
