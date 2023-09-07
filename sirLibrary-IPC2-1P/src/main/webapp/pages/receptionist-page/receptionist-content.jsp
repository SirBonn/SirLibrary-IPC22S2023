<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="content">           
    <jsp:include page="receptionist-resources.jsp"/>

    <!-- Inicio Reqs list-->
    <div id="pendingReqs" style="display: none;">

        <div class="container-fluid row">
            <div class="col-sm-9 col-md-6 col-lg-8 col-xl-10">
                <div class="card">
                    <div class="card-header">
                        <h4>Solicitudes Pendientes</h4>
                    </div>
                    <table class="table table-striped ">

                        <thead class="thead-light">
                            <tr>

                                <th>Code</th>
                                <th>Estado</th>
                                <th>Tipo</th>   
                                <th>Libro</th>
                                <th>Fecha de solicitud</th>      
                                <th>Dias solicitados</th>       
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach var="req" items="${pendingReqs}">
                                <tr>
                                    <td>${req.loanReq.code}</td>
                                    <td>Solicitado</td>

                                    <c:choose>
                                        <c:when test="${req.typeRequest == 1}" >
                                            <td>Recoger en Libreria</td>
                                        </c:when>
                                        <c:when test="${req.typeRequest == 2}" >
                                            <td>Servicio a domicilio</td>
                                        </c:when>
                                    </c:choose>

                                    <td>${req.loanReq.book.tittle}</td>
                                    <td>${req.loanReq.reqDate}</td>
                                    <td>${req.retrievedDays}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/receptionistServices?action=acceptReq&code=${req.loanReq.code}"
                                           class="btn btn-secondary">Aceptar</a>
                                    </td><td>
                                        <a href="${pageContext.request.contextPath}/receptionistServices?action=rejectdReq&code=${req.loanReq.code}"
                                           class="btn btn-secondary">Rechazar</a>
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

    <!-- Inicio activeReqs list-->
    <div id="aceptedReqs" style="display: none;">

        <div class="container-fluid row">
            <div class="col-sm-9 col-md-6 col-lg-8 col-xl-10">
                <div class="card">
                    <div class="card-header">
                        <h4>Solicitudes Aceptadas</h4>
                    </div>
                    <table class="table table-striped ">

                        <thead class="thead-light">
                            <tr>

                                <th>Code</th>
                                <th>Estado</th>
                                <th>Tipo</th>     
                                <th>Fecha de solicitud</th>      
                                <th>Dias Solicitados</th>   

                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach var="req" items="${aceptedReqs}">
                                <tr>
                                    <td>${req.loanReq.code}</td>

                                    <td>Aceptada</td>
                                    <c:choose>
                                        <c:when test="${req.typeRequest == 1}" >
                                            <td>Recoger en Libreria</td>
                                        </c:when>
                                        <c:when test="${req.typeRequest == 2}" >
                                            <td>Servicio a domicilio</td>
                                        </c:when>
                                    </c:choose>
                                    <td>${req.loanReq.reqDate}</td>
                                    <td>${req.retrievedDays}</td>
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

    <!-- Inicio retrievedReqs list-->
    <div id="rejectedReqs" style="display: none;">

        <div class="container-fluid row">
            <div class="col-sm-9 col-md-6 col-lg-8 col-xl-10">
                <div class="card">
                    <div class="card-header">
                        <h4>Solicitudes Rechazadas</h4>
                    </div>
                    <table class="table table-striped ">

                        <thead class="thead-light">
                            <tr>

                                <th>Code</th>
                                <th>Estado</th>
                                <th>Tipo</th>     
                                <th>Fecha de solicitud</th>      
                                <th>Dias solicitados</th>       

                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach var="req" items="${rejectedReqs}">
                                <tr>
                                    <td>${req.loanReq.code}</td>

                                    <td>Rechazado</td>
                                    <c:choose>
                                        <c:when test="${req.typeRequest == 1}" >
                                            <td>Recoger en Libreria</td>
                                        </c:when>
                                        <c:when test="${req.typeRequest == 2}" >
                                            <td>Servicio a domicilio</td>
                                        </c:when>
                                    </c:choose>
                                    <td>${req.loanReq.reqDate}</td>
                                    <td>${req.retrievedDays}</td>
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
    <div id="booksList">
        <div class="container-fluid row">
            <div class="col-sm-9 col-md-6 col-lg-8 col-xl-10">
                <div class="card">
                    <div class="card-header">
                        <h4>Libros en ${sessionScope.userLogged.library.alias}</h4>
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

    <!-- Inicio activeLoans list-->
    <div id="activeLoans" style="display: none;">
        <div class="container-fluid row">
            <div class="col-sm-9 col-md-6 col-lg-8 col-xl-10">
                <div class="card">
                    <div class="card-header">
                        <h4>Prestamos activos</h4>
                    </div>
                    <table class="table table-striped ">

                        <thead class="head-light">
                            <tr>
                                <th>Codigo</th>
                                <th>Libro</th>
                                <th>Estado</th>     
                                <th>Usuario</th>
                                <th>Fecha de prestamo</th>     
                                <th>Fecha de devolucion</th>     
                                <th></th>
                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach var="loanDet" items="${activeLoans}">
                                <tr>
                                    <td>${loanDet.loan.code}</td>
                                    <td>${loanDet.loan.book.tittle}</td>
                                    <td>${loanDet.loan.state}</td>
                                    <td>${loanDet.user.username}</td>
                                    <td>${loanDet.loan.loanDate}</td>
                                    <td>${loanDet.retrievedDate}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/receptionistServices?action=finishLoan&code=${loanDet.loan.code}"
                                           class="btn btn-secondary">Finalizar</a>
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

    <!-- Inicio retrievedLoans list-->
    <div id="retrievedLoans" style="display: none;">
        <div class="container-fluid row">
            <div class="col-sm-9 col-md-6 col-lg-8 col-xl-10">
                <div class="card">
                    <div class="card-header">
                        <h4>Prestamos Entregados</h4>
                    </div>
                    <table class="table table-striped ">

                        <thead class="head-light">
                            <tr>
                                <th>Codigo</th>
                                <th>Libro</th>
                                <th>Estado</th>     
                                <th>Usuario</th>
                                <th>Finalizador</th>
                                <th>Fecha de prestamo</th>     
                                <th>Fecha de devolucion</th>     
                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach var="loanDet" items="${retrievedLoans}">
                                <tr>
                                    
                                    <td>${loanDet.loan.code}</td>
                                    <td>${loanDet.loan.book.tittle}</td>
                                    <td>${loanDet.loan.state}</td>
                                    <td>${loanDet.user.username}</td>
                                    <td>${loanDet.finisher.username}</td>
                                    <td>${loanDet.loan.loanDate}</td>
                                    <td>${loanDet.retrievedDate}</td>

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

        <h1>Contenido</h1>
        <button class="btn btn-primary">Botón</button>


    </div>

</div>