package userManagement;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UserList
 */
@WebServlet("/UserList")
public class UserList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserList() {
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
			// userテーブルにある全てのユーザーを取り出す
			List<UserInfo> userList = dao.UserInfoDao.findAll();
			request.setAttribute("userList", userList);

			// userList.jspへフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userList.jsp");
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
		String name = request.getParameter("name");
		String startBirth = request.getParameter("startBirth");
		String endBirth = request.getParameter("endBirth");

		// sql文を生成
		String text = dao.UserInfoDao.lgIdSql(loginId) + dao.UserInfoDao.nameSql(name)
				+ dao.UserInfoDao.stDtSql(startBirth) + dao.UserInfoDao.edDtSql(endBirth);

		// 条件にあうユーザーを取り出す
		List<UserInfo> userList = dao.UserInfoDao.userSearch(text);

		// リクエストパラメータを保存
		request.setAttribute("userList", userList);
		request.setAttribute("loginId", loginId);
		request.setAttribute("name", name);
		request.setAttribute("startBirth", startBirth);
		request.setAttribute("endBirth", endBirth);

		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userList.jsp");
		dispatcher.forward(request, response);
	}

}
