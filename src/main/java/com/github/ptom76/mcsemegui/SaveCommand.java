
package com.github.ptom76.mcsemegui;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaveCommand {

    @SerializedName("commands")
    @Expose
    public List<Command> commands = null;

}