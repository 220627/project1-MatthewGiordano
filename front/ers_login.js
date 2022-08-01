const url = "http://localhost:3000"

document.getElementById("loginButton").addEventListener('click', loginFunc)

async function loginFunc(){

    console.log("hello");

    //receiving user inputs for logging in
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;

    //sending login cred as json
    let user = {
        user_username:username,
        user_password:password,
    }

    console.log(user);

    let response = await fetch(url + "/login", {

        method: "POST",
        body: JSON.stringify(user),
        credentials: "include"

    })

    console.log(response.status);

   if(response.status === 202){

    document.getElementById("login-row").innerText="Welcome!";

    if(username == 'FormerPresidentBillClinton'){
    location.replace("http://127.0.0.1:5500/ers_manager.html")
    } else {
    location.replace("http://127.0.0.1:5500/ers.html")
    }

} else {
        document.getElementById("login-row").innerText="Login Failed";
        }

}