<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Information System</title>
    <link href="./assets/css/allpage.css" rel="stylesheet" type="text/css">
    <link href="./assets/css/fonts/fontawesome-5/css/all.css" rel="stylesheet" type="text/css">
    <link href="./assets/css/form.css" rel="stylesheet" type="text/css">
    <link href="./assets/css/entreprise.css" rel="stylesheet" type="text/css">

    <script src="./assets/js/jquery.min.js"></script>
    <script src="./assets/js/themes.js" rel="script" type="text/javascript"></script>
</head>

<body>
    <%      //------------ Header INCLUDE -------------
        RequestDispatcher footerdispatcher = request.getRequestDispatcher("./inc/navigation.jsp");
        footerdispatcher.include(request, response);
    %>

    <%
        String includePage = request.getParameter("includePage");
        if(includePage == null){
            includePage = "home";
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("./inc/"+includePage+".jsp");
        dispatcher.include(request, response);
    %>

    <jsp:include page="./inc/footer.jsp"/>

    <script src="./assets/js/trait.js" rel="script" type="text/javascript"></script>
</body>
</html>