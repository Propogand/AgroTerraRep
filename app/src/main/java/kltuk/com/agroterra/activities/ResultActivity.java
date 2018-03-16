package kltuk.com.agroterra.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import kltuk.com.agroterra.R;
import kltuk.com.agroterra.adapters.ResultAdapter;
import kltuk.com.agroterra.models.Act;
import kltuk.com.agroterra.mvp.presenters.ResultPresenter;
import kltuk.com.agroterra.mvp.views.ResultView;

public class ResultActivity extends BaseActivity implements ResultView {

    @Override
    public void onLoadedActs(List<Act> data) {
        setupRecycler(data);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @InjectPresenter
    ResultPresenter resultPresenter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        setTitle(R.string.title_result);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        unbinder = ButterKnife.bind(this);

        resultPresenter.getActs(this);

    }

    @Override
    protected void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }

    private Unbinder unbinder;

    private void setupRecycler(List<Act> data) {

        ResultAdapter resultAdapter = new ResultAdapter(data);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(resultAdapter);

    }


}
