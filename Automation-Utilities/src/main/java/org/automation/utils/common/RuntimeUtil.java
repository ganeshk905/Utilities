package org.automation.utils.common;

/**
 * Created by shantonu on 9/4/16.
 */
public class RuntimeUtil {

    public static Long getFreeMemory(){

        return Runtime.getRuntime().freeMemory();
    }

    public static void shutdownJVM(){
        Runtime.getRuntime().exit(0);
    }
    public static void initiateShutdown(Thread hook){
        Runtime.getRuntime().addShutdownHook(hook);
    }
    public static void avoidShutdown(Thread hook){
        Runtime.getRuntime().removeShutdownHook(hook);
    }
}