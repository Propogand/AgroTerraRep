package kltuk.com.agroterra.network;


import java.util.List;

import io.reactivex.Observable;
import kltuk.com.agroterra.C;
import kltuk.com.agroterra.models.ActForm;
import kltuk.com.agroterra.models.Poly;
import kltuk.com.agroterra.models.Response;
import kltuk.com.agroterra.models.Act;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AgroTerraApi {

    @GET(C.Network.API_GET_FIELDS)
    Observable<List<Poly>> getPolygons();

    @GET(C.Network.API_GET_ACT)
    Observable<List<Act>> getActs();

    @POST(C.Network.API_SEND_ACT)
    Observable<Response> createAct(@Body ActForm act);

}
