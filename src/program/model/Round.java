package program.model;

/**
 * This class is controlling the different phases for one round.
 * StartPhase() is always called when Deploy, Attack, or Movement is called in ModelDataHandler
 */

class Round {

    private Phase currentPhase = Phase.DEPLOY;

    /**
     * This is an enum with all the different phases in one round.
     * It also makes it possible to know what the next phase is
     */

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
    boolean startPhase(Space selectedSpace, Space selectedSpace2, Player currentPlayer, int units)
    {
        if(selectedSpace != null)
        {
            if(currentPhase == Phase.DEPLOY)
            {
                return Deployment.startDeployment(selectedSpace, currentPlayer,units);
            }
            else if(selectedSpace2 != null){
                switch (currentPhase)
                {
                    case ATTACK:
                        if(Attack.DeclareAttack(selectedSpace, selectedSpace2, selectedSpace.getUnits()) ) {
                            return Attack.calculateAttack(selectedSpace, selectedSpace2);
                        }
                        return false;
                    case MOVE:
                        return Movement.MoveUnits(selectedSpace,selectedSpace2, units);
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