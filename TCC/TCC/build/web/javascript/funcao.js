/*function verificasenha(edsenha, edconfirmasenha){
    
    confirmacao = document.getElementById(edsenha).value;
    confirm = document.getElementById(edconfirmasenha).value;
    
    if(confirmacao !== confirm){
        alert("Senha diferentes");
        return (cadastro_pessoa.conf_senha)};
    }*/

function myFunction() {
    var senha1 = document.getElementById("edsenha").value;
    var senha2 = document.getElementById("edconfirmasenha").value;
    document.getElementById("edconfirmasenha").style.backgroundColor = "white"; 
    if (senha1 !== senha2){
       document.getElementById("edconfirmasenha").focus();
       document.getElementById("edconfirmasenha").style.backgroundColor = "red"; 
    }
}



