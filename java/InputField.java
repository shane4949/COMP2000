package com.example.practice2;

public class InputField {
    private String name;
    private String hint;
    private String inputType;
    private boolean isTitle;
    private boolean isDivider;

    // Constructor for fields
    public InputField(String name, String hint, String inputType) {
        this.name = name;
        this.hint = hint;
        this.inputType = inputType;
        this.isTitle = false;
        this.isDivider = false;
    }

    // Constructor for titles
    public InputField(String title) {
        this.name = title;
        this.hint = null;
        this.inputType = null;
        this.isTitle = true;
        this.isDivider = false;
    }

    // Constructor for dividers
    public InputField(boolean isDivider) {
        this.name = null;
        this.hint = null;
        this.inputType = null;
        this.isTitle = false;
        this.isDivider = isDivider;
    }

    public boolean isTitle() {
        return isTitle;
    }

    public boolean isDivider() {
        return isDivider;
    }

    public String getName() {
        return name;
    }

    public String getHint() {
        return hint;
    }

    public String getInputType() {
        return inputType;
    }
}

