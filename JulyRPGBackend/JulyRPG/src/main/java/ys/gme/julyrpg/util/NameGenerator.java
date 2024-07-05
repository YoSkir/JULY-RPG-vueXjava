package ys.gme.julyrpg.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ys.gme.julyrpg.entity.name.NameEntity;
import ys.gme.julyrpg.entity.name.Nation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * 產生隨機名的工具
 * @author yoskir
 */
@Component
public class NameGenerator {

    private final Map<Enums.Nation,List<String>> firstNameMap=new HashMap<>();
    private final Map<Enums.Nation,List<String>> lastNameMap=new HashMap<>();

    public NameGenerator(DataManager dataManager,JsonMapper jsonMapper){
        String nameListJson= dataManager.readStringFromFile(Enums.DataFileTypeEnum.name);
        NameEntity nameList=jsonMapper.jsonToObject(nameListJson,NameEntity.class);
        setNameList(nameList);
    }

    /**
     * 載入名稱資料的Map
     * @param nameList 名稱資料容器
     */
    public void setNameList(NameEntity nameList){
        for(Nation nation:nameList.getNationalNameList()){
            firstNameMap.put(nation.getNation(),nation.getFirstNames());
            lastNameMap.put(nation.getNation(),nation.getLastNames());
        }
    }

    /**
     * 獲得隨機名字
     * @return 名字
     */
    public String getRandomName(){
        return getRandomName(randomNation());
    }
    public String getRandomName(Enums.Nation nation) {
        List<String> firstNameList;
        List<String> lastNameList;
        //米克斯名字列表
        if (Objects.requireNonNull(nation) == Enums.Nation.Mix) {
            Enums.Nation secondNation;
            Enums.Nation firstNation;
            //找非米克斯名列表
            do {
                secondNation = randomNation();
            } while (secondNation.equals(Enums.Nation.Mix));
            //找國籍不與名重複列表
            do {
                firstNation= randomNation();
            }while (firstNation.equals(Enums.Nation.Mix)||firstNation.equals(secondNation));
            firstNameList = firstNameMap.get(firstNation);
            lastNameList = lastNameMap.get(secondNation);
        } else {
            firstNameList = firstNameMap.get(nation);
            lastNameList = lastNameMap.get(nation);
        }
        String firstName=firstNameList.get(Constant.getRandomInt(1,firstNameList.size())-1);
        String lastName=lastNameList.get(Constant.getRandomInt(1,lastNameList.size())-1);
        //中國名一半機率雙字
        if(nation.equals(Enums.Nation.Chinese)){
            if(firstName.length()==1&&Constant.getRandomInt(0,1)>0){
                String secondFirstName=firstNameList.get(Constant.getRandomInt(1,firstNameList.size())-1);
                while (secondFirstName.length()>1){
                    secondFirstName=firstNameList.get(Constant.getRandomInt(1,firstNameList.size())-1);
                }
                firstName+=secondFirstName;
            }
        }else if(nation.equals(Enums.Nation.Mix)){
            lastName+=" ";
        }else if(nation.equals(Enums.Nation.Western)){
            lastName+="．";
        }
        return lastName+firstName;
    }

    /**
     * 獲得隨機國籍
     * @return 國籍
     */
    public Enums.Nation randomNation() {
        return switch (Constant.getRandomInt(1,4)){
            case 1-> Enums.Nation.Japanese;
            case 2-> Enums.Nation.Chinese;
            case 3-> Enums.Nation.Western;
            default -> Enums.Nation.Mix;
        };
    }
}
