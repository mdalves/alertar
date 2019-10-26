
$(document).ready(function () {
    $("#enviarAlerta").click(function () {
        enviarAlerta();
    });
    
    $("#limpar").click(function () {
        limpar();
    });
});

function limpar(){
    $('#titulo').val('');
    $('#descricao').val('');
    $('#resultado').empty();
}

function enviarAlerta() {
    $.ajax({
        url: 'Alerta',
        type: 'POST',
        dataType: 'json',
        data: {
            titulo: $('#titulo').val(),
            descricao: $('#descricao').val(),
            grupo: $("[name='grupo']").val()
        },
        success: function (data) {
            if (data.mensagem === "sucesso") {
                $('#resultado').text('Alerta enviado com sucesso');
            } else {
                $('#resultado').text('Falha ao enviar alerta');
            }

            //".text()" é usado em divs
            //".val()" é usado em inputs e textareas
        },
        error: function (data) {
            //alert(data.responseText);
            $('#resultado').append(data);
        }
    });
}