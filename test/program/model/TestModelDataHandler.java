package program.model;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestModelDataHandler {

    ModelDataHandler mdh = new ModelDataHandler(25);

    @Test
    public void testRound() {

        assertNull(mdh.getSelectedSpace(), "selectedSpace is not null at start");

    }
}
