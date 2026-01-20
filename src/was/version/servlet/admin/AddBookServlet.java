package was.version.servlet.admin;

import Library.LibraryService;
import model.*;
import was.httpserver.*;

public class AddBookServlet implements HttpServlet {

    @Override
    public void service(HttpRequest request, HttpResponse response) {
        ServletManager manager = request.getServletManager();

        LibraryService libraryService = manager.getLibraryService();
        SessionManager sessionManager = manager.getSessionManager();

        String sid = request.getParameter("sessionId");
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String type = request.getParameter("type");

        User user = sessionManager.getUser(sid);

        if (title == null || author == null || type == null) {
            renderForm(response, sid);
            return;
        }

        boolean result = libraryService.addBook(user, title, author, type);
        if (result) {
            response.writeBody("<p>책 추가 성공!</p>");
        } else {
            response.writeBody("<p>책 추가 실패!</p>");
        }

        response.writeBody("<a href='/admin?sessionId=" + sid + "'>뒤로가기</a>");
    }

    private void renderForm(HttpResponse response, String sid) {

        response.writeBody("<h2>책 추가</h2>");
        response.writeBody("<form action='/addBook' method='GET'>");
        response.writeBody("<input type='hidden' name='sessionId' value='" + sid + "'>");
        response.writeBody("""
                    <label>제목:</label><br>
                    <input type='text' name='title'><br><br>
                
                    <label>저자:</label><br>
                    <input type='text' name='author'><br><br>
                
                    <label>종류:</label><br>
                    1: 인쇄본<br>
                    2: eBook<br>
                    <input type='text' name='type'><br><br>
                
                    <button type='submit'>추가</button>
                </form>
                <br>
                """);
        response.writeBody("<a href='/admin?sessionId=" + sid + "'>관리자 메뉴로</a>");
    }
}
