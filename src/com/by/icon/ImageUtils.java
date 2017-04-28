// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst 
// Source File Name:   ImageUtils.java

package com.by.icon;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class ImageUtils
{

    public ImageUtils()
    {
    }

    public static double getAspectRatio(BufferedImage img, int newWidth, int newHeight)
    {
        int imgWidth = img.getWidth(null);
        int imgHeight = img.getHeight(null);
        double xRatio = (double)newWidth / (double)imgWidth;
        double yRatio = (double)newHeight / (double)imgHeight;
        return Math.min(xRatio, yRatio);
    }

    public static Image resizeImage(BufferedImage bImg, int newWidth, int newHeight)
        throws FileNotFoundException, IOException
    {
        double ratio = getAspectRatio(bImg, newWidth, newHeight);
        newWidth = (int)((double)bImg.getWidth(null) * ratio);
        newHeight = (int)((double)bImg.getHeight(null) * ratio);
        Image resizedImage = bImg.getScaledInstance(newWidth, newHeight, 4);
        return resizedImage;
    }

    public static int getMaxWidth(File img)
        throws FileNotFoundException, IOException
    {
        BufferedImage bImg = ImageIO.read(new FileInputStream(img));
        int width = bImg.getWidth();
        int height = bImg.getHeight();
        return Math.max(width, height);
    }
}
