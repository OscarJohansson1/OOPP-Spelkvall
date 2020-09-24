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

    /**
     * Method that switch to the next phase. If the current phase is Move, switch to Deploy instead.
     */
    void nextPhase(){
        //Byter till nästa fas
        if(currentPhase == Phase.MOVE)
        {
            currentPhase = Phase.DEPLOY;
            return;

        }
        currentPhase = currentPhase.next;
    }

    /**
     * Method that calls start-methods of Deployment, Attack and Move, based on current phase, selected spaces and
     * the current player.
     * @param selectedSpace The space the action starts from. Eg. the space to deploy units on, attack from and move from
     * @param selectedSpace2 The space the action impacts. Eg. the space that is attacked or moved to.
     * @param currentPlayer The active player during the turn.
     * @return True if phase can start as expected, else false.
     */
    boolean startPhase(Space selectedSpace, Space selectedSpace2, Player currentPlayer)
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
                        if(Attack.DeclareAttack(selectedSpace, selectedSpace2, selectedSpace.getUnits()) ) {

                            if(1 == Attack.calculateAttack(selectedSpace, selectedSpace2)){
                                return true;
                            }
                        }
                        return false;
                    case MOVE:
                        Movement.MoveUnits(selectedSpace,selectedSpace2);
                        return true;
                    default:
                        return false;
                }
            }
        }
        return false;
    }
    Phase getCurrentPhase(){
        return currentPhase;
    }
}
