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
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginScreen.jsp");
		dispatcher.forward(request, response);
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

		String encPass = null;

		try {
			encPass = Common.Encrpt(password);
		} catch (NoSuchAlgorithmException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		if (!dao.UserInfoDao.userCheck(loginId, encPass)) {
			request.setAttribute("errMsg", "ログインIDまたはパスワードが異なります。");
			// フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginScreen.jsp");
			dispatcher.forward(request, response);
		} else {

			// セッションスコープに保存するインスタンス(JavaBeans)の生成
			UserInfo userInfo = new UserInfo();

			userInfo = dao.UserInfoDao.findAll(loginId, encPass);

			// HttpSessionインスタンスの取得
			HttpSession session = request.getSession();

			// セッションスコープにインスタスを保存
			session.setAttribute("userInfo", userInfo);

			// フォワード
			response.sendRedirect("UserList");
		}

	}

}
