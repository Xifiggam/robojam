package eu.robo.veit.robojam;

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
    public static ArrayList<Entity> gameObjects;
    public static int coinCount = 0;
    public static boolean movedOriginalPlayerChar = false;
    public static URDriver urDriver;
    private static int arrrsleft = 0;

    public static void initGame(){

        playerChar = new Entity(GameObject.PLAYERCHAR,7,7);
        currentGoal = new Entity(GameObject.GOAL,1,0);
        gameObjects = new ArrayList<>();
        gameObjects.add(playerChar);
        gameObjects.add(currentGoal);
        gameObjects.add(new Entity(GameObject.BARRICADE,3,2));
        gameObjects.add(new Entity(GameObject.BARRICADE,6,4));
    }

//    public GameObject[][] field = new GameObject[8][8];

//    public void makeMove(GameField src, GameField dst){
//        GameObject tempObject  = field[src.posx][src.posy];
//        field[src.posx][src.posy] = GameObject.EMPTY;
//        field[dst.posx][dst.posy] = tempObject;
//    }

    public static void update(){
        turns++;
        for (Entity entity : gameObjects) {
            if(entity.type == GameObject.BARRICADE){
                if(entity.isAdjacent(playerChar))
                    lostGame();
            }
            if(entity.type == GameObject.GOAL){
                if(entity.isAdjacent(playerChar))
                    if(phase!=1) {
                        goalAchieved();
                    }
                    else {
                        if(entity.isAdjacent(cat) && coinCount >=3)
                            goalAchieved();
                    }

            }
            if(entity.type == GameObject.COIN){
                if(entity.isAdjacent(cat))
                    coinCount++;
                    gameObjects.remove(entity);
                    switch (coinCount){
                        case 1: gameObjects.add(new Entity(GameObject.COIN,2,6)); break;
                        case 2: gameObjects.add(new Entity(GameObject.COIN,4,7)); break;
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
    }

    private static void goalAchieved() {

        //TODO Stub Start Minigame Phase here
        //Right intent here
        //remove goal
    }

    public static void initNextPhase(){
        phase++; //waah
        switch (phase){
            case 0:
                break;
            case 1:
                gameObjects.remove(currentGoal);
                currentGoal = new Entity(GameObject.GOAL,7,3);
                gameObjects.add(currentGoal);
                gameObjects.add(new Entity(GameObject.COIN,3,3));
                gameObjects.add(new Entity(GameObject.CAT,1,0));
                break;
            case 2:
                gameObjects.remove(currentGoal);
                gameObjects.remove(cat);
                currentGoal = new Entity(GameObject.GOAL,4,7);
                wolf = new Entity(GameObject.WOLF,3,3);
                gameObjects.add(wolf);
                break;
            case 3:
                gameObjects.remove(currentGoal);
                gameObjects.remove(wolf);
                currentGoal = new Entity(GameObject.GOAL,0,4);

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

    private static void getBoardState() {
        //TODO Stub
    }


    public static void placeBarricade(){
        //TODO Stub
    }

//    private class GameField {
//        public int posx;
//        public int posy;
//
//        public GameField(int posx, int posy) {
//            this.posx = posx;
//            this.posy = posy;
//        }
//    }

    public static void getPlayerTurn(String s){
        new URDriver().execute(Integer.parseInt(s.charAt(1)+""));
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
        update();
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
            if(entity.posx == this.posx && (entity.posy == this.posy+1 || entity.posy == this.posy-1))
                return true;
            else if(entity.posy == this.posy && (entity.posx == this.posx+1 || entity.posx == this.posx-1))
                return true;
            return false;
        }
    }
}
