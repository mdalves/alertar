$(document).ready(function () {
    $("#userEntrar").click(function () {
        $.ajax({
            url: 'Login',
            type: 'POST',
            dataType: 'json',
            data: {
                userLogin: $('#userLogin').val(),
                userPassword: $('#userPassword').val()
            },
            success: function (data) {
                if (data.userId > 0) {
                    $('#resultado').text('Bem vindo(a) ' + data.userName);
                } else {
                    $('#resultado').text('Usuário não cadastrado');
                }

                //".text()" é usado em divs
                //".val()" é usado em inputs e textareas
            },
            error: function (data) {
                //alert(data.responseText);
                $('#resultado').append(data);
            }
        });
    });

    $("#userEntrar").click(function () {
    });
});