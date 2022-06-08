import model.*;
import org.junit.Before;
import org.junit.Test;
import service.Service;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;

public class KotikiTest {
    private Service service;

    @Before
    public void setUp() {
        service = new Service();
    }

    @Test
    public void AddOwner() {
        Owner owner = new Owner("Masha", new Date(25 - 04 - 2003));

        service.saveOwner(owner);
        assertEquals(1, service.getAllOwners().size());

        service.deleteOwner(owner);
    }

    @Test
    public void AddKotiki() {
        Kotiki kotik = new Kotiki("Kot", new Date(11 - 11 - 2011), Breed.MAINECOON, Color.BLACK);

        service.saveKotik(kotik);
        assertEquals(1, service.getAllKotiki().size());

        service.deleteKotik(kotik);
    }

    @Test
    public void AddFriends() {
        Kotiki kotik = new Kotiki("Kot", new Date(11 - 11 - 2011), Breed.MAINECOON, Color.BLACK);
        Kotiki kotik2 = new Kotiki("Kot2", new Date(11 - 11 - 2011), Breed.LOPEARED, Color.WHITE);

        service.saveKotik(kotik);
        service.saveKotik(kotik2);

        Friends friend = service.addFriend(kotik, kotik2);

        service.updateKotik(kotik);
        service.updateKotik(kotik2);

        List<Kotiki> kotikFriends = service.getKotikFriends(kotik.getId());

        assertEquals(1, service.getAllFriends().size());

        service.deleteKotik(kotik);
        service.deleteKotik(kotik2);
        service.deleteFriend(friend);
    }

    @Test
    public void AddFriendsToKotikAndDelete() {
        Owner owner = new Owner("Masha", new Date(25 - 04 - 2003));
        Kotiki kotik = new Kotiki("Kot", new Date(11 - 11 - 2011), Breed.MAINECOON, Color.BLACK);
        Kotiki kotik2 = new Kotiki("Kot2", new Date(11 - 11 - 2011), Breed.LOPEARED, Color.WHITE);

        service.saveOwner(owner);
        service.saveKotik(kotik);
        service.saveKotik(kotik2);

        owner.addKotik(kotik);
        owner.addKotik(kotik2);

        service.updateOwner(owner);

        Friends friend = service.addFriend(kotik, kotik2);

        service.updateKotik(kotik);
        service.updateKotik(kotik2);

        assertEquals(2, service.getAllKotiki().size());
        assertEquals(1, service.getAllOwners().size());
        assertEquals(1, service.getAllFriends().size());

        owner.setOwnerName("Sasha");

        assertEquals("Sasha", owner.getOwnerName());

        service.updateOwner(owner);

        service.deleteKotik(kotik);
        service.deleteKotik(kotik2);
        service.deleteFriend(friend);
        service.deleteOwner(owner);

        assertEquals(0, service.getAllKotiki().size());
        assertEquals(0, service.getAllOwners().size());
        assertEquals(0, service.getAllFriends().size());
    }
}