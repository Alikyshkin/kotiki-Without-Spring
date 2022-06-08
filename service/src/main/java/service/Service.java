package service;

import dao.FriendsDao;
import dao.KotikiDao;
import dao.OwnerDao;
import daoImpl.FriendsDaoImpl;
import daoImpl.KotikiDaoImpl;
import daoImpl.OwnerDaoImpl;
import model.Friends;
import model.Kotiki;
import model.Owner;

import java.util.ArrayList;
import java.util.List;

public class Service {
    private final OwnerDao ownersDao = new OwnerDaoImpl();
    private final KotikiDao kotikiDao = new KotikiDaoImpl();
    private final FriendsDao friendsDao = new FriendsDaoImpl();

    public Service() {
    }

    public Friends addFriend(Kotiki friend1, Kotiki friend2) {
        Friends friends = new Friends(friend1.getId(), friend2.getId());
        friendsDao.save(friends);
        return friends;
    }

    public Owner findOwner(int id) {
        return ownersDao.findById(id);
    }

    public Kotiki findKotik(int id) {
        return kotikiDao.findById(id);
    }

    public Friends findFriend(int id) {
        return friendsDao.findById(id);
    }

    public List<Owner> getAllOwners() {
        return ownersDao.findAll();
    }

    public List<Kotiki> getAllKotiki() {
        return kotikiDao.findAll();
    }

    public List<Friends> getAllFriends() {
        return friendsDao.findAll();
    }

    public void saveOwner(Owner owner) {
        ownersDao.save(owner);
    }

    public void saveKotik(Kotiki kotik) {
        kotikiDao.save(kotik);
    }

    public void saveFriend(Friends friend) {
        friendsDao.save(friend);
    }

    public void deleteOwner(Owner owner) {
        ownersDao.delete(owner);
    }

    public void deleteKotik(Kotiki kotik) {
        kotikiDao.delete(kotik);
    }

    public void deleteFriend(Friends friend) {
        friendsDao.delete(friend);
    }

    public void updateOwner(Owner owner) {
        ownersDao.update(owner);
    }

    public void updateKotik(Kotiki kotik) {
        kotikiDao.update(kotik);
    }

    public void updateFriend(Friends friend) {
        friendsDao.update(friend);
    }

    public List<Owner> findAllOwners() {
        return ownersDao.findAll();
    }

    public List<Kotiki> findAllKotiki() {
        return kotikiDao.findAll();
    }

    public List<Friends> findAllFriends() {
        return friendsDao.findAll();
    }

    public Kotiki findKotikById(int id) {
        for (int i = 0; i < kotikiDao.findAll().size(); i++) {
            if (kotikiDao.findAll().get(i).getId() == id) {
                return kotikiDao.findAll().get(i);
            }
        }

        return null;
    }

    public Owner findOwnerById(int id) {
        for (int i = 0; i < ownersDao.findAll().size(); i++) {
            if (ownersDao.findAll().get(i).getId() == id) {
                return ownersDao.findAll().get(i);
            }
        }

        return null;
    }

    public List<Kotiki> getKotikFriends(int id) {
        List<Kotiki> kotikFriends = new ArrayList<>();
        List<Friends> allFriends = friendsDao.findAll();

        for (Friends allFriend : allFriends) {
            if (allFriend.getKotik2() == id) {
                kotikFriends.add(findKotikById(allFriend.getKotik1()));
            }

            if (allFriend.getKotik1() == id) {
                kotikFriends.add(findKotikById(allFriend.getKotik2()));
            }
        }

        return kotikFriends;
    }

    public List<Kotiki> getOwnerKotiki(int id) {
        Owner owner = findOwnerById(id);
        List<Kotiki> kotiki = kotikiDao.findAll();
        List<Kotiki> ownerKotiki = new ArrayList<>();

        for (Kotiki value : kotiki) {
            if (value.getOwnerId() == owner.getId()) {
                ownerKotiki.add(value);
            }
        }

        return ownerKotiki;
    }
}
