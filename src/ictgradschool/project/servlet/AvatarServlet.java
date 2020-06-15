package ictgradschool.project.servlet;

import ictgradschool.project.controller.AvatarController;
import ictgradschool.project.entity.User;
import ictgradschool.project.repository.AvatarDao;
import ictgradschool.project.repository.UserDao;
import ictgradschool.project.util.ServletUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
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

@WebServlet("/avatar")
public class AvatarServlet extends HttpServlet {

    private File uploadsFolder; // The folder where article images should be uploaded
    private File tempFolder; // The temp folder required by the file-upload logic

    @Override
    public void init() throws ServletException {
        super.init();
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
        AvatarDao avatarDao = new AvatarDao();
        List<String> predefinedAvatarNames = avatarDao.getPredefinedAvatarNames();
        req.setAttribute("predefinedAvatarNames", predefinedAvatarNames);
        ServletUtil.forward(req, resp, getServletContext(), "avatar.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        UserDao userDao = new UserDao();
        AvatarDao avatarDao = new AvatarDao();
        AvatarController avatarController = new AvatarController(user, userDao, avatarDao);

        FileItem fileItem = getFileItem(req);
        String name = upload(fileItem);

        user = avatarController.updateAvatar(name);

        req.getSession().setAttribute("user", user);
        resp.sendRedirect("./avatar");
    }

    private FileItem getFileItem(HttpServletRequest req) {
        ServletFileUpload upload = getUpload();
        List<FileItem> fileItems = null;
        try {
            fileItems = upload.parseRequest(req);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        Optional<FileItem> fileItemOptional = fileItems.stream()
                .filter(fileItem -> fileItem.getFieldName().equals("avatar"))
                .findFirst();
        return fileItemOptional.get();
    }

    private String upload(FileItem fileItem) {
        String name = fileItem.getName();
        File imageFile = new File(uploadsFolder, name);
        while (imageFile.exists()) {
            // TODO: fine grain the name management
            name = imageFile.getName() + "-2";
            imageFile = new File(uploadsFolder, name);
        }
        try {
            fileItem.write(imageFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }

    private ServletFileUpload getUpload() {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(tempFolder);
        return new ServletFileUpload(factory);
    }
}

