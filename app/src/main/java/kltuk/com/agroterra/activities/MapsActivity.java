package kltuk.com.agroterra.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import kltuk.com.agroterra.C;
import kltuk.com.agroterra.R;
import kltuk.com.agroterra.models.Poly;
import kltuk.com.agroterra.mvp.presenters.MapsPresenter;
import kltuk.com.agroterra.mvp.views.MapsView;

public class MapsActivity extends BaseActivity implements OnMapReadyCallback, MapsView, GoogleMap.OnPolygonClickListener {

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnPolygonClickListener(this);

        mapsPresenter.getPolygons(this);

    }

    @Override
    public void addPolygon(PolygonOptions polygonOptions, Poly poly) {
        Polygon polygon = mMap.addPolygon(polygonOptions);
        polygon.setTag(poly);
        polygon.setClickable(true);
    }

    @Override
    public void showPosition(LatLngBounds.Builder builder) {

        int padding = (int) getResources().getDimension(R.dimen.padding_zoom);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(builder.build(), padding);

        mMap.animateCamera(cameraUpdate);

    }


    @Override
    public void onPolygonClick(Polygon polygon) {

        if (polygon.getTag() != null && polygon.getTag() instanceof Poly) {

            final Poly poly = (Poly) polygon.getTag();

            String title = poly.getName();
            String message = getString(R.string.message_open_field);

            showMessage(title, message, (dialogInterface, i) -> {

                Intent intent = new Intent(MapsActivity.this, FormActivity.class);
                intent.putExtra(C.BundleKeys.POLY, poly);

                startActivity(intent);

            });

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.maps_menu, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.maps_menu_result:
                Intent intent = new Intent(MapsActivity.this, ResultActivity.class);

                startActivity(intent);
                return true;
                default:

        }

        return super.onOptionsItemSelected(item);
    }

    // Внедряем презентер для управление логикой формы
    @InjectPresenter()
    MapsPresenter mapsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        setTitle(R.string.title_map);
    }

    private GoogleMap mMap;



}
