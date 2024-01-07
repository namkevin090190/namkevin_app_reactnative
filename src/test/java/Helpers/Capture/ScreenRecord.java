package Helpers.Capture;



import Helpers.Helpers;

import org.monte.media.Format;
import org.monte.media.Registry;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
;



import java.awt.*;

import java.io.File;
import java.io.IOException;

import static org.monte.media.AudioFormatKeys.EncodingKey;
import static org.monte.media.AudioFormatKeys.FrameRateKey;
import static org.monte.media.AudioFormatKeys.KeyFrameIntervalKey;
import static org.monte.media.AudioFormatKeys.MediaTypeKey;
import static org.monte.media.AudioFormatKeys.MimeTypeKey;
import static org.monte.media.VideoFormatKeys.MIME_AVI;
import static org.monte.media.VideoFormatKeys.*;


public class ScreenRecord extends ScreenRecorder {

    private static ScreenRecorder screenRecorder;
    private static String name;

    public ScreenRecord(GraphicsConfiguration cfg, Rectangle captureArea, Format fileFormat, Format screenFormat, Format mouseFormat, Format audioFormat, File movieFolder, String name) throws IOException, AWTException {
        super(cfg, captureArea, fileFormat, screenFormat, mouseFormat, audioFormat, movieFolder);
        this.name = name;
    }

    //    Hàm này bắt buộc để ghi đè custom lại hàm trong thư viên viết sẵn
    @Override
    protected File createMovieFile(Format fileFormat) throws IOException {

        if (!movieFolder.exists()) {
            movieFolder.mkdirs();
        } else if (!movieFolder.isDirectory()) {
            throw new IOException(movieFolder + " is not a directory.");
        }
//        dateFormat.format(new Date()) + "." + Registry.getInstance().getExtension(fileFormat)
        return new File(movieFolder, name + "_" + Helpers.getDateTime() + "." + Registry.getInstance().getExtension(fileFormat));
    }

    // Hàm Start record video
    public static File startRecord(String methodName) {
        //Tạo thư mục để lưu file video vào
        File file = new File("./" + "/Videos/");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        int width = screenSize.width;
//        int height = screenSize.height;

        System.out.println("width" + screenSize.width);
        System.out.println("height" + screenSize.height);

        Rectangle captureSize = new Rectangle(0, 0, screenSize.width, screenSize.height);

        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        try {
            screenRecorder = new ScreenRecord(gc, null, new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI), new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey, Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60), new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)), null, file, methodName);
            screenRecorder.start();
        } catch (IOException | AWTException e) {
            e.printStackTrace();
        }
        return new File(file.getPath().concat("\\".concat(methodName + "_".concat(Helpers.getDateTime().concat(".avi")))));
    }

    // Stop record video
    public static void stopRecord() {
        try {
            screenRecorder.stop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static void captureScreenshot(AppiumDriver<MobileElement> appiumDriver, String screenName) {
//        try {
//            String path = System.getProperty("user.dir") + "/ScreenRecord/Images/" + screenName;
//            File file = new File(path);
//            if (!file.exists()) {
//                Log.info("No Folder: " + path);
//                file.mkdir();
//                Log.info("Folder created: " + file);
//            }
//
//            Log.info("Driver for ScreenshotEx: " + appiumDriver);
//            // Tạo tham chiếu của TakesScreenshot
//            TakesScreenshot ts = (TakesScreenshot) appiumDriver;
//            // Gọi hàm capture screenshot - getScreenshotAs
//            File source = ts.getScreenshotAs(OutputType.FILE);
//            // result.getName() lấy tên của test case xong gán cho tên File chụp màn hình
//            FileUtils.copyFile(source, new File(path + "/" + screenName + "_" + dateFormat.format(new Date()) + ".png"));
//            Log.info("ScreenshotEx taken: " + screenName);
//
//        } catch (Exception e) {
//            System.out.println("Exception while taking screenshot: " + e.getMessage());
//        }
//    }

//    public static File getScreenshot(String screenshotName) {
//        Rectangle allScreenBounds = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
//        String dateName = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss.SSS").format(new Date());
//        BufferedImage image = null;
//        try {
//            image = new Robot().createScreenCapture(allScreenBounds);
//        } catch (AWTException e) {
//            throw new RuntimeException(e);
//        }
//
//        String path = System.getProperty("user.dir") + "/ScreenRecord/Videos" + File.separator + "images";
//        File folder = new File(path);
//        if (!folder.exists()) {
//            folder.mkdir();
//            Log.info("Folder created: " + folder);
//        }
//
//        String filePath = path + File.separator + screenshotName + dateName + ".png";
//        File file = new File(filePath);
//        try {
//            ImageIO.write(image, "PNG", file);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return file;
//    }
}


