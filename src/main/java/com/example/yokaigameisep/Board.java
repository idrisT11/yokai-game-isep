package com.example.yokaigameisep;

public class Board {

    // Attributes used .......................................................
    private CaseBoard[][] mainGrid;
    private char[][] copymainGrid;
    private boolean canMoveCard;
    private boolean canMoveTip;
    private boolean stateEndGame;
    private boolean canTurnCard;

    // Constructor ....................................................................
    public CaseBoard[][] Initialize_Board() {

        mainGrid = new CaseBoard[Constant.LENGTH_MAIN_GRID][Constant.LENGTH_MAIN_GRID];
        for(int i=0; i<Constant.LENGTH_MAIN_GRID;i++){
            for(int j = 0; j<Constant.LENGTH_MAIN_GRID; j++){
                mainGrid[i][j] = new CaseBoard(Constant.VOID_CASE,null);
                mainGrid[i][j].setCardOnTop(null);

            }
        }

        int min_init_layout = (Constant.LENGTH_MAIN_GRID/2)-2;
        int max_init_layout = (Constant.LENGTH_MAIN_GRID/2)+2;
        int Red_Left=4,Green_Left =4, Blue_Left=4, Purple_Left = 4;

        for(int i = min_init_layout; i<max_init_layout; i++){

            for(int j = min_init_layout; j<max_init_layout; j++){
                int randnumb0=0;

                do {randnumb0 = (int) (Math.random() * 4) + 1;
                }while(
                        (randnumb0 == Constant.COLOR_RED && Red_Left==0)||
                                (randnumb0==Constant.COLOR_GREEN && Green_Left==0) ||
                                (randnumb0 == Constant.COLOR_BLUE && Blue_Left==0) ||
                                (randnumb0 == Constant.COLOR_PURPLE && Purple_Left==0) );

                switch(randnumb0) {
                    case Constant.COLOR_RED:
                        mainGrid[i][j].setColor(Constant.COLOR_RED); //set case content to Red
                        Red_Left -= 1;
                        break;

                    case Constant.COLOR_GREEN:
                        mainGrid[i][j].setColor(Constant.COLOR_GREEN); //set case value
                        Green_Left -= 1;
                        break;

                    case Constant.COLOR_PURPLE:
                        mainGrid[i][j].setColor(Constant.COLOR_PURPLE); //set case value
                        Purple_Left -= 1;
                        break;

                    case Constant.COLOR_BLUE:
                        mainGrid[i][j].setColor(Constant.COLOR_BLUE); //set case value
                        Blue_Left -= 1;
                        break;
                    default:
                        // code block
                }

            }
        }

        return mainGrid;
    }




    // Getter and Setter    ....................................................................
    public void getCopymainGrid(CaseBoard[][] mainGrid) {
        copymainGrid = new char[Constant.LENGTH_MAIN_GRID][Constant.LENGTH_MAIN_GRID];
        for (int i = 0; i <Constant.LENGTH_MAIN_GRID; i++) {
            for (int j = 0; j < Constant.LENGTH_MAIN_GRID; j++) {
                if(mainGrid[i][j].getColor() == Constant.VOID_CASE) {
                    copymainGrid[i][j]='0';
                } else {
                    copymainGrid[i][j]='A';
                }
            }
        }
    }


    public CaseBoard[][] getMainGrid() {
        return mainGrid;
    }

    public void setMainGrid(CaseBoard[][] mainGrid) {
        this.mainGrid = mainGrid;
    }

    // Methods    .............................................................................
    public boolean isPlayerMoveGood(int x, int y){
        getCopymainGrid(mainGrid);
        if(mainGrid[x][y].getCardOnTop()== null){
            copymainGrid[x][y]='0';
            if(isBlockOnepiece(copymainGrid)){
                canMoveCard = true;
            } else {
                canMoveCard = false;
            }
        } else {
            canMoveCard = false;
        }
        return canMoveCard;
    }
    
    public boolean isTipMoveGood(){
        return canMoveTip;
    }

    public boolean verifyEndGame(){
        return stateEndGame;
    }

    public boolean isCardTurnable(){
        return canTurnCard;
    }

    public void makeMove(){

    }


