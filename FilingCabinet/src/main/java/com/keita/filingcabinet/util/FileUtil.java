package com.keita.filingcabinet.util;

public abstract class FileUtil {

    public static boolean isAnAcceptableFile(String fileName){
        return fileName.split("\\.")[1].equalsIgnoreCase("pdf") ||
                fileName.split("\\.")[1].equalsIgnoreCase("docx") ||
                fileName.split("\\.")[1].equalsIgnoreCase("xlsx") ||
                fileName.split("\\.")[1].equalsIgnoreCase("jpeg") ||
                fileName.split("\\.")[1].equalsIgnoreCase("jpg") ||
                fileName.split("\\.")[1].equalsIgnoreCase("png") ||
                fileName.split("\\.")[1].equalsIgnoreCase("txt");
    }
}
