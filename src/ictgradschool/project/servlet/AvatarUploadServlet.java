package ictgradschool.project.servlet;

import ictgradschool.project.util.ServletUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet("/avatar-upload")
public class AvatarUploadServlet extends HttpServlet {

    private File uploadsFolder; // The folder where article images should be uploaded
    private File tempFolder; // The temp folder required by the file-upload logic

    @Override
    public void init() throws ServletException {
        super.init();

        System.out.println("mark 1");

        // Get the upload folder, ensure it exists.
        uploadsFolder = new File(getServletContext().getRealPath("/assets/images"));
        if (!uploadsFolder.exists()) {
            uploadsFolder.mkdirs();
        }
        // Create the temporary folder that the file-upload mechanism needs.
        tempFolder = new File(getServletContext().getRealPath("/WEB-INF/temp"));
        if (!tempFolder.exists()) {
            tempFolder.mkdirs();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletUtil.forward(req, resp, getServletContext(), "avatar-upload.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Set up file upload mechanism
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(tempFolder);
        ServletFileUpload upload = new ServletFileUpload(factory);

        List<FileItem> fileItems = null;
        try {
            fileItems = upload.parseRequest(req);
            for (FileItem fileItem : fileItems) {
                System.out.println("mark 2");
                System.out.println(fileItem.getFieldName());
                System.out.println(fileItem.getName());
                File imageFile = new File(uploadsFolder, fileItem.getName());
                fileItem.write(imageFile);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }

        resp.sendRedirect("./avatar-upload");
    }
}
