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
<div class="modal fade" id="addUser" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">New message</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>

            <form class="formularioSignin needs-validation" id="formularioSignin" method="post" name="user-singin" action="${pageContext.request.contextPath}/adminServices?action=addUser">
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
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-6 ">
                                <label for="nombre" class="form-label">Tipo de Usuario</label>
                                <select type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" id="tipoUsuario" name="tipoUsuario" required>
                                    <option>Selecciona</option>
                                    <option value="1">Recepcionista</option>
                                    <option value="2">Repartidor</option>
                                    <option value="3">Nuevo usuario</option>
                                </select>
                            </div>
                        </div>
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

<!-- Carga de archivos -->

<div class="modal fade" id="loadFiles" role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-info text-white">
                <h5 class="modal-tittle">Importar archivo</h5>
                <button class="close" data-dismiss="modal">
                    <span>&times;</span>
                </button>
            </div>
            <div >
                <form class="" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/adminServices?action=loadFile"> 
                    <div class="modal-body">
                        <input type="file" class="form-control-file border" id="JSONfile" name="JSONfile" required>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-primary" type="submit"> Cargar</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- fin de carga de archivos -->


<!-- agregar libros -->
<div class="modal fade" id="addBook" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">New message</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>

            <form class="formularioSignin needs-validation" id="formularioSignin" method="post" name="user-singin" action="${pageContext.request.contextPath}/adminServices?action=addBook">
                <div class="modal-body">

                    <div class="row">
                        <div class="col-md-6 mb-4">
                            <div class="form-outline">
                                <input type="text" id="tittle" name="tittle" class="form-control" required/>
                                <label class="form-label" for="tittle">Tittle</label>
                            </div>
                        </div>
                        <div class="col-md-6 mb-4">
                            <div class="form-outline">
                                <input type="number" id="isbn" name="isbn" class="form-control" required/>
                                <label class="form-label" for="isbn">ISBN</label>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 mb-4">
                            <div class="form-outline">
                                <input type="number" step="0.01"  id="cost" name="cost" class="form-control" required/>
                                <label class="form-label" for="cost">Costo</label>
                            </div>
                        </div>
                        <div class="col-md-6 mb-4">
                            <div class="form-outline">
                                <input type="text" id="author" name="author" class="form-control" required/>
                                <label class="form-label" for="author">Author</label>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-3 mb-4">
                            <div class="form-outline">
                                <input type="number" id="units" name="units" class="form-control" required/>
                                <label class="form-label" for="units">Unidades</label>
                            </div>
                        </div>
                        <div class="col-md-4 mb-4">
                            <select type="button" class="btn btn-outline-secondary dropdown-toggle" data-toggle="dropdown" id="library" name="library" required>
                                <%--listado de librerias--%>
                                <option>libreria</option>
                                <c:forEach var="lib" items="${libraries}">
                                    <tr>
                                    <option value ="${lib.code}">${lib.alias}</option>
                                    </tr>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-sm-3 mb-4">
                            <select type="button" class="btn btn-outline-secondary dropdown-toggle" data-toggle="dropdown" id="category" name="category" required>
                                <%--listado de categorias--%>
                                <option>Categoria</option>
                                <c:forEach var="categ" items="${categories}">
                                    <tr>
                                    <option value ="${categ.code}">${categ.name}</option>
                                    </tr>
                                </c:forEach>
                            </select>
                        </div>
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
