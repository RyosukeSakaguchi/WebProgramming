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
 * Servlet implementation class SignUp
 */
@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignUp() {
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
			// signUp.jspへフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/signUp.jsp");
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
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String passwordConf = request.getParameter("passwordConf");
		String name = request.getParameter("name");
		String birthDate = request.getParameter("birthDate");

		// 現在時刻を読みやすい文字列形式でdateStrに代入
		Date date = new Date();
		SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = f1.format(date);

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
		if (!dao.UserInfoDao.userCheck(loginId, encPass, encPassConf, name, birthDate)) {
			//リクエストパラメーターを保存
			request.setAttribute("errMsg", "入力された内容は正しくありません。");
			request.setAttribute("loginId", loginId);
			request.setAttribute("name", name);
			request.setAttribute("birthDate", birthDate);

			// signUp.jspへフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/signUp.jsp");
			dispatcher.forward(request, response);
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
				// リクエストパラメーターを保存
				request.setAttribute("errMsg", "生年月日が正しくありません。");
				request.setAttribute("loginId", loginId);
				request.setAttribute("name", name);
				request.setAttribute("birthDate", birthDate);

				// signUp.jspへフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/signUp.jsp");
				dispatcher.forward(request, response);
			} else {
				// ユーザー情報をuserテーブルに保存
				dao.UserInfoDao.userSet(loginId, encPass, name, birthDate, dateStr);

				// UserListへリダイレクト
				response.sendRedirect("UserList");
			}
		}
	}
}
