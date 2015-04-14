$(function () {
    $("#movel").draggable();

    $("#noticia").accordion();

    var vPalavras = ["Cleverton", "Diosefer", "Patrik", "Rosimeri"];

    $("#palavras").autocomplete({
        source: vPalavras
    });

    $("#dataNasc").datepicker();

    $("#tabs").tabs();


});

 