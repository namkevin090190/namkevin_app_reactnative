package DriverFactory.CapabilitiesEX;

import io.appium.java_client.remote.MobileCapabilityType;

public interface MobileCapabilityTypeEx extends MobileCapabilityType {

    String APP_PACKAGE = "appPackage";
    String APP_ACTIVITY = "appActivity";
    //    Có nên chặn cho đến khi ứng dụng được kiểm tra trả lại điều khiển cho người gọi sau khi hoạt động
//    của nó được bắt đầu bởi Trình quản lý hoạt động (true, giá trị mặc định) hoặc để tiếp tục kiểm tra
//    mà không chờ đợi điều đó (false).
    String APP_WAIT_FOR_LAUNCH = "AppWaitForLaunch";
    //Tự động cấp quyền mà ứng dụng đó yêu cầu.
    //Mặc định: false
    String AUTO_GRANT_PERMISSIONS = "autoGrantPermissions";
    String AVD_LAUNCH_TIMEOUT = "avdLaunchTimeout";
    //The maximum duration to wait until the appWaitActivity is focused in milliseconds (20000 by default)
    String APP_WAIT_DURATION = "appWaitDuration";
    String APP_WAIT_ACTIVITY = "appWaitActivity";
    String APP_WAIT_PACKAGE = "appWaitPackage";
    String BUNDLEID = "bundleId";

}
