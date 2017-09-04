package userManagement;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UserUpdate
 */
@WebServlet("/UserUpdate")
public class UserUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserUpdate() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// HttpSessionインスタンスの取得
		HttpSession session = request.getSession();

		// セッションにログイン情報があるかないかで分岐
		if ((UserInfo) session.getAttribute("loginUser") == null) {
			// LoginScreenへリダイレクト
			response.sendRedirect("LoginScreen");
		} else {
			// リクエストパラメータの取得
			String id = request.getParameter("id");

			// リクエストスコープに保存するインスタンス(JavaBeans)の生成
			UserInfo userInfo = new UserInfo();

			// ユーザーを探してuserInfoに代入
			userInfo = dao.UserInfoDao.findAll(id);
			request.setAttribute("userInfo", userInfo);

			// userUpdate.jspへフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userUpdate.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// リクエストパラメータの文字コードを指定
		request.setCharacterEncoding("UTF-8");

		// リクエストパラメータの取得
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String passwordConf = request.getParameter("passwordConf");
		String name = request.getParameter("name");
		String birthDate = request.getParameter("birthDate");

		// 現在時刻を読みやすい文字列形式でdateStrに代入
		Date date = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = f.format(date);

		// 暗号化されたパスワードとパスワード(確認)を生成
		String encPass = null;
		String encPassConf = null;
		try {
			encPass = Common.encrpt(password);
			encPassConf = Common.encrpt(passwordConf);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		// 入力項目に未入力があるかないかで分岐
		if (name.length() == 0 || birthDate.length() == 0) {
			request.setAttribute("errMsg", "入力された内容は正しくありません。");
			doGet(request, response);
		} else {
			//誕生日と現在の日付をlong型に変換
			SimpleDateFormat f2 = new SimpleDateFormat("yyyy-MM-dd");
			Date birth = null;
			try {
				birth = f2.parse(birthDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			long dateTime = date.getTime();
			long birthTime = birth.getTime();

			//誕生日が現在の日付以前であるかで分岐
			if (dateTime - birthTime < 0) {
				request.setAttribute("errMsg", "生年月日が正しくありません。");
				doGet(request, response);
			}
		}

		// パスワードとパスワード(確認)が同じであるか、もし同じ時にパスワードは未入力であるかで分岐
		if (!encPass.equals(encPassConf)) {
			request.setAttribute("errMsg", "入力された内容は正しくありません。");
			doGet(request, response);
		} else if (password.length() == 0) {
			// ユーザー情報を更新
			dao.UserInfoDao.userUpdate(id, name, birthDate, dateStr);
		} else {
			// ユーザー情報を更新
			dao.UserInfoDao.userUpdate(id, encPass, name, birthDate, dateStr);
		}

		// UserListへリダイレクト
		response.sendRedirect("UserList");

	}

}
