/*
 * Create by Hamza elkhatri
 */

/*
 * Create by Hamza elkhatri
 */

package com.example.netflix.models;

public class slide {
    private int image;
    private String Title;


    public slide(int image,String Title)
    {
        this.image = image;
        this.Title = Title;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return Title;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
