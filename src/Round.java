import javafx.scene.paint.Color;

public class Round {

    //TODO:  Create rounds and phases

    Player currentPlayer;
    Phase currentPhase;

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

    public void startRound(Player currentPlayer){

        //Deploymentfasen, kastar in i deployments loop
        currentPhase = currentPhase.next;
        //Deployment.startDeploying();

        //Attackfasen, kastar in i attackloopen
        currentPhase = currentPhase.next;
        //Attack.startAttacking();

        //Movementfasen, kastar in i movementloopen, inväntar rörelseorder
        currentPhase = currentPhase.next;
        //Movement.startMoving();

    }

    //Chans skriver startRound(player); och så ska Round sköta resten.
    //START FÖR ROUND HÄR



    //TODO: Se till att användaren börjar med DEPLOY och slutar med END efter att den har gått igenom alla stages.
    /*
    Round kallas på när man ska manipulera spelflödet och dess faser.
    I Chans finns gameloopen. Chans sätter igång en Round som i sin tur togglar igenom alla faser tills Round är slut.
    Round får spelare från Chans när den skapar en Round.
    Switcha spelare efter

    I Chans:

        /*

    Första spelaren väljs randomly

    //Gameloop

        //Byt till nästa spelare
        //Round startar, Round togglar genom alla faser
            //Startar Deploy
            //Startar Attack
            //Startar Move
        //Round är färdig.

        //Är spelet avslutat? Win conditions met?

    */

    public Color getCurrentPlayerColor() {
        return currentPlayer.getColor();
    }



}
