package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import userManagement.UserInfo;

public class UserInfoDao {

	public static boolean userCheck(String loginId, String password) {
		Connection conn = null;
		try {

			// データベースへ接続
			conn = DBManager.getConnection();
			// SELECT文を準備
			String sql = "select * from user where login_id = ? and password = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, loginId);
			pStmt.setString(2, password);

			// SELECTを実行し、結果表（ResultSet）を取得
			ResultSet rs = pStmt.executeQuery();

			// 結果表に格納されたレコードの内容を表示
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	public static boolean userCheck(String loginId, String encPass, String encPassConf, String name, String birthDate) {
		Connection conn = null;
		if (loginId.length() != 0 && encPass.length() != 0 && name.length() != 0 && birthDate.length() != 0) {
			if (encPass.equals(encPassConf)) {
				try {

					// データベースへ接続
					conn = DBManager.getConnection();

					// SELECT文を準備
					String sql = "select * from user";
					PreparedStatement pStmt = conn.prepareStatement(sql);

					// SELECTを実行し、結果表を取得
					ResultSet rs = pStmt.executeQuery();
					// 結果表に格納されたレコードの内容を
					// Employeeインスタンスに設定し、ArrayListインスタンスに追加

					while (rs.next()) {
						if (loginId.equals(rs.getString("login_id"))) {
							return false;
						}
					}
					return true;
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					// データベース切断
					if (conn != null) {
						try {
							conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}

				}
			}
		}

		return false;

	}

	public static List<UserInfo> findAll() {
		Connection conn = null;
		List<UserInfo> userList = new ArrayList<UserInfo>();

		try {
			// データベースへ接続
			conn = DBManager.getConnection();

			// SELECT文を準備
			String sql = "select * from user";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SELECTを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();
			// 結果表に格納されたレコードの内容を
			// Employeeインスタンスに設定し、ArrayListインスタンスに追加
			while (rs.next()) {
				UserInfo userInfo = new UserInfo();
				userInfo.setId(rs.getString("id"));
				userInfo.setLoginId(rs.getString("login_id"));
				userInfo.setName(rs.getString("name"));
				userInfo.setBirthDate(rs.getString("birth_date"));
				userInfo.setPassword(rs.getString("password"));
				userInfo.setCreateDate(rs.getString("create_date"));
				userInfo.setUpdateDate(rs.getString("update_date"));
				userList.add(userInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return userList;
	}

	public static UserInfo findAll(String id) {
		Connection conn = null;
		UserInfo userInfo = new UserInfo();

		try {
			// データベースへ接続
			conn = DBManager.getConnection();

			// SELECT文を準備
			String sql = "select * from user where id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, id);

			// SELECTを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();
			// 結果表に格納されたレコードの内容を
			// Employeeインスタンスに設定し、ArrayListインスタンスに追加
			while (rs.next()) {
				userInfo.setId(id);
				userInfo.setLoginId(rs.getString("login_id"));
				userInfo.setName(rs.getString("name"));
				userInfo.setBirthDate(rs.getString("birth_date"));
				userInfo.setPassword(rs.getString("password"));
				userInfo.setCreateDate(rs.getString("create_date"));
				userInfo.setUpdateDate(rs.getString("update_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return userInfo;

	}

	public static UserInfo findAll(String loginId, String password) {
		Connection conn = null;
		UserInfo userInfo = new UserInfo();

		try {
			// データベースへ接続
			conn = DBManager.getConnection();

			// SELECT文を準備
			String sql = "select * from user where login_id = ? and password = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, loginId);
			pStmt.setString(2, password);

			// SELECTを実行し、結果表（ResultSet）を取得
			ResultSet rs = pStmt.executeQuery();

			// 結果表に格納されたレコードの内容を
			// Employeeインスタンスに設定し、ArrayListインスタンスに追加
			if (rs.next()) {
				userInfo.setId(rs.getString("id"));
				userInfo.setLoginId(loginId);
				userInfo.setName(rs.getString("name"));
				userInfo.setBirthDate(rs.getString("birth_date"));
				userInfo.setPassword(password);
				userInfo.setCreateDate(rs.getString("create_date"));
				userInfo.setUpdateDate(rs.getString("update_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return userInfo;

	}

	public static void userUpdate(String id, String name, String birthDate, String dateStr) {
		Connection conn = null;

		try {
			// データベースへ接続
			conn = DBManager.getConnection();

			// INSERT文を準備
			String sql = "UPDATE user SET name ='" + name + "', birth_date ='" + birthDate + "', update_date ='"
					+ dateStr + "' WHERE id = ? ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, id);

			// SELECTを実行し、結果表（ResultSet）を取得
			pStmt.executeUpdate();

			// 結果表に格納されたレコードの内容を
			// Employeeインスタンスに設定し、ArrayListインスタンスに追加
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void userUpdate(String id, String encPass, String name, String birthDate, String dateStr) {
		Connection conn = null;
		try {
			// データベースへ接続
			conn = DBManager.getConnection();

			// INSERT文を準備
			String sql = "UPDATE user SET name ='" + name + "', birth_date ='" + birthDate + "', update_date ='"
					+ dateStr + "', password ='" + encPass + "' WHERE id = ? ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, id);

			// SELECTを実行し、結果表（ResultSet）を取得
			pStmt.executeUpdate();

			// 結果表に格納されたレコードの内容を
			// Employeeインスタンスに設定し、ArrayListインスタンスに追加
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void userSet(String loginId, String password, String name, String birthDate, String dateStr) {
		Connection conn = null;
		int i = 0;

		try {
			// データベースへ接続
			conn = DBManager.getConnection();

			// SELECT文を準備
			String sql = "select * from user";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SELECTを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();
			// 結果表に格納されたレコードの内容を
			// Employeeインスタンスに設定し、ArrayListインスタンスに追加
			while (rs.next()) {
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		try {
			// データベースへ接続
			conn = DBManager.getConnection();

			// INSERT文を準備
			String sql = "INSERT INTO user VALUES (" + "\"" + (i + 1) + "\" , \"" + loginId + "\" , \"" + name
					+ "\" , \"" + birthDate + "\" , \"" + password + "\" , \"" + dateStr + "\" , \"" + dateStr
					+ "\"  );";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// INSERTを実行
			pStmt.executeUpdate();

			// 結果表に格納されたレコードの内容を
			// Employeeインスタンスに設定し、ArrayListインスタンスに追加
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void userDel(String id) {
		Connection conn = null;

		try {
			// データベースへ接続
			conn = DBManager.getConnection();

			// SELECT文を準備
			String sql = "DELETE FROM user WHERE id =?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, id);

			// SELECTを実行し、結果表（ResultSet）を取得
			pStmt.executeUpdate();
			// 結果表に格納されたレコードの内容を
			// Employeeインスタンスに設定し、ArrayListインスタンスに追加

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public static String lgIdSql(String loginId) {

		if (loginId.length() == 0) {
			System.out.println(loginId.length());
			return " 1=1 ";
		} else {
			return " login_id ='" + loginId + "' ";
		}
	}

	public static String nameSql(String name) {

		if (name.length() == 0) {
			return " ";
		} else {
			return "and name like'%" + name + "%' ";
		}
	}


	public static String stDtSql(String  startBirth) {

		if ( startBirth.length() == 0) {
			return " and birth_date between '" + 0 + "'" ;
		} else {
			return " and birth_date between '" + startBirth + "' ";
		}
	}

	public static String edDtSql(String  endBirth) {

		Date date = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = f.format(date);

		if ( endBirth.length() == 0) {
			return " and '" + dateStr + "'" ;
		} else {
			return " and'" + endBirth + "' ";
		}
	}

	public static List<UserInfo> userSearch(String text) {
		Connection conn = null;
		List<UserInfo> userList = new ArrayList<UserInfo>();

		try {
			// データベースへ接続
			conn = DBManager.getConnection();

			// SELECT文を準備
			String sql = "select * from user where" + text;
			PreparedStatement pStmt = conn.prepareStatement(sql);


			// SELECTを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();
			// 結果表に格納されたレコードの内容を
			// Employeeインスタンスに設定し、ArrayListインスタンスに追加
			while (rs.next()) {
				UserInfo userInfo = new UserInfo();
				userInfo.setId(rs.getString("id"));
				userInfo.setLoginId(rs.getString("login_id"));
				userInfo.setName(rs.getString("name"));
				userInfo.setBirthDate(rs.getString("birth_date"));
				userInfo.setPassword(rs.getString("password"));
				userInfo.setCreateDate(rs.getString("create_date"));
				userInfo.setUpdateDate(rs.getString("update_date"));
				userList.add(userInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return userList;
	}

}
