package com.rateNUS.backend.image;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class ImageUtil {
    public static byte[] ImageUrlToByteArray(String urlString)  {
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException malformedURLException) {
            malformedURLException.printStackTrace();
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        assert (url != null);
        try (InputStream inputStream = url.openStream()) {
            int n = 0;
            byte[] buffer = new byte[1024];
            while (-1 != (n = inputStream.read(buffer))) {
                bos.write(buffer, 0, n);
            }
        }  catch (IOException exception) {
           exception.printStackTrace();
        }
        byte[] data = bos.toByteArray();
        System.out.println("image data: " + Arrays.toString(data));
        return data;
    }
}
