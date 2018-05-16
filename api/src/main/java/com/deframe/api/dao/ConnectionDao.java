package com.deframe.api.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.deframe.api.accounts.Account;
import com.deframe.api.gallery.FeaturedImages;
import com.deframe.api.gallery.Gallery;
import com.deframe.api.gallery.MuseumMap;
import com.deframe.api.museums.Museum;
import com.deframe.api.user.*;
import com.deframe.api.utils.PasswordHashing;
import com.deframe.api.utils.ProjectConstants;
import com.deframe.api.utils.mail.EmailUtility;

public class ConnectionDao {

	private static ConnectionDao connectionDao;

	private ConnectionDao() {

	}

	public static ConnectionDao getConnectionDao() {
		if (connectionDao == null)
			connectionDao = new ConnectionDao();
		return connectionDao;
	}

	public static Connection getConnection() {
		Connection connection = null;
		// STEP 2: Register JDBC driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// STEP 3: Open a connection
		try {
			connection = DriverManager.getConnection(DbConstants.DB_URL, DbConstants.USER, DbConstants.PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
		return connection;
	}

	public static void main(String[] args) {
		getConnection();
		System.out.println();
	}

	/**
	 * Signup Service
	 * 
	 * @param conn
	 * @param useraccount
	 * @return
	 */
	public static List<User> getUsers(Connection conn) {

		String query = "SELECT * from User";
		ResultSet rs = null;

		ArrayList list = new ArrayList<User>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();
			while (rs != null && rs.next()) {
				User user = new User();

				user.setId(rs.getInt(1));
				user.setFirstName(rs.getString(2));
				user.setLastName(rs.getString(3));
				user.setEmailAddress(rs.getString(4));
				user.setPassword(rs.getString(5));
				user.setProfilePicture(rs.getString("Profile_Picture"));
				user.setRole(rs.getString("Role"));
				list.add(user);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Signup Service
	 * 
	 * @param conn
	 * @param useraccount
	 * @return
	 */
	public static User getUserById(Connection conn, int id) {

		String query = "SELECT * from User where id=?";
		ResultSet rs = null;
		User user = new User();

		try {
			PreparedStatement pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();
			if (rs != null && rs.next()) {

				user.setId(rs.getInt(1));
				user.setFirstName(rs.getString(2));
				user.setLastName(rs.getString(3));
				user.setEmailAddress(rs.getString(4));
				user.setPassword(rs.getString(5));
				user.setProfilePicture(rs.getString("Profile_Picture"));
				user.setRole(rs.getString("Role"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	/**
	 * Gets user by email address
	 * 
	 * @param conn
	 * @param useraccount
	 * @return
	 */
	public static User getUserByEmailAddress(Connection conn, String email) {

		String query = "SELECT * from User where email_address=?";
		ResultSet rs = null;
		User user = null;

		try {
			PreparedStatement pstmt = conn.prepareStatement(query);

			pstmt.setString(1, email);

			rs = pstmt.executeQuery();
			if (rs != null && rs.next()) {
				user = new User();
				user.setId(rs.getInt(1));
				user.setFirstName(rs.getString(2));
				user.setLastName(rs.getString(3));
				user.setEmailAddress(rs.getString(4));
				user.setPassword(rs.getString(5));
				user.setProfilePicture(rs.getString("Profile_Picture"));
				user.setRole(rs.getString("Role"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	/**
	 * Authenticate the user
	 * 
	 * @param conn
	 * @param account
	 * @return
	 */
	public static boolean isAunthenticated(Connection conn, Account account) {

		String query = "SELECT * from User where email_address=?";
		ResultSet rs = null;
		User user = null;
		PasswordHashing passhash = new PasswordHashing();
		boolean isauthenticated = false;

		try {
			PreparedStatement pstmt = conn.prepareStatement(query);

			pstmt.setString(1, account.getEmailAddress());

			rs = pstmt.executeQuery();
			if (rs != null && rs.next()) {
				String storedpassword = rs.getString("Password");
				String hashedpassword = passhash.generateHash(ProjectConstants.SALT + account.getPassword());
				return storedpassword != null ? storedpassword.equals(hashedpassword) : false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isauthenticated;
	}

	/**
	 * Adds User object
	 * 
	 * @param conn
	 * @param user
	 * @return
	 */
	public static int addUser(Connection conn, AddUser user) {
		String query = "INSERT INTO `User` (`First_Name`, `Last_Name`, `Email_address`, `Password`, `Role` )"
				+ "    values (?,?,?,?,?)";
		int res = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			PasswordHashing passwordHash = new PasswordHashing();
			pstmt.setString(1, user.getFirstName());
			pstmt.setString(2, user.getLastName());
			pstmt.setString(3, user.getEmailAddress());
			pstmt.setString(4, passwordHash.generateHash(ProjectConstants.SALT + user.getPassword()));
			pstmt.setString(5, user.getRole());

			res = pstmt.executeUpdate();
			//Send Welcome Mail
			if(res>0)
				new EmailUtility().sendWelcomeMail(user.getEmailAddress());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}

	/**
	 * get all museums
	 * 
	 * @param conn
	 * @return
	 */
	public static List<Museum> getMusuems(Connection conn) {

		String query = "SELECT * from Museum";
		ResultSet rs = null;

		ArrayList list = new ArrayList<User>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();
			while (rs != null && rs.next()) {
				Museum museum = new Museum();

				museum.setId(rs.getInt("Id"));
				museum.setName(rs.getString("Name"));
				museum.setStreet(rs.getString("Street"));
				museum.setCity(rs.getString("City"));
				museum.setState(rs.getString("State"));
				museum.setCountry(rs.getString("Country"));
				museum.setZip(rs.getString("Zip"));
				museum.setLat(rs.getFloat("Lat"));
				museum.setLng(rs.getFloat("Lng"));
				museum.setBannerUrl(rs.getString("Banner_url"));
				museum.setLogoUrl(rs.getString("Logo_url"));
				museum.setAcronym(rs.getString("Acronym"));
				list.add(museum);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * get museum by id.
	 * 
	 * @param conn
	 * @param id
	 * @return
	 */
	public static Museum getMusuemById(Connection conn, int id) {

		String query = "SELECT * from Museum where id = ?";
		ResultSet rs = null;

		Museum museum = new Museum();
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();
			if (rs != null && rs.next()) {
				museum.setId(rs.getInt("Id"));
				museum.setName(rs.getString("Name"));
				museum.setStreet(rs.getString("Street"));
				museum.setCity(rs.getString("City"));
				museum.setState(rs.getString("State"));
				museum.setCountry(rs.getString("Country"));
				museum.setZip(rs.getString("Zip"));
				museum.setLat(rs.getFloat("Lat"));
				museum.setLng(rs.getFloat("Lng"));
				museum.setBannerUrl(rs.getString("Banner_url"));
				museum.setLogoUrl(rs.getString("Logo_url"));
				museum.setAcronym(rs.getString("Acronym"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return museum;
	}

	/**
	 * get Museum by museum name
	 * 
	 * @param conn
	 * @param name
	 * @return
	 */
	public static Museum getMusuemByName(Connection conn, String name) {

		String query = "SELECT * from Museum where name like ?";
		ResultSet rs = null;

		Museum museum = new Museum();
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);

			pstmt.setString(1, "%" + name + "%");

			rs = pstmt.executeQuery();
			if (rs != null && rs.next()) {
				museum.setId(rs.getInt("Id"));
				museum.setName(rs.getString("Name"));
				museum.setStreet(rs.getString("Street"));
				museum.setCity(rs.getString("City"));
				museum.setState(rs.getString("State"));
				museum.setCountry(rs.getString("Country"));
				museum.setZip(rs.getString("Zip"));
				museum.setLat(rs.getFloat("Lat"));
				museum.setLng(rs.getFloat("Lng"));
				museum.setBannerUrl(rs.getString("Banner_url"));
				museum.setLogoUrl(rs.getString("Logo_url"));
			} else {
				return null;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return museum;
	}

	/**
	 * get Museum by museum acronym
	 * 
	 * @param conn
	 * @param acname
	 * @return
	 */
	public static Museum getMusuemByAcronym(Connection conn, String acname) {

		String query = "SELECT * from Museum where acronym like ?";
		ResultSet rs = null;

		Museum museum = new Museum();
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			acname = "%" + acname + "%";
			pstmt.setString(1, acname);

			rs = pstmt.executeQuery();
			while (rs != null && rs.next()) {
				museum.setId(rs.getInt("Id"));
				museum.setName(rs.getString("Name"));
				museum.setStreet(rs.getString("Street"));
				museum.setCity(rs.getString("City"));
				museum.setState(rs.getString("State"));
				museum.setCountry(rs.getString("Country"));
				museum.setZip(rs.getString("Zip"));
				museum.setLat(rs.getFloat("Lat"));
				museum.setLng(rs.getFloat("Lng"));
				museum.setBannerUrl(rs.getString("Banner_url"));
				museum.setLogoUrl(rs.getString("Logo_url"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return museum;
	}

	/**
	 * get Museums by city
	 * 
	 * @param conn
	 * @param city
	 * @return
	 */
	public static List<Museum> getMusuemByCity(Connection conn, String city) {

		String query = "SELECT * from Museum where UPPER(city) like ?";
		ResultSet rs = null;

		List<Museum> museums = new ArrayList<Museum>();
		try {
			if(city != null && !city.equals("")){
				PreparedStatement pstmt = conn.prepareStatement(query);

				pstmt.setString(1, "%" + city.toUpperCase() + "%");

				rs = pstmt.executeQuery();
				while (rs != null && rs.next()) {
					Museum museum = new Museum();
					museum.setId(rs.getInt("Id"));
					museum.setName(rs.getString("Name"));
					museum.setStreet(rs.getString("Street"));
					museum.setCity(rs.getString("City"));
					museum.setState(rs.getString("State"));
					museum.setCountry(rs.getString("Country"));
					museum.setZip(rs.getString("Zip"));
					museum.setLat(rs.getFloat("Lat"));
					museum.setLng(rs.getFloat("Lng"));
					museum.setBannerUrl(rs.getString("Banner_url"));
					museum.setLogoUrl(rs.getString("Logo_url"));
					museums.add(museum);
				} 
			}
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return museums;
	}
	
	/**
	 * get Museums by zip
	 * 
	 * @param conn
	 * @param city
	 * @return
	 */
	public static List<Museum> getMusuemByZip(Connection conn, String zip) {

		String query = "SELECT * from Museum where zip like ?";
		ResultSet rs = null;

		List<Museum> museums = new ArrayList<Museum>();
		try {
			if(zip != null && !zip.equals("")){
				PreparedStatement pstmt = conn.prepareStatement(query);

				pstmt.setString(1, "%" + zip + "%");

				rs = pstmt.executeQuery();
				while (rs != null && rs.next()) {
					Museum museum = new Museum();
					museum.setId(rs.getInt("Id"));
					museum.setName(rs.getString("Name"));
					museum.setStreet(rs.getString("Street"));
					museum.setCity(rs.getString("City"));
					museum.setState(rs.getString("State"));
					museum.setCountry(rs.getString("Country"));
					museum.setZip(rs.getString("Zip"));
					museum.setLat(rs.getFloat("Lat"));
					museum.setLng(rs.getFloat("Lng"));
					museum.setBannerUrl(rs.getString("Banner_url"));
					museum.setLogoUrl(rs.getString("Logo_url"));
					museums.add(museum);
				} 
			}
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return museums;
	}


	
	/**
	 * gets the list of images for a museum
	 * 
	 * @param conn
	 * @param id
	 * @return
	 */
	public static List<Gallery> getMuseumGalleryById(Connection conn, int id) {

		String query = "SELECT * from Gallery where museum_id = ?";
		ResultSet rs = null;

		List<Gallery> gallery = new ArrayList<Gallery>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();
			while (rs != null && rs.next()) {
				Gallery image = new Gallery();
				image.setId(rs.getInt("Id"));
				image.setName(rs.getString("Name"));
				image.setUrl(rs.getString("url"));
				image.setFeaturetype(rs.getString("feature_type"));
				image.setDescription(rs.getString("description"));
				image.setArtist(rs.getString("artist"));
				image.setYear(rs.getString("year"));
				image.setMuseumId(id);
				gallery.add(image);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return gallery;
	}

	/**
	 * gets the list of featured images for a museum
	 * 
	 * @param conn
	 * @param id
	 * @return
	 */
	public static List<FeaturedImages> getFeaturedImagesByMuseumId(Connection conn, int id) {

		String query = "SELECT * from Featured_Images where museum_id = ?";
		ResultSet rs = null;

		List<FeaturedImages> fimages = new ArrayList<FeaturedImages>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();
			while (rs != null && rs.next()) {
				FeaturedImages image = new FeaturedImages();
				image.setId(rs.getInt("Id"));
				image.setName(rs.getString("Name"));
				image.setUrl(rs.getString("url"));
				image.setMuseumId(id);
				fimages.add(image);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return fimages;
	}

	/**
	 * Gets Gallery by artist name
	 * @param conn
	 * @param artist
	 * @return
	 */
	public static List<Gallery> getMuseumGalleryByArtist(Connection conn, String artist) {

		String query = "SELECT * from Gallery where UPPER(artist) like ?";
		ResultSet rs = null;

		List<Gallery> gallery = new ArrayList<Gallery>();
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(query);

			pstmt.setString(1, "%" + artist.toUpperCase() + "%");
			
			rs = pstmt.executeQuery();
			while (rs != null && rs.next()) {
				Gallery image = new Gallery();
				image.setId(rs.getInt("Id"));
				image.setName(rs.getString("Name"));
				image.setUrl(rs.getString("url"));
				image.setFeaturetype(rs.getString("feature_type"));
				image.setDescription(rs.getString("description"));
				image.setArtist(rs.getString("artist"));
				image.setYear(rs.getString("year"));
				image.setMuseumId(rs.getInt("museum_id"));
				gallery.add(image);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return gallery;
	}
	
	/**
	 * Gets gallery for the category
	 * @param conn
	 * @param category
	 * @return
	 */
	public static List<Gallery> getMuseumGalleryByCategory(Connection conn, String category) {

		String query = "SELECT * from Gallery where UPPER(feature_type) like ?";
		ResultSet rs = null;

		List<Gallery> gallery = new ArrayList<Gallery>();
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(query);

			pstmt.setString(1, "%" + category.toUpperCase() + "%");
			
			rs = pstmt.executeQuery();
			while (rs != null && rs.next()) {
				Gallery image = new Gallery();
				image.setId(rs.getInt("Id"));
				image.setName(rs.getString("Name"));
				image.setUrl(rs.getString("url"));
				image.setFeaturetype(rs.getString("feature_type"));
				image.setDescription(rs.getString("description"));
				image.setArtist(rs.getString("artist"));
				image.setYear(rs.getString("year"));
				image.setMuseumId(rs.getInt("museum_id"));
				gallery.add(image);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return gallery;
	}
	
	
	/**
	 * gets the list of floor plans for a museum
	 * 
	 * @param conn
	 * @param id
	 * @return
	 */
	public static List<MuseumMap> getMapByMuseumId(Connection conn, int id) {

		String query = "SELECT * from Museum_Map where museum_id = ?";
		ResultSet rs = null;

		List<MuseumMap> mapImages = new ArrayList<MuseumMap>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();
			while (rs != null && rs.next()) {
				MuseumMap image = new MuseumMap();
				image.setId(rs.getInt("Id"));
				image.setName(rs.getString("Map_Name"));
				image.setUrl(rs.getString("url"));
				image.setMuseumId(id);
				mapImages.add(image);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mapImages;
	}

	/**
	 * Gets museum price details
	 * 
	 * @param conn
	 * @param id
	 * @return
	 */
	public static Museum getMuseumPrice(Connection conn, int id) {

		String query = "SELECT * from Museum_prices where museum_id = ?";
		ResultSet rs = null;

		Museum museum = new Museum();
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();
			if (rs != null && rs.next()) {
				museum.setId(rs.getInt("museum_id"));
				museum.setPriceDetails(rs.getString("comments"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return museum;
	}

	/**
	 * Get Museum visiting hours
	 * 
	 * @param conn
	 * @param id
	 * @return
	 */
	public static Museum getMuseumTimings(Connection conn, int id) {

		String query = "SELECT * from Museum_Timings where museum_id = ?";
		ResultSet rs = null;

		Museum museum = new Museum();
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();
			if (rs != null && rs.next()) {
				museum.setId(rs.getInt("museum_id"));
				museum.setTimingDetails(rs.getString("comments"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return museum;
	}

	/**
	 * Adds Museum object
	 * 
	 * @param conn
	 * @param museum
	 * @return
	 */
	public static int addMuseum(Connection conn, Museum museum) {

		String query = "INSERT INTO `Museum` (`Name`, `Street`, `City`, `Country`, `Zip`, `Lat`, `Lng`, `Banner_url`, `State`, `logo_url`, `Acronym`)"
				+ "    values (?,?,?,?,?,?,?,?,?,?,?)";
		int res = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, museum.getName());
			pstmt.setString(2, museum.getStreet());
			pstmt.setString(3, museum.getCity());
			pstmt.setString(4, museum.getCountry());
			pstmt.setString(5, museum.getZip());
			pstmt.setDouble(6, museum.getLat());
			pstmt.setDouble(7, museum.getLng());
			pstmt.setString(8, museum.getBannerUrl());
			pstmt.setString(9, museum.getState());
			pstmt.setString(10, museum.getLogoUrl());
			pstmt.setString(11, museum.getAcronym());

			res = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}

	/**
	 * Updates User object
	 * 
	 * @param conn
	 * @param user
	 * @return
	 */
	public static int updateUser(Connection conn, UpdatedUser user) {

		String query = "Update User set first_name=?, last_name=?, email_address=?, role=? where id = ?";
		int res = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user.getFirstName());
			pstmt.setString(2, user.getLastName());
			pstmt.setString(3, user.getEmailAddress());
			pstmt.setString(4, user.getRole());
			pstmt.setInt(5, user.getId());

			res = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}

	/**
	 * Updates Museum object
	 * 
	 * @param conn
	 * @param museum
	 * @return
	 */
	public static int updateMuseum(Connection conn, Museum museum) {

		String query = "Update Museum set name=?, street=?, city=?, state=?, country=?, lat=?, lng=?, zip=?  where id = ?";
		int res = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, museum.getName());
			pstmt.setString(2, museum.getStreet());
			pstmt.setString(3, museum.getCity());
			pstmt.setString(4, museum.getState());
			pstmt.setString(5, museum.getCountry());
			pstmt.setDouble(6, museum.getLat());
			pstmt.setDouble(7, museum.getLng());
			pstmt.setString(8, museum.getZip());
			pstmt.setInt(9, museum.getId());
			res = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}

	/**
	 * Deletes a museum
	 * 
	 * @param conn
	 * @param id
	 * @return
	 */
	public static int deleteMuseum(Connection conn, int id) {

		String query = "Delete from Museum where id = ?";
		int res = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}

}
