import main.Player;
import main.PlayerAgent;
import main.Request;
import main.TeamManager;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;

public class ClassesTest extends ApplicationTest {
    private TeamManager tm;
    private PlayerAgent pa;
    private Player p;
    private Request r;

    @Before
    public void setup()
    {
        tm = new TeamManager("tm_name");
        pa = new PlayerAgent("pa_name");
        p = new Player("p_name", "pos", "desc");
        r = new Request("tm_name", "pa_name", "p_name");

    }
    @Test
    public void PAAddPlayerTest()
    {

        pa.addPlayer(p);
        assertEquals(1, pa.getPlayers().size());
        assertEquals(p.getName(), pa.getPlayers().get(0).getName());
        assertEquals(p.getPlaying_position(), pa.getPlayers().get(0).getPlaying_position());
        assertEquals(p.getDescription(), pa.getPlayers().get(0).getDescription());
    }

    @Test

    public void PASolveRequestTest()
    {

    }

}
