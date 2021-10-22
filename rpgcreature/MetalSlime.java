package rpgcreature;

import java.util.Random;

/**
 * メタルスライムクラス
 */
public class MetalSlime extends Monster{
    private final static int ESCAPE_RATE = 40;
    private final int NORMAL_DAMAGE_LIMIT = 5;
    
    /**
     * メタルスライムのコンストラクタ
     */
    public MetalSlime(){
        super("メタルスライム",12);
    }

    /**
     * 攻撃メソッド
     * @param opponent：攻撃相手
     */
    @Override
    public void attack(Creature opponent) {
        
        Random r = new Random();
        if( r.nextInt(100) < ESCAPE_RATE ){
            System.out.printf("%sは逃げ出した！\n",getName());
            escapeFlag = true;
        }else{
            int damage = r.nextInt(NORMAL_DAMAGE_LIMIT);
            System.out.printf("%sの攻撃！\n",getName());
            
            opponent.damaged(damage);
            
            displayMessage(opponent,damage);
        }
    }
}
