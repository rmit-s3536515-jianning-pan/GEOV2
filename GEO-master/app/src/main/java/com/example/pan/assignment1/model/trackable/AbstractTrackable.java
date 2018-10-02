package com.example.pan.assignment1.model.trackable;

public abstract class AbstractTrackable implements Trackable {

    private static int count = 0; //add 1 to Id for each instance
    private int Id;
    private String name;
    private String description;
    private String url;
    private String category;

    public AbstractTrackable(String name,String description,String url,String category){
        count +=1;
        Id = count;
        this.name = name;
        this.description = description;
        this.url = url;
        this.category = category;
    }

    public AbstractTrackable(){

    }

    public int getId(){
        return Id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description =description;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public void setCategory(String category) {
        this.category = category;
    }


}
