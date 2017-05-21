package eu.robo.veit.robojam;

import android.content.Context;
import android.content.Intent;

import eu.robo.veit.robojam.yumiDriver.MRIConnector;
import eu.robo.veit.robojam.yumiDriver.Rumpelstielzchen;
import eu.robo.veit.robojam.yumiDriver.Vector3;


import java.sql.SQLOutput;
import java.util.ArrayList;

/**
 * Created by Veit on 20.05.2017.
 */
public class Game {

    public static int phase = 0;
    public static int turns = 0;
    public static Entity playerChar;
    public static Entity currentGoal;
    public static Entity cat;
    public static Entity wolf;
    public static Entity bac1;
    public static Entity bac2;
    public static Entity coin1;
    public static Entity coin2;
    public static Entity coin3;

    public static ArrayList<Entity> gameObjects;
    public static int coinCount = 0;
    public static boolean movedOriginalPlayerChar = false;
    public static URDriver urDriver;
    private static int arrrsleft = 0;
    private static Rumpelstielzchen stielzchen;
    private static boolean initialized = false;

    public static void initGame(){
        if(initialized)
            return;
        initialized = true;
        /**
         * @todo hostname
         */
        MRIConnector leftArm=new MRIConnector("localhost", 30004);
        //leftArm.connect();
        //leftArm.execute("connect");
        MRIConnector rightArm=new MRIConnector("localhost", 30005);
        //rightArm.connect();
        //rightArm.execute("connect");
        stielzchen=new Rumpelstielzchen(leftArm, rightArm,new Vector3(630, -280, 150));

        playerChar = new Entity(GameObject.PLAYERCHAR,7,7);
        currentGoal = new Entity(GameObject.GOAL,1,0);
        gameObjects = new ArrayList<>();
        gameObjects.add(playerChar);
        gameObjects.add(currentGoal);
        bac1 = new Entity(GameObject.BARRICADE,3,2);
        bac2 = new Entity(GameObject.BARRICADE,6,4);
        gameObjects.add(bac1);
        gameObjects.add(bac2);
        //place Shirkan
        stielzchen.pickUpAt(4,1);
        stielzchen.placeAt(2,5);
        //place Scar (location G5 ???)
        stielzchen.pickUpAt(3,10);
        stielzchen.placeAt(4,8);

        //place kater
        stielzchen.pickUpAt(1,0);
        stielzchen.placeAt(0,3);


    }

//    public GameObject[][] field = new GameObject[8][8];

//    public void makeMove(GameField src, GameField dst){
//        GameObject tempObject  = field[src.posx][src.posy];
//        field[src.posx][src.posy] = GameObject.EMPTY;
//        field[dst.posx][dst.posy] = tempObject;
//    }

