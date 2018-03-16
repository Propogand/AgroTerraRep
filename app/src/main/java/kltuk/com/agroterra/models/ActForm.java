package kltuk.com.agroterra.models;

public class ActForm {

    // Количество сорняков
    public int count_weeds;

    // Площадь поля
    public float field_area;

    // Общее количество сорняков
    public float total_count_weeds;

    // Комментарий
    public String comment;

    // ID
    public int field_id;

    public ActForm() {
        count_weeds = 0;
        field_area = 0;
        total_count_weeds = 0;
        comment = "";
        field_id = 0;
    }

}
