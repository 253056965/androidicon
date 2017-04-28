// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst 
// Source File Name:   AndroidDrawableFactory.java

package com.by.icon;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class AndroidDrawableFactory {
	public static final String DENSITIES[] = { "ldpi", "mdpi", "hdpi", "xhdpi", "xxhdpi", "xxxhdpi" };
	public static final double DENSITY_MULTIPLIERS[] = { 0.75D, 1.0D, 1.5D, 2D, 3D, 4D };

	public AndroidDrawableFactory() {
	}
	public static void run(String filePath, String fileoutPath, String jibie,String iconName) throws IOException {
		File sourceImg = new File(filePath);
		BufferedImage bufferedSource = ImageIO.read(sourceImg);
		String densities[] = AndroidDrawableFactory.DENSITIES;
		double density_ratio[] = AndroidDrawableFactory.DENSITY_MULTIPLIERS;
		HashMap<String, Double> densityMap = new HashMap<>();
		for (int i = 0; i < densities.length; i++) {
			densityMap.put(densities[i], Double.valueOf(density_ratio[i]));
		}
		double targetDensity = densityMap.get(jibie).doubleValue();
		for (java.util.Map.Entry<String, Double> e : densityMap.entrySet()) {
			String projectPath = fileoutPath;
			File projectResourceRoot = new File(projectPath);
			if (!"res".equals(projectResourceRoot.getName()))
				projectResourceRoot = new File(projectResourceRoot, "res");
			if (true) {
				String folderName = (new StringBuilder("drawable-")).append((String) e.getKey()).toString()+"-v4";
				double densityRatio = ((Double) e.getValue()).doubleValue();
				int newWidth = Math
						.round((float) (((double) bufferedSource.getWidth() / targetDensity) * densityRatio));
				int newHeight = Math
						.round((float) (((double) bufferedSource.getHeight() / targetDensity) * densityRatio));
				try {
					Image newImg = ImageUtils.resizeImage(bufferedSource, newWidth, newHeight);
					File targetDir = new File(projectResourceRoot, folderName);
					boolean dirExists = false;
					dirExists = targetDir.exists() || targetDir.mkdirs();
					if (dirExists) {
						BufferedImage bufImg = new BufferedImage(newImg.getWidth(null), newImg.getHeight(null), 2);
						Graphics2D img2D = bufImg.createGraphics();
						img2D.drawImage(newImg, null, null);
						java.awt.image.RenderedImage targetImg = bufImg;
						File newFile = new File((new StringBuilder()).append(targetDir).append(File.separator)
								.append(iconName).toString());
						ImageIO.write(targetImg, "png", newFile);
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	public static void main(String args[]) throws IOException {
		//run("/Users/wuyake/IconSplashMaker/IC221.png", "/Users/wuyake/IconSplashMaker/", "xxxhdpi","icon.png");
		//Paths.get("").
		run(args[0], args[1], args[2],args[3]);
	}
}