    public static boolean update(){
        turns++;
        System.out.println("Coins:" + coinCount);
        System.out.println("gamObjectsSize:" + gameObjects.size());
        boolean ret = false;

        for (Entity entity :
                gameObjects) {
            System.out.println(entity.type + " " + entity.posx + " " + entity.posy);

        }
        int x = gameObjects.size();
        for (int i = 0; i<x && i<gameObjects.size(); i++){
            Entity entity = gameObjects.get(i);
            System.out.println(entity.type + " " + entity.posx + " " + entity.posy);
            if(entity.type == GameObject.BARRICADE){
                if(entity.isAdjacent(playerChar))
                    lostGame();
            }
            if(entity.type == GameObject.GOAL){
                if(entity.isAdjacent(playerChar))
                    if(phase!=1) {
                        ret = true;
                    }
                    else {
                        if(entity.isAdjacent(cat) && coinCount >=3)
                            ret= true;
                    }

            }
            if(phase == 1 && entity.type == GameObject.COIN){
                if(entity.isAdjacent(cat)){
                    coinCount++;
                gameObjects.remove(entity);

                switch (coinCount){
                    case 1:
                        coin2 = new Entity(GameObject.COIN,2,6);
                        gameObjects.add(coin2);
                        // coin 2
                        stielzchen.pickUpAt(6,1);
                        stielzchen.placeAt(6,2);
                        break;
                    case 2:
                        coin3 = new Entity(GameObject.COIN,4,7);
                        gameObjects.add(coin3);
                        //coin 3
                        stielzchen.pickUpAt(7,10);
                        stielzchen.placeAt(7,6);
                        break;

                }
                }
            }
        }
        switch (phase){
            case 0:
                break;
            case 1:
                break;
            case 2:
                if(wolf.posx>playerChar.posx){
                    wolf.posx--;
                }
                else if(wolf.posx<playerChar.posx){
                    wolf.posx++;
                }
                else if(wolf.posy<playerChar.posy){
                    wolf.posy++;
                }
                else if(wolf.posy>playerChar.posy){
                    wolf.posy--;
                }
                if(wolf.isAdjacent(playerChar))
                    lostGame();
                break;
            case 3:
                if(arrrsleft>0){
                    if(currentGoal.posx>playerChar.posx){
                        gameObjects.add(new Entity(GameObject.ARRR,playerChar.posx+1, playerChar.posy));
                    }
                    else if(currentGoal.posx<playerChar.posx){
                        gameObjects.add(new Entity(GameObject.ARRR,playerChar.posx-1, playerChar.posy));
                    }
                    else if(currentGoal.posy<playerChar.posy){
                        gameObjects.add(new Entity(GameObject.ARRR,playerChar.posx, playerChar.posy-1));
                    }
                    else if(currentGoal.posy>playerChar.posy){
                        gameObjects.add(new Entity(GameObject.ARRR,playerChar.posx, playerChar.posy+1));
                    }
                    arrrsleft--;
                }
                break;
        }
        return ret;
    }

    public static void initNextPhase(){

        phase++; //waah
        System.out.println("Phase: " + phase + " !!!!!!!!!!!!!");

        switch (phase){
            case 0:
                break;
            case 1:
                gameObjects.remove(currentGoal);
                gameObjects.remove(bac1);
                gameObjects.remove(bac2);
                currentGoal = new Entity(GameObject.GOAL,7,3);
                gameObjects.add(currentGoal);
                coin1 = new Entity(GameObject.COIN,3,3);
                gameObjects.add(coin1);
                //coin1
                stielzchen.pickUpAt(7,0);
                stielzchen.placeAt(3,3);
                cat = new Entity(GameObject.CAT,1,0);
                gameObjects.add(cat);

                break;
            case 2:
                //place fiona at H4
                stielzchen.pickUpAt(1,10);
                stielzchen.placeAt(3,9);
                gameObjects.remove(currentGoal);
                gameObjects.remove(cat);
                gameObjects.remove(coin1);
                gameObjects.remove(coin2);
                gameObjects.remove(coin3);
                currentGoal = new Entity(GameObject.GOAL,4,7);
                gameObjects.add(currentGoal);
                wolf = new Entity(GameObject.WOLF,3,3);
                gameObjects.add(wolf);
                break;
            case 3:
                gameObjects.remove(currentGoal);
                gameObjects.remove(wolf);
                currentGoal = new Entity(GameObject.GOAL,0,4);
                gameObjects.add(currentGoal);

                if(currentGoal.posx>playerChar.posx){
                    gameObjects.add(new Entity(GameObject.ARRR,playerChar.posx+1, playerChar.posy));
                }
                else if(currentGoal.posx<playerChar.posx){
                    gameObjects.add(new Entity(GameObject.ARRR,playerChar.posx-1, playerChar.posy));
                }
                else if(currentGoal.posy<playerChar.posy){
                    gameObjects.add(new Entity(GameObject.ARRR,playerChar.posx, playerChar.posy-1));
                }
                else if(currentGoal.posy>playerChar.posy){
                    gameObjects.add(new Entity(GameObject.ARRR,playerChar.posx, playerChar.posy+1));
                }
                arrrsleft = 3;


                break;
        }
    }

    private static void lostGame() {
        //TODO Stub
    }



    public static void placeBarricade(){
        //TODO Stub
    }


