
package kltuk.com.agroterra.models;

import android.provider.BaseColumns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Act {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("field")
    @Expose
    private Poly field;
    @SerializedName("count_weeds")
    @Expose
    private Integer countWeeds;
    @SerializedName("field_area")
    @Expose
    private Integer fieldArea;
    @SerializedName("total_count_weeds")
    @Expose
    private Integer totalCountWeeds;
    @SerializedName("comment")
    @Expose
    private String comment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Poly getField() {
        return field;
    }

    public void setField(Poly field) {
        this.field = field;
    }

    public Integer getCountWeeds() {
        return countWeeds;
    }

    public void setCountWeeds(Integer countWeeds) {
        this.countWeeds = countWeeds;
    }

    public Integer getFieldArea() {
        return fieldArea;
    }

    public void setFieldArea(Integer fieldArea) {
        this.fieldArea = fieldArea;
    }

    public Integer getTotalCountWeeds() {
        return totalCountWeeds;
    }

    public void setTotalCountWeeds(Integer totalCountWeeds) {
        this.totalCountWeeds = totalCountWeeds;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public static class ActEntry implements BaseColumns {
        public static final String TABLE_NAME = "Act";
        public static final String COLUMN_FIELD_ID = "field_id";
        public static final String COLUMN_POLY_PRIMARY_KEY = "poly_primary_key";
        public static final String COLUMN_COUNT_WEEDS = "count_weeds";
        public static final String COLUMN_FIELD_AREA = "field_area";
        public static final String COLUMN_TOTAL_COUNT_WEEDS = "total_count_weeds";
        public static final String COLUMN_COMMENT = "comment";

        public static final String COLUMN_NAME_NULLABLE = "";
    }

}
