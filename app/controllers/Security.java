package controllers;

import models.Administrator;
import play.libs.Crypto;

/**
 * Created by aazzou on 10/22/14.
 */
public class Security extends Secure.Security {
    static boolean authenticate(String username, String password) {
        Administrator admin = Administrator.find("byUsername",username).first();
        if(admin != null &&
                admin.password.
                        equals(Crypto.passwordHash(password, Crypto.HashType.SHA256))){
            session.put("username",username);
            return true;
        }else return false;
    }

}
