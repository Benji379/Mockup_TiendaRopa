document.getElementById("form").addEventListener("submit", function (event) {
    event.preventDefault(); // Evitar el envío del formulario por defecto

    var formData = new FormData(this);

    // Realizar la petición AJAX usando fetch
    fetch("../ControladorRegistroUsuario", {
        method: "POST",
        body: formData
    })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    Swal.fire({
                        icon: 'success',
                        title: 'Registro Exitoso',
                        text: data.message,
                        confirmButtonColor: '#FF6363'
                    });
                    document.getElementById("form").reset();
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
                    text: 'Hubo un problema con el registro. Por favor, intenta nuevamente.',
                    confirmButtonColor: '#FF6363'
                });
            });
});
