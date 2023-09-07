<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form class="formularioSignin needs-validation" id="formularioSignin" method="post" name="user-singin" action="${pageContext.request.contextPath}/signInServlet">

    <div class="row">
        
        <div class="col-md-6 mb-4">
            <div class="form-outline">
                <input type="text" id="forename" name="forename" class="form-control" required/>
                <label class="form-label" for="forename">Forename</label>
            </div>
        </div>
        
        <div class="col-md-6 mb-4">
            <div class="form-outline">
                <input type="text" id="username" name="username" class="form-control" required/>
                <label class="form-label" for="username">Username</label>
            </div>
        </div>
        
    </div>

    <div class="form-outline mb-4">
        <input type="email" id="emailAddress" name="emailAddress" class="form-control" required/>
        <label class="form-label" for="emailAddress">Email address</label>
    </div>

    <div class="form-outline mb-4">
        <input type="password" id="userpassword" name="userpassword" class="form-control" required minlength="8"/>
        <label class="form-label" for="userpassword">Password</label>
    </div>

    <button type="submit" class="btn btn-outline-light btn-lg px-5">
        Sign up
    </button>
    <input  class="btn btn-outline-light btn-lg px-5" type="button" value="Cancelar" id="Cancelar" class="btn btn-outline-dark" onclick='location.href = location = "index.jsp"'');>

</form>