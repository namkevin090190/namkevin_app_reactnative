package Helpers;

import org.apache.commons.io.IOUtils;
import org.testng.ITestResult;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Helpers {

    public static String getProjectPath() {
        return System.getProperty("user.dir");
    }

    public static String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
        return dateFormat.format(new Date());
    }

    public static void deleteAllFilesFromDirectory(String pathFodler) {
        String directory = pathFodler;

        File file = new File(directory);
        String[] currentFiles;
        if (file.isDirectory()) {
            currentFiles = file.list();
            for (int i = 0; i < currentFiles.length; i++) {
                File myFile = new File(file, currentFiles[i]);
                myFile.delete();
            }
        }
    }

    public static void ExcuteCommandCMD(String cmd_command) {
        try {
            // Khởi tạo một ProcessBuilder với lệnh ipconfig
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", cmd_command);

            // Thiết lập các thuộc tính cho quá trình
            builder.redirectErrorStream(true);
            Process process = builder.start();

            // Đọc kết quả từ đầu ra của quá trình
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Đợi quá trình kết thúc và lấy mã trạng thái
            int exitCode = process.waitFor();
            System.out.println("Exit code: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static byte[] convertToByteArray(File file) throws IOException, IOException {
        FileInputStream fis = new FileInputStream(file.getPath());
        byte[] bytes = IOUtils.toByteArray(fis);
        fis.close();
        return bytes;
    }

    public static String createFileNameImage(ITestResult result) {
        return result.getName() + "_" + Helpers.getDateTime() + ".png";
    }

    public static String createFileNameVideo(ITestResult result) {
        return result.getName() + "_" + Helpers.getDateTime() + ".avi";
    }

}

