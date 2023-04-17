<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Information System</title>
    <link href="../assets/lib/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">

    <link href="../assets/css/allpage.css" rel="stylesheet" type="text/css">
    <link href="../assets/css/fonts/fontawesome-5/css/all.css" rel="stylesheet" type="text/css">
    <link href="../assets/css/form.css" rel="stylesheet" type="text/css">
    <link href="../assets/css/entreprise.css" rel="stylesheet" type="text/css">

    <link href="../assets/input-file-master/input-file.css" rel="stylesheet" type="text/css">
</head>

<body>

<%      //------------ Header INCLUDE -------------
    RequestDispatcher footerdispatcher = request.getRequestDispatcher("../inc/entreprise/entrepriseNav.jsp");
    footerdispatcher.include(request, response);
%>

<%
    String includePage = request.getParameter("includePage");
    if(includePage == null){
        includePage = "entrepriseHome";
    }
    RequestDispatcher dispatcher = request.getRequestDispatcher("../inc/entreprise/"+includePage+".jsp");
    dispatcher.include(request, response);
%>

<jsp:include page="../inc/footer.jsp"/>

<script src="../assets/js/jquery.min.js"></script>
<script src="../assets/lib/bootstrap/bootstrap.bundle.js"></script>

<script src="../assets/js/themes.js"></script>
<script src="../assets/js/suppressionPC.js"></script>
<script src="../assets/js/modificationPC.js"></script>

</body>
</html>
