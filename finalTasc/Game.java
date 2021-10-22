import java.util.*;

public class Game {
    private int shipNum = 1; //船の数
    public final int stageSize = 5; //ステージのサイズ
    private int turnNum = 0; //現在のターン
    private Ship[] shipStorage = new Ship[shipNum]; //船のインスタンス保存用配列; //船の保管場所
    private int remainingShipCnt = 0; //残りの船の数
    private Ship[][] stage = new Ship[stageSize][stageSize];; //ステージの配列
    private int bombX; //爆弾x座標
    private int bombY; //爆弾y座標 
    
    

    public void gameStart(){
        Scanner sc = new Scanner(System.in);
        while(remainingShipCnt>0){ //残りの船が0になるまで
            turnNum++; //ターンをカウント
            displayTurn(); //ターンを表示
            dispShipStatus(); //船のを表示
            inputBomb(sc); //座標入力
            for(int i=0; i<shipNum; i++){
                if(shipStorage[i] != null){  //沈没していない時
                    Ship ship = shipStorage[i]; //選択中の船
                    int result = ship.judgmentShip(bombX-1,bombY-1); //爆弾の船の命中判定
                    if(result == 0){//命中した時
                        hitBomb(ship,i); 
                    }else if(result == 1){//波高しの時
                        System.out.printf("船%d：%s\n",i+1,"波高し");
                    }else if(result == 2){ //外れたとき
                        System.out.printf("船%d：%s\n",i+1,"はずれ");
                    }
                }
            }
        }
        sc.close();
    }

    public void title(){    //タイトル
        System.out.println("*********************"); 
        System.out.println("      戦艦ゲーム      ");
        System.out.println("*********************");
    }
    
    //初期設定
    public void gameSetting(){
        shipStorage = new Ship[shipNum]; //船のインスタンス保存用配列
        for(int i=0; i<shipNum; i++){
            Ship ship = new Ship(); //インスタンスを作成
            shipStorage[i] = ship; //船のインスタンスを配列に格納
            ship.placement(stage,ship); //船をステージに配置
            remainingShipCnt++; //残りの船をカウント
        }
    }

    //ターン数を表示
    public void displayTurn(){
        System.out.printf ("------[ターン%d]------\n",turnNum); //ターン数表示
    }
    
    //船の状態を表示
    public void dispShipStatus(){ 
        for(int i=0; i<shipNum; i++){ 
            if(shipStorage[i] != null){ //船が存在している時
                int sHp = shipStorage[i].getHp();
                System.out.printf("船%d：生きている(hp:%d)\n",i+1,sHp);
            }else{
                System.out.printf("船%d：沈没\n",i+1);
            }
        }
    }

    //座標の入力
    public void inputBomb(Scanner sc){
        //x座標
        do{
            System.out.printf("爆弾のx座標を入力してください(1-%d)\n",stageSize);
            bombX = Integer.valueOf(sc.nextLine());
            if(bombX < 1 || bombX > stageSize){
                System.out.printf("1~%dの間で選択してください\n",stageSize);
            }
        }while(!(bombX >= 1 && bombX <= stageSize)); //0以下の時またはステージサイズより大きい時は繰り返す
        //y座標
        do{
            System.out.printf("爆弾のy座標を入力してください(1-%d)\n",stageSize);
            bombY = Integer.valueOf(sc.nextLine());
            if(bombY < 1 || bombY > stageSize){
                System.out.printf("1~%dの間で選択してください\n",stageSize);
            }
        }while(!(bombY >= 1 && bombY <= stageSize)); //0以下の時またはステージサイズより大きい時は繰り返す

    }

    

    //命中したときのメソッド
    public void hitBomb(Ship ship,int i){
        ship.damage(); //hpを減らす
        int shipHp = ship.getHp();
        if(shipHp <= 0){ //撃沈したとき
            System.out.printf("船%d：%s\n",i+1,"撃沈！！");
            remainingShipCnt--;
        }else{ //命中したが沈まないとき
            System.out.printf("船%d：%s\n",i+1,"命中したがまだ沈まない。船が移動します");
            ship.move(stage,ship); //再配置
        }
    }

    
    
   
    public void gameClear(){ //ゲームクリア
        System.out.println("---------------------");
        System.out.println("*********************");
        System.out.println("     ゲームクリア     ");
        System.out.println("*********************");
        System.out.printf("全ての船を沈没させるのに%dターンかかりました。",turnNum);
    }
}
