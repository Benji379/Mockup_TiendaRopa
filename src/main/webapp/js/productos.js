document.getElementById("form").addEventListener("submit", function (event) {
    event.preventDefault(); // Evitar el envío del formulario por defecto

    // Obtener valores de los campos
    var nombreProducto = document.getElementById("nombreProducto").value;
    var precioProducto = document.getElementById("precioProducto").value;
    var descripcionProducto = document.getElementById("descripcionProducto").value;
    var imagen = document.getElementById("imagen").files[0];

    // Validar que todos los campos estén llenos
    if (!nombreProducto || !precioProducto || !descripcionProducto || !imagen) {
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'Todos los campos son obligatorios. Por favor, rellénalos.',
            confirmButtonColor: '#FF6363'
        });
        return;
    }

    // Crear objeto FormData para enviar datos del formulario
    var formData = new FormData(this);

    // Realizar la petición AJAX usando fetch
    fetch("../ControladorProducto", {
        method: "POST",
        body: formData
    })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    // Mostrar notificación de éxito
                    Swal.fire({
                        icon: 'success',
                        title: 'Registro Exitoso',
                        text: data.message,
                        confirmButtonColor: '#FF6363'
                    });
                    // Limpiar el formulario después de éxito
                    document.getElementById("form").reset();
                } else {
                    // Mostrar notificación de error
                    Swal.fire({
                        icon: 'error',
                        title: 'Error',
                        text: data.message,
                        confirmButtonColor: '#FF6363'
                    });
                }
            })
            .catch(error => {
                console.error('Error:', error);
                // Mostrar notificación de error genérico
                Swal.fire({
                    icon: 'error',
                    title: 'Error',
                    text: 'Hubo un problema al registrar el producto. Por favor, intenta nuevamente.',
                    confirmButtonColor: '#FF6363'
                });
            });
});