    public boolean isBlockOnepiece(char[][] copymainGrid){
        int[] memory={0,0};
        boolean shouldBreak = false;
        int i,j;
        boolean inOnePiece = true;

        for (i = 0; i <Constant.LENGTH_MAIN_GRID-1; i++) {    // get first element with a card on it
            for (j = 0; j < Constant.LENGTH_MAIN_GRID-1; j++) {
                if(copymainGrid[i][j]=='A'){    // has a card
                    copymainGrid[i][j]='B';
                    memory[0]=i;
                    memory[1]=j;
                    shouldBreak = true;
                    break;
                }
            }
            if(shouldBreak){
                break;
            }
        }
        i=memory[0];
        j=memory[1];

        while (copymainGrid[i][j]=='B' && j<11) {           // si un élément a une carte
            if(copymainGrid[i+1][j]=='A'){          // checker l'élément de dessous
                copymainGrid[i+1][j]='B';
                int k = j;
                while(copymainGrid[i+1][k+1]=='A'){  // for the first line after first element check elements on the right
                    copymainGrid[i+1][k+1]='B';
                    k +=1;
                }
                k=j;
                while(copymainGrid[i+1][k-1]=='A'){   // for line k check elements on the left
                    copymainGrid[i+1][k-1]='B';
                    k -=1;
                }
            }
            if(copymainGrid[i][j+1]=='A'){          // checker l'élément à droite de l'élément de base
                copymainGrid[i][j+1] = 'B';
            }
            j+=1;
        }


        for (i = memory[0]+1; i <Constant.LENGTH_MAIN_GRID-1; i++) {
            for (j = 0; j < Constant.LENGTH_MAIN_GRID-1; j++) {
                if(copymainGrid[i][j]=='B'){
                    if(copymainGrid[i+1][j]=='A'){          // checker l'élément de dessous
                        copymainGrid[i+1][j]='B';
                        int k = j;
                        while(copymainGrid[i+1][k+1]=='A'){  // for the first line after first element check elements on the right
                            copymainGrid[i+1][k+1]='B';
                            k +=1;
                        }
                        k=j;
                        while(copymainGrid[i+1][k-1]=='A'){   // for line k check elements on the left
                            copymainGrid[i+1][k-1]='B';
                            k -=1;
                        }
                    }
                    if(copymainGrid[i][j+1]=='A'){          // checker l'élément à droite de l'élément de base
                        copymainGrid[i][j+1] = 'B';
                    }
                }
            }
        }


        for (i = Constant.LENGTH_MAIN_GRID-1; i > 0; i--) {
            for (j = 0; j < Constant.LENGTH_MAIN_GRID - 1; j++) {
                if(copymainGrid[i][j]=='B'){

                    if(copymainGrid[i-1][j]=='A'){          // checker l'élément de dessus
                        copymainGrid[i-1][j]='B';
                        int k = j;
                        while(copymainGrid[i-1][k+1]=='A'){  // for the first line after first element check elements on the right
                            copymainGrid[i-1][k+1]='B';
                            k +=1;
                        }
                        k=j;
                        while(copymainGrid[i-1][k-1]=='A'){   // for line k check elements on the left
                            copymainGrid[i-1][k-1]='B';
                            k -=1;
                        }
                    }
                }
            }
        }

        for (i = 0; i <Constant.LENGTH_MAIN_GRID-1; i++) {    // get first element with a card on it
            for (j = 0; j < Constant.LENGTH_MAIN_GRID - 1; j++) {
                if(copymainGrid[i][j]=='A'){
                    inOnePiece = false;
                }
            }
        }

        return inOnePiece;
    }




    public CaseBoard getCase(int x, int y){
        if(x < 0 || y < 0 || x >= Constant.LENGTH_MAIN_GRID || y >= Constant.LENGTH_MAIN_GRID)
            return null;
        return mainGrid[x][y];
    }

    public void printBoard(){
        for (int i = 0; i <Constant.LENGTH_MAIN_GRID; i++) {
            for (int j = 0; j < Constant.LENGTH_MAIN_GRID; j++) {
                switch(mainGrid[i][j].getColor()) {
                    case Constant.COLOR_RED:
                        System.out.print("1");
                        break;

                    case Constant.COLOR_GREEN:
                        System.out.print("2");
                        break;

                    case Constant.COLOR_PURPLE:
                        System.out.print("3");
                        break;

                    case Constant.COLOR_BLUE:
                        System.out.print("4");
                        break;
                    default:
                        System.out.print("*");
                }
            }
            System.out.println("");
        }
    }


    public static boolean IsIndiceMoveGood(Board b, int x, int y) {


        CaseBoard caseConcernée = b.getCase(x, y);
        int colorCaseConcernee = caseConcernée.getColor();
        if (colorCaseConcernee != 0) {
            return false;
        }
        CaseBoard caseDroite = b.getCase(x+1,y);
        if (caseDroite != null){
            int colorCaseDroite = caseDroite.getColor();
            if (colorCaseDroite != 0) {
                return true;
            }
        }

        CaseBoard caseGauche = b.getCase(x-1,y);
        if (caseGauche != null){
            int colorCaseGauche = caseGauche.getColor();
            if (colorCaseGauche != 0) {
                return true;
            }
        }
        boolean stateCaseHaut;
        CaseBoard caseHaut = b.getCase(x,y+1);
        if (caseHaut != null){
            int colorCaseHaut = caseHaut.getColor();
            if (colorCaseHaut != 0) {
                return true;
            }
        }
        CaseBoard caseBas = b.getCase(x,y-1);
        if (caseBas != null){
            int colorCaseBas = caseBas.getColor();
            if (colorCaseBas != 0) {
                return true;
            }
        }
        return false;
    }
}
