package com.bekrenov.util;

import javax.swing.*;
import java.awt.*;

public class ImageScaler {

    /**
     * Returns an instance of ImageIcon class scaled to size of params width and height
     * */
    public static ImageIcon scale(String filePath, int width, int height) {
        ImageIcon icon = new ImageIcon(filePath);
        Image imgScaled = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(imgScaled);
    }

    public static ImageIcon scale(ImageIcon icon, int width, int height){
        Image imgScaled = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(imgScaled);
    }

    /*public static ImageIcon scaleProportionallyToLabel(ImageIcon icon, JLabel label){
        double firstProportion = (double)icon.getIconHeight() / (double)icon.getIconWidth();
        firstProportion = (double)Math.round(firstProportion * 1000d) / 1000d;
        int height = icon.getIconHeight();
        int width = icon.getIconWidth();
        if(icon.getIconWidth() > label.getWidth() && icon.getIconHeight() > label.getHeight()){
            for(int i = height; i >= 0;i--){
                for(int j = width; j >= 0; j--){
                    double proportion = (double)Math.round((double)i / (double)j * 1000d) / 1000d;
                    if(proportion == firstProportion && i <= label.getHeight() && j <= label.getWidth()){
                        System.out.println(i);
                        System.out.println(j);
                        return scale(icon, j, i);
                    }
                }
            }
        } else if(icon.getIconWidth() < label.getWidth() && icon.getIconHeight() < label.getHeight()){
            for(int i = height; i <= label.getHeight(); i++){
                for(int j = width; j <= label.getWidth(); j++){
                    double proportion = (double)Math.round((double)i / (double)j * 1000d) / 1000d;
                    if(proportion == firstProportion && (i == label.getHeight() || j == label.getWidth())){
                        System.out.println(i);
                        System.out.println(j);
                        return scale(icon, j, i);
                    }
                }
            }
        }

        return icon;
    }*/

}
