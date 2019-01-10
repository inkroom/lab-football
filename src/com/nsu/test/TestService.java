package com.nsu.test;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nsu.service.ServiceManager;

public class TestService {
//	public static void main(String[] args) throws SocketException, UnknownHostException {
		
		
//		Pattern scriptPattern = Pattern.compile("&nbsp", Pattern.CASE_INSENSITIVE);
//		String value = "asdfhu&nbsp";
//        value = scriptPattern.matcher(value).replaceAll("");
//        System.out.println(value);
		
		
//		Enumeration allNetInterfaces = NetworkInterface.getNetworkInterfaces();
//		InetAddress ip = null;
//		while (allNetInterfaces.hasMoreElements())
//		{
//			NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
//			System.out.println(netInterface.getName());
//			Enumeration addresses = netInterface.getInetAddresses();
//			while (addresses.hasMoreElements()){
//				ip = (InetAddress) addresses.nextElement();
//				if (ip != null && ip instanceof Inet4Address){
//					System.out.println("本机的IP = " + ip.getHostAddress());
//				} 
//			}
//		}
//		getLocalIP();

		
//		String a = "19a4777a595817056ad9a009398c0cd31ae603fc0c08ff88cbd2932d2b55c79822bad88733e75f03e9c59e430c5355604bc085c02adabb87a51c9dcbd2889d539a560a8d2ac71692208e19a8caace5a04a115cf7fc597e9ee9cb9f724c83a103c6a1c31931bf78e64f77d58711e43424e4de55b9694b0df5238299a26051d6c72ae5e1a4cce26434fbf31e5e523b06484a1f295e4b7d077f48cf21454a56930a31ebe3051631ca49751a30455e13025f0282bf5b06d51719553942501304c1859379dd420f8f04c0e8fe85ee7008bf36d02e0d04bd16ac3cfecd8b1d66783b08";
//		System.out.print(a.substring(0, 112));
		
//		String[] xmlList = {"classpath:spring/applicationContext.xml","classpath:spring/applicationContext-service.xml","classpath:spring/applicationContext-util.xml"};
//        ApplicationContext app = new ClassPathXmlApplicationContext(xmlList);
//        ServiceManager serviceManager = (ServiceManager) app.getBean("serviceManager");
//        serviceManager.getLogService().saveLogs("1", "1", "1", "1", "1");

		
		
//		InetAddress ia=null;
//        try {
//            ia=ia.getLocalHost();
//             
//            String localname=ia.getHostName();
//            String localip=ia.getHostAddress();
//            System.out.println("本机名称是："+ localname);
//            System.out.println("本机的ip是 ："+localip);
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//	}
	
	
	
	
	
	
	
	
//	/**
//     * 获取本地IP地址
//     *
//     * @throws SocketException
//     */
//    public static String getLocalIP() throws UnknownHostException, SocketException {
//        if (isWindowsOS()) {
//            return InetAddress.getLocalHost().getHostAddress();
//        } else {
//            return getLinuxLocalIp();
//        }
//    }
 
//    /**
//     * 判断操作系统是否是Windows
//     *
//     * @return
//     */
//    public static boolean isWindowsOS() {
//        boolean isWindowsOS = false;
//        String osName = System.getProperty("os.name");
//        if (osName.toLowerCase().indexOf("windows") > -1) {
//            isWindowsOS = true;
//        }
//        return isWindowsOS;
//    }
 
    /**
     * 获取本地Host名称
     */
    public static String getLocalHostName() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostName();
    }
 
    /**
     * 获取Linux下的IP地址
     *
     * @return IP地址
     * @throws SocketException
     */
    private static String getLinuxLocalIp() throws SocketException {
        String ip = "";
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                String name = intf.getName();
                if (!name.contains("docker") && !name.contains("lo")) {
                    for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                        InetAddress inetAddress = enumIpAddr.nextElement();
                        if (!inetAddress.isLoopbackAddress()) {
                            String ipaddress = inetAddress.getHostAddress().toString();
                            if (!ipaddress.contains("::") && !ipaddress.contains("0:0:") && !ipaddress.contains("fe80")) {
                                ip = ipaddress;
                                System.out.println(ipaddress);
                            }
                        }
                    }
                }
            }
        } catch (SocketException ex) {
            System.out.println("获取ip地址异常");
            ip = "127.0.0.1";
            ex.printStackTrace();
        }
        System.out.println("IP:"+ip);
        return ip;
    }
 
    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
     *
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
     *
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
     * 192.168.1.100
     *
     * 用户真实IP为： 192.168.1.110
     *
     * @param request
     * @return
     */
//    public static String getIpAddress(HttpServletRequest request) {
//        String ip = request.getHeader("x-forwarded-for");
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("WL-Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("HTTP_CLIENT_IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getRemoteAddr();
//        }
//        return ip;
//    }
}
