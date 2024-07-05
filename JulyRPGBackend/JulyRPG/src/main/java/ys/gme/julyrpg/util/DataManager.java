package ys.gme.julyrpg.util;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * 檔案資料存取類
 * @author yoskir
 */
@Component
public class DataManager {
    public String readStringFromFile(Enums.DataFileTypeEnum dataFileTypeEnum){
        try {
            Path path=getPathAddFileName(dataFileTypeEnum);
            assert path != null;
            return Files.readString(path);
        } catch (IOException e) {
            Constant.DebugLog("資料讀取失敗 "+e.getMessage());
            return "";
        }
    }

    /**
     * 回傳包含檔名的完整儲存路徑
     * @param dataFileTypeEnum 資料檔案類別
     * @return 完整路徑
     */
    private Path getPathAddFileName(Enums.DataFileTypeEnum dataFileTypeEnum) {
        return switch (dataFileTypeEnum) {
            case name -> Paths.get(Objects.requireNonNull(getDataFileURI("/data/name_data.txt")));
            default -> null;
        };
    }

    /**
     * 獲得程式內部檔案的URI
     * @param filePath 檔案resource中相對位置
     * @return URI
     */
    private URI getDataFileURI(String filePath){
        try {
            return Objects.requireNonNull(getClass().getResource(filePath)).toURI();
        }catch (URISyntaxException e) {
            Constant.DebugLog("URI獲取失敗",e.getMessage());
            return null;
        }
    }

}
