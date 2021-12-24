package com.rateNUS.backend.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class ImageUtil {
    public static byte[] ImageUrlToByteArray(String urlString) throws IOException {
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException malformedURLException) {
            malformedURLException.printStackTrace();
        }

        assert url != null;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream is = null;
        try {
            is = new BufferedInputStream(url.openStream());
            byte[] byteChunk = new byte[4096];
            int n;
            while ( (n = is.read(byteChunk)) > 0 ) {
                baos.write(byteChunk, 0, n);
            }
            return baos.toByteArray();
        }
        catch (IOException e) {e.printStackTrace ();}
        finally {
            if (is != null) { is.close(); }
        }
        return null;
    }
}
