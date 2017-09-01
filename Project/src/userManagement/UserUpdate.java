package userManagement;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		UserInfo userInfo = new UserInfo();
		userInfo = dao.UserInfoDao.findAll(id);
		request.setAttribute("userInfo", userInfo);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userUpdate.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String passwordConf = request.getParameter("passwordConf");
		String name = request.getParameter("name");
		String birthDate = request.getParameter("birthDate");

		Date date = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = f.format(date);

		String encPass = null;
		String encPassConf = null;

		try {
			encPass = Common.Encrpt(password);
			encPassConf = Common.Encrpt(passwordConf);
		} catch (NoSuchAlgorithmException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		if (name.length() == 0 || birthDate.length() == 0) {
			request.setAttribute("errMsg", "入力された内容は正しくありません。");
			doGet(request, response);
		} else if (!encPass.equals(encPassConf)) {
			request.setAttribute("errMsg", "入力された内容は正しくありません。");
			doGet(request, response);
		} else if (password.length() == 0) {
			dao.UserInfoDao.userUpdate(id, name, birthDate, dateStr);
		} else {
			dao.UserInfoDao.userUpdate(id, encPass, name, birthDate, dateStr);
		}

		response.sendRedirect("UserList");

	}

}
