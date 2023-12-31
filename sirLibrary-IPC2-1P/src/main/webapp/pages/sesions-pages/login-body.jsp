<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section class="vh-100 gradient-custom">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card bg-dark text-white" style="border-radius: 1rem;">
                    <div class="card-body p-5 text-center">

                        <div class="mb-md-5 mt-md-4 pb-5">

                            <h2 class="fw-bold mb-2 text-uppercase">Login</h2>
                            <p class="text-white-50 mb-5">Please enter your login and password!</p>

                            <jsp:include page="login-form.jsp"/>

                        </div>
                        <div>
                            <div>
                                <c:if test="${errorMessage != null}"> 
                                    <strong> ${errorMessage}</strong>
                                </c:if>
                            </div>
                        </div>

                        <div>
                            <p class="mb-0">Don't have an account? <a href="signin.jsp" class="text-white-50 fw-bold">Sign Up</a>
                            </p>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</section>



