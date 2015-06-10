function validar(nome, senha, email) {

    vNome = document.getElementById(nome).value;
    vSenha = document.getElementById(senha).value;
    vEmail = document.getElementById(email).value;
    emailerro = false;
     $("#nome").css("background-color", "white");
      $("#senha").css("background-color", "white");
       $("#email").css("background-color", "white");

    if (vNome.length < 2) {

        alert("Informe seu nome Zé!");
        $("#nome").css("background-color", "red");
    }

    if (vSenha.length < 6 || vSenha.length > 8) {

        alert("Senha deve conter entre 6 e 8 dígitos");
         $("#senha").css("background-color", "red");
    }

    if (vEmail.indexOf("@") === -1) {

        emailerro = true;
    }

    if (vEmail.indexOf(".") === -1) {

        emailerro = true;
    }

    if (vEmail.indexOf(" ") !== -1) {

        emailerro = true;
    }

    if (emailerro === true) {

        alert("E-mail inválido");
         $("#email").css("background-color", "red");
    }

}

