package rpgcreature;

import java.util.Random;
/**
 * ゴーレムクラス
 */
public class Golem extends Monster{
    private final int NORMAL_DAMAGE_LIMIT = 5;
    private final int NORMAL_DAMEGE_lOWER_LIMIT = 5;
    private final int CRITICAL_HIT_RATE = 5;
    private final int CRITICAL_HIT_DAMAGE = 30;

    private final static int GOLEM_MAX_HP = 100;

    /**
     * ゴーレムクラスのコンストラクタ
     */
    public Golem(){
        super("ゴーレム",GOLEM_MAX_HP);
    }

    /**攻撃メソッド
     * @param opponent:攻撃相手
     */
    @Override
    public void attack(Creature opponent) {

        Random r = new Random();
        int damage = 0;
        if( r.nextInt(100) < CRITICAL_HIT_RATE){
            damage = CRITICAL_HIT_DAMAGE;
            System.out.printf("%sのクリティカルヒット！\n",getName());
        }else{
            damage = r.nextInt(NORMAL_DAMAGE_LIMIT)+NORMAL_DAMEGE_lOWER_LIMIT;
            System.out.printf("%sの攻撃！\n",getName());
        }
        opponent.damaged(damage);
        
        displayMessage(opponent,damage);
        
    }
}
