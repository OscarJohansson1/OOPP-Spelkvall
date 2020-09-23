package Program.Model;

import javafx.scene.paint.Color;

class Round {

    private Phase currentPhase = Phase.DEPLOY;

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

    public void nextPhase(){
        //Byter till nästa fas
        if(currentPhase == Phase.MOVE)
        {
            currentPhase = Phase.DEPLOY;
            return;

        }
        currentPhase = currentPhase.next;
    }
    public boolean startPhase(Space selectedSpace, Space selectedSpace2, Player currentPlayer)
    {
        if(selectedSpace != null)
        {
            if(currentPhase == Phase.DEPLOY)
            {
                return Deployment.startDeployment(selectedSpace, currentPlayer);
            }
            else if(selectedSpace2 != null){
                switch (currentPhase)
                {
                    case ATTACK:
                        if( selectedSpace != selectedSpace2 && Attack.DeclareAttack(selectedSpace, selectedSpace2, selectedSpace.getUnits()) ) {

                            if(1 == Attack.calculateAttack(selectedSpace, selectedSpace2)){
                            /*mapController.updateColor();
                            mapController.updateText();*/
                            }
                        /*
                        mapController.updateColor();
                        mapController.updateText();
                        mapController.resetSelectedSpace();
                        */

                        }
                        else {

                        //mapController.resetSelectedSpace();
                        }
                        return true;
                    case MOVE:
                        Movement.MoveUnits(selectedSpace,selectedSpace2);

                        /*mapController.updateText();
                        mapController.resetSelectedSpace();*/
                        return true;
                    default:
                        return false;

                }
            }
        }
        return false;
    }
    public Phase getCurrentPhase(){
        return currentPhase;
    }
}
