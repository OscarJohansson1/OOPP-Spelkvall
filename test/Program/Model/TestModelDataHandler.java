package Program.Model;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestModelDataHandler {

    ModelDataHandler mdh = new ModelDataHandler();

    @Test
    public void testRound() {

        assertNull(mdh.getSelectedSpace(), "selectedSpace is not null at start");

    }
}
