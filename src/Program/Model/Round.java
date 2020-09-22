package Program.Model;

import javafx.scene.paint.Color;

class Round {

    private Player currentPlayer;
    private Phase currentPhase = Phase.DEPLOY;
    private Space mySpace;
    private Space oppSpace;
    /*Deployment deployment = new Deployment();
    Attack attack = new Attack();
    Program.View.View.Model.Movement movement = new Program.View.View.Model.Movement(this);*/


    public enum Phase {

        DEPLOY, ATTACK, MOVE, END;

        private Phase next;

        static {
            DEPLOY.next = ATTACK;
            ATTACK.next = MOVE;
            MOVE.next = END;
        }

        public Phase getNextPhase() {
            return next;
        }

    }

    public Round(Player currentPlayer){

        this.currentPlayer = currentPlayer;
    }

    //Ge en random spelare första turen

    public void nextPhase(){
        //Byter till nästa fas
        if(currentPhase == Phase.MOVE)
        {
            currentPhase = Phase.DEPLOY;
            return;

        }
        currentPhase = currentPhase.next;
    }
    public void startPhase(Space selectedSpace, Space selectedSpace2)
    {
        if(selectedSpace != null && selectedSpace2 != null)
        {
            if(mySpace == null){
                //Signalera fel
                mySpace = selectedSpace;

            }
            if (oppSpace == null && selectedSpace != mySpace) {
            //Signalera fel
            oppSpace = selectedSpace2;

            }
            if(currentPhase == Phase.DEPLOY)
            {
                if(!Deployment.startDeployment(mySpace, currentPlayer)){
                    resetSpaces();
                }

            }
            else {
                if(oppSpace != null)
                switch (currentPhase)
                {
                    case ATTACK:
                        if( mySpace != oppSpace && Attack.DeclareAttack(mySpace, oppSpace, mySpace.getUnits()) ) {

                            if(1 == Attack.calculateAttack(mySpace, oppSpace)){
                            /*mapController.updateColor();
                            mapController.updateText();*/
                            }
                        /*
                        mapController.updateColor();
                        mapController.updateText();
                        mapController.resetSelectedSpace();
                        */
                            resetSpaces();
                        }
                        else {
                            resetSpaces();
                            //mapController.resetSelectedSpace();
                        }
                        break;
                    case MOVE:
                        Movement.MoveUnits(mySpace,oppSpace);
                        resetSpaces();
                        /*mapController.updateText();
                        mapController.resetSelectedSpace();*/
                        break;
                }
            }
        }
    }
    private void resetSpaces() {
        mySpace = null;
        oppSpace = null;
    }
    public Color getCurrentPlayerColor() {
        return currentPlayer.getColor();
    }

    Player getCurrentPlayer(){
        return currentPlayer;
    }

    public Phase getCurrentPhase(){
        return currentPhase;
    }




}
