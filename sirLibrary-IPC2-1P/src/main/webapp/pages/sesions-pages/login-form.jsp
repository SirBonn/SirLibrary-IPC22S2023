

<form class="formularioLogin needs-validation" id="formularioLogin" method="post" name="user-login" action="/sirLibrary-IPC2-1P/loginServlet"> 
    <div class="form-outline form-white mb-4">
        <input type="text" id="emailAddress" name="emailAddress"class="form-control form-control-lg" />
        <label class="form-label" for="emailAddress">Email</label>
    </div>

    <div class="form-outline form-white mb-4">
        <input type="password" id="userpassword" name="userpassword" class="form-control form-control-lg" />
        <label class="form-label" for="userpassword">Password</label>
    </div>

    <p class="small mb-5 pb-lg-2"><a class="text-white-50" href="#!">Forgot password?</a></p>

    <button class="btn btn-outline-light btn-lg px-5" type="submit" >Login</button>
    <input  class="btn btn-outline-light btn-lg px-5" type="button" value="Cancelar" id="Cancelar" class="btn btn-outline-dark" onclick='location.href = location = "index.jsp"'');>
</form>