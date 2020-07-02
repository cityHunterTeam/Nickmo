package member;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/mem/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberDAO memberDAO;

	public void init(ServletConfig config) throws ServletException {
		memberDAO = new MemberDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);

	}

	protected void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextPage = null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action = request.getPathInfo(); //경로를 지정하는 아이
		System.out.println("action:" + action);

		if (action.equals("/join.do")) {
			
			nextPage = "/member/join.jsp";
			
		} else if (action.equals("/joinPro.do")) {
			String id = request.getParameter("id");
			String passwd = request.getParameter("passwd");
			String name = request.getParameter("name");
			String birth = request.getParameter("birth");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			MemberVO memberVO = new MemberVO(id, passwd, name, birth, email, phone, address);
			memberDAO.addMember(memberVO);
			nextPage = "/member/joinPro.jsp";
			
		} else if (action.equals("/login.do")) {
			
			nextPage = "/member/login.jsp";
			
		} else if (action.equals("/loginPro.do")) {
			
			String id = request.getParameter("id");
			String passwd = request.getParameter("passwd");
			int chk = memberDAO.loginCheck(id, passwd);
			if (chk > 0) {
				HttpSession session = request.getSession();
				session.setAttribute("id", id);
				nextPage = "/member/loginPro.jsp";
			} else {
				nextPage = "/member/loginFail.jsp";
			}
			
		} else if (action.equals("/index.do")) {
			
			nextPage = "../index.jsp";
			
		} else if (action.equals("/logout.do")) {
			
			HttpSession session = request.getSession();
			session.invalidate();
			nextPage = "/member/logout.jsp";
			
		} else if (action.equals("/myCheck.do")) {

			nextPage = "../member/mypageCheck.jsp";

		} else if (action.equals("/myPage.do")) {
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("id");
			String passwd = request.getParameter("passwd");
			int chk = memberDAO.loginCheck(id, passwd);
			MemberVO vo = new MemberVO();
			if (chk > 0) {
				vo = memberDAO.selectAll(id);
				request.setAttribute("vo", vo);
				nextPage = "/member/mypage.jsp";
			} else {
				nextPage = "/member/mypageFail.jsp";
			}
			
		} else if (action.equals("/mypagePro.do")) {
			
			String passwd = request.getParameter("passwd");
			String name = request.getParameter("name");
			String birth = request.getParameter("birth");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			MemberVO vo2 = new MemberVO(passwd, name, birth, email, phone, address);
			memberDAO.updateAll(vo2);
			nextPage = "/member/mypagePro.jsp";
			
		} else if(action.equals("/deleteMem.do")) {
			
			HttpSession session = request.getSession();
			String id = (String)session.getAttribute("id");
			memberDAO.deleteMem(id);
			session.invalidate();
			nextPage = "/member/deleteMem.jsp";
			
		}

		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);

	}

}
