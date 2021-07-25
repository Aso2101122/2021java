import java.util.*;

public class Ship {
    private int x; //船のx座標
    private int y; //船のy座標
    private int hp; //船のhp
    

    public Ship() { //船を配置
        Random x = new Random();
        this.x = x.nextInt(5);
        Random y = new Random();
        this.y = y.nextInt(5);
        this.hp = 2;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getHp(){
        return hp;
    }

    public void placement(Ship[][] stage,Ship ship){ //船を配置
        boolean endFlag = false;
        int cnt = 0; //無限ループ回避用変数
        while(endFlag == false && cnt < 99){
            Random x = new Random();
            this.x = x.nextInt(4);
            Random y = new Random();
            this.y = y.nextInt(4);
            if(stage[this.x][this.y] == null){ //ランダムな場所に船がない場合
                stage[this.x][this.y] = ship; //船の場所を設定
                System.out.printf("%d,%d\n",this.x+1,this.y+1);
                endFlag = true;
            }
            cnt++;
        }
    }

    
    
    public int judgmentShip(int bombX, int bombY){ //判定メソッド
        if(bombX == x && bombY == y){ //命中判定
            return 0;
        }
        int[] shipSideNear = {x-1,x+1}; //船の左右のx座標
        int[] shipVerticalNear = {y-1,y+1}; //船の上下のx座標
        boolean flag = false;
        for(int i=0; i<2; i++){
            if(shipSideNear[i] == bombX && y == bombY){ //左右の波高し判定
                flag = true;
            }if(shipVerticalNear[i] == bombY && x == bombX){ //上下の波高し判定
                flag = true;
            }
        }
        if(flag == true){
            return 1;
        }else{
            return 2; //はずれ判定
        }
    }

    public void move(Ship[][] stage,Ship ship){ //船を再配置
        stage[this.x][this.y] = null; //現在の船の場所を削除
        boolean endFlag = false;
        int cnt = 0; //無限ループ回避用変数
        while(endFlag == false && cnt < 99){
            Random x = new Random();
            this.x = x.nextInt(5);
            Random y = new Random();
            this.y = y.nextInt(5);
            if(stage[this.x][this.y] == null){ //ランダムな場所に船がない場合
                stage[this.x][this.y] = ship; //船の場所を設定
                System.out.printf("%d,%d\n",this.x+1,this.y+1);
                endFlag = true;
            }
            cnt++;
        }
    }

    public void damage(){
        hp--;
    }

    public void sinking(int i,Ship[] shipStorage,Ship[][] stage){ //沈没したとき
        shipStorage[i] = null; //存在する船を削除
        stage[x][y] = null; //ステージ内の沈没した船を削除
    }
}