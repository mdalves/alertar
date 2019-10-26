$(document).ready(function () {
    $("#confirmar").click(function () {
        resolverAlerta();
    });
});


function resolverAlerta() {
    alert($("[name='rad_alerta']").val());
//    $.ajax({
//        url: 'resolverAlerta',
//        type: 'POST',
//        dataType: 'json',
//        data: {
//            id: $("[name='rad_alerta']").val()
//        },
//        success: function (data) {
//            alert(data.mensagem);
//        }
//    });
}