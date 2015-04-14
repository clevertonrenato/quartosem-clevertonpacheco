$(document).ready(function() {

    /*
     
     $("p").click(function(){               //clique do mouse
     $(this).hide(); 
     });
     
     $("p").dblclick(function() {           //duplo clique do mouse
     $(this).hide();
     });
     
     $("p").mouseenter(function() {         //entrar com mouse no elemento
     alert("Você está sendo filmado!");
     });
     
     $("p").mouseleave(function() {         //sair com o mouse do elemento
     alert("Não se vááááá!");
     });
     
     $("p").mousedown(function() {          //pressionar o botão de clique do mouse
     alert("Não se vááááá!");
     });
     
     $("p").mouseup(function() {             //soltar o botão de clique do mouse
     alert("Não se vááááá!");
     });
     
     $("p").hover(function() {             //passar com o mouse por cima
     alert("Passou com o mouse");
     });
     
     $("#nome").focus(function() {           //ao entrar no campo do nome
     alert("Digite seu nome!");
     });
     
     $("#email").focus(function() {          //ao entrar no campo do email
     alert("Digite seu email!");
     });
     
     $("input").focus(function() {           //ao entrar no campo
     $(this).css("background-color", "blue");
     });
     
     $("input").blur(function() {           //ao sair do campo
     $(this).css("background-color", "white");
     });  
     
     */

    $("input").focus(function() {           //ao entrar no campo
        $(this).css({
            "background-color": "blue",
            "font-size": "120%"
        });
    });

    $("input").blur(function() {           //ao sair do campo
        $(this).css({
            "background-color": "white",
            "font-size": "100%"
        });
    });

    $("button").click(function() {           //ao sair do campo
        $("body").css({
            "background-color": "green",
            "font-size": "160%"
        });
    });
    
    $("button").dblclick(function() {           //duplo clique do mouse
        $("body").css({
            "background-color": "aquamarine",
            "font-size": "100%"
        });
    });

});