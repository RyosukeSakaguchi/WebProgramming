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

	/** ログインIDとパスワードを受け取り、一致するユーザーが存在するかどうかをチェックするメソッド
	 * @param loginId ログインID
	 * @param encPass 暗号化されたパスワード
	 * @return 一致するユーザーがuserテーブルにいる場合はtrue、いない場合はfalseを返す
	 */
	public static boolean userCheck(String loginId, String encPass) {
		Connection conn = null;
		try {
			// データベースへ接続
			conn = DBManager.getConnection();

			// SELECT文を準備
			String sql = "select * from user where login_id = ? and password = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, loginId);
			pStmt.setString(2, encPass);

			// SELECTを実行し、結果表（ResultSet）を取得
			ResultSet rs = pStmt.executeQuery();

			// 結果表に格納されたレコード数で繰り返し
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

	/** 新規登録が可能かどうかを調べるメソッド
	 * @param loginId ログインID
	 * @param encPass 暗号化されたパスワード
	 * @param encPassConf 暗号化されたパスワード(確認)
	 * @param name ユーザー名
	 * @param birthDate 誕生日
	 * @return パスワード以外に未記入がない、かつ、パスワードとパスワード(確認)が一致している、かつ、ログインIDが被っていないときtrueを返す(それ以外はfalse)
	 */
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

					// 結果表に格納されたレコード数で繰り返し
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

	/** userテーブルにある全てのユーザーを取り出すメソッド
	 * @return userテーブルにある全てのユーザーのリスト(userList)
	 */
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
			// UserInfoインスタンスに設定し、Listインスタンスに追加
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


	/** userテーブルにあるIDの値がidであるユーザーを取り出すメソッド
	 * @param id ID
	 * @return IDの値がidであるユーザー(UserInfo型)
	 */
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

			// 結果表に格納されたレコード数で繰り返し、IDの値がidであるユーザーを探す
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


	/** ログインIDと暗号化されたパスワードを受け取り、一致するユーザーを取り出すメソッド
	 * @param loginId ログインID
	 * @param encPass 暗号化されたパスワード
	 * @return ログインIDと暗号化されたパスワードがそれぞれ、loginIdとencPassであるユーザー(UserInfo型)
	 */
	public static UserInfo findAll(String loginId, String encPass) {
		Connection conn = null;
		UserInfo userInfo = new UserInfo();
		try {
			// データベースへ接続
			conn = DBManager.getConnection();

			// SELECT文を準備
			String sql = "select * from user where login_id = ? and password = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, loginId);
			pStmt.setString(2, encPass);

			// SELECTを実行し、結果表（ResultSet）を取得
			ResultSet rs = pStmt.executeQuery();

			// 結果表に格納されたレコード数で繰り返し、ログインIDと暗号化されたパスワードがそれぞれ、loginIdとencPassであるユーザーを探す
			if (rs.next()) {
				userInfo.setId(rs.getString("id"));
				userInfo.setLoginId(loginId);
				userInfo.setName(rs.getString("name"));
				userInfo.setBirthDate(rs.getString("birth_date"));
				userInfo.setPassword(encPass);
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

	/** IDとユーザー名と誕生日と更新日を受け取り、ユーザー情報を更新するメソッド
	 * @param id ID
	 * @param name ユーザー名
	 * @param birthDate 誕生日
	 * @param dateStr 更新日
	 */
	public static void userUpdate(String id, String name, String birthDate, String dateStr) {
		Connection conn = null;

		try {
			// データベースへ接続
			conn = DBManager.getConnection();

			// UPDATE文を準備
			String sql = "UPDATE user SET name ='" + name + "', birth_date ='" + birthDate + "', update_date ='"
					+ dateStr + "' WHERE id = ? ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, id);

			// UPDATEを実行
			pStmt.executeUpdate();

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

	/** IDと暗号化されたパスワードとユーザー名と誕生日と更新日を受け取り、ユーザー情報を更新するメソッド
	 * @param id ID
	 * @param encPass 暗号化されたパスワード
	 * @param name ユーザー名
	 * @param birthDate 誕生日
	 * @param dateStr 更新日
	 */
	public static void userUpdate(String id, String encPass, String name, String birthDate, String dateStr) {
		Connection conn = null;
		try {
			// データベースへ接続
			conn = DBManager.getConnection();

			// UPDATE文を準備
			String sql = "UPDATE user SET name ='" + name + "', birth_date ='" + birthDate + "', update_date ='"
					+ dateStr + "', password ='" + encPass + "' WHERE id = ? ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, id);

			// UPDATEを実行
			pStmt.executeUpdate();

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

	/**ログインIDと暗号化されたパスワードとユーザー名と誕生日と登録日を受け取り、ユーザー情報を作成するメソッド
	 * @param loginId ログインID
	 * @param encPass 暗号化されたパスワード
	 * @param name ユーザー名
	 * @param birthDate 誕生日
	 * @param dateStr 登録日
	 */
	public static void userSet(String loginId, String encPass, String name, String birthDate, String dateStr) {
		Connection conn = null;
		try {
			// データベースへ接続
			conn = DBManager.getConnection();

			// INSERT文を準備
			String sql = "INSERT INTO user (login_id, name, birth_date, password, create_date, update_date) VALUES ('" + loginId + "' , '" + name
					+ "' , '" + birthDate + "' , '" + encPass + "' , '" + dateStr + "' , '" + dateStr
					+ "'  )";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// INSERTを実行
			pStmt.executeUpdate();

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

	/** IDを受け取り、一致するユーザーを消去するメソッド
	 * @param id ID
	 */
	public static void userDel(String id) {
		Connection conn = null;
		try {
			// データベースへ接続
			conn = DBManager.getConnection();

			// DELETE文を準備
			String sql = "DELETE FROM user WHERE id =?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, id);

			// DELETEを実行
			pStmt.executeUpdate();

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

	/** 全てのユーザーを消去するメソッド
	 */
	public static void allUserDel() {
		Connection conn = null;
		try {
			// データベースへ接続
			conn = DBManager.getConnection();

			// DELETE文を準備
			String sql = "DELETE FROM user WHERE id NOT IN ('1')";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// DELETEを実行し、結果表（ResultSet）を取得
			pStmt.executeUpdate();

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

	/** ログインIDを受け取り、SQL文の一部を返すメソッド
	 * @param loginId ログインID
	 * @return ログインIDが未入力の時「1=1」、ログインIDが入力されている時「 login_id = 'loginId'」を返す
	 */
	public static String lgIdSql(String loginId) {
		if (loginId.length() == 0) {
			return " 1=1 ";
		} else {
			return " login_id ='" + loginId + "' ";
		}
	}

	/** ユーザー名を受け取り、SQL文の一部を返すメソッド
	 * @param name ユーザー名
	 * @return ユーザー名が未入力の時「 」、ユーザー名が入力されている時「 and name like '%name%' 」を返す
	 */
	public static String nameSql(String name) {
		if (name.length() == 0) {
			return " ";
		} else {
			return "and name like'%" + name + "%' ";
		}
	}


	/** 開始日を受け取り、SQL文の一部を返すメソッド
	 * @param startBirth 開始日
	 * @return 開始日が未入力の時「and birth_date between '0'」、開始日が入力されている時「and birth_date between 'startBirth'」を返す
	 */
	public static String stDtSql(String  startBirth) {
		if ( startBirth.length() == 0) {
			return " and birth_date between '" + 0 + "'" ;
		} else {
			return " and birth_date between '" + startBirth + "' ";
		}
	}

	/** 終了日を受け取り、SQL文の一部を返すメソッド
	 * @param endBirth 終了日
	 * @return 終了日が未入力の時「and '現在の日付'」、終了日が入力されている時「and　'endBirth'」を返す
	 */
	public static String edDtSql(String  endBirth) {

		// 日付を読みやすい文字列形式で表示する
		Date date = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = f.format(date);

		if ( endBirth.length() == 0) {
			return " and '" + dateStr + "'" ;
		} else {
			return " and'" + endBirth + "' ";
		}
	}

	/** SQL文の一部を受け取り、完全なSQL文を返すメソッド
	 * @param text SQL文の一部
	 * @return select * from user where textを返す
	 */
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
			// UserInfoインスタンスに設定し、Listインスタンスに追加
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
