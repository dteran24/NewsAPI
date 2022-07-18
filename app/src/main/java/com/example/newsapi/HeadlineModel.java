package com.example.newsapi;


/**We create a model so that we can add data to each article when we create a list
 */
public class HeadlineModel {
    private String headlineText;
    private String DescText;

    public HeadlineModel(String headlineText, String descText) {
        this.headlineText = headlineText;
        DescText = descText;
    }

    public HeadlineModel() {

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
