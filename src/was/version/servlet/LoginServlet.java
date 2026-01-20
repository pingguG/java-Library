package was.version.servlet;

import Library.LibraryService;
import model.*;
import was.httpserver.*;

public class LoginServlet implements HttpServlet {

    @Override
    public void service(HttpRequest request, HttpResponse response) {
        ServletManager manager = request.getServletManager();

        LibraryService libraryService = manager.getLibraryService();
        SessionManager sessionManager = manager.getSessionManager();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || password == null) {
            renderLoginForm(response, null);
            return;
        }

        User user = libraryService.login(username, password);

        if (user != null) {
            String sessionId = sessionManager.createSession(user);

            if (user instanceof Admin) {
                response.sendDirect("/admin?sessionId=" + sessionId);
                return;
            }

            if (user instanceof RegularUser) {
                response.sendDirect("/user?sessionId=" + sessionId);
                return;
            }

            response.sendDirect("/login");
            return;
        }

        renderLoginForm(response, "로그인 실패: 잘못된 아이디/비밀번호");
    }

    private void renderLoginForm(HttpResponse response, String errorMessage) {

        if (errorMessage != null) {
            response.writeBody("<p style='color:red;'>" + errorMessage + "</p>");
        }

        response.writeBody("<h1>로그인</h1>");
        response.writeBody("""
                <form action='/login' method='GET'>
                    <label>아이디:</label><br>
                    <input type='text' name='username'><br><br>
                
                    <label>비밀번호:</label><br>
                    <input type='password' name='password'><br><br>
                
                    <button type='submit'>로그인</button>
                </form>
                <br>
                """);
        response.writeBody("<a href='/'>홈으로</a>");
    }
}

