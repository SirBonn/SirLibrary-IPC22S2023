
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../admin-page/admin-resources.jsp"/>


<div class="content">           


    <c:if test="${editUser != null}">
        <form action="${pageContext.request.contextPath}/adminServices?action=editUser&code=${editUser.code}&lvlAcces=${editUser.levelUser}" 
              method="POST">

            <secion id="formEdit" >
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <div class="card ">
                                <div class="card-header">
                                    <h4>Editar Cliente</h4>
                                </div>
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-md-6 mb-4">
                                            <div class="form-outline">
                                                <input type="text" id="forename" name="forename" class="form-control" required value="${editUser.forename}"/>
                                                <label class="form-label" for="forename">Forename</label>
                                            </div>
                                        </div>
                                        <div class="col-md-6 mb-4">
                                            <div class="form-outline">
                                                <input type="text" id="username" name="username" class="form-control" required value="${editUser.username}"/>
                                                <label class="form-label" for="username">Username</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6 mb-4">
                                            <div class="form-outline mb-4">
                                                <input type="email" id="emailAddress" name="emailAddress" readonly class="form-control" required value="${editUser.email}"/>
                                                <label class="form-label" for="emailAddress">Email address</label>
                                            </div>
                                        </div>
                                    </div>
                                    <c:if test="${editUser.levelUser == 3}">
                                        <div class="row">
                                            <div class="col-md-6 mb-4">
                                                <div class="form-outline">
                                                    <input type="number" id="maxBooks" name="maxBooks" class="form-control" required value="${editUser.maxBooks}"/>
                                                    <label class="form-label" for="maxBooks">Maximo de libros</label>
                                                </div>
                                            </div>
                                            <div class="col-md-6 mb-4">
                                                <div class="form-outline">
                                                    <input type="number" id="amount" name="amount" class="form-control" required value="${editUser.amount}"/>
                                                    <label class="form-label" for="amount">Amount</label>
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>
                                    <c:if test="${editUser.levelUser == 2}">
                                        <div class="row">
                                            <div class="col-md-6 mb-4">
                                                <div class="form-outline">
                                                    <input type="number" id="deliveries" name="deliveries" class="form-control" required value="${editUser.deliveries}"/>
                                                    <label class="form-label" for="deliveries">Entregas Realizadas</label>
                                                </div>
                                            </div>

                                        </div>
                                    </c:if>
                                    <c:if test="${editUser.levelUser == 1}">
                                        <div class="row">
                                            <div class="col-md-6 mb-4">
                                                <div class="form-outline">
                                                    <input type="number" id="Libreria" name="Libreria" class="form-control" required value="${editUser.libraryCode}"/>
                                                    <label class="form-label" for="maxBooks">Libreria</label>
                                                </div>
                                            </div>

                                        </div>
                                    </c:if>

                                </div>
                                <hr class="dropdown-divider">

                                <section id="actions  ">
                                    <div class="container col-md-6 mb-4">
                                        <div class="row">
                                            <div class="col-xl-4">
                                                <a href="${pageContext.request.contextPath}/adminServices" class="btn btn-light btn-block">
                                                    Cancelar
                                                </a>
                                            </div>
                                            <div class="col-xl-4">
                                                <button type="submit" class="btn btn-success btn-block">
                                                    Guardar Cambios
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </section>
                            </div>
                        </div>
                    </div>
                </div>
            </secion>
        </form>
        <hr class="dropdown-divider">

    </c:if>

    <!-- Inicio customer list-->
    <div id="customersList" style="display: none;">

        <div class="container-fluid row">
            <div class="col-sm-9 col-md-6 col-lg-8 col-xl-10">
                <div class="card">
                    <div class="card-header">
                        <h4>Clientes</h4>
                    </div>
                    <table class="table table-striped ">

                        <thead class="thead-light">
                            <tr>

                                <th>Code</th>
                                <th>Nombre</th>
                                <th>Email</th>     
                                <th>Maximo</th>       
                                <th></th>


                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach var="user" items="${customers}">
                                <tr>
                                    <td>${user.code}</td>
                                    <td>${user.username}</td>
                                    <td>${user.email}</td>
                                    <td>${user.maxBooks}</td>

                                    <td><a href="${pageContext.request.contextPath}/adminServices?action=editUser&code=${user.code}&lvlAcces=${user.levelUser}"
                                           class="btn btn-secondary">Editar</a>
                                        <c:if test="${user.status != null}">
                                            <a href="${pageContext.request.contextPath}/adminServices?action=disableUser&code=${user.code}&lvlAcces=${user.levelUser}" 
                                               class="btn btn-danger">Deshabilitar</a>
                                        </c:if>
                                        <c:if test="${user.status == null}">
                                            <a href="${pageContext.request.contextPath}/adminServices?action=enableUser&code=${user.code}&lvlAcces=${user.levelUser}" 
                                               class="btn btn-success">Habilitar</a>
                                        </c:if>
                                    </td>

                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <hr class="dropdown-divider">
    </div>
    <!-- fin listado-->

    <!-- Inicio receptionists list-->
    <div id="receptionistsList" style="display: none;">

        <div class="container-fluid row">
            <div class="col-sm-9 col-md-6 col-lg-8 col-xl-10">
                <div class="card">
                    <div class="card-header">
                        <h4>Recepcionistas</h4>
                    </div>
                    <table class="table table-striped ">

                        <thead class="thead-light">
                            <tr>

                                <th>Code</th>
                                <th>Nombre</th>
                                <th>Email</th>     
                                <th>Libreria</th>
                                <th></th>


                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach var="user" items="${receptionists}">
                                <tr>
                                    <td>${user.code}</td>
                                    <td>${user.username}</td>
                                    <td>${user.email}</td>
                                    <td>${user.library.code}</td>

                                    <td><a href="${pageContext.request.contextPath}/adminServices?action=editUser&code=${user.code}&lvlAcces=${user.levelUser}"
                                           class="btn btn-secondary">Editar</a>
                                        <c:if test="${user.status != null}">
                                            <a href="${pageContext.request.contextPath}/adminServices?action=disableUser&code=${user.code}&lvlAcces=${user.levelUser}" 
                                               class="btn btn-danger">Deshabilitar</a>
                                        </c:if>
                                        <c:if test="${user.status == null}">
                                            <a href="${pageContext.request.contextPath}/adminServices?action=enableUser&code=${user.code}&lvlAcces=${user.levelUser}" 
                                               class="btn btn-success">Habilitar</a>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <hr class="dropdown-divider">

    </div>
    <!-- fin listado-->  

    <!-- Inicio dealers list-->
    <div id="dealersList" style="display: none;">

        <div class="container-fluid row">
            <div class="col-sm-9 col-md-6 col-lg-8 col-xl-10">
                <div class="card">
                    <div class="card-header">
                        <h4>Repartidores</h4>
                    </div>
                    <table class="table table-striped ">

                        <thead class="head-light">
                            <tr>

                                <th>Code</th>
                                <th>Nombre</th>
                                <th>Email</th>     
                                <th>Entregas</th>
                                <th></th>


                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach var="user" items="${dealers}">
                                <tr>
                                    <td>${user.code}</td>
                                    <td>${user.username}</td>
                                    <td>${user.email}</td>
                                    <td>${user.deliveries}</td>

                                    <td><a href="${pageContext.request.contextPath}/adminServices?action=editUser&code=${user.code}&lvlAcces=${user.levelUser}"
                                           class="btn btn-secondary">Editar</a>
                                        <c:if test="${user.status != null}">
                                            <a href="${pageContext.request.contextPath}/adminServices?action=disableUser&code=${user.code}&lvlAcces=${user.levelUser}" 
                                               class="btn btn-danger">Deshabilitar</a>
                                        </c:if>
                                        <c:if test="${user.status == null}">
                                            <a href="${pageContext.request.contextPath}/adminServices?action=enableUser&code=${user.code}&lvlAcces=${user.levelUser}" 
                                               class="btn btn-success">Habilitar</a>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <hr class="dropdown-divider">

    </div>
    <!-- fin listado-->  

    <!-- Inicio books list-->
    <div id="booksList" style="display: none;">

        <div class="container-fluid row">
            <div class="col-sm-9 col-md-6 col-lg-8 col-xl-10">
                <div class="card">
                    <div class="card-header">
                        <h4>Libros</h4>
                    </div>
                    <table class="table table-striped ">

                        <thead class="head-light">
                            <tr>

                                <th>ISBN</th>
                                <th>Titulo</th>
                                <th>Categoria</th>     
                                <th>Unidades</th>
                                <th>Tienda</th>     
                                <th>Direccion</th>     
                                <th></th>
                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach var="books" items="${bookList}">
                                <tr>
                                    <td>${books.book.isbn}</td>
                                    <td>${books.book.tittle}</td>
                                    <td>${books.book.category.name}</td>
                                    <td>${books.units}</td>
                                    <td>${books.library.alias}</td>
                                    <td>${books.library.direction}</td>
                                    <td>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <hr class="dropdown-divider">

    </div>
    <!-- fin listado-->  


    <c:if test="${sessionScope.message != null}">
        <div class="alert alert-warning d-flex align-items-center alert-dismissible fade show" role="alert">
            <strong>Holy guacamole! </strong> ${sessionScope.message}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    </c:if>
    <c:if test="${sessionScope.errorMessage != null}">
        <div class="alert alert-danger d-flex align-items-center alert-dismissible fade show" role="alert">
            <div>
                ${sessionScope.errorMessage}
            </div>
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>

        </div>
    </c:if>

    <div class="d-flex justify-content-between align-items-center">


    </div>

    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>

    <div class="d-flex justify-content-between align-items-center">
        <h1>Contenido ${editUser.username}</h1>
        <button class="btn btn-primary">Botón</button>
    </div>

</div>

