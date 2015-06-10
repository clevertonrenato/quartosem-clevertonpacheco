$(document).ready(function () {

    $("#data").mask("00/00/00");
    $("#tempo").mask("00:00");
    $("#datatempo").mask("00/00/0000-00:00");
    $("#cep").mask("00.000-000");
    $("#fone").mask("0000 0000");
    $("#dddfone").mask("(00)0000 0000");
    $("#cpf").mask("000.000.000-00");
    $("#cnpj").mask("00.000.000/0000-00");
    $("#dinheiro").mask("#.##0,00", {reverse: true});
    $("#ip").mask("000.000.000.000");
    $("#percentual").mask("##0,00%");


});


