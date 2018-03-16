package kltuk.com.agroterra.mvp.views;


import java.util.List;

import kltuk.com.agroterra.models.Act;

public interface ResultView extends BaseView {

    void onLoadedActs(List<Act> data);

}