    public static boolean getPlayerTurn(String s){
        if(phase!=1) {
            switch (s.charAt(0)) {
                case 'A':
                    playerChar.posx = 0;
                    break;
                case 'B':
                    playerChar.posx = 1;
                    break;
                case 'C':
                    playerChar.posx = 2;
                    break;
                case 'D':
                    playerChar.posx = 3;
                    break;
                case 'E':
                    playerChar.posx = 4;
                    break;
                case 'F':
                    playerChar.posx = 5;
                    break;
                case 'G':
                    playerChar.posx = 6;
                    break;
                case 'H':
                    playerChar.posx = 7;
                    break;
            }
            switch (s.charAt(1)) {
                case '1':
                    playerChar.posy = 0;
                    break;
                case '2':
                    playerChar.posy = 1;
                    break;
                case '3':
                    playerChar.posy = 2;
                    break;
                case '4':
                    playerChar.posy = 3;
                    break;
                case '5':
                    playerChar.posy = 4;
                    break;
                case '6':
                    playerChar.posy = 5;
                    break;
                case '7':
                    playerChar.posy = 6;
                    break;
                case '8':
                    playerChar.posy = 7;
                    break;
            }
        }
        else {
            if(movedOriginalPlayerChar) {
                movedOriginalPlayerChar = false;
                switch (s.charAt(0)) {
                    case 'A':
                        playerChar.posx = 0;
                        break;
                    case 'B':
                        playerChar.posx = 1;
                        break;
                    case 'C':
                        playerChar.posx = 2;
                        break;
                    case 'D':
                        playerChar.posx = 3;
                        break;
                    case 'E':
                        playerChar.posx = 4;
                        break;
                    case 'F':
                        playerChar.posx = 5;
                        break;
                    case 'G':
                        playerChar.posx = 6;
                        break;
                    case 'H':
                        playerChar.posx = 7;
                        break;
                }
                switch (s.charAt(1)) {
                    case '1':
                        playerChar.posy = 0;
                        break;
                    case '2':
                        playerChar.posy = 1;
                        break;
                    case '3':
                        playerChar.posy = 2;
                        break;
                    case '4':
                        playerChar.posy = 3;
                        break;
                    case '5':
                        playerChar.posy = 4;
                        break;
                    case '6':
                        playerChar.posy = 5;
                        break;
                    case '7':
                        playerChar.posy = 6;
                        break;
                    case '8':
                        playerChar.posy = 7;
                        break;
                }
            }
            else{
                movedOriginalPlayerChar = true;
                switch (s.charAt(0)) {
                    case 'A':
                        cat.posx = 0;
                        break;
                    case 'B':
                        cat.posx = 1;
                        break;
                    case 'C':
                        cat.posx = 2;
                        break;
                    case 'D':
                        cat.posx = 3;
                        break;
                    case 'E':
                        cat.posx = 4;
                        break;
                    case 'F':
                        cat.posx = 5;
                        break;
                    case 'G':
                        cat.posx = 6;
                        break;
                    case 'H':
                        cat.posx = 7;
                        break;
                }
                switch (s.charAt(1)) {
                    case '1':
                        cat.posy = 0;
                        break;
                    case '2':
                        cat.posy = 1;
                        break;
                    case '3':
                        cat.posy = 2;
                        break;
                    case '4':
                        cat.posy = 3;
                        break;
                    case '5':
                        cat.posy = 4;
                        break;
                    case '6':
                        cat.posy = 5;
                        break;
                    case '7':
                        cat.posy = 6;
                        break;
                    case '8':
                        cat.posy = 7;
                        break;
                }
            }
        }

        System.out.println(playerChar.posx + " " + playerChar.posy);
        return update();

    }

    private static class Entity {
        GameObject type;
        public int posx;
        public int posy;

        public Entity(GameObject type, int posx, int posy) {
            this.type = type;
            this.posx = posx;
            this.posy = posy;
        }

        public boolean isAdjacent(Entity entity){
            boolean ret = false;
            if(entity.posx == this.posx && (entity.posy == this.posy+1 || entity.posy == this.posy-1))
                ret = true;
            else if(entity.posy == this.posy && (entity.posx == this.posx+1 || entity.posx == this.posx-1))
                ret = true;
            System.out.println(entity.type +" " + entity.posx +" "+ entity.posy + " - "+ this.type +" " + this.posx +" "+ this.posy + " Return: " + ret);
            return ret;
        }
    }
}
