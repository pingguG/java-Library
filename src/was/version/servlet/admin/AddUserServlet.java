package was.version.servlet.admin;

import Library.LibraryService;
import model.User;
import was.httpserver.*;

public class AddUserServlet implements HttpServlet {

    @Override
    public void service(HttpRequest request, HttpResponse response) {

        ServletManager manager = request.getServletManager();

        LibraryService libraryService = manager.getLibraryService();
        SessionManager sessionManager = manager.getSessionManager();

        String sid = request.getParameter("sessionId");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String type = request.getParameter("type");

        User user = sessionManager.getUser(sid);

        if (username == null || password == null || type == null) {
            renderForm(response, sid);
            return;
        }

        boolean result = libraryService.addUser(user, username, password, type);

        if (result) {
            response.writeBody("<p>유저 생성 성공!</p>");
        } else {
            response.writeBody("<p>유저 생성 실패!</p>");
        }

        response.writeBody("<a href='/admin?sessionId=" + sid + "'>뒤로가기</a>");
    }

    private void renderForm(HttpResponse response, String sid) {

        response.writeBody("<h2>유저 생성</h2>");
        response.writeBody("<form action='/addUser' method='GET'>");
        response.writeBody("<input type='hidden' name='sessionId' value='" + sid + "'>");
        response.writeBody("""
                    <label>아이디:</label><br>
                    <input type='text' name='username'><br><br>
                
                    <label>비밀번호:</label><br>
                    <input type='password' name='password'><br><br>
                
                    <label>유형:</label><br>
                    1: 관리자<br>
                    2: 일반 사용자<br>
                    <input type='text' name='type'><br><br>
                
                    <button type='submit'>생성</button>
                </form>
                <br>
                """);
        response.writeBody("<a href='/admin?sessionId=" + sid + "'>관리자 메뉴로</a>");
    }
}
