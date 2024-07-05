package ys.gme.julyrpg.util;

import jakarta.annotation.PreDestroy;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 常數類
 * @author yoskir
 */
@Component
public class Constant {
    @Getter
    private static final DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final Random random=new Random();
    /**
     * 獲得隨機整數
     * @param min 最低值
     * @param max 最高值
     * @return 隨機數
     */
    public static int getRandomInt(int min,int max){
        return random.nextInt(min,max+1);
    }

    //偵錯日誌皆使用異步處理
    private static final ExecutorService logExecutor= Executors.newSingleThreadExecutor();
    /**
     * 方便偵錯的訊息 彩色版
     * @param params 數量、型別不定的傳入參數
     */
    public static void DebugLog(Object... params){
        //用到的顏色與標頭
        String ERROR_PREFIX = "\033[38;5;01;1m|錯誤訊息| \033[0m";
        String TIME_COLOR = "\033[38;5;214m";
        String CLASS_COLOR = "\033[38;5;226;1m Class名稱: \033[0m\033[38;5;02m";
        String METHOD_COLOR = "\033[38;5;04;1m Method名稱: \033[0m\033[38;5;183m";
        String SEPARATOR_START =
                "\uD83D\uDC36\uD83D\uDC36\033[38;5;01;1m-\033[38;5;214;1m-\033[38;5;226;1m-\033[38;5;02;1m-\033[38;5;04;1m-\033[38;5;87;1m-\033[38;5;183;1m>";
        String SEPARATOR_END =
                "\033[38;5;183;1m<\033[38;5;87;1m-\033[38;5;04;1m-\033[38;5;02;1m-\033[38;5;226;1m-\033[38;5;214;1m-\033[38;5;01;1m-\033[0;97m\uD83D\uDC36\uD83D\uDC36";
        logExecutor.submit(()->{
            //獲取呼叫debug log的線程資訊
            StackTraceElement[] stackTrace=Thread.currentThread().getStackTrace();
            String className,methodName;
            if(stackTrace.length>2){
                StackTraceElement caller=stackTrace[2];
                className=caller.getClassName();
                methodName=caller.getMethodName();
            }else {
                className="未知類";
                methodName="未知方法";
            }
            //構建調試訊息
            String time=dtf.format(LocalDateTime.now());
            StringBuilder debugMessage= new StringBuilder(ERROR_PREFIX)
                    .append(TIME_COLOR).append(time)
                    .append(CLASS_COLOR).append(className)
                    .append(METHOD_COLOR).append(methodName)
                    .append("\n")
                    .append(SEPARATOR_START)
                    .append(" \033[0;97m");
            for(Object param:params){
                debugMessage.append(param).append(" ");
            }
            debugMessage
                    .append("\033[0m")
                    .append(SEPARATOR_END);
            System.out.println(debugMessage);
        });
    }
    private static final String[] COLOR =new String[]
            {"\033[38;5;01m","\033[38;5;214m","\033[38;5;226m","\033[38;5;02m"
                    ,"\033[38;5;04m","\033[38;5;87m","\033[38;5;183m"};
    private static final String[] COLOR_BOLD =new String[]
            {"\033[38;5;01;1m","\033[38;5;214;1m","\033[38;5;226;1m","\033[38;5;02;1m"
                    ,"\033[38;5;04;1m","\033[38;5;87;1m","\033[38;5;183;1m"};
    public static void RainBowPrint(String input){
        int colorIndex=0;
        String[] headerArr=input.split(" ");
        StringBuilder startStr =new StringBuilder();
        for(String s:headerArr){
            startStr.append(COLOR_BOLD[colorIndex]);
            colorIndex++;
            if(colorIndex>= COLOR_BOLD.length){
                colorIndex=0;
            }
            startStr.append(s).append(" ");
        }
        startStr.append("\033[0;97m");
        System.out.println(startStr);
    }
    public static void MoreRainBowPrint(String input){
        int colorIndex=0;
        String[] headerArr=input.split("");
        StringBuilder startStr =new StringBuilder();
        for(String s:headerArr){
            if(!s.equals(" ")){
                startStr.append(COLOR_BOLD[colorIndex]);
                colorIndex++;
                if(colorIndex>=COLOR_BOLD.length){
                    colorIndex=0;
                }
            }
            startStr.append(s);
        }
        startStr.append("\033[0;97m");
        System.out.println(startStr);
    }
    @PreDestroy
    public void shutDown(){
        logExecutor.shutdown();
        try {
            if(!logExecutor.awaitTermination(60, TimeUnit.SECONDS)){
                logExecutor.shutdownNow();
                if(!logExecutor.awaitTermination(60,TimeUnit.SECONDS)){
                    MoreRainBowPrint("異步處理器關閉失敗");
                }
            }
        }catch (InterruptedException e){
            logExecutor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
