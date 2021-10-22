package rpgcreature;

import java.util.Random;

/**
 * スライムクラス
 */
public class Slime extends Monster{

    private final static int SLIME_HP = 12;
    private final int NOMAL_HIT_DAMAGE_LIMIT = 5;
    /**
     * スライムクラスのコンストラクタ
     */
    public Slime(){
        super("スライム",SLIME_HP);
    }

    /**
     * 攻撃メソッド
     * @param opponent：攻撃相手
     */
    @Override
    public void attack(Creature opponent) {
        
        Random r = new Random();
        int damage = r.nextInt(NOMAL_HIT_DAMAGE_LIMIT);
        System.out.printf("%sの攻撃！\n",getName());
        
        opponent.damaged(damage);
        
        displayMessage(opponent,damage);
        
    }
    
}
