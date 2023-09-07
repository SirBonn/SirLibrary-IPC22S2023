
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="content">           
    <jsp:include page="customer-resources.jsp"/>


    <c:if test="${initLoan != null}">
        <form action="${pageContext.request.contextPath}/customerServices?action=sendReqLoan" 
              method="POST">

            <secion id="formEdit" class="mb-4" >
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <div class="card ">
                                <div class="card-header">
                                    <h4>Solicitar Prestamo</h4>
                                </div>
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-md-6 mb-4">
                                            <div class="form-outline">
                                                <input type="text" id="libro" name="libro" class="form-control" readonly required value="${initLoan.book.tittle}"/>
                                                <label class="form-label" for="libro">Libro</label>
                                            </div>
                                        </div>
                                        <div class="col-md-6 mb-4">
                                            <div class="form-outline">
                                                <input type="text" id="category" name="category" class="form-control" readonly required value="${initLoan.book.category.name}"/>
                                                <label class="form-label" for="category">Categoria</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6 mb-4">
                                            <div class="form-outline mb-4">
                                                <input type="email" id="library" name="library" readonly class="form-control" required value="${initLoan.library.alias}"/>
                                                <label class="form-label" for="library">Libreia</label>
                                            </div>
                                        </div>
                                        <div class="col-md-6 mb-4">
                                            <div class="form-outline mb-4">
                                                <select type="button" class="btn btn-outline-secondary dropdown-toggle" data-toggle="dropdown" id="retrievedDays" name="retrievedDays" required>
                                                    <option>Dias de prestamo</option>
                                                    <option value="1">1</option>
                                                    <option value="2">2</option>
                                                    <option value="3">3</option>
                                                    <option value="4">4</option>
                                                    <option value="5">5</option>
                                                    <option value="6">6</option>
                                                    <option value="7">7</option>
                                                    <option value="8">8</option>
                                                </select> Dias

                                            </div>
                                        </div>
                                    </div>
                                    <label class="form-label" >Tipo de entrega</label>

                                    <hr class="dropdown-divider">

                                    <div class="row">
                                        <div class="col-md-6 mb-4">
                                            <div class="form-outline">
                                                <select type="button" class="btn btn-outline-secondary dropdown-toggle" data-toggle="dropdown" id="type" name="type" required>
                                                    <option>Tipo de Entrega</option>
                                                    <option value="1">Recoger en libreria</option>
                                                    <option value="2">Entrega adomicilio</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-6 mb-4">
                                            <div class="form-outline">
                                                <input type="text" id="direction" name="direction" class="form-control" required/>
                                                <label class="form-label" for="direction">Direccion</label>
                                            </div>
                                        </div>
                                        <div style="display: none;">
                                            <div class="form-outline">
                                                <input type="text" id="code" name="code" readonly class="form-control" required value="${initLoan.code}"/>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                                <hr class="dropdown-divider">

                                <section id="actions  ">
                                    <div class="container col-md-6 mb-4">
                                        <div class="row">
                                            <div class="col-xl-4">
                                                <a href="${pageContext.request.contextPath}/customerServices" class="btn btn-light btn-block">
                                                    Cancelar
                                                </a>
                                            </div>
                                            <div class="col-xl-4">
                                                <button type="submit" class="btn btn-success btn-block">
                                                    Enviar Solicitud
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

    <!-- Inicio Reqs list-->
    <div id="customersList" style="display: none;">

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
                                <th>Fecha de devolucion</th>       

                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach var="req" items="${pendingReqs}">
                                <tr>
                                    <td>${req.loanReq.code}</td>



                                    <c:choose>
                                        <c:when test="${req.loanReq.reqState == 0}" >
                                            <td>Solicitado</td>
                                        </c:when>
                                        <c:when test="${req.loanReq.reqState == 1}" >                 
                                            <td>Aprobado</td>
                                        </c:when>
                                        <c:when test="${req.loanReq.reqState == 2}" >                 
                                            <td>Rechazado</td>
                                        </c:when>
                                    </c:choose>

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
    <div id="customersList" style="display: none;">

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
                                <th>Fecha de solicitud</th>      
                                <th>Fecha de devolucion</th>       

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
    <div id="customersList" style="display: none;">

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
                                <th>Fecha de solicitud</th>      
                                <th>Fecha de devolucion</th>       

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
                                    <td>
                                        <a href="${pageContext.request.contextPath}/customerServices?action=reqLoan&isbn=${books.book.isbn}&library=${books.library.code}"
                                           class="btn btn-secondary">Prestar</a>
                                    </td>
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

        <h1>Contenido</h1>
        <button class="btn btn-primary">Botón</button>


    </div>

</div>