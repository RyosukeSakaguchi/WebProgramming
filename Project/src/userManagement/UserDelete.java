package userManagement;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UserDelete
 */
@WebServlet("/UserDelete")
public class UserDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserDelete() {
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
		} else if (request.getParameter("id") == null) {
			// userDelete.jspへフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userDelete.jsp");
			dispatcher.forward(request, response);
		} else {
			// リクエストパラメータの取得
			String id = request.getParameter("id");

			// リクエストスコープに保存するインスタンス(JavaBeans)の生成
			UserInfo userInfo = new UserInfo();

			// ユーザーを探してuserInfoに代入
			userInfo = dao.UserInfoDao.findAll(id);
			request.setAttribute("userInfo", userInfo);

			// userDelete.jspへフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userDelete.jsp");
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

		// リクエストスコープにパラメーターがあるかで分岐
		if (id == null) {
			// 全てのユーザーを消去するメソッドを実行
			dao.UserInfoDao.allUserDel();

			//全てのユーザー消去成功のメッセージをリクエストスコープに保存
			request.setAttribute("sucMsg", "全ユーザー情報の削除に成功しました。");

			// UserListのdoGetメソッドを実行
			UserList userList = new UserList();
			userList.doGet(request, response);
		} else {
			// ユーザーを消去するメソッドを実行
			dao.UserInfoDao.userDel(id);

			//ユーザー消去成功のメッセージをリクエストスコープに保存
			request.setAttribute("sucMsg", "ユーザー情報の削除に成功しました。");

			// UserListのdoGetメソッドを実行
			UserList userList = new UserList();
			userList.doGet(request, response);
		}
	}

}
