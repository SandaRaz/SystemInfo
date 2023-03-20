package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@WebServlet(name = "MereController", value = "*.MereController")
public class MereController extends HttpServlet {

    public RequestDispatcher dispat;
    public HttpServletRequest request;
    public HttpServletResponse response;

    protected void processResquest() throws IOException {
        PrintWriter out = response.getWriter();
        this.test(this, request.getRequestURI());
    }
    public void redirect(String indexFile) throws ServletException, IOException {
        dispat = request.getRequestDispatcher(indexFile);
        dispat.forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.request = request;
        this.response=response;
        processResquest();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.request = request;
        this.response=response;
        processResquest();
    }

    public String urlUtile(String URL) {
//        if(URL.contains("_war")){
//            System.out.println("'war' artifact detected");
//        }
        String[] urlsplitted = URL.split("/");
        String target = urlsplitted[urlsplitted.length-1];
        //System.out.println("Target >>> "+target);

        return target;
    }

    public String getController(String URL) {
        String controller = urlUtile(URL).split("\\.")[1];
        //System.out.println("controller >>> "+controller);

        return controller;
    }
    public String getMethodName(String URL) {
        String methodName = urlUtile(URL).split("."+ getController(URL))[0];
        //System.out.println("methodName >>> "+methodName);

        return methodName;
    }

    public void test(Object cls , String URL) {
        Method[] methods = cls.getClass().getDeclaredMethods();
        for(Method M : methods) {
            Annotation annotation = M.getAnnotation(CtrlAnnotation.class);
            CtrlAnnotation ctrl = (CtrlAnnotation) annotation;
            if(ctrl!=null && ctrl.name().equals(getMethodName(URL))) {
                try {
                    M.invoke(cls);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            }
        }
    }
}
