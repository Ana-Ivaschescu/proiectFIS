import main.*;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.util.HashMap;

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
        tm.setTeam(new Team("team_name", "team_city", "team_league", "team_desc"));
        pa = new PlayerAgent("pa_name");
        p = new Player("p_name", "guard", "desc");
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
    public void TMSendRequestTest()
    {
        tm.addRequest(r);
        assertEquals(1, tm.getRequest_list().size());
    }
    @Test
    public void PASolveRequestTest()
    {
        pa.getRequest_list().add(r);
        assertEquals(1, pa.getRequest_list().size());
        pa.solveRequest(r, "accept");
        assertEquals("accept", r.getStatus());

    }
    @Test
    public void TestTeam()
    {
        tm.getTeam().addPlayer(p);
        assertEquals(1, tm.getTeam().getPlayers().size());
        tm.getTeam().addPlayer(new Player("p_name_2", "center", "desc_2"));
        tm.getTeam().addPlayer(new Player("p_name_3", "wing", "desc_3"));
        HashMap<String, Integer> player_count = tm.getTeam().playerCount();
        assertEquals(new Integer(3), player_count.get("total"));
        assertEquals(new Integer(1), player_count.get("centers"));
        assertEquals(new Integer(1), player_count.get("guards"));
        assertEquals(new Integer(1), player_count.get("wings"));
    }

}
