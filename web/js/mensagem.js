$(document).ready(function () {
    $("input:radio[value='1']").prop('checked', true);


    $("#cadastrarMensagem").click(function () {
        //validarFormulario();
        //enviarMensagemPreCadastrada();
        cadastrarMensagem();
    });

    $(".nivel").click(function () {
        listarMensagemPreCadastrada(this);
    });

    $("#limpar").click(function () {
        limpar();
    });
});

function validarFormulario() {
    if ($("#titulo").val() === '') {
        $("#resultado").addClass("alert-danger");
        $("#resultado").text("Teste");
    }
}

function cadastrarMensagem() {
    $.ajax({
        url: 'Mensagem',
        type: 'POST',
        dataType: 'json',
        data: {
            action: 'cadastrar',
            titulo: $("#titulo").val(),
            descricao: $("#descricao").val(),
            nivel: $("input[name='nivel']:checked").val(),
            grupo: $("[name='grupo']").val(),
            substancia: $("[name='substancia']").val()
        },
        success: function (data) {
            if ($("#titulo").val() === '') {
                $("#resultado").addClass("alert-danger");
                $("#resultado").text("Teste");
            }
        }

    });
}

function enviarMensagemPreCadastrada() {
    $.ajax({
        url: 'Mensagem',
        type: 'POST',
        dataType: 'json',
        data: {
            action: 'enviar',
            titulo: $("#dioxidoCarbono1").attr('name'),
            descricao: $("#descricao").val(),
            nivel: $("input[name='nivel']:checked").val(),
            grupo: $("[name='grupo']").val(),
            substancia: $("[name='substancia']").val()
        },
        success: function (data) {

        }

    });
}


function listarMensagemPreCadastrada(nivel) {

    $.ajax({
        url: 'Mensagem',
        type: 'POST',
        dataType: 'json',
        data: {
            action: 'listar',
            substancia: $("#carbono").text(),
            nivel: $(nivel).val()
        },
        success: function (data) {
            if (data.mensagem === "sucesso") {
                $('#resultado').text('Alerta enviado com sucesso');
            } else {
                $('#resultado').text('Alerta enviado com sucesso');
            }
        }

    });
}


function limpar() {
    $('#resultado').empty();
}