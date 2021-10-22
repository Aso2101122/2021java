package rpgcreature;

import java.util.Random;

/**
 * 魔法使いクラス
 */
public class Wizard extends Monster{
    private final int NORMAL_DAMAGE_LIMIT = 5;
    private final int NORMAL_DAMEGE_lOWER_LIMIT = 1;
    private final int MAGIC_HIT_DAMAGE_LIMIT = 10;
    private final int MAGIC_HIT_DAMAGE_lOWER_LIMIT = 10;
    private final int MAGIC_HIT_RATE = 70;
    
    private final static int WIZARD_MAX_HP = 30;
    /**
     * 魔法使いのコンストラクタ
     */
    public Wizard(){
        super("魔法使い", WIZARD_MAX_HP);
    }

    /**
     * 攻撃メソッド
     * @param opponent：攻撃相手
     */
    @Override
    public void attack(Creature opponent) {
        
        Random r = new Random();
        int damage = 0;
        if( r.nextInt(100) < MAGIC_HIT_RATE){
            System.out.printf("%sは魔法を唱えた！\n",getName());
            damage = r.nextInt(MAGIC_HIT_DAMAGE_LIMIT)+MAGIC_HIT_DAMAGE_lOWER_LIMIT;
        }else{
            System.out.printf("%sの攻撃！\n",getName());
            damage = r.nextInt(NORMAL_DAMAGE_LIMIT)+NORMAL_DAMEGE_lOWER_LIMIT;
        }
        opponent.damaged(damage);
        
        displayMessage(opponent,damage);
        
    }
}
