function tiracor() {
    confirmacao = document.getElementById('ednome');
    confirmacao.style.backgroundColor = "white";
    
    confirmacao1 = document.getElementById('edemail');
    confirmacao1.style.backgroundColor = "white";
    
    confirmacao2 = document.getElementById('edendereco');
    confirmacao2.style.backgroundColor = "white";
    
    confirmacao3 = document.getElementById('ednumero');
    confirmacao3.style.backgroundColor = "white";
    
    confirmacao4 = document.getElementById('edbairro');
    confirmacao4.style.backgroundColor = "white";
    
    confirmacao5 = document.getElementById('edtelefone');
    confirmacao5.style.backgroundColor = "white";
    
    confirmacao6 = document.getElementById('edsenha');
    confirmacao6.style.backgroundColor = "white";
    
    confirmacao7 = document.getElementById('edconfirmasenha');
    confirmacao7.style.backgroundColor = "white";
    
    confirmacao8 = document.getElementById('edcpf');
    confirmacao8.style.backgroundColor = "white";

}



function validar() {

// Pegar Valores dos Campos através dos seus nomes
    var nome = form1.ednome.value;
    var endereco = form1.edendereco.value;
    var numero = form1.ednumero.value;
    var bairro = form1.edbairro.value;
    var telefone = form1.edtelefone.value;
    var email = form1.edemail.value;
    var senha = form1.edsenha.value;
    var senha2 = form1.edconfirmasenha.value;
    form1.ednome.style.backgroundColor = "white";
    form1.edemail.style.backgroundColor = "white";
    form1.edendereco.style.backgroundColor = "white";
    form1.ednumero.style.backgroundColor = "white";
    form1.edbairro.style.backgroundColor = "white";
    form1.edtelefone.style.backgroundColor = "white";
    form1.edsenha.style.backgroundColor = "white";
    form1.edconfirmasenha.style.backgroundColor = "white";
    form1.edcpf.style.backgroundColor = "white";



    if ((nome === "") || (nome.length < 3)) {
        alert('Campo Nome obrigatorio e deve ter mais de 2 caracteres');
        form1.ednome.focus();
        form1.ednome.style.backgroundColor = "red";

        return false;
    }

    if (email === "")
    {
        alert('Campo email obrigatorio.');
        form1.edemail.focus();
        form1.edemail.style.backgroundColor = "red";
        return false;
    }

    if ((email.indexOf("@") < 1) || (email.indexOf('.') < 1))
    {
        alert('Email Invalido.');
        form1.edemail.focus();
        form1.edemail.style.backgroundColor = "red";
        return false;
    }

    if (endereco === "") {
        alert('Campo Endereco Obrigatorio.');
        form1.edendereco.focus();
        form1.edendereco.style.backgroundColor = "red";
        return false;
    }

    if (numero === "") {
        alert('Campo Numero Obrigatorio.');
        form1.ednumero.focus();
        form1.ednumero.style.backgroundColor = "red";
        return false;
    }

    if (bairro === "") {
        alert('Campo Bairro Obrigatorio.');
        form1.edbairro.focus();
        form1.edbairro.style.backgroundColor = "red";
        return false;
    }

    if (telefone === "") {
        alert('Campo Telefone Obrigatorio.');
        form1.edtelefone.focus();
        form1.edtelefone.style.backgroundColor = "red";
        return false;
    }

    if (senha === "") {
        alert('Campo senha Obrigatorio.');
        form1.edsenha.focus();
        form1.edsenha.style.backgroundColor = "red";
        return false;
    }

    if (senha2 === "") {
        alert('Campo Confirma Senha Obrigatorio.');
        form1.edconfirmasenha.focus();
        form1.edconfirmasenha.style.backgroundColor = "red";
        return false;
    }

    if (senha !== senha2) {
        alert('As Senhas devem ser iguais.');
        form1.edsenha.focus();
        form1.edsenha.style.backgroundColor = "red";
        form1.edconfirmasenha.focus();
        form1.edconfirmasenha.style.backgroundColor = "red";
        return false;
    }

    var vCPF = form1.edcpf.value;

    soma = 0;
    resto = 0;
    repetido = true;
    valida = true;

    primeiro = parseInt(vCPF.substring(0, 1));
    for (i = 1; i <= 11; i++) {
        if (primeiro !== parseInt(vCPF.substring(i - 1, i))) {
            repetido = false;
        }
    }

    if (repetido === false) {
        for (i = 1; i <= 9; i++)
            soma += parseInt(vCPF.substring(i - 1, i)) * (11 - i);

        resto = (soma * 10) % 11;

        if ((resto === 10) || (resto === 11))
            resto = 0;

        if (resto !== parseInt(vCPF.substring(9, 10)))
            valida = false;

        soma = 0;

        for (i = 1; i <= 10; i++)
            soma += parseInt(vCPF.substring(i - 1, i)) * (12 - i);

        resto = (soma * 10) % 11;

        if ((resto === 10) || (resto === 11))
            resto = 0;

        if (resto !== parseInt(vCPF.substring(10, 11)))
            valida = false;

        if (valida === false) {
            alert("Digite um CPF valido!");
            form1.edcpf.focus();
            form1.edcpf.style.backgroundColor = "red";
        }

    }
    else {
        alert("Número repetido!");
        form1.edcpf.focus();
        form1.edcpf.style.backgroundColor = "red";
    }


    return true;
}