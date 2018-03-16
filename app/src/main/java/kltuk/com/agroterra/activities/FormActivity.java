package kltuk.com.agroterra.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import kltuk.com.agroterra.C;
import kltuk.com.agroterra.R;
import kltuk.com.agroterra.models.Poly;
import kltuk.com.agroterra.mvp.presenters.FormPresenter;
import kltuk.com.agroterra.mvp.views.FormView;

public class FormActivity extends BaseActivity implements FormView {

    @Override
    public void setTotalCountWeeds(String totalCountWeeds) {
        totalCountWeedsView.setText(totalCountWeeds);
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

    // Внедряем презентер для управление логикой формы
    @InjectPresenter
    FormPresenter formPresenter;

    @BindView(R.id.form_calture)
    TextView caltureView;

    @BindView(R.id.form_count_weeds)
    EditText countWeedsView;

    @BindView(R.id.form_field_area)
    EditText fieldAreaView;

    @BindView(R.id.form_total_count_weeds)
    TextView totalCountWeedsView;

    @BindView(R.id.form_comment)
    EditText commentView;

    @BindView(R.id.form_field_id)
    TextView fieldIdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Делаем привязку view к активити, получаем интерфейс для освобождения ресурсов
        unbinder = ButterKnife.bind(this);

        Intent intent = getIntent();

        if (intent.getExtras() != null) {

            Poly poly = (Poly) intent.getExtras().getSerializable(C.BundleKeys.POLY);

            int id = poly.getId();

            String calture = poly.getCalture();

            setTitle(poly.getName());

            formPresenter.createAct(id);

            initViews(id, calture);

        }

    }

    @Override
    protected void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }

    @OnClick(R.id.form_send)
    void onClickSend() {
        formPresenter.sendAct(this);
    }

    private Unbinder unbinder;

    // Инициализируем поля ввода, добавляем слушателей на изменение текста
    private void initViews(int id, String calture) {

        fieldIdView.setText(String.valueOf(id));

        caltureView.setText(calture);

        commentView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                formPresenter.setComment(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        fieldAreaView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                formPresenter.setFieldArea(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        countWeedsView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                formPresenter.setCountWeeds(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }


}
