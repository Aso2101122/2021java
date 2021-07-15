import java.util.*;

public class Game {
    private int turnNum = 0; //現在のターン
    private int shipNum = 1; //船の数
    private int remainingShipCnt = 0; //残りの船の数
    public final int stageSize = 5; //ステージのサイズ
    private int maxTurnNum = 100; //無限ループ回避用
    

    public void gameStart(){
        Scanner sc = new Scanner(System.in);
        System.out.println("*********************"); //タイトル
        System.out.println("      戦艦ゲーム      ");
        System.out.println("*********************");

        Ship[][] stage = new Ship[stageSize][stageSize]; //ステージ配列の作成
        Ship[] shipStorage = new Ship[shipNum]; //船のインスタンス保存配列
        for(int i=0; i<shipNum; i++){
            Ship ship = new Ship();
            shipStorage[i] = ship; //船のインスタンスアドレスを記録
            ship.placement(stage,ship); //船を配置
            remainingShipCnt++; //残りの船をカウント
        }

        //ゲーム
        while(remainingShipCnt>0 && turnNum < maxTurnNum){
            turnNum++; //ターンをカウント
            System.out.printf ("------[ターン%d]------\n",turnNum); //ターン数表示
            for(int i=0; i<shipNum; i++){ 
                if(shipStorage[i] != null){ //船が存在している時
                    int sHp = shipStorage[i].getHp();
                    System.out.printf("船%d：生きている(hp:%d)\n",i+1,sHp);
                }else{
                    System.out.printf("船%d：沈没\n",i+1);
                }
            }
            //X座標入力
            int bombX;
            do{
                System.out.println("爆弾のx座標を入力してください(1-5)");
                bombX = sc.nextInt();
                if(bombX < 1 || bombX > stageSize){
                    System.out.println("1~5の間で選択してください");
                }
            }while(!(bombX >= 1 && bombX <= stageSize)); //0以下の時またはステージサイズより大きい時は繰り返す
                

                //Y座標入力
            int bombY;
            do{
                System.out.println("爆弾のy座標を入力してください(1-5)");
                bombY = sc.nextInt();
                if(bombY < 1 || bombY > stageSize){
                    System.out.println("1~5の間で選択してください");
                }
            }while(!(bombY >= 1 && bombY <= stageSize)); //0以下の時またはステージサイズより大きい時は繰り返す

            for(int i=0; i<shipNum; i++){
                if(shipStorage[i] != null){
                    Ship ship = shipStorage[i]; //選択中の船
                    int result = ship.judgmentShip(bombX-1,bombY-1);
                    if(result == 0){ //命中した時
                        ship.damage(); //hpを減らす
                        int shipHp = ship.getHp();
                        if(shipHp <= 0){ //撃沈したとき
                            System.out.printf("船%d：%s\n",i+1,"撃沈！！");
                            remainingShipCnt--;
                        }else{ //命中したが沈まないとき
                            System.out.printf("船%d：%s\n",i+1,"命中したがまだ沈まない。船が移動します");
                            ship.move(stage,ship); //再配置
                        }
                    }else if(result == 1){
                        System.out.printf("船%d：%s\n",i+1,"波高し");
                    }else{
                        System.out.printf("船%d：%s\n",i+1,"はずれ");
                    }
                }
            }
        }
        System.out.println("---------------------");
        System.out.println("*********************");
        System.out.println("     ゲームクリア     ");
        System.out.println("*********************");
        System.out.printf("全ての船を沈没させるのに%dターンかかりました。",turnNum++);
        sc.close();
    }
}