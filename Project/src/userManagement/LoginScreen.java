package userManagement;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginScreen
 */
@WebServlet("/LoginScreen")
public class LoginScreen extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginScreen() {
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
			// loginScreen.jspへフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginScreen.jsp");
			dispatcher.forward(request, response);
		} else {
			// UserListへリダイレクト
			response.sendRedirect("UserList");
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

		//暗号化されたパスワードを生成
		String encPass = null;
		try {
			encPass = Common.encrpt(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		//ログインができるかできないかを判断
		if (!dao.UserInfoDao.userCheck(loginId, encPass)) {
			request.setAttribute("errMsg", "ログインIDまたはパスワードが異なります。");
			request.setAttribute("loginId", loginId);
			// loginScreen.jspへフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginScreen.jsp");
			dispatcher.forward(request, response);
		} else {
			// セッションスコープに保存するインスタンス(JavaBeans)の生成
			UserInfo userInfo = new UserInfo();

			// ユーザーを探し出し、userInfoに代入
			userInfo = dao.UserInfoDao.findAll(loginId, encPass);

			// HttpSessionインスタンスの取得
			HttpSession session = request.getSession();

			// セッションスコープにインスタスを保存
			session.setAttribute("loginUser", userInfo);

			// UserListへリダイレクト
			response.sendRedirect("UserList");
		}

	}

}
