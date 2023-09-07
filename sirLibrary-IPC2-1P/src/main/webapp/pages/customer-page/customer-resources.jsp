
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

 