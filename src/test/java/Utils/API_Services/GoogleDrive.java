package Utils.API_Services;


import Common.Log;
import Helpers.Helpers;
import Utils.PropertiesFile;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import static Helpers.Helpers.convertToByteArray;

public class GoogleDrive {
    private static final String APPLICATION_NAME = "autotest";
    private static final String SERVICE_ACCOUNT_EMAIL = "gg-api-autotest@autotest-381401.iam.gserviceaccount.com";
    private static final String SERVICE_ACCOUNT_PKCS12_FILE_PATH = Helpers.getProjectPath() + "\\resources\\Credentials\\key.p12";

    Drive drive;

    private Drive CreateDrive() throws GeneralSecurityException, IOException {
        // Authenticate with the Google Drive API using a service account
        GoogleCredential credential = new GoogleCredential.Builder()
                .setTransport(GoogleNetHttpTransport.newTrustedTransport())
                .setJsonFactory(JacksonFactory.getDefaultInstance())
                .setServiceAccountId(SERVICE_ACCOUNT_EMAIL)
                .setServiceAccountScopes(Collections.singleton(DriveScopes.DRIVE))
                .setServiceAccountPrivateKeyFromP12File(new FileInputStream(SERVICE_ACCOUNT_PKCS12_FILE_PATH))
                .build();
        // Create a Drive instance and authenticate with the given credentials
        return new Drive.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), credential)
                .setApplicationName(APPLICATION_NAME)
                .build();

    }

    // Create a new image file and set its metadata
    private File CreatFileAndSetMetadata(String FileName, String FolderId) {
        File fileMetadata = new File();
        fileMetadata.setName(FileName);
        fileMetadata.setParents(Collections.singletonList(FolderId));
        return fileMetadata;
    }

    // Upload the image file to Google Drive
    private String UploadFileToGoogleDrive(String FileName, File metadata, java.io.File file, String FileType) throws IOException {
        ByteArrayContent content = new ByteArrayContent(FileType, convertToByteArray(file));
        File fileUpload = drive.files().create(metadata, content).setFields("id,webViewLink").execute();
        Log.info("File upload - : ".concat(FileType + " id : ").concat(fileUpload.getId()));
        Log.info("Upload ".concat(FileName).concat(" success. \nLink: ") + fileUpload.getWebViewLink());
        return fileUpload.getWebViewLink();
    }


    public String UploadFilePhoto(String FileName, java.io.File file) throws GeneralSecurityException, IOException {
        //Create Drive instance
        drive = CreateDrive();

        //create Filemetadate
        PropertiesFile.setPropertiesFile("configs.properties");
        String FolderId = PropertiesFile.getPropValue("photos_ggdrive");
        File filemetadata = CreatFileAndSetMetadata(FileName, FolderId);

        // Upload the image file to Google Drive
        return UploadFileToGoogleDrive(FileName, filemetadata, file, "image/png");
    }

    public String UploadFileVideo(String FileName, java.io.File file) throws GeneralSecurityException, IOException {
        //Create Drive instance
        drive = CreateDrive();

        //create File Metadate
        PropertiesFile.setPropertiesFile("configs.properties");
        String FolderId = PropertiesFile.getPropValue("videos_ggdrive");
        File fileMetadata = CreatFileAndSetMetadata(FileName, FolderId);

//        // Upload the videos file to Google Drive
        return UploadFileToGoogleDrive(FileName, fileMetadata, file, "video/x-msvideo");
    }
}
