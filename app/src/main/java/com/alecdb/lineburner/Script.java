package com.alecdb.lineburner;

/**
 * User-created scripts, which will be formed from data stored in the Script Entries and Scene Entries tables.
 * Created by ALec on 1/23/2016.
 */
public class Script {
    public String title;
    public String subTitle;
    public userScene[] sceneArray; // These scenes are found in the Scene Entries table, which is linked to Script Entries.

    public Script(String title, String subTitle) {
        this.title = title;
        this.subTitle = subTitle;
    }


}
