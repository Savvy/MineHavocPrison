package us.timberdnd.prisonheroes.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.Bukkit;

import code.husky.mysql.MySQL;
import us.timberdnd.prisonheroes.PrisonHeroes;

public class SQLManager {

    static String host;
    static String port;
    static String database;
    static String user;
    static String password;

    public SQLManager() {
    }

    public enum RESULT {
	SUCCESS, TRUE, FALSE, ERROR;
    }

    public static MySQL MYSQL = new MySQL(PrisonHeroes.plugin, host, port, database, user, password);
    public static Connection c = null;

    public void createTable() {
	try {
	    c = MYSQL.openConnection();
	} catch (ClassNotFoundException | SQLException e1) {
	    e1.printStackTrace();
	}
	try {
	    PreparedStatement ps = (PreparedStatement) c.prepareStatement(
		    "CREATE TABLE IF NOT EXISTS Server (UUID varchar(36) NOT NULL, name varchar(32) NOT NULL, coins int(6) NOT NULL)");
	    ps.executeUpdate();
	} catch (SQLException e) {
	    System.out.println("[ERROR] Could not check if table exists, stopping server.");
	    e.printStackTrace();
	    Bukkit.getServer().shutdown();
	}
    }

    public static void checkConnection() {
	try {
	    if(!MYSQL.checkConnection()) {
		c = MYSQL.openConnection();
	    }
	}  catch(ClassNotFoundException e) {
	    e.printStackTrace();
	}catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    public RESULT updatePlayerName(UUID p, String newName) {
	checkConnection();
	try {
	    PreparedStatement ps = (PreparedStatement) c.prepareStatement("UPDATE `Server` SET name=?, WHERE UUIDD = ?");
	    ps.setString(1,  newName);
	    ps.setString(2, p.toString());
	    ps.executeUpdate();
	    return RESULT.SUCCESS;
	} catch(SQLException e) {
	    e.printStackTrace();
	    return RESULT.ERROR;
	}
    }

    public String getName(UUID uuid) throws SQLException {
	checkConnection();
	PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT name FROM Server WHERE UUID = ?");
	ps.setString(1, uuid.toString());
	ResultSet rs = ps.executeQuery();
	if (rs.next()) {
	    return rs.getString("name");
	} else {
	    return null;
	}
    }

    public UUID getUUID(String name) throws SQLException {
	checkConnection();
	PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT UUID FROM Server WHERE name = ?");
	ps.setString(1, name);
	ResultSet rs = ps.executeQuery();
	if(rs.next()) {
	    return UUID.fromString(rs.getString("UUID"));
	} else {
	    return null;
	}
    }

    public RESULT checkExists(UUID uuid) {
	checkConnection();
	try {
	    PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT UUID FROM Server WHERE UUID = ?");
	    ps.setString(1, uuid.toString());
	    ResultSet rs = ps.executeQuery();
	    if(rs.next()) {
		return RESULT.TRUE;
	    }else {
		return RESULT.FALSE;
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	    return RESULT.ERROR;
	}
    }

    public RESULT addPlayer(UUID uuid, String name) {
	checkConnection();
	if(checkExists(uuid) == RESULT.FALSE) {
	    PreparedStatement ps;
	    try {
		ps = (PreparedStatement) c.prepareStatement("INSERT INTO `Server` VALUES(?,?,?)");
		ps.setString(1, uuid.toString());
		ps.setString(2, name);
		ps.setInt(3, 0);
		ps.executeUpdate();
		return RESULT.SUCCESS;
	    } catch (SQLException e) {
		e.printStackTrace();
		return RESULT.ERROR;
	    }
	}else {
	    return RESULT.ERROR;
	}
    }
    public static RESULT setObject(UUID uuid, String object, Object object1) {
	checkConnection();
	PreparedStatement ps;
	try {
	    ps = (PreparedStatement) c.prepareStatement("UPDATE `Server` SET ? = ? WHERE UUID = ?");
	    ps.setString(1, object);
	    ps.setObject(2, object1);
	    ps.setString(3, uuid.toString());
	    ps.executeUpdate();
	    return RESULT.SUCCESS;
	} catch (SQLException e) {
	    e.printStackTrace();
	    return RESULT.ERROR;
	}
    }

    public static Object getStatement(UUID uuid, String object) throws SQLException {
	checkConnection();
	PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT " + object + " FROM Server WHERE UUID = ?");
	ps.setString(1, uuid.toString());
	ResultSet rs = ps.executeQuery();
	if(rs.next()) {
	    return rs.getObject(object);
	}else{
	    return null;
	}
    }
} 