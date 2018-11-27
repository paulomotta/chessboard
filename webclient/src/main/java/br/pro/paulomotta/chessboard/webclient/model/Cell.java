package br.pro.paulomotta.chessboard.webclient.model;

/**
 *
 * @author paulo
 */
public class Cell {
    private boolean label = false;
    private String name;
    private int color;
    private String bgColor;
    
    public String getName(){
        return name;
    }

    /**
     * @return the color
     */
    public int getColor() {
        return color;
    }

    public String getBgColor() {
        if (bgColor != null) return bgColor;
        if (label) return "#CCCCCC";
        return (color==0?"#000000":"#FFFFFF");
    }
    
    public void setBgColor(String s) {
        bgColor = s;
    }
    
    public String getTextColor() {
        if (bgColor != null) return "#000000";
        if (label) return "#CCCCCC";
        return (color!=0?"#000000":"#FFFFFF");
    }
    
    public void setTextColor(String s) {
    }
    /**
     * @param color the color to set
     */
    public void setColor(int color) {
        this.color = color;
    }

    /**
     * @return the label
     */
    public boolean isLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(boolean label) {
        this.label = label;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
