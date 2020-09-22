import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class View {

    public void setUpMap(){


    }

    public void setUpStart(){



    }

    public void updateColor()
    {
        cube1.setFill(space1.getPlayer().getColor());
        cube2.setFill(space2.getPlayer().getColor());
        cube3.setFill(space3.getPlayer().getColor());
        cube4.setFill(space4.getPlayer().getColor());
        Color yes1 = space1.getPlayer().getColor();
        Color yes2 = space2.getPlayer().getColor();
        Color yes3 = space3.getPlayer().getColor();
        Color yes4 = space4.getPlayer().getColor();

        if(yes1 == yes2 && yes1 == yes3 && yes1 == yes4)
        {
            System.out.println("You Win!");
        }
    }

    public void update()
    {
        round = new Round(player1, this);
        playerText.setText(round.currentPlayer.getId() + "");
        phaseText.setText(round.currentPhase.name());
        updateText();

    }

    public void updateText()
    {
        text1.setText(space1.getUnits() + "");
        text2.setText(space2.getUnits() + "");
        text3.setText(space3.getUnits() + "");
        text4.setText(space4.getUnits() + "");

        text1.setFill(Color.WHITE);
        text2.setFill(Color.WHITE);
        text3.setFill(Color.WHITE);
        text4.setFill(Color.WHITE);

        playerText.setText("Currentplayer: " + round.currentPlayer.getId() + "");
        phaseText.setText(round.currentPhase.name());
    }

    public void resetColor(Rectangle cube, Space selectedSpace)
    {
        cube1.setFill(findColor(space1.getPlayer()));
        cube2.setFill(findColor(space2.getPlayer()));
        cube3.setFill(findColor(space3.getPlayer()));
        cube4.setFill(findColor(space4.getPlayer()));
        if(cube == cube1 || selectedSpace == space1)
        {
            cube1.setFill(findHighLightColor(space1.getPlayer()));
        }
        if(cube == cube2 || selectedSpace == space2)
        {
            cube2.setFill(findHighLightColor(space2.getPlayer()));
        }
        if(cube == cube3 ||  selectedSpace == space3)
        {
            cube3.setFill(findHighLightColor(space3.getPlayer()));
        }
        if(cube == cube4 || selectedSpace == space4)
        {
            cube4.setFill(findHighLightColor(space4.getPlayer()));
        }
    }

    //Initialize i mapController, gör till en setUpMap()
    /*
    space1 = new Space(1, player1, 3, "1",cube1);
        cube1.setFill(player1.getColor());
    space2 = new Space(2, player1, 5, "2",cube2);
        cube2.setFill(player1.getColor());
    space3 = new Space(3, player2, 6, "3",cube3);
        cube3.setFill(player2.getColor());
    space4 = new Space(4, player2, 1, "4",cube4);
        cube4.setFill(player2.getColor());
    Text text = new Text();
        text.setText("1");
        text.setFill(Color.GREEN);
        text.setX(cube1.getX() + (cube1.getWidth() / 2));
        text.setY(cube1.getY() + (cube1.getHeight() / 2));
        rootpane.getChildren().add(text);

     */
}
