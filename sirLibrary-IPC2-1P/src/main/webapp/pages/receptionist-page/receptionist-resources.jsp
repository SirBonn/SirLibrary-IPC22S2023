<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>
    function mostrarFormulario(formularioId) {
        var formularioN = $('#' + formularioId);

        if (formularioN.is(":visible")) {
            formularioN.hide();
        } else {

            $('.formulario').hide();
            formularioN.show();
        }
    }

</script>

<!-- agregar usuarios -->
<div class="modal fade" id="addCustomer" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Nuevo Cliente</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>

            <form class="formularioSignin needs-validation" id="formularioSignin" method="post" name="user-singin" action="${pageContext.request.contextPath}/receptionistServices?action=addUser">
                <div class="modal-body">

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
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary"> Agregar </button>
                </div>
            </form>
        </div>
    </div>
</div>