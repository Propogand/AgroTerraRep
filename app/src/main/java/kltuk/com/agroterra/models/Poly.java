
package kltuk.com.agroterra.models;

import android.graphics.Color;
import android.provider.BaseColumns;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Poly implements Serializable {

    // ID
    @SerializedName("id")
    @Expose
    private Integer id;
    // Название поля
    @SerializedName("name")
    @Expose
    private String name;
    // Название культуры
    @SerializedName("calture")
    @Expose
    private String calture;
    // Цвет полигона
    @SerializedName("color")
    @Expose
    private String color;
    // Полигон
    @SerializedName("polygons")
    @Expose
    private String polygons;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCalture() {
        return calture;
    }

    public void setCalture(String calture) {
        this.calture = calture;
    }

    public int getColor() {
        return Color.parseColor(color);
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<LatLng> getPolygons() {
        return convertPolygons();
    }

    public void setPolygons(String polygons) {
        this.polygons = polygons;
    }

    private List<LatLng> convertPolygons() {

        List<LatLng> result = new ArrayList<>();

        StringBuilder builderPoly = new StringBuilder(polygons);

        while (builderPoly.length() > 0) {

            int firstIndex = builderPoly.indexOf("[");
            int lastIndex = builderPoly.indexOf("]") + 1; // + 1 - чтобы захватить данный символ в диапозон

            String subString = builderPoly.substring(firstIndex, lastIndex);
            subString = subString.replace("[","");
            subString = subString.replace("]","");

            builderPoly.delete(firstIndex, lastIndex);

            int indexComma = builderPoly.indexOf(",");

            if (indexComma != -1) {
                builderPoly.delete(indexComma, indexComma + 1);
            }

            String[] latLngStr = subString.split(",");

            LatLng latLng = new LatLng(Double.parseDouble(latLngStr[1]), Double.parseDouble(latLngStr[0]));

            result.add(latLng);

        }


        return result;

    }

    public static class PolyEntry implements BaseColumns {
        public static final String TABLE_NAME = "Poly";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_NAME_NULLABLE = "";

    }

}
