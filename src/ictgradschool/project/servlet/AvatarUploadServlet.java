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
import java.util.Optional;

@WebServlet("/avatar-upload")
public class AvatarUploadServlet extends HttpServlet {

    private File uploadsFolder; // The folder where article images should be uploaded
    private File tempFolder; // The temp folder required by the file-upload logic

    @Override
    public void init() throws ServletException {
        super.init();

        System.out.println("mark 1");
        initUpload();
    }

    private void initUpload() {
        // Get the upload folder, ensure it exists.
        uploadsFolder = new File(getServletContext().getRealPath("/avatar"));
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
        ServletFileUpload upload = getUpload();

        try {
            List<FileItem> fileItems = upload.parseRequest(req);
            Optional<FileItem> fileItemOptional = fileItems.stream()
                    .filter(fileItem -> fileItem.getFieldName().equals("avatar"))
                    .findFirst();
            FileItem fileItem = fileItemOptional.get();
            String name = upload(fileItem);

            resp.sendRedirect("./avatar-upload");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private String upload(FileItem fileItem) throws Exception {
        String name = fileItem.getName();
        File imageFile = new File(uploadsFolder, name);
        while (imageFile.exists()) {
            // TODO: fine grain the name management
            name = imageFile.getName() + "-2";
            imageFile = new File(uploadsFolder, name);
        }
        fileItem.write(imageFile);
        return name;
    }

    private ServletFileUpload getUpload() {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(tempFolder);
        return new ServletFileUpload(factory);
    }
}

