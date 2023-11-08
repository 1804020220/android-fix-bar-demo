package com.app.fixbardemo.ui;

public class itemBean {

    private String fruitName;
    private String fruitGroup;

    public itemBean(String fruitName, String fruitGroup) {
        this.fruitName = fruitName;
        this.fruitGroup = fruitGroup;
    }

    public String getFruitName() {
        return fruitName;
    }

    public void setFruitName(String fruitName) {
        this.fruitName = fruitName;
    }

    public String getFruitGroup() {
        return fruitGroup;
    }

    public void setFruitGroup(String fruitGroup) {
        this.fruitGroup = fruitGroup;
    }
}
