package controller;

import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import themes.Themes;

import java.io.File;
import java.io.IOException;

@WebServlet(name = "Assets", value = "*.Assets")
public class Assets extends MereController{

    @CtrlAnnotation(name = "loadTheme")
    public void loadTheme() throws IOException {
        String path = getServletContext().getRealPath("/WEB-INF/Themes");
        File current = new File(path+"/current");

        Themes theme = Themes.getCurrentTheme(path);

        Gson gson = new Gson();
        String json = gson.toJson(theme);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    @CtrlAnnotation(name = "changeTheme")
    public void changeTheme() throws IOException {
        String path = getServletContext().getRealPath("/WEB-INF/Themes");
        File current = new File(path+"/current");

        Themes theme = Themes.getCurrentTheme(path);
        System.out.println(theme.getCouleurPolice()+" equals dimgrey:"+theme.getCouleurPolice().equals("dimgrey"));

        if(theme.getCouleurPolice().equals("black")){ // Police black donc deja un LightTheme
            System.out.println("Override current by a dark theme");
            Themes.BlackTheme(current);
        }
        if(theme.getCouleurPolice().equals("dimgrey")){
            System.out.println("Override current by a light theme");
            Themes.LightTheme(current);
        }
        theme = Themes.getCurrentTheme(path);
        System.out.println(theme.getCouleurMenu());

        Gson gson = new Gson();
        String json = gson.toJson(theme);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
