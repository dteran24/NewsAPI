package com.example.newsapi;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**We create a model so that we can add data to each article when we create a list
 Set up the table name as well as the primary key. RoomDB ignores constructors that are not needed*/
@Entity(tableName = "Headline")
public class HeadlineModel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String headlineText;
    private String DescText;

    public HeadlineModel(int id, String headlineText, String DescText) {
        this.headlineText = headlineText;
        this.DescText = DescText;
        this.id = id;
    }
    @Ignore
    public HeadlineModel(String headlineText, String DescText) {
        this.headlineText = headlineText;
        this.DescText = DescText;
    }
    @Ignore
    public HeadlineModel() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeadlineText() {
        return headlineText;
    }

    public void setHeadlineText(String headlineText) {
        this.headlineText = headlineText;
    }

    public String getDescText() {
        return DescText;
    }

    public void setDescText(String descText) {
        DescText = descText;
    }
}
