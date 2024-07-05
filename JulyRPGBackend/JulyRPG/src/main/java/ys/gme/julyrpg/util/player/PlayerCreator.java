package ys.gme.julyrpg.util.player;

import org.springframework.stereotype.Component;
import ys.gme.julyrpg.entity.Player;
import ys.gme.julyrpg.entity.collection.player.PlayerInfo;
import ys.gme.julyrpg.util.Constant;
import ys.gme.julyrpg.util.NameGenerator;

/**
 * 創建選手的類
 * @author yoskir
 */
@Component
public class PlayerCreator {
    private final NameGenerator nameGenerator;

    public PlayerCreator(NameGenerator nameGenerator){
        this.nameGenerator=nameGenerator;
    }
    public Player createPlayer(){
        Player player=new Player();

        //創建選手資訊
        PlayerInfo playerInfo=new PlayerInfo();
        playerInfo.setNation(nameGenerator.randomNation());
        playerInfo.setName(nameGenerator.getRandomName(playerInfo.getNation()));
        setPotential(playerInfo);
    }

    /**
     * 設定選手的潛能
     * @param playerInfo 選手名
     */
    private void setPotential(PlayerInfo playerInfo) {
        //特殊名稱獎勵
        Integer potential_hp=playerInfo.getName().equals("宮城良田")?15:Constant.getRandomInt(0,10);
        Integer potential_mp=playerInfo.getName().equals("犬與 羅霸特")?15:Constant.getRandomInt(0,10);
        Integer potential_str=playerInfo.getName().equals("厄林．哈蘭德")?15: Constant.getRandomInt(0,10);
        Integer potential_spd=playerInfo.getName().equals("基利安．姆巴佩")?15:Constant.getRandomInt(0,10);
        Integer potential_mag=playerInfo.getName().equals("里奧．梅西")?15:Constant.getRandomInt(0,10);
        Integer potential_luc;
        if(playerInfo.getName().equals("伍佑群")||playerInfo.getName().equals("吳玟頤")){
            potential_luc=30;
        }else {
            potential_luc=Constant.getRandomInt(0,10);
        }
        lis
    }
    /**
     * 若傳入為null(檢查是否有特殊獎勵)則回傳隨機0~10潛能值
     * @return 潛能值
     */
    private Integer randomPotential(Integer potential){
        return potential==null?Constant.getRandomInt(0,10):potential;
    }
}
