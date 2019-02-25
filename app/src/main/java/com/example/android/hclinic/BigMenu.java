package com.example.android.hclinic;

/**
 * Data model for each row of the RecyclerView
 */

public class BigMenu {
    // Member variables representing the title and information about the menu.
    private String title;
    private String linkto;
    private final int imageResource;

    public BigMenu(String title, String linkto, int imageResource) {
        this.title = title;
        this.linkto = linkto;
        this.imageResource = imageResource;
    }

    public String getTitle() {
        return title;
    }

    public String getLinkto() {
        return linkto;
    }

    public int getImageResource() {
        return imageResource;
    }
}
