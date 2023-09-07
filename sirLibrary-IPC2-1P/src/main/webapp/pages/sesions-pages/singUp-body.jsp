<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<section class="vh-100 gradient-custom">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card bg-dark text-white" style="border-radius: 1rem;">
                    <div class="card-body p-5 text-center">

                        <div class="mb-md-5 mt-md-4 pb-5">

                            <h2 class="fw-bold mb-5">Sign up now</h2>
                            <jsp:include page="singup-form.jsp"/>

                        </div>
                        <div>
                            <c:if test="${isOk == false}"> 
                                <strong> ${errorMessage}</strong>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>