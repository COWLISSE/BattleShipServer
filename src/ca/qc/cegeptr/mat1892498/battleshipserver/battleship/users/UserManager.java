package ca.qc.cegeptr.mat1892498.battleshipserver.battleship.users;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class UserManager {
    public static ConcurrentMap<String, User> USERS = new ConcurrentHashMap<>();
}
