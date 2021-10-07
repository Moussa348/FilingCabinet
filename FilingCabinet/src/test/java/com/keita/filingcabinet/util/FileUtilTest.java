package com.keita.filingcabinet.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class FileUtilTest {

    @Test
    void isPdfAnAcceptableFile(){
        //ARRANGE
        String fileName = "toto.pdf";

        //ACT
        boolean isAnAcceptableFile = FileUtil.isAnAcceptableFile(fileName);

        //ASSERT
        assertTrue(isAnAcceptableFile);
    }

    @Test
    void isDocAnAcceptableFile(){
        //ARRANGE
        String fileName = "toto.docx";

        //ACT
        boolean isAnAcceptableFile = FileUtil.isAnAcceptableFile(fileName);

        //ASSERT
        assertTrue(isAnAcceptableFile);

    }

    @Test
    void isXlsxAnAcceptableFile(){
        //ARRANGE
        String fileName = "toto.xlsx";

        //ACT
        boolean isAnAcceptableFile = FileUtil.isAnAcceptableFile(fileName);

        //ASSERT
        assertTrue(isAnAcceptableFile);
    }

    @Test
    void isJpegAnAcceptableFile(){
        //ARRANGE
        String fileName = "toto.jpeg";

        //ACT
        boolean isAnAcceptableFile = FileUtil.isAnAcceptableFile(fileName);

        //ASSERT
        assertTrue(isAnAcceptableFile);

    }

    @Test
    void isJpgAnAcceptableFile(){
        //ARRANGE
        String fileName = "toto.jpg";

        //ACT
        boolean isAnAcceptableFile = FileUtil.isAnAcceptableFile(fileName);

        //ASSERT
        assertTrue(isAnAcceptableFile);

    }

    @Test
    void isPngAnAcceptableFile(){
        //ARRANGE
        String fileName = "toto.png";

        //ACT
        boolean isAnAcceptableFile = FileUtil.isAnAcceptableFile(fileName);

        //ASSERT
        assertTrue(isAnAcceptableFile);

    }

    @Test
    void isTxtAnAcceptableFile(){
        //ARRANGE
        String fileName = "toto.txt";

        //ACT
        boolean isAnAcceptableFile = FileUtil.isAnAcceptableFile(fileName);

        //ASSERT
        assertTrue(isAnAcceptableFile);

    }

    @Test
    void isPyAnAcceptableFile(){
        //ARRANGE
        String fileName = "toto.py";

        //ACT
        boolean isAnAcceptableFile = FileUtil.isAnAcceptableFile(fileName);

        //ASSERT
        assertFalse(isAnAcceptableFile);

    }
}
