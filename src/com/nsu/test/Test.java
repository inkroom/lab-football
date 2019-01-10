package com.nsu.test;

import com.nsu.common.Constants;
import com.nsu.util.InfoProtUtil;
import com.nsu.util.NewFileUtils;
import redis.clients.jedis.Jedis;

import javax.swing.filechooser.FileSystemView;
import java.io.File;

/**
 * Created by MeiXiebing on 4/10/17.
 */
public class Test {


    public static void main(String[] args) {
        Jedis jedis = new Jedis("10.14.2.212",6379);
        jedis.auth("a7217sec!@#");
        System.out.println("Connection to server sucessfully");
        System.out.println(jedis.ping());
        //查看服务是否运行
        jedis.set("mxb","123");
        System.out.println(jedis.get("mxb"));
//        String url = "/admin/index.html";
//        String roleString = url.substring(1,url.indexOf("/",1));
//        System.out.println(roleString);
    }

    private static String getBasePath(){
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File com=fsv.getHomeDirectory();
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("mac")){

            return com.getPath()+File.separator + "Desktop" + File.separator;

        }if (os.toLowerCase().startsWith("linux")){

            return File.separator+"home"+ File.separator;

        }if (os.toLowerCase().startsWith("win")){

            return  com.getPath() + File.separator;

        }

        return  "/upload";

    }


}
