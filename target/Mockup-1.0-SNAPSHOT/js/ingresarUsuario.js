document.getElementById("form").addEventListener("submit", function (event) {
    event.preventDefault(); // Evitar el envío del formulario por defecto

    var formData = new FormData(this);

    // Realizar la petición AJAX usando fetch
    fetch("../ControladorIngreso", {
        method: "POST",
        body: formData
    })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    Swal.fire({
                        icon: 'success',
                        title: 'Bienvenido',
                        text: data.message,
                        confirmButtonColor: '#FF6363'
                    }).then((result) => {
                        if (result.isConfirmed) {
                            window.location.href = "../index.jsp"; // Redirigir a index.jsp
                        }
                    });
                } else {
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
                Swal.fire({
                    icon: 'error',
                    title: 'Error',
                    text: 'Hubo un problema con el ingreso. Por favor, intenta nuevamente.',
                    confirmButtonColor: '#FF6363'
                });
            });
});
